package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.CommAddResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/31 18:39
 * 描述：
 * 版本：1.0
 */
public class CommAddRequest extends GsonRequest<CommAddResult> {
    public CommAddRequest(HttpRequestListener<CommAddResult> listener) {
        super(HttpUrls.HTTP_DYNAMIC_COMMENT_ADD_REQUEST, CommAddResult.class, null, listener);
    }
    public void setRequestParams(String params){
        mParams.put("param",params);
    }
}
