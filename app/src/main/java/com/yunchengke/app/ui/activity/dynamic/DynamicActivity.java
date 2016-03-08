package com.yunchengke.app.ui.activity.dynamic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.dynamic.DynamicDetailsEntity;
import com.yunchengke.app.bean.dynamic.DynamicListEntity;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.DynamicListRequest;
import com.yunchengke.app.http.request.PublishDynamicRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.CollapsibleTextView;
import com.yunchengke.app.ui.view.NoScrollGridView;
import com.yunchengke.app.ui.view.OnNestedClickListener;
import com.yunchengke.app.ui.view.daemon.CircleImageView;
import com.yunchengke.app.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DynamicActivity extends BaseActivity {

    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数

    private PullToRefreshListView mPullToRefreshListView;
    private List<DynamicDetailsEntity> mDynamicList;
    private DynamicListAdapter mDynamicListAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_home_dynamic, parent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPullToRefreshListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshListView(true);
            }
        }, 100);

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        lytTitle.setBackgroundResource(R.color.dynamic_title_background);
        setTitle("动态");
        setTitleRight("发布");


        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.plv_home_dynamic_content);
        mPullToRefreshListView.setPullRefreshEnabled(true);
        mPullToRefreshListView.setScrollLoadEnabled(true);

        ListView listView = mPullToRefreshListView.getRefreshableView();
        listView.setDivider(getResources().getDrawable(R.color.transparent));
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.dynamic_list_divider_height));

        mDynamicList = new ArrayList<DynamicDetailsEntity>();
        mDynamicListAdapter = new DynamicListAdapter(this, new OnNestedClickListener() {
            @Override
            public void onNestedClickListener(int position, View view) {
                int id = view.getId();

                if (id == R.id.dynamic_portrait) { // 个人详情
                    UIHelper.gotoUserInfoActivity(DynamicActivity.this, mDynamicList.get(position).getField_YHID());
                } else if (id == R.id.tv_item_dynamic_comment) { // 进入动态详情
                    UIHelper.gotoDynamicDetailActivity(DynamicActivity.this, mDynamicList.get(position));
                }
            }
        });
        mDynamicListAdapter.setRows(mDynamicList);
        listView.setAdapter(mDynamicListAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int mState = SCROLL_STATE_IDLE;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (mState != scrollState) {
                    mState = scrollState;

                    if (mState == SCROLL_STATE_FLING) {
                        Glide.with(DynamicActivity.this).pauseRequestsRecursive();
                    } else {
                        Glide.with(DynamicActivity.this).resumeRequestsRecursive();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {

                    DynamicDetailsEntity dynamic = mDynamicList.get(position);

                    String type = dynamic.getField_type();

                    String productId = dynamic.getX6_Product_Product();

                    long Id = Long.parseLong(productId);
                    AppLog.e(type);
                    if (TextUtils.isEmpty(type) || DynamicDetailsEntity.DYNAMIC_TYPE_SELF.equals(type)) {
                        UIHelper.gotoDynamicDetailActivity(DynamicActivity.this, mDynamicList.get(position));
                    } else if (DynamicDetailsEntity.DYNAMIC_TYPE_MEDIA_DETAIL.equals(type)) {
                        UIHelper.gotoMediaDetailActivity(DynamicActivity.this, Id);
                    } else if (DynamicDetailsEntity.DYNAMIC_TYPE_NEWS_DETAIL.equals(type)) {
                        UIHelper.gotoNewsDetailActivity(DynamicActivity.this, Id);
                    } else if (DynamicDetailsEntity.DYNAMIC_TYPE_GROUP_DETAIL.equals(type)) {
                        UIHelper.gotoGroupDetailActivity(DynamicActivity.this, Id);
                    } else if (DynamicDetailsEntity.DYNAMIC_TYPE_TOPIC_DETAIL.equals(type)) {
                        UIHelper.gotoTopicDetailActivity(DynamicActivity.this, Id);
                    } else if (DynamicDetailsEntity.DYNAMIC_TYPE_SHANGJIA.equals(type)) {
                        // TODO 商家
                        //UIHelper.gotoDynamicDetailActivity(DynamicActivity.this,dynamic);
                        UIHelper.gotoCateringDetailActivity(DynamicActivity.this, Id);
                    } else if (DynamicDetailsEntity.DYNAMIC_TYPE_TONGCHENG.equals(type)) {
                        // TODO 同城
                        UIHelper.gotoCityDetailsActivity(DynamicActivity.this,(int)Id);
                    }

                } catch (Exception e) {
                    // 捕获异常
                }

            }
        });

        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
            }
        });

        mPullToRefreshListView.doPullRefreshing(true, 100);
    }

    private void refreshListView(boolean pullDownRefresh) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }

        AppLog.i("DynamicListView", "刷新第" + mCurrentPage + "页");

        DynamicListRequest req = new DynamicListRequest(mListener);
        req.setRequestParams(mCurrentPage);
        HttpRequestQueue.addToRequestQueue(req);
    }

    HttpRequestListener<DynamicListEntity> mListener = new HttpRequestListener<DynamicListEntity>() {
        @Override
        public void onFinishRequest() {
            super.onFinishRequest();

            mPullToRefreshListView.onPullRefreshComplete();
        }

        @Override
        public void onResponse(DynamicListEntity response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mDynamicList.clear();
            }

            List<DynamicDetailsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mDynamicList.addAll(response.getRows());
            mDynamicListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mDynamicList.size()) {
                mPullToRefreshListView.setHasMoreData(false);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title_left:

                break;

            case R.id.tv_base_title_right:
                startActivity(new Intent(getApplicationContext(), PublishDynamicActivity.class));
                break;

            default:
                super.onClick(view);
                break;
        }
    }

    static class DynamicListAdapter extends AbsBaseAdapter<DynamicDetailsEntity> {

        public DynamicListAdapter(Context context, OnNestedClickListener onNestedClickListener) {
            super(context);
            this.mOnNestedClickListener = onNestedClickListener;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_dynamic, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final DynamicDetailsEntity row = getItem(position);

            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getField_YHID();
            Glide.with(mContext).load(url).placeholder(R.drawable.ic_default_portrait).into(holder.portrait);

            holder.portrait.setTag(holder.portrait.getId(), position);
            holder.portrait.setOnClickListener(mOnNestedClickListener);

            String comment = mContext.getResources().getString(R.string.item_dynamic_comment_count, row.getCommcount());
            holder.comment.setText(comment);
//            holder.comment.setTag(holder.comment.getId(), position);
//            holder.comment.setOnClickListener(mOnNestedClickListener);

            String picList = row.getX6_Product_PicList();
            if (!TextUtils.isEmpty(picList)) { // 不为空

                String[] urlList = picList.split(Constants.IMAGE_SPLIT_REGULAR);

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

                DynamicImagesAdapter adapter = new DynamicImagesAdapter(mContext);
                adapter.setRows(picUrlList);
                holder.images.setAdapter(adapter);
                holder.images.setClickable(false);
                holder.images.setFocusable(false);

                holder.images.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            ArrayList<String> imageList = new ArrayList<String>();

                            for (Map<String, String> map : picUrlList) {
                                imageList.add(map.get("large"));
                            }

                            UIHelper.gotoZoomGalleryActivity(mContext, imageList, position);
                        } catch (Exception e) {
                            // 捕获异常
                        }
                    }
                });
            }else{
                DynamicImagesAdapter adapter = new DynamicImagesAdapter(mContext);
                adapter.setRows(null);
                holder.images.setAdapter(adapter);
            }

            // 更新图片列表

            holder.name.setText(row.getField_DTYH());

            String interval = DateTimeUtils.getIntervalTime(row.getField_DTSJ());
            holder.date.setText(interval);
            holder.title.setDesc(row.getField_DTJL(), holder.title, TextView.BufferType.NORMAL);

            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.dynamic_portrait)
            CircleImageView portrait;
            @Bind(R.id.tv_item_dynamic_comment)
            TextView comment;
            @Bind(R.id.dynamic_name)
            TextView name;
            @Bind(R.id.dynamic_date)
            TextView date;
            @Bind(R.id.dynamic_images_title)
            CollapsibleTextView title;
            @Bind(R.id.dynamic_images_list)
            NoScrollGridView images;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

    }

    public static class DynamicImagesAdapter extends AbsBaseAdapter<Map<String, String>> {

        public DynamicImagesAdapter(Context context) {
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

}
