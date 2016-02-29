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

package com.wallellen.wechat.mp.bean;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxXmlOutNewsMessageTest {

    public void test() {
        WxMpXmlOutNewsMessage m = new WxMpXmlOutNewsMessage();
        m.setCreateTime(1122l);
        m.setFromUserName("fromUser");
        m.setToUserName("toUser");

        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
        item.setDescription("description");
        item.setPicUrl("picUrl");
        item.setTitle("title");
        item.setUrl("url");
        m.addArticle(item);
        m.addArticle(item);
        String expected = "<xml>"
                + "<ToUserName><![CDATA[toUser]]></ToUserName>"
                + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
                + "<CreateTime>1122</CreateTime>"
                + "<MsgType><![CDATA[news]]></MsgType>"
                + "    <ArticleCount>2</ArticleCount>"
                + "    <Articles>"
                + "        <item>"
                + "            <Title><![CDATA[title]]></Title>"
                + "            <Description><![CDATA[description]]></Description>"
                + "            <PicUrl><![CDATA[picUrl]]></PicUrl>"
                + "            <Url><![CDATA[url]]></Url>"
                + "        </item>"
                + "        <item>"
                + "            <Title><![CDATA[title]]></Title>"
                + "            <Description><![CDATA[description]]></Description>"
                + "            <PicUrl><![CDATA[picUrl]]></PicUrl>"
                + "            <Url><![CDATA[url]]></Url>"
                + "        </item>"
                + "    </Articles>"
                + "</xml>";
        System.out.println(m.toXml());
        Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
    }

    public void testBuild() {
        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
        item.setDescription("description");
        item.setPicUrl("picUrl");
        item.setTitle("title");
        item.setUrl("url");

        WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS()
                .fromUser("fromUser")
                .toUser("toUser")
                .addArticle(item)
                .addArticle(item)
                .build();
        String expected = "<xml>"
                + "<ToUserName><![CDATA[toUser]]></ToUserName>"
                + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
                + "<CreateTime>1122</CreateTime>"
                + "<MsgType><![CDATA[news]]></MsgType>"
                + "    <ArticleCount>2</ArticleCount>"
                + "    <Articles>"
                + "        <item>"
                + "            <Title><![CDATA[title]]></Title>"
                + "            <Description><![CDATA[description]]></Description>"
                + "            <PicUrl><![CDATA[picUrl]]></PicUrl>"
                + "            <Url><![CDATA[url]]></Url>"
                + "        </item>"
                + "        <item>"
                + "            <Title><![CDATA[title]]></Title>"
                + "            <Description><![CDATA[description]]></Description>"
                + "            <PicUrl><![CDATA[picUrl]]></PicUrl>"
                + "            <Url><![CDATA[url]]></Url>"
                + "        </item>"
                + "    </Articles>"
                + "</xml>";
        System.out.println(m.toXml());
        Assert.assertEquals(
                m
                        .toXml()
                        .replaceAll("\\s", "")
                        .replaceAll("<CreateTime>.*?</CreateTime>", ""),
                expected
                        .replaceAll("\\s", "")
                        .replaceAll("<CreateTime>.*?</CreateTime>", "")
        );
    }

}
