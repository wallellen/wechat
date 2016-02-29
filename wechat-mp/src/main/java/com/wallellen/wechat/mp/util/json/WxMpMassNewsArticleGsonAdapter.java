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
package com.wallellen.wechat.mp.util.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wallellen.wechat.common.util.json.GsonHelper;
import com.wallellen.wechat.mp.bean.WxMpMassNews;

import java.lang.reflect.Type;

public class WxMpMassNewsArticleGsonAdapter implements JsonSerializer<WxMpMassNews.WxMpMassNewsArticle>, JsonDeserializer<WxMpMassNews.WxMpMassNewsArticle> {

    public JsonElement serialize(WxMpMassNews.WxMpMassNewsArticle article, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject articleJson = new JsonObject();

        articleJson.addProperty("thumb_media_id", article.getThumbMediaId());
        articleJson.addProperty("title", article.getTitle());
        articleJson.addProperty("content", article.getContent());
        if (null != article.getAuthor()) {
            articleJson.addProperty("author", article.getAuthor());
        }
        if (null != article.getContentSourceUrl()) {
            articleJson.addProperty("content_source_url", article.getContentSourceUrl());
        }
        if (null != article.getDigest()) {
            articleJson.addProperty("digest", article.getDigest());
        }
        articleJson.addProperty("show_cover_pic", article.isShowCoverPic() ? "1" : "0");
        return articleJson;
    }

    public WxMpMassNews.WxMpMassNewsArticle deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject articleInfo = jsonElement.getAsJsonObject();
        WxMpMassNews.WxMpMassNewsArticle article = new WxMpMassNews.WxMpMassNewsArticle();

        JsonElement title = articleInfo.get("title");
        if (title != null && !title.isJsonNull()) {
            article.setTitle(GsonHelper.getAsString(title));
        }
        JsonElement content = articleInfo.get("content");
        if (content != null && !content.isJsonNull()) {
            article.setContent(GsonHelper.getAsString(content));
        }
        JsonElement contentSourceUrl = articleInfo.get("content_source_url");
        if (contentSourceUrl != null && !contentSourceUrl.isJsonNull()) {
            article.setContentSourceUrl(GsonHelper.getAsString(contentSourceUrl));
        }
        JsonElement author = articleInfo.get("author");
        if (author != null && !author.isJsonNull()) {
            article.setAuthor(GsonHelper.getAsString(author));
        }
        JsonElement digest = articleInfo.get("digest");
        if (digest != null && !digest.isJsonNull()) {
            article.setDigest(GsonHelper.getAsString(digest));
        }
        JsonElement thumbMediaId = articleInfo.get("thumb_media_id");
        if (thumbMediaId != null && !thumbMediaId.isJsonNull()) {
            article.setThumbMediaId(GsonHelper.getAsString(thumbMediaId));
        }
        JsonElement showCoverPic = articleInfo.get("show_cover_pic");
        if (showCoverPic != null && !showCoverPic.isJsonNull()) {
            article.setShowCoverPic(GsonHelper.getAsBoolean(showCoverPic));
        }
        return article;
    }
}
