package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.CodeResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/14 13:01
 * 描述：
 * 版本：
 */
public class CodeRequest extends GsonRequest<CodeResult>{
    public CodeRequest(HttpRequestListener<CodeResult> listener) {
        super(HttpUrls.HTTP_CODE_REQUEST,CodeResult.class,null,listener);
    }
    public void setRequestParams(String param){
        mParams.put("param",param);
    }
}
