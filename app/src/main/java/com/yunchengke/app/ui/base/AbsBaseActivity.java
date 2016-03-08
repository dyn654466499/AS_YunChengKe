package com.yunchengke.app.ui.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

import com.yunchengke.app.app.AppManager;
import com.umeng.analytics.MobclickAgent;

/**
 * 名称: AbsBaseActivity <br/>
 * 描述: 添加BUG统计、Activity管理 <br/>
 * 创建时间：2016/1/7 11:00
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class AbsBaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AppManager.getAppManager().addActivity(this);
        // UMENG统计
        MobclickAgent.setDebugMode(true);
        MobclickAgent.openActivityDurationTrack(false);  // 禁用默认页面统计方式(类名统计)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AppManager.getAppManager().finishActivity(this);
    }

}
