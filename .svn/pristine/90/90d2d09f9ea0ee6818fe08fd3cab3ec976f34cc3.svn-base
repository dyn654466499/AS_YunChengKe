package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.GroupList;
import com.yunchengke.app.bean.NewsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：NewsListRequest <br/>
 * 描述：新闻列表请求
 * 创建时间：2016/01/12 22:07
 *
 * @author hanter
 * @version 1.0
 */
public class NewsListRequest extends GsonRequest<NewsList> {

    public NewsListRequest(HttpRequestListener<NewsList> listener) {
        super(HttpUrls.HTTP_NEWS_LIST_REQUEST, NewsList.class, listener);
    }

    /**
     * 详情
     * @param newsId 新闻ID
     */
    public void setDetailRequestParams(long newsId) {
        mParams.put("Id", newsId + "");
    }


    /**
     * 搜索
     * @param page 分页
     * @param keyword 搜索关键字
     */
    public void setSearchParams(int page, String keyword) {
        mParams.put("page", page + "");
        mParams.put("keyword", keyword + "");
    }

    /**
     * 列表
     * @param page 分页
     */
    public void setRequestParams(int page) {
        mParams.put("page", page + "");
    }
}
