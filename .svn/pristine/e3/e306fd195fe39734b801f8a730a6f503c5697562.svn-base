package com.yunchengke.app.ui.activity.group;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.GroupList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.GroupListRequest;
import com.yunchengke.app.ui.adapter.GroupListAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.GroupListView;

import butterknife.Bind;

/**
 * 类名：CategoryDetailActivity <br/>
 * 描述：分类详情页面
 * 创建时间：2016/01/10 15:45
 *
 * @author hanter
 * @version 1.0
 */
public class CategoryDetailActivity extends BaseActivity {
    public final static String EXTRA_CATEGORY_ID = "EXTRA_CATEGORY_ID";

    private int mCategoryId;

    @Bind(R.id.btn_category_detail_create_group)
    Button btnCategoryDetailCreateGroup;
    @Bind(R.id.lv_category_detail_refresh)
    PullToRefreshListView lvCategoryDetailRefresh;
    @Bind(R.id.lv_category_detail_content)
    GroupListView lvCategoryDetailContent;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(EXTRA_CATEGORY_ID, mCategoryId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_category_detail, parent);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mCategoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID, 0);
        } else {
            mCategoryId = savedInstanceState.getInt(EXTRA_CATEGORY_ID, 0);
        }


        lytTitle.setBackgroundResource(R.color.group_background);
        setTitle(R.string.category_detail);
        setTitleLeft(R.string.back);

        lvCategoryDetailContent.setCategoryId(mCategoryId);
        lvCategoryDetailContent.refresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            default:
                super.onClick(view);
                break;
        }
    }

}

