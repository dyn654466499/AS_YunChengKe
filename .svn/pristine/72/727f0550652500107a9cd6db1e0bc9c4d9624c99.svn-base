package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.NewsCommentList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：NewsCommentListRequest <br/>
 * 描述：新闻评论列表
 * 创建时间：2016/01/16 21:18
 *
 * @author hanter
 * @version 1.0
 */
public class NewsCommentListRequest extends GsonRequest<NewsCommentList> {

    public NewsCommentListRequest(HttpRequestListener<NewsCommentList> listener) {
        super(HttpUrls.HTTP_NEWS_COMMENT_LIST_REQUEST, NewsCommentList.class, listener);
    }

    public void setRequestParams(long newsId, int page) {
        mParams.put("Id", newsId + "");
        mParams.put("page", page + "");
    }

}
