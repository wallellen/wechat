package com.wallellen.wechat.mp.demo;

import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.common.session.WxSessionManager;
import com.wallellen.wechat.mp.api.WxMpMessageHandler;
import com.wallellen.wechat.mp.api.WxMpService;
import com.wallellen.wechat.mp.bean.WxMpXmlMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutMessage;

import java.util.Map;

/**
 * Created by qianjia on 15/1/22.
 */
public class DemoOAuth2Handler implements WxMpMessageHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
      WxMpService wxMpService, WxSessionManager sessionManager) {
    String href = "<a href=\"" + wxMpService.oauth2buildAuthorizationUrl(WxConsts.OAUTH2_SCOPE_USER_INFO, null)
        + "\">测试oauth2</a>";
    return WxMpXmlOutMessage
        .TEXT()
        .content(href)
        .fromUser(wxMessage.getToUserName())
        .toUser(wxMessage.getFromUserName()).build();
  }
}
