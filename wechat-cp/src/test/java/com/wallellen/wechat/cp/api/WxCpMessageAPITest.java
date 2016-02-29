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

package com.wallellen.wechat.cp.api;

import com.google.inject.Inject;
import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.cp.bean.WxCpMessage;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试发送消息
 *
 * @author wallellen
 */
@Test(groups = "customMessageAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxCpMessageAPITest {

    @Inject
    protected WxCpServiceImpl wxService;

    public void testSendCustomMessage() throws WxErrorException {
        ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage = (ApiTestModule.WxXmlCpInMemoryConfigStorage) wxService.wxCpConfigStorage;
        WxCpMessage message1 = new WxCpMessage();
        message1.setAgentId(configStorage.getAgentId());
        message1.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
        message1.setToUser(configStorage.getUserId());
        message1.setContent("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");
        wxService.messageSend(message1);

        WxCpMessage message2 = WxCpMessage
                .TEXT()
                .agentId(configStorage.getAgentId())
                .toUser(configStorage.getUserId())
                .content("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>")
                .build();
        wxService.messageSend(message2);

    }

}
