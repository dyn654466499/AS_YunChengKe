package com.yunchengke.app.dynamic.activity.homepage;

import android.app.Dialog;
import android.view.KeyEvent;
import android.widget.Toast;

import com.yunchengke.app.dynamic.activity.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;


public class KeyBackStopActivity extends BaseActivity{
	
	Dialog dialog;
	/**
	 * 菜单、返回键响应
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();		//调用双击退出函数
        }
		return false;
	}
	/**
	 * 双击退出函数
	 */
	private static Boolean isExit1 = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit1 == false) {
			isExit1 = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit1 = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

		} else {
			finish();
//			System.exit(0);
		}
	}
	

	
}
