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

package com.wallellen.wechat.mp.bean;

import com.wallellen.wechat.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * OpenId列表群发的消息
 *
 * @author chanjarster
 */
public class WxMpMassOpenIdsMessage implements Serializable {

    private List<String> toUsers = new ArrayList<>();
    private String msgType;
    private String content;
    private String mediaId;

    public WxMpMassOpenIdsMessage() {
        super();
    }

    public String getMsgType() {
        return msgType;
    }

    /**
     * <pre>
     * 请使用
     * {@link com.wallellen.wechat.common.api.WxConsts#MASS_MSG_IMAGE}
     * {@link com.wallellen.wechat.common.api.WxConsts#MASS_MSG_NEWS}
     * {@link com.wallellen.wechat.common.api.WxConsts#MASS_MSG_TEXT}
     * {@link com.wallellen.wechat.common.api.WxConsts#MASS_MSG_VIDEO}
     * {@link com.wallellen.wechat.common.api.WxConsts#MASS_MSG_VOICE}
     * 如果msgtype和media_id不匹配的话，会返回系统繁忙的错误
     * </pre>
     *
     * @param msgType
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String toJson() {
        return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

    /**
     * OpenId列表，最多支持10,000个
     *
     * @return
     */
    public List<String> getToUsers() {
        return toUsers;
    }

    /**
     * 添加OpenId，最多支持10,000个
     *
     * @param openId
     */
    public void addUser(String openId) {
        this.toUsers.add(openId);
    }
}
