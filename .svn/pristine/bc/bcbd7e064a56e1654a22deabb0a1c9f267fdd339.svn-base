package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.AddNewsCommentResult;
import com.yunchengke.app.bean.AddTopicCommentResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：AddNewsCommentRequest <br/>
 * 描述：添加对新闻的评论
 * 创建时间：2016/01/17 0:05
 *
 * @author hanter
 * @version 1.0
 */
public class AddNewsCommentRequest extends GsonRequest<AddNewsCommentResult> {

    public AddNewsCommentRequest(HttpRequestListener<AddNewsCommentResult> listener) {
        super(HttpUrls.HTTP_ADD_NEWS_COMMENT_REQUEST, AddNewsCommentResult.class, listener);
    }

    /**
     * 设置参数
     * @param newsId 新闻ID
     * @param comment 评论
     */
    public void setRequestParams(long newsId, String comment) {
        JsonObject json = new JsonObject();
        json.addProperty("Id", newsId + "");
        json.addProperty("Field_PLNR", comment);

        AppLog.i(json.toString());

        this.mParams.put("param", json.toString());
    }
}
