package com.yunchengke.app.ui.fragments.city;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.city.CityActiveList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.MyAddCityActiveListResquest;
import com.yunchengke.app.http.request.MyFollowCityActiveListResquest;
import com.yunchengke.app.http.request.MyJoinCityActiveListResquest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zgw_android on 2016/1/29.
 * <p/>
 * 我的
 */
public class CityMyselfFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {

    private static final int MY_SEND_ACTIVE = 1;
    private static final int MY_ATTR_ACTIVE = 2;
    private static final int MY_FLOOW_ACTIVE = 3;

    @Bind(R.id.rg_my_city_active)
    RadioGroup rgMyCityActive;
    @Bind(R.id.lv_city_list)
    PullToRefreshListView lvCityList;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数
    private boolean mPullDownRefresh;

    private int mSelectStatus = 1;


    private List<CityActiveList.RowsEntity> mCityActiveList;
    private CityActiveListAdapter mCityActiveListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_citymyself, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rgMyCityActive.setOnCheckedChangeListener(this);
        initData();


    }


    private void initData() {

        lvCityList.setScrollLoadEnabled(true);

        lvCityList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true, mSelectStatus);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false, mSelectStatus);
            }
        });

        mCityActiveList = new ArrayList<CityActiveList.RowsEntity>();
        mCityActiveListAdapter = new CityActiveListAdapter(getActivity());
        mCityActiveListAdapter.setRows(mCityActiveList);

        ListView listView = lvCityList.getRefreshableView();
        listView.setAdapter(mCityActiveListAdapter);
        listView.setDivider(getResources().getDrawable(R.color.topic_list_divider_color));
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.activity_margin_bottom));
        listView.setFooterDividersEnabled(true);
        listView.setOnItemClickListener(this);
        lvCityList.doPullRefreshing(true, 100);

    }

    public void refresh(){
        if(lvCityList!=null){
            lvCityList.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshListView(true,MY_SEND_ACTIVE);
                }
            }, 100);
        }

    }

    private void refreshListView(boolean pullDownRefresh, int status) {
        mPrePage = mCurrentPage;
        if (pullDownRefresh) {
            mPullDownRefresh = true;
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
        }
        AppLog.i("NewsListView", "刷新第" + mCurrentPage + "页");
        switch (status) {
            case MY_SEND_ACTIVE:
                MyAddCityActiveListResquest myAddCityActiveListResquest = new MyAddCityActiveListResquest(mCityListListener);
                myAddCityActiveListResquest.setRequestParams(mCurrentPage);
                HttpRequestQueue.addToRequestQueue(myAddCityActiveListResquest);
                break;
            case MY_ATTR_ACTIVE:

                MyJoinCityActiveListResquest myJoinCityActiveListResquest = new MyJoinCityActiveListResquest(mCityListListener);
                myJoinCityActiveListResquest.setRequestParams(mCurrentPage);
                HttpRequestQueue.addToRequestQueue(myJoinCityActiveListResquest);
                break;

            case MY_FLOOW_ACTIVE:
                MyFollowCityActiveListResquest myFollowCityActiveListResquest = new MyFollowCityActiveListResquest(mCityListListener);
                myFollowCityActiveListResquest.setRequestParams(mCurrentPage);
                HttpRequestQueue.addToRequestQueue(myFollowCityActiveListResquest);

                break;

        }

        ;
    }


    HttpRequestListener<CityActiveList> mCityListListener = new HttpRequestListener<CityActiveList>() {
        @Override
        public void onFinishRequest() {
            super.onFinishRequest();
            lvCityList.onPullRefreshComplete();
        }

        @Override
        public void onResponse(CityActiveList response) {
            super.onResponse(response);

            if (mCurrentPage == 1 || mPullDownRefresh) {
                mPullDownRefresh = false;

                mCityActiveList.clear();
            }

            List<CityActiveList.RowsEntity> row = response.getRows();

            // 是否未取到书库
            if (row == null || row.size() <= 0) {
                // 错误处理
                mPullDownRefresh = false;
                mCurrentPage = mPrePage;
            }

            mCityActiveList.addAll(response.getRows());
            mCityActiveListAdapter.notifyDataSetChanged();

            if (response.getTotal() <= mCityActiveList.size()) {
                lvCityList.setHasMoreData(false);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_my_send_active:
                mSelectStatus = MY_SEND_ACTIVE;
                refreshListView(true, MY_SEND_ACTIVE);
                break;
            case R.id.rb_my_attr_active:
                mSelectStatus = MY_ATTR_ACTIVE;
                refreshListView(true, MY_ATTR_ACTIVE);
                break;
            case R.id.rb_my_floow_active:
                mSelectStatus = MY_FLOOW_ACTIVE;
                refreshListView(true, MY_ATTR_ACTIVE);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CityActiveList.RowsEntity rowsEntity = (CityActiveList.RowsEntity)parent.getItemAtPosition(position);
        UIHelper.gotoCityDetailsActivity(getActivity(), rowsEntity.getX6_Product_Id());
    }


    static class CityActiveListAdapter extends AbsBaseAdapter<CityActiveList.RowsEntity> {




        public CityActiveListAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView =
                        View.inflate(mContext, R.layout.item_city_active_list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            CityActiveList.RowsEntity row = getRows().get(position);

            String url = row.getX6_Product_Pic();
            AppLog.e("更新URL：" + url);
            Glide.with(mContext)
                    .load(url)
                    .into(holder.ivActiveCity);
            holder.tvActiveAddresss.setText(row.getField_HDDD());
            holder.tvActiveAttend.setText(row.getField_HDBMRS()+"人参加");
            holder.tvActiveFollow.setText(row.getGzrs()+"人关注");
            holder.tvActiveTitle.setText(row.getField_HDMC());
            holder.tvActiveType.setText(row.getField_HDFL());
            holder.tvActiveTime.setText(row.getField_HDSJ());
            return convertView;
        }

        static class ViewHolder {
            @Bind(R.id.iv_active_city)
            ImageView ivActiveCity;
            @Bind(R.id.tv_active_attend)
            TextView tvActiveAttend;
            @Bind(R.id.tv_active_follow)
            TextView tvActiveFollow;
            @Bind(R.id.tv_active_title)
            TextView tvActiveTitle;
            @Bind(R.id.tv_active_type)
            TextView tvActiveType;
            @Bind(R.id.tv_active_addresss)
            TextView tvActiveAddresss;
            @Bind(R.id.tv_active_time)
            TextView tvActiveTime;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
