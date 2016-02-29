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

/**
 * <pre>
 * 消息重复检查器
 * 微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次
 * </pre>
 */
public interface WxMessageDuplicateChecker {

    /**
     * <h2>公众号的排重方式</h2>
     * <p/>
     * <p>普通消息：关于重试的消息排重，推荐使用msgid排重。<a href="http://mp.weixin.qq.com/wiki/10/79502792eef98d6e0c6e1739da387346.html">文档参考</a>。</p>
     * <p>事件消息：关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。<a href="http://mp.weixin.qq.com/wiki/2/5baf56ce4947d35003b86a9805634b1e.html">文档参考</a></p>
     * <p/>
     * <h2>企业号的排重方式</h2>
     * <p/>
     * 官方文档完全没有写，参照公众号的方式排重。
     * <p/>
     * <p>或者可以采取更简单的方式，如果有MsgId就用MsgId排重，如果没有就用FromUserName+CreateTime排重</p>
     *
     * @param messageId messageId需要根据上面讲的方式构造
     * @return 如果是重复消息，返回true，否则返回false
     */
    public boolean isDuplicate(String messageId);

}
