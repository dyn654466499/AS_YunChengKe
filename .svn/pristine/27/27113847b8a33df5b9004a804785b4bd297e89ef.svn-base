package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.city.YCKAddCommentResult;
import com.yunchengke.app.bean.city.YCKCommentList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 作者：Tinchy
 * 创建时间：2016/2/3 17:14
 * 描述：
 * 版本：1.0
 */
public class YCKAddCommentRequest extends GsonRequest<YCKAddCommentResult> {
    public YCKAddCommentRequest(HttpRequestListener<YCKAddCommentResult> listener) {
        super(HttpUrls.HTTP_ACTIVE_ADD_COMMENT, YCKAddCommentResult.class, listener);
    }
    public void setRequestParams(int id,String edit) {

        JsonObject json = new JsonObject();
        json.addProperty("Id", id);
        json.addProperty("Field_PLNR", edit);
        mParams.put("param", json.toString());
    }
}
