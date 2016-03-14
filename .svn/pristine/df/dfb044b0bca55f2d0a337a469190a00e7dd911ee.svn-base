package com.yunchengke.app.ui.activity.maintab;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.ui.activity.login.LoginActivity;

public class MyInfoActivity extends Activity {

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
        setContentView(R.layout.layout_menu);
        initView();
    }

    private void initView() {
        final SharedPreferences sp = getSharedPreferences("login_info", MODE_PRIVATE);
        faxian = (LinearLayout) findViewById(R.id.faxian);
        wodexiaofei = (LinearLayout) findViewById(R.id.wodexiaofei);
        liwu = (LinearLayout) findViewById(R.id.liwu);
        jifeng = (LinearLayout) findViewById(R.id.jifeng);
        settings = (LinearLayout) findViewById(R.id.settings);
        sh = (LinearLayout) findViewById(R.id.sh);
        image = (ImageView) findViewById(R.id.image);
        sheng = (TextView) findViewById(R.id.shengfeng);
        sheng.setText(sp.getString("local","未填写"));
        shi = (TextView) findViewById(R.id.shi);
        shi.setVisibility(View.GONE);
        qu = (TextView) findViewById(R.id.qu);
        qu.setVisibility(View.GONE);
        fengsi2 = (TextView) findViewById(R.id.fengsi2);
        fengsi2.setText(String.valueOf(sp.getInt("follow_me", 0)));
        guanzhu2 = (TextView) findViewById(R.id.guanzhu2);
        guanzhu2.setText(String.valueOf(sp.getInt("followed", 0)));
        String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST +sp.getString("id","");
        Glide.with(this).load(url).into(image);
        chazhaohaoyou = (LinearLayout) findViewById(R.id.chaozhaohaoyou);

        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.remove("id");
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(MyInfoActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
