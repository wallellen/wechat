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

package com.wallellen.wechat.mp.api;

import com.google.inject.Inject;
import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.mp.bean.result.WxMpUser;
import com.wallellen.wechat.mp.bean.result.WxMpUserList;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试用户相关的接口
 *
 * @author chanjarster
 */
@Test(groups = "userAPI", dependsOnGroups = {"baseAPI", "groupAPI"})
@Guice(modules = ApiTestModule.class)
public class WxMpUserAPITest {

    @Inject
    protected WxMpServiceImpl wxService;

    public void testUserUpdateRemark() throws WxErrorException {
        ApiTestModule.WxXmlMpInMemoryConfigStorage configProvider = (ApiTestModule.WxXmlMpInMemoryConfigStorage) wxService.wxMpConfigStorage;
        wxService.userUpdateRemark(configProvider.getOpenId(), "测试备注名");
    }

    public void testUserInfo() throws WxErrorException {
        ApiTestModule.WxXmlMpInMemoryConfigStorage configProvider = (ApiTestModule.WxXmlMpInMemoryConfigStorage) wxService.wxMpConfigStorage;
        WxMpUser user = wxService.userInfo(configProvider.getOpenId(), null);
        Assert.assertNotNull(user);
    }

    public void testUserList() throws WxErrorException {
        WxMpUserList wxMpUserList = wxService.userList(null);
        Assert.assertNotNull(wxMpUserList);
        Assert.assertFalse(wxMpUserList.getCount() == -1);
        Assert.assertFalse(wxMpUserList.getTotal() == -1);
        Assert.assertFalse(wxMpUserList.getOpenIds().size() == -1);
    }

    public void testGroupQueryUserGroup() throws WxErrorException {
        ApiTestModule.WxXmlMpInMemoryConfigStorage configStorage = (ApiTestModule.WxXmlMpInMemoryConfigStorage) wxService.wxMpConfigStorage;
        long groupid = wxService.userGetGroup(configStorage.getOpenId());
        Assert.assertTrue(groupid != -1l);
    }

    public void getGroupMoveUser() throws WxErrorException {
        ApiTestModule.WxXmlMpInMemoryConfigStorage configStorage = (ApiTestModule.WxXmlMpInMemoryConfigStorage) wxService.wxMpConfigStorage;
        wxService.userUpdateGroup(configStorage.getOpenId(), wxService.groupGet().get(3).getId());
    }

}
