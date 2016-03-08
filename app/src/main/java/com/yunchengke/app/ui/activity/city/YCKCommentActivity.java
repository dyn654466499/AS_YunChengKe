package com.yunchengke.app.ui.activity.city;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.city.YCKCommentList;
import com.yunchengke.app.bean.city.YCKJoinedList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.YCKCommentListRequest;
import com.yunchengke.app.http.request.YCKJoinedListRequest;
import com.yunchengke.app.ui.adapter.city.CommentListAdapter;
import com.yunchengke.app.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class YCKCommentActivity extends BaseActivity {

    public final static String EXTRA_CITY_ID = "EXTRA_CITY_ID";
    @Bind(R.id.yck_comment_list)
    PullToRefreshListView mPullToRefreshListView;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private int mId;
    private CommentListAdapter mAdapter;
    private List<YCKCommentList.RowsEntity> mUsers;

    @Override
    protected View createContentView(ViewGroup parent) {
        View view = inflate(R.layout.layout_yck_comment, parent);

        return view;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        lytTitle.setBackgroundResource(R.color.dynamic_title_background);
        setTitle("评论");
        setTitleLeft(R.string.back);
        mId = getIntent().getIntExtra(EXTRA_CITY_ID,0);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.yck_comment_list);
        mPullToRefreshListView.setPullRefreshEnabled(true);
        mPullToRefreshListView.setScrollLoadEnabled(true);
        ListView listView = mPullToRefreshListView.getRefreshableView();
        listView.setDivider(getResources().getDrawable(R.color.transparent));
        listView.setDividerHeight(2);

        mUsers = new ArrayList<YCKCommentList.RowsEntity>();
        mAdapter = new CommentListAdapter(getBaseContext());
        mAdapter.setRows(mUsers);
        mPullToRefreshListView.setAdapter(mAdapter);

        mPullToRefreshListView.doPullRefreshing(true, 100);

        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
            }
        });


    }

    private void refreshListView(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        YCKCommentListRequest req = new YCKCommentListRequest(mListener);
        req.setRequestParams(mId,mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);

    }

    HttpRequestListener<YCKCommentList> mListener = new HttpRequestListener<YCKCommentList>() {
        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            mPullToRefreshListView.onPullRefreshComplete();
        }

        @Override
        public void onResponse(YCKCommentList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mUsers.clear();
            }

            List<YCKCommentList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mUsers.addAll(response.getRows());
            mAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mUsers.size()) {
                mPullToRefreshListView.setHasMoreData(false);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);

            // 错误处理
            mPullDownRefresh = false;
            mCurrentPage = mPrePage;
        }
    };


    //		ptrlvComment.setPullRefreshEnabled(false);
//		ptrlvComment.setScrollLoadEnabled(true);
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_base_title_left ){

            finish();
        }

    }
}
