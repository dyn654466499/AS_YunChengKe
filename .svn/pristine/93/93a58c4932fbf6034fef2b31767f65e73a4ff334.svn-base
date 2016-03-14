package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.CommonRequestResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 名称: FollowUserRequest <br/>
 * 描述: 关注用户 <br/>
 * 创建时间：2016/1/29 15:16
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class FollowUserRequest extends GsonRequest<CommonRequestResult> {
    public FollowUserRequest(HttpRequestListener<CommonRequestResult> listener) {
        super(HttpUrls.HTTP_FOLLOW_USER_REQUEST, CommonRequestResult.class, listener);
    }

    public void setRequestParams(int userType, long userId, boolean follow) {
        JsonObject json = new JsonObject();
        json.addProperty("Field_GZMK", 1);
        json.addProperty("Field_YHID2", userId);
        if (follow) {
            json.addProperty("Type", "follow");
        } else {
            json.addProperty("Type", "cancel");
        }

        if (userType == 2) {
            json.addProperty("X6_Product_Recommend", "1");
        } else {
            json.addProperty("X6_Product_Recommend", "");
        }

        mParams.put("param", json.toString());
    }
}
