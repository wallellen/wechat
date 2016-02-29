package com.wallellen.wechat.common.api;

import com.wallellen.wechat.common.exception.WxErrorException;

/**
 * WxErrorException处理器
 */
public interface WxErrorExceptionHandler {

  public void handle(WxErrorException e);

}
