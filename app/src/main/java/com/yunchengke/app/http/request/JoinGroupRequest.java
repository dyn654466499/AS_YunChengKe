package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.GroupList;
import com.yunchengke.app.bean.JoinGroupResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：JoinGroupRequest <br/>
 * 描述：加入小组请求
 * 创建时间：2016/01/17 20:34
 *
 * @author hanter
 * @version 1.0
 */
public class JoinGroupRequest extends GsonRequest<JoinGroupResult> {

    public JoinGroupRequest(HttpRequestListener<JoinGroupResult> listener) {
        super(HttpUrls.HTTP_JOIN_GROUP_REQUEST, JoinGroupResult.class, listener);
    }

    public void setRequestParams(long groupId) {
        mParams.put("Id", groupId + "");
    }
}
