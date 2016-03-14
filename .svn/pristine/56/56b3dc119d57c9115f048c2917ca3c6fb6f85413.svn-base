package com.yunchengke.app.ui.activity.daemon;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.consts.Constants;

public class CateringMerchantInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_merchant_info);
        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("商家信息");

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);
        if(getIntent().hasExtra(Constants.KEY)){
            TextView tv_catering_merchant_info_content = (TextView)findViewById(R.id.tv_catering_merchant_info_content);
            tv_catering_merchant_info_content.setText("         "+getIntent().getStringExtra(Constants.KEY));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title_back:
                finish();
                break;
        }
    }

    @Override
    public void onViewChange(Message msg) {

    }
}
