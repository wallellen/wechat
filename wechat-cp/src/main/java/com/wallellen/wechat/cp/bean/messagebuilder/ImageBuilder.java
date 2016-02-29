package com.wallellen.wechat.cp.bean.messagebuilder;


import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.cp.bean.WxCpMessage;

/**
 * 获得消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.IMAGE().mediaId(...).toUser(...).build();
 * </pre>
 *
 * @author wallellen
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder> {
    private String mediaId;

    public ImageBuilder() {
        this.msgType = WxConsts.CUSTOM_MSG_IMAGE;
    }

    public ImageBuilder mediaId(String media_id) {
        this.mediaId = media_id;
        return this;
    }

    public WxCpMessage build() {
        WxCpMessage m = super.build();
        m.setMediaId(this.mediaId);
        return m;
    }
}
