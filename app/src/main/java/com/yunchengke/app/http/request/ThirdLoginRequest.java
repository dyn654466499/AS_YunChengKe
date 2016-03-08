package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.LoginResult;
import com.yunchengke.app.bean.ThirdLoginResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/21 14:18
 * 描述：
 * 版本：1.0
 */
public class ThirdLoginRequest extends GsonRequest<ThirdLoginResult> {
    public ThirdLoginRequest(HttpRequestListener<ThirdLoginResult> listener) {
        super(HttpUrls.HTTP_THIRDLOGIN_REQUEST, ThirdLoginResult.class, null, listener);
    }

    public void setRequestParams(String openId,String type) {
        mParams.put("openId", openId);
        mParams.put("type",type);
    }
}
