package com.wallellen.wechat.mp.bean.outxmlbuilder;

import com.wallellen.wechat.mp.bean.WxMpXmlOutImageMessage;

/**
 * 图片消息builder
 *
 * @author chanjarster
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder, WxMpXmlOutImageMessage> {

    private String mediaId;

    public ImageBuilder mediaId(String media_id) {
        this.mediaId = media_id;
        return this;
    }

    public WxMpXmlOutImageMessage build() {
        WxMpXmlOutImageMessage m = new WxMpXmlOutImageMessage();
        setCommon(m);
        m.setMediaId(this.mediaId);
        return m;
    }

}
