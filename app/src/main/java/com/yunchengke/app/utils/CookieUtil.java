package com.yunchengke.app.utils;

import android.webkit.CookieManager;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 类名：CookieUtil <br/>
 * 描述：获取相关域名的COOKIES
 * 创建时间：2016/01/10 20:21
 *
 * @author hanter
 * @version 1.0
 */
public class CookieUtil {

    /**
     * 获取URL对应的Domain的Cookie
     * @param url url
     * @return cookie
     */
    public static String getCookie(String url) {

        CookieManager cm = CookieManager.getInstance();

        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (httpUrl != null) {
            return cm.getCookie(httpUrl.getHost());
        } else {
            return "";
        }

    }
}
