package com.wallellen.wechat.cp.bean.messagebuilder;

import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.cp.bean.WxCpMessage;

/**
 * 文本消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.TEXT().content(...).toUser(...).build();
 * </pre>
 *
 * @author wallellen
 */
public final class TextBuilder extends BaseBuilder<TextBuilder> {
    private String content;

    public TextBuilder() {
        this.msgType = WxConsts.CUSTOM_MSG_TEXT;
    }

    public TextBuilder content(String content) {
        this.content = content;
        return this;
    }

    public WxCpMessage build() {
        WxCpMessage m = super.build();
        m.setContent(this.content);
        return m;
    }
}
