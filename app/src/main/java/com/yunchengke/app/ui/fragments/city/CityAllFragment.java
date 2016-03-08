package com.yunchengke.app.ui.fragments.city;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.yunchengke.app.bean.city.CityActiveList;
import com.yunchengke.app.bean.city.CityType;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.CityActiveListResquest;
import com.yunchengke.app.http.request.GetCityTypeRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.widget.ExpandPopTabView;
import com.yunchengke.app.ui.widget.PopOneListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zgw_android on 2016/1/29.
 * 全部
 */
public class CityAllFragment extends Fragment implements AdapterView.OnItemClickListener {
    private boolean mPullDownRefresh;
    private int mPrePage; // 上一个页数
    private int mCurrentPage; // 当前页数
    private String keyword  ="" ;

    @Bind(R.id.lv_city_list)
    PullToRefreshListView lvCityList;
    @Bind(R.id.expandtab_view)
    ExpandPopTabView expandtabView;
    private List<CityType.RowsEntity> mArea = new ArrayList<CityType.RowsEntity>();
    private List<CityType.RowsEntity> mTime = new ArrayList<CityType.RowsEntity>();
    private List<CityType.RowsEntity> mType;

    private List<CityActiveList.RowsEntity> mCityActiveList;
    private CityActiveListAdapter mCityActiveListAdapter;

    private String activeArea = "";

    private String activeTime = "";

    private String activeType = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_cityall, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public void addItem(ExpandPopTabView expandTabView, List<CityType.RowsEntity> lists, String defaultSelect, String defaultShowText) {
        PopOneListView popOneListView = new PopOneListView(getActivity());
        popOneListView.setDefaultSelectByValue(defaultSelect);
        popOneListView.setCallBackAndData(lists, expandTabView, new PopOneListView.OnSelectListener() {
            @Override
            public void getValue(String key, String value) {
                switch ((Integer) expandtabView.mSelectedToggleBtn.getTag()) {
                    case 0:
                        activeArea = value;
                        Log.i("activeArea", activeArea);
                        mCurrentPage = 1;
                        refreshListView(true);
                        break;
                    case 1:
                        activeTime = value;
                        mCurrentPage = 1;
                        refreshListView(true);
                        Log.i("activeTime", activeTime);
                        break;
                    case 2:
                        refreshListView(true);
                        mCurrentPage = 1;
                        activeType = value;
                        Log.i("activeType", activeType);
                        break;
                }
                Log.e("tag", "key :" + key + " ,value :" + value);


            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popOneListView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        keyword= getArguments().getString("keyword");
        initData();

    }

    private void initData() {
        setConfigData();

        lvCityList.setScrollLoadEnabled(true);

        lvCityList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView(false);
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
        lvCityList.doPullRefreshing(true, 100);
        lvCityList.getRefreshableView().setOnItemClickListener(this);


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

        CityActiveListResquest req = new CityActiveListResquest(mCityListListener);

            AppLog.i("activeType", activeType );
        req.setRequestParams(""
                    , "", "", mCurrentPage, 0, "");
        HttpRequestQueue.addToRequestQueue(req);
    };


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
            AppLog.e("更新URL：" + response.getRows().size()+"");
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
                convertView = View
                        .inflate(mContext,R.layout.item_city_active_list, null);
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
            holder.row.setText(row.getField_HDBMRS()+"人参加");
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
            TextView row;
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
    private void setConfigData() {
        CityType.RowsEntity keyValueBean1 = new CityType.RowsEntity();
        keyValueBean1.setX6_Product_Id(1);
        keyValueBean1.setField_HDFL("全部");
        mArea.add(keyValueBean1);

        CityType.RowsEntity keyValueBean2 = new CityType.RowsEntity();
        keyValueBean2.setX6_Product_Id(2);
        keyValueBean2.setField_HDFL("宁波");
        mArea.add(keyValueBean2);

        CityType.RowsEntity keyValueBean3 = new CityType.RowsEntity();
        keyValueBean3.setX6_Product_Id(3);
        keyValueBean3.setField_HDFL("其他");
        mArea.add(keyValueBean3);

        CityType.RowsEntity keyValueBean4 = new CityType.RowsEntity();
        keyValueBean4.setX6_Product_Id(1);
        keyValueBean4.setField_HDFL("今天");
        mTime.add(keyValueBean4);

        CityType.RowsEntity keyValueBean5 = new CityType.RowsEntity();
        keyValueBean5.setX6_Product_Id(2);
        keyValueBean5.setField_HDFL("明天");
        mTime.add(keyValueBean5);

        CityType.RowsEntity keyValueBean6 = new CityType.RowsEntity();
        keyValueBean6.setX6_Product_Id(3);
        keyValueBean6.setField_HDFL("最近一周");
        mTime.add(keyValueBean6);

        CityType.RowsEntity keyValueBean7 = new CityType.RowsEntity();
        keyValueBean7.setX6_Product_Id(4);
        keyValueBean7.setField_HDFL("更多");
        mTime.add(keyValueBean7);
        GetCityTypeRequest req = new GetCityTypeRequest(mListener);
        HttpRequestQueue.addToRequestQueue(req);


    }

    HttpRequestListener<CityType> mListener = new HttpRequestListener<CityType>() {
        @Override
        public void onResponse(CityType response) {
            super.onResponse(response);
            Log.d("response", response.toString());
            mType = response.getRows();
            addItem(expandtabView, mArea, "", "地区");
            addItem(expandtabView, mTime, "", "时间");
            addItem(expandtabView, mType, "", "类型");
        }

    };


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("activeTime", activeTime);
        Log.i("activeType", activeType);
        Log.i("activeArea", activeArea);
        expandtabView.onExpandPopView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
