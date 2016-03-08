package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.MediaList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 名称: MyFollowedMediaListRequest <br/>
 * 描述: 我关注的媒体列表 <br/>
 * 创建时间：2016/1/20 11:03
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class MyFollowedMediaListRequest extends GsonRequest<MediaList> {
    public MyFollowedMediaListRequest(HttpRequestListener<MediaList> listener) {
        super(HttpUrls.HTTP_MY_FOLLOWED_MEDIA_LIST_REQUEST, MediaList.class, listener);
    }

    public void setRequestParams(int page) {
        mParams.put("page", page + "");
    }
}
