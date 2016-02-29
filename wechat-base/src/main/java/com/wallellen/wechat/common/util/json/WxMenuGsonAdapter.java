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
package com.wallellen.wechat.common.util.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wallellen.wechat.common.bean.WxMenu;

import java.lang.reflect.Type;

/**
 * @author wallellen
 */
public class WxMenuGsonAdapter implements JsonSerializer<WxMenu>, JsonDeserializer<WxMenu> {

    public JsonElement serialize(WxMenu menu, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();

        JsonArray buttonArray = new JsonArray();
        for (WxMenu.WxMenuButton button : menu.getButtons()) {
            JsonObject buttonJson = convertToJson(button);
            buttonArray.add(buttonJson);
        }
        json.add("button", buttonArray);

        if (menu.getMatchRule() != null) {
            Gson gson = new Gson();
            json.add("matchrule", gson.toJsonTree(menu.getMatchRule()));
        }

        return json;
    }

    protected JsonObject convertToJson(WxMenu.WxMenuButton button) {
        JsonObject buttonJson = new JsonObject();
        buttonJson.addProperty("type", button.getType());
        buttonJson.addProperty("name", button.getName());
        buttonJson.addProperty("key", button.getKey());
        buttonJson.addProperty("url", button.getUrl());
        if (button.getSubButtons() != null && button.getSubButtons().size() > 0) {
            JsonArray buttonArray = new JsonArray();
            for (WxMenu.WxMenuButton sub_button : button.getSubButtons()) {
                buttonArray.add(convertToJson(sub_button));
            }
            buttonJson.add("sub_button", buttonArray);
        }
        return buttonJson;
    }

    public WxMenu deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    /*
     * 操蛋的微信
     * 创建菜单时是 { button : ... }
     * 查询菜单时是 { menu : { button : ... } }
     */
        WxMenu menu = new WxMenu();
        JsonObject menuJson = json.getAsJsonObject().get("menu").getAsJsonObject();
        JsonArray buttonsJson = menuJson.get("button").getAsJsonArray();
        for (int i = 0; i < buttonsJson.size(); i++) {
            JsonObject buttonJson = buttonsJson.get(i).getAsJsonObject();
            WxMenu.WxMenuButton button = convertFromJson(buttonJson);
            menu.getButtons().add(button);
            if (buttonJson.get("sub_button") == null || buttonJson.get("sub_button").isJsonNull()) {
                continue;
            }
            JsonArray sub_buttonsJson = buttonJson.get("sub_button").getAsJsonArray();
            for (int j = 0; j < sub_buttonsJson.size(); j++) {
                JsonObject sub_buttonJson = sub_buttonsJson.get(j).getAsJsonObject();
                button.getSubButtons().add(convertFromJson(sub_buttonJson));
            }
        }
        return menu;
    }

    protected WxMenu.WxMenuButton convertFromJson(JsonObject json) {
        WxMenu.WxMenuButton button = new WxMenu.WxMenuButton();
        button.setName(GsonHelper.getString(json, "name"));
        button.setKey(GsonHelper.getString(json, "key"));
        button.setUrl(GsonHelper.getString(json, "url"));
        button.setType(GsonHelper.getString(json, "type"));
        return button;
    }

}
