package com.yunchengke.app.ui.activity.headline;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
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
import com.yunchengke.app.bean.AddNewsCommentResult;
import com.yunchengke.app.bean.NewsCommentList;
import com.yunchengke.app.bean.NewsDetail;
import com.yunchengke.app.bean.NewsList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.AddNewsCommentRequest;
import com.yunchengke.app.http.request.NewsCommentListRequest;
import com.yunchengke.app.http.request.NewsDetailRequest;
import com.yunchengke.app.http.request.NewsListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.utils.DateTimeUtils;
import com.yunchengke.app.utils.ShareUtil;
import com.yunchengke.app.utils.SoftInputUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    public static final String EXTRA_NEWS_DETAIL = "EXTRA_NEWS_DETAIL";

    @Bind(R.id.btn_news_detail_comment)
    Button btnNewsDetailComment;
    @Bind(R.id.edt_news_detail_comment)
    EditText edtNewsDetailComment;
    @Bind(R.id.tv_news_detail_title)
    TextView tvNewsDetailTitle;
    @Bind(R.id.iv_news_detail_image)
    ImageView ivNewsDetailImage;
    TextView tvNewsDetailContent;
    @Bind(R.id.tv_news_detail_interval)
    TextView tvNewsDetailInterval;
    @Bind(R.id.tv_news_detail_creator)
    TextView tvNewsDetailCreator;
    @Bind(R.id.tv_news_detail_create_time)
    TextView tvNewsDetailCreateTime;
    @Bind(R.id.tv_news_detail_comment_count)
    TextView tvNewsDetailCommentCount;
    @Bind(R.id.plv_news_detail_comment)
    PullToRefreshListView plvNewsDetailComment;
    @Bind(R.id.lyt_news_detail_comment)
    LinearLayout lytNewsDetailComment;
    @Bind(R.id.webview)
    WebView mWebview;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private long mNewsId;
    private NewsDetail.RowsEntity mNewsDetail;

    private List<NewsCommentList.RowsEntity> mCommentList;
    private NewsCommentListAdapter mNewsCommentListAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        View rootView = inflate(R.layout.activity_news_detail, parent);

        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.plv_news_detail_comment);

        ListView listView = pullToRefreshListView.getRefreshableView();
        listView.addHeaderView(inflate(R.layout.layout_news_detail_header, listView), null, false);

        return rootView;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mNewsId = getIntent().getLongExtra(EXTRA_NEWS_ID, 0);
            mNewsDetail = (NewsDetail.RowsEntity) getIntent().getSerializableExtra(EXTRA_NEWS_DETAIL);
        } else {
            mNewsId = savedInstanceState.getLong(EXTRA_NEWS_ID);
            mNewsDetail = (NewsDetail.RowsEntity) savedInstanceState.getSerializable(EXTRA_NEWS_DETAIL);
        }

        lytTitle.setBackgroundResource(R.color.headline_background);
        setTitle(R.string.news_detail);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.share);

        mCommentList = new ArrayList<NewsCommentList.RowsEntity>();
        mNewsCommentListAdapter = new NewsCommentListAdapter(NewsDetailActivity.this);
        mNewsCommentListAdapter.setRows(mCommentList);
        plvNewsDetailComment.setAdapter(mNewsCommentListAdapter);

        //横线？
        ListView listView = plvNewsDetailComment.getRefreshableView();
        listView.setDivider(getResources().getDrawable(R.color.topic_list_divider_color));
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.split_line_height));

        plvNewsDetailComment.setPullRefreshEnabled(false);
        plvNewsDetailComment.setScrollLoadEnabled(true);
        plvNewsDetailComment.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshCommentList(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshCommentList(false);
            }
        });

        if (mNewsDetail != null) {
            mNewsId = mNewsDetail.getX6_Product_Id();
            updateViews(mNewsDetail);
        }

        refreshNewsDetail();
        refreshCommentList(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_NEWS_DETAIL, mNewsDetail);
        outState.putLong(EXTRA_NEWS_ID, mNewsId);
        super.onSaveInstanceState(outState);
    }

    private void refreshCommentList(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        AppLog.i("ListView", "刷新第" + mCurrentPage + "页");

        NewsCommentListRequest req = new NewsCommentListRequest(mListener);
        req.setRequestParams(mNewsId, mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
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
                ShareUtil.shareInit(this,"4",mNewsDetail.getField_XWBT(),mNewsDetail.getField_XWNR(),mNewsDetail.getX6_Product_Pic(),"482",mNewsDetail.getX6_Product_Id());
                break;

            default:
                super.onClick(view);
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

        mWebview.loadUrl("http://www.icityto.com/?Type=3&Module=487&UId=yesicity2015&Yesicity=1&productid="+newsDetail.getX6_Product_Id());
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
//        if (newsDetail.getField_XWNR() !=null) {
//            tvNewsDetailContent.setText(Html.fromHtml(newsDetail.getField_XWNR()));
//            mWebview.loadData(Html.fromHtml(newsDetail.getField_XWNR()).toString(), "text/html", "utf-8");
//
//        }
        tvNewsDetailInterval.setText(DateTimeUtils.getIntervalTime(newsDetail.getX6_Product_Time()));

        tvNewsDetailCreator.setText(newsDetail.getField_XWCJR());

        tvNewsDetailCreateTime.setText(newsDetail.getX6_Product_Time());
    }

    private void refreshNewsDetail() {
        NewsDetailRequest req = new NewsDetailRequest(new HttpRequestListener<NewsDetail>() {
            @Override
            public void onResponse(NewsDetail response) {
                super.onResponse(response);

                try {
                    NewsDetail.RowsEntity rowsEntity = response.getRows().get(0);

                    if  (rowsEntity != null) {
                        updateViews(rowsEntity);
                        refreshCommentList(true);

                        mNewsDetail = rowsEntity;
                    }
                } catch (Exception e) {
                    // 捕获异常
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });

        req.setRequestParams(mNewsId);
        HttpRequestQueue.addToRequestQueue(req);
    }

    HttpRequestListener<NewsCommentList> mListener = new HttpRequestListener<NewsCommentList>() {
        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            plvNewsDetailComment.onPullRefreshComplete();
        }

        @Override
        public void onResponse(NewsCommentList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mCommentList.clear();
            }

            List<NewsCommentList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mCommentList.addAll(response.getRows());
            mNewsCommentListAdapter.notifyDataSetChanged();


            if (mCommentList.size() != 0) {
                lytNewsDetailComment.setVisibility(View.VISIBLE);
                tvNewsDetailCommentCount.setText(getResources().getString(R.string.dynamic_detail_comment_count, response.getTotal()));
            } else {
                lytNewsDetailComment.setVisibility(View.GONE);
            }

            if (response.getTotal() <= mCommentList.size()) {
                if (mCommentList.size() == 0) {
                    plvNewsDetailComment.getFooterLoadingLayout().setNoMoreDataLabel("暂无评论");
                } else {
                    plvNewsDetailComment.getFooterLoadingLayout().setNoMoreDataLabel("");
                }

                plvNewsDetailComment.setHasMoreData(false);
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

    private void addNewsComment() {

        String comment = edtNewsDetailComment.getText().toString();

        if (TextUtils.isEmpty(comment)) {
            return;
        }

        startDialog(R.string.loading);
        AddNewsCommentRequest req = new AddNewsCommentRequest(new HttpRequestListener<AddNewsCommentResult>() {
            @Override
            public void onResponse(AddNewsCommentResult response) {
                super.onResponse(response);
                closeDialog();
                if (AddNewsCommentResult.RESULT_SUCCESS.equals(response.getResultState())) {
                    edtNewsDetailComment.clearFocus();
                    edtNewsDetailComment.getText().clear();
                    SoftInputUtil.hideSoftInput(NewsDetailActivity.this,edtNewsDetailComment);
                    refreshCommentList(true);
                }

                Toast.makeText(NewsDetailActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                closeDialog();
                Toast.makeText(NewsDetailActivity.this, "网络不给力哦！", Toast.LENGTH_SHORT).show();
            }
        });

        req.setRequestParams(mNewsId, comment);
        HttpRequestQueue.addToRequestQueue(req);
    }

    static class NewsCommentListAdapter extends AbsBaseAdapter<NewsCommentList.RowsEntity> implements View.OnClickListener {

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

           final NewsCommentList.RowsEntity row = this.mRows.get(position);

            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getX6_Product_Admin();
            Glide.with(mContext).load(url).into(holder.ivItemNewsCommentPortrait);
            holder.ivItemNewsCommentPortrait.setClickable(true);
            holder.ivItemNewsCommentPortrait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.gotoUserInfoActivity(v.getContext(),row.getX6_Product_Admin());
                }
            });

            holder.tvItemNewsCommentName.setText(row.getField_PLYH());
            holder.tvItemNewsCommentContent.setText(row.getField_PLNR());
            holder.tvItemNewsCommentDatetime.setText(row.getField_PLSJ());

            return convertView;
        }

        @Override
        public void onClick(View v) {
            NewsCommentList.RowsEntity rowsEntity =  (NewsCommentList.RowsEntity) v.getTag();
            UIHelper.gotoUserInfoActivity(v.getContext(),rowsEntity.getX6_Product_Admin());
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
