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

package com.wallellen.wechat.common.bean;

import com.wallellen.wechat.common.bean.WxMenu.WxMenuButton;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class WxMenuTest {

    @Test(dataProvider = "wxReturnMenu")
    public void testFromJson(String json) {
        WxMenu menu = WxMenu.fromJson(json);
        Assert.assertEquals(menu.getButtons().size(), 3);
    }

    @Test(dataProvider = "wxPushMenu")
    public void testToJson(String json) {
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType("click");
        button1.setName("今日歌曲");
        button1.setKey("V1001_TODAY_MUSIC");

        WxMenuButton button2 = new WxMenuButton();
        button2.setType("click");
        button2.setName("歌手简介");
        button2.setKey("V1001_TODAY_SINGER");

        WxMenuButton button3 = new WxMenuButton();
        button3.setName("菜单");

        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        WxMenuButton button31 = new WxMenuButton();
        button31.setType("view");
        button31.setName("搜索");
        button31.setUrl("http://www.soso.com/");

        WxMenuButton button32 = new WxMenuButton();
        button32.setType("view");
        button32.setName("视频");
        button32.setUrl("http://v.qq.com/");

        WxMenuButton button33 = new WxMenuButton();
        button33.setType("click");
        button33.setName("赞一下我们");
        button33.setKey("V1001_GOOD");

        button3.getSubButtons().add(button31);
        button3.getSubButtons().add(button32);
        button3.getSubButtons().add(button33);

        Assert.assertEquals(menu.toJson(), json);
    }

    @DataProvider
    public Object[][] wxReturnMenu() {
        Object[][] res = menuJson();
        String json = "{ \"menu\" : " + res[0][0] + " }";
        return new Object[][]{
                new Object[]{json}
        };
    }

    @DataProvider(name = "wxPushMenu")
    public Object[][] menuJson() {
        String json =
                "{"
                        + "\"button\":["
                        + "{"
                        + "\"type\":\"click\","
                        + "\"name\":\"今日歌曲\","
                        + "\"key\":\"V1001_TODAY_MUSIC\""
                        + "},"
                        + "{"
                        + "\"type\":\"click\","
                        + "\"name\":\"歌手简介\","
                        + "\"key\":\"V1001_TODAY_SINGER\""
                        + "},"
                        + "{"
                        + "\"name\":\"菜单\","
                        + "\"sub_button\":["
                        + "{"
                        + "\"type\":\"view\","
                        + "\"name\":\"搜索\","
                        + "\"url\":\"http://www.soso.com/\""
                        + "},"
                        + "{"
                        + "\"type\":\"view\","
                        + "\"name\":\"视频\","
                        + "\"url\":\"http://v.qq.com/\""
                        + "},"
                        + "{"
                        + "\"type\":\"click\","
                        + "\"name\":\"赞一下我们\","
                        + "\"key\":\"V1001_GOOD\""
                        + "}"
                        + "]"
                        + "}"
                        + "]"
                        + "}";
        return new Object[][]{
                new Object[]{json}
        };
    }

}
