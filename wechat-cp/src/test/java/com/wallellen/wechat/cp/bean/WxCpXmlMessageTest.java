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

package com.wallellen.wechat.cp.bean;

import com.wallellen.wechat.common.api.WxConsts;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxCpXmlMessageTest {

    public void testFromXml() {

        String xml = "<xml>"
                + "<ToUserName><![CDATA[toUser]]></ToUserName>"
                + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
                + "<CreateTime>1348831860</CreateTime>"
                + "<MsgType><![CDATA[text]]></MsgType>"
                + "<Content><![CDATA[this is a test]]></Content>"
                + "<MsgId>1234567890123456</MsgId>"
                + "<PicUrl><![CDATA[this is a url]]></PicUrl>"
                + "<MediaId><![CDATA[media_id]]></MediaId>"
                + "<Format><![CDATA[Format]]></Format>"
                + "<ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>"
                + "<Location_X>23.134521</Location_X>"
                + "<Location_Y>113.358803</Location_Y>"
                + "<Scale>20</Scale>"
                + "<Label><![CDATA[位置信息]]></Label>"
                + "<Description><![CDATA[公众平台官网链接]]></Description>"
                + "<Url><![CDATA[url]]></Url>"
                + "<Title><![CDATA[公众平台官网链接]]></Title>"
                + "<Event><![CDATA[subscribe]]></Event>"
                + "<EventKey><![CDATA[qrscene_123123]]></EventKey>"
                + "<Ticket><![CDATA[TICKET]]></Ticket>"
                + "<Latitude>23.137466</Latitude>"
                + "<Longitude>113.352425</Longitude>"
                + "<Precision>119.385040</Precision>"
                + "<ScanCodeInfo>"
                + " <ScanType><![CDATA[qrcode]]></ScanType>"
                + " <ScanResult><![CDATA[1]]></ScanResult>"
                + "</ScanCodeInfo>"
                + "<SendPicsInfo>"
                + " <Count>1</Count>\n"
                + " <PicList>"
                + "  <item>"
                + "   <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>"
                + "  </item>"
                + " </PicList>"
                + "</SendPicsInfo>"
                + "<SendLocationInfo>"
                + "  <Location_X><![CDATA[23]]></Location_X>\n"
                + "  <Location_Y><![CDATA[113]]></Location_Y>\n"
                + "  <Scale><![CDATA[15]]></Scale>\n"
                + "  <Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>\n"
                + "  <Poiname><![CDATA[wo de poi]]></Poiname>\n"
                + "</SendLocationInfo>"
                + "</xml>";
        WxCpXmlMessage wxMessage = WxCpXmlMessage.fromXml(xml);
        Assert.assertEquals(wxMessage.getToUserName(), "toUser");
        Assert.assertEquals(wxMessage.getFromUserName(), "fromUser");
        Assert.assertEquals(wxMessage.getCreateTime(), new Long(1348831860l));
        Assert.assertEquals(wxMessage.getMsgType(), WxConsts.XML_MSG_TEXT);
        Assert.assertEquals(wxMessage.getContent(), "this is a test");
        Assert.assertEquals(wxMessage.getMsgId(), new Long(1234567890123456l));
        Assert.assertEquals(wxMessage.getPicUrl(), "this is a url");
        Assert.assertEquals(wxMessage.getMediaId(), "media_id");
        Assert.assertEquals(wxMessage.getFormat(), "Format");
        Assert.assertEquals(wxMessage.getThumbMediaId(), "thumb_media_id");
        Assert.assertEquals(wxMessage.getLocationX(), new Double(23.134521d));
        Assert.assertEquals(wxMessage.getLocationY(), new Double(113.358803d));
        Assert.assertEquals(wxMessage.getScale(), new Double(20));
        Assert.assertEquals(wxMessage.getLabel(), "位置信息");
        Assert.assertEquals(wxMessage.getDescription(), "公众平台官网链接");
        Assert.assertEquals(wxMessage.getUrl(), "url");
        Assert.assertEquals(wxMessage.getTitle(), "公众平台官网链接");
        Assert.assertEquals(wxMessage.getEvent(), "subscribe");
        Assert.assertEquals(wxMessage.getEventKey(), "qrscene_123123");
        Assert.assertEquals(wxMessage.getTicket(), "TICKET");
        Assert.assertEquals(wxMessage.getLatitude(), new Double(23.137466));
        Assert.assertEquals(wxMessage.getLongitude(), new Double(113.352425));
        Assert.assertEquals(wxMessage.getPrecision(), new Double(119.385040));
        Assert.assertEquals(wxMessage.getScanCodeInfo().getScanType(), "qrcode");
        Assert.assertEquals(wxMessage.getScanCodeInfo().getScanResult(), "1");
        Assert.assertEquals(wxMessage.getSendPicsInfo().getCount(), new Long(1l));
        Assert.assertEquals(wxMessage.getSendPicsInfo().getPicList().get(0).getPicMd5Sum(), "1b5f7c23b5bf75682a53e7b6d163e185");
        Assert.assertEquals(wxMessage.getSendLocationInfo().getLocationX(), "23");
        Assert.assertEquals(wxMessage.getSendLocationInfo().getLocationY(), "113");
        Assert.assertEquals(wxMessage.getSendLocationInfo().getScale(), "15");
        Assert.assertEquals(wxMessage.getSendLocationInfo().getLabel(), " 广州市海珠区客村艺苑路 106号");
        Assert.assertEquals(wxMessage.getSendLocationInfo().getPoiname(), "wo de poi");
    }

}
