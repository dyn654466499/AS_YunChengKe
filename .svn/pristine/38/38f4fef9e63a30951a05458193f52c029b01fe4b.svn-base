package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.FollowList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 名称: FollowListRequest <br/>
 * 描述: 关注列表 <br/>
 * 创建时间：2016/1/28 16:34
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class FollowListRequest extends GsonRequest<FollowList> {
    public FollowListRequest(HttpRequestListener<FollowList> listener) {
        super(HttpUrls.HTTP_FOLLOW_LIST_REQUEST, FollowList.class, listener);
    }

    public void setRequestParams(long userId, long page) {
        mParams.put("Field_YHID", userId + "");
        mParams.put("page", page + "");
    }
}
