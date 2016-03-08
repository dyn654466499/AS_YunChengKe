package com.yunchengke.app.ui.activity.group;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.ui.adapter.CommonPagerAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.CategoryView;
import com.yunchengke.app.ui.view.GroupListView;
import com.yunchengke.app.ui.view.MyGroupView;
import com.yunchengke.app.ui.view.TopicListView;
import com.yunchengke.app.ui.widget.SegmentedGroup;
import com.yunchengke.app.utils.SoftInputUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：GroupActivity <br/>
 * 描述：小组模块主界面
 * 创建时间：2016/01/07 21:40
 *
 * @author hanter
 * @version 1.0
 */
public class GroupActivity extends BaseActivity {

    private int mCurrentPage = 0;

    private SegmentedGroup sgpTop;

    private ViewPager vpgContent;
    private List<View> mViews;
    private CommonPagerAdapter pagerAdapter;

    private TopicListView topicListView;
    private GroupListView groupListView;
    private CategoryView categoryView;
    private MyGroupView myGroupView;

    @Override
    protected View createContentView(ViewGroup parent) {
        return LayoutInflater.from(this).inflate(R.layout.activity_group, parent, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lytTitle.setBackgroundResource(R.color.group_background);
        setTitle(R.string.group);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.search);

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String keyword = textView.getText().toString();
                    topicListView.searchKeyword(keyword);
                    groupListView.searchKeyword(keyword);
                    SoftInputUtil.hideSoftInput(getBaseContext(), edtSearch);
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

        topicListView = new TopicListView(this);
        groupListView = new GroupListView(this);
        categoryView = new CategoryView(this);
        myGroupView = new MyGroupView(this);

        vpgContent = (ViewPager) findViewById(R.id.vpg_group_content);

        mViews = new ArrayList<View>();

        mViews.add(topicListView);
        mViews.add(groupListView);
        mViews.add(categoryView);
        mViews.add(myGroupView);

        pagerAdapter = new CommonPagerAdapter(mViews);
        vpgContent.setAdapter(pagerAdapter);

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        myGroupView.refresh();
        groupListView.refresh();
        topicListView.refresh();
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
        }
    }

    @Override
    public void showSearchView(@StringRes int resid) {
        super.showSearchView(resid);

        sgpTop.check(R.id.topic);

        sgpTop.getChildAt(2).setVisibility(View.GONE);

        topicListView.showSearchView();
        groupListView.showSearchView();

        sgpTop.getChildAt(3).setVisibility(View.GONE);

        mViews.remove(categoryView);
        mViews.remove(myGroupView);
        pagerAdapter.notifyDataSetChanged();

        sgpTop.updateBackground();
    }

    @Override
    public void hideSearchView(@StringRes int resid) {
        super.hideSearchView(resid);

        sgpTop.check(R.id.topic);

        edtSearch.getText().clear();

        topicListView.showNormalView();
        groupListView.showNormalView();

        sgpTop.getChildAt(2).setVisibility(View.VISIBLE);
        sgpTop.getChildAt(3).setVisibility(View.VISIBLE);

        mViews.add(categoryView);
        mViews.add(myGroupView);
        pagerAdapter.notifyDataSetChanged();

        sgpTop.updateBackground();
    }
}
