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
import com.wallellen.wechat.common.util.crypto.SHA1;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;

/**
 * 测试jsapi ticket接口
 *
 * @author chanjarster
 */
@Test(groups = "jsAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpJsAPITest {

    @Inject
    protected WxMpServiceImpl wxService;


    public void testJsapiTicket() throws WxErrorException {
        String jsapiTicket = wxService.getJsapiTicket(false);
        System.out.println(jsapiTicket);
        Assert.assertNotNull(jsapiTicket);
    }

    public void test() throws NoSuchAlgorithmException {
        long timestamp = 1419835025l;
        String url = "http://omstest.vmall.com:23568/thirdparty/wechat/vcode/gotoshare?quantity=1&batchName=MATE7";
        String noncestr = "82693e11-b9bc-448e-892f-f5289f46cd0f";
        String jsapiTicket = "bxLdikRXVbTPdHSM05e5u4RbEYQn7pNQMPrfzl8lJNb1foLDa3HIwI3BRMkQmSO_5F64VFa75uURcq6Uz7QHgA";
        String result = SHA1.genWithAmple(
                "jsapi_ticket=" + jsapiTicket,
                "noncestr=" + noncestr,
                "timestamp=" + timestamp,
                "url=" + url
        );

        Assert.assertEquals(result, "c6f04b64d6351d197b71bd23fb7dd2d44c0db486");
    }

}
