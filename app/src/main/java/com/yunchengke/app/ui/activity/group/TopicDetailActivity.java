package com.yunchengke.app.ui.activity.group;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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
import com.yunchengke.app.bean.AddTopicCommentResult;
import com.yunchengke.app.bean.TopicCommentList;
import com.yunchengke.app.bean.TopicDetail;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.AddTopicCommentRequest;
import com.yunchengke.app.http.request.PublishDynamicRequest;
import com.yunchengke.app.http.request.TopicCommentListRequest;
import com.yunchengke.app.http.request.TopicDetailRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.OnNestedClickListener;
import com.yunchengke.app.utils.DateTimeUtils;
import com.yunchengke.app.utils.ShareUtil;
import com.yunchengke.app.utils.SoftInputUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名：TopicDetailActivity <br/>
 * 描述：话题详情
 * 创建时间：2016/01/10 11:21
 *
 * @author hanter
 * @version 1.0
 */
public class TopicDetailActivity extends BaseActivity {

    public static final String EXTRA_TOPIC_ID = "EXTRA_TOPIC_ID";

    private long mTopicId;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    static TopicDetail.RowsEntity mTopicDetail;

    private List<TopicCommentList.RowsEntity> mTopicCommentList;
    private TopicCommentListAdapter mTopicCommentListAdapter;

    @Bind(R.id.tv_topic_detail_title)
    TextView tvTopicDetailTitle;
    @Bind(R.id.iv_topic_detail_portrait)
    ImageView ivTopicDetailPortrait;
    @Bind(R.id.tv_topic_detail_creator)
    TextView tvTopicDetailCreator;
    @Bind(R.id.tv_topic_detail_createtime)
    TextView tvTopicDetailCreateTime;
    @Bind(R.id.tv_topic_detail_content)
    TextView tvTopicDetailContent;
    @Bind(R.id.tv_topic_detail_comment_count)
    TextView tvTopicDetailCommentCount;

    @Bind(R.id.lv_topic_detail_comment)
    PullToRefreshListView lvTopicDetailComment;

    @Bind(R.id.btn_topic_detail_comment)
    Button btnTopicDetailComment;
    @Bind(R.id.edt_topic_detail_comment)
    EditText edtTopicDetailComment;

    @Bind(R.id.lyt_topic_detail_comment)
    LinearLayout lytTopicDetailComment;

    @Bind(R.id.gdv_topic_detail_images)
    GridView gdvTopicDetailImages;


    @Override
    protected View createContentView(ViewGroup parent) {
        View rootView = inflate(R.layout.activity_topic_detail, parent);

        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.lv_topic_detail_comment);

        ListView listView = pullToRefreshListView.getRefreshableView();
        listView.addHeaderView(inflate(R.layout.layout_topic_detail_header, listView), null, false);

        return rootView;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_TOPIC_ID, mTopicId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mTopicId = getIntent().getLongExtra(EXTRA_TOPIC_ID, 0);
        } else {
            mTopicId = savedInstanceState.getLong(EXTRA_TOPIC_ID, 0);
        }

        lytTitle.setBackgroundResource(R.color.group_background);
        setTitle(R.string.topic_detail_title);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.share);

        lvTopicDetailComment.setPullRefreshEnabled(false);
        lvTopicDetailComment.setScrollLoadEnabled(true);

        ListView listView = lvTopicDetailComment.getRefreshableView();

        listView.setDivider(getResources().getDrawable(R.color.common_split_line_color));
