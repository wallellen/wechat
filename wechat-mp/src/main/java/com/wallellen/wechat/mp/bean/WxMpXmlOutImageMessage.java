package com.wallellen.wechat.mp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.common.util.xml.XStreamMediaIdConverter;

@XStreamAlias("xml")
public class WxMpXmlOutImageMessage extends WxMpXmlOutMessage {

    @XStreamAlias("Image")
    @XStreamConverter(value = XStreamMediaIdConverter.class)
    private String mediaId;

    public WxMpXmlOutImageMessage() {
        this.msgType = WxConsts.XML_MSG_IMAGE;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
