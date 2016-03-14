package com.yunchengke.app.app;

import android.text.TextUtils;
import android.util.Log;

import com.yunchengke.app.BuildConfig;

/**
 * 名称: AppLog <br/>
 * 描述: 应用程序Log管理 :用于控制程序Log输出 <br/>
 * 创建时间：2016/1/6 10:43
 *
 * @author 709835509@qq.com
 * @version 1.0
 */
public class AppLog {

	private static boolean DEBUG = BuildConfig.DEBUG;

	public static void i(String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.i(getFunctionName(), msg);
		}
	}

	public static void i(String tag, String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.i(tag, msg);
		}
	}

	public static void v(String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.v(getFunctionName(), msg);
		}
	}

	public static void v(String tag, String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.v(tag, msg);
		}
	}

	public static void d(String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.d(getFunctionName(), msg);
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.d(tag, msg);
		}
	}

	public static void e(String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.e(getFunctionName(), msg);
		}
	}

	public static void e(String tag, String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.e(tag, msg);
		}
	}

	public static void w(String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.w(getFunctionName(), msg);
		}
	}

	public static void w(String tag, String msg) {
		if (DEBUG && !TextUtils.isEmpty(msg)) {
			Log.w(tag, msg);
		}
	}

	protected static String getFunctionName() {
		StackTraceElement caller = new Throwable().fillInStackTrace()
				.getStackTrace()[2];
		return caller.getFileName() + "." + caller.getMethodName() + "(" + caller.getLineNumber() + ")";
	}


}
