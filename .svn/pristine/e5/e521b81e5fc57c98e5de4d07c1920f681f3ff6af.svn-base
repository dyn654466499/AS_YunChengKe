package com.yunchengke.app.ui.activity.daemon;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.yunchengke.app.R;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.utils.daemon.BaiduMapUtil;

public class MapActivity extends BaseActivity {
    /**
     * 地图View
     */
    private MapView mapView;
    /**
     * 地图控制者
     */
    private BaiduMap mBaiduMap;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Button btn_title_back = (Button) findViewById(R.id.btn_title_back);
        btn_title_back.setOnClickListener(this);

        if(getIntent().hasExtra(Constants.KEY)) {
            double mLat2 = 40.056858;
            double mLon2 = 116.308194;
          if(!TextUtils.isEmpty(getIntent().getStringExtra(Constants.KEY))) {
              String[] zb = getIntent().getStringExtra(Constants.KEY).split(",");

              TextView tv_title = (TextView) findViewById(R.id.tv_title);
              tv_title.setText("商家详情");

              RelativeLayout relativeLayout_mapView = (RelativeLayout) findViewById(R.id.relativeLayout_mapView);
              mapView = BaiduMapUtil.locateSearchPlace(this, new LatLng(Double.valueOf(zb[1]), Double.valueOf(zb[0])));
              //mapView = BaiduMapUtil.locateSearchPlace(this, new LatLng(mLat2, mLon2));
              relativeLayout_mapView.addView(mapView);
          }else{
              showTip("该商家尚未提供不地址！");
          }
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
