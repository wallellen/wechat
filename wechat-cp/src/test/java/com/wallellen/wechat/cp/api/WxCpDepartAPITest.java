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
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 测试部门接口
 *
 * @author wallellen
 */
@Test(groups = "departAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxCpDepartAPITest {

    @Inject
    protected WxCpServiceImpl wxCpService;

    protected WxCpDepart depart;

    public void testDepartCreate() throws WxErrorException {
        WxCpDepart depart = new WxCpDepart();
        depart.setName("子部门" + System.currentTimeMillis());
        depart.setParentId(1);
        depart.setOrder(1);
        Integer departId = wxCpService.departCreate(depart);
    }

    @Test(dependsOnMethods = "testDepartCreate")
    public void testDepartGet() throws WxErrorException {
        System.out.println("=================获取部门");
        List<WxCpDepart> departList = wxCpService.departGet();
        Assert.assertNotNull(departList);
        Assert.assertTrue(departList.size() > 0);
        for (WxCpDepart g : departList) {
            depart = g;
            System.out.println(depart.getId() + ":" + depart.getName());
            Assert.assertNotNull(g.getName());
        }
    }

    @Test(dependsOnMethods = {"testDepartGet", "testDepartCreate"})
    public void testDepartUpdate() throws WxErrorException {
        System.out.println("=================更新部门");
        depart.setName("子部门改名" + System.currentTimeMillis());
        wxCpService.departUpdate(depart);
    }

    @Test(dependsOnMethods = "testDepartUpdate")
    public void testDepartDelete() throws WxErrorException {
        System.out.println("=================删除部门");
        System.out.println(depart.getId() + ":" + depart.getName());
        wxCpService.departDelete(depart.getId());
    }

}
