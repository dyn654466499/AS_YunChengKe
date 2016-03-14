package com.yunchengke.app.ui.activity.daemon;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.interfaces.ViewChangeListener;
import com.yunchengke.app.model.BaseModel;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.activity.CommonWebView;
import com.yunchengke.app.ui.activity.login.LoginActivity;
import com.yunchengke.app.ui.activity.maintab.MainTabAct;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 该Activity作为controler的抽象类，负责向model转发view的业务逻辑计算请求，并通知view改变其状态。
 * @author  邓耀宁
 *
 */
public abstract class BaseActivity extends Activity implements OnClickListener,ViewChangeListener {
    
    /**
     * model的代理对象，需要相应的子类设置它（多态）
     */
    private BaseModel modelDelegate;
    /**
     * 与UI线程通信的handler
     */
    protected Handler handler;
    /**
     * view状态监听者
     */
    protected ViewChangeListener viewChangeListener;
    /**
     * 使用线程池
     */
    private ThreadPoolExecutor executor;
    
    protected boolean isActive = false;
	protected boolean isDestroyed = false;
    
    private Toast mToast;

    /**
     * 设置Model代理
     * @param modelDelegate
     */
	public void setModelDelegate(BaseModel modelDelegate) {
		this.modelDelegate = modelDelegate;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (executor == null)
			executor = new ThreadPoolExecutor(3, 6, 3000, TimeUnit.SECONDS,
					new LinkedBlockingQueue<Runnable>(3),
					new ThreadPoolExecutor.DiscardOldestPolicy());

		handler = new Handler(getMainLooper()) {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				viewChangeListener.onViewChange(msg);
			}

		};
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
	}


	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		initSlidingMenu();
	}

	@Override
	public void setContentView(View view, ViewGroup.LayoutParams params) {
		super.setContentView(view, params);
		initSlidingMenu();
	}


	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initSlidingMenu();
	}

	/**
	     * 提示框
	     * @param str
	     */
		public void showTip(final String str) {
			if (!TextUtils.isEmpty(str)) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mToast.setText(str);
						mToast.show();
					}
				});
			}
		}

		/**
		 * 取消提示框
		 */
		public void cancelShowTip() {
			if (mToast!=null) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mToast.cancel();
					}
				});
			}
		}
		
	/**
	 * 设置view状态监听者
	 * @param viewChangeListener
	 */
    public void setViewChangeListener(ViewChangeListener viewChangeListener) {
		this.viewChangeListener = viewChangeListener;
	}

    /**
     * 需设置model代理后，才能给给model转发请求，通过封装Message传递参数
     * @param changeStateMessage
     */
	public void notifyModelChange(final Message changeStateMessage) {
		// TODO Auto-generated method stub
		/**
		 * 交付给子线程做业务逻辑计算
		 */
		executor.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				modelDelegate.changeModelState(changeStateMessage);
			}
		});
	}


	/**
	 * 需设置model代理后，才能给model转发请求
	 * @param changeState
	 */
	public void notifyModelChange(final int changeState) {
		// TODO Auto-generated method stub
		/**
		 * 交付给子线程做业务逻辑计算
		 */
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				modelDelegate.changeModelState(changeState);
			}
		});
	}
	
	/**
	 * 获取当前类名
	 * @return 类名
	 */
    protected String getTAG(){
 	   return this.getClass().getName();
    }
    
    @Override
    protected void onStop() {
            // TODO Auto-generated method stub
            super.onStop();
            if (!isAppOnForeground()) {
                    //app 进入后台
                    isActive = false ;//记录当前已经进入后台
            }
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (!isActive) {
			// app 从后台唤醒，进入前台
			isActive = true;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isDestroyed = true;
	}

	/**
     * 程序是否在前台运行
     * 
     * @return
     */
    public boolean isAppOnForeground() {
            // Returns a list of application processes that are running on the
            // device
            ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            String packageName = getApplicationContext().getPackageName();

            List<RunningAppProcessInfo> appProcesses = activityManager
                            .getRunningAppProcesses();
            if (appProcesses == null)
                    return false;

            for (RunningAppProcessInfo appProcess : appProcesses) {
                    // The name of the process that this object is associated with.
                    if (appProcess.processName.equals(packageName)
                                    && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                            return true;
                    }
            }
            return false;
    }
    
    abstract class ButtonListener implements OnClickListener{};


	protected boolean mShowSlidingMenu = true;
	// 侧滑
	SlidingMenu menu;
	LinearLayout wodexiaofei;
	LinearLayout liwu;
	LinearLayout jifeng;
	LinearLayout settings;
	LinearLayout chazhaohaoyou;
	LinearLayout sh;
	LinearLayout faxian;
	ImageView image;
	TextView sheng, shi, qu,fengsi2, guanzhu2;

	protected void initSlidingMenu() {

		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);

		// 设置滑动菜单视图的宽度
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		menu.setFadeDegree(0.35f);

		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT, true);
		View view = getLayoutInflater().inflate(R.layout.layout_menu, menu, false);
		menu.setMenu(view);

		final SharedPreferences sp = getSharedPreferences("login_info", MODE_PRIVATE);

		faxian = (LinearLayout) view.findViewById(R.id.faxian);
		wodexiaofei = (LinearLayout) view.findViewById(R.id.wodexiaofei);
		liwu = (LinearLayout) view.findViewById(R.id.liwu);
		jifeng = (LinearLayout) view.findViewById(R.id.jifeng);
		settings = (LinearLayout) view.findViewById(R.id.settings);
		sh = (LinearLayout) view.findViewById(R.id.sh);
		image = (ImageView) view.findViewById(R.id.image);
		sheng = (TextView) view.findViewById(R.id.shengfeng);
		sheng.setText(sp.getString("local","未填写"));
		shi = (TextView) view.findViewById(R.id.shi);
		shi.setVisibility(View.GONE);
		qu = (TextView) view.findViewById(R.id.qu);
		qu.setVisibility(View.GONE);
		fengsi2 = (TextView) view.findViewById(R.id.fengsi2);
		fengsi2.setText(String.valueOf(sp.getInt("follow_me", 0)));
		guanzhu2 = (TextView) view.findViewById(R.id.guanzhu2);
		guanzhu2.setText(String.valueOf(sp.getInt("followed", 0)));
		String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST +sp.getString("id","");
		Glide.with(this).load(url).into(image);
		chazhaohaoyou = (LinearLayout) view.findViewById(R.id.chaozhaohaoyou);

		MenuListener menuListener = new MenuListener();

		faxian.setOnClickListener(menuListener);
		wodexiaofei.setOnClickListener(menuListener);
		liwu.setOnClickListener(menuListener);
		jifeng.setOnClickListener(menuListener);
		settings.setOnClickListener(menuListener);
		sh.setOnClickListener(menuListener);
		image.setOnClickListener(menuListener);

		image.setOnClickListener(menuListener);
		image.setOnClickListener(menuListener);
		image.setOnClickListener(menuListener);
		view.findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = sp.edit();
				editor.clear();
				editor.remove("id");
				editor.commit();
				Intent intent = new Intent();
				intent.setClass(BaseActivity.this, LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
			}
		});

	}

	public class MenuListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			menuListener(v);
		}
	}

	public void menuListener(View v) {
		String url = "";
		Intent intent;
		switch (v.getId()) {
			case R.id.faxian:
				menu.toggle();
				startActivity(new Intent(this, MainTabAct.class));
				url += "http://www.icityto.com";
				return;

			case R.id.wodexiaofei:
				//url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=273";
				intent = new Intent(this, MyConsumeActivity.class);
				startActivity(intent);
				return;

			case R.id.image:
				UIHelper.gotoEditPersonInfoActivity(this);
				return;

			case R.id.liwu:
				url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=267";
				break;
			case R.id.jifeng:
				url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=266";
				break;
			case R.id.settings:
				url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=268";
				break;
			case R.id.sh:
				url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=461";
				break;
			case R.id.guanzhu:
				url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=271&GZID=" + Application.loginInfo.getId();
				break;
			case R.id.fengsi:
				url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=270&GZID=" + Application.loginInfo.getId();
				break;
			case R.id.chaozhaohaoyou:
				url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=293";
				break;
		}
		Intent i = new Intent(this, CommonWebView.class);
		i.putExtra(getString(R.string.url), url);
		startActivity(i);
	}
}
