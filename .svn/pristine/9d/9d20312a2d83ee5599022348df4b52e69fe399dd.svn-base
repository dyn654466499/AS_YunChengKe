package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.GroupDetail;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.net.HttpCookie;
import java.util.ArrayList;

/**
 * 类名：GroupDetailRequest <br/>
 * 描述：小组详情请求
 * 创建时间：2016/01/10 10:45
 *
 * @author hanter
 * @version 1.0
 */
public class GroupDetailRequest extends GsonRequest<GroupDetail> {

    public GroupDetailRequest(HttpRequestListener<GroupDetail> listener) {
        super(HttpUrls.HTTP_GROUP_DETAIL_REQUEST, GroupDetail.class, listener);
    }

    /**
     * 设置参数
     * @param groupId 小组ID
     */
    public void setRequestParams(long groupId) {
        this.mParams.put("Id", groupId + "");
    }
}
