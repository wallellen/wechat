package com.wallellen.wechat.mp.bean.result;

import com.wallellen.wechat.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

public class WxMpMaterialUploadResult implements Serializable {

    private String mediaId;
    private String url;

    public static WxMpMaterialUploadResult fromJson(String json) {
        return WxMpGsonBuilder.create().fromJson(json, WxMpMaterialUploadResult.class);
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WxMpMaterialUploadResult [media_id=" + mediaId + ", url=" + url + "]";
    }

}

