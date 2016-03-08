package com.yunchengke.app.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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
import com.yunchengke.app.bean.MediaList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.MediaListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 名称: MediaListView <br/>
 * 描述: 媒体列表 <br/>
 * 创建时间：2016/1/13 11:33
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */

public class MediaListView extends BaseCompoundView {

    @Bind(R.id.lv_medialist_content)
    PullToRefreshListView lvMedialistContent;

    private boolean mInSearch;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private String mKeyword;
    private int mSearchPrePage;
    private int mSearchCurrentPage;

    List<MediaList.RowsEntity> mMediaList;
    List<MediaList.RowsEntity> mSearchMediaList;
    MediaListAdapter mMediaListAdapter;

    public MediaListView(Context context) {
        super(context);
    }

    public MediaListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MediaListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MediaListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initViews() {

        lvMedialistContent.setScrollLoadEnabled(true);
        ListView listView = lvMedialistContent.getRefreshableView();
        setBackgroundResource(R.color.newslist_background);
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.medialist_divider_height));
        mMediaListAdapter = new MediaListAdapter(getContext());
        mMediaList = new ArrayList<MediaList.RowsEntity>();
        mSearchMediaList = new ArrayList<MediaList.RowsEntity>();
        mMediaListAdapter.setRows(mMediaList);
        listView.setAdapter(mMediaListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    UIHelper.gotoMediaDetailActivity(getContext(), mMediaList.get(position));
                } catch (Exception e) {
                    // 异常捕获
                }
            }
        });

        lvMedialistContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
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

        lvMedialistContent.doPullRefreshing(true, 100);
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

        Log.i("MediaListView", "刷新第" + mCurrentPage + "页");

        MediaListRequest req = new MediaListRequest(mListener);
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

        Log.i("MediaListView", "刷新第" + mSearchCurrentPage + "页");

        MediaListRequest req = new MediaListRequest(mSearchListener);
        req.setSearchParams(mSearchCurrentPage, mKeyword);
        HttpRequestQueue.addToRequestQueue(req);
    }

    public void searchKeyword(String keyword) {
        mKeyword = keyword;
        showSearchView();
        lvMedialistContent.doPullRefreshing(true, 100);
    }

    public void showSearchView() {
        mInSearch = true;
        mMediaListAdapter.setRows(mSearchMediaList);
        mMediaListAdapter.notifyDataSetChanged();
    }

    public void showNormalView() {
        mInSearch = false;
        mMediaListAdapter.setRows(mMediaList);
        mMediaListAdapter.notifyDataSetChanged();
    }

    HttpRequestListener<MediaList> mListener = new HttpRequestListener<MediaList>() {
        @Override
        public void onResponse(MediaList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mMediaList.clear();
            }

            mMediaList.addAll(response.getRows());
            mMediaListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mMediaList.size() && !mInSearch) {
                lvMedialistContent.setHasMoreData(false);
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

            lvMedialistContent.onPullRefreshComplete();
        }
    };

    HttpRequestListener<MediaList> mSearchListener = new HttpRequestListener<MediaList>() {
        @Override
        public void onResponse(MediaList response) {
            super.onResponse(response);

            if (mSearchCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mSearchMediaList.clear();
            }

            List<MediaList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mSearchCurrentPage = mSearchPrePage;
            }

            mSearchMediaList.addAll(response.getRows());
            mMediaListAdapter.notifyDataSetChanged();


            if (response.getTotal() <= mSearchMediaList.size() && mInSearch) {
                lvMedialistContent.setHasMoreData(false);
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
            lvMedialistContent.onPullRefreshComplete();
        }
    };

    @Override
    public int getContentLayoutResourceId() {
        return R.layout.view_media_list;
    }

    static class MediaListAdapter extends AbsBaseAdapter<MediaList.RowsEntity> {

        public MediaListAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_media, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            // TODO 初始化，时间实时刷新
            MediaList.RowsEntity row = mRows.get(position);
            String url = row.getX6_Product_Pic();

            AppLog.i(url);

            Glide.with(mContext).load(url).into(holder.image);

            String logo = row.getField_logo();
            Glide.with(mContext).load(logo).into(holder.logo);

            holder.name.setText(row.getField_XWFL());
            holder.introduction.setText(row.getField_MTJJ());

            holder.count.setText(mContext.getResources().getString(R.string.media_member_count, row.get_count()));

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_media_list_image)
            ImageView image;
            @Bind(R.id.iv_item_media_list_logo)
            ImageView logo;
            @Bind(R.id.iv_item_media_list_name)
            TextView name;
            @Bind(R.id.iv_item_media_list_introduction)
            TextView introduction;
            @Bind(R.id.iv_item_media_list_count)
            TextView count;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}

