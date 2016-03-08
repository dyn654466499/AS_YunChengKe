package com.yunchengke.app.ui.activity.city;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.yunchengke.app.R;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.http.request.PublishDynamicRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.city.YCKPhotoAdapter;
import com.yunchengke.app.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YCKPhotoActivity extends BaseActivity implements AdapterView.OnItemClickListener {

	public final static String EXTRA_PICTURE = "EXTRA_PICTURE";

	private GridView mGridView;

	private YCKPhotoAdapter mAdapter;

	private String picList;

	private ArrayList<String> imageList = new ArrayList<String>();

	@Override
	protected View createContentView(ViewGroup parent) {
		return inflate(R.layout.layout_yck_phoho, parent);
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		super.initViews(savedInstanceState);
		picList = getIntent().getStringExtra(EXTRA_PICTURE);
		lytTitle.setBackgroundResource(R.color.city_background);
		setTitle("图片列表");
		setTitleLeft(R.string.string_back);
		mGridView = (GridView) findViewById(R.id.yck_photo_grid);

		if (picList!=null) {
			String[] urlList = picList.split(Constants.IMAGE_SPLIT_REGULAR);

			final ArrayList<Map<String, String>> picUrlList = new ArrayList<Map<String, String>>();

			for (String imagePairUrl : urlList) {
				String[] pairUrl = imagePairUrl.split(Constants.SUB_IMAGE_SPLIT_REGULAR);
				try {
					Map<String, String> pairUrlMap = new HashMap<String, String>();
					pairUrlMap.put("small", pairUrl[0]);
					pairUrlMap.put("large", pairUrl[1]);
					imageList.add(pairUrl[1]);
					picUrlList.add(pairUrlMap);
				} catch (Exception e) {
					// 捕获异常
				}
			}
			mAdapter = new YCKPhotoAdapter(YCKPhotoActivity.this, picUrlList);
			mGridView.setAdapter(mAdapter);
			mGridView.setOnItemClickListener(this);
		}
	}


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
		switch (v.getId()) {
			case R.id.tv_base_title_left:
				finish();
				break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		UIHelper.gotoZoomGalleryActivity(this, imageList, position);
	}
}
