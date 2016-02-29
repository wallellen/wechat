package com.wallellen.wechat.common.bean;

import com.wallellen.wechat.common.util.json.WxGsonBuilder;

import java.io.Serializable;

public class WxAccessToken implements Serializable {

    private String accessToken;

    private int expiresIn = -1;

    public static WxAccessToken fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WxAccessToken.class);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
