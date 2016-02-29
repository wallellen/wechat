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
import com.wallellen.wechat.common.util.json.GsonHelper;
import com.wallellen.wechat.mp.bean.result.WxMpUser;

import java.lang.reflect.Type;

public class WxMpUserGsonAdapter implements JsonDeserializer<WxMpUser> {

    public WxMpUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject o = json.getAsJsonObject();
        WxMpUser wxMpUser = new WxMpUser();
        Integer subscribe = GsonHelper.getInteger(o, "subscribe");
        if (subscribe != null) {
            wxMpUser.setSubscribe(!new Integer(0).equals(subscribe));
        }
        wxMpUser.setCity(GsonHelper.getString(o, "city"));
        wxMpUser.setCountry(GsonHelper.getString(o, "country"));
        wxMpUser.setHeadImgUrl(GsonHelper.getString(o, "headimgurl"));
        wxMpUser.setLanguage(GsonHelper.getString(o, "language"));
        wxMpUser.setNickname(GsonHelper.getString(o, "nickname"));
        wxMpUser.setOpenId(GsonHelper.getString(o, "openid"));
        wxMpUser.setProvince(GsonHelper.getString(o, "province"));
        wxMpUser.setSubscribeTime(GsonHelper.getLong(o, "subscribe_time"));
        wxMpUser.setUnionId(GsonHelper.getString(o, "unionid"));
        Integer sexId = GsonHelper.getInteger(o, "sex");
        wxMpUser.setRemark(GsonHelper.getString(o, "remark"));
        wxMpUser.setGroupId(GsonHelper.getInteger(o, "groupid"));
        wxMpUser.setSexId(sexId);
        if (new Integer(1).equals(sexId)) {
            wxMpUser.setSex("男");
        } else if (new Integer(2).equals(sexId)) {
            wxMpUser.setSex("女");
        } else {
            wxMpUser.setSex("未知");
        }
        return wxMpUser;
    }

}
