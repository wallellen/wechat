package com.wallellen.wechat.mp.demo;

import com.wallellen.wechat.common.session.WxSessionManager;
import com.wallellen.wechat.mp.api.WxMpMessageHandler;
import com.wallellen.wechat.mp.api.WxMpService;
import com.wallellen.wechat.mp.bean.WxMpXmlMessage;
import com.wallellen.wechat.mp.bean.WxMpXmlOutMessage;

import java.util.Map;

/**
 * Created by wallellen on 15/1/22.
 */
public class DemoLogHandler implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        System.out.println(wxMessage.toString());
        return null;
    }
}
