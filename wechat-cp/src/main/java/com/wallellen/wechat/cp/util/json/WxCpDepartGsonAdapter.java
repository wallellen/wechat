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

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wallellen.wechat.common.util.json.GsonHelper;
import com.wallellen.wechat.cp.bean.WxCpDepart;

import java.lang.reflect.Type;

/**
 * @author wallellen
 */
public class WxCpDepartGsonAdapter implements JsonSerializer<WxCpDepart>, JsonDeserializer<WxCpDepart> {

    public JsonElement serialize(WxCpDepart group, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        if (group.getId() != null) {
            json.addProperty("id", group.getId());
        }
        if (group.getName() != null) {
            json.addProperty("name", group.getName());
        }
        if (group.getParentId() != null) {
            json.addProperty("parentid", group.getParentId());
        }
        if (group.getOrder() != null) {
            json.addProperty("order", String.valueOf(group.getOrder()));
        }
        return json;
    }

    public WxCpDepart deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        WxCpDepart depart = new WxCpDepart();
        JsonObject departJson = json.getAsJsonObject();
        if (departJson.get("id") != null && !departJson.get("id").isJsonNull()) {
            depart.setId(GsonHelper.getAsInteger(departJson.get("id")));
        }
        if (departJson.get("name") != null && !departJson.get("name").isJsonNull()) {
            depart.setName(GsonHelper.getAsString(departJson.get("name")));
        }
        if (departJson.get("order") != null && !departJson.get("order").isJsonNull()) {
            depart.setOrder(GsonHelper.getAsInteger(departJson.get("order")));
        }
        if (departJson.get("parentid") != null && !departJson.get("parentid").isJsonNull()) {
            depart.setParentId(GsonHelper.getAsInteger(departJson.get("parentid")));
        }
        return depart;
    }

}
