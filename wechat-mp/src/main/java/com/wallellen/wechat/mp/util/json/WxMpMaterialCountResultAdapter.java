/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package com.wallellen.wechat.mp.util.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wallellen.wechat.common.util.json.GsonHelper;
import com.wallellen.wechat.mp.bean.result.WxMpMaterialCountResult;

import java.lang.reflect.Type;

/**
 * @author codepiano
 */
public class WxMpMaterialCountResultAdapter implements JsonDeserializer<WxMpMaterialCountResult> {

    public WxMpMaterialCountResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        WxMpMaterialCountResult countResult = new WxMpMaterialCountResult();
        JsonObject materialCountResultJsonObject = json.getAsJsonObject();

        if (materialCountResultJsonObject.get("voice_count") != null && !materialCountResultJsonObject.get("voice_count").isJsonNull()) {
            countResult.setVoiceCount(GsonHelper.getAsInteger(materialCountResultJsonObject.get("voice_count")));
        }
        if (materialCountResultJsonObject.get("video_count") != null && !materialCountResultJsonObject.get("video_count").isJsonNull()) {
            countResult.setVideoCount(GsonHelper.getAsInteger(materialCountResultJsonObject.get("video_count")));
        }
        if (materialCountResultJsonObject.get("image_count") != null && !materialCountResultJsonObject.get("image_count").isJsonNull()) {
            countResult.setImageCount(GsonHelper.getAsInteger(materialCountResultJsonObject.get("image_count")));
        }
        if (materialCountResultJsonObject.get("news_count") != null && !materialCountResultJsonObject.get("news_count").isJsonNull()) {
            countResult.setNewsCount(GsonHelper.getAsInteger(materialCountResultJsonObject.get("news_count")));
        }
        return countResult;
    }

}
