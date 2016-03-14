package com.yunchengke.app.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 类名：CommonPagerAdapter <br/>
 * 描述：通用的PagerAdapter
 * 创建时间：2016/01/08 0:26
 *
 * @author hanter
 * @version 1.0
 */
public class CommonPagerAdapter extends PagerAdapter {
    private List<View> mViews = null;

    public CommonPagerAdapter(List<View> listViews) {
        this.mViews = listViews;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        if (mViews != null) {
            return mViews.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view, 0);
        return view;
    }

}