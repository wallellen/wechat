package com.wallellen.wechat.mp.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wallellen.wechat.mp.bean.WxMpCustomMessage;
import com.wallellen.wechat.mp.bean.WxMpGroup;
import com.wallellen.wechat.mp.bean.WxMpMassGroupMessage;
import com.wallellen.wechat.mp.bean.WxMpMassNews;
import com.wallellen.wechat.mp.bean.WxMpMassOpenIdsMessage;
import com.wallellen.wechat.mp.bean.WxMpMassVideo;
import com.wallellen.wechat.mp.bean.WxMpMaterialArticleUpdate;
import com.wallellen.wechat.mp.bean.WxMpMaterialNews;
import com.wallellen.wechat.mp.bean.WxMpTemplateMessage;
import com.wallellen.wechat.mp.bean.result.WxMpMassSendResult;
import com.wallellen.wechat.mp.bean.result.WxMpMassUploadResult;
import com.wallellen.wechat.mp.bean.result.WxMpMaterialCountResult;
import com.wallellen.wechat.mp.bean.result.WxMpMaterialFileBatchGetResult;
import com.wallellen.wechat.mp.bean.result.WxMpMaterialNewsBatchGetResult;
import com.wallellen.wechat.mp.bean.result.WxMpMaterialUploadResult;
import com.wallellen.wechat.mp.bean.result.WxMpMaterialVideoInfoResult;
import com.wallellen.wechat.mp.bean.result.WxMpOAuth2AccessToken;
import com.wallellen.wechat.mp.bean.result.WxMpQrCodeTicket;
import com.wallellen.wechat.mp.bean.result.WxMpSemanticQueryResult;
import com.wallellen.wechat.mp.bean.result.WxMpUser;
import com.wallellen.wechat.mp.bean.result.WxMpUserCumulate;
import com.wallellen.wechat.mp.bean.result.WxMpUserList;
import com.wallellen.wechat.mp.bean.result.WxMpUserSummary;

public class WxMpGsonBuilder {

    public static final GsonBuilder INSTANCE = new GsonBuilder();

    static {
        INSTANCE.disableHtmlEscaping();
        INSTANCE.registerTypeAdapter(WxMpCustomMessage.class, new WxMpCustomMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassNews.class, new WxMpMassNewsGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassGroupMessage.class, new WxMpMassGroupMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassOpenIdsMessage.class, new WxMpMassOpenIdsMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpGroup.class, new WxMpGroupGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpUser.class, new WxMpUserGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpUserList.class, new WxUserListGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassVideo.class, new WxMpMassVideoAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassSendResult.class, new WxMpMassSendResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassUploadResult.class, new WxMpMassUploadResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpQrCodeTicket.class, new WxQrCodeTicketAdapter());
        INSTANCE.registerTypeAdapter(WxMpTemplateMessage.class, new WxMpTemplateMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpSemanticQueryResult.class, new WxMpSemanticQueryResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpOAuth2AccessToken.class, new WxMpOAuth2AccessTokenAdapter());
        INSTANCE.registerTypeAdapter(WxMpUserSummary.class, new WxMpUserSummaryGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpUserCumulate.class, new WxMpUserCumulateGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialUploadResult.class, new WxMpMaterialUploadResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialVideoInfoResult.class, new WxMpMaterialVideoInfoResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassNews.WxMpMassNewsArticle.class, new WxMpMassNewsArticleGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialArticleUpdate.class, new WxMpMaterialArticleUpdateGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialCountResult.class, new WxMpMaterialCountResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialNews.class, new WxMpMaterialNewsGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialNews.WxMpMaterialNewsArticle.class, new WxMpMaterialNewsArticleGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialNewsBatchGetResult.class, new WxMpMaterialNewsBatchGetGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem.class, new WxMpMaterialNewsBatchGetGsonItemAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialFileBatchGetResult.class, new WxMpMaterialFileBatchGetGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem.class, new WxMpMaterialFileBatchGetGsonItemAdapter());
    }

    public static Gson create() {
        return INSTANCE.create();
    }

}
