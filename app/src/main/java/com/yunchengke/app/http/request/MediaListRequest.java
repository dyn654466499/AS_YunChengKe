package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.MediaList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 名称: MediaListRequest <br/>
 * 描述: 媒体列表请求 <br/>
 * 创建时间：2016/1/13 11:30
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class MediaListRequest extends GsonRequest<MediaList> {

    public MediaListRequest(HttpRequestListener<MediaList> listener) {
        super(HttpUrls.HTTP_MEDIA_LIST_REQUEST, MediaList.class, listener);
    }

    public void setDetailParams(long mediaId) {
        mParams.put("Id", mediaId + "");
    }

    public void setRequestParams(int page) {
        mParams.put("page", page + "");
    }

    public void setSearchParams(int page, String keyword) {
        mParams.put("page", page + "");
        mParams.put("keyword", keyword);
    }
}
