package com.yunchengke.app.app;

import java.util.Iterator;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.yunchengke.app.http.HttpRequestQueue;

/**
 * 名称: AppManager <br/>
 * 描述: 应用程序管理类:管理程序Activity <br/>
 * 创建时间：2016/1/6 10:43
 *
 * @author 709835509@qq.com
 * @version 1.0
 */
public class AppManager {
	
	private static Stack<Activity> activityStack;

	private static AppManager instance;
	
	private AppManager(){}
	/**
	 * 单例
	 */
	public static AppManager getAppManager(){
		if(instance==null){
			instance=new AppManager();
		}
		return instance;
	}
	
	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	
	/**
	 * 获取栈顶的Activity
	 */
	public Activity currentActivity(){
		Activity activity=activityStack.lastElement();
		return activity;
	}
	
	/**
	 * 结束栈顶的Activity
	 */
	public void finishActivity(){
		Activity activity=activityStack.lastElement();
		finishActivity(activity);
	}
	
	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity){
		if(activity != null){
			activityStack.remove(activity);
			activity.finish();
			activity=null;
		}
	}
	
	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls){
		Iterator<Activity> itr = activityStack.iterator();   
		while (itr.hasNext()) {
			Activity activity = itr.next();
			if(activity.getClass().equals(cls) ){
				itr.remove();
				activity.finish();
			}
	    }
	}
	
	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity(){
		try {
			for (int i = 0, size = activityStack.size(); i < size; i++){
	            if (null != activityStack.get(i)){
	            	activityStack.get(i).finish();
	            }
		    }
			activityStack.clear();
		} catch (Exception e) {
			// handle exception
		}
	}
	
	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		// 取消所有HttpRequestQueue中的请求
		try {
			Glide.with(context).pauseRequestsRecursive();
			HttpRequestQueue.cancelAllRequest();
		} catch (Exception e) {		
			// e.printStackTrace();
		}
		
		try {
			finishAllActivity();
			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());											
			System.exit(0);
		} catch (Exception e) {	}
	}
}