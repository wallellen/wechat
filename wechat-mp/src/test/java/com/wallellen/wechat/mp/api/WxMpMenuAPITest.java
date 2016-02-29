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
import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.common.bean.WxMenu;
import com.wallellen.wechat.common.bean.WxMenu.WxMenuButton;
import com.wallellen.wechat.common.exception.WxErrorException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试菜单
 *
 * @author chanjarster
 */
@Test(groups = "menuAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpMenuAPITest {

    @Inject
    protected WxMpServiceImpl wxService;

    @Test(dataProvider = "menu")
    public void testCreateMenu(WxMenu wxMenu) throws WxErrorException {
        System.out.println(wxMenu.toJson());
        wxService.menuCreate(wxMenu);
    }

    @Test
    public void testCreateMenu2() throws WxErrorException {
        String a = "{\n"
                + "  \"menu\": {\n"
                + "    \"button\": [\n"
                + "      {\n"
                + "        \"type\": \"click\",\n"
                + "        \"name\": \"今日歌曲\",\n"
                + "        \"key\": \"V1001_TODAY_MUSIC\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"type\": \"click\",\n"
                + "        \"name\": \"歌手简介\",\n"
                + "        \"key\": \"V1001_TODAY_SINGER\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"name\": \"菜单\",\n"
                + "        \"sub_button\": [\n"
                + "          {\n"
                + "            \"type\": \"view\",\n"
                + "            \"name\": \"搜索\",\n"
                + "            \"url\": \"http://www.soso.com/\"\n"
                + "          },\n"
                + "          {\n"
                + "            \"type\": \"view\",\n"
                + "            \"name\": \"视频\",\n"
                + "            \"url\": \"http://v.qq.com/\"\n"
                + "          },\n"
                + "          {\n"
                + "            \"type\": \"click\",\n"
                + "            \"name\": \"赞一下我们\",\n"
                + "            \"key\": \"V1001_GOOD\"\n"
                + "          }\n"
                + "        ]\n"
                + "      }\n"
                + "    ]\n"
                + "  }\n"
                + "}";

        WxMenu menu = WxMenu.fromJson(a);
        System.out.println(menu.toJson());
        wxService.menuCreate(menu);
    }

    @Test(dependsOnMethods = {"testCreateMenu"})
    public void testGetMenu() throws WxErrorException {
        Assert.assertNotNull(wxService.menuGet());
    }

    @Test(dependsOnMethods = {"testGetMenu"})
    public void testDeleteMenu() throws WxErrorException {
        wxService.menuDelete();
    }

    @DataProvider(name = "menu")
    public Object[][] getMenu() {
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType(WxConsts.BUTTON_CLICK);
        button1.setName("今日歌曲");
        button1.setKey("V1001_TODAY_MUSIC");

        WxMenuButton button2 = new WxMenuButton();
        button2.setType(WxConsts.BUTTON_CLICK);
        button2.setName("歌手简介");
        button2.setKey("V1001_TODAY_SINGER");

        WxMenuButton button3 = new WxMenuButton();
        button3.setName("菜单");

        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        WxMenuButton button31 = new WxMenuButton();
        button31.setType(WxConsts.BUTTON_VIEW);
        button31.setName("搜索");
        button31.setUrl("http://www.soso.com/");

        WxMenuButton button32 = new WxMenuButton();
        button32.setType(WxConsts.BUTTON_VIEW);
        button32.setName("视频");
        button32.setUrl("http://v.qq.com/");

        WxMenuButton button33 = new WxMenuButton();
        button33.setType(WxConsts.BUTTON_CLICK);
        button33.setName("赞一下我们");
        button33.setKey("V1001_GOOD");

        button3.getSubButtons().add(button31);
        button3.getSubButtons().add(button32);
        button3.getSubButtons().add(button33);

        return new Object[][]{
                new Object[]{
                        menu
                }
        };

    }


}
