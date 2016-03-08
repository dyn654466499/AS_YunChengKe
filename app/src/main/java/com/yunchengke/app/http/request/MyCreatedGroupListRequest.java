package com.yunchengke.app.http.request;

import com.yunchengke.app.bean.GroupList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：MyCreatedGroupListRequest <br/>
 * 描述：我创建的小组列表请求
 * 创建时间：2016/01/09 19:54
 *
 * @author hanter
 * @version 1.0
 */
public class MyCreatedGroupListRequest extends GsonRequest<GroupList> {

    /**
     * Make a GET request and return a parsed object from JSON.
     * @param listener 回调
     */
    public MyCreatedGroupListRequest(HttpRequestListener<GroupList> listener) {
        super(HttpUrls.HTTP_MY_CREATED_GROUP_REQUEST, GroupList.class, listener);
    }

    public void setRequestParams(int page) {
        mParams.put(HttpUrls.HTTP_URL_UID, HttpUrls.HTTP_URL_UID_VALUE);
        mParams.put("page", page + "");

        /*
        try {
            CookieManager cm = CookieManager.getInstance();
            String saveCookie = cm.getCookie("www.icityto.com");
            Log.i("保存的cookie", saveCookie);
        } catch (Exception e) {

        }


        ArrayList<HttpCookie> cookies = new ArrayList<HttpCookie>();
        cookies.add(new HttpCookie("X6AdminName", "18815291912"));
        cookies.add(new HttpCookie("X6AdminId", "272"));
        cookies.add(new HttpCookie("X6AdminPass", "f9248837f86d1a6b"));
        setCookie(cookies);
        */

//        HttpCookie cookie = new HttpCookie("X6AdminName", "18815291912");
//
//        ArrayList<String> cookies = new ArrayList<String>();
//        cookies.add("X6AdminName=18815291912");
//        cookies.add("X6AdminId=272");
//        cookies.add("X6AdminPass=f9248837f86d1a6b");
//        setCookie(cookies);
    }

}
