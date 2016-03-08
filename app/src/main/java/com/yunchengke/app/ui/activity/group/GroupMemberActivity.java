package com.yunchengke.app.ui.activity.group;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.GroupMemberList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.GroupMemberListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.OnNestedClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 名称: GroupMemberActivity <br/>
 * 描述: 小组成员 <br/>
 * 创建时间：2016/1/14 14:17
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class GroupMemberActivity extends BaseActivity {
    public static final String EXTRA_GROUP_ID = "EXTRA_GROUP_ID";

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private long mGroupId;
    private List<GroupMemberList.RowsEntity> mGroupMemberList;
    private GroupMemberListAdapter mGroupMemberListAdapter;

    @Bind(R.id.lv_group_member_content)
    PullToRefreshListView lvGroupMemberContent;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_group_member, parent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_GROUP_ID, mGroupId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mGroupId = getIntent().getLongExtra(EXTRA_GROUP_ID, 0);
        } else {
            mGroupId = savedInstanceState.getLong(EXTRA_GROUP_ID, 0);
        }

        lytTitle.setBackgroundResource(R.color.group_background);
        setTitle(R.string.group_member);
        setTitleLeft(R.string.back);

        lvGroupMemberContent.setScrollLoadEnabled(true);

        lvGroupMemberContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
            }
        });

        mGroupMemberList = new ArrayList<GroupMemberList.RowsEntity>();
        mGroupMemberListAdapter = new GroupMemberListAdapter(this, new OnNestedClickListener() {
            @Override
            public void onNestedClickListener(int position, View view) {
                try {
                    UIHelper.gotoUserInfoActivity(GroupMemberActivity.this,
                            mGroupMemberList.get(position).getX6_Product_Admin());
                } catch (Exception e) {
                    // 捕捉异常
                }
            }
        });
        mGroupMemberListAdapter.setRows(mGroupMemberList);

        ListView listView = lvGroupMemberContent.getRefreshableView();
        listView.setAdapter(mGroupMemberListAdapter);
        listView.setDivider(getResources().getDrawable(R.color.topic_list_divider_color));
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.split_line_height));
        listView.setFooterDividersEnabled(true);

        lvGroupMemberContent.doPullRefreshing(true, 100);
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

        GroupMemberListRequest req = new GroupMemberListRequest(mListener) ;
        req.setRequestParams(mGroupId, mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

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

    HttpRequestListener<GroupMemberList> mListener = new HttpRequestListener<GroupMemberList>() {
        @Override
        public void onResponse(GroupMemberList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mGroupMemberList.clear();
            }

            List<GroupMemberList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mGroupMemberList.addAll(response.getRows());
            mGroupMemberListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mGroupMemberList.size()) {
                lvGroupMemberContent.setHasMoreData(false);
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
            lvGroupMemberContent.onPullRefreshComplete();
        }
    };


    static class GroupMemberListAdapter extends AbsBaseAdapter<GroupMemberList.RowsEntity> {

        public GroupMemberListAdapter(Context context, OnNestedClickListener onNestedClickListener) {
            super(context, onNestedClickListener);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_group_member, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            GroupMemberList.RowsEntity row = getRows().get(position);
            holder.ivItemGroupMemberName.setText(row.getField_YHNC());

            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST +row.getX6_Product_Admin();

            AppLog.e("更新URL：" + url);

            Glide.with(mContext)
                    .load(url)
                    .into(holder.ivItemGroupMemberPortrait);

            holder.ivItemGroupMemberPortrait.setTag(holder.ivItemGroupMemberPortrait.getId(), position);
            holder.ivItemGroupMemberPortrait.setClickable(true);
            holder.ivItemGroupMemberPortrait.setOnClickListener(mOnNestedClickListener);

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_group_member_portrait)
            ImageView ivItemGroupMemberPortrait;
            @Bind(R.id.iv_item_group_member_name)
            TextView ivItemGroupMemberName;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
