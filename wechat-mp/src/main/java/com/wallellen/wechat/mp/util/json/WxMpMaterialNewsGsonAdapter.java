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

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wallellen.wechat.mp.bean.WxMpMaterialNews;

import java.lang.reflect.Type;

public class WxMpMaterialNewsGsonAdapter implements JsonSerializer<WxMpMaterialNews>, JsonDeserializer<WxMpMaterialNews> {

    public JsonElement serialize(WxMpMaterialNews wxMpMaterialNews, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject newsJson = new JsonObject();

        JsonArray articleJsonArray = new JsonArray();
        for (WxMpMaterialNews.WxMpMaterialNewsArticle article : wxMpMaterialNews.getArticles()) {
            JsonObject articleJson = WxMpGsonBuilder.create().toJsonTree(article, WxMpMaterialNews.WxMpMaterialNewsArticle.class).getAsJsonObject();
            articleJsonArray.add(articleJson);
        }
        newsJson.add("articles", articleJsonArray);

        return newsJson;
    }

    public WxMpMaterialNews deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        WxMpMaterialNews wxMpMaterialNews = new WxMpMaterialNews();
        JsonObject json = jsonElement.getAsJsonObject();
        if (json.get("news_item") != null && !json.get("news_item").isJsonNull()) {
            JsonArray articles = json.getAsJsonArray("news_item");
            for (JsonElement article1 : articles) {
                JsonObject articleInfo = article1.getAsJsonObject();
                WxMpMaterialNews.WxMpMaterialNewsArticle article = WxMpGsonBuilder.create().fromJson(articleInfo, WxMpMaterialNews.WxMpMaterialNewsArticle.class);
                wxMpMaterialNews.addArticle(article);
            }
        }
        return wxMpMaterialNews;
    }
}
