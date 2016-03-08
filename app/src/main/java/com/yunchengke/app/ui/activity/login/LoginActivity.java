package com.yunchengke.app.ui.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andreabaccega.formedittextvalidator.AndValidator;
import com.andreabaccega.formedittextvalidator.EmptyValidator;
import com.andreabaccega.widget.FormEditText;
import com.android.volley.VolleyError;
import com.mob.tools.utils.UIHandler;
import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;
import com.yunchengke.app.bean.LoginEntity;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.LoginResult;
import com.yunchengke.app.bean.MyUserInfo;
import com.yunchengke.app.bean.ThirdLoginResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.Login_Request;
import com.yunchengke.app.http.request.MyUserInfoRequest;
import com.yunchengke.app.http.request.ThirdLoginRequest;
import com.yunchengke.app.ui.activity.login.validator.PasswordValidator;
import com.yunchengke.app.ui.activity.login.validator.PhoneValidator;
import com.yunchengke.app.ui.activity.maintab.MainTabAct;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.utils.ToastUtils;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/13 11:20
 * 描述：登录
 * 版本：1.0
 */
public class LoginActivity extends BaseActivity implements PlatformActionListener, Handler.Callback {
    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR= 4;
    private static final int MSG_AUTH_COMPLETE = 5;

    @Bind(R.id.login_username)
    FormEditText username;
    @Bind(R.id.login_password)
    FormEditText password;
    @Bind(R.id.login_show_password)
    Button showp;
    @Bind(R.id.login_forget_password)
    TextView forgetp;
    @Bind(R.id.login_login)
    Button login;
    @Bind(R.id.login_register)
    Button register;
    @Bind(R.id.login_weixin)
    LinearLayout weixin;
    @Bind(R.id.login_weibo)
    LinearLayout weibo;
    //密码显示开关
    boolean hide = true;
    //param序列数据
    static String type;
    LoginEntity params = new LoginEntity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        //添加验证
        username.addValidator(new AndValidator(new EmptyValidator(getString(R.string.empty_validator)),new PhoneValidator()));
        password.addValidator(new AndValidator(new EmptyValidator(getString(R.string.empty_validator)),new PasswordValidator()));

    }

    @Override
    protected void onDestroy() {
        ShareSDK.stopSDK(this);
        super.onDestroy();
    }

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_login,parent);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        lytTitle.setBackgroundResource(R.color.login_background);
        setTitle(R.string.login_login);
        setTitleLeft(R.string.back);
    }

    @OnClick(R.id.login_login) void login_login() {
        if (username.testValidity() && password.testValidity()) {
            startDialog(R.string.loading);
            Login_Request req = new Login_Request(new HttpRequestListener<LoginResult>() {
                @Override
                public void onResponse(LoginResult response) {
                    closeDialog();
                    if (response.isSucceed(getBaseContext(), response.getResultState(), response.getMessage())) {
                        ToastUtils.show(getBaseContext(), getString(R.string.login_success));
                        LoginResult.UseInfoEntity info = response.getUseInfo();
                        String un = info.getUsername();
                        String up = info.getUserpassword();
                        String id = info.getId();
                        String mobile = info.getMobile();
                        Log.e("ID=", id+",mobile ="+mobile);
                        //login_info存放账号、密码、ID
                        SharedPreferences.Editor editor = getSharedPreferences("login_info",MODE_PRIVATE).edit();
                        editor.putString("username",un);
                        editor.putString("password",up);
                        editor.putString("id",id);
                        editor.putString("mobile",mobile);
                        editor.commit();
                        Application.login_info(id);
                        updateUserInfo();
                        /*
                        finish();
                        startActivity(new Intent(LoginActivity.this,MainTabAct.class));
                        */
                    }else {
                        ToastUtils.show(getBaseContext(), response.getMessage());
                    }
                }
            });
            params.setUsername(username.getText().toString());
            params.setUsepassword(password.getText().toString());
            String param = params.toString();
            Log.e("param:",param);
            req.setRequestParams(param);
            HttpRequestQueue.addToRequestQueue(req);
        }
    }
    @OnClick(R.id.login_register) void login_register(){
        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }
    @OnClick(R.id.login_show_password) void login_showp(){
        if (hide){
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            showp.setText("隐藏");
            hide = false;
        }else {
            //这里不加 TYPE_CLASS_TEXT 不行。
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showp.setText("显示");
            hide = true;
        }
    }
    @OnClick(R.id.login_forget_password) void login_forgetp(){
        Intent i = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        startActivity(i);
    }
    @OnClick(R.id.login_weixin) void login_weixin(){
        Platform weixin = ShareSDK.getPlatform(Wechat.NAME);
        if (weixin == null) {
            return;
        }
//判断指定平台是否已经完成授权
        if (weixin.isValid()){
            String userId = weixin.getDb().getUserId();
            UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
            ToastUtils.show(this,userId);
        }else {
            ToastUtils.show(this,"正在申请授权");
        }
        type = "weixin";
        weixin.SSOSetting(false);
        weixin.setPlatformActionListener(this);
        weixin.showUser(null);
        String userId = weixin.getDb().getUserId();
//        weixin.removeAccount(true);
    }

    private void thirdLogin(String type,String userId) {
        startDialog(R.string.loading);
        ThirdLoginRequest tlr = new ThirdLoginRequest(new HttpRequestListener<ThirdLoginResult>() {
            @Override
            public void onResponse(ThirdLoginResult response) {
                closeDialog();
                if (response.isSucceed(getBaseContext(), response.getResultState(), response.getMessage())) {
                    String id = response.getId();
                    SharedPreferences.Editor editor = getSharedPreferences("login_info",MODE_PRIVATE).edit();
                    editor.putString("id", id);
                    editor.commit();
                    Application.login_info(id);
                    updateUserInfo();
                    /*
                    finish();
                    startActivity(new Intent(LoginActivity.this,MainTabAct.class));
                    */
                }else {
                    ToastUtils.show(getBaseContext(), getString(R.string.login_lose));
                }
            }
        });
        tlr.setRequestParams(userId,type);
        HttpRequestQueue.addToRequestQueue(tlr);
    }

    @OnClick(R.id.login_weibo) void login_weibo(){
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        if (weibo == null) {
            return;
        }
//判断指定平台是否已经完成授权
        if (weibo.isValid()) {
            String userId = weibo.getDb().getUserId();
            UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
            ToastUtils.show(this,userId);
        }else {
            ToastUtils.show(this,"正在申请授权");
        }
        type = "weibo";
        weibo.setPlatformActionListener(this);
        weibo.SSOSetting(false);
        weibo.showUser(null);
//        weixin.removeAccount(true);
    }

    private void login(String userId) {
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = userId;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (i == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);
            String id = platform.getDb().getUserId();
            Log.e("cx",id);
            login(platform.getDb().getUserId());
        }
        System.out.println(hashMap);
        System.out.println("------User Name ---------" + platform.getDb().getUserName());
        System.out.println("------User ID ---------" + platform.getDb().getUserId());
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        if (i == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, this);
        }
        throwable.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        if (i == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch(msg.what) {
            case MSG_USERID_FOUND: {
                Toast.makeText(this, R.string.userid_found, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_LOGIN: {

//                String text = getString(R.string.logining, msg.obj);
//                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                System.out.println("---------------");
                Log.e("cx2",msg.obj.toString());
                thirdLogin(type,msg.obj.toString());
//                ThirdLoginRequest req = new ThirdLoginRequest(new HttpRequestListener<ThirdLoginResult>() {
//                    @Override
//                    public void onResponse(ThirdLoginResult response) {
//                        closeDialog();
//                        if (response.isSucceed(getBaseContext(), response.getResultState(), response.getMessage())) {
//                            ToastUtils.show(getBaseContext(), getString(R.string.login_success));
//                            String id = response.getId();
//                            Log.e("ID=", id);
//                            //login_info存放账号、密码、ID
//                            SharedPreferences.Editor editor = getSharedPreferences("login_info",MODE_PRIVATE).edit();
//                            editor.putString("id",id);
//                            editor.commit();
//                            Intent intent = new Intent();
//                            intent.putExtra("ID", id);
//                            setResult(RESULT_OK, intent);
//                            finish();
//                        }else {
//                            ToastUtils.show(getBaseContext(), getString(R.string.login_lose));
//                        }
//                    }
//                });
//                params.setUsername(username.getText().toString());
//                params.setUsepassword(password.getText().toString());
//                String param = params.toString();
//                Log.e("param:",param);
//                req.setRequestParams(param);
//                HttpRequestQueue.addToRequestQueue(req);
//				Builder builder = new Builder(this);
//				builder.setTitle(R.string.if_register_needed);
//				builder.setMessage(R.string.after_auth);
//				builder.setPositiveButton(R.string.ok, null);
//				builder.create().show();
            }
            break;
            case MSG_AUTH_CANCEL: {
                Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
                System.out.println("-------MSG_AUTH_CANCEL--------");
            }
            break;
            case MSG_AUTH_ERROR: {
                Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
                System.out.println("-------MSG_AUTH_ERROR--------");
            }
            break;
            case MSG_AUTH_COMPLETE: {
                Toast.makeText(this, R.string.auth_complete, Toast.LENGTH_SHORT).show();
                System.out.println("--------MSG_AUTH_COMPLETE-------");
            }
            break;
        }
        return false;
    }


    private void updateUserInfo() {
        startDialog(R.string.loading);
        MyUserInfoRequest req = new MyUserInfoRequest(new HttpRequestListener<MyUserInfo>() {

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                closeDialog();
            }

            @Override
            public void onResponse(MyUserInfo response) {
                super.onResponse(response);
                closeDialog();
                try {
                    MyUserInfo.RowsEntity mUserInfo = response.getRows().get(0);

                    if (mUserInfo != null) {
                        SharedPreferences.Editor editor = getSharedPreferences("login_info",MODE_PRIVATE).edit();
                        editor.putString("local", mUserInfo.getField_SZCS());
                        editor.putInt("follow_me", mUserInfo.getFollow_Me());
                        editor.putInt("followed", mUserInfo.getFollowed());
                        editor.commit();
                        finish();
                        startActivity(new Intent(LoginActivity.this, MainTabAct.class));
                    }
                } catch (Exception e) {
                    // 异常处理
                }

            }
        }
        );
        req.setRequestParams(Long.parseLong(LoginInfo.getInstance().getId()));
        HttpRequestQueue.addToRequestQueue(req);

    }

}
