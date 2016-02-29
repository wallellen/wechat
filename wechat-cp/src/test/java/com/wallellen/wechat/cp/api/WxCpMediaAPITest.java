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

import com.google.inject.Inject;
import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.common.bean.result.WxMediaUploadResult;
import com.wallellen.wechat.common.exception.WxErrorException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试多媒体文件上传下载
 *
 * @author wallellen
 */
//@Test(groups="mediaAPI", dependsOnGroups="baseAPI")
@Test
@Guice(modules = ApiTestModule.class)
public class WxCpMediaAPITest {

    @Inject
    protected WxCpServiceImpl wxService;

    private List<String> media_ids = new ArrayList<String>();

    @Test(dataProvider = "uploadMedia")
    public void testUploadMedia(String mediaType, String fileType, String fileName) throws WxErrorException, IOException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
        WxMediaUploadResult res = wxService.mediaUpload(mediaType, fileType, inputStream);
        Assert.assertNotNull(res.getType());
        Assert.assertNotNull(res.getCreatedAt());
        Assert.assertTrue(res.getMediaId() != null || res.getThumbMediaId() != null);

        if (res.getMediaId() != null) {
            media_ids.add(res.getMediaId());
        }
        if (res.getThumbMediaId() != null) {
            media_ids.add(res.getThumbMediaId());
        }
    }

    @DataProvider
    public Object[][] uploadMedia() {
        return new Object[][]{
                new Object[]{WxConsts.MEDIA_IMAGE, WxConsts.FILE_JPG, "mm.jpeg"},
                new Object[]{WxConsts.MEDIA_VOICE, WxConsts.FILE_MP3, "mm.mp3"},
                new Object[]{WxConsts.MEDIA_VIDEO, WxConsts.FILE_MP4, "mm.mp4"},
                new Object[]{WxConsts.MEDIA_FILE, WxConsts.FILE_JPG, "mm.jpeg"}
        };
    }

    @Test(dependsOnMethods = {"testUploadMedia"}, dataProvider = "downloadMedia")
    public void testDownloadMedia(String media_id) throws WxErrorException {
        wxService.mediaDownload(media_id);
    }

    @DataProvider
    public Object[][] downloadMedia() {
        Object[][] params = new Object[this.media_ids.size()][];
        for (int i = 0; i < this.media_ids.size(); i++) {
            params[i] = new Object[]{this.media_ids.get(i)};
        }
        return params;
    }

}
