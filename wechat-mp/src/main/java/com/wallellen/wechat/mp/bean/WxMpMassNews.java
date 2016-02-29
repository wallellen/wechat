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
 * 群发时用到的图文消息素材
 *
 * @author chanjarster
 */
public class WxMpMassNews implements Serializable {

    private List<WxMpMassNewsArticle> articles = new ArrayList<>();

    public List<WxMpMassNewsArticle> getArticles() {
        return articles;
    }

    public void addArticle(WxMpMassNewsArticle article) {
        this.articles.add(article);
    }

    public String toJson() {
        return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

    public boolean isEmpty() {
        return articles == null || articles.isEmpty();
    }

    @Override
    public String toString() {
        return "WxMpMassNews [" + "articles=" + articles + "]";
    }

    /**
     * <pre>
     * 群发图文消息article
     * 1. thumbMediaId  (必填) 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     * 2. author          图文消息的作者
     * 3. title           (必填) 图文消息的标题
     * 4. contentSourceUrl 在图文消息页面点击“阅读原文”后的页面链接
     * 5. content (必填)  图文消息页面的内容，支持HTML标签
     * 6. digest          图文消息的描述
     * 7, showCoverPic  是否显示封面，true为显示，false为不显示
     * </pre>
     *
     * @author chanjarster
     */
    public static class WxMpMassNewsArticle {
        /**
         * (必填) 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
         */
        private String thumbMediaId;
        /**
         * 图文消息的作者
         */
        private String author;
        /**
         * (必填) 图文消息的标题
         */
        private String title;
        /**
         * 在图文消息页面点击“阅读原文”后的页面链接
         */
        private String contentSourceUrl;
        /**
         * (必填) 图文消息页面的内容，支持HTML标签
         */
        private String content;
        /**
         * 图文消息的描述
         */
        private String digest;
        /**
         * 是否显示封面，true为显示，false为不显示
         */
        private boolean showCoverPic;

        public String getThumbMediaId() {
            return thumbMediaId;
        }

        public void setThumbMediaId(String thumbMediaId) {
            this.thumbMediaId = thumbMediaId;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContentSourceUrl() {
            return contentSourceUrl;
        }

        public void setContentSourceUrl(String contentSourceUrl) {
            this.contentSourceUrl = contentSourceUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public boolean isShowCoverPic() {
            return showCoverPic;
        }

        public void setShowCoverPic(boolean showCoverPic) {
            this.showCoverPic = showCoverPic;
        }

        @Override
        public String toString() {
            return "WxMpMassNewsArticle [" + "thumbMediaId=" + thumbMediaId + ", author=" + author + ", title=" + title +
                    ", contentSourceUrl=" + contentSourceUrl + ", content=" + content + ", digest=" + digest +
                    ", showCoverPic=" + showCoverPic + "]";
        }
    }
}
