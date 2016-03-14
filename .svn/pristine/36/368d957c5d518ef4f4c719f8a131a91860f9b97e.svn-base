package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.LoginResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/13 16:02
 * 描述：登录请求
 * 版本：1.0
 */
public class Login_Request extends GsonRequest<LoginResult>{

    public Login_Request(HttpRequestListener<LoginResult> listener) {
        super(HttpUrls.HTTP_LOGIN_REQUEST, LoginResult.class, null, listener);
    }

    public void setRequestParams(String param) {
        mParams.put("param", param);
    }
}
