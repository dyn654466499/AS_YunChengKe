package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.city.FreeJoinResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 作者：Tinchy
 * 创建时间：2016/2/26 17:04
 * 描述：
 * 版本：1.0
 */
public class FreeJoinRequest extends GsonRequest<FreeJoinResult> {
    public FreeJoinRequest(HttpRequestListener<FreeJoinResult> listener) {
        super(HttpUrls.HTTP_FREEJOIN_PAGE, FreeJoinResult.class, listener);
    }

    public void setRequestParams(int id) {
        JsonObject param = new JsonObject();
        param.addProperty("Id",id);
        mParams.put("param", param.toString());
    }
}
