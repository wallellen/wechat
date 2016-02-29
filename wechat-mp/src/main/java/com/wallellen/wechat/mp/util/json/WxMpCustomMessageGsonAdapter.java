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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.mp.bean.WxMpCustomMessage;

import java.lang.reflect.Type;

public class WxMpCustomMessageGsonAdapter implements JsonSerializer<WxMpCustomMessage> {

    public JsonElement serialize(WxMpCustomMessage message, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject messageJson = new JsonObject();
        messageJson.addProperty("touser", message.getToUser());
        messageJson.addProperty("msgtype", message.getMsgType());
        JsonObject obj = new JsonObject();

        if (WxConsts.CUSTOM_MSG_TEXT.equals(message.getMsgType())) {
            obj.addProperty("content", message.getContent());
            messageJson.add("text", obj);
        } else if (WxConsts.CUSTOM_MSG_IMAGE.equals(message.getMsgType())) {
            obj.addProperty("media_id", message.getMediaId());
            messageJson.add("image", obj);
        } else if (WxConsts.CUSTOM_MSG_VOICE.equals(message.getMsgType())) {
            obj.addProperty("media_id", message.getMediaId());
            messageJson.add("voice", obj);
        } else if (WxConsts.CUSTOM_MSG_VIDEO.equals(message.getMsgType())) {
            obj.addProperty("media_id", message.getMediaId());
            obj.addProperty("thumb_media_id", message.getThumbMediaId());
            obj.addProperty("title", message.getTitle());
            obj.addProperty("description", message.getDescription());
            messageJson.add("video", obj);
        } else if (WxConsts.CUSTOM_MSG_MUSIC.equals(message.getMsgType())) {
            obj.addProperty("title", message.getTitle());
            obj.addProperty("description", message.getDescription());
            obj.addProperty("thumb_media_id", message.getThumbMediaId());
            obj.addProperty("musicurl", message.getMusicUrl());
            obj.addProperty("hqmusicurl", message.getHqMusicUrl());
            messageJson.add("music", obj);
        } else if (WxConsts.CUSTOM_MSG_NEWS.equals(message.getMsgType())) {
            JsonArray articleJsonArray = new JsonArray();
            for (WxMpCustomMessage.WxArticle article : message.getArticles()) {
                JsonObject articleJson = new JsonObject();
                articleJson.addProperty("title", article.getTitle());
                articleJson.addProperty("description", article.getDescription());
                articleJson.addProperty("url", article.getUrl());
                articleJson.addProperty("picurl", article.getPicUrl());
                articleJsonArray.add(articleJson);
            }
            obj.add("articles", articleJsonArray);
            messageJson.add("news", obj);
        } else {
            throw new RuntimeException("Invalid message type: " + message.getMsgType());
        }

        return messageJson;
    }

}
