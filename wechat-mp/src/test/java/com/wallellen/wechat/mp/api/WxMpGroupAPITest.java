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
import com.wallellen.wechat.mp.bean.WxMpGroup;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 测试分组接口
 *
 * @author chanjarster
 */
@Test(groups = "groupAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpGroupAPITest {

    @Inject
    protected WxMpServiceImpl wxService;

    protected WxMpGroup group;

    public void testGroupCreate() throws WxErrorException {
        WxMpGroup res = wxService.groupCreate("测试分组1");
        Assert.assertEquals(res.getName(), "测试分组1");
    }

    @Test(dependsOnMethods = "testGroupCreate")
    public void testGroupGet() throws WxErrorException {
        List<WxMpGroup> groupList = wxService.groupGet();
        Assert.assertNotNull(groupList);
        Assert.assertTrue(groupList.size() > 0);
        for (WxMpGroup g : groupList) {
            group = g;
            Assert.assertNotNull(g.getName());
        }
    }

    @Test(dependsOnMethods = {"testGroupGet", "testGroupCreate"})
    public void getGroupUpdate() throws WxErrorException {
        group.setName("分组改名");
        wxService.groupUpdate(group);
    }

}
