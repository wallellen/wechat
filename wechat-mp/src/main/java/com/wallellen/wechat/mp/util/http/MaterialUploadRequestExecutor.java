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

package com.wallellen.wechat.mp.util.http;

import com.wallellen.wechat.common.bean.result.WxError;
import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.common.util.http.RequestExecutor;
import com.wallellen.wechat.common.util.http.Utf8ResponseHandler;
import com.wallellen.wechat.common.util.json.WxGsonBuilder;
import com.wallellen.wechat.mp.bean.WxMpMaterial;
import com.wallellen.wechat.mp.bean.result.WxMpMaterialUploadResult;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class MaterialUploadRequestExecutor implements RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {

    public WxMpMaterialUploadResult execute(CloseableHttpClient httpClient, HttpHost httpProxy, String uri, WxMpMaterial material) throws WxErrorException, IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (httpProxy != null) {
            RequestConfig response = RequestConfig.custom().setProxy(httpProxy).build();
            httpPost.setConfig(response);
        }

        if (material != null) {
            File file = material.getFile();
            if (file == null || !file.exists()) {
                throw new FileNotFoundException();
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder
                    .addPart("media", new InputStreamBody(bufferedInputStream, material.getName()))
                    .setMode(HttpMultipartMode.RFC6532);
            Map<String, String> form = material.getForm();
            if (material.getForm() != null) {
                multipartEntityBuilder.addTextBody("description", WxGsonBuilder.create().toJson(form));
            }
            httpPost.setEntity(multipartEntityBuilder.build());
            httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());
        }

        CloseableHttpResponse response = httpClient.execute(httpPost);
        String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
        WxError error = WxError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        } else {
            return WxMpMaterialUploadResult.fromJson(responseContent);
        }
    }

}
