package com.yunchengke.app.ui.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.yunchengke.app.bean.GroupDetail;
import com.yunchengke.app.bean.JoinGroupResult;
import com.yunchengke.app.bean.TopicList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.GroupDetailRequest;
import com.yunchengke.app.http.request.JoinGroupRequest;
import com.yunchengke.app.http.request.TopicListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.TopicListView;
import com.yunchengke.app.utils.ShareUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名：GroupDetailActivity <br/>
 * 描述：小组详情
 * 创建时间：2016/01/10 1:21
 *
 * @author hanter
 * @version 1.0
 */
public class GroupDetailActivity extends BaseActivity {

    public static final int REQUEST_CREATE_TOPIC = 1;

    public static final String EXTRA_GROUP_ID = "EXTRA_GROUP_ID";

    @Bind(R.id.iv_group_detail_background)
    ImageView ivGroupDetailBackground;
    @Bind(R.id.iv_group_detail_logo)
    ImageView ivGroupDetailLogo;
    @Bind(R.id.tv_group_detail_title)
    TextView tvGroupDetailTitle;
    @Bind(R.id.tv_group_detail_creator)
    TextView tvGroupDetailCreator;
    @Bind(R.id.tv_group_detail_member_count)
    TextView tvGroupDetailMemberCount;
    @Bind(R.id.tv_group_detail_member_detail)
    TextView tvGroupDetailMemberDetail;
    @Bind(R.id.tv_group_detail_content)
    TextView tvGroupDetailContent;
    @Bind(R.id.lyt_group_detail_content)
    LinearLayout lytGroupDetailContent;
    @Bind(R.id.btn_group_detail_create_topic)
    Button btnGroupDetailCreateTopic;
    @Bind(R.id.btn_group_detail_join)
    Button btnGroupDetailJoin;
    @Bind(R.id.lv_group_detail_topics)
    PullToRefreshListView lvGroupDetailTopics;

    private long mGroupId;

   private GroupDetail.RowsEntity mDetailGroup;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private List<TopicList.RowsEntity> mTopicList;
    private TopicListView.TopicListAdapter mTopicListAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        View rootView = inflate(R.layout.activity_group_detail, parent);

        lvGroupDetailTopics = (PullToRefreshListView) rootView.findViewById(R.id.lv_group_detail_topics);
        ListView listView = lvGroupDetailTopics.getRefreshableView();
        listView.addHeaderView(inflate(R.layout.layout_group_detail_header, listView), null, false);

        return rootView;
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
        setTitle(R.string.group_detail);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.share);

        mTopicList = new ArrayList<TopicList.RowsEntity>();
        mTopicListAdapter = new TopicListView.TopicListAdapter(this);
        mTopicListAdapter.setShowPortrait(true);
        mTopicListAdapter.setRows(mTopicList);
        lvGroupDetailTopics.getRefreshableView().setAdapter(mTopicListAdapter);

        lvGroupDetailTopics.setPullRefreshEnabled(false);
        lvGroupDetailTopics.setScrollLoadEnabled(true);
        lvGroupDetailTopics.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                updateTopicList(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                updateTopicList(false);
            }
        });

        lvGroupDetailTopics.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    // 这个ListView有Header，所以要 -1 。
                    if (position < 1) {
                        return;
                    }

                    TopicList.RowsEntity row = mTopicList.get(position - 1);
                    UIHelper.gotoTopicDetailActivity(GroupDetailActivity.this, row.getX6_Product_Id());
                } catch (Exception e) {
                    // 捕获异常
                }
            }
        });

        GroupDetailRequest req = new GroupDetailRequest(new HttpRequestListener<GroupDetail>() {

            @Override
            public void onResponse(GroupDetail response) {
                super.onResponse(response);

                mDetailGroup = response.getRows().get(0);

                Glide.with(GroupDetailActivity.this).load(mDetailGroup.getX6_Product_Pic()).into(ivGroupDetailBackground);
                Glide.with(GroupDetailActivity.this).load(mDetailGroup.getField_Pic()).into(ivGroupDetailLogo);

                tvGroupDetailTitle.setText(mDetailGroup.getField_XZBT());

                String creator = getResources().getString(R.string.group_from_creator, mDetailGroup.getField_XZCJR());
                tvGroupDetailCreator.setText(creator);

                String member = getResources().getString(R.string.group_member_count, mDetailGroup.getField_YHSL());
                tvGroupDetailMemberCount.setText(member);

                tvGroupDetailContent.setText(mDetailGroup.getField_XZJJ());

                if (mDetailGroup.getIStatus() == 0) {
                    btnGroupDetailJoin.setEnabled(true);
                    btnGroupDetailJoin.setText("加入");

                    btnGroupDetailCreateTopic.setVisibility(View.GONE);
                } else {
                    btnGroupDetailJoin.setEnabled(false);
                    btnGroupDetailJoin.setText("已加入");

                    btnGroupDetailCreateTopic.setVisibility(View.VISIBLE);
                }
            }
        });

        req.setRequestParams(mGroupId);
        HttpRequestQueue.addToRequestQueue(req);

        lvGroupDetailTopics.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateTopicList(true);
            }
        }, 50);
    }

    public void updateTopicList(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        AppLog.i("刷新第" + mCurrentPage + "页");

        TopicListRequest req = new TopicListRequest(mListener);
        req.setRequestParams(mGroupId, mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
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

            // 是否未取到数据
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mTopicList.addAll(response.getRows());

            mTopicListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mTopicList.size()) {
                lvGroupDetailTopics.setScrollLoadEnabled(true);
                lvGroupDetailTopics.setHasMoreData(false);
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

            lvGroupDetailTopics.onPullRefreshComplete();
        }
    };

    @Override
    @OnClick({R.id.tv_group_detail_member_detail, R.id.btn_group_detail_join, R.id.btn_group_detail_create_topic})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            case R.id.tv_group_detail_member_detail:
                UIHelper.gotoGroupMemberActivity(GroupDetailActivity.this, mGroupId);
                break;

            case R.id.btn_group_detail_join:
                joinGroup();
                break;

            case R.id.btn_group_detail_create_topic:
                UIHelper.gotoCreateTopicActivity(GroupDetailActivity.this, mGroupId);
                break;
            case R.id.tv_base_title_right:
                Log.d("syb","mDetailGroup= "+mDetailGroup.getX6_Product_Pic());
                ShareUtil.shareInit(this,"5", mDetailGroup.getField_XZBT(),mDetailGroup.getField_XZJJ(),mDetailGroup.getX6_Product_Pic(),"483",mDetailGroup.getX6_Product_Id());
                break;

            default:
                super.onClick(view);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CREATE_TOPIC) {
            updateTopicList(true);
        }
    }

    public void joinGroup() {
        JoinGroupRequest req = new JoinGroupRequest(new HttpRequestListener<JoinGroupResult>() {
            @Override
            public void onResponse(JoinGroupResult response) {
                super.onResponse(response);

                Toast.makeText(GroupDetailActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                if (CommonRequestResult.RESULT_SUCCESS.equals(response.getResultState())) {


                    if (mDetailGroup != null) {
                        mDetailGroup.setIStatus(1);
                    }

                    btnGroupDetailJoin.setEnabled(false);
                    btnGroupDetailJoin.setText("已加入");

                    btnGroupDetailCreateTopic.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });

        req.setRequestParams(mGroupId);
        HttpRequestQueue.addToRequestQueue(req);
    }

}