//        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.split_line_height));
        listView.setDividerHeight(0);
        listView.setOverscrollHeader(getResources().getDrawable(R.color.white));
        listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        listView.setHeaderDividersEnabled(true);

        mTopicCommentListAdapter = new TopicCommentListAdapter(this);
        mTopicCommentList = new ArrayList<TopicCommentList.RowsEntity>();
        mTopicCommentListAdapter.setRows(mTopicCommentList);
        listView.setAdapter(mTopicCommentListAdapter);

        lvTopicDetailComment.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //
                updateTopicCommentList(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // 上拉加载
                updateTopicCommentList(false);
            }
        });

        updateTopicDetail();

        // 更新评论
        updateTopicCommentList(true);

        ivTopicDetailPortrait.setClickable(true);
    }

    /**
     * 更新话题评论列表
     *
     * @param pullDownRefresh 是否下拉
     */
    private void updateTopicCommentList(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        AppLog.i("刷新第" + mCurrentPage + "页");

        TopicCommentListRequest req = new TopicCommentListRequest(mListener);
        req.setRequestParams(mTopicId, mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private void updateTopicDetail() {
        TopicDetailRequest req = new TopicDetailRequest(new HttpRequestListener<TopicDetail>() {
            @Override
            public void onResponse(TopicDetail response) {
                super.onResponse(response);

                try {
                    TopicDetail.RowsEntity row = response.getRows().get(0);
                    mTopicDetail = row;
                    tvTopicDetailTitle.setText(row.getField_HTBT());
                    tvTopicDetailCreator.setText(getResources().getString(R.string.topic_from_creator, row.getField_HTCJR()));
                    tvTopicDetailContent.setText(row.getField_HTNR());
                    String url = row.getX6_Product_Pic();
                    Glide.with(TopicDetailActivity.this).load(url).into(ivTopicDetailPortrait);

                    String portraitUrl = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getField_YHID();
                    Glide.with(TopicDetailActivity.this).load(portraitUrl).into(ivTopicDetailPortrait);

                    String interval = DateTimeUtils.getIntervalTime(row.getField_HTCJSJ());
                    tvTopicDetailCreateTime.setText(interval);

                    String commentCount = getResources().getString(R.string.topic_detail_comment_count, row.getField_PLSL());
                    tvTopicDetailCommentCount.setText(commentCount);

                    String picUrls = row.getX6_Product_Pic();

                    if (!TextUtils.isEmpty(picUrls)) {

                        String[] urlList = picUrls.split(Constants.IMAGE_SPLIT_REGULAR);
                        final ArrayList<Map<String, String>> picUrlList = new ArrayList<Map<String, String>>();

                        for (String imagePairUrl : urlList) {
                            String[] pairUrl = imagePairUrl.split(Constants.SUB_IMAGE_SPLIT_REGULAR);
                            try {
                                Map<String, String> pairUrlMap = new HashMap<String, String>();
                                pairUrlMap.put("small", pairUrl[0]);
                                pairUrlMap.put("large", pairUrl[1]);
                                picUrlList.add(pairUrlMap);
                            } catch (Exception e) {
                                // 捕获异常
                            }
                        }

                        TopicImagesAdapter adapter = new TopicImagesAdapter(TopicDetailActivity.this);
                        adapter.setRows(picUrlList);
                        gdvTopicDetailImages.setAdapter(adapter);

                        gdvTopicDetailImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                try {
                                    ArrayList<String> imageList = new ArrayList<String>();

                                    for (Map<String, String> map : picUrlList) {
                                        imageList.add(map.get("large"));
                                    }

                                    UIHelper.gotoZoomGalleryActivity(TopicDetailActivity.this, imageList, position);
                                } catch (Exception e) {
                                    // 捕获异常
                                }
                            }
                        });

                    }

                } catch (Exception e) {
                    // 异常捕捉
                }

            }
        });
        req.setRequestParams(mTopicId);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private void addTopicComment() {
        String comment = edtTopicDetailComment.getText().toString();

        if (TextUtils.isEmpty(comment)) {
            return;
        }

        startDialog(R.string.loading);
        AddTopicCommentRequest req = new AddTopicCommentRequest(new HttpRequestListener<AddTopicCommentResult>() {
            @Override
            public void onResponse(AddTopicCommentResult response) {
                super.onResponse(response);
                closeDialog();
                if (AddTopicCommentResult.RESULT_SUCCESS.equals(response.getResultState())) {
                    edtTopicDetailComment.getText().clear();
                    updateTopicCommentList(true);

                    SoftInputUtil.hideSoftInput(TopicDetailActivity.this,edtTopicDetailComment);
                }


                Toast.makeText(TopicDetailActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                closeDialog();
            }
        });

        req.setRequestParams(mTopicId, comment);
        HttpRequestQueue.addToRequestQueue(req);
    }

    @Override
    @OnClick({R.id.btn_topic_detail_comment, R.id.iv_topic_detail_portrait})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;
            case R.id.tv_base_title_right:
                if (mTopicDetail != null)
                    Log.d("syb","mTopicDetail= "+mTopicDetail.getX6_Product_Pic());
                    ShareUtil.shareInit(this, "6", mTopicDetail.getField_HTBT(), mTopicDetail.getField_HTNR(), mTopicDetail.getX6_Product_Pic(), "484", mTopicDetail.getX6_Product_Id());
            case R.id.btn_topic_detail_comment:
                addTopicComment();
                break;

            case R.id.iv_topic_detail_portrait:
                if (mTopicDetail != null)
                    UIHelper.gotoUserInfoActivity(TopicDetailActivity.this, mTopicDetail.getX6_Product_Id());
                break;

            default:
                super.onClick(view);
                break;
        }
    }

    HttpRequestListener<TopicCommentList> mListener = new HttpRequestListener<TopicCommentList>() {
        @Override
        public void onResponse(TopicCommentList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mTopicCommentList.clear();
            }

            List<TopicCommentList.RowsEntity> row = response.getRows();

            // 是否未取到数据
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mTopicCommentList.addAll(response.getRows());
            mTopicCommentListAdapter.notifyDataSetChanged();

            if (mTopicCommentList.size() != 0) {
                lytTopicDetailComment.setVisibility(View.VISIBLE);
                tvTopicDetailCommentCount.setText(getResources().getString(R.string.dynamic_detail_comment_count, response.getTotal()));
            } else {
                lytTopicDetailComment.setVisibility(View.GONE);
            }

            if (response.getTotal() <= mTopicCommentList.size()) {
                if (mTopicCommentList.size() == 0) {
                    lvTopicDetailComment.getFooterLoadingLayout().setNoMoreDataLabel("暂无评论");
                } else {
                    lvTopicDetailComment.getFooterLoadingLayout().setNoMoreDataLabel("");
                }

                lvTopicDetailComment.setHasMoreData(false);
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

            lvTopicDetailComment.onPullRefreshComplete();
        }
    };

    public static class TopicImagesAdapter extends AbsBaseAdapter<Map<String, String>> {

        public TopicImagesAdapter(Context context) {
            super(context);
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_dynamic_img, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Map<String, String> urlMap = getItem(position);
            String smallUrl = urlMap.get("small");
            Glide.with(mContext).load(smallUrl).into(holder.img);

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.img)
            ImageView img;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

     class TopicCommentListAdapter extends AbsBaseAdapter<TopicCommentList.RowsEntity> implements View.OnClickListener {

        public TopicCommentListAdapter(Context context) {
            super(context);
        }

        public TopicCommentListAdapter(Context context, OnNestedClickListener onNestedClickListener) {
            super(context, onNestedClickListener);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.item_topic_comment_list, parent, false);

                holder = new ViewHolder();

                holder.portrait = (ImageView) convertView
                        .findViewById(R.id.iv_item_topic_comment_portrait);
                holder.name = (TextView) convertView
                        .findViewById(R.id.tv_item_topic_comment_name);
                holder.content = (TextView) convertView
                        .findViewById(R.id.tv_item_topic_comment_content);
                holder.datetime = (TextView) convertView
                        .findViewById(R.id.tv_item_topic_comment_datetime);

                holder.divider = convertView
                        .findViewById(R.id.lyt_item_topic_comment_divider);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == (getCount() - 1)) {
                holder.divider.setVisibility(View.INVISIBLE);
            } else {
                holder.divider.setVisibility(View.VISIBLE);
            }

           final TopicCommentList.RowsEntity row = this.mRows.get(position);

            // 获取用户图片
            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getX6_Product_Admin();
            Glide.with(mContext).load(url).into(holder.portrait);
            holder.portrait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UIHelper.gotoUserInfoActivity(TopicDetailActivity.this,row.getX6_Product_Admin());
                }
            });
            holder.name.setText(row.getField_PLYH());
            holder.content.setText(row.getField_PLNR());
            holder.datetime.setText(row.getField_PLSJ());

            return convertView;
        }

        @Override
        public void onClick(View v) {

        }

        class ViewHolder {
            ImageView portrait;
            TextView name;
            TextView content;
            TextView datetime;
            View divider;
        }
    }
}
