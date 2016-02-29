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

package com.wallellen.wechat.mp.demo;

import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.mp.api.WxMpService;
import com.wallellen.wechat.mp.bean.result.WxMpOAuth2AccessToken;
import com.wallellen.wechat.mp.bean.result.WxMpUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WxMpOAuth2Servlet extends HttpServlet {

    protected WxMpService wxMpService;

    public WxMpOAuth2Servlet(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String code = request.getParameter("code");
        try {
            response.getWriter().println("<h1>code</h1>");
            response.getWriter().println(code);

            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            response.getWriter().println("<h1>access token</h1>");
            response.getWriter().println(wxMpOAuth2AccessToken.toString());

            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            response.getWriter().println("<h1>user info</h1>");
            response.getWriter().println(wxMpUser.toString());

            wxMpOAuth2AccessToken = wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
            response.getWriter().println("<h1>after refresh</h1>");
            response.getWriter().println(wxMpOAuth2AccessToken.toString());

        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        response.getWriter().flush();
        response.getWriter().close();

    }

}
