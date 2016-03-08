package com.yunchengke.app.dynamic.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.lang.Character.UnicodeBlock;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static String getVolumePath(Context context) {
		String path = null;
		String[] paths = null;
		StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
		try {
			Method mMethodGetPaths = storageManager.getClass().getMethod("getVolumePaths");

			paths = (String[]) mMethodGetPaths.invoke(storageManager);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> pathList = new ArrayList<String>();
		if (paths != null) {
			for (int i = 0; i < paths.length; i++) {
				StatFs stat = new StatFs(paths[i]);
				long blockSize = stat.getBlockSize();
				long blockCount = stat.getBlockCount();
				if (blockCount != 0 && blockSize != 0) {
					pathList.add(paths[i]);
				}
			}
		}
		if (pathList.size() == 0) {// 没有外部存储的时候放在内部存储
			pathList.add(Environment.getExternalStorageDirectory().toString()
			// .getAbsolutePath()
			);
			path = pathList.get(0);
		}
		// File sd = new
		// File(Environment.getExternalStorageDirectory().toString());
		if (pathList.size() > 1) {// 有外部存储的时候就放在外部存储中
			path = pathList.get(pathList.size() - 1);
		}
		if (path == null || TextUtils.isEmpty(path)) {
			pathList.add(Environment.getExternalStorageDirectory().toString()
			// .getAbsolutePath()
			);
			path = pathList.get(0);
		}
		boolean sdCardExit = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if (sdCardExit) {
			path = Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return path;
	}

	/**
	 * 判断程序是在后台运行,
	 */
	public static boolean isRunBackGround(Context mContext) {
		ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> mRunningTaskInfoList = mActivityManager.getRunningTasks(1);
		Log.i("当前运行的程序= ", mRunningTaskInfoList.get(0).baseActivity.getPackageName() + "当前Activity=" + mRunningTaskInfoList.get(0).baseActivity.getClassName());
		if (mRunningTaskInfoList != null && !mRunningTaskInfoList.isEmpty())
			if (mContext.getPackageName().equals(mRunningTaskInfoList.get(0).baseActivity.getPackageName())) {
				return false;
			}
		return true;
	}

	/**
	 * 获取当前显示activity的名称
	 * 
	 * @param context
	 * @return
	 */
	public static String currentActivity(Context context) {
		String cmpNameTemp = "";
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
		if (null != runningTaskInfos && runningTaskInfos.size() > 0)
			cmpNameTemp = runningTaskInfos.get(0).topActivity.getClassName();
		return cmpNameTemp;
	}

	/**
	 * 是否运行的该activity
	 * 
	 * @param context
	 * @param activityName
	 * @return
	 */
	public static boolean isRunActivity(Context context, String activityName) {
		if (TextUtils.isEmpty(activityName))
			return false;
		String cmpNameTemp = currentActivity(context);
		if (cmpNameTemp.equals(activityName))
			return true;
		return false;
	}

	/**
	 * 内部版本号
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static int getVersionCode(Context context) {
		// 获取packagemanager的实例
		int version = 0;
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			version = packInfo.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return version;
	}

	/**
	 * 外部版本号
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static String getVersionName(Context context) throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
		String version = packInfo.versionName;
		return version;
	}

	/**
	 * 判断关联AndroidManifest.xml中的debug设置;
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isApplicationDebug(Context context) {
		boolean isDebug = false;
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
			int flags = packageInfo.applicationInfo.flags;
			int debug = (flags & ApplicationInfo.FLAG_DEBUGGABLE);
			if (debug != 0) {
				// development mode
				isDebug = true;
			} else {
				// release mode
				isDebug = false;
			}
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isDebug;
	}


	public static void showShortToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static void showLongToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	// unicode转utf-8
	public static String unicodeToUtf8(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case ' ':
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							// throw new
							// IllegalArgumentException("Malformed   \\uxxxx   encoding.");
							break;
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	// utf-8转Unicode
	public static String utf8ToUnicode(String str) {

		char[] buf = str.toCharArray();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			UnicodeBlock block = UnicodeBlock.of(buf[i]);
			if (block == UnicodeBlock.BASIC_LATIN) {
				buffer.append(buf[i]);
			} else if (block == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				int j = buf[i] - 65248;
				buffer.append((char) j);
			} else {
				short s = (short) buf[i];
				String hex = Integer.toHexString(s);
				String unicode = "\\u" + hex;
				buffer.append(unicode.toLowerCase());
			}
		}
		return buffer.toString();
	}

	/**
	 * 在url后面添加分享 参数
	 * 
	 * @param webUrl
	 * @return
	 * @author lht
	 */
//	public static String addShareIdAtLastUrl(String webUrl) {
//		if (TextUtils.isEmpty(webUrl)) {
//			return webUrl;
//		}
//		String webUrlNew = "";
//		String and = "?";
//		int index = webUrl.indexOf("?");
//		if (index > 0) {
//			and = "&";
//		} else {
//			and = "?";
//		}
//		if ("".equals(Profile.getUserShare()) || null == Profile.getUserShare()) {
//			webUrlNew = webUrl;
//		} else {
//			webUrlNew = webUrl + and + "share=" + Profile.getUserShare();
//		}
//		return webUrlNew;
//	}

}
