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

package com.wallellen.wechat.cp.api;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wallellen.wechat.common.util.xml.XStreamInitializer;

import java.io.InputStream;

public class ApiTestModule implements Module {

    public static <T> T fromXml(Class<T> clazz, InputStream is) {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.alias("xml", clazz);
        xstream.processAnnotations(clazz);
        return (T) xstream.fromXML(is);
    }

    @Override
    public void configure(Binder binder) {
        InputStream is1 = ClassLoader.getSystemResourceAsStream("test-config.xml");

        WxXmlCpInMemoryConfigStorage config = fromXml(WxXmlCpInMemoryConfigStorage.class, is1);
        WxCpServiceImpl wxService = new WxCpServiceImpl();
        wxService.setWxCpConfigStorage(config);

        binder.bind(WxCpServiceImpl.class).toInstance(wxService);
        binder.bind(WxCpConfigStorage.class).toInstance(config);
    }

    @XStreamAlias("xml")
    public static class WxXmlCpInMemoryConfigStorage extends WxCpInMemoryConfigStorage {

        protected String userId;

        protected String departmentId;

        protected String tagId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public String getTagId() {
            return tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        @Override
        public String toString() {
            return super.toString() + " > WxXmlCpConfigStorage{" +
                    "userId='" + userId + '\'' +
                    ", departmentId='" + departmentId + '\'' +
                    ", tagId='" + tagId + '\'' +
                    '}';
        }
    }

}
