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

/**
 * 分组群发的消息
 *
 * @author chanjarster
 */
public class WxMpMassGroupMessage implements Serializable {

    private Long groupId;
    private String msgtype;
    private String content;
    private String mediaId;

    public WxMpMassGroupMessage() {
        super();
    }

    public String getMsgtype() {
        return msgtype;
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
     * @param msgtype
     */
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
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

    public Long getGroupId() {
        return groupId;
    }

    /**
     * 如果不设置则就意味着发给所有用户
     *
     * @param groupId
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}
