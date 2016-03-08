package com.yunchengke.app.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.TopicList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.TopicListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.view.daemon.CircleImageView;
import com.yunchengke.app.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 类名：TopicListView <br/>
 * 描述：话题列表
 * 创建时间：2016/01/07 21:55
 *
 * @author hanter
 * @version 1.0
 */
public class TopicListView extends BaseCompoundView {

    private boolean mInSearch;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private String mKeyword;
    private int mSearchPrePage;
    private int mSearchCurrentPage;

    private PullToRefreshListView mPullToRefreshListView;
    private List<TopicList.RowsEntity> mTopicList;
    private List<TopicList.RowsEntity> mSearchTopicList;
    private TopicListAdapter mTopicListAdapter;

    public TopicListView(Context context) {
        super(context);
    }

    public TopicListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopicListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TopicListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initViews() {
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.lv_topiclist_topic);
        mPullToRefreshListView.setScrollLoadEnabled(true);

        ListView listView = mPullToRefreshListView.getRefreshableView();
        listView.setDivider(getResources().getDrawable(R.color.topic_list_divider_color));
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.split_line_height));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    TopicList.RowsEntity row = mTopicList.get(i);
                    UIHelper.gotoTopicDetailActivity(getContext(), row.getX6_Product_Id());
                } catch (Exception e) {
                    // 异常捕捉
                }
            }
        });

        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mInSearch) {
                    refreshSearchListView(true);
                } else {
                    refreshListView(true);
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mInSearch) {
                    refreshSearchListView(false);
                } else {
                    refreshListView(false);
                }
            }
        });

        mTopicList = new ArrayList<TopicList.RowsEntity>();
        mTopicListAdapter = new TopicListAdapter(getContext());
        mTopicListAdapter.setRows(mTopicList);

        mSearchTopicList = new ArrayList<TopicList.RowsEntity>();

        listView.setAdapter(mTopicListAdapter);

        mPullToRefreshListView.doPullRefreshing(true, 50);
    }

    public void searchKeyword(String keyword) {
        mKeyword = keyword;

        showSearchView();

        mPullToRefreshListView.doPullRefreshing(true, 100);
    }

    public void showSearchView() {
        mInSearch = true;

        mTopicListAdapter.setRows(mSearchTopicList);
        mTopicListAdapter.notifyDataSetChanged();
    }

    public void showNormalView() {
        mInSearch = false;


        mTopicListAdapter.setRows(mTopicList);
        mTopicListAdapter.notifyDataSetChanged();
    }

    HttpRequestListener<TopicList> mListener = new HttpRequestListener<TopicList>() {

        @Override
        public void onResponse(TopicList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mTopicList.clear();
            }

            List<TopicList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mTopicList.addAll(response.getRows());
            mTopicListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mTopicList.size()) {
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

        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            mPullToRefreshListView.onPullRefreshComplete();
        }
    };

    HttpRequestListener<TopicList> mSearchListener = new HttpRequestListener<TopicList>() {
        @Override
        public void onStartRequest(Request<TopicList> request) {
            super.onStartRequest(request);

            AppLog.i(request.getUrl());
        }

        @Override
        public void onResponse(TopicList response) {
            super.onResponse(response);

            if (mSearchCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mSearchTopicList.clear();
            }

            List<TopicList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mSearchCurrentPage = mSearchPrePage;
            }

            mSearchTopicList.addAll(response.getRows());
            mTopicListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mSearchTopicList.size()) {
                mPullToRefreshListView.setHasMoreData(false);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);

            // 错误处理
            mPullDownRefresh = false;
            mSearchCurrentPage = mSearchPrePage;
        }

        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            mPullToRefreshListView.onPullRefreshComplete();
        }
    };

    @Override
    public int getContentLayoutResourceId() {
        return R.layout.view_topic_listview;
    }

    public void refresh() {
        mPullToRefreshListView.doPullRefreshing(true, 100);
    }

    private void refreshListView(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        AppLog.i("刷新第" + mCurrentPage + "页");

        TopicListRequest req = new TopicListRequest(mListener);
        req.setRequestParams(mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private void refreshSearchListView(boolean pullDownRefresh) {
        mSearchPrePage = mSearchCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mSearchCurrentPage = 1;
        } else {
            mSearchCurrentPage++;
        }

        AppLog.i("刷新第" + mSearchCurrentPage + "页");

        TopicListRequest req = new TopicListRequest(mSearchListener);
        req.setSearchParams(mKeyword, mSearchCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    public static class TopicListAdapter extends AbsBaseAdapter<TopicList.RowsEntity> {

        private boolean mShowPortrait;

        public TopicListAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.item_topic_list, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            TopicList.RowsEntity row = mRows.get(position);

            if (mShowPortrait) {
                String imageUrl = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getField_YHID();
                Glide.with(mContext).load(imageUrl).into(holder.image);
                holder.image.setVisibility(View.VISIBLE);
            } else {
                holder.image.setVisibility(View.GONE);
            }

            holder.title.setText(row.getField_HTBT());

            holder.name.setText(row.getField_HTCJR());

            String commentCount = mContext.getResources().getString(R.string.topic_comment_count, row.getField_PLSL());
            holder.commentcount.setText(commentCount);

            String interval = DateTimeUtils.getIntervalTime(row.getField_HTCJSJ());

            holder.time.setText(interval);

            return convertView;
        }

        public void setShowPortrait(boolean showPortrait) {
            mShowPortrait = showPortrait;
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_topic_image)
            CircleImageView image;
            @Bind(R.id.tv_item_topic_title)
            TextView title;
            @Bind(R.id.tv_item_topic_name)
            TextView name;
            @Bind(R.id.tv_item_topic_comment_count)
            TextView commentcount;
            @Bind(R.id.tv_item_topic_time)
            TextView time;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
