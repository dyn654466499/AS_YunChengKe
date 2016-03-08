package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.LoginResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/14 14:17
 * 描述：注册请求
 * 版本：1.0
 */
public class RegisterRequest extends GsonRequest<LoginResult> {
    public RegisterRequest(HttpRequestListener<LoginResult> listener) {
        super(HttpUrls.HTTP_REGISTEER_REQUEST,LoginResult.class,null,listener);
    }
    public void setRequestParams(String param){
        mParams.put("param",param);
    }
}
