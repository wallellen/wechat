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

package com.wallellen.wechat.cp.bean.messagebuilder;

import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.cp.bean.WxCpMessage;

public class BaseBuilder<T> {
    protected String msgType;
    protected String agentId;
    protected String toUser;
    protected String toParty;
    protected String toTag;
    protected String safe;

    public T agentId(String agentId) {
        this.agentId = agentId;
        return (T) this;
    }

    public T toUser(String toUser) {
        this.toUser = toUser;
        return (T) this;
    }

    public T toParty(String toParty) {
        this.toParty = toParty;
        return (T) this;
    }

    public T toTag(String toTag) {
        this.toTag = toTag;
        return (T) this;
    }

    public T safe(String safe) {
        this.safe = safe;
        return (T) this;
    }

    public WxCpMessage build() {
        WxCpMessage m = new WxCpMessage();
        m.setAgentId(this.agentId);
        m.setMsgType(this.msgType);
        m.setToUser(this.toUser);
        m.setToParty(this.toParty);
        m.setToTag(this.toTag);
        m.setSafe(
                (this.safe == null || "".equals(this.safe)) ? WxConsts.CUSTOM_MSG_SAFE_NO : this.safe);
        return m;
    }

}
