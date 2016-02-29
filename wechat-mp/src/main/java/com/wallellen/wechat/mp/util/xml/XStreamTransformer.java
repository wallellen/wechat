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

package com.wallellen.wechat.mp.util.xml;

import com.thoughtworks.xstream.XStream;
import com.wallellen.wechat.common.util.xml.XStreamInitializer;
import com.wallellen.wechat.mp.bean.WxMpXmlMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutImageMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutMusicMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutNewsMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutTextMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutTransferCustomerServiceMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutVideoMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutVoiceMessage;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XStreamTransformer {

    protected static final Map<Class, XStream> CLASS_2_XSTREAM_INSTANCE = configXStreamInstance();

    /**
     * xml -> pojo
     *
     * @param clazz
     * @param xml
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXml(Class<T> clazz, String xml) {
        T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
        return object;
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXml(Class<T> clazz, InputStream is) {
        T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(is);
        return object;
    }

    /**
     * pojo -> xml
     *
     * @param clazz
     * @param object
     * @return
     */
    public static <T> String toXml(Class<T> clazz, T object) {
        return CLASS_2_XSTREAM_INSTANCE.get(clazz).toXML(object);
    }

    private static Map<Class, XStream> configXStreamInstance() {
        Map<Class, XStream> map = new HashMap<Class, XStream>();
        map.put(WxMpXmlMessage.class, config_WxMpXmlMessage());
        map.put(WxMpXmlOutMusicMessage.class, config_WxMpXmlOutMusicMessage());
        map.put(WxMpXmlOutNewsMessage.class, config_WxMpXmlOutNewsMessage());
        map.put(WxMpXmlOutTextMessage.class, config_WxMpXmlOutTextMessage());
        map.put(WxMpXmlOutImageMessage.class, config_WxMpXmlOutImageMessage());
        map.put(WxMpXmlOutVideoMessage.class, config_WxMpXmlOutVideoMessage());
        map.put(WxMpXmlOutVoiceMessage.class, config_WxMpXmlOutVoiceMessage());
        map.put(WxMpXmlOutTransferCustomerServiceMessage.class, config_WxMpXmlOutTransferCustomerServiceMessage());

        return map;
    }

    private static XStream config_WxMpXmlMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpXmlMessage.class);
        xstream.processAnnotations(WxMpXmlMessage.ScanCodeInfo.class);
        xstream.processAnnotations(WxMpXmlMessage.SendPicsInfo.class);
        xstream.processAnnotations(WxMpXmlMessage.SendPicsInfo.Item.class);
        xstream.processAnnotations(WxMpXmlMessage.SendLocationInfo.class);

        xstream.aliasField("MsgID", WxMpXmlMessage.class, "msgId");
        return xstream;
    }

    private static XStream config_WxMpXmlOutImageMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpXmlOutMessage.class);
        xstream.processAnnotations(WxMpXmlOutImageMessage.class);
        return xstream;
    }

    private static XStream config_WxMpXmlOutNewsMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpXmlOutMessage.class);
        xstream.processAnnotations(WxMpXmlOutNewsMessage.class);
        xstream.processAnnotations(WxMpXmlOutNewsMessage.Item.class);
        return xstream;
    }

    private static XStream config_WxMpXmlOutMusicMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpXmlOutMessage.class);
        xstream.processAnnotations(WxMpXmlOutMusicMessage.class);
        xstream.processAnnotations(WxMpXmlOutMusicMessage.Music.class);
        return xstream;
    }

    private static XStream config_WxMpXmlOutTextMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpXmlOutMessage.class);
        xstream.processAnnotations(WxMpXmlOutTextMessage.class);
        return xstream;
    }

    private static XStream config_WxMpXmlOutVideoMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpXmlOutMessage.class);
        xstream.processAnnotations(WxMpXmlOutVideoMessage.class);
        xstream.processAnnotations(WxMpXmlOutVideoMessage.Video.class);
        return xstream;
    }

    private static XStream config_WxMpXmlOutVoiceMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpXmlOutMessage.class);
        xstream.processAnnotations(WxMpXmlOutVoiceMessage.class);
        return xstream;
    }

    private static XStream config_WxMpXmlOutTransferCustomerServiceMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpXmlOutMessage.class);
        xstream.processAnnotations(WxMpXmlOutTransferCustomerServiceMessage.class);
        xstream.processAnnotations(WxMpXmlOutTransferCustomerServiceMessage.TransInfo.class);
        return xstream;
    }

}
