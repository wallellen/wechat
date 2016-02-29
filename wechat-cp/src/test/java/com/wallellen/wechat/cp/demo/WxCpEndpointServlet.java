package com.wallellen.wechat.cp.demo;

import com.wallellen.wechat.common.util.StringUtils;
import com.wallellen.wechat.cp.api.WxCpConfigStorage;
import com.wallellen.wechat.cp.api.WxCpMessageRouter;
import com.wallellen.wechat.cp.api.WxCpService;
import com.wallellen.wechat.cp.bean.WxCpXmlMessage;
import com.wallellen.wechat.cp.bean.WxCpXmlOutMessage;
import com.wallellen.wechat.cp.util.crypto.WxCpCryptUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wallellen
 */
public class WxCpEndpointServlet extends HttpServlet {

  protected WxCpConfigStorage wxCpConfigStorage;
  protected WxCpService wxCpService;
  protected WxCpMessageRouter wxCpMessageRouter;

  public WxCpEndpointServlet(WxCpConfigStorage wxCpConfigStorage, WxCpService wxCpService,
      WxCpMessageRouter wxCpMessageRouter) {
    this.wxCpConfigStorage = wxCpConfigStorage;
    this.wxCpService = wxCpService;
    this.wxCpMessageRouter = wxCpMessageRouter;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String msgSignature = request.getParameter("msg_signature");
    String nonce = request.getParameter("nonce");
    String timestamp = request.getParameter("timestamp");
    String echostr = request.getParameter("echostr");

    if (StringUtils.isNotBlank(echostr)) {
      if (!wxCpService.checkSignature(msgSignature, timestamp, nonce, echostr)) {
        // 消息签名不正确，说明不是公众平台发过来的消息
        response.getWriter().println("非法请求");
        return;
      }
      WxCpCryptUtil cryptUtil = new WxCpCryptUtil(wxCpConfigStorage);
      String plainText = cryptUtil.decrypt(echostr);
      // 说明是一个仅仅用来验证的请求，回显echostr
      response.getWriter().println(plainText);
      return;
    }

    WxCpXmlMessage inMessage = WxCpXmlMessage
        .fromEncryptedXml(request.getInputStream(), wxCpConfigStorage, timestamp, nonce, msgSignature);
    WxCpXmlOutMessage outMessage = wxCpMessageRouter.route(inMessage);
    if (outMessage != null) {
      response.getWriter().write(outMessage.toEncryptedXml(wxCpConfigStorage));
    }

    return;
  }

}
