package com.wallellen.wechat.mp.demo;

import com.wallellen.wechat.common.session.WxSessionManager;
import com.wallellen.wechat.mp.api.WxMpMessageHandler;
import com.wallellen.wechat.mp.api.WxMpService;
import com.wallellen.wechat.mp.bean.WxMpXmlMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutTextMessage;

import java.util.Map;

/**
 * Created by qianjia on 15/1/22.
 */
public class DemoTextHandler implements WxMpMessageHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
      WxMpService wxMpService, WxSessionManager sessionManager) {
    WxMpXmlOutTextMessage m
        = WxMpXmlOutMessage.TEXT().content("测试加密消息").fromUser(wxMessage.getToUserName())
        .toUser(wxMessage.getFromUserName()).build();
    return m;
  }

}
