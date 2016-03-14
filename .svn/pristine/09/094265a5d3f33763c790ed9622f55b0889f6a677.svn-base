package com.yunchengke.app.ui.view;

import android.view.View;

import java.util.Calendar;

/**
 * 类名：OnNestedClickListener <br/>
 * 描述：AdapterView内部点击事件，并禁止快速点击
 * 创建时间：2016/01/30 14:21
 *
 * @author hanter
 * @version 1.0
 */
public abstract class OnNestedClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 800;
    private long lastClickTime = 0;

    @Override
    public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            Integer position = (Integer) view.getTag(view.getId());
            onNestedClickListener(position, view);
        }
    }

    public abstract void onNestedClickListener(int position, View view);
}
