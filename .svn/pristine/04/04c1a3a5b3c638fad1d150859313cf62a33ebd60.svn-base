package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.TopicList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 名称: LoginRequest <br/>
 * 描述: 登陆请求 <br/>
 * 创建时间：2016/1/7 16:30
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class LoginRequest extends GsonRequest<TopicList> {

    /**
     * Make a GET request and return a parsed object from JSON.
     * @param listener 回调
     */
    public LoginRequest(HttpRequestListener<TopicList> listener) {
        super(HttpUrls.HTTP_LOGIN_REQUEST, TopicList.class, null, listener);
    }

    public void setRequestParams() {
        mParams.put("Uid", "yesicity2015");
        mParams.put("param", "{username:\"andy\",\"usepassword\":\"123456\"}");
    }

}
