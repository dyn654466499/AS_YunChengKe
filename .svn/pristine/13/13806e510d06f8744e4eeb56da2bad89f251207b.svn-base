package com.yunchengke.app.http;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.Build;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;

/**
 * HTTP请求队列单例
 */
public class HttpRequestQueue {

    private static Context sContext;
    private static HttpRequestQueue sInstance;

    private RequestQueue mRequestQueue;

    private HttpRequestQueue() {
        mRequestQueue = getRequestQueue();
    }

    /**
     * 初始化单例
     * @param context context
     */
    public static void init(Context context) {

//        if (context == null) {
//            throw (new Exception("context is null!"));
//        }

        sContext = context.getApplicationContext();

        if (sInstance == null) {
            sInstance = new HttpRequestQueue();
        }
    }

    public static synchronized HttpRequestQueue getInstance() {
        if (sInstance == null) {
//             sInstance = new HttpRequestQueue(context);
        }
        return sInstance;
    }

    public synchronized RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            Cache cache = new DiskBasedCache(sContext.getCacheDir(), 8 * 1024 * 1024); // 8MB cap

            // 判断API，设置最佳的网络
            HttpStack stack;
            // If the device is running a version >= Gingerbread...
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                // ...use HttpURLConnection for stack.
                stack = new HurlStack();
            } else {
                // ...use AndroidHttpClient for stack.
                stack = new HttpClientStack(AndroidHttpClient.newInstance(""));
            }
            Network network = new BasicNetwork(stack);

//            mRequestQueue = Volley.newRequestQueue(sContext.getApplicationContext(), );
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }

    /**
     * 添加请求到队列
     * @param req Request
     */
    public static <T> void addToRequestQueue(Request<T> req) {
        getInstance().getRequestQueue().add(req);
    }

    /**
     * 添加请求到队列
     * @param req Request
     * @param tag Tag
     */
    public static <T> void addToRequestQueue(Request<T> req, Object tag) {
        req.setTag(tag);
        getInstance().getRequestQueue().add(req);
    }

    /**
     * 取消所有请求
     */
    public static void cancelAllRequest() {
        cancelAll(new CancelAllRequestFilter());
    }

    /**
     * 获取缓存Cache
     * @return Cache
     */
    public static Cache getCache() {
        return getInstance().getRequestQueue().getCache();
    }

    /**
     * 获取指定Key的缓存
     * @param key key
     * @return key对应的缓存
     */
    public static Cache.Entry getCacheEntry(String key) {
        return getCache().get(key);
    }

    /**
     * 获取指定请求的缓存
     * @param request
     * @return request对应的缓存
     */
    public static <T> Cache.Entry getCacheEntry(Request<T> request) {
        return getCache().get(request.getCacheKey());
    }

    /**
     * 取消TAG标记的请求
     * @param tag 请求标记
     */
    public static void cancelAll(final Object tag) {
        HttpRequestQueue.getInstance().getRequestQueue().cancelAll(tag);
    }

    /**
     * 取消符合过滤规则的请求
     * @param filter 过滤器
     */
    public static void cancelAll(RequestQueue.RequestFilter filter) {
        HttpRequestQueue.getInstance().getRequestQueue().cancelAll(filter);
    }
}
