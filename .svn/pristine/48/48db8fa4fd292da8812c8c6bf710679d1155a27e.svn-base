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
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.CommonRequestResult;
import com.yunchengke.app.bean.FunsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.CancelFollowRequest;
import com.yunchengke.app.http.request.FansListRequest;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.daemon.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 名称: FansListActivity <br/>
 * 描述: 粉丝列表 <br/>
 * 创建时间：2016/1/28 15:47
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class FansListActivity extends BaseActivity {
    public static final String EXTRA_USER_ID = "EXTRA_USER_ID";

    @Bind(R.id.lv_funs_list_content)
    PullToRefreshListView lvFunsListContent;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private long mUserId;
    private List<FunsList.RowsEntity> mFunsList;
    private FunsListAdapter mFunsListAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_funs_list, parent);
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
        setTitle(R.string.my_fans_title);
        setTitleLeft(R.string.back);

        mFunsList = new ArrayList<FunsList.RowsEntity>();
        mFunsListAdapter = new FunsListAdapter(this, new FunsListAdapter.Callback() {
            @Override
            public void onAdapterViewClickListener(int position, View v) {
                try {
                    CancelFollowRequest req = new CancelFollowRequest(new HttpRequestListener<CommonRequestResult>() {
                        @Override
                        public void onFinishRequest() {
                            super.onFinishRequest();
                        }

                        @Override
                        public void onResponse(CommonRequestResult response) {
                            super.onResponse(response);

                            lvFunsListContent.doPullRefreshing(true, 100);
                            Toast.makeText(FansListActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });
                    req.setRemoveFansRequest(mFunsList.get(position).getX6_Admin_Id());
                    HttpRequestQueue.addToRequestQueue(req);
                } catch (Exception e) {
                    // 捕获异常
                }
            }
        });
        mFunsListAdapter.setRows(mFunsList);

        ListView listView = lvFunsListContent.getRefreshableView();
        listView.setAdapter(mFunsListAdapter);

        lvFunsListContent.setScrollLoadEnabled(true);
        lvFunsListContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
            }
        });

        lvFunsListContent.doPullRefreshing(true, 100);
    }

    private void refreshListView(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        FansListRequest req = new FansListRequest(mListener) ;
        req.setRequestParams(mUserId, mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    HttpRequestListener<FunsList> mListener = new HttpRequestListener<FunsList>() {
        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            lvFunsListContent.onPullRefreshComplete();
        }

        @Override
        public void onResponse(FunsList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mFunsList.clear();
            }

            List<FunsList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mFunsList.addAll(response.getRows());
            mFunsListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mFunsList.size()) {
                lvFunsListContent.setHasMoreData(false);
            }

        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);

            AppLog.e(error.getMessage());

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

    public static class FunsListAdapter extends AbsBaseAdapter<FunsList.RowsEntity> implements View.OnClickListener {

        private Callback mCallback;

        interface Callback {
            void onAdapterViewClickListener(int position, View v);
        }

        public FunsListAdapter(Context context, Callback callback) {
            super(context);
            this.mCallback = callback;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fans, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            FunsList.RowsEntity row = getItem(position);
            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getX6_Admin_Id();
            Glide.with(mContext).load(url).into(holder.ivItemFansPortrait);
            holder.tvItemFansName.setText(row.getX6_Product_Admin());

            holder.btnItemFansRemove.setTag(holder.btnItemFansRemove.getId(), position);
            holder.btnItemFansRemove.setOnClickListener(this);

            return convertView;
        }

        @Override
        public void onClick(View v) {
            Integer position = (Integer) v.getTag(v.getId());
            mCallback.onAdapterViewClickListener(position, v);
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_fans_portrait)
            CircleImageView ivItemFansPortrait;
            @Bind(R.id.tv_item_fans_name)
            TextView tvItemFansName;
            @Bind(R.id.btn_item_fans_remove)
            Button btnItemFansRemove;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
