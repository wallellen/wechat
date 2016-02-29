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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wallellen.wechat.common.bean.result.WxError;
import com.wallellen.wechat.common.util.json.WxErrorAdapter;
import com.wallellen.wechat.cp.bean.WxCpDepart;
import com.wallellen.wechat.cp.bean.WxCpMessage;
import com.wallellen.wechat.cp.bean.WxCpTag;
import com.wallellen.wechat.cp.bean.WxCpUser;

public class WxCpGsonBuilder {

    public static final GsonBuilder INSTANCE = new GsonBuilder();

    static {
        INSTANCE.disableHtmlEscaping();
        INSTANCE.registerTypeAdapter(WxCpMessage.class, new WxCpMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxCpDepart.class, new WxCpDepartGsonAdapter());
        INSTANCE.registerTypeAdapter(WxCpUser.class, new WxCpUserGsonAdapter());
        INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
        INSTANCE.registerTypeAdapter(WxCpTag.class, new WxCpTagGsonAdapter());
    }

    public static Gson create() {
        return INSTANCE.create();
    }

}
