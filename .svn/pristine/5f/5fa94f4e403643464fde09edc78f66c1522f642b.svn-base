package com.yunchengke.app.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.andreabaccega.formedittextvalidator.AndValidator;
import com.andreabaccega.formedittextvalidator.EmptyValidator;
import com.andreabaccega.widget.FormEditText;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.ForgetEntity;
import com.yunchengke.app.bean.CodeResult;
import com.yunchengke.app.bean.LoginResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.CodeRequest;
import com.yunchengke.app.http.request.ForgetPasswordRequest;
import com.yunchengke.app.ui.activity.login.validator.CodeValidator;
import com.yunchengke.app.ui.activity.login.validator.PasswordValidator;
import com.yunchengke.app.ui.activity.login.validator.PhoneValidator;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity {

    @Bind(R.id.forget_username)
    FormEditText username;
    @Bind(R.id.forget_code)
    FormEditText code;
    @Bind(R.id.forget_password)
    FormEditText password;
    @Bind(R.id.forget_show_password)
    Button showp;
    @Bind(R.id.forget_get_code)
    Button getc;
    @Bind(R.id.forget_reset)
    Button reset;
    //密码显示开关
    boolean hide = true;
    ForgetEntity params = new ForgetEntity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加验证

        username.addValidator(new AndValidator(new EmptyValidator(getString(R.string.empty_validator)),new PhoneValidator()));
        password.addValidator(new AndValidator(new EmptyValidator(getString(R.string.empty_validator)),new PasswordValidator()));
        code.addValidator(new AndValidator(new EmptyValidator(getString(R.string.empty_validator)),new CodeValidator()));
    }

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_forget_password,parent);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        lytTitle.setBackgroundResource(R.color.login_background);
        setTitle(R.string.forget_title);
        setTitleLeft(R.string.back);
    }

    @OnClick(R.id.forget_get_code) void getc(){
        CodeRequest req = new CodeRequest(new HttpRequestListener<CodeResult>() {
            @Override
            public void onResponse(CodeResult response) {
                if (response.isSucceed(getBaseContext(),response.getErrorCode(),response.getMessage())){
                    ToastUtils.show(getBaseContext(),"发送成功");
                }else {
                    ToastUtils.show(getBaseContext(),"发送出错");
                }
            }
        });
        String param = "{phone:\"" + username.getText().toString() + "\"}";
        req.setRequestParams(param);
        HttpRequestQueue.addToRequestQueue(req);
    }
    @OnClick(R.id.forget_show_password) void showp(){
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
    @OnClick(R.id.forget_reset) void reset(){
        if (username.testValidity() && password.testValidity()) {
            ForgetPasswordRequest req = new ForgetPasswordRequest(new HttpRequestListener<LoginResult>() {
                @Override
                public void onResponse(LoginResult response) {
                    if (response.isSucceed(getBaseContext(), response.getResultState(), response.getMessage())) {
                        ToastUtils.show(getBaseContext(), "修改成功");
                        LoginResult.UseInfoEntity info = response.getUseInfo();
                        String id = info.getId();
                        Log.e("ID=", id);
                        Intent intent = new Intent();
                        intent.putExtra("ID", id);
                        setResult(RESULT_OK, intent);
                    }
                }
            });
            params.setUsername(username.getText().toString());
            params.setUsepassword(password.getText().toString());
            params.setCode(code.getText().toString());
            Log.e("param:",params.toString());
            req.setRequestParams(params.toString());
            HttpRequestQueue.addToRequestQueue(req);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;
        }
    }
}
