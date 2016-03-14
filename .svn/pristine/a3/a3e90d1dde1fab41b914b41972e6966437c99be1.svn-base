package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.FollowMediaResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 作者：Tinchy
 * 创建时间：2016/2/4 19:22
 * 描述：
 * 版本：1.0
 */
public class ActivityFocus extends GsonRequest<FollowMediaResult>{
    public ActivityFocus(HttpRequestListener<FollowMediaResult> listener) {
        super(HttpUrls.HTTP_ACTIVEIN_FOCUS, FollowMediaResult.class, listener);
    }

    public void setRequestParams(int id) {
        JsonObject param = new JsonObject();
        param.addProperty("Id",id);
        mParams.put("param", param.toString());
    }
}
