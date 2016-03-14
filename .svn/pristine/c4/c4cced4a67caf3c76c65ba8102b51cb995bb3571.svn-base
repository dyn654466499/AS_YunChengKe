package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.TopicList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 名称: TopicListRequest <br/>
 * 描述: 话题列表 <br/>
 * 创建时间：2016/1/7 13:55
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class TopicListRequest extends GsonRequest<TopicList> {

    public TopicListRequest(HttpRequestListener<TopicList> listener) {
        super(HttpUrls.HTTP_TOPIC_LIST_REQUEST, TopicList.class, null, listener);
    }

    public void setRequestParams(int page) {
        mParams.put("page", page + "");
    }

    public void setRequestParams(long groupId, int page) {
        mParams.put("groupid", groupId + "");
        mParams.put("page", page + "");
    }

    public void setSearchParams(String keyword, int page) {
        mParams.put("keyword", keyword);
        mParams.put("page", page + "");
    }

}
