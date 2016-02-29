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

package com.wallellen.wechat.common.util;

import com.wallellen.wechat.common.api.WxMessageInMemoryDuplicateChecker;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxMessageInMemoryDuplicateCheckerTest {

    public void test() throws InterruptedException {
        Long[] msgIds = new Long[]{1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l};
        WxMessageInMemoryDuplicateChecker checker = new WxMessageInMemoryDuplicateChecker(2000l, 1000l);

        // 第一次检查
        for (Long msgId : msgIds) {
            boolean result = checker.isDuplicate(String.valueOf(msgId));
            Assert.assertFalse(result);
        }

        // 过1秒再检查
        Thread.sleep(1000l);
        for (Long msgId : msgIds) {
            boolean result = checker.isDuplicate(String.valueOf(msgId));
            Assert.assertTrue(result);
        }

        // 过1.5秒再检查
        Thread.sleep(1500l);
        for (Long msgId : msgIds) {
            boolean result = checker.isDuplicate(String.valueOf(msgId));
            Assert.assertFalse(result);
        }

    }

}
