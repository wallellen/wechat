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

package com.wallellen.wechat.cp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wallellen.wechat.common.util.xml.XStreamCDataConverter;
import com.wallellen.wechat.cp.api.WxCpConfigStorage;
import com.wallellen.wechat.cp.bean.outxmlbuilder.ImageBuilder;
import com.wallellen.wechat.cp.bean.outxmlbuilder.NewsBuilder;
import com.wallellen.wechat.cp.bean.outxmlbuilder.TextBuilder;
import com.wallellen.wechat.cp.bean.outxmlbuilder.VideoBuilder;
import com.wallellen.wechat.cp.bean.outxmlbuilder.VoiceBuilder;
import com.wallellen.wechat.cp.util.crypto.WxCpCryptUtil;
import com.wallellen.wechat.cp.util.xml.XStreamTransformer;

@XStreamAlias("xml")
public abstract class WxCpXmlOutMessage {

    @XStreamAlias("ToUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String fromUserName;

    @XStreamAlias("CreateTime")
    protected Long createTime;

    @XStreamAlias("MsgType")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String msgType;

    /**
     * 获得文本消息builder
     *
     * @return
     */
    public static TextBuilder TEXT() {
        return new TextBuilder();
    }

    /**
     * 获得图片消息builder
     *
     * @return
     */
    public static ImageBuilder IMAGE() {
        return new ImageBuilder();
    }

    /**
     * 获得语音消息builder
     *
     * @return
     */
    public static VoiceBuilder VOICE() {
        return new VoiceBuilder();
    }

    /**
     * 获得视频消息builder
     *
     * @return
     */
    public static VideoBuilder VIDEO() {
        return new VideoBuilder();
    }

    /**
     * 获得图文消息builder
     *
     * @return
     */
    public static NewsBuilder NEWS() {
        return new NewsBuilder();
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    protected String toXml() {
        return XStreamTransformer.toXml((Class) this.getClass(), this);
    }

    /**
     * 转换成加密的xml格式
     *
     * @return
     */
    public String toEncryptedXml(WxCpConfigStorage wxCpConfigStorage) {
        String plainXml = toXml();
        WxCpCryptUtil pc = new WxCpCryptUtil(wxCpConfigStorage);
        return pc.encrypt(plainXml);
    }
}
