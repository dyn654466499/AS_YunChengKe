package com.yunchengke.app.http.request;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yunchengke.app.utils.CookieUtil;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 名称: GsonRequest <br/>
 * 描述: 基本的Gson对象请求 <br/>
 * 创建时间：2016/1/6 10:06
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class GsonRequest<T> extends Request<T> {

    private static final String HTTP_HEADER_COOKIE = "Cookie";
    private static final String HTTP_HEADER_SET_COOKIE = "Set-Cookie";

    private final Gson mGson = new Gson();
    private final Class<T> mClazz; // 返回泛型的类
    protected Map<String, String> mParams;
    private final Map<String, String> mHeaders;
    private boolean saveCookie; // 是否保存Cookie
    private final HttpRequestListener<T> mListener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                       HttpRequestListener<T> listener) {
        super(Method.GET, url, listener);
        this.mClazz = clazz;
        this.mHeaders = headers;
        this.mListener = listener;
        this.mParams = new HashMap<String, String>();

        initParams();
    }

    public GsonRequest(String url, Class<T> clazz, HttpRequestListener<T> listener) {
        super(Method.GET, url, listener);
        this.mClazz = clazz;
        this.mHeaders = new HashMap<String, String>();
        this.mListener = listener;
        this.mParams = new HashMap<String, String>();

        initParams();
    }

    protected void initParams() {
        this.mParams.put("Field_YHID", LoginInfo.getInstance().getId());
        this.mParams.put("Yesicity", "1");
        this.mParams.put(HttpUrls.HTTP_URL_UID, HttpUrls.HTTP_URL_UID_VALUE);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return this.mParams;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> result = mHeaders != null ? mHeaders : new HashMap<String, String>();
        String cookies = CookieUtil.getCookie(getUrl());

        if (!TextUtils.isEmpty(cookies)) {
            AppLog.d("Cookie", cookies);
            result.put(HTTP_HEADER_COOKIE, cookies);
        }

        return result;
    }

    /** GET */
    @Override
    public String getUrl() {
        String url;

        if (mRedirectUrl != null) {
            url = mRedirectUrl;
        } else {
            if (getMethod() == Method.GET) {
                url = assembleGetParams();
            } else {
                url = super.getUrl();
            }
        }

        AppLog.e(url);

        return url;
    }

    @Override
    public String getCacheKey() {
        return getMethod() + ":" + getUrl();
    }

    protected String assembleGetParams() {
        StringBuilder stringBuilder = new StringBuilder(super.getUrl());
        Iterator<Map.Entry<String, String>> iterator = mParams.entrySet().iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if(i == 1) {
                stringBuilder.append("?");
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(entry.getValue());
            } else {
                stringBuilder.append("&");
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(entry.getValue());
            }
            i++;
        }
        return stringBuilder.toString();
    }

    /**
     * HTTP请求、完成、失败
     */
    protected void onFinishRequest() {
        if (mListener != null) {
            try {
                mListener.onFinishRequest();
            } catch (Exception e) {
                AppLog.e(e.getMessage());
            }
        }
    }

    @Override
    public void cancel() {
        super.cancel();

        // 取消请求
        if (mListener != null) {
            mListener.onCancel();

            onFinishRequest();
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        if(mListener != null){
            mListener.onErrorResponse(error);
        }

        if (error != null) {
            AppLog.e(error.getMessage());
        }

        onFinishRequest();
    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null) {
            onFinishRequest();

            try {
                mListener.onResponse(response);
            } catch (Exception e) {
                AppLog.e(e.getMessage());
            }
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            if (response.headers != null) {

                List<String> cookies = response.headers.get(HTTP_HEADER_SET_COOKIE);

                if (cookies != null) {

                    List<HttpCookie> httpCookies = new ArrayList<HttpCookie>();
                    for (String cookie : cookies) {
                        httpCookies.addAll(HttpCookie.parse(cookie));
                    }

                    String host = null;
                    try {
                        URL url = new URL(getUrl());
                        host = url.getHost();
                    } catch (MalformedURLException e) {
                        // e.printStackTrace();
                    }


                    // 保存Cookie、输出Cookie字符串
                    CookieManager cm = CookieManager.getInstance();

                    for (HttpCookie cookie : httpCookies) {
                        if (host != null) {
                            cm.setCookie(host, cookie.getName() + "=" + cookie.getValue());
                        }
                    }

                    CookieSyncManager.getInstance().sync();

                }

            }

            String escapeJson = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

//            String json = StringEscapeUtils.unescapeJava(escapeJson);

//            AppLog.i(escapeJson);
            AppLog.i("返回数据 escapeJson：" + escapeJson);
            //String json = StringEscapeUtils.unescapeJson(escapeJson);

            //AppLog.i("返回数据：" + json);

            return Response.success(
                    mGson.fromJson(escapeJson, mClazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    public boolean isSaveCookie() {
        return saveCookie;
    }

    public void setSaveCookie(boolean saveCookie) {
        this.saveCookie = saveCookie;
    }

}
