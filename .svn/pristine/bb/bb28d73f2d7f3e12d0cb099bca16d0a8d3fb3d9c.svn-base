package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.CommonRequestResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 名称: CancelFollowRequest <br/>
 * 描述: 取消关注或者移除粉丝 <br/>
 * 创建时间：2016/1/29 13:53
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class CancelFollowRequest extends GsonRequest<CommonRequestResult> {
    public CancelFollowRequest(HttpRequestListener<CommonRequestResult> listener) {
        super(HttpUrls.HTTP_CANCEL_FOLLOW_USER_REQUEST, CommonRequestResult.class, listener);
    }

    public void setCancelFollowRequest(long userId) {
        mParams.put("Type", "cancel");
        mParams.put("Id", userId + "");
        mParams.put("cancel", "0");
    }

    public void setRemoveFansRequest(long userId) {
        mParams.put("Type", "cancel");
        mParams.put("Id", userId + "");
        mParams.put("cancel", "1");
    }
}
