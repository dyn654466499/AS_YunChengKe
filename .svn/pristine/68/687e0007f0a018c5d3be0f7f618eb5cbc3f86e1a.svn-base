package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.MyUserInfo;
import com.yunchengke.app.bean.city.ActiveinPageResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：MyUserInfoRequest <br/>
 * 描述：用户信息
 * 创建时间：2016/01/28 20:29
 *
 * @author hanter
 * @version 1.0
 */
public class ActiveinPageRequest extends GsonRequest<ActiveinPageResult> {
    public ActiveinPageRequest(HttpRequestListener<ActiveinPageResult> listener) {
        super(HttpUrls.HTTP_ACTIVEIN_PAGE, ActiveinPageResult.class, listener);
    }

    public void setRequestParams(int id) {

        mParams.put("Id", String.valueOf(id));

    }

}
