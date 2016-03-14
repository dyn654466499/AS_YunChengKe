package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.GroupList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：MyJoinedGroupListRequest <br/>
 * 描述：我加入的小组列表
 * 创建时间：2016/01/16 16:35
 *
 * @author hanter
 * @version 1.0
 */
public class MyJoinedGroupListRequest extends GsonRequest<GroupList> {

    public MyJoinedGroupListRequest(HttpRequestListener<GroupList> listener) {
        super(HttpUrls.HTTP_MY_JOINED_GROUP_REQUEST, GroupList.class, listener);
    }

    public void setRequestParams(int page) {
        mParams.put("page", page + "");
    }

}
