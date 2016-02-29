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
import com.wallellen.wechat.mp.bean.result.WxMpSemanticQueryResult;

import java.lang.reflect.Type;

/**
 * @author wallellen
 */
public class WxMpSemanticQueryResultAdapter implements JsonDeserializer<WxMpSemanticQueryResult> {

    public WxMpSemanticQueryResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        WxMpSemanticQueryResult result = new WxMpSemanticQueryResult();
        JsonObject resultJsonObject = json.getAsJsonObject();

        if (GsonHelper.getString(resultJsonObject, "query") != null) {
            result.setQuery(GsonHelper.getString(resultJsonObject, "query"));
        }
        if (GsonHelper.getString(resultJsonObject, "type") != null) {
            result.setType(GsonHelper.getString(resultJsonObject, "type"));
        }
        if (resultJsonObject.get("semantic") != null) {
            result.setSemantic(resultJsonObject.get("semantic").toString());
        }
        if (resultJsonObject.get("result") != null) {
            result.setResult(resultJsonObject.get("result").toString());
        }
        if (GsonHelper.getString(resultJsonObject, "answer") != null) {
            result.setAnswer(GsonHelper.getString(resultJsonObject, "answer"));
        }
        if (GsonHelper.getString(resultJsonObject, "text") != null) {
            result.setText(GsonHelper.getString(resultJsonObject, "text"));
        }
        return result;
    }

}
