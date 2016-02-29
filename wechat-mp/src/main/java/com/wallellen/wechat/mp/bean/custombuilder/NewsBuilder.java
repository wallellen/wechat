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

package com.wallellen.wechat.mp.bean.custombuilder;

import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.mp.bean.WxMpCustomMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息builder
 * <pre>
 * 用法:
 * WxMpCustomMessage m = WxMpCustomMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 *
 * @author chanjarster
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {

    private List<WxMpCustomMessage.WxArticle> articles = new ArrayList<WxMpCustomMessage.WxArticle>();

    public NewsBuilder() {
        this.msgType = WxConsts.CUSTOM_MSG_NEWS;
    }

    public NewsBuilder addArticle(WxMpCustomMessage.WxArticle article) {
        this.articles.add(article);
        return this;
    }

    public WxMpCustomMessage build() {
        WxMpCustomMessage m = super.build();
        m.setArticles(this.articles);
        return m;
    }
}
