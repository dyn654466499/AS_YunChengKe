package com.yunchengke.app.ui.activity.headline;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshExpandableListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.FollowMediaResult;
import com.yunchengke.app.bean.GroupedPastNews;
import com.yunchengke.app.bean.InstantNewsList;
import com.yunchengke.app.bean.MediaList;
import com.yunchengke.app.bean.NewsDetail;
import com.yunchengke.app.bean.NewsList;
import com.yunchengke.app.bean.PastNewsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.FollowMediaRequest;
import com.yunchengke.app.http.request.InstantNewsListRequest;
import com.yunchengke.app.http.request.MediaListRequest;
import com.yunchengke.app.http.request.NewsDetailRequest;
import com.yunchengke.app.http.request.NewsListRequest;
import com.yunchengke.app.http.request.PastNewsListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.utils.DateTimeUtils;
import com.yunchengke.app.utils.ShareUtil;
import com.yunchengke.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名：MediaDetailActivity <br/>
 * 描述：媒体详情
 * 创建时间：2016/01/11 23:400
 *
 * @author hanter
 * @version 1.0
 */
public class MediaDetailActivity extends BaseActivity {

    public final static String EXTRA_MEDIA_INFO = "EXTRA_MEDIA_INFO";
    public final static String EXTRA_MEDIA_ID = "EXTRA_MEDIA_ID";

    @Bind(R.id.iv_media_detail_background)
    ImageView ivMediaDetailBackground;
    @Bind(R.id.tv_media_detail_title)
    TextView tvMediaDetailTitle;
    @Bind(R.id.tv_media_detail_creator)
    TextView tvMediaDetailCreator;
    @Bind(R.id.tv_media_detail_member_count)
    TextView tvMediaDetailMemberCount;
    @Bind(R.id.tv_media_detail_member_detail)
    TextView tvMediaDetailMemberDetail;
    @Bind(R.id.tv_media_detail_content)
    TextView tvMediaDetailContent;
    @Bind(R.id.lyt_media_detail_content)
    LinearLayout lytMediaDetailContent;
    @Bind(R.id.btn_media_detail_follow)
    Button btnMediaDetailFollow;
    @Bind(R.id.plv_media_detail_content)
    PullToRefreshExpandableListView plvMediaDetailContent;

    @Bind(R.id.lyt_media_detail_live)
    RelativeLayout lytMediaDetailLive;
    @Bind(R.id.iv_item_sub_past_news_image_live)
    ImageView ivItemSubPastNewsImageLive;
    @Bind(R.id.tv_item_sub_past_news_title_live)
    TextView tvItemSubPastNewsTitleLive;
    @Bind(R.id.tv_item_sub_past_news_introduction_live)
    TextView tvItemSubPastNewsIntroductionLive;
    @Bind(R.id.tv_item_sub_past_news_creator_live)
    TextView tvItemSubPastNewsCreatorLive;
    @Bind(R.id.content_live)
    LinearLayout contentLive;
    @Bind(R.id.lyt_media_detail_first_live)
    RelativeLayout lytMediaDetailFirstLive;
    @Bind(R.id.lyt_media_detail_instant)
    RelativeLayout lytMediaDetailInstant;
    @Bind(R.id.iv_item_sub_past_news_image_instant)
    ImageView ivItemSubPastNewsImageInstant;
    @Bind(R.id.tv_item_sub_past_news_title_instant)
    TextView tvItemSubPastNewsTitleInstant;
    @Bind(R.id.tv_item_sub_past_news_introduction_instant)
    TextView tvItemSubPastNewsIntroductionInstant;
    @Bind(R.id.tv_item_sub_past_news_creator_instant)
    TextView tvItemSubPastNewsCreatorInstant;
    @Bind(R.id.content_instant)
    LinearLayout contentInstant;
    @Bind(R.id.lyt_media_detail_first_instant)
    RelativeLayout lytMediaDetailFirstInstant;
    @Bind(R.id.lyt_media_detail_past)
    RelativeLayout lytMediaDetailPast;
    @Bind(R.id.iv_item_sub_past_news_image_past)
    ImageView ivItemSubPastNewsImagePast;
    @Bind(R.id.tv_item_sub_past_news_title_past)
    TextView tvItemSubPastNewsTitlePast;
    @Bind(R.id.tv_item_sub_past_news_introduction_past)
    TextView tvItemSubPastNewsIntroductionPast;
    @Bind(R.id.tv_item_sub_past_news_creator_past)
    TextView tvItemSubPastNewsCreatorPast;
    @Bind(R.id.content_past)
    LinearLayout contentPast;
    @Bind(R.id.lyt_media_detail_first_past)
    RelativeLayout lytMediaDetailFirstPast;

