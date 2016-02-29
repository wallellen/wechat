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


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class GsonHelper {

    public static boolean isNull(JsonElement element) {
        return element == null || element.isJsonNull();
    }

    public static boolean isNotNull(JsonElement element) {
        return !isNull(element);
    }

    public static Long getLong(JsonObject json, String property) {
        return getAsLong(json.get(property));
    }

    public static long getPrimitiveLong(JsonObject json, String property) {
        return getAsPrimitiveLong(json.get(property));
    }

    public static Integer getInteger(JsonObject json, String property) {
        return getAsInteger(json.get(property));
    }

    public static int getPrimitiveInteger(JsonObject json, String property) {
        return getAsPrimitiveInt(json.get(property));
    }

    public static Double getDouble(JsonObject json, String property) {
        return getAsDouble(json.get(property));
    }

    public static double getPrimitiveDouble(JsonObject json, String property) {
        return getAsPrimitiveDouble(json.get(property));
    }

    public static Float getFloat(JsonObject json, String property) {
        return getAsFloat(json.get(property));
    }

    public static float getPrimitiveFloat(JsonObject json, String property) {
        return getAsPrimitiveFloat(json.get(property));
    }

    public static Boolean getBoolean(JsonObject json, String property) {
        return getAsBoolean(json.get(property));
    }

    public static String getString(JsonObject json, String property) {
        return getAsString(json.get(property));
    }

    public static String getAsString(JsonElement element) {
        return isNull(element) ? null : element.getAsString();
    }

    public static Long getAsLong(JsonElement element) {
        return isNull(element) ? null : element.getAsLong();
    }

    public static long getAsPrimitiveLong(JsonElement element) {
        Long r = getAsLong(element);
        return r == null ? 0l : r;
    }

    public static Integer getAsInteger(JsonElement element) {
        return isNull(element) ? null : element.getAsInt();
    }

    public static int getAsPrimitiveInt(JsonElement element) {
        Integer r = getAsInteger(element);
        return r == null ? 0 : r;
    }

    public static Boolean getAsBoolean(JsonElement element) {
        return isNull(element) ? null : element.getAsBoolean();
    }

    public static boolean getAsPrimitiveBool(JsonElement element) {
        Boolean r = getAsBoolean(element);
        return r == null ? false : r.booleanValue();
    }

    public static Double getAsDouble(JsonElement element) {
        return isNull(element) ? null : element.getAsDouble();
    }

    public static double getAsPrimitiveDouble(JsonElement element) {
        Double r = getAsDouble(element);
        return r == null ? 0d : r;
    }

    public static Float getAsFloat(JsonElement element) {
        return isNull(element) ? null : element.getAsFloat();
    }

    public static float getAsPrimitiveFloat(JsonElement element) {
        Float r = getAsFloat(element);
        return r == null ? 0f : r;
    }

}
