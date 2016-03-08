package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.InstantNewsList;
import com.yunchengke.app.bean.city.CityActiveList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * Created by Administrator on 2016/1/30.
 */
public class CityActiveListResquest extends GsonRequest<CityActiveList>  {

    public CityActiveListResquest(HttpRequestListener<CityActiveList> listener) {
        super(HttpUrls.HTTP_CITY_ACTIVE_INDEX_LIST, CityActiveList.class, listener);
    }

    public void setRequestParams(String type, String place,String time,int page,int isAll ,String Keyword) {
        mParams.put("type", type + "");
        mParams.put("place", place + "");
        mParams.put("time", time + "");
        mParams.put("page", page + "");
        mParams.put("isAll", isAll + "");
        mParams.put("Keyword", Keyword + "");

    }
}
