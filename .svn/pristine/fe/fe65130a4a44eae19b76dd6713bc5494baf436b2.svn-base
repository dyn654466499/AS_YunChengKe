package com.yunchengke.app.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.GroupList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.GroupListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.GroupListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 类名：GroupListView <br/>
 * 描述：小组列表
 * 创建时间：2016/01/07 21:58
 *
 * @author hanter
 * @version 1.0
 */
public class GroupListView extends BaseCompoundView {

    @Bind(R.id.btn_grouplist_new)
    Button btnGrouplistNew;
    @Bind(R.id.lv_grouplist_content)
    PullToRefreshListView lvGrouplistContent;

    private boolean mInSearch;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private String mKeyword;
    private int mSearchPrePage;
    private int mSearchCurrentPage;

    private List<GroupList.RowsEntity> mGroupList;
    private List<GroupList.RowsEntity> mSearchGroupList;
    private GroupListAdapter mGroupListAdapter;

    private int mCategoryId;

    public GroupListView(Context context) {
        super(context);
    }

    public GroupListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GroupListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getContentLayoutResourceId() {
        return R.layout.view_group;
    }

    @Override
    protected void initViews() {
        lvGrouplistContent = (PullToRefreshListView) findViewById(R.id.lv_grouplist_content);
        lvGrouplistContent.setScrollLoadEnabled(true);

        lvGrouplistContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
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

        ListView listView = lvGrouplistContent.getRefreshableView();
        listView.setSelector(R.drawable.bg_common_item);
        listView.setDivider(getResources().getDrawable(R.color.comment_list_divider_color));
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.split_line_height));

        btnGrouplistNew.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.gotoSelectCategoryActivity(getContext());
            }
        });

        mGroupList = new ArrayList<GroupList.RowsEntity>();
        mGroupListAdapter = new GroupListAdapter(getContext());
        mGroupListAdapter.setRows(mGroupList);
        listView.setAdapter(mGroupListAdapter);

        mSearchGroupList = new ArrayList<GroupList.RowsEntity>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    GroupList.RowsEntity row = mGroupList.get(position);
                    long groupId = row.getX6_Product_Id();
                    UIHelper.gotoGroupDetailActivity(getContext(), groupId);
                } catch (Exception e ) {
                    // 异常捕捉
                }
            }
        });

        lvGrouplistContent.doPullRefreshing(true, 150);
    }

    HttpRequestListener<GroupList> mListener = new HttpRequestListener<GroupList>() {

        @Override
        public void onResponse(GroupList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;
                mGroupList.clear();
            }

            List<GroupList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mGroupList.addAll(response.getRows());
            mGroupListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mGroupList.size() && !mInSearch) {
                lvGrouplistContent.setHasMoreData(false);
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
            lvGrouplistContent.onPullRefreshComplete();
        }
    };

    HttpRequestListener<GroupList> mSearchListener = new HttpRequestListener<GroupList>() {

        @Override
        public void onResponse(GroupList response) {
            super.onResponse(response);

            if (mSearchCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mSearchGroupList.clear();
            }

            List<GroupList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mSearchCurrentPage = mSearchPrePage;
            }

            mSearchGroupList.addAll(response.getRows());
            mGroupListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mSearchGroupList.size() && mInSearch) {
                lvGrouplistContent.setHasMoreData(false);
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
            lvGrouplistContent.onPullRefreshComplete();
        }
    };

    public void searchKeyword(String keyword) {
        mKeyword = keyword;

        showSearchView();

        lvGrouplistContent.doPullRefreshing(true, 100);
    }

    public void showSearchView() {
        mInSearch = true;

        btnGrouplistNew.setVisibility(View.GONE);

        mGroupListAdapter.setRows(mSearchGroupList);
        mGroupListAdapter.notifyDataSetChanged();
    }

    public void showNormalView() {
        mInSearch = false;

        btnGrouplistNew.setVisibility(View.VISIBLE);

        mGroupListAdapter.setRows(mGroupList);
        mGroupListAdapter.notifyDataSetChanged();
    }

    private void refreshListView(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        AppLog.i("GroupListView", "刷新第" + mCurrentPage + "页");

        GroupListRequest req = new GroupListRequest(mListener);
        req.setRequestParams(0, mCurrentPage);
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

        AppLog.i("NewsListView", "刷新第" + mSearchCurrentPage + "页");

        GroupListRequest req = new GroupListRequest(mSearchListener);
        req.setSearchParams(0, mSearchCurrentPage, mKeyword);
        HttpRequestQueue.addToRequestQueue(req);
    }
    
    public void refresh() {
        lvGrouplistContent.doPullRefreshing(true, 50);
    }

    /**
     * 设置分类ID，默认值0
     * @param categoryId 分类ID
     */
    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }
}
