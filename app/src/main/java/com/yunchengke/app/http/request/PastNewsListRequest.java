package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.PastNewsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 名称: PastNewsListRequest <br/>
 * 描述: 往期新闻列表请求 <br/>
 * 创建时间：2016/1/20 9:42
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class PastNewsListRequest extends GsonRequest<PastNewsList> {
    public PastNewsListRequest(HttpRequestListener<PastNewsList> listener) {
        super(HttpUrls.HTTP_PAST_NEWS_LIST_REQUEST, PastNewsList.class, listener);
    }

    public void setRequestParams(long mediaId, int page) {
        mParams.put("Id", mediaId + "");
        mParams.put("page", page + "");
    }
}
