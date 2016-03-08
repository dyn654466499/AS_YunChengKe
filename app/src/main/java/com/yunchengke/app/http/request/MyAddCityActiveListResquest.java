package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.city.CityActiveList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * Created by Administrator on 2016/1/30.
 */
public class MyAddCityActiveListResquest extends GsonRequest<CityActiveList>  {

    public MyAddCityActiveListResquest(HttpRequestListener<CityActiveList> listener) {
        super(HttpUrls.HTTP_MYADDACTIVE_PAGE, CityActiveList.class, listener);
    }

    public void setRequestParams(int page) {

        mParams.put("page", page + "");


    }
}
