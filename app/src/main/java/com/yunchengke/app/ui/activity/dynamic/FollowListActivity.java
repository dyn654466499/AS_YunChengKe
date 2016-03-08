package com.yunchengke.app.ui.activity.dynamic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.CommonRequestResult;
import com.yunchengke.app.bean.FollowList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.CancelFollowRequest;
import com.yunchengke.app.http.request.FollowListRequest;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.daemon.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 名称: FollowListActivity <br/>
 * 描述: 关注列表页面 <br/>
 * 创建时间：2016/1/28 15:49
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class FollowListActivity extends BaseActivity {
    public static final String EXTRA_USER_ID = "EXTRA_USER_ID";

    @Bind(R.id.lv_follow_list_content)
    PullToRefreshListView lvFollowListContent;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private long mUserId;
    private List<FollowList.RowsEntity> mFollowList;
    private FollowListAdapter mFollowListAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_follow_list, parent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_USER_ID, mUserId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mUserId = getIntent().getLongExtra(EXTRA_USER_ID, 0);
        } else {
            mUserId = savedInstanceState.getLong(EXTRA_USER_ID, 0);
        }

        lytTitle.setBackgroundResource(R.color.dynamic_title_background);
        setTitle(R.string.my_follow_title);
        setTitleLeft(R.string.back);

        mFollowList = new ArrayList<FollowList.RowsEntity>();
        mFollowListAdapter = new FollowListAdapter(this, new FollowListAdapter.Callback() {
            @Override
            public void onAdapterViewClickListener(int position, View v) {
                try {
                    CancelFollowRequest req = new CancelFollowRequest(new HttpRequestListener<CommonRequestResult>() {
                        @Override
                        public void onFinishRequest() {
                            super.onFinishRequest();

                            lvFollowListContent.doPullRefreshing(true, 100);
                        }

                        @Override
                        public void onResponse(CommonRequestResult response) {
                            super.onResponse(response);

                            Toast.makeText(FollowListActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    req.setCancelFollowRequest(mFollowList.get(position).getX6_Admin_Id());
                    HttpRequestQueue.addToRequestQueue(req);
                } catch (Exception e) {
                    // 捕获异常
                }
            }
        });
        mFollowListAdapter.setRows(mFollowList);

        ListView listView = lvFollowListContent.getRefreshableView();
        listView.setAdapter(mFollowListAdapter);

        lvFollowListContent.setScrollLoadEnabled(true);
        lvFollowListContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
            }
        });

        lvFollowListContent.doPullRefreshing(true, 100);
    }

    private void refreshListView(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        FollowListRequest req = new FollowListRequest(mListener) ;
        req.setRequestParams(mUserId, mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    HttpRequestListener<FollowList> mListener = new HttpRequestListener<FollowList>() {
        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            lvFollowListContent.onPullRefreshComplete();
        }

        @Override
        public void onResponse(FollowList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mFollowList.clear();
            }

            List<FollowList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mFollowList.addAll(response.getRows());
            mFollowListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mFollowList.size()) {
                lvFollowListContent.setHasMoreData(false);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            default:
                super.onClick(v);
                break;
        }
    }

    public static class FollowListAdapter extends AbsBaseAdapter<FollowList.RowsEntity> implements View.OnClickListener {

        private Callback mCallback;

        @Override
        public void onClick(View v) {
            Integer position = (Integer) v.getTag(v.getId());
            mCallback.onAdapterViewClickListener(position,v);
        }

        interface Callback {
            void onAdapterViewClickListener(int position, View v);
        }

        public FollowListAdapter(Context context, Callback callback) {
            super(context);
            this.mCallback = callback;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_follow, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            FollowList.RowsEntity row = getItem(position);
            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getX6_Admin_Id();
            Glide.with(mContext).load(url).into(holder.ivItemFollowPortrait);
            holder.tvItemFollowName.setText(row.getX6_Product_Admin());


            holder.btnItemFollowCancel.setTag(holder.btnItemFollowCancel.getId(), position);
            holder.btnItemFollowCancel.setOnClickListener(this);

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_follow_portrait)
            CircleImageView ivItemFollowPortrait;
            @Bind(R.id.tv_item_follow_name)
            TextView tvItemFollowName;
            @Bind(R.id.btn_item_follow_cancel)
            Button btnItemFollowCancel;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
