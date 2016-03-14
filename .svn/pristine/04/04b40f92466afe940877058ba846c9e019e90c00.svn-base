package com.yunchengke.app.ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;
import com.yunchengke.app.bean.LoginEntity;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.activity.daemon.MyConsumeActivity;
import com.yunchengke.app.ui.activity.login.LoginActivity;
import com.yunchengke.app.ui.activity.maintab.MainTabAct;
import com.yunchengke.app.ui.base.AbsBaseActivity;

public class CommonWebView extends AbsBaseActivity implements View.OnClickListener{

    public WebView web;
    public Button back;
    public LinearLayout content;
    public String murl = null;
    ProgressDialog progressDialog;
    LoginEntity params = new LoginEntity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(true);
        content = (LinearLayout) findViewById(R.id.content);
        web = (WebView) findViewById(R.id.wb);
        initWeb();
        initLogin();
        Intent intent = this.getIntent();
        murl = intent.getStringExtra(this.getString(R.string.url));
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

            initSlidingMenu();
    }

    private void initLogin() {
        //自动执行H5登录
        SharedPreferences pref = getSharedPreferences("login_info",MODE_PRIVATE);
        String UId = "UId=yesicity2015";
        params.setUsername(pref.getString("username",null));
        params.setUsepassword(pref.getString("password",null));
        String param = params.toString();
        String a = HttpUrls.HTTP_LOGIN_REQUEST+"?"+UId+"&"+"param="+param;
        progressDialog.show();
        web.loadUrl(a);
        Log.e("url:",a);
    }

    @SuppressLint("JavascriptInterface")
    private void initWeb() {
        web.addJavascriptInterface(this,"control");
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setJavaScriptEnabled(true);

        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        web.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                web.loadUrl(murl);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        content.setVisibility(View.GONE);
                    }
                }, 3 * 1000);
            }
        });
    }

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

    private void initSlidingMenu() {

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

        view.findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.remove("id");
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(CommonWebView.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
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
