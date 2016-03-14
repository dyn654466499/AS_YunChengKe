package com.yunchengke.app.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.activity.CommonWebView;
import com.yunchengke.app.ui.activity.daemon.MyConsumeActivity;
import com.yunchengke.app.ui.activity.login.LoginActivity;
import com.yunchengke.app.ui.activity.maintab.MainTabAct;

import butterknife.ButterKnife;

/**
 * 名称: BaseActivity <br/>
 * 描述: 自定义基类Activity <br/>
 * 创建时间：2016/1/6 11:21
 *
 * @author 709835509@qq.com
 * @version 1.0
 */
public abstract class BaseActivity extends AbsBaseActivity implements View.OnClickListener {
    protected final String mRequestTag = "HTTP_REQUEST_TAG";

    private boolean mShowSlidingMenu = true;

    private boolean mShowSearchView;
    private ProgressDialog progressDialog;
    protected ViewGroup lytTitle;

    // 标题栏视图
    protected TextView tvTitle;
    protected TextView tvLeft;
    protected TextView tvRight;

    protected EditText edtSearch;

    // 内容视图
    protected FrameLayout mContentView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置初始化
        setContentView(R.layout.activity_base);

        // 初始化
        initBaseContentView();

        ButterKnife.bind(this);

        // 初始化标题栏
        initViews(savedInstanceState);

        if (mShowSlidingMenu) {
            initSlidingMenu();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()) { // 只有当结束Activity才取消请求
            HttpRequestQueue.cancelAll(mRequestTag);
        }
    }

    /**
     * 初始化视图
     */
    private void initBaseContentView() {

        // 内容视图
        mContentView = (FrameLayout) findViewById(R.id.lyt_base_content);

        View view = createContentView(mContentView);
        if (view != null) {
            mContentView.addView(view);
        }

    }

    /**
     * 必须实现，用于添加当前视图
     * @return 返回视图
     */
    protected abstract View createContentView(ViewGroup parent);

    protected void initViews(Bundle savedInstanceState) {
        lytTitle = (ViewGroup) findViewById(R.id.lyt_base_title);

        tvTitle = (TextView) findViewById(R.id.tv_base_title);
        tvTitle.setOnClickListener(this);
        tvLeft = (TextView) findViewById(R.id.tv_base_title_left);
        tvLeft.setOnClickListener(this);
        tvRight = (TextView) findViewById(R.id.tv_base_title_right);
        tvRight.setOnClickListener(this);

        edtSearch = (EditText) findViewById(R.id.et_catering_search);
    }

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
                intent.setClass(BaseActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    public void showSearchView(@StringRes int resid) {
        setTitleRight(resid);

        edtSearch.setVisibility(View.VISIBLE);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_search_view_show);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                edtSearch.requestFocus();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtSearch, 0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        edtSearch.startAnimation(anim);

        mShowSearchView = true;
    }

    public void hideSearchView(@StringRes int resid) {

        setTitleRight(resid);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_search_view_hide);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edtSearch.getWindowToken(), 0);


                edtSearch.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        edtSearch.startAnimation(anim);

        mShowSearchView = false;
    }

    public View inflate(@LayoutRes int resid, ViewGroup parent) {
        return LayoutInflater.from(this).inflate(resid, parent, false);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitle(@StringRes int resid) {
        tvTitle.setText(resid);
    }

    public void setTitleLeft(String title) {
        tvLeft.setText(title);
    }

    public void setTitleLeft(@StringRes int resid) {
        tvLeft.setText(resid);
    }

    public void setTitleRight(String title) {
        tvRight.setText(title);
    }

    public void setTitleRight(@StringRes int resid) {
        tvRight.setText(resid);
    }

    public void setImageLeft(@DrawableRes int resid) {
        tvLeft.setCompoundDrawablesWithIntrinsicBounds(resid, 0, 0, 0);
    }

    public void setImageRight(@DrawableRes int resid) {
        tvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, resid, 0);
    }

    public void setTitleBackgroundColor(@ColorInt int color) {
        lytTitle.setBackgroundColor(color);
    }

    public void setTitleBackgroundResource(@DrawableRes int resid) {
        lytTitle.setBackgroundResource(resid);
    }

    public String getRequestTag() {
        return mRequestTag;
    }

    public boolean isShowSearchView() {
        return mShowSearchView;
    }

    public void startDialog(int id){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(id));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public void closeDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public boolean isShowSlidingMenu() {
        return mShowSlidingMenu;
    }

    public void setShowSlidingMenu(boolean showSlidingMenu) {
        this.mShowSlidingMenu = showSlidingMenu;
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
                url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=267&UId=yesicity2015";
                break;
            case R.id.jifeng:
                url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=266&UId=yesicity2015";
                break;
            case R.id.settings:
                url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=268&UId=yesicity2015";
                break;
            case R.id.sh:
                url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=461&UId=yesicity2015";
                break;
            case R.id.guanzhu:
                url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=271&UId=yesicity2015&GZID=" + Application.loginInfo.getId();
                break;
            case R.id.fengsi:
                url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=270&UId=yesicity2015&GZID=" + Application.loginInfo.getId();
                break;
            case R.id.chaozhaohaoyou:
                url += "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=293&UId=yesicity2015";
                break;
        }
        Intent i = new Intent(this, CommonWebView.class);
        i.putExtra(getString(R.string.url), url);
        startActivity(i);
    }
}
