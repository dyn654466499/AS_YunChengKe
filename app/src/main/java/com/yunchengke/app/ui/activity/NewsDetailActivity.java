package com.yunchengke.app.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.AddNewsCommentResult;
import com.yunchengke.app.bean.NewsCommentList;
import com.yunchengke.app.bean.NewsDetail;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.AddNewsCommentRequest;
import com.yunchengke.app.http.request.NewsCommentListRequest;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.ListViewForScrollView;
import com.yunchengke.app.utils.DateTimeUtils;
import com.yunchengke.app.utils.ShareUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 类名：NewsDetailActivity <br/>
 * 描述：新闻详情
 * 创建时间：2016/01/11 23:42
 *
 * @author hanter
 * @version 1.0
 */
public class NewsDetailActivity extends BaseActivity {
    public static final String EXTRA_NEWS_ID = "EXTRA_NEWS_ID";
    public static final String EXTRA_USER_ID = "EXTRA_USER_ID";
    public static final String EXTRA_NEWS_DETAIL = "EXTRA_NEWS_DETAIL";

    @Bind(R.id.btn_news_detail_comment)
    Button btnNewsDetailComment;
    @Bind(R.id.edt_news_detail_comment)
    EditText edtNewsDetailComment;
    @Bind(R.id.tv_news_detail_title)
    TextView tvNewsDetailTitle;
    @Bind(R.id.iv_news_detail_image)
    ImageView ivNewsDetailImage;
    @Bind(R.id.tv_news_detail_content)
    TextView tvNewsDetailContent;
    @Bind(R.id.tv_news_detail_interval)
    TextView tvNewsDetailInterval;
    @Bind(R.id.tv_news_detail_creator)
    TextView tvNewsDetailCreator;
    @Bind(R.id.tv_news_detail_create_time)
    TextView tvNewsDetailCreateTime;
    @Bind(R.id.tv_news_detail_comment_count)
    TextView tvNewsDetailCommentCount;
    ListViewForScrollView lvNewsDetailComment;
    @Bind(R.id.lyt_news_detail_comment)
    LinearLayout lytNewsDetailComment;

    private long mNewId;
    private long mUserId;
    private NewsDetail.RowsEntity mNewsDetail;

