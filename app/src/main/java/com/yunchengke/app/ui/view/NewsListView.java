package com.yunchengke.app.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.NewsDetail;
import com.yunchengke.app.bean.NewsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.NewsListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 类名：NewsListView <br/>
 * 描述：新闻列表
 * 创建时间：2016/01/11 23:17
 *
 * @author hanter
 * @version 1.0
 */
public class NewsListView extends BaseCompoundView {

    @Bind(R.id.lv_view_news_content)
    PullToRefreshListView lvNewsListContent;

    private boolean mInSearch;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private String mKeyword;
    private int mSearchPrePage;
    private int mSearchCurrentPage;

    List<NewsDetail.RowsEntity> mNewsList;
    private List<NewsDetail.RowsEntity> mSearchNewsList;
    NewsListAdapter mNewsListAdapter;

    public NewsListView(Context context) {
        super(context);
    }

    public NewsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initViews() {

        lvNewsListContent.setScrollLoadEnabled(true);
        ListView listView = lvNewsListContent.getRefreshableView();
        listView.setDivider(getResources().getDrawable(R.color.transparent));
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.newslist_divider_height));
        mNewsListAdapter = new NewsListAdapter(getContext());
        mNewsList = new ArrayList<NewsDetail.RowsEntity>();
        mSearchNewsList = new ArrayList<NewsDetail.RowsEntity>();
        mNewsListAdapter.setRows(mNewsList);
        listView.setAdapter(mNewsListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    // 跳转到详情
                    NewsDetail.RowsEntity row = mNewsList.get(position);
                    int newId = row.getX6_Product_Id();
                    UIHelper.gotoNewsDetailActivity(getContext(), row);
                } catch (Exception e) {
                    // 异常捕获
                }
            }
        });

        lvNewsListContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
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

        lvNewsListContent.doPullRefreshing(true, 50);
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

        AppLog.i("刷新第" + mCurrentPage + "页");

        NewsListRequest req = new NewsListRequest(mListener);
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

        NewsListRequest req = new NewsListRequest(mSearchListener);
        req.setSearchParams(mSearchCurrentPage, mKeyword);
        HttpRequestQueue.addToRequestQueue(req);
    }

    public void searchKeyword(String keyword) {
        mKeyword = keyword;
        showSearchView();
        lvNewsListContent.doPullRefreshing(true, 100);
    }

    public void showSearchView() {
        mInSearch = true;
        mNewsListAdapter.setRows(mSearchNewsList);
        mNewsListAdapter.notifyDataSetChanged();
    }

    public void showNormalView() {
        mInSearch = false;
        mNewsListAdapter.setRows(mNewsList);
        mNewsListAdapter.notifyDataSetChanged();
    }

    HttpRequestListener<NewsList> mListener = new HttpRequestListener<NewsList>() {
        @Override
        public void onResponse(NewsList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mNewsList.clear();
            }

            mNewsList.addAll(response.getRows());
            mNewsListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mNewsList.size()) {
                lvNewsListContent.setHasMoreData(false);
            }

        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);

            // 错误处理
            mPullDownRefresh = false;
            mCurrentPage = mPrePage;

            if (error != null) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            lvNewsListContent.onPullRefreshComplete();
        }
    };

    HttpRequestListener<NewsList> mSearchListener = new HttpRequestListener<NewsList>() {
        @Override
        public void onResponse(NewsList response) {
            super.onResponse(response);

            if (mSearchCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mSearchNewsList.clear();
            }

            List<NewsDetail.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mSearchCurrentPage = mSearchPrePage;
            }

            mSearchNewsList.addAll(response.getRows());
            mNewsListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mSearchNewsList.size()) {
                lvNewsListContent.setHasMoreData(false);
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
            lvNewsListContent.onPullRefreshComplete();
        }
    };

    @Override
    public int getContentLayoutResourceId() {
        return R.layout.view_news;
    }

    static class NewsListAdapter extends AbsBaseAdapter<NewsDetail.RowsEntity> {

        public NewsListAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // TODO 初始化，时间实时刷新
            NewsDetail.RowsEntity row = mRows.get(position);
            String url = row.getX6_Product_Pic();
            Glide.with(mContext).load(url).into(holder.ivItemNewsImage);

            holder.tvItemNewsContent.setText(row.getField_XWBT());

            holder.tvItemNewsGroup.setText(row.getField_XWCJR());

            String count = mContext.getResources().getString(R.string.item_news_comment_count, row.get_count());
            holder.tvItemNewsCount.setText(count);

            holder.tvItemNewsDatetime.setText(DateTimeUtils.getIntervalTime(row.getX6_Product_Time()));

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_news_image)
            ImageView ivItemNewsImage;
            @Bind(R.id.tv_item_news_content)
            TextView tvItemNewsContent;
            @Bind(R.id.tv_item_news_group)
            TextView tvItemNewsGroup;
            @Bind(R.id.tv_item_news_datetime)
            TextView tvItemNewsDatetime;
            @Bind(R.id.tv_item_news_count)
            TextView tvItemNewsCount;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
