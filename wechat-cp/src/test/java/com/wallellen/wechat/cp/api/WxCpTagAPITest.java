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
import com.wallellen.wechat.cp.bean.WxCpTag;
import com.wallellen.wechat.cp.bean.WxCpUser;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test(groups = "departAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxCpTagAPITest {

    @Inject
    protected WxCpServiceImpl wxService;

    @Inject
    protected WxCpConfigStorage configStorage;

    protected String tagId;

    public void testTagCreate() throws Exception {
        tagId = wxService.tagCreate("测试标签4");
        System.out.println(tagId);
    }

    @Test(dependsOnMethods = "testTagCreate")
    public void testTagUpdate() throws Exception {
        wxService.tagUpdate(tagId, "测试标签-改名");
    }

    @Test(dependsOnMethods = "testTagUpdate")
    public void testTagGet() throws Exception {
        List<WxCpTag> tags = wxService.tagGet();
        Assert.assertNotEquals(tags.size(), 0);
    }

    @Test(dependsOnMethods = "testTagGet")
    public void testTagAddUsers() throws Exception {
        List<String> userIds = new ArrayList<String>();
        userIds.add(((ApiTestModule.WxXmlCpInMemoryConfigStorage) configStorage).getUserId());
        wxService.tagAddUsers(tagId, userIds, null);
    }

    @Test(dependsOnMethods = "testTagAddUsers")
    public void testTagGetUsers() throws Exception {
        List<WxCpUser> users = wxService.tagGetUsers(tagId);
        Assert.assertNotEquals(users.size(), 0);
    }

    @Test(dependsOnMethods = "testTagGetUsers")
    public void testTagRemoveUsers() throws Exception {
        List<String> userIds = new ArrayList<String>();
        userIds.add(((ApiTestModule.WxXmlCpInMemoryConfigStorage) configStorage).getUserId());
        wxService.tagRemoveUsers(tagId, userIds);
    }

    @Test(dependsOnMethods = "testTagRemoveUsers")
    public void testTagDelete() throws Exception {
        wxService.tagDelete(tagId);
    }

}
