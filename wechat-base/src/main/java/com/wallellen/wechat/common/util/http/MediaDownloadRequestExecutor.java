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

package com.wallellen.wechat.common.util.http;

import com.wallellen.wechat.common.bean.result.WxError;
import com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.common.util.StringUtils;
import com.wallellen.wechat.common.util.fs.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下载媒体文件请求执行器，请求的参数是String, 返回的结果是File
 *
 * @author wallellen
 */
public class MediaDownloadRequestExecutor implements RequestExecutor<File, String> {

    private File tmpDirFile;

    public MediaDownloadRequestExecutor() {
        super();
    }

    public MediaDownloadRequestExecutor(File tmpDirFile) {
        super();
        this.tmpDirFile = tmpDirFile;
    }


    @Override
    public File execute(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, String queryParam) throws WxErrorException, ClientProtocolException, IOException {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1) {
                uri += '?';
            }
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }

        HttpGet httpGet = new HttpGet(uri);
        if (httpProxy != null) {
            RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
            httpGet.setConfig(config);
        }

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {

            Header[] contentTypeHeader = response.getHeaders("Content-Type");
            if (contentTypeHeader != null && contentTypeHeader.length > 0) {
                // 下载媒体文件出错
                if (ContentType.TEXT_PLAIN.getMimeType().equals(contentTypeHeader[0].getValue())) {
                    String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
                    throw new WxErrorException(WxError.fromJson(responseContent));
                }
            }
            InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response);

            // 视频文件不支持下载
            String fileName = getFileName(response);
            if (StringUtils.isBlank(fileName)) {
                return null;
            }
            String[] name_ext = fileName.split("\\.");
            File localFile = FileUtils.createTmpFile(inputStream, name_ext[0], name_ext[1], tmpDirFile);
            return localFile;

        }

    }

    protected String getFileName(CloseableHttpResponse response) {
        Header[] contentDispositionHeader = response.getHeaders("Content-disposition");
        Pattern p = Pattern.compile(".*filename=\"(.*)\"");
        Matcher m = p.matcher(contentDispositionHeader[0].getValue());
        m.matches();
        String fileName = m.group(1);
        return fileName;
    }

}
