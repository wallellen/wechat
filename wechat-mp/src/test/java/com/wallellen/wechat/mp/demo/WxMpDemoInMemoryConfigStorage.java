package com.wallellen.wechat.mp.demo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wallellen.wechat.common.util.xml.XStreamInitializer;
import com.wallellen.wechat.mp.api.WxMpInMemoryConfigStorage;

import java.io.InputStream;

/**
 * @author wallellen
 */
@XStreamAlias("xml")
class WxMpDemoInMemoryConfigStorage extends WxMpInMemoryConfigStorage {

    public static WxMpDemoInMemoryConfigStorage fromXml(InputStream is) {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpDemoInMemoryConfigStorage.class);
        return (WxMpDemoInMemoryConfigStorage) xstream.fromXML(is);
    }

    @Override
    public String toString() {
        return "SimpleWxConfigProvider [appId=" + appId + ", secret=" + secret + ", accessToken=" + accessToken
                + ", expiresTime=" + expiresTime + ", token=" + token + ", aesKey=" + aesKey + "]";
    }

}
