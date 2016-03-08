package com.yunchengke.app.ui.activity.dynamic;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.dynamic.AddDynamicCommentResult;
import com.yunchengke.app.bean.dynamic.DynamicDetailsCommentEntity;
import com.yunchengke.app.bean.dynamic.DynamicDetailsCommentListEntity;
import com.yunchengke.app.bean.dynamic.DynamicDetailsEntity;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.AddDynamicCommentRequest;
import com.yunchengke.app.http.request.DynamicCommentListRequest;
import com.yunchengke.app.http.request.PublishDynamicRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.daemon.CircleImageView;
import com.yunchengke.app.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DynamicDetailActivity extends BaseActivity {
    public final static String EXTRA_DYNAMIC_DETAIL = "EXTRA_DYNAMIC_DETAIL";

    // 头部
    @Bind(R.id.iv_dynamic_detail_portrait)
    CircleImageView ivDynamicDetailPortrait;
    @Bind(R.id.tv_dynamic_detail_name)
    TextView tvDynamicDetailName;
    @Bind(R.id.tv_dynamic_detail_date)
    TextView tvDynamicDetailDate;
    @Bind(R.id.tv_dynamic_detail_content)
    TextView tvDynamicDetailContent;
    @Bind(R.id.gdv_dynamic_detail_images)
    GridView gdvDynamicDetailImages;
    @Bind(R.id.tv_dynamic_detail_comment_count)
    TextView tvDynamicDetailCommentCount;
    @Bind(R.id.lyt_dynamic_detail_comment)
    ViewGroup lytDynamicDetailComment;

    // 评论布局和列表
    @Bind(R.id.btn_dynamic_detail_comment)
    Button btnDynamicDetailComment;
    @Bind(R.id.edt_dynamic_detail_comment)
    EditText edtDynamicDetailComment;
    @Bind(R.id.plv_dynamic_detail_comment)
    PullToRefreshListView plvDynamicDetailComment;

    private DynamicDetailsEntity mDynamicDetailsEntity;
    private List<DynamicDetailsCommentEntity> mDynamicCommentList;
    private DynamicCommentAdapter mDynamicCommentAdapter;

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    @Override
    protected View createContentView(ViewGroup parent) {
        View rootView = inflate(R.layout.activity_dynamic_detail, parent);

        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.plv_dynamic_detail_comment);

        ListView listView = pullToRefreshListView.getRefreshableView();
        listView.addHeaderView(inflate(R.layout.layout_dynamic_detail_header, listView), null, false);

        return rootView;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_DYNAMIC_DETAIL, mDynamicDetailsEntity);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mDynamicDetailsEntity = (DynamicDetailsEntity) getIntent().getSerializableExtra(EXTRA_DYNAMIC_DETAIL);
        } else {
            mDynamicDetailsEntity = (DynamicDetailsEntity) savedInstanceState.getSerializable(EXTRA_DYNAMIC_DETAIL);
        }

        lytTitle.setBackgroundResource(R.color.dynamic_title_background);
        setTitle(R.string.dynamic_detail_title);
        setTitleLeft(R.string.back);

        updateDynamicDetail();

        plvDynamicDetailComment.setPullRefreshEnabled(false);
        plvDynamicDetailComment.setScrollLoadEnabled(true);

        mDynamicCommentList = new ArrayList<DynamicDetailsCommentEntity>();
        mDynamicCommentAdapter = new DynamicCommentAdapter(DynamicDetailActivity.this);
        mDynamicCommentAdapter.setRows(mDynamicCommentList);
        plvDynamicDetailComment.setAdapter(mDynamicCommentAdapter);
        plvDynamicDetailComment.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
            }
        });

        refreshListView(true);
    }

    private void updateDynamicDetail() {

        String picUrls = mDynamicDetailsEntity.getX6_Product_PicList();

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

            DynamicActivity.DynamicImagesAdapter adapter = new DynamicActivity.DynamicImagesAdapter(DynamicDetailActivity.this);
            adapter.setRows(picUrlList);
            gdvDynamicDetailImages.setAdapter(adapter);

            gdvDynamicDetailImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        ArrayList<String> imageList = new ArrayList<String>();

                        for (Map<String, String> map : picUrlList) {
                            imageList.add(map.get("large"));
                        }

                        UIHelper.gotoZoomGalleryActivity(DynamicDetailActivity.this, imageList, position);
                    } catch (Exception e) {
                        // 捕获异常
                    }
                }
            });
        }

        String portraitUrl = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + mDynamicDetailsEntity.getField_YHID();
        Glide.with(this).load(portraitUrl).into(ivDynamicDetailPortrait);

        tvDynamicDetailName.setText(mDynamicDetailsEntity.getField_DTYH());
        tvDynamicDetailDate.setText(DateTimeUtils.getIntervalTime(mDynamicDetailsEntity.getField_DTSJ()));
        tvDynamicDetailContent.setText(mDynamicDetailsEntity.getField_DTJL());
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

        DynamicCommentListRequest req = new DynamicCommentListRequest(mListener);
        req.setRequestParams(mDynamicDetailsEntity.getX6_Product_Id(), mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    HttpRequestListener<DynamicDetailsCommentListEntity> mListener = new HttpRequestListener<DynamicDetailsCommentListEntity>() {
        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            plvDynamicDetailComment.onPullRefreshComplete();
        }

        @Override
        public void onResponse(DynamicDetailsCommentListEntity response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mDynamicCommentList.clear();
            }


            List<DynamicDetailsCommentEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mDynamicCommentList.addAll(response.getRows());
            mDynamicCommentAdapter.notifyDataSetChanged();

            if (mDynamicCommentList.size() != 0) {
                lytDynamicDetailComment.setVisibility(View.VISIBLE);
                tvDynamicDetailCommentCount.setText(getResources().getString(R.string.dynamic_detail_comment_count, response.getTotal()));
            } else {
                lytDynamicDetailComment.setVisibility(View.GONE);
            }

            if (response.getTotal() <= mDynamicCommentList.size()) {
                if (mDynamicCommentList.size() == 0) {
                    plvDynamicDetailComment.getFooterLoadingLayout().setNoMoreDataLabel("暂无评论");
                } else {
                    plvDynamicDetailComment.getFooterLoadingLayout().setNoMoreDataLabel("");
                }

                plvDynamicDetailComment.setHasMoreData(false);
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
    @OnClick({R.id.btn_dynamic_detail_comment, R.id.iv_dynamic_detail_portrait})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            case R.id.btn_dynamic_detail_comment:

                String comment = edtDynamicDetailComment.getText().toString();

                if (!comment.equals("")) {
                    startDialog(R.string.loading);
                    AddDynamicCommentRequest req = new AddDynamicCommentRequest(new HttpRequestListener<AddDynamicCommentResult>() {
                        @Override
                        public void onResponse(AddDynamicCommentResult response) {
                            super.onResponse(response);
                            closeDialog();
                            Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            refreshListView(true);

                            if (AddDynamicCommentResult.RESULT_SUCCESS.equals(response.getResultState())) {
                                edtDynamicDetailComment.getText().clear();
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            super.onErrorResponse(error);
                            closeDialog();
                            Toast.makeText(getApplicationContext(), "评论失败!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    req.setRequestParams(mDynamicDetailsEntity.getX6_Product_Id(), comment);
                    HttpRequestQueue.addToRequestQueue(req);
                } else {
                    Toast.makeText(getApplicationContext(), "发表评论不能为空!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.iv_dynamic_detail_portrait:
                UIHelper.gotoUserInfoActivity(DynamicDetailActivity.this, mDynamicDetailsEntity.getField_YHID());
                break;

            default:
                super.onClick(v);
                break;
        }
    }

    /**
     * 评论列表Adapter
     */
    static class DynamicCommentAdapter extends AbsBaseAdapter<DynamicDetailsCommentEntity> {

        public DynamicCommentAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_dynamic_comment, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            DynamicDetailsCommentEntity row = getItem(position);

            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getX6_Product_Admin();

            Glide.with(mContext).load(url).into(holder.ivItemDynamicCommentPortrait);

            holder.tvItemDynamicCommentName.setText(row.getField_PLYH());
            holder.tvItemDynamicCommentDatetime.setText(row.getField_PLSJ());
            holder.tvItemDynamicCommentContent.setText(row.getField_PLNR());

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.iv_item_dynamic_comment_portrait)
            CircleImageView ivItemDynamicCommentPortrait;
            @Bind(R.id.tv_item_dynamic_comment_name)
            TextView tvItemDynamicCommentName;
            @Bind(R.id.tv_item_dynamic_comment_datetime)
            TextView tvItemDynamicCommentDatetime;
            @Bind(R.id.tv_item_dynamic_comment_content)
            TextView tvItemDynamicCommentContent;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