    private NewsCommentListAdapter mNewsCommentListAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_news_detail, parent);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mNewId = getIntent().getLongExtra(EXTRA_NEWS_ID, 0);
            mUserId = getIntent().getLongExtra(EXTRA_USER_ID, 0);
            mNewsDetail = (NewsDetail.RowsEntity) getIntent().getSerializableExtra(EXTRA_NEWS_DETAIL);
        } else {
            mNewId = savedInstanceState.getLong(EXTRA_NEWS_ID);
            mUserId = savedInstanceState.getLong(EXTRA_USER_ID);
            mNewsDetail = (NewsDetail.RowsEntity) savedInstanceState.getSerializable(EXTRA_NEWS_DETAIL);
        }

        lytTitle.setBackgroundResource(R.color.headline_background);
        setTitle(R.string.news_detail);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.share);

        mNewsCommentListAdapter = new NewsCommentListAdapter(NewsDetailActivity.this);
        lvNewsDetailComment.setAdapter(mNewsCommentListAdapter);

        lvNewsDetailComment.setDivider(getResources().getDrawable(R.color.topic_list_divider_color));
        lvNewsDetailComment.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.split_line_height));

        updateViews(mNewsDetail);

        refreshNewsDetail();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_NEWS_DETAIL, mNewsDetail);
        outState.putLong(EXTRA_NEWS_ID, mNewId);
        outState.putLong(EXTRA_USER_ID, mUserId);
        super.onSaveInstanceState(outState);
    }

    @Override
    @OnClick({R.id.btn_news_detail_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            case R.id.btn_news_detail_comment:
                addNewsComment();
                break;

            case R.id.tv_base_title_right:
              //  ShareUtil.shareInit(this,mNewsDetail.getField_XWBT(),mNewsDetail.getField_XWNR(),mNewsDetail.getX6_Product_Pic(),"482",mNewsDetail.getX6_Product_Id());
                break;
        }
    }

    private void updateViews(NewsDetail.RowsEntity newsDetail) {

        if (newsDetail == null) {
            return;
        }

        tvNewsDetailTitle.setText(newsDetail.getField_XWBT());

        String imageUrl = newsDetail.getX6_Product_Pic();
        Glide.with(this).load(imageUrl).into(ivNewsDetailImage);

        tvNewsDetailContent.setText(newsDetail.getField_XWNR());

        tvNewsDetailInterval.setText(DateTimeUtils.getIntervalTime(newsDetail.getX6_Product_Time()));

        tvNewsDetailCreator.setText(newsDetail.getField_XWCJR());

        tvNewsDetailCreateTime.setText(newsDetail.getX6_Product_Time());
    }

    private void refreshNewsDetail() {
        /*
        NewsListRequest req = new NewsListRequest(new HttpRequestListener<NewsList>() {
            @Override
            public void onResponse(NewsList response) {
                super.onResponse(response);

                try {
                    mNewsCommentListAdapter.setRows(response.getRows());
                    mNewsCommentListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });

        req.setDetailRequestParams(mNewId);
        HttpRequestQueue.addToRequestQueue(req);
        */


        NewsCommentListRequest req = new NewsCommentListRequest(new HttpRequestListener<NewsCommentList>() {
            @Override
            public void onResponse(NewsCommentList response) {
                super.onResponse(response);

                try {
                    String commentCount = getResources().getString(R.string.news_comment_count, response.getTotal());
                    tvNewsDetailCommentCount.setText(commentCount);

                    if (response.getTotal() <= 0) {
                        lytNewsDetailComment.setVisibility(View.GONE);
                    } else {
                        lytNewsDetailComment.setVisibility(View.VISIBLE);
                    }

                    AppLog.i("评论数量：" + response.getTotal());

                    mNewsCommentListAdapter.setRows(response.getRows());
                    mNewsCommentListAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    // 异常捕捉
                }

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);

                Toast.makeText(NewsDetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        req.setRequestParams(mNewId, 1);
        HttpRequestQueue.addToRequestQueue(req);
    }

    private void addNewsComment() {

        String comment = edtNewsDetailComment.getText().toString();

        if (TextUtils.isEmpty(comment)) {
            return;
        }

        AddNewsCommentRequest req = new AddNewsCommentRequest(new HttpRequestListener<AddNewsCommentResult>() {
            @Override
            public void onResponse(AddNewsCommentResult response) {
                super.onResponse(response);

                Toast.makeText(NewsDetailActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                Toast.makeText(NewsDetailActivity.this, "网络不给力哦！", Toast.LENGTH_SHORT).show();
            }
        });

        req.setRequestParams(mNewId, comment);
        HttpRequestQueue.addToRequestQueue(req);
    }

    static class NewsCommentListAdapter extends AbsBaseAdapter<NewsCommentList.RowsEntity> {

        public NewsCommentListAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.item_news_comment_list, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            NewsCommentList.RowsEntity row = this.mRows.get(position);

            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getX6_Product_Admin();
            Glide.with(mContext).load(url).into(holder.ivItemNewsCommentPortrait);

            holder.tvItemNewsCommentName.setText(row.getField_PLYH());
            holder.tvItemNewsCommentContent.setText(row.getField_PLNR());
            holder.tvItemNewsCommentDatetime.setText(row.getField_PLSJ());

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_news_comment_portrait)
            ImageView ivItemNewsCommentPortrait;
            @Bind(R.id.tv_item_news_comment_name)
            TextView tvItemNewsCommentName;
            @Bind(R.id.tv_item_news_comment_datetime)
            TextView tvItemNewsCommentDatetime;
            @Bind(R.id.tv_item_news_comment_content)
            TextView tvItemNewsCommentContent;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

    }
}
