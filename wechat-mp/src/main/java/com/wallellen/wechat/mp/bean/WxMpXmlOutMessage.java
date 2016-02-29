package com.wallellen.wechat.mp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wallellen.wechat.common.util.xml.XStreamCDataConverter;
import com.wallellen.wechat.mp.api.WxMpConfigStorage;
import com.wallellen.wechat.mp.bean.outxmlbuilder.ImageBuilder;
import com.wallellen.wechat.mp.bean.outxmlbuilder.MusicBuilder;
import com.wallellen.wechat.mp.bean.outxmlbuilder.NewsBuilder;
import com.wallellen.wechat.mp.bean.outxmlbuilder.TextBuilder;
import com.wallellen.wechat.mp.bean.outxmlbuilder.TransferCustomerServiceBuilder;
import com.wallellen.wechat.mp.bean.outxmlbuilder.VideoBuilder;
import com.wallellen.wechat.mp.bean.outxmlbuilder.VoiceBuilder;
import com.wallellen.wechat.mp.util.crypto.WxMpCryptUtil;
import com.wallellen.wechat.mp.util.xml.XStreamTransformer;

import java.io.Serializable;

@XStreamAlias("xml")
public abstract class WxMpXmlOutMessage implements Serializable {

    @XStreamAlias("ToUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String fromUserName;

    @XStreamAlias("CreateTime")
    protected Long createTime;

    @XStreamAlias("MsgType")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String msgType;

    /**
     * 获得文本消息builder
     *
     * @return
     */
    public static TextBuilder TEXT() {
        return new TextBuilder();
    }

    /**
     * 获得图片消息builder
     *
     * @return
     */
    public static ImageBuilder IMAGE() {
        return new ImageBuilder();
    }

    /**
     * 获得语音消息builder
     *
     * @return
     */
    public static VoiceBuilder VOICE() {
        return new VoiceBuilder();
    }

    /**
     * 获得视频消息builder
     *
     * @return
     */
    public static VideoBuilder VIDEO() {
        return new VideoBuilder();
    }

    /**
     * 获得音乐消息builder
     *
     * @return
     */
    public static MusicBuilder MUSIC() {
        return new MusicBuilder();
    }

    /**
     * 获得图文消息builder
     *
     * @return
     */
    public static NewsBuilder NEWS() {
        return new NewsBuilder();
    }

    /**
     * 获得客服消息builder
     *
     * @return
     */
    public static TransferCustomerServiceBuilder TRANSFER_CUSTOMER_SERVICE() {
        return new TransferCustomerServiceBuilder();
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String toXml() {
        return XStreamTransformer.toXml((Class) this.getClass(), this);
    }

    /**
     * 转换成加密的xml格式
     *
     * @return
     */
    public String toEncryptedXml(WxMpConfigStorage wxMpConfigStorage) {
        String plainXml = toXml();
        WxMpCryptUtil pc = new WxMpCryptUtil(wxMpConfigStorage);
        return pc.encrypt(plainXml);
    }
}
