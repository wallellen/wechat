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

package com.wallellen.wechat.common.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <pre>
 * 默认消息重复检查器
 * 将每个消息id保存在内存里，每隔5秒清理已经过期的消息id，每个消息id的过期时间是15秒
 * </pre>
 */
public class WxMessageInMemoryDuplicateChecker implements WxMessageDuplicateChecker {

    /**
     * 一个消息ID在内存的过期时间：15秒
     */
    private final Long timeToLive;

    /**
     * 每隔多少周期检查消息ID是否过期：5秒
     */
    private final Long clearPeriod;

    /**
     * 消息id->消息时间戳的map
     */
    private final ConcurrentHashMap<String, Long> msgId2Timestamp = new ConcurrentHashMap<String, Long>();

    /**
     * 后台清理线程是否已经开启
     */
    private final AtomicBoolean backgroundProcessStarted = new AtomicBoolean(false);

    /**
     * WxMsgIdInMemoryDuplicateChecker构造函数
     * <pre>
     * 一个消息ID在内存的过期时间：15秒
     * 每隔多少周期检查消息ID是否过期：5秒
     * </pre>
     */
    public WxMessageInMemoryDuplicateChecker() {
        this.timeToLive = 15 * 1000l;
        this.clearPeriod = 5 * 1000l;
    }

    /**
     * WxMsgIdInMemoryDuplicateChecker构造函数
     *
     * @param timeToLive  一个消息ID在内存的过期时间：毫秒
     * @param clearPeriod 每隔多少周期检查消息ID是否过期：毫秒
     */
    public WxMessageInMemoryDuplicateChecker(Long timeToLive, Long clearPeriod) {
        this.timeToLive = timeToLive;
        this.clearPeriod = clearPeriod;
    }

    protected void checkBackgroundProcessStarted() {
        if (backgroundProcessStarted.getAndSet(true)) {
            return;
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(clearPeriod);
                        Long now = System.currentTimeMillis();
                        for (Map.Entry<String, Long> entry : msgId2Timestamp.entrySet()) {
                            if (now - entry.getValue() > timeToLive) {
                                msgId2Timestamp.entrySet().remove(entry);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    @Override
    public boolean isDuplicate(String messageId) {
        if (messageId == null) {
            return false;
        }
        checkBackgroundProcessStarted();
        Long timestamp = msgId2Timestamp.putIfAbsent(messageId, System.currentTimeMillis());
        if (timestamp == null) {
            // 第一次接收到这个消息
            return false;
        }
        return true;
    }


}
