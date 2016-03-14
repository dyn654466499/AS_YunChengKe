package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.ForgetEntity;
import com.yunchengke.app.bean.LoginResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/14 14:05
 * 描述：重置密码请求
 * 版本：1.0
 */
public class ForgetPasswordRequest extends GsonRequest<LoginResult>{

    public ForgetPasswordRequest(HttpRequestListener<LoginResult> listener) {
        super(HttpUrls.HTTP_RESETPASSWORD_REQUEST,LoginResult.class,null,listener);
    }
    public void setRequestParams(String param){
        mParams.put("param",param);
    }
}
