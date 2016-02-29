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

import com.wallellen.wechat.common.bean.WxAccessToken;

import java.io.File;

/**
 * 微信客户端配置存储
 *
 * @author wallellen
 */
public interface WxCpConfigStorage {

    public String getAccessToken();

    public boolean isAccessTokenExpired();

    /**
     * 强制将access token过期掉
     */
    public void expireAccessToken();

    public void updateAccessToken(WxAccessToken accessToken);

    public void updateAccessToken(String accessToken, int expiresIn);

    public String getJsapiTicket();

    public boolean isJsapiTicketExpired();

    /**
     * 强制将jsapi ticket过期掉
     */
    public void expireJsapiTicket();

    /**
     * 应该是线程安全的
     *
     * @param jsapiTicket
     */
    public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

    public String getCorpId();

    public String getCorpSecret();

    public String getAgentId();

    public String getToken();

    public String getAesKey();

    public long getExpiresTime();

    public String getOauth2redirectUri();

    public String getHttp_proxy_host();

    public int getHttp_proxy_port();

    public String getHttp_proxy_username();

    public String getHttp_proxy_password();

    public File getTmpDirFile();

}
