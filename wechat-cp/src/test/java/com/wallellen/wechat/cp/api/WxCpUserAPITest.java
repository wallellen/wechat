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
import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.cp.bean.WxCpDepart;
import com.wallellen.wechat.cp.bean.WxCpUser;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 测试用户接口
 *
 * @author wallellen
 */
@Test(groups = "userAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxCpUserAPITest {

    @Inject
    protected WxCpServiceImpl wxCpService;

    protected WxCpDepart depart;

    public void testUserCreate() throws WxErrorException {
        WxCpUser user = new WxCpUser();
        user.setUserId("some.woman");
        user.setName("Some Woman");
        user.setDepartIds(new Integer[]{9, 8});
        user.setEmail("none@none.com");
        user.setGender("女");
        user.setMobile("13560084979");
        user.setPosition("woman");
        user.setTel("3300393");
        user.addExtAttr("爱好", "table");
        wxCpService.userCreate(user);
    }

    @Test(dependsOnMethods = "testUserCreate")
    public void testUserUpdate() throws WxErrorException {
        WxCpUser user = new WxCpUser();
        user.setUserId("some.woman");
        user.setName("Some Woman");
        user.addExtAttr("爱好", "table2");
        wxCpService.userUpdate(user);
    }

    @Test(dependsOnMethods = "testUserUpdate")
    public void testUserGet() throws WxErrorException {
        WxCpUser user = wxCpService.userGet("some.woman");
        Assert.assertNotNull(user);
    }

    @Test(dependsOnMethods = "testUserGet")
    public void testDepartGetUsers() throws WxErrorException {
        List<WxCpUser> users = wxCpService.departGetUsers(1, true, 0);
        Assert.assertNotEquals(users.size(), 0);
    }

    @Test(dependsOnMethods = "testDepartGetUsers")
    public void testUserDelete() throws WxErrorException {
        wxCpService.userDelete("some.woman");
    }
}
