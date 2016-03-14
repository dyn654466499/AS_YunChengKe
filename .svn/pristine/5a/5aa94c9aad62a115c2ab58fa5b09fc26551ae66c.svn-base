package com.yunchengke.app.ui.activity.headline;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshExpandableListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.GroupedPastNews;
import com.yunchengke.app.bean.NewsDetail;
import com.yunchengke.app.bean.PastNewsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.PastNewsListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 类名：PastNewsActivity <br/>
 * 描述：往期新闻
 * 创建时间：2016/01/19 22:36
 *
 * @author hanter
 * @version 1.0
 */
public class PastNewsActivity extends BaseActivity {
    public static final String EXTRA_MEDIA_ID = "EXTRA_MEDIA_ID";

    @Bind(R.id.lv_past_news_content)
    PullToRefreshExpandableListView lvPastNewsContent;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private long mMediaId;

    List<PastNewsList.RowsEntity> mPastNewsList;
    List<GroupedPastNews> mGroupedPastNewsList;
//    GroupedPastNewsListAdapter mGroupedPastNewsListAdapter;

    SampleExpandableListAdapter mGroupedPastNewsListAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_past_news, parent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_MEDIA_ID, mMediaId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mMediaId = getIntent().getLongExtra(EXTRA_MEDIA_ID, 0);
        } else {
            mMediaId = savedInstanceState.getLong(EXTRA_MEDIA_ID, 0);
        }

        lytTitle.setBackgroundResource(R.color.headline_background);
        setTitle(R.string.media_past_news_title);
        setTitleLeft(R.string.back);

        lvPastNewsContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ExpandableListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                refreshListView(false);
            }
        });

        lvPastNewsContent.setScrollLoadEnabled(true);

        ExpandableListView listView = lvPastNewsContent.getRefreshableView();

        mPastNewsList = new ArrayList<PastNewsList.RowsEntity>();
        mGroupedPastNewsList = new ArrayList<GroupedPastNews>();

