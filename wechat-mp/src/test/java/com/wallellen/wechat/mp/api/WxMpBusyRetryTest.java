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

import com.wallellen.wechat.common.bean.result.WxError;
import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.common.util.http.RequestExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Test
public class WxMpBusyRetryTest {

    @DataProvider(name = "getService")
    public Object[][] getService() {
        WxMpService service = new WxMpServiceImpl() {

            @Override
            protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
                WxError error = new WxError();
                error.setErrorCode(-1);
                throw new WxErrorException(error);
            }
        };

        service.setMaxRetryTimes(3);
        service.setRetrySleepMillis(500);
        return new Object[][]{
                new Object[]{service}
        };
    }

    @Test(dataProvider = "getService", expectedExceptions = RuntimeException.class)
    public void testRetry(WxMpService service) throws WxErrorException {
        service.execute(null, null, null);
    }

    @Test(dataProvider = "getService")
    public void testRetryInThreadPool(final WxMpService service) throws InterruptedException, ExecutionException {
        // 当线程池中的线程复用的时候，还是能保证相同的重试次数
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("=====================");
                    System.out.println(Thread.currentThread().getName() + ": testRetry");
                    service.execute(null, null, null);
                } catch (WxErrorException e) {
                    throw new RuntimeException(e);
                } catch (RuntimeException e) {
                    // OK
                }
            }
        };
        Future<?> submit1 = executorService.submit(runnable);
        Future<?> submit2 = executorService.submit(runnable);

        submit1.get();
        submit2.get();
    }

}
