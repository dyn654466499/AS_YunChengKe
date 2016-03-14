package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.city.YCKCommentList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 作者：Tinchy
 * 创建时间：2016/2/3 14:00
 * 描述：
 * 版本：1.0
 */
public class YCKCommentListRequest extends GsonRequest<YCKCommentList> {
    public YCKCommentListRequest(HttpRequestListener<YCKCommentList> listener) {
        super(HttpUrls.HTTP_CITY_ACTIVE_COMMENT_LIST, YCKCommentList.class, listener);
    }

    public void setRequestParams(int mediaId, int page) {
        mParams.put("Id", mediaId + "");
        mParams.put("page", page + "");
    }
}
