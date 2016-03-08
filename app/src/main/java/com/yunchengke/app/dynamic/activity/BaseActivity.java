package com.yunchengke.app.dynamic.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;
import com.yunchengke.app.dynamic.view.TitleActionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity基类
 */
public class BaseActivity extends FragmentActivity {
	private Dialog progressDlg;
	private TitleActionBar titleActionBar;
	// private TarBar tabbar;
	Application myApp = null;
	protected String jsonData = "";

	protected List<Activity> activityList = new ArrayList<Activity>();

	protected String resultMsg = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activityList.add(this);
		myApp = (Application) getApplication();
	}

	/**
	 * 公共title使用 功能：设置title名称、添加返回按钮、添加右边栏按钮 1、在当前activity对应布局文件内添加<inclund
	 * layout="@layout/commont_title"> 2、在当前activity内调用此函数活动对象并调用相关方法
	 * 
	 * @return CurActionBar
	 */
	public TitleActionBar getTitleActionBar() {
		if (titleActionBar == null) {
			titleActionBar = new TitleActionBar(getWindow());
		}
		return titleActionBar;
	}

	// public TarBar getTarBar() {
	// if (tabbar == null)
	// {
	// tabbar = new TarBar(getWindow());
	// }
	// return tabbar;
	// }

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	/**
	 * 重写startActivityForResult方法
	 */
	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	/**
	 * 重写finish方法
	 */
	@Override
	public void finish() {
		super.finish();
		if (getWindow().getCurrentFocus() != null) {
			((InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(BaseActivity.this.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

	/**
	 * 结束所有activity
	 */
	protected void finishAll() {
		if (activityList != null) {
			for (int i = 0; i < activityList.size(); i++) {
				if (!activityList.get(i).isFinishing()) {
					activityList.get(i).finish();
				}
			}
		}
		activityList.clear();

	}

	/**
	 * 隐藏手机键盘
	 * 
	 * @param v
	 */
	public void hideIM(View v) {
		try {
			InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			IBinder windowToken = v.getWindowToken();
			if (windowToken != null) {
				im.hideSoftInputFromWindow(windowToken, 0);
			}
		} catch (Exception e) {
		}
	}

//	/**
//	 * 显示旋转进度条
//	 * 
//	 * @param titleresid
//	 * @param flagCancelable
//	 */
//	protected void showProgressDialog(int titleresid, boolean flagCancelable) {
//		if (progressDlg == null) {
//			progressDlg = new ProgressDialog(BaseActivity.this, titleresid);
//		}
//		if (!progressDlg.isShowing())
//			progressDlg.show();
//	}
//
//	/**
//	 * 隐藏旋转进度条
//	 */
//	protected void hideProgressDialog() {
//		if (progressDlg != null) {
//			progressDlg.dismiss();
//			progressDlg = null;
//		}
//	}
}
