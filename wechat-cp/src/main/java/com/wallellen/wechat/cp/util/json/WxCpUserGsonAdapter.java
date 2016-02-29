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
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wallellen.wechat.common.util.json.GsonHelper;
import com.wallellen.wechat.cp.bean.WxCpUser;

import java.lang.reflect.Type;

/**
 * @author wallellen
 */
public class WxCpUserGsonAdapter implements JsonDeserializer<WxCpUser>, JsonSerializer<WxCpUser> {

    public WxCpUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject o = json.getAsJsonObject();
        WxCpUser user = new WxCpUser();
        user.setUserId(GsonHelper.getString(o, "userid"));
        user.setName(GsonHelper.getString(o, "name"));

        if (o.get("department") != null) {
            JsonArray departJsonArray = o.get("department").getAsJsonArray();
            Integer[] departIds = new Integer[departJsonArray.size()];
            int i = 0;
            for (JsonElement jsonElement : departJsonArray) {
                departIds[i++] = jsonElement.getAsInt();
            }
            user.setDepartIds(departIds);
        }

        user.setPosition(GsonHelper.getString(o, "position"));
        user.setMobile(GsonHelper.getString(o, "mobile"));
        user.setGender(GsonHelper.getString(o, "gender"));
        user.setTel(GsonHelper.getString(o, "tel"));
        user.setEmail(GsonHelper.getString(o, "email"));
        user.setWeiXinId(GsonHelper.getString(o, "weixinid"));
        user.setAvatar(GsonHelper.getString(o, "avatar"));
        user.setStatus(GsonHelper.getInteger(o, "status"));

        if (GsonHelper.isNotNull(o.get("extattr"))) {
            JsonArray attrJsonElements = o.get("extattr").getAsJsonObject().get("attrs").getAsJsonArray();
            for (JsonElement attrJsonElement : attrJsonElements) {
                WxCpUser.Attr attr = new WxCpUser.Attr(
                        GsonHelper.getString(attrJsonElement.getAsJsonObject(), "name"),
                        GsonHelper.getString(attrJsonElement.getAsJsonObject(), "value")
                );
                user.getExtAttrs().add(attr);
            }
        }
        return user;
    }

    @Override
    public JsonElement serialize(WxCpUser user, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject o = new JsonObject();
        if (user.getUserId() != null) {
            o.addProperty("userid", user.getUserId());
        }
        if (user.getName() != null) {
            o.addProperty("name", user.getName());
        }
        if (user.getDepartIds() != null) {
            JsonArray jsonArray = new JsonArray();
            for (Integer departId : user.getDepartIds()) {
                jsonArray.add(new JsonPrimitive(departId));
            }
            o.add("department", jsonArray);
        }
        if (user.getPosition() != null) {
            o.addProperty("position", user.getPosition());
        }
        if (user.getMobile() != null) {
            o.addProperty("mobile", user.getMobile());
        }
        if (user.getGender() != null) {
            o.addProperty("gender", user.getGender());
        }
        if (user.getTel() != null) {
            o.addProperty("tel", user.getTel());
        }
        if (user.getEmail() != null) {
            o.addProperty("email", user.getEmail());
        }
        if (user.getWeiXinId() != null) {
            o.addProperty("weixinid", user.getWeiXinId());
        }
        if (user.getAvatar() != null) {
            o.addProperty("avatar", user.getAvatar());
        }
        if (user.getStatus() != null) {
            o.addProperty("status", user.getStatus());
        }
        if (user.getEnable() != null) {
            o.addProperty("enable", user.getEnable());
        }
        if (user.getExtAttrs().size() > 0) {
            JsonArray attrsJsonArray = new JsonArray();
            for (WxCpUser.Attr attr : user.getExtAttrs()) {
                JsonObject attrJson = new JsonObject();
                attrJson.addProperty("name", attr.getName());
                attrJson.addProperty("value", attr.getValue());
                attrsJsonArray.add(attrJson);
            }
            JsonObject attrsJson = new JsonObject();
            attrsJson.add("attrs", attrsJsonArray);
            o.add("extattr", attrsJson);
        }
        return o;
    }

}
