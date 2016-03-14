package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.AddTopicCommentResult;
import com.yunchengke.app.bean.AddTopicCommentResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：AddTopicCommentRequest <br/>
 * 描述：对话题添加评论请求
 * 创建时间：2016/01/13 23:49
 *
 * @author hanter
 * @version 1.0
 */
public class AddTopicCommentRequest extends GsonRequest<AddTopicCommentResult> {

    public AddTopicCommentRequest(HttpRequestListener<AddTopicCommentResult> listener) {
        super(HttpUrls.HTTP_ADD_TOPIC_COMMONT_REQUEST, AddTopicCommentResult.class, listener);
    }

    /**
     * 设置参数
     * @param id 话题ID
     * @param comment 评论
     */
    public void setRequestParams(long id, String comment) {
        JsonObject json = new JsonObject();
        json.addProperty("Id", id + "");
        json.addProperty("Field_PLNR", comment);

        AppLog.i(json.toString());

        this.mParams.put("param", json.toString());
    }
}
