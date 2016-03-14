package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.BaseEntity;
import com.yunchengke.app.bean.city.ActiveinPageResult;
import com.yunchengke.app.bean.city.YCKJoinedList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 作者：Tinchy
 * 创建时间：2016/2/3 16:48
 * 描述：
 * 版本：1.0
 */
public class YCKJoinedListRequest extends GsonRequest<YCKJoinedList> {
    public YCKJoinedListRequest(HttpRequestListener<YCKJoinedList> listener) {
        super(HttpUrls.HTTP_ACTIVE_JOINED_LIST, YCKJoinedList.class, listener);
    }
    public void setRequestParams(int id) {
        mParams.put("Id", String.valueOf(id));
    }
}
