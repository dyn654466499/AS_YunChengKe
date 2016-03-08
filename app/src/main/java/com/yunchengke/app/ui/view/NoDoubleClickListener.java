package com.yunchengke.app.ui.view;

import android.view.View;

import java.util.Calendar;

/**
 * 类名：NoDoubleClickListener <br/>
 * 描述：禁止多次点击的OnClickListener
 * 创建时间：2016/01/14 0:07
 *
 * @author hanter
 * @version 1.0
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 800;
    private long lastClickTime = 0;

    @Override
    public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(view);
        }
    }

    public abstract void onNoDoubleClick(View view);
}
