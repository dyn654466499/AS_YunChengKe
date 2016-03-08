package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.GroupList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：GroupListRequest <br/>
 * 描述：小组列表接口
 * 创建时间：2016/01/09 0:50
 *
 * @author hanter
 * @version 1.0
 */
public class GroupListRequest extends GsonRequest<GroupList> {

    public GroupListRequest(HttpRequestListener<GroupList> listener) {
        super(HttpUrls.HTTP_GROUP_LIST_REQUEST, GroupList.class, null, listener);
    }

    public void setRequestParams(long classId, int page) {
        mParams.put("page", page + "");
        mParams.put("classId", classId + "");
    }

    public void setSearchParams(long classId, int page, String keyword) {
        mParams.put("page", page + "");
        mParams.put("classId", classId + "");
        mParams.put("keyword", keyword);
    }

}
