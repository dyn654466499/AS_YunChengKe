package com.yunchengke.app.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mob.tools.gui.ViewPagerAdapter;
import com.yunchengke.app.R;
import com.yunchengke.app.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{

    private static final String GUIDE_CUSTOM_KEY = "GUIDE_CUSTOM_KEY";

    private ViewPager viewPager;
    private ViewPageradapter viewPageradapter;
    private List<View> views;
    private ImageView[] dots;
    private int[] ids = {
            R.id.iv1,
            R.id.iv2,
            R.id.iv3,
            R.id.iv4
    };
    private Button enterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean mCustom = PreferencesUtils.getBoolean(this, GUIDE_CUSTOM_KEY);
        if (mCustom) {
            startActivity(new Intent(GuideActivity.this,WelcomeActivity.class));
            finish();
        } else {
            setContentView(R.layout.activity_guide);
            initView();
            initDots();
        }
    }

    private void initDots() {
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();

        views.add(inflater.inflate(R.layout.guide_one,null));
        views.add(inflater.inflate(R.layout.guide_two,null));
        views.add(inflater.inflate(R.layout.guide_three,null));
        views.add(inflater.inflate(R.layout.guide_four,null));

        enterBtn = (Button) views.get(3).findViewById(R.id.enter);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesUtils.putBoolean(GuideActivity.this,
                        GUIDE_CUSTOM_KEY, true);
                startActivity(new Intent(GuideActivity.this,WelcomeActivity.class));
                finish();
            }
        });
        viewPageradapter = new ViewPageradapter(views,this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPageradapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i){
                dots[i].setImageResource(R.drawable.selected_point);
            } else {
                dots[i].setImageResource(R.drawable.unselected_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class ViewPageradapter extends PagerAdapter {
        private List<View> views;
        private Context context;

        public ViewPageradapter(List<View> views,Context context){
            this.views = views;
            this.context = context;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager)container).removeView(views.get(position));
        }
        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager)container).addView(views.get(position));
            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }
}
