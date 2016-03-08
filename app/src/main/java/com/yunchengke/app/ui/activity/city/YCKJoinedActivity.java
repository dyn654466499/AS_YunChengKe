package com.yunchengke.app.ui.activity.city;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.city.YCKJoinedList;
import com.yunchengke.app.bean.dynamic.DynamicDetailsEntity;
import com.yunchengke.app.bean.dynamic.DynamicListEntity;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.DynamicListRequest;
import com.yunchengke.app.http.request.YCKJoinedListRequest;
import com.yunchengke.app.ui.adapter.city.JoinedListAdapter;
import com.yunchengke.app.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class YCKJoinedActivity extends BaseActivity {

	public final static String EXTRA_CITY_ID = "EXTRA_CITY_ID";
	private boolean mPullDownRefresh;
	private int mPrePage; // 上一个页数
	private int mCurrentPage; // 当前页数
	private List<YCKJoinedList.RowsEntity> mYCKJoinedList;
	private JoinedListAdapter mAdapter;

	private PullToRefreshListView mPullToRefreshListView;
	private int mId = 23;

	@Override
	protected View createContentView(ViewGroup parent) {
		View view = inflate(R.layout.layout_yck_joined, parent);
		return view;
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		super.initViews(savedInstanceState);


		lytTitle.setBackgroundResource(R.color.dynamic_title_background);
		setTitle("已参加");
		setTitleLeft(R.string.back);

		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.plv_yck_joined_content);
		mPullToRefreshListView.setPullRefreshEnabled(true);
		mPullToRefreshListView.setScrollLoadEnabled(true);
		mId = getIntent().getIntExtra(EXTRA_CITY_ID,0);

		ListView listView = mPullToRefreshListView.getRefreshableView();
		listView.setDivider(getResources().getDrawable(R.color.transparent));
		listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.line_height));
		mYCKJoinedList = new ArrayList<YCKJoinedList.RowsEntity>();
		mAdapter = new JoinedListAdapter(this);
		listView.setAdapter(mAdapter);
		mAdapter.setRows(mYCKJoinedList);
		mPullToRefreshListView.doPullRefreshing(true, 100);

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


	}

	private void refreshListView(boolean pullDownRefresh) {
		mPrePage = mCurrentPage;
		if (pullDownRefresh) {
			mPullDownRefresh = true;
			mCurrentPage = 1;
		} else {
			mCurrentPage++;
		}

		YCKJoinedListRequest req = new YCKJoinedListRequest(mListener);
		req.setRequestParams(mId);
		HttpRequestQueue.addToRequestQueue(req);

	}

	HttpRequestListener<YCKJoinedList> mListener = new HttpRequestListener<YCKJoinedList>() {
		@Override
		public void onFinishRequest() {
			super.onFinishRequest();

			mPullToRefreshListView.onPullRefreshComplete();
		}

		@Override
		public void onResponse(YCKJoinedList response) {
			super.onResponse(response);

			if (mCurrentPage == 1 || mPullDownRefresh) {
				mPullDownRefresh = false;

				mYCKJoinedList.clear();
			}

			List<YCKJoinedList.RowsEntity> row = response.getRows();

			// 是否未取到书库
			if (row == null || row.size() <= 0) {
				// 错误处理
				mPullDownRefresh = false;
				mCurrentPage = mPrePage;
			}

			mYCKJoinedList.addAll(response.getRows());
			mAdapter.notifyDataSetChanged();

			if (response.getTotal() <= mYCKJoinedList.size()) {
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
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}


	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.tv_base_title_left ){

				finish();
		}

	}
}
