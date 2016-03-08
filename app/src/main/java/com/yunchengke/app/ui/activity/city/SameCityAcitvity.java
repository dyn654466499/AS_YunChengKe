package com.yunchengke.app.ui.activity.city;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.fragments.city.CityAllFragment;
import com.yunchengke.app.ui.fragments.city.CityMyselfFragment;
import com.yunchengke.app.ui.fragments.city.HotsFragment;
import com.yunchengke.app.ui.widget.SegmentedGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zgw_android on 2016/1/29.
 * zhang
 */


public class SameCityAcitvity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    @Bind(R.id.hots)
    RadioButton hots;
    @Bind(R.id.city_all)
    RadioButton cityAll;
    @Bind(R.id.city_myself)
    RadioButton cityMyself;
    @Bind(R.id.sgp_city_top)
    SegmentedGroup sgpCityTop;
    @Bind(R.id.tv_base_title_left)
    TextView tvBaseTitleLeft;
    @Bind(R.id.tv_base_title_right)
    TextView tvBaseTitleRight;
    @Bind(R.id.ed_cityactive_keyword)
    EditText edCityactiveKeyword;
    @Bind(R.id.tv_base_title_cancel_right)
    TextView tvBaseTitleCancelRight;


    private String keyword = "";

    private HotsFragment mHotsFragment;

    private CityAllFragment mCityAllFragment;

    private CityMyselfFragment mCityMyselfFragment;

    private boolean IsCityMyself = false;

    private int isWhich = 1;

    private static final int HOT_FRAGMENT = 0;
    private static final int CITY_FRAGMENT = 1;
    private static final int CITY_MYSELF_FRAGMENT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_city);
        ButterKnife.bind(this);
        initView();
        sgpCityTop.setOnCheckedChangeListener(this);
        tvBaseTitleCancelRight.setOnClickListener(this);
        edCityactiveKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s != null) {
                    tvBaseTitleCancelRight.setVisibility(View.GONE);
                    tvBaseTitleRight.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("update_samecity");
        registerReceiver(mMessageReceiver, filter);

    }
    private MessageReceiver mMessageReceiver;

    private void initView() {
        tvBaseTitleLeft.setText("返回");
        tvBaseTitleRight.setText("搜索");
        setSelect(CITY_FRAGMENT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMessageReceiver!=null){
            unregisterReceiver(mMessageReceiver);
        }
    }

    @Override
    @OnClick({R.id.tv_base_title_right})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_base_title_right:
                if (IsCityMyself) {

//                    UIHelper.gotoToPayActivity(this,"2月8日 周五 20：00","40");
                    startActivity(new Intent(SameCityAcitvity.this, AddCreateActivities.class));
                } else {
                    if (TextUtils.isEmpty(edCityactiveKeyword.getText().toString())) {
                        edCityactiveKeyword.setVisibility(View.VISIBLE);
                        sgpCityTop.setVisibility(View.GONE);
                        tvBaseTitleLeft.setVisibility(View.GONE);
                        tvBaseTitleRight.setVisibility(View.GONE);
                        tvBaseTitleCancelRight.setVisibility(View.VISIBLE);
                    } else {
                        edCityactiveKeyword.setVisibility(View.VISIBLE);
                        sgpCityTop.setVisibility(View.GONE);
                        tvBaseTitleLeft.setVisibility(View.GONE);
                        tvBaseTitleRight.setVisibility(View.GONE);
                        tvBaseTitleCancelRight.setVisibility(View.VISIBLE);
                        edCityactiveKeyword.setText("");
                        setSelect(isWhich);

                    }
                }
                break;

            case R.id.tv_base_title_cancel_right:
                tvBaseTitleCancelRight.setVisibility(View.GONE);
                edCityactiveKeyword.setVisibility(View.GONE);
                sgpCityTop.setVisibility(View.VISIBLE);
                tvBaseTitleLeft.setVisibility(View.VISIBLE);
                tvBaseTitleRight.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_base_title_left:

                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.hots:
                IsCityMyself = false;
                ChangeTitleText();
                setSelect(HOT_FRAGMENT);
                break;
            case R.id.city_all:
                IsCityMyself = false;
                ChangeTitleText();
                setSelect(CITY_FRAGMENT);
                break;
            case R.id.city_myself:
                IsCityMyself = true;
                ChangeTitleText();
                setSelect(CITY_MYSELF_FRAGMENT);
                break;
        }
    }


    public void setSelect(int i) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case HOT_FRAGMENT:
                if (mHotsFragment == null) {
                    mHotsFragment = new HotsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("keyword", keyword);
                    isWhich = HOT_FRAGMENT;
                    mHotsFragment.setArguments(bundle);
                    transaction.add(R.id.city_content, mHotsFragment, "hot");
                } else {
                    transaction.show(mHotsFragment);
                }
                break;
            case CITY_FRAGMENT:
                if (mCityAllFragment == null) {
                    mCityAllFragment = new CityAllFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("keyword", keyword);
                    isWhich = CITY_FRAGMENT;
                    mCityAllFragment.setArguments(bundle);
                    transaction.add(R.id.city_content, mCityAllFragment, "city");
                } else {
                    transaction.show(mCityAllFragment);
                }
                break;
            case CITY_MYSELF_FRAGMENT:
                if (mCityMyselfFragment == null) {
                    mCityMyselfFragment = new CityMyselfFragment();
                    transaction.add(R.id.city_content, mCityMyselfFragment, "city_my");
                } else {
                    transaction.show(mCityMyselfFragment);
                }
                break;
        }
        transaction.commit();
    }

    public void hideFragment(FragmentTransaction transaction) {
        if (mHotsFragment != null) {
            transaction.hide(mHotsFragment);
        }

        if (mCityAllFragment != null) {
            transaction.hide(mCityAllFragment);
        }
        if (mCityMyselfFragment != null) {
            transaction.hide(mCityMyselfFragment);
        }
    }

    private void ChangeTitleText() {


        if (IsCityMyself) {
            tvBaseTitleRight.setText("发起");

        } else {
            tvBaseTitleRight.setText("搜索");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isWhich = savedInstanceState.getInt("position");
        setSelect(isWhich);
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //记录当前的position
        outState.putInt("position", isWhich);
    }


    public class MessageReceiver extends BroadcastReceiver {
        private static final String TAG = "MyReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            AppLog.e(TAG, "tag");
            if(mCityMyselfFragment!=null){
                mCityMyselfFragment.refresh();
            }

        }
    }

}
