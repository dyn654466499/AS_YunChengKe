package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.dynamic.DynamicListEntity;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：DynamicListRequest <br/>
 * 描述：动态列表
 * 创建时间：2016/01/29 0:45
 *
 * @author hanter
 * @version 1.0
 */
public class DynamicListRequest extends GsonRequest<DynamicListEntity> {

    public final int DYNAMIC_PER_PAGE_COUNT = 10;

    public DynamicListRequest(HttpRequestListener<DynamicListEntity> listener) {
        super(HttpUrls.HTTP_DYNAMIC_LIST_REQUEST, DynamicListEntity.class, listener);
    }

    public void setRequestParams(int page) {
        mParams.put("page", page + "");
        mParams.put("pagesize", DYNAMIC_PER_PAGE_COUNT+"");
//        mParams.put("Field_YHID", "1");
    }
}
