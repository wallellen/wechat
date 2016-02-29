package com.wallellen.wechat.cp.bean;

import com.wallellen.wechat.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * Created by wallellen
 */
public class WxCpTag implements Serializable {

    private String id;

    private String name;

    public WxCpTag() {
        super();
    }

    public WxCpTag(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public static WxCpTag fromJson(String json) {
        return WxCpGsonBuilder.create().fromJson(json, WxCpTag.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toJson() {
        return WxCpGsonBuilder.create().toJson(this);
    }

}
