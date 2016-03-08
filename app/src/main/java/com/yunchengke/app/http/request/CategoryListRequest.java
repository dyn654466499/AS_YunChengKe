package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.CategoryList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：CategoryListRequest <br/>
 * 描述：小组分类请求
 * 创建时间：2016/01/09 23:45
 *
 * @author hanter
 * @version 1.0
 */
public class CategoryListRequest extends GsonRequest<CategoryList> {
    /**
     * Make a GET request and return a parsed object from JSON.
     * @param listener 回调
     */
    public CategoryListRequest(HttpRequestListener<CategoryList> listener) {
        super(HttpUrls.HTTP_CATEGORY_LIST_REQUEST, CategoryList.class, null, listener);
    }

}
