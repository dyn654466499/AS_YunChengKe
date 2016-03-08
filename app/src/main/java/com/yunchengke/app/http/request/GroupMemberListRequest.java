package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.GroupMemberList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 名称: GroupMemberListRequest <br/>
 * 描述: 小组成员请求 <br/>
 * 创建时间：2016/1/14 14:10
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class GroupMemberListRequest extends GsonRequest<GroupMemberList> {

    public GroupMemberListRequest(HttpRequestListener<GroupMemberList> listener) {
        super(HttpUrls.HTTP_GROUP_MEMBER_LIST_REQUEST, GroupMemberList.class, listener);
    }

    public void setRequestParams(long groupId, int page) {
        mParams.put(HttpUrls.HTTP_URL_UID, HttpUrls.HTTP_URL_UID_VALUE);
        mParams.put("page", page + "");
        mParams.put("Id", groupId + "");
    }
}
