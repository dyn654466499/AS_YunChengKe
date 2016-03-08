package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.TopicDetail;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：TopicDetailRequest <br/>
 * 描述：话题详情
 * 创建时间：2016/01/10 11:42
 *
 * @author hanter
 * @version 1.0
 */
public class TopicDetailRequest extends GsonRequest<TopicDetail> {
    public TopicDetailRequest(HttpRequestListener<TopicDetail> listener) {
        super(HttpUrls.HTTP_TOPIC_DETAIL_REQUEST, TopicDetail.class, listener);
    }

    public void setRequestParams(long topicId) {
        this.mParams.put("Id", topicId + "");
    }
}
