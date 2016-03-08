package com.yunchengke.app.ui.activity.city;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.yunchengke.app.R;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.widget.SegmentedGroup;

public class CityMainActivity extends BaseActivity {

    private SegmentedGroup sgpTop;

    private ViewPager vpgContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lytTitle.setBackgroundResource(R.color.city_background);
        setTitle(R.string.main_tab_text3);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.search);

        sgpTop = (SegmentedGroup) findViewById(R.id.sgp_city_top);
        sgpTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.news:
                        vpgContent.setCurrentItem(0);
                        break;

                    case R.id.media:
                        vpgContent.setCurrentItem(1);
                        break;

                    case R.id.myself:
                        vpgContent.setCurrentItem(2);
                        break;
                }
            }
        });


    }

    @Override
    protected View createContentView(ViewGroup parent) {
        return LayoutInflater.from(this).inflate(R.layout.activity_city_main, parent, false);
    }

    @Override
    public void onClick(View v) {

    }
}
