package com.yunchengke.app.ui.activity.headline;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.ui.adapter.CommonPagerAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.MediaListView;
import com.yunchengke.app.ui.view.MyHeadlineView;
import com.yunchengke.app.ui.view.NewsListView;
import com.yunchengke.app.ui.widget.SegmentedGroup;
import com.yunchengke.app.utils.SoftInputUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：HeadlineActivity <br/>
 * 描述：头条页面
 * 创建时间：2016/01/11 22:19
 *
 * @author hanter
 * @version 1.0
 */
public class HeadlineActivity extends BaseActivity {
    private SegmentedGroup sgpTop;

    private int mCurrentPage;

    private ViewPager vpgContent;
    private List<View> mViews;
    private CommonPagerAdapter pagerAdapter;
    private NewsListView newsListView;
    private MediaListView mediaListView;
    private MyHeadlineView myHeadlineView;

    @Override
    protected View createContentView(ViewGroup parent) {
        return LayoutInflater.from(this).inflate(R.layout.activity_headline, parent, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lytTitle.setBackgroundResource(R.color.headline_background);
        setTitle(R.string.headline_title);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.search);

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String keyword = textView.getText().toString();
                    newsListView.searchKeyword(keyword);
                    mediaListView.searchKeyword(keyword);
                    SoftInputUtil.hideSoftInput(HeadlineActivity.this,edtSearch);
                    return true;
                }

                return false;
            }
        });

        sgpTop = (SegmentedGroup) findViewById(R.id.sgp_group_top);
        sgpTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                int checkedPosition = group.indexOfChild(group.findViewById(checkedId));

                if (mCurrentPage != checkedPosition) {
                    vpgContent.setCurrentItem(checkedPosition, true);
                }
            }
        });

        newsListView = new NewsListView(this);
        mediaListView = new MediaListView(this);
        myHeadlineView = new MyHeadlineView(this);

        vpgContent = (ViewPager) findViewById(R.id.vpg_headline_content);
        vpgContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                ((RadioButton) sgpTop.getChildAt(mCurrentPage)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViews = new ArrayList<View>();
        mViews.add(newsListView);
        mViews.add(mediaListView);
        mViews.add(myHeadlineView);
        pagerAdapter = new CommonPagerAdapter(mViews);
        vpgContent.setAdapter(pagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        newsListView.refresh();
        mediaListView.refresh();
        myHeadlineView.refresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            case R.id.tv_base_title_right:
                if (isShowSearchView()) {
                    hideSearchView(R.string.search);
                } else {
                    showSearchView(R.string.cancel);
                }
                break;

            default:
                super.onClick(view);
                break;
        }
    }

    @Override
    public void showSearchView(@StringRes int resId) {
        super.showSearchView(resId);

        sgpTop.check(R.id.news);

        newsListView.showSearchView();
        mediaListView.showSearchView();

        sgpTop.getChildAt(2).setVisibility(View.GONE);

        mViews.remove(myHeadlineView);
        pagerAdapter.notifyDataSetChanged();

        sgpTop.updateBackground();
    }

    @Override
    public void hideSearchView(@StringRes int resId) {
        super.hideSearchView(resId);

        sgpTop.check(R.id.news);

        edtSearch.getText().clear();

        newsListView.showNormalView();
        mediaListView.showNormalView();

        sgpTop.getChildAt(2).setVisibility(View.VISIBLE);

        mViews.add(myHeadlineView);
        pagerAdapter.notifyDataSetChanged();

        sgpTop.updateBackground();
    }
}
