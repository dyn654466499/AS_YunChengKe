package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.FollowMediaResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：FollowMediaRequest <br/>
 * 描述：关注媒体请求
 * 创建时间：2016/01/19 21:15
 *
 * @author hanter
 * @version 1.0
 */
public class FollowMediaRequest extends GsonRequest<FollowMediaResult> {

    public FollowMediaRequest(HttpRequestListener<FollowMediaResult> listener) {
        super(HttpUrls.HTTP_FOLLOW_MEDIA_REQUEST, FollowMediaResult.class, listener);
    }

    public void setRequestParams(String type,int id) {

        mParams.put("type", type);
        mParams.put("id",String.valueOf(id));
    }
}
