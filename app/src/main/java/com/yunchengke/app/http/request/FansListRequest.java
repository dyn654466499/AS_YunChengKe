package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.FunsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.Map;

/**
 * 名称: FansListRequest <br/>
 * 描述: 粉丝列表 <br/>
 * 创建时间：2016/1/28 16:09
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class FansListRequest extends GsonRequest<FunsList> {

    public FansListRequest(HttpRequestListener<FunsList> listener) {
        super(HttpUrls.HTTP_FUNS_LIST_REQUEST, FunsList.class, listener);
    }

    public void setRequestParams(long userId, int page) {
        mParams.put("page", page + "");
        mParams.put("Field_YHID", userId + "");
    }
}
