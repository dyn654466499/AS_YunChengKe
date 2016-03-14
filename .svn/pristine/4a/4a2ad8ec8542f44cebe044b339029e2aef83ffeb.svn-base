package com.yunchengke.app.dynamic.activity.homepage.dynamic;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.dynamic.activity.BaseActivity;
import com.yunchengke.app.dynamic.view.TitleActionBar;

public class OtherDynamicDetailsActivity extends BaseActivity {
	private TextView person_attention;

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
		findViewById(R.id.person_state).setVisibility(View.VISIBLE);
		person_attention = (TextView) findViewById(R.id.person_attention);
		person_attention.setOnClickListener(clickListener);
		findViewById(R.id.person_send_msg).setOnClickListener(clickListener);
	}
	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.person_attention:

				break;
			case R.id.person_send_msg:

				break;

			default:
				break;
			}
		}
	};
}
