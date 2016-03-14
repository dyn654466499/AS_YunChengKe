package com.yunchengke.app.ui.activity.maintab;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.ui.activity.city.SameCityAcitvity;
import com.yunchengke.app.ui.activity.daemon.CateringSearchActivity;
import com.yunchengke.app.ui.activity.daemon.MyConsumeActivity;
import com.yunchengke.app.ui.activity.dynamic.DynamicActivity;
import com.yunchengke.app.ui.activity.CommonWebView;
import com.yunchengke.app.ui.activity.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 主界面的tab
 *
 * @author suligen
 */
@SuppressWarnings("deprecation")
public class MainTabAct extends TabActivity {

	// private TabHost tabHost;

	private Intent mHomepageIntent, mProductListIntent, mMyIntent, mMoreIntent,mMessageIntent;
	private LinearLayout mBut0, mBut1, mBut2,linearLayout;
	private RelativeLayout mBut3,mBut4;
	private ImageView mIv0, mIv1, mIv2, mIv3,mIv4;
	private TextView mTv0, mTv1, mTv2, mTv3,mTv4;
	// private Typeface iconfont = null;

	// glw
	// private ImageView mIv0,mIv1,mIv2;

	// private ImageView mIv0, mIv1, mIv1, mIv3;

	/** 双击返回键退出程序 */
	private long exitTime = 0;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_tab);

		prepareIntent();
		setupIntent();
		prepareView();
		// AppTool.getUserStatusInfoRequest();

		initSlide();
	}

	private void initSlide() {
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

		view.findViewById(R.id.btn_exit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = sp.edit();
				editor.clear();
				editor.remove("id");
				editor.commit();
				Intent intent = new Intent();
				intent.setClass(MainTabAct.this, LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void prepareIntent() {
		mHomepageIntent = new Intent(this, HomePageActivity.class);
		//动态
		mProductListIntent = new Intent(this, DynamicActivity.class);
		mProductListIntent.putExtra(getString(R.string.url), "http://www.icityto.com/?Type=3&Module=334&UId=yesicity2015");
		//同城
		mMyIntent = new Intent(this, SameCityAcitvity.class);
		mMyIntent.putExtra(getString(R.string.url),"http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=218&UId=yesicity2015");
		//餐饮

		mMoreIntent = new Intent(this, CateringSearchActivity.class);
		mMoreIntent.putExtra(getString(R.string.url),"http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=336&UId=yesicity2015");
		mMoreIntent.putExtra("isWaimai",false);
		mMoreIntent.putExtra("hasBackBtn",false)
;		//消息
		mMessageIntent = new Intent(this, CommonWebView.class);
		mMessageIntent.putExtra(getString(R.string.url),"http://www.icityto.com/?Type=3&Module=490&UId=yesicity2015");

	}

	private void setupIntent() {
		Application.tabHost = getTabHost();
		Application.tabHost.setup();
		TabSpec recommend_tab = Application.tabHost.newTabSpec("发现").setIndicator("发现").setContent(mHomepageIntent);
		TabSpec list_tab = Application.tabHost.newTabSpec("动态").setIndicator("动态").setContent(mProductListIntent);
		TabSpec product_tab = Application.tabHost.newTabSpec("同城").setIndicator("同城").setContent(mMyIntent);
		TabSpec my_tab = Application.tabHost.newTabSpec("餐饮").setIndicator("餐饮").setContent(mMoreIntent);
		TabSpec my_message = Application.tabHost.newTabSpec("消息").setIndicator("消息").setContent(mMessageIntent);
		Application.tabHost.addTab(recommend_tab);
		Application.tabHost.addTab(list_tab);
		Application.tabHost.addTab(product_tab);
		Application.tabHost.addTab(my_tab);
		Application.tabHost.addTab(my_message);

		Application.tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String arg0) {
                    switch (Application.tabHost.getCurrentTab()) {
						case 0:
							// 发现
							tianchongtext(mIv0, R.drawable.ic_tab_faxian_selected);
							tianchongColors(mTv0, R.color.tab_default);

							// 动态
							tianchongtext(mIv1, R.drawable.ic_tab_dongtai);
							tianchongColors(mTv1, R.color.tab_default);

							// 同城
							tianchongtext(mIv2, R.drawable.ic_tab_tongcheng);
							tianchongColors(mTv2, R.color.tab_default);

							// 餐饮
							tianchongtext(mIv3, R.drawable.ic_tab_canyin);
							tianchongColors(mTv3, R.color.tab_default);
							// 消息
							tianchongtext(mIv4, R.drawable.ic_tab_xiaoxi);
							tianchongColors(mTv4, R.color.tab_default);
							break;
						case 1:
							// 发现
							tianchongtext(mIv0, R.drawable.ic_tab_faxian);
							tianchongColors(mTv0, R.color.tab_default);

							// 动态
							tianchongtext(mIv1, R.drawable.ic_tab_dongtai_selected);
							tianchongColors(mTv1, R.color.tab_default);

							// 同城
							tianchongtext(mIv2, R.drawable.ic_tab_tongcheng);
							tianchongColors(mTv2, R.color.tab_default);

							// 餐饮
							tianchongtext(mIv3, R.drawable.ic_tab_canyin);
							tianchongColors(mTv3, R.color.tab_default);
							// 消息
							tianchongtext(mIv4, R.drawable.ic_tab_xiaoxi);
							tianchongColors(mTv4, R.color.tab_default);
							break;
						case 2:
							// 发现
							tianchongtext(mIv0, R.drawable.ic_tab_faxian);
							tianchongColors(mTv0, R.color.tab_default);

							// 动态
							tianchongtext(mIv1, R.drawable.ic_tab_dongtai);
							tianchongColors(mTv1, R.color.tab_default);

							// 同城
							tianchongtext(mIv2, R.drawable.ic_tab_tongcheng_selected);
							tianchongColors(mTv2, R.color.tab_default);

							// 餐饮
							tianchongtext(mIv3, R.drawable.ic_tab_canyin);
							tianchongColors(mTv3, R.color.tab_default);
							// 消息
							tianchongtext(mIv4, R.drawable.ic_tab_xiaoxi);
							tianchongColors(mTv4, R.color.tab_default);
							break;
						case 3:
							// 发现
							tianchongtext(mIv0, R.drawable.ic_tab_faxian);
							tianchongColors(mTv0, R.color.tab_default);

							// 动态
							tianchongtext(mIv1, R.drawable.ic_tab_dongtai);
							tianchongColors(mTv1, R.color.tab_default);

							// 同城
							tianchongtext(mIv2, R.drawable.ic_tab_tongcheng);
							tianchongColors(mTv2, R.color.tab_default);

							// 餐饮
							tianchongtext(mIv3, R.drawable.ic_tab_canyin_selected);
							tianchongColors(mTv3, R.color.tab_default);
							// 消息
							tianchongtext(mIv4, R.drawable.ic_tab_xiaoxi);
							tianchongColors(mTv4, R.color.tab_default);
							break;
						case 4:
							// 发现
							tianchongtext(mIv0, R.drawable.ic_tab_faxian);
							tianchongColors(mTv0, R.color.tab_default);

							// 动态
							tianchongtext(mIv1, R.drawable.ic_tab_dongtai);
							tianchongColors(mTv1, R.color.tab_default);

							// 同城
							tianchongtext(mIv2, R.drawable.ic_tab_tongcheng);
							tianchongColors(mTv2, R.color.tab_default);

							// 餐饮
							tianchongtext(mIv3, R.drawable.ic_tab_canyin);
							tianchongColors(mTv3, R.color.tab_default);
							// 消息
							tianchongtext(mIv4, R.drawable.ic_tab_xiaoxi_selected);
							tianchongColors(mTv4, R.color.tab_default);
							break;
                        default:
                            break;
                    }
			}
		});
	}

	private void prepareView() {
		mBut0 = (LinearLayout) findViewById(R.id.main_tab0);
		mIv0 = (ImageView) findViewById(R.id.main_tab0_iv);
		mTv0 = (TextView) findViewById(R.id.main_tab0_tv);
		mBut1 = (LinearLayout) findViewById(R.id.main_tab1);
		mIv1 = (ImageView) findViewById(R.id.main_tab1_iv);
		mTv1 = (TextView) findViewById(R.id.main_tab1_tv);
		mBut2 = (LinearLayout) findViewById(R.id.main_tab2);
		mIv2 = (ImageView) findViewById(R.id.main_tab2_iv);
		mTv2 = (TextView) findViewById(R.id.main_tab2_tv);
		mBut3 = (RelativeLayout) findViewById(R.id.main_tab3);
		mIv3 = (ImageView) findViewById(R.id.main_tab3_iv);
		mTv3 = (TextView) findViewById(R.id.main_tab3_tv);
		mBut4 = (RelativeLayout) findViewById(R.id.main_tab4);
		mIv4 = (ImageView) findViewById(R.id.main_tab4_iv);
		mTv4 = (TextView) findViewById(R.id.main_tab4_tv);
		linearLayout = (LinearLayout) findViewById(R.id.linear);

		tianchongtext(mIv0, R.drawable.ic_tab_faxian_selected);
		tianchongColors(mTv0, R.color.tab_default);

		MyListener myListener = new MyListener();
		mBut0.setOnClickListener(myListener);
		mBut1.setOnClickListener(myListener);
		mBut2.setOnClickListener(myListener);
		mBut3.setOnClickListener(myListener);
		mBut4.setOnClickListener(myListener);
	}

	public void showMenu() {
		menu.showMenu();
	}

	public void onClick(View v){
		String url = "";
		Intent intent = null;
		switch (v.getId()) {
			case R.id.faxian:
				menu.toggle();
				url += "http://www.icityto.com";
				return;
			case R.id.wodexiaofei:
				//url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=273";
				intent = new Intent(this, MyConsumeActivity.class);
				startActivity(intent);
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

	private class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.main_tab0:

				Application.tabHostId = 0;
				break;
			case R.id.main_tab1:

				Application.tabHostId = 1;
				break;
			case R.id.main_tab2:
				Application.tabHostId = 2;
				break;
			case R.id.main_tab3:
				Application.tabHostId = 3;
				break;
			case R.id.main_tab4:
				Application.tabHostId = 4;
//				Intent i = new Intent(MainTabAct.this, CommonWebView.class);
//				i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=348&UId=yesicity2015");
//				startActivity(i);
				break;

			default:
				break;
			}
			Application.tabHost.setCurrentTab(Application.tabHostId);
		}
	}

	private void tianchongColors(TextView textView, int clolors) {
		textView.setTextColor(getResources().getColor(clolors));
	}

	private void tianchongtext(ImageView imageviews, int fonts) {
		imageviews.setImageResource(fonts);
		// imageviews.setBackgroundDrawable(getResources().getDrawable(fonts));
	}

	/**
	 * // * 菜单、返回键响应 //
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // 调用双击退出函数
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
			System.exit(0);
		}
	}

}
