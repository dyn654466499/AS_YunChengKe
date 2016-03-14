package com.yunchengke.app.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.widget.TabHost;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.utils.PRNGFixes;

public class Application extends android.app.Application {
    public static TabHost tabHost;
    public static int tabHostId = 0;
    public static LoginInfo loginInfo = LoginInfo.getInstance();

    private final String TAG = Application.class.getSimpleName();
    private static Application instance;
    private static int screenWidth, screenHeight;
    public static final String S_MSG_TAG = "msgHandler";

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        PRNGFixes.apply();
        CookieSyncManager.createInstance(this);
        HttpRequestQueue.init(this);
        SharedPreferences pref = getSharedPreferences("login_info",MODE_PRIVATE);
        login_info(pref.getString("id", ""));


        instance = this;
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);

        setScreenWidth(wm.getDefaultDisplay().getWidth());// 屏幕宽度
        setScreenHeight(wm.getDefaultDisplay().getHeight());// 屏幕高度

        /**
         * 百度地图初始化
         */
        SDKInitializer.initialize(getApplicationContext());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                //.defaultDisplayImageOptions(options)
                //.memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                // .taskExecutor()
                // .taskExecutorForCachedImages()
                .threadPoolSize(5) // default
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                        //.diskCache(new UnlimitedDiscCache(cacheDir)) // default
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                        //.imageDownloader(new BaseImageDownloader(context)) // default
                        //.imageDecoder(new BaseImageDecoder()) // default
       /* .writeDebugLogs()*/
                .memoryCache(new WeakMemoryCache())
                .build();
        ImageLoader.getInstance().init(config);

    }
    public static void login_info(String id){
        loginInfo.setId(id);
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        Application.screenWidth = screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static void setScreenHeight(int screenHeight) {
        Application.screenHeight = screenHeight;
    }

}
