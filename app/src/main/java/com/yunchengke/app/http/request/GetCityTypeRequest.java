package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.GroupMemberList;
import com.yunchengke.app.bean.city.CityType;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * Created by Administrator on 2016/1/30.
 */
public class GetCityTypeRequest extends GsonRequest<CityType> {

    public GetCityTypeRequest(HttpRequestListener<CityType> listener) {
        super(HttpUrls.HTTP_ACTIVE_TYPE_LIST, CityType.class, listener);
    }

}