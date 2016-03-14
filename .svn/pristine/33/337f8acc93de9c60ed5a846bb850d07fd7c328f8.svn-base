package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.TopicCommentList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：TopicCommentListRequest <br/>
 * 描述：话题列表
 * 创建时间：2016/01/10 14:36
 *
 * @author hanter
 * @version 1.0
 */
public class TopicCommentListRequest extends GsonRequest<TopicCommentList> {
    public TopicCommentListRequest(HttpRequestListener<TopicCommentList> listener) {
        super(HttpUrls.HTTP_TOPIC_COMMENT_LIST_REQUEST, TopicCommentList.class, listener);
    }

    public void setRequestParams(long id, int page) {
        this.mParams.put("Id", id + "");
        this.mParams.put("page", page + "");
    }
}
