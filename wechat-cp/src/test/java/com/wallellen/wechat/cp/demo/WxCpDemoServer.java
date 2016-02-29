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

package com.wallellen.wechat.cp.demo;

import com.wallellen.wechat.common.session.WxSessionManager;
import com.wallellen.wechat.cp.api.WxCpConfigStorage;
import com.wallellen.wechat.cp.api.WxCpMessageHandler;
import com.wallellen.wechat.cp.api.WxCpMessageRouter;
import com.wallellen.wechat.cp.api.WxCpService;
import com.wallellen.wechat.cp.api.WxCpServiceImpl;
import com.wallellen.wechat.cp.bean.WxCpXmlMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutTextMessage;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.InputStream;
import java.util.Map;

public class WxCpDemoServer {

    private static WxCpConfigStorage wxCpConfigStorage;
    private static WxCpService wxCpService;
    private static WxCpMessageRouter wxCpMessageRouter;

    public static void main(String[] args) throws Exception {
        initWeixin();

        Server server = new Server(8080);

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        ServletHolder endpointServletHolder = new ServletHolder(new WxCpEndpointServlet(wxCpConfigStorage, wxCpService, wxCpMessageRouter));
        servletHandler.addServletWithMapping(endpointServletHolder, "/*");

        ServletHolder oauthServletHolder = new ServletHolder(new WxCpOAuth2Servlet(wxCpService));
        servletHandler.addServletWithMapping(oauthServletHolder, "/oauth2/*");

        server.start();
        server.join();
    }

    private static void initWeixin() {
        InputStream is1 = ClassLoader.getSystemResourceAsStream("test-config.xml");
        WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(is1);

        wxCpConfigStorage = config;
        wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);

        WxCpMessageHandler handler = new WxCpMessageHandler() {
            @Override
            public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context,
                                            WxCpService wxCpService, WxSessionManager sessionManager) {
                WxCpXmlOutTextMessage m = WxCpXmlOutMessage
                        .TEXT()
                        .content("测试加密消息")
                        .fromUser(wxMessage.getToUserName())
                        .toUser(wxMessage.getFromUserName())
                        .build();
                return m;
            }
        };

        WxCpMessageHandler oauth2handler = new WxCpMessageHandler() {
            @Override
            public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context,
                                            WxCpService wxCpService, WxSessionManager sessionManager) {
                String href = "<a href=\"" + wxCpService.oauth2buildAuthorizationUrl(wxCpConfigStorage.getOauth2redirectUri(), null)
                        + "\">测试oauth2</a>";
                return WxCpXmlOutMessage
                        .TEXT()
                        .content(href)
                        .fromUser(wxMessage.getToUserName())
                        .toUser(wxMessage.getFromUserName()).build();
            }
        };

        wxCpMessageRouter = new WxCpMessageRouter(wxCpService);
        wxCpMessageRouter
                .rule()
                .async(false)
                .content("哈哈") // 拦截内容为“哈哈”的消息
                .handler(handler)
                .end()
                .rule()
                .async(false)
                .content("oauth")
                .handler(oauth2handler)
                .end()
        ;

    }
}
