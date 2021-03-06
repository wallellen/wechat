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

package com.wallellen.wechat.cp.util.xml;

import com.thoughtworks.xstream.XStream;
import com.wallellen.wechat.common.util.xml.XStreamInitializer;
import com.wallellen.wechat.cp.bean.WxCpXmlMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutImageMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutNewsMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutTextMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutVideoMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutVoiceMessage;

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
        Map<Class, XStream> map = new HashMap<>();
        map.put(WxCpXmlMessage.class, config_WxCpXmlMessage());
        map.put(WxCpXmlOutNewsMessage.class, config_WxCpXmlOutNewsMessage());
        map.put(WxCpXmlOutTextMessage.class, config_WxCpXmlOutTextMessage());
        map.put(WxCpXmlOutImageMessage.class, config_WxCpXmlOutImageMessage());
        map.put(WxCpXmlOutVideoMessage.class, config_WxCpXmlOutVideoMessage());
        map.put(WxCpXmlOutVoiceMessage.class, config_WxCpXmlOutVoiceMessage());
        return map;
    }

    private static XStream config_WxCpXmlMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxCpXmlMessage.class);
        xstream.processAnnotations(WxCpXmlMessage.ScanCodeInfo.class);
        xstream.processAnnotations(WxCpXmlMessage.SendPicsInfo.class);
        xstream.processAnnotations(WxCpXmlMessage.SendPicsInfo.Item.class);
        xstream.processAnnotations(WxCpXmlMessage.SendLocationInfo.class);
        return xstream;
    }

    private static XStream config_WxCpXmlOutImageMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxCpXmlOutMessage.class);
        xstream.processAnnotations(WxCpXmlOutImageMessage.class);
        return xstream;
    }

    private static XStream config_WxCpXmlOutNewsMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxCpXmlOutMessage.class);
        xstream.processAnnotations(WxCpXmlOutNewsMessage.class);
        xstream.processAnnotations(WxCpXmlOutNewsMessage.Item.class);
        return xstream;
    }

    private static XStream config_WxCpXmlOutTextMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxCpXmlOutMessage.class);
        xstream.processAnnotations(WxCpXmlOutTextMessage.class);
        return xstream;
    }

    private static XStream config_WxCpXmlOutVideoMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxCpXmlOutMessage.class);
        xstream.processAnnotations(WxCpXmlOutVideoMessage.class);
        xstream.processAnnotations(WxCpXmlOutVideoMessage.Video.class);
        return xstream;
    }

    private static XStream config_WxCpXmlOutVoiceMessage() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxCpXmlOutMessage.class);
        xstream.processAnnotations(WxCpXmlOutVoiceMessage.class);
        return xstream;
    }

}
