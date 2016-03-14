package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.dynamic.AddDynamicCommentResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 名称: AddDynamicCommentRequest <br/>
 * 描述: 添加动态评论 <br/>
 * 创建时间：2016/1/29 11:51
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class AddDynamicCommentRequest extends GsonRequest<AddDynamicCommentResult> {
    public AddDynamicCommentRequest(HttpRequestListener<AddDynamicCommentResult> listener) {
        super(HttpUrls.HTTP_ADD_DYNAMIC_COMMENT_REQUEST, AddDynamicCommentResult.class, listener);
    }

    public void setRequestParams(long dynamicId, String comment) {
        JsonObject json = new JsonObject();
        json.addProperty("Id", dynamicId);
        json.addProperty("Field_PLNR", comment);
        mParams.put("param", json.toString());
    }
}
