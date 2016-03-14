package com.yunchengke.app.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.MediaList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.MediaListRequest;
import com.yunchengke.app.http.request.MyFollowedMediaListRequest;
import com.yunchengke.app.ui.UIHelper;

import org.apache.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 名称: MyHeadlineView <br/>
 * 描述: 我关注的媒体 <br/>
 * 创建时间：2016/1/13 17:23
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class MyHeadlineView extends BaseCompoundView {
    @Bind(R.id.lv_my_headline_news)
    PullToRefreshListView lvMyHeadlineNews;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    List<MediaList.RowsEntity> mMediaList;
    MediaListView.MediaListAdapter mMediaListAdapter;

    public MyHeadlineView(Context context) {
        super(context);
    }

    public MyHeadlineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHeadlineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyHeadlineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initViews() {
        lvMyHeadlineNews.setScrollLoadEnabled(true);
        lvMyHeadlineNews.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
            }
        });

        ListView listView = lvMyHeadlineNews.getRefreshableView();
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.medialist_divider_height));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    UIHelper.gotoMediaDetailActivity(getContext(), mMediaList.get(position));
                } catch (Exception e) {
                    // 异常捕捉
                }
            }
        });
        mMediaList = new ArrayList<MediaList.RowsEntity>();
        mMediaListAdapter = new MediaListView.MediaListAdapter(getContext());
        mMediaListAdapter.setRows(mMediaList);
        listView.setAdapter(mMediaListAdapter);

        lvMyHeadlineNews.doPullRefreshing(true, 150);
    }

    public void refresh() {
        refreshListView(true);
    }

    private void refreshListView(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        MyFollowedMediaListRequest req = new MyFollowedMediaListRequest(mListener);
        req.setRequestParams(mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private HttpRequestListener<MediaList> mListener = new HttpRequestListener<MediaList>() {
        @Override
        public void onResponse(MediaList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mMediaList.clear();
            }

            mMediaList.addAll(response.getRows());
            mMediaListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mMediaList.size()) {
                lvMyHeadlineNews.setHasMoreData(false);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);

            // 错误处理
            mPullDownRefresh = false;
            mCurrentPage = mPrePage;
        }

        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            lvMyHeadlineNews.onPullRefreshComplete();
        }
    };

    @Override
    public int getContentLayoutResourceId() {
        return R.layout.view_my_headline;
    }
}
