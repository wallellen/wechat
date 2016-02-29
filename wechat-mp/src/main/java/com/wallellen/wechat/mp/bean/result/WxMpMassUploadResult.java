package com.wallellen.wechat.mp.bean.result;

import com.wallellen.wechat.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 上传群发用的素材的结果
 * 视频和图文消息需要在群发前上传素材
 * </pre>
 *
 * @author chanjarster
 */
public class WxMpMassUploadResult implements Serializable {

    private String type;
    private String mediaId;
    private long createdAt;

    public static WxMpMassUploadResult fromJson(String json) {
        return WxMpGsonBuilder.create().fromJson(json, WxMpMassUploadResult.class);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "WxUploadResult [type=" + type + ", media_id=" + mediaId + ", created_at=" + createdAt + "]";
    }

}