    private long mMediaId;
    private MediaList.RowsEntity mMediaInfo;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数


    private InstantNewsList.RowsEntity mFirstLiveNews;
    private InstantNewsList.RowsEntity mFirstInstantNews;

    List<PastNewsList.RowsEntity> mPastNewsList;
    List<GroupedPastNews> mGroupedPastNewsList;
    PastNewsActivity.SampleExpandableListAdapter mGroupedPastNewsListAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        View rootView = inflate(R.layout.activity_media_detail, parent);

        PullToRefreshExpandableListView pullToRefreshExpandableListView =
                (PullToRefreshExpandableListView) rootView.findViewById(R.id.plv_media_detail_content);

        ListView listView = pullToRefreshExpandableListView.getRefreshableView();
        listView.addHeaderView(inflate(R.layout.layout_media_detail_header, listView),
                null, false);

        return rootView;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mMediaId = getIntent().getLongExtra(EXTRA_MEDIA_ID, 0);
            mMediaInfo = (MediaList.RowsEntity) getIntent().getSerializableExtra(EXTRA_MEDIA_INFO);
        } else {
            mMediaId = savedInstanceState.getLong(EXTRA_MEDIA_ID, 0);
            mMediaInfo = (MediaList.RowsEntity) savedInstanceState.getSerializable(EXTRA_MEDIA_INFO);
        }

        lytTitle.setBackgroundResource(R.color.headline_background);
        setTitle(R.string.media_detail);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.share);

        plvMediaDetailContent.setPullRefreshEnabled(false);
        plvMediaDetailContent.setScrollLoadEnabled(true);

        ExpandableListView listView = plvMediaDetailContent.getRefreshableView();
        mPastNewsList = new ArrayList<PastNewsList.RowsEntity>();
        mGroupedPastNewsList = new ArrayList<GroupedPastNews>();
        mGroupedPastNewsListAdapter = new PastNewsActivity.SampleExpandableListAdapter(this);
        mGroupedPastNewsListAdapter.setGroupedPastNewsList(mGroupedPastNewsList);
        listView.setAdapter(mGroupedPastNewsListAdapter);

        listView.setDividerHeight(0);
        listView.setHeaderDividersEnabled(true);
        listView.setGroupIndicator(getResources().getDrawable(R.color.transparent));

        plvMediaDetailContent.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ExpandableListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                refreshListView(false);
            }
        });

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

                UIHelper.gotoNewsDetailActivity(MediaDetailActivity.this, row);

                return false;
            }
        });

        if (mMediaInfo != null) {
            mMediaId = mMediaInfo.getX6_Product_Id();

            refreshMediaDetail();

           refreshListView(true);

           updateFirstLiveNews();
            updateFirstInstantNews();
        } else {
            refreshMediaDetail();
        }

    }

    private void updateFirstLiveNews() {
        InstantNewsListRequest req = new InstantNewsListRequest(new HttpRequestListener<InstantNewsList>() {
            @Override
            public void onResponse(InstantNewsList response) {
                super.onResponse(response);

                try {
                    if (response.getRows().size() == 0) {
                        lytMediaDetailFirstLive.setVisibility(View.GONE);
                        return;
                    }

                    lytMediaDetailFirstLive.setVisibility(View.VISIBLE);

                    InstantNewsList.RowsEntity row = response.getRows().get(0);

                    mFirstLiveNews = row;

                    tvItemSubPastNewsTitleLive.setText(row.getField_XWBT());

                    String imageUrl =row.getX6_Product_Pic();
                    Glide.with(MediaDetailActivity.this.getApplicationContext()).load(imageUrl).into(ivItemSubPastNewsImageLive);

                    tvItemSubPastNewsTitleLive.setText(row.getField_XWBT());

                    String introduction = getResources().getString(R.string.news_introduction, row.getField_XWJJ());
                    tvItemSubPastNewsIntroductionLive.setText(introduction);

                    String creator = getResources().getString(R.string.past_news_creator,
                            row.getField_XWCJR());
                    tvItemSubPastNewsCreatorLive.setText(creator);
                } catch (Exception e) {
                    // 捕获异常
                }
            }
        });
        req.setRequestParams(InstantNewsActivity.NEWS_TYPE_LIVE, mMediaInfo.getX6_Product_Id(), 1);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private void updateFirstInstantNews() {
        InstantNewsListRequest req = new InstantNewsListRequest(new HttpRequestListener<InstantNewsList>() {
            @Override
            public void onResponse(InstantNewsList response) {
                super.onResponse(response);

                try {
                    if (response.getRows().size() == 0) {
                        lytMediaDetailFirstInstant.setVisibility(View.GONE);
                        return;
                    }

                    lytMediaDetailFirstInstant.setVisibility(View.VISIBLE);

                    InstantNewsList.RowsEntity row = response.getRows().get(0);

                    mFirstInstantNews = row;

                    tvItemSubPastNewsTitleInstant.setText(row.getField_XWBT());

                    String imageUrl = row.getX6_Product_Pic();
//                    String imageUrl = row.getX6_Product_Pic();
                    Glide.with(MediaDetailActivity.this.getApplicationContext()).load(imageUrl).into(ivItemSubPastNewsImageLive);

                    tvItemSubPastNewsTitleInstant.setText(row.getField_XWBT());

                    String introduction = getResources().getString(R.string.news_introduction, row.getField_XWJJ());
                    tvItemSubPastNewsIntroductionInstant.setText(introduction);

                    String creator = getResources().getString(R.string.past_news_creator,
                            row.getField_XWCJR());
                    tvItemSubPastNewsCreatorInstant.setText(creator);
                } catch (Exception e) {
                    // 捕获异常
                }
            }
        });
        req.setRequestParams(InstantNewsActivity.NEWS_TYPE_INSTANT, mMediaInfo.getX6_Product_Id(), 1);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private void updateFirstPastNews() {

        if (mPastNewsList.size() > 0) {
            lytMediaDetailFirstPast.setVisibility(View.VISIBLE);

            PastNewsList.RowsEntity row = mPastNewsList.get(0);

            String imageUrl = row.getX6_Product_Pic();
            Glide.with(getApplicationContext()).load(imageUrl).into(ivItemSubPastNewsImagePast);

            tvItemSubPastNewsTitlePast.setText(row.getField_XWBT());

            String introduction = getResources().getString(R.string.news_introduction, row.getField_XWJJ());
            tvItemSubPastNewsIntroductionPast.setText(introduction);

            String creator = getResources().getString(R.string.past_news_creator, row.getField_XWCJR());
            tvItemSubPastNewsCreatorPast.setText(creator);
        } else {
            lytMediaDetailFirstPast.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_MEDIA_INFO, mMediaInfo);
        super.onSaveInstanceState(outState);
    }

    private void updateViews(MediaList.RowsEntity mediaInfo) {

        if (mediaInfo == null) {
            refreshMediaDetail();
            return;
        }



        String url = mediaInfo.getX6_Product_Pic();
        Glide.with(getApplicationContext()).load(url).into(ivMediaDetailBackground);

//        String logo = HttpUrls.HTTP_URL_WEB + row.getField_logo();
//        Glide.with(mContext).load(logo).into(holder.logo);

        tvMediaDetailTitle.setText(mediaInfo.getField_XWFL());

        String creator = getResources().getString(R.string.media_creator, mediaInfo.getField_MTCJRR());
        tvMediaDetailCreator.setText(creator);

        String introduction = getResources().getString(R.string.media_introduction, mediaInfo.getField_MTJJ());
        tvMediaDetailContent.setText(introduction);

        String memberCount = getResources().getString(R.string.media_member_count, mediaInfo.get_count());
        tvMediaDetailMemberCount.setText(memberCount);

        if (mediaInfo.getIsFollowed() != 0) {
            //关注
            setCancel();
        } else {
            //没关注
            setFollow();
        }
    }

    private void setCancel(){

            btnMediaDetailFollow.setBackgroundResource(R.color.white);
            btnMediaDetailFollow.setText("已关注");
            btnMediaDetailFollow.setTextColor(getResources().getColor(R.color.common_gray_color));

    }

    private void setFollow(){

            btnMediaDetailFollow.setBackgroundResource(R.drawable.btn_follow_media);
            btnMediaDetailFollow.setText("关注");
            btnMediaDetailFollow.setTextColor(getResources().getColor(R.color.white));



    }

    private MediaList.RowsEntity rowsEntity;
    private void refreshMediaDetail() {
        MediaListRequest req = new MediaListRequest(mListener0);

        req.setDetailParams(mMediaId);
        req.setRequestParams(1);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private HttpRequestListener<MediaList> mListener0 = new HttpRequestListener<MediaList>() {
        @Override
        public void onResponse(MediaList response) {
            super.onResponse(response);

            try {
                rowsEntity = response.getRows().get(0);

                if (rowsEntity != null) {
                    updateViews(rowsEntity);
                    refreshListView(true);
                    updateFirstLiveNews();
                    updateFirstInstantNews();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);


        }

        @Override
        public void onFinishRequest() {
            super.onFinishRequest();


        }
    };
    @Override
    @OnClick({R.id.tv_media_detail_member_detail,
            R.id.lyt_media_detail_instant,
            R.id.lyt_media_detail_live,
            R.id.lyt_media_detail_first_live,
            R.id.lyt_media_detail_past,
            R.id.lyt_media_detail_first_instant,
            R.id.lyt_media_detail_first_past,
            R.id.btn_media_detail_follow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;
            case R.id.tv_base_title_right:
                ShareUtil.shareInit(this,"3", mMediaInfo.getField_XWFL(), mMediaInfo.getField_MTJJ(), mMediaInfo.getX6_Product_Pic(), "481", mMediaInfo.getX6_Product_Id());
                break;
            case R.id.tv_media_detail_member_detail:
                UIHelper.gotoMediaMemberActivity(MediaDetailActivity.this,
                        mMediaInfo.getX6_Product_Id());
                break;

            case R.id.lyt_media_detail_live:
                // 到现场
                UIHelper.gotoInstantNewsActivity(MediaDetailActivity.this,
                        InstantNewsActivity.NEWS_TYPE_LIVE,
                        mMediaInfo.getX6_Product_Id());
                break;

            case R.id.lyt_media_detail_instant:
                // 到及时
                UIHelper.gotoInstantNewsActivity(MediaDetailActivity.this,
                        InstantNewsActivity.NEWS_TYPE_INSTANT,
                        mMediaInfo.getX6_Product_Id());
                break;

            case R.id.lyt_media_detail_past:
                UIHelper.gotoPastNewsActivity(MediaDetailActivity.this,
                        mMediaInfo.getX6_Product_Id());
                break;

            case R.id.btn_media_detail_follow:
                followMedia();
                break;

            case R.id.lyt_media_detail_first_live:
                try {
                    int newsId = mFirstLiveNews.getX6_Product_Id();

                    NewsDetailRequest req = new NewsDetailRequest(new HttpRequestListener<NewsDetail>() {
                        @Override
                        public void onResponse(NewsDetail response) {
                            super.onResponse(response);

                            try {
                                NewsDetail.RowsEntity row = response.getRows().get(0);
                                if (row != null)
                                    UIHelper.gotoNewsDetailActivity(MediaDetailActivity.this, row);
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

                break;

            case R.id.lyt_media_detail_first_instant:
                try {
                    int newsId = mFirstInstantNews.getX6_Product_Id();

                    NewsDetailRequest req = new NewsDetailRequest(new HttpRequestListener<NewsDetail>() {
                        @Override
                        public void onResponse(NewsDetail response) {
                            super.onResponse(response);

                            try {
                                NewsDetail.RowsEntity row = response.getRows().get(0);
                                if (row != null)
                                    UIHelper.gotoNewsDetailActivity(MediaDetailActivity.this, row);
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
                break;

            case R.id.lyt_media_detail_first_past:
                try {
                    PastNewsList.RowsEntity rowPastNews = (PastNewsList.RowsEntity) mGroupedPastNewsListAdapter.getChild(0, 0);

                    NewsDetail.RowsEntity row = new NewsDetail.RowsEntity();

                    row.setField_XWBT(rowPastNews.getField_XWBT());
                    row.setField_XWCJR(rowPastNews.getField_XWCJR());
                    row.setField_XWNR(rowPastNews.getField_XWJJ());
                    row.setX6_Product_Id(rowPastNews.getX6_Product_Id());
                    row.setX6_Product_Pic(rowPastNews.getX6_Product_Pic());
                    row.setX6_Product_Time(rowPastNews.getX6_Product_Time());

                    UIHelper.gotoNewsDetailActivity(MediaDetailActivity.this, row);
                } catch (Exception e) {
                    // 捕捉异常
                }
                break;

            default:
                super.onClick(view);
                break;
        }
    }

    private FollowMediaResult mFollowMediaResult;
    private void followMedia() {
        FollowMediaRequest req = new FollowMediaRequest(new HttpRequestListener<FollowMediaResult>() {
            @Override
            public void onResponse(FollowMediaResult response) {
                super.onResponse(response);
                mFollowMediaResult = response;

                if (rowsEntity.getIsFollowed() == 0){
                    setCancel();
                    rowsEntity.setIsFollowed(1);
                }else {

                    setFollow();
                    rowsEntity.setIsFollowed(0);
                }

                Toast.makeText(MediaDetailActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });

        String type;

        if(rowsEntity.getIsFollowed() == 0){
            type = FollowMediaResult.FOLLOW;
        }else{
            type = FollowMediaResult.CANCEL;
        }

        req.setRequestParams(type,mMediaInfo.getX6_Product_Id());
        HttpRequestQueue.addToRequestQueue(req);
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
        req.setRequestParams(mMediaInfo.getX6_Product_Id(), mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    /**
     * 展开所有的ListView
     */
    private void expandListView() {
        for (int i = 0; i < mGroupedPastNewsListAdapter.getGroupCount(); i++)
            plvMediaDetailContent.getRefreshableView().expandGroup(i);
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

            if (mCurrentPage == 1) {
                updateFirstPastNews();
            }

            groupPastNewsList();

            mGroupedPastNewsListAdapter.notifyDataSetChanged();

            expandListView();

            AppLog.i("消息总数：" + response.getTotal() + "，列表数据：" + mPastNewsList.size());

            if (response.getTotal() <= mPastNewsList.size()) {
                plvMediaDetailContent.setHasMoreData(false);
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

            plvMediaDetailContent.onPullRefreshComplete();
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

}
