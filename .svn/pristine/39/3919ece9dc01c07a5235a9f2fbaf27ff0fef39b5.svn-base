package com.yunchengke.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.yunchengke.app.R;
import com.yunchengke.app.ui.activity.login.LoginActivity;
import com.yunchengke.app.ui.activity.maintab.MainTabAct;

public class WelcomeActivity extends Activity implements Animation.AnimationListener {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        iv = (ImageView) findViewById(R.id.iv);
        Animation animation = new AlphaAnimation(0.8f,1f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(this);
        iv.setAnimation(animation);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        SharedPreferences pref = getSharedPreferences("login_info",MODE_PRIVATE);

        if (pref.contains("id") ) {
            startActivity(new Intent(WelcomeActivity.this,MainTabAct.class));
        } else {
            Intent i =new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(i);
        }
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
