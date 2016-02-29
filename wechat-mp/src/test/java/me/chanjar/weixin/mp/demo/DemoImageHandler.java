package com.wallellen.wechat.mp.demo;

import com.wallellen.wechat.common.api.WxConsts;
import com.wallellen.wechat.common.bean.result.WxMediaUploadResult;
import  com.wallellen.wechat.common.exception.WxErrorException;
import com.wallellen.wechat.common.session.WxSessionManager;
import com.wallellen.wechat.mp.api.WxMpMessageHandler;
import com.wallellen.wechat.mp.api.WxMpService;
import com.wallellen.wechat.mp.bean.WxMpXmlMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutImageMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutMessage;

import java.io.IOException;
import java.util.Map;

public class DemoImageHandler implements WxMpMessageHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
      WxMpService wxMpService, WxSessionManager sessionManager) {
    try {
      WxMediaUploadResult wxMediaUploadResult = wxMpService
          .mediaUpload(WxConsts.MEDIA_IMAGE, WxConsts.FILE_JPG, ClassLoader.getSystemResourceAsStream("mm.jpeg"));
      WxMpXmlOutImageMessage m
          = WxMpXmlOutMessage
          .IMAGE()
          .mediaId(wxMediaUploadResult.getMediaId())
          .fromUser(wxMessage.getToUserName())
          .toUser(wxMessage.getFromUserName())
          .build();
      return m;
    } catch (WxErrorException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
