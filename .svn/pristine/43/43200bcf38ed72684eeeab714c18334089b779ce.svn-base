package com.yunchengke.app.ui.activity.headline;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.InstantNewsList;
import com.yunchengke.app.bean.NewsDetail;
import com.yunchengke.app.bean.NewsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.InstantNewsListRequest;
import com.yunchengke.app.http.request.NewsDetailRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 类名：InstantNewsActivity <br/>
 * 描述：及时或现场新闻
 * 创建时间：2016/01/19 22:38
 *
 * @author hanter
 * @version 1.0
 */
public class InstantNewsActivity extends BaseActivity {
    public final static String EXTRA_TYPE= "EXTRA_TYPE";
    public final static String EXTRA_MEDIA_ID = "EXTRA_MEDIA_ID";

    public final static int NEWS_TYPE_INSTANT = 0;
    public final static int NEWS_TYPE_LIVE = 1;

    @Bind(R.id.lv_instant_news_content)
    PullToRefreshListView lvInstantNewsContent;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private int mType;
    private long mMediaId;

    ArrayList<InstantNewsList.RowsEntity> mList;
    MediaSubNewsListAdapter mListAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_instant_news, parent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(EXTRA_TYPE, mType);
        outState.putLong(EXTRA_MEDIA_ID, mMediaId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mType = getIntent().getIntExtra(EXTRA_TYPE, 0);
            mMediaId = getIntent().getLongExtra(EXTRA_MEDIA_ID, 0);
        } else {
            mType = savedInstanceState.getInt(EXTRA_TYPE, 0);
            mMediaId = savedInstanceState.getLong(EXTRA_MEDIA_ID, 0);
        }

        lytTitle.setBackgroundResource(R.color.headline_background);
        setTitle(R.string.news);
        setTitleLeft(R.string.back);

        ListView listView = lvInstantNewsContent.getRefreshableView();
        lvInstantNewsContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
            }
        });

        lvInstantNewsContent.setScrollLoadEnabled(true);
        mList = new ArrayList<InstantNewsList.RowsEntity>();
        mListAdapter = new MediaSubNewsListAdapter(this);
        mListAdapter.setRows(mList);
        listView.setAdapter(mListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    // 跳转到详情
                    InstantNewsList.RowsEntity row = mList.get(0);
                    int newsId = row.getX6_Product_Id();

                    NewsDetailRequest req = new NewsDetailRequest(new HttpRequestListener<NewsDetail>() {
                        @Override
                        public void onResponse(NewsDetail response) {
                            super.onResponse(response);

                            try {
                                NewsDetail.RowsEntity row = response.getRows().get(0);
                                if (row != null)
                                    UIHelper.gotoNewsDetailActivity(InstantNewsActivity.this, row);
                            } catch (Exception e) {
                                // 捕捉异常
                            }

                        }

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            super.onErrorResponse(error);

                            AppLog.e(error.getMessage());
                        }
                    });

                    req.setRequestParams(newsId);
                    HttpRequestQueue.addToRequestQueue(req);

                } catch (Exception e) {
                    // 异常捕获
                }
            }
        });

        refreshListView(true);
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

    private void refreshListView(boolean pulldownRefresh) {
        mPrePage = mCurrentPage;
        if (pulldownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        AppLog.i("刷新第" + mCurrentPage + "页");

        InstantNewsListRequest req = new InstantNewsListRequest(mListener);
        req.setRequestParams(mType, mMediaId, mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private HttpRequestListener<InstantNewsList> mListener = new HttpRequestListener<InstantNewsList>() {
        @Override
        public void onResponse(InstantNewsList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mList.clear();
            }

            mList.addAll(response.getRows());
            mListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mList.size()) {
                lvInstantNewsContent.setHasMoreData(false);
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

            lvInstantNewsContent.onPullRefreshComplete();
        }
    };

    static class MediaSubNewsListAdapter extends AbsBaseAdapter<InstantNewsList.RowsEntity> {

        public MediaSubNewsListAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sub_past_news, parent, false);
                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            InstantNewsList.RowsEntity row = mRows.get(position);

            String imageUrl = row.getX6_Product_Pic();
            Glide.with(mContext).load(imageUrl).into(holder.image);


            holder.title.setText(row.getField_XWBT());

            String introduction = mContext.getResources().getString(R.string.news_introduction, row.getField_XWJJ());
            holder.introduction.setText(introduction);

            String creator = mContext.getResources().getString(R.string.past_news_creator,
                    row.getField_XWCJR());
            holder.creator.setText(creator);

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_sub_past_news_image)
            ImageView image;
            @Bind(R.id.tv_item_sub_past_news_title)
            TextView title;
            @Bind(R.id.tv_item_sub_past_news_introduction)
            TextView introduction;
            @Bind(R.id.tv_item_sub_past_news_creator)
            TextView creator;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


}
