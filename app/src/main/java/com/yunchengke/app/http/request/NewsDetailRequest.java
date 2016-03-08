package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.NewsDetail;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：NewsDetailRequest <br/>
 * 描述：新闻详情
 * 创建时间：2016/01/16 18:44
 *
 * @author hanter
 * @version 1.0
 */
public class NewsDetailRequest extends GsonRequest<NewsDetail> {

    public NewsDetailRequest(HttpRequestListener<NewsDetail> listener) {
        super(HttpUrls.HTTP_NEWS_DETAIL_REQUEST, NewsDetail.class, listener);
    }

    public void setRequestParams(long id) {
        mParams.put("Id", id + "");
    }
}
