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

import com.wallellen.wechat.cp.bean.messagebuilder.FileBuilder;
import com.wallellen.wechat.cp.bean.messagebuilder.ImageBuilder;
import com.wallellen.wechat.cp.bean.messagebuilder.NewsBuilder;
import com.wallellen.wechat.cp.bean.messagebuilder.TextBuilder;
import com.wallellen.wechat.cp.bean.messagebuilder.VideoBuilder;
import com.wallellen.wechat.cp.bean.messagebuilder.VoiceBuilder;
import com.wallellen.wechat.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息
 *
 * @author wallellen
 */
public class WxCpMessage implements Serializable {

    private String toUser;
    private String toParty;
    private String toTag;
    private String agentId;
    private String msgType;
    private String content;
    private String mediaId;
    private String thumbMediaId;
    private String title;
    private String description;
    private String musicUrl;
    private String hqMusicUrl;
    private String safe;
    private List<WxArticle> articles = new ArrayList<>();

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

    /**
     * 获得文件消息builder
     *
     * @return
     */
    public static FileBuilder FILE() {
        return new FileBuilder();
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToParty() {
        return toParty;
    }

    public void setToParty(String toParty) {
        this.toParty = toParty;
    }

    public String getToTag() {
        return toTag;
    }

    public void setToTag(String toTag) {
        this.toTag = toTag;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getMsgType() {
        return msgType;
    }

    /**
     * <pre>
     * 请使用
     * {@link com.wallellen.wechat.common.api.WxConsts#CUSTOM_MSG_TEXT}
     * {@link com.wallellen.wechat.common.api.WxConsts#CUSTOM_MSG_IMAGE}
     * {@link com.wallellen.wechat.common.api.WxConsts#CUSTOM_MSG_VOICE}
     * {@link com.wallellen.wechat.common.api.WxConsts#CUSTOM_MSG_MUSIC}
     * {@link com.wallellen.wechat.common.api.WxConsts#CUSTOM_MSG_VIDEO}
     * {@link com.wallellen.wechat.common.api.WxConsts#CUSTOM_MSG_NEWS}
     * </pre>
     *
     * @param msgType
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
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

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public List<WxArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<WxArticle> articles) {
        this.articles = articles;
    }

    public String toJson() {
        return WxCpGsonBuilder.INSTANCE.create().toJson(this);
    }

    public static class WxArticle {

        private String title;
        private String description;
        private String url;
        private String picUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

    }

}
