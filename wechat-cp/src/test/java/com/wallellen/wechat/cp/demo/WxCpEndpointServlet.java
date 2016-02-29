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

import com.wallellen.wechat.common.util.StringUtils;
import com.wallellen.wechat.cp.api.WxCpConfigStorage;
import com.wallellen.wechat.cp.api.WxCpMessageRouter;
import com.wallellen.wechat.cp.api.WxCpService;
import com.wallellen.wechat.cp.bean.WxCpXmlMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutMessage;
import com.wallellen.wechat.cp.util.crypto.WxCpCryptUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wallellen
 */
public class WxCpEndpointServlet extends HttpServlet {

    protected WxCpConfigStorage wxCpConfigStorage;
    protected WxCpService wxCpService;
    protected WxCpMessageRouter wxCpMessageRouter;

    public WxCpEndpointServlet(WxCpConfigStorage wxCpConfigStorage, WxCpService wxCpService,
                               WxCpMessageRouter wxCpMessageRouter) {
        this.wxCpConfigStorage = wxCpConfigStorage;
        this.wxCpService = wxCpService;
        this.wxCpMessageRouter = wxCpMessageRouter;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String msgSignature = request.getParameter("msg_signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String echostr = request.getParameter("echostr");

        if (StringUtils.isNotBlank(echostr)) {
            if (!wxCpService.checkSignature(msgSignature, timestamp, nonce, echostr)) {
                // 消息签名不正确，说明不是公众平台发过来的消息
                response.getWriter().println("非法请求");
                return;
            }
            WxCpCryptUtil cryptUtil = new WxCpCryptUtil(wxCpConfigStorage);
            String plainText = cryptUtil.decrypt(echostr);
            // 说明是一个仅仅用来验证的请求，回显echostr
            response.getWriter().println(plainText);
            return;
        }

        WxCpXmlMessage inMessage = WxCpXmlMessage
                .fromEncryptedXml(request.getInputStream(), wxCpConfigStorage, timestamp, nonce, msgSignature);
        WxCpXmlOutMessage outMessage = wxCpMessageRouter.route(inMessage);
        if (outMessage != null) {
            response.getWriter().write(outMessage.toEncryptedXml(wxCpConfigStorage));
        }

        return;
    }

}
