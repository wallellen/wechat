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
import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.common.session.WxSessionManager;
import com.wallellen.wechat.cp.bean.WxCpXmlMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class WxCpMessageRouterRule {

    private final WxCpMessageRouter routerBuilder;

    private boolean async = true;

    private String fromUser;

    private String msgType;

    private String event;

    private String eventKey;

    private String content;

    private String rContent;

    private WxCpMessageMatcher matcher;

    private boolean reEnter = false;

    private Integer agentId;

    private List<WxCpMessageHandler> handlers = new ArrayList<>();

    private List<WxCpMessageInterceptor> interceptors = new ArrayList<>();

    protected WxCpMessageRouterRule(WxCpMessageRouter routerBuilder) {
        this.routerBuilder = routerBuilder;
    }

    /**
     * 设置是否异步执行，默认是true
     *
     * @param async
     * @return
     */
    public WxCpMessageRouterRule async(boolean async) {
        this.async = async;
        return this;
    }

    /**
     * 如果agentId匹配
     *
     * @param agentId
     * @return
     */
    public WxCpMessageRouterRule agentId(Integer agentId) {
        this.agentId = agentId;
        return this;
    }

    /**
     * 如果msgType等于某值
     *
     * @param msgType
     * @return
     */
    public WxCpMessageRouterRule msgType(String msgType) {
        this.msgType = msgType;
        return this;
    }

    /**
     * 如果event等于某值
     *
     * @param event
     * @return
     */
    public WxCpMessageRouterRule event(String event) {
        this.event = event;
        return this;
    }

    /**
     * 如果eventKey等于某值
     *
     * @param eventKey
     * @return
     */
    public WxCpMessageRouterRule eventKey(String eventKey) {
        this.eventKey = eventKey;
        return this;
    }

    /**
     * 如果content等于某值
     *
     * @param content
     * @return
     */
    public WxCpMessageRouterRule content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 如果content匹配该正则表达式
     *
     * @param regex
     * @return
     */
    public WxCpMessageRouterRule rContent(String regex) {
        this.rContent = regex;
        return this;
    }

    /**
     * 如果fromUser等于某值
     *
     * @param fromUser
     * @return
     */
    public WxCpMessageRouterRule fromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    /**
     * 如果消息匹配某个matcher，用在用户需要自定义更复杂的匹配规则的时候
     *
     * @param matcher
     * @return
     */
    public WxCpMessageRouterRule matcher(WxCpMessageMatcher matcher) {
        this.matcher = matcher;
        return this;
    }

    /**
     * 设置微信消息拦截器
     *
     * @param interceptor
     * @return
     */
    public WxCpMessageRouterRule interceptor(WxCpMessageInterceptor interceptor) {
        return interceptor(interceptor, (WxCpMessageInterceptor[]) null);
    }

    /**
     * 设置微信消息拦截器
     *
     * @param interceptor
     * @param otherInterceptors
     * @return
     */
    public WxCpMessageRouterRule interceptor(WxCpMessageInterceptor interceptor, WxCpMessageInterceptor... otherInterceptors) {
        this.interceptors.add(interceptor);
        if (otherInterceptors != null && otherInterceptors.length > 0) {
            for (WxCpMessageInterceptor i : otherInterceptors) {
                this.interceptors.add(i);
            }
        }
        return this;
    }

    /**
     * 设置微信消息处理器
     *
     * @param handler
     * @return
     */
    public WxCpMessageRouterRule handler(WxCpMessageHandler handler) {
        return handler(handler, (WxCpMessageHandler[]) null);
    }

    /**
     * 设置微信消息处理器
     *
     * @param handler
     * @param otherHandlers
     * @return
     */
    public WxCpMessageRouterRule handler(WxCpMessageHandler handler, WxCpMessageHandler... otherHandlers) {
        this.handlers.add(handler);
        if (otherHandlers != null && otherHandlers.length > 0) {
            for (WxCpMessageHandler i : otherHandlers) {
                this.handlers.add(i);
            }
        }
        return this;
    }

    /**
     * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
     *
     * @return
     */
    public WxCpMessageRouter end() {
        this.routerBuilder.getRules().add(this);
        return this.routerBuilder;
    }

    /**
     * 规则结束，但是消息还会进入其他规则
     *
     * @return
     */
    public WxCpMessageRouter next() {
        this.reEnter = true;
        return end();
    }

    protected boolean test(WxCpXmlMessage wxMessage) {
        return
                (this.fromUser == null || this.fromUser.equals(wxMessage.getFromUserName()))
                        &&
                        (this.agentId == null || this.agentId.equals(wxMessage.getAgentId()))
                        &&
                        (this.msgType == null || this.msgType.equals(wxMessage.getMsgType()))
                        &&
                        (this.event == null || this.event.equals(wxMessage.getEvent()))
                        &&
                        (this.eventKey == null || this.eventKey.equals(wxMessage.getEventKey()))
                        &&
                        (this.content == null || this.content
                                .equals(wxMessage.getContent() == null ? null : wxMessage.getContent().trim()))
                        &&
                        (this.rContent == null || Pattern
                                .matches(this.rContent, wxMessage.getContent() == null ? "" : wxMessage.getContent().trim()))
                        &&
                        (this.matcher == null || this.matcher.match(wxMessage))
                ;
    }

    /**
     * 处理微信推送过来的消息
     *
     * @param wxMessage
     * @return true 代表继续执行别的router，false 代表停止执行别的router
     */
    protected WxCpXmlOutMessage service(WxCpXmlMessage wxMessage,
                                        WxCpService wxCpService,
                                        WxSessionManager sessionManager,
                                        WxErrorExceptionHandler exceptionHandler) {

        try {

            Map<String, Object> context = new HashMap<>();
            // 如果拦截器不通过
            for (WxCpMessageInterceptor interceptor : this.interceptors) {
                if (!interceptor.intercept(wxMessage, context, wxCpService, sessionManager)) {
                    return null;
                }
            }

            // 交给handler处理
            WxCpXmlOutMessage res = null;
            for (WxCpMessageHandler handler : this.handlers) {
                // 返回最后handler的结果
                res = handler.handle(wxMessage, context, wxCpService, sessionManager);
            }
            return res;

        } catch (WxErrorException e) {
            exceptionHandler.handle(e);
        }

        return null;

    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public void setMatcher(WxCpMessageMatcher matcher) {
        this.matcher = matcher;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public void setHandlers(List<WxCpMessageHandler> handlers) {
        this.handlers = handlers;
    }

    public void setInterceptors(List<WxCpMessageInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public boolean isReEnter() {
        return reEnter;
    }

    public void setReEnter(boolean reEnter) {
        this.reEnter = reEnter;
    }

}
