package com.yunchengke.app.ui.activity.headline;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.yunchengke.app.bean.MediaMemberList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.MediaMemberListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.OnNestedClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 类名：MediaMemberActivity <br/>
 * 描述：媒体-所有成员
 * 创建时间：2016/01/11 23:51
 *
 * @author hanter
 * @version 1.0
 */
public class MediaMemberActivity extends BaseActivity {

    public final static String EXTRA_MEDIA_ID = "EXTRA_MEDIA_ID";

    @Bind(R.id.lv_media_member_content)
    PullToRefreshListView lvMediaMemberContent;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private long mMediaId;
    private MediaMemberListAdapter mMediaMemberListAdapter;
    private List<MediaMemberList.RowsEntity> mMediaMemberList;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_meadia_member, parent);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mMediaId = getIntent().getLongExtra(EXTRA_MEDIA_ID, 0);
        } else {
            mMediaId = savedInstanceState.getLong(EXTRA_MEDIA_ID);
        }

        lytTitle.setBackgroundResource(R.color.headline_background);
        setTitle(R.string.media_member);
        setTitleLeft(R.string.back);
        lvMediaMemberContent.setScrollLoadEnabled(true);

        lvMediaMemberContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
            }
        });

        mMediaMemberList = new ArrayList<MediaMemberList.RowsEntity>();
        mMediaMemberListAdapter = new MediaMemberListAdapter(this, new OnNestedClickListener() {
            @Override
            public void onNestedClickListener(int position, View view) {
                try {
                    UIHelper.gotoUserInfoActivity(MediaMemberActivity.this,
                            mMediaMemberList.get(position).getX6_Product_Admin());
                } catch (Exception e) {
                    // 捕捉异常
                }
            }
        });
        mMediaMemberListAdapter.setRows(mMediaMemberList);

        ListView listView = lvMediaMemberContent.getRefreshableView();
        listView.setAdapter(mMediaMemberListAdapter);
        listView.setDivider(getResources().getDrawable(R.color.topic_list_divider_color));
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.split_line_height));
        listView.setFooterDividersEnabled(true);

        lvMediaMemberContent.doPullRefreshing(true, 100);
    }

    private void refreshListView(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        AppLog.i("NewsListView", "刷新第" + mCurrentPage + "页");

        MediaMemberListRequest req = new MediaMemberListRequest(mListener) ;
        req.setRequestParams(mMediaId, mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_MEDIA_ID, mMediaId);
        super.onSaveInstanceState(outState);
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

    HttpRequestListener<MediaMemberList> mListener = new HttpRequestListener<MediaMemberList>() {
        @Override
        public void onResponse(MediaMemberList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mMediaMemberList.clear();
            }

            List<MediaMemberList.RowsEntity> row = response.getRows();

            // 是否未取到数据
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mMediaMemberList.addAll(response.getRows());
            mMediaMemberListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mMediaMemberList.size()) {
                lvMediaMemberContent.setHasMoreData(false);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);

            Toast.makeText(MediaMemberActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            // 错误处理
            mPullDownRefresh = false;
            mCurrentPage = mPrePage;
        }

        @Override
        public void onFinishRequest() {
            super.onFinishRequest();
            lvMediaMemberContent.onPullRefreshComplete();
        }
    };

    static class MediaMemberListAdapter extends AbsBaseAdapter<MediaMemberList.RowsEntity> {

        public MediaMemberListAdapter(Context context, OnNestedClickListener onNestedClickListener) {
            super(context, onNestedClickListener);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_media_member, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            MediaMemberList.RowsEntity row = getRows().get(position);
            holder.tvItemMediaMemberName.setText(row.getField_YHNC());

            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getX6_Product_Admin();

            Glide.with(mContext)
                    .load(url)
                    .into(holder.ivItemMediaMemberPortrait);

            holder.ivItemMediaMemberPortrait.setClickable(true);
            holder.ivItemMediaMemberPortrait.setTag(holder.ivItemMediaMemberPortrait.getId(), position);
            holder.ivItemMediaMemberPortrait.setOnClickListener(mOnNestedClickListener);

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_media_member_portrait)
            ImageView ivItemMediaMemberPortrait;
            @Bind(R.id.tv_item_media_member_name)
            TextView tvItemMediaMemberName;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
