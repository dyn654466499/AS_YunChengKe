package com.yunchengke.app.ui.activity.maintab;

import android.os.Bundle;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;

public class MyAct extends KeyBackStopActivity {

	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_home);
//		EditText et= () findViewById(R.id.et);
		tv = (TextView) findViewById(R.id.show_id);
		tv.setText(Application.loginInfo.getId());
	}
	
}
