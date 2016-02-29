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
package com.wallellen.wechat.cp.util.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.common.util.StringUtils;
import com.wallellen.wechat.cp.bean.WxCpMessage;

import java.lang.reflect.Type;

/**
 * @author wallellen
 */
public class WxCpMessageGsonAdapter implements JsonSerializer<WxCpMessage> {

    public JsonElement serialize(WxCpMessage message, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject messageJson = new JsonObject();
        messageJson.addProperty("agentid", message.getAgentId());
        if (StringUtils.isNotBlank(message.getToUser())) {
            messageJson.addProperty("touser", message.getToUser());
        }
        messageJson.addProperty("msgtype", message.getMsgType());

        if (StringUtils.isNotBlank(message.getToParty())) {
            messageJson.addProperty("toparty", message.getToParty());
        }
        if (StringUtils.isNotBlank(message.getToTag())) {
            messageJson.addProperty("totag", message.getToTag());
        }
        if (WxConsts.CUSTOM_MSG_TEXT.equals(message.getMsgType())) {
            JsonObject text = new JsonObject();
            text.addProperty("content", message.getContent());
            messageJson.add("text", text);
        }

        if (WxConsts.CUSTOM_MSG_IMAGE.equals(message.getMsgType())) {
            JsonObject image = new JsonObject();
            image.addProperty("media_id", message.getMediaId());
            messageJson.add("image", image);
        }

        if (WxConsts.CUSTOM_MSG_FILE.equals(message.getMsgType())) {
            JsonObject image = new JsonObject();
            image.addProperty("media_id", message.getMediaId());
            messageJson.add("file", image);
        }

        if (WxConsts.CUSTOM_MSG_VOICE.equals(message.getMsgType())) {
            JsonObject voice = new JsonObject();
            voice.addProperty("media_id", message.getMediaId());
            messageJson.add("voice", voice);
        }

        if (WxConsts.CUSTOM_MSG_VIDEO.equals(message.getMsgType())) {
            JsonObject video = new JsonObject();
            video.addProperty("media_id", message.getMediaId());
            video.addProperty("thumb_media_id", message.getThumbMediaId());
            video.addProperty("title", message.getTitle());
            video.addProperty("description", message.getDescription());
            messageJson.add("video", video);
        }

        if (WxConsts.CUSTOM_MSG_NEWS.equals(message.getMsgType())) {
            JsonObject newsJsonObject = new JsonObject();
            JsonArray articleJsonArray = new JsonArray();
            for (WxCpMessage.WxArticle article : message.getArticles()) {
                JsonObject articleJson = new JsonObject();
                articleJson.addProperty("title", article.getTitle());
                articleJson.addProperty("description", article.getDescription());
                articleJson.addProperty("url", article.getUrl());
                articleJson.addProperty("picurl", article.getPicUrl());
                articleJsonArray.add(articleJson);
            }
            newsJsonObject.add("articles", articleJsonArray);
            messageJson.add("news", newsJsonObject);
        }

        return messageJson;
    }

}
