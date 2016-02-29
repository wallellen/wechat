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
import com.wallellen.wechat.common.util.http.InputStreamResponseHandler;
import com.wallellen.wechat.common.util.http.RequestExecutor;
import com.wallellen.wechat.common.util.json.WxGsonBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MaterialVoiceAndImageDownloadRequestExecutor implements RequestExecutor<InputStream, String> {

    private File tmpDirFile;

    public MaterialVoiceAndImageDownloadRequestExecutor() {
        super();
    }

    public MaterialVoiceAndImageDownloadRequestExecutor(File tmpDirFile) {
        super();
        this.tmpDirFile = tmpDirFile;
    }

    public InputStream execute(CloseableHttpClient httpClient, HttpHost httpProxy, String uri, String materialId) throws WxErrorException, IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (httpProxy != null) {
            RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
            httpPost.setConfig(config);
        }

        Map<String, String> params = new HashMap<>();
        params.put("media_id", materialId);
        httpPost.setEntity(new StringEntity(WxGsonBuilder.create().toJson(params)));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 下载媒体文件出错
        InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response);
        byte[] responseContent = IOUtils.toByteArray(inputStream);
        String responseContentString = new String(responseContent, "UTF-8");
        if (responseContentString.length() < 100) {
            try {
                WxError wxError = WxGsonBuilder.create().fromJson(responseContentString, WxError.class);
                if (wxError.getErrorCode() != 0) {
                    throw new WxErrorException(wxError);
                }
            } catch (com.google.gson.JsonSyntaxException ex) {
                return new ByteArrayInputStream(responseContent);
            }
        }
        return new ByteArrayInputStream(responseContent);
    }

}
