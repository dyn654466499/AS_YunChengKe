package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.dynamic.DynamicDetailsCommentListEntity;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 名称: DynamicCommentListRequest <br/>
 * 描述: 动态评论 <br/>
 * 创建时间：2016/1/29 11:20
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class DynamicCommentListRequest extends GsonRequest<DynamicDetailsCommentListEntity> {
    public final static int DYNAMIC_DEFAULT_PAGE_SIZE = 3;

    public DynamicCommentListRequest(HttpRequestListener<DynamicDetailsCommentListEntity> listener) {
        super(HttpUrls.HTTP_DYNAMIC_COMMENT_LIST_REQUEST, DynamicDetailsCommentListEntity.class, listener);
    }

    public void setRequestParams(long dynamicId, int page) {
        mParams.put("Id", dynamicId + "");
        mParams.put("pagesize", DYNAMIC_DEFAULT_PAGE_SIZE + "");
        mParams.put("page", page + "");
    }
}
