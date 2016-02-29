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

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.common.util.xml.XStreamCDataConverter;

@XStreamAlias("xml")
public class WxMpXmlOutMusicMessage extends WxMpXmlOutMessage {

    @XStreamAlias("Music")
    protected final Music music = new Music();

    public WxMpXmlOutMusicMessage() {
        this.msgType = WxConsts.XML_MSG_MUSIC;
    }

    public String getTitle() {
        return music.getTitle();
    }

    public void setTitle(String title) {
        music.setTitle(title);
    }

    public String getDescription() {
        return music.getDescription();
    }

    public void setDescription(String description) {
        music.setDescription(description);
    }

    public String getThumbMediaId() {
        return music.getThumbMediaId();
    }

    public void setThumbMediaId(String thumbMediaId) {
        music.setThumbMediaId(thumbMediaId);
    }

    public String getMusicUrl() {
        return music.getMusicUrl();
    }

    public void setMusicUrl(String musicUrl) {
        music.setMusicUrl(musicUrl);
    }

    public String getHqMusicUrl() {
        return music.getHqMusicUrl();
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        music.setHqMusicUrl(hqMusicUrl);
    }

    @XStreamAlias("Music")
    public static class Music {

        @XStreamAlias("Title")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String title;

        @XStreamAlias("Description")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String description;

        @XStreamAlias("ThumbMediaId")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String thumbMediaId;

        @XStreamAlias("MusicUrl")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String musicUrl;

        @XStreamAlias("HQMusicUrl")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String hqMusicUrl;

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

        public String getThumbMediaId() {
            return thumbMediaId;
        }

        public void setThumbMediaId(String thumbMediaId) {
            this.thumbMediaId = thumbMediaId;
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

    }

}