//        mGroupedPastNewsListAdapter = new GroupedPastNewsListAdapter(this);
//        mGroupedPastNewsListAdapter.setRows(mGroupedPastNewsList);
//        listView.setAdapter(mGroupedPastNewsListAdapter);

        mGroupedPastNewsListAdapter = new SampleExpandableListAdapter(this);
        mGroupedPastNewsListAdapter.setGroupedPastNewsList(mGroupedPastNewsList);
        listView.setAdapter(mGroupedPastNewsListAdapter);


        listView.setDividerHeight(0);
        listView.setHeaderDividersEnabled(true);
        listView.setGroupIndicator(getResources().getDrawable(R.color.transparent));

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return parent.isGroupExpanded(groupPosition);
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {

                PastNewsList.RowsEntity rowPastNews = (PastNewsList.RowsEntity) mGroupedPastNewsListAdapter.getChild(groupPosition, childPosition);

                NewsDetail.RowsEntity row = new NewsDetail.RowsEntity();

                row.setField_XWBT(rowPastNews.getField_XWBT());
                row.setField_XWCJR(rowPastNews.getField_XWCJR());
                row.setField_XWNR(rowPastNews.getField_XWJJ());
                row.setX6_Product_Id(rowPastNews.getX6_Product_Id());
                row.setX6_Product_Pic(rowPastNews.getX6_Product_Pic());
                row.setX6_Product_Time(rowPastNews.getX6_Product_Time());

                UIHelper.gotoNewsDetailActivity(PastNewsActivity.this, row);

                return false;
            }
        });

        lvPastNewsContent.doPullRefreshing(true, 100);
    }

    private void refreshListView(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        PastNewsListRequest req = new PastNewsListRequest(mListener);
        req.setRequestParams(mMediaId, mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private HttpRequestListener<PastNewsList> mListener = new HttpRequestListener<PastNewsList>() {
        @Override
        public void onResponse(PastNewsList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mPastNewsList.clear();
            }

            mPastNewsList.addAll(response.getRows());

            groupPastNewsList();

            mGroupedPastNewsListAdapter.notifyDataSetChanged();

            expandListView();

            if (response.getTotal() <= mPastNewsList.size()) {
                lvPastNewsContent.setHasMoreData(false);
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

            lvPastNewsContent.onPullRefreshComplete();
        }
    };

    private void groupPastNewsList() {

        mGroupedPastNewsList.clear();

        for (PastNewsList.RowsEntity row : mPastNewsList) {

            boolean hasGroup = false;

            for (GroupedPastNews group : mGroupedPastNewsList) {
                if (group.getField_XWQS() == row.getField_XWQS()) {
                    hasGroup = true;
                    group.getPastNewsList().add(row);
                    break;
                }
            }

            if (!hasGroup) {
                // 创建分期，并添加到分期列表里面
                GroupedPastNews newGroup = new GroupedPastNews();

                newGroup.setField_FMTP(row.getField_FMTP());
                newGroup.setField_XWBK(row.getField_XWBK());
                newGroup.setField_XWCKRQ(row.getField_XWCKRQ());
                newGroup.setField_XWKM(row.getField_XWKM());
                newGroup.setField_XWLM(row.getField_XWLM());
                newGroup.setField_XWQS(row.getField_XWQS());

                List<PastNewsList.RowsEntity> pastNewsList = new ArrayList<PastNewsList.RowsEntity>();
                pastNewsList.add(row);

                newGroup.setPastNewsList(pastNewsList);

                mGroupedPastNewsList.add(newGroup);
            }


        }


    }

    /**
     * 展开所有的ListView
     */
    private void expandListView() {
        for(int i = 0; i < mGroupedPastNewsListAdapter.getGroupCount(); i++)
            lvPastNewsContent.getRefreshableView().expandGroup(i);
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

    public static class SampleExpandableListAdapter extends BaseExpandableListAdapter {
        private Context mContext;
        private List<GroupedPastNews> mGroupedPastNewsList;

        public SampleExpandableListAdapter(Context context) {
            this.mContext = context;
        }

        public Object getChild(int groupPosition, int childPosition) {
            return mGroupedPastNewsList.get(groupPosition).getPastNewsList().get(childPosition);
        }

        public long getChildId(int groupPosition, int childPosition) {
            return groupPosition << 32 + childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return mGroupedPastNewsList.get(groupPosition).getPastNewsList().size();
        }

        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sub_past_news, parent, false);
                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            PastNewsList.RowsEntity row = (PastNewsList.RowsEntity) getChild(groupPosition, childPosition);

            String imageUrl = row.getX6_Product_Pic();
            Glide.with(mContext).load(imageUrl).into(holder.image);

            holder.title.setText(row.getField_XWBT());

            String introduction = mContext.getResources().getString(R.string.news_introduction, row.getField_XWJJ());
            holder.introduction.setText(introduction);

            String creator = mContext.getResources().getString(R.string.past_news_creator,
                    row.getField_XWCJR());
            holder.creator.setText(creator);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.split.getLayoutParams();
            if (isLastChild) {
                params.setMargins(0, 0, 0, 0);
            } else {
                int marginLeft = mContext.getResources().getDimensionPixelSize(R.dimen.activity_margin_left);
                int marginRight = mContext.getResources().getDimensionPixelSize(R.dimen.activity_margin_right);
                params.setMargins(marginLeft, 0, marginRight, 0);
            }

            return convertView;

        }

        public Object getGroup(int groupPosition) {
            return mGroupedPastNewsList.get(groupPosition);
        }

        public int getGroupCount() {
            return mGroupedPastNewsList.size();
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {

            GroupViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_common_media_past, parent, false);
                holder = new GroupViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (GroupViewHolder) convertView.getTag();
            }

            GroupedPastNews row = (GroupedPastNews) getGroup(groupPosition);

            holder.tvItemMediaPastPeriod.setText(mContext.getResources().getString(R.string.media_news_period_number, row.getField_XWQS()));

            String url = row.getField_FMTP();

            Glide.with(mContext).load(url).into(holder.ivItemMediaPastImage);

            return convertView;
        }

        public boolean hasStableIds() {
            return false;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public void setGroupedPastNewsList(List<GroupedPastNews> mGroupedPastNewsList) {
            this.mGroupedPastNewsList = mGroupedPastNewsList;
        }

        static class GroupViewHolder {
            @Bind(R.id.tv_item_media_past_period)
            TextView tvItemMediaPastPeriod;
            @Bind(R.id.iv_item_media_past_image)
            ImageView ivItemMediaPastImage;

            GroupViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
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

            @Bind(R.id.split_line)
            View split;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
