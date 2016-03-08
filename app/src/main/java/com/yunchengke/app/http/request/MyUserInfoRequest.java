package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.MyUserInfo;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 类名：MyUserInfoRequest <br/>
 * 描述：用户信息
 * 创建时间：2016/01/28 20:29
 *
 * @author hanter
 * @version 1.0
 */
public class MyUserInfoRequest extends GsonRequest<MyUserInfo> {
    public MyUserInfoRequest(HttpRequestListener<MyUserInfo> listener) {
        super(HttpUrls.HTTP_USER_INFO_LIST_REQUEST, MyUserInfo.class, listener);
    }

    public void setRequestParams(long userId) {
        JsonObject json = new JsonObject();
        json.addProperty("Id", userId);

        mParams.put("param", json.toString());
    }

}
