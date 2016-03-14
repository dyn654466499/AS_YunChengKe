package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.MediaMemberList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：MediaMemberListRequest <br/>
 * 描述：媒体成员列表
 * 创建时间：2016/01/17 0:31
 *
 * @author hanter
 * @version 1.0
 */
public class MediaMemberListRequest extends GsonRequest<MediaMemberList> {

    public MediaMemberListRequest(HttpRequestListener<MediaMemberList> listener) {
        super(HttpUrls.HTTP_MEDIA_MEMBER_LIST_REQUEST, MediaMemberList.class, listener);
    }

    public void setRequestParams(long mediaId, int page) {
        mParams.put("page", page + "");
        mParams.put("Id", mediaId + "");
    }
}
