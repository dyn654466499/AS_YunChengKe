package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.InstantNewsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 名称: InstantNewsListRequest <br/>
 * 描述: 及时新闻 <br/>
 * 创建时间：2016/1/20 9:37
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class InstantNewsListRequest extends GsonRequest<InstantNewsList> {

    public InstantNewsListRequest(HttpRequestListener<InstantNewsList> listener) {
        super(HttpUrls.HTTP_INSTANT_NEWS_LIST_REQUEST, InstantNewsList.class, listener);
    }

    public void setRequestParams(int type, long mediaId, int page) {
        mParams.put("type", type + "");
        mParams.put("Id", mediaId + "");
        mParams.put("page", page + "");
    }
}
