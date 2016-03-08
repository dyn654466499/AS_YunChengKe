package com.yunchengke.app.dynamic.activity.homepage.dynamic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yunchengke.app.R;
import com.yunchengke.app.dynamic.activity.BaseActivity;
import com.yunchengke.app.dynamic.data.BaseParam;
import com.yunchengke.app.dynamic.entity.OtherDynamicEntity;
import com.yunchengke.app.dynamic.entity.OtherDynamicListEntity;
import com.yunchengke.app.dynamic.view.RoundImageView;
import com.yunchengke.app.dynamic.view.TitleActionBar;
import com.yunchengke.app.dynamic.xutils.HttpUtils;
import com.yunchengke.app.dynamic.xutils.exception.HttpException;
import com.yunchengke.app.dynamic.xutils.http.ResponseInfo;
import com.yunchengke.app.dynamic.xutils.http.callback.RequestCallBack;
import com.yunchengke.app.dynamic.xutils.http.client.HttpRequest.HttpMethod;

public class MyDynamicDetailsActivity extends BaseActivity {
	private HttpUtils httpUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_person_dynamic);
		TitleActionBar titleActionBar = getTitleActionBar();

		titleActionBar.setAppTitleLeftButton(0, new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		titleActionBar.getAppTopTitle().setVisibility(View.GONE);
		titleActionBar.getTopTitle().setBackgroundColor(getResources().getColor(R.color.person_msg_bg));
		titleActionBar.setAppTitleRightButton1("编辑资料", 0, new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		initView();
	}

	private void initView() {
		httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, BaseParam.ServerAddress + BaseParam.my_dynamic, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				Log.d("jsondata", result);
				Gson gson = new Gson();
				OtherDynamicListEntity dynamicListEntity = gson.fromJson(result, OtherDynamicListEntity.class);
				OtherDynamicEntity otherDynamicEntity = dynamicListEntity.getRows().get(0);
				person_location.setText(otherDynamicEntity.getField_SZCS());
				person_attention_number.setText(otherDynamicEntity.getFollowed());
				person_fans_number.setText(otherDynamicEntity.getFollow_Me());
//				if ("0".equals(otherDynamicEntity.getIsFollow())) {
//					person_attention.setText("未关注");
//				} else {
//					person_attention.setText("已关注");
//				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Log.e("jsondata", msg);
			}
		});
		person_name = (TextView) findViewById(R.id.person_name);
		person_location = (TextView) findViewById(R.id.person_location);
		person_attention_number = (TextView) findViewById(R.id.person_attention_number);
		person_fans_number = (TextView) findViewById(R.id.person_fans_number);
		
		RoundImageView person_portrait = (RoundImageView) findViewById(R.id.person_portrait);
		ImageLoader.getInstance().displayImage("http://www.icityto.com/X_UserFileLogic/X_yesicity2015/X_TX/?TX=8", person_portrait);
	}

	
	private TextView person_name;
	private TextView person_location;
	private TextView person_attention_number;
	private TextView person_fans_number;
}
