package com.wallellen.wechat.cp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.common.util.xml.XStreamMediaIdConverter;

@XStreamAlias("xml")
public class WxCpXmlOutImageMessage extends WxCpXmlOutMessage {

    @XStreamAlias("Image")
    @XStreamConverter(value = XStreamMediaIdConverter.class)
    private String mediaId;

    public WxCpXmlOutImageMessage() {
        this.msgType = WxConsts.XML_MSG_IMAGE;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
