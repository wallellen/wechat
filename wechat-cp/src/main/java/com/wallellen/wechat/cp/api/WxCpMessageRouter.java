/*
 * This file Copyright (c) 2016. Walle.
 * (http://www.wallellen.com). All rights reserved.
 *
 *
 * This file is dual-licensed under both the
 * Walle Agreement (WA) and the GNU General Public License.
 * You may elect to use one or the other of these licenses.
 *
 * This file is distributed in the hope that it will be
 * useful, but AS-IS and WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE, or NONINFRINGEMENT.
 * Redistribution, except as permitted by whichever of the GPL
 * or WA you select, is prohibited.
 *
 * 1. For the GPL license (GPL), you can redistribute and/or
 * modify this file under the terms of the GNU General
 * Public License, Version 3, as published by the Free Software
 * Foundation.  You should have received a copy of the GNU
 * General Public License, Version 3 along with this program;
 * if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * 2. For the Walle Agreement (WA), this file
 * and the accompanying materials are made available under the
 * terms of the WA which accompanies this distribution, and
 * is available at http://www.wallellen.com/agreement.html
 *
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */

package com.wallellen.wechat.cp.api;

import com.wallellen.wechat.common.api.WxErrorExceptionHandler;
import com.wallellen.wechat.common.api.WxMessageDuplicateChecker;
import com.wallellen.wechat.common.api.WxMessageInMemoryDuplicateChecker;
import com.wallellen.wechat.common.session.InternalSession;
import com.wallellen.wechat.common.session.InternalSessionManager;
import com.wallellen.wechat.common.session.StandardSessionManager;
import com.wallellen.wechat.common.session.WxSessionManager;
import com.wallellen.wechat.common.util.LogExceptionHandler;
import com.wallellen.wechat.cp.bean.WxCpXmlMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <pre>
 * 微信消息路由器，通过代码化的配置，把来自微信的消息交给handler处理
 *
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link WxCpMessageRouterRule#next()}
 * 3. 规则的结束必须用{@link WxCpMessageRouterRule#end()}或者{@link WxCpMessageRouterRule#next()}，否则不会生效
 *
 * 使用方法：
 * WxCpMessageRouter router = new WxCpMessageRouter();
 * router
 *   .rule()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *       // 另外一个匹配规则
 *   .end()
 * ;
 *
 * // 将WxXmlMessage交给消息路由器
 * router.route(message);
 *
 * </pre>
 *
 * @author wallellen
 */
public class WxCpMessageRouter {

    private static final int DEFAULT_THREAD_POOL_SIZE = 100;
    protected final Logger log = LoggerFactory.getLogger(WxCpMessageRouter.class);
    private final List<WxCpMessageRouterRule> rules = new ArrayList<WxCpMessageRouterRule>();

    private final WxCpService wxCpService;

    private ExecutorService executorService;

    private WxMessageDuplicateChecker messageDuplicateChecker;

    private WxSessionManager sessionManager;

    private WxErrorExceptionHandler exceptionHandler;

    public WxCpMessageRouter(WxCpService wxCpService) {
        this.wxCpService = wxCpService;
        this.executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
        this.messageDuplicateChecker = new WxMessageInMemoryDuplicateChecker();
        this.sessionManager = new StandardSessionManager();
        this.exceptionHandler = new LogExceptionHandler();
    }

    /**
     * <pre>
     * 设置自定义的 {@link ExecutorService}
     * 如果不调用该方法，默认使用 Executors.newFixedThreadPool(100)
     * </pre>
     *
     * @param executorService
     */
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * <pre>
     * 设置自定义的 {@link com.wallellen.wechat.common.api.WxMessageDuplicateChecker}
     * 如果不调用该方法，默认使用 {@link com.wallellen.wechat.common.api.WxMessageInMemoryDuplicateChecker}
     * </pre>
     *
     * @param messageDuplicateChecker
     */
    public void setMessageDuplicateChecker(WxMessageDuplicateChecker messageDuplicateChecker) {
        this.messageDuplicateChecker = messageDuplicateChecker;
    }

    /**
     * <pre>
     * 设置自定义的{@link com.wallellen.wechat.common.session.WxSessionManager}
     * 如果不调用该方法，默认使用 {@link com.wallellen.wechat.common.session.StandardSessionManager}
     * </pre>
     *
     * @param sessionManager
     */
    public void setSessionManager(WxSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * <pre>
     * 设置自定义的{@link com.wallellen.wechat.common.api.WxErrorExceptionHandler}
     * 如果不调用该方法，默认使用 {@link com.wallellen.wechat.common.util.LogExceptionHandler}
     * </pre>
     *
     * @param exceptionHandler
     */
    public void setExceptionHandler(WxErrorExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    List<WxCpMessageRouterRule> getRules() {
        return this.rules;
    }

    /**
     * 开始一个新的Route规则
     *
     * @return
     */
    public WxCpMessageRouterRule rule() {
        return new WxCpMessageRouterRule(this);
    }

    /**
     * 处理微信消息
     *
     * @param wxMessage
     */
    public WxCpXmlOutMessage route(final WxCpXmlMessage wxMessage) {
        if (isDuplicateMessage(wxMessage)) {
            // 如果是重复消息，那么就不做处理
            return null;
        }

        final List<WxCpMessageRouterRule> matchRules = new ArrayList<WxCpMessageRouterRule>();
        // 收集匹配的规则
        for (final WxCpMessageRouterRule rule : rules) {
            if (rule.test(wxMessage)) {
                matchRules.add(rule);
                if (!rule.isReEnter()) {
                    break;
                }
            }
        }

        if (matchRules.size() == 0) {
            return null;
        }

        WxCpXmlOutMessage res = null;
        final List<Future> futures = new ArrayList<Future>();
        for (final WxCpMessageRouterRule rule : matchRules) {
            // 返回最后一个非异步的rule的执行结果
            if (rule.isAsync()) {
                futures.add(
                        executorService.submit(new Runnable() {
                            public void run() {
                                rule.service(wxMessage, wxCpService, sessionManager, exceptionHandler);
                            }
                        })
                );
            } else {
                res = rule.service(wxMessage, wxCpService, sessionManager, exceptionHandler);
                // 在同步操作结束，session访问结束
                log.debug("End session access: async=false, sessionId={}", wxMessage.getFromUserName());
                sessionEndAccess(wxMessage);
            }
        }

        if (futures.size() > 0) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (Future future : futures) {
                        try {
                            future.get();
                            log.debug("End session access: async=true, sessionId={}", wxMessage.getFromUserName());
                            // 异步操作结束，session访问结束
                            sessionEndAccess(wxMessage);
                        } catch (InterruptedException e) {
                            log.error("Error happened when wait task finish", e);
                        } catch (ExecutionException e) {
                            log.error("Error happened when wait task finish", e);
                        }
                    }
                }
            });
        }
        return res;
    }

    protected boolean isDuplicateMessage(WxCpXmlMessage wxMessage) {

        String messageId = "";
        if (wxMessage.getMsgId() == null) {
            messageId = String.valueOf(wxMessage.getCreateTime())
                    + "-" + String.valueOf(wxMessage.getAgentId() == null ? "" : wxMessage.getAgentId())
                    + "-" + wxMessage.getFromUserName()
                    + "-" + String.valueOf(wxMessage.getEventKey() == null ? "" : wxMessage.getEventKey())
                    + "-" + String.valueOf(wxMessage.getEvent() == null ? "" : wxMessage.getEvent())
            ;
        } else {
            messageId = String.valueOf(wxMessage.getMsgId());
        }

        if (messageDuplicateChecker.isDuplicate(messageId)) {
            return true;
        }
        return false;

    }

    /**
     * 对session的访问结束
     *
     * @param wxMessage
     */
    protected void sessionEndAccess(WxCpXmlMessage wxMessage) {

        InternalSession session = ((InternalSessionManager) sessionManager).findSession(wxMessage.getFromUserName());
        if (session != null) {
            session.endAccess();
        }

    }


}
