package com.yunchengke.app.dynamic.android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.TabHost;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {
	public Context context;
	public static TabHost tabHost;
	public static int tabHostId = 0;

	private static MyApplication mInstance;
	private List<Activity> activityList = new LinkedList<Activity>();

	public static MyApplication getInstance() {
		if (mInstance == null) {
			mInstance = new MyApplication();
		}
		return mInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(getApplicationContext());

	}


	/**
	 * 添加Activity到容器中
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 遍历所有Activity并finish
	 */
	public void exit() {
		// JPushInterface.stopPush(this);
		// stopService(new Intent(this, com.baidu.location.f.class));
		android.os.Process.killProcess(android.os.Process.myUid());
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

	// 配置ImageLoader 参数
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.diskCacheFileCount(150);
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config.build());
	}
}
