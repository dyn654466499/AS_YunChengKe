package com.yunchengke.app.ui.activity.city;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.util.URLEncodedUtils;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.order.Resp_OrderLocalCityList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.ui.activity.daemon.OrderLocalCityDetailActivity;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.utils.ToastUtils;
import com.yunchengke.app.utils.daemon.DialogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;

import static com.yunchengke.app.consts.Constants.REQUEST_CODE_PAY;

public class ToPayActivity extends BaseActivity {

    public final static String EXTRA_TIME = "EXTRA_TIME";
    public final static String EXTRA_PRICE = "EXTRA_PRICE";
    public final static String EXTRA_ID = "EXTRA_ID";
    private String mTime;
    private String mPrice,pid;
    private int num = 1;
    @Bind(R.id.time_rb1)
    RadioButton mTimeRB1;
    @Bind(R.id.time_rb2)
    RadioButton mTimeRB2;
    @Bind(R.id.time_rb3)
    RadioButton mTimeRB3;
    @Bind(R.id.price_rb1)
    RadioButton mPriceRB1;
    @Bind(R.id.price_rb2)
    RadioButton mPriceRB2;
    @Bind(R.id.price_rb3)
    RadioButton mPriceRB3;
    @Bind(R.id.time_rg)
    RadioGroup mTimeRG;
    @Bind(R.id.price_rg)
    RadioGroup mPriceRG;
    @Bind(R.id.minus)
    Button mMinus;
    @Bind(R.id.add)
    Button mAdd;
    @Bind(R.id.num)
    TextView mNum;
    @Bind(R.id.btn_city_pay)
    Button pay;

   public static String id;
    @OnClick(R.id.btn_city_pay)
    public void btn_city_pay(View view) {


        DialogUtil.showDialog(ToPayActivity.this, "提示！", "确定下单了吗？", new Commands() {
            @Override
            public void executeCommand(Message msg_params) {

                String url = "http://www.icityto.com/X_UserLogic/yesicity2015/canyinactive_JsonAdd";

                Req req = new Req();
                req.id =Integer.parseInt(pid);
                req.peoples =Integer.parseInt(mNum.getText().toString().trim());
                req.money =Integer.parseInt(mPrice);
                Gson gson = new Gson();
                String s = gson.toJson(req);
                Log.d("syb",s);
                HttpUtils http = new HttpUtils();
                RequestParams param = new RequestParams();
                param.addQueryStringParameter("UId", "yesicity2015");
                param.addQueryStringParameter("param", s);
                param.addQueryStringParameter("Field_YHID", LoginInfo.getInstance().getId());
                param.addQueryStringParameter("Yesicity", "1");
                http.send(HttpRequest.HttpMethod.GET,
                        url,
                        param,
                        new RequestCallBack<String>() {
                            @Override
                            public void onLoading(long total, long current, boolean isUploading) {
                            }

                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                Log.d("syb 0", responseInfo.result);
                                try {
                                    JSONObject jsonObject = new JSONObject(responseInfo.result);
                                    String resultState = jsonObject.getString("resultState");

                                    Log.d("syb 1", responseInfo.result);

                                    if ("success".equals(resultState))
                                    {
                                        Log.d("syb 2", responseInfo.result);
                                        id = jsonObject.getString("Id");
                                        gotoDetail();
                                    }else {
                                        DialogUtil.showDialog(mContext, "提示！", jsonObject.getString("message"), new Commands() {
                                            @Override
                                            public void executeCommand(Message msg_params) {

                                            }
                                        });
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.d("syb", "e"+e);
                                }
                            }

                            @Override
                            public void onStart() {
                                Log.d("syb", "onStart");
                            }

                            @Override
                            public void onFailure(HttpException error, String msg) {
                                Log.d("syb", "onFailure" + msg);
                                ToastUtils.show(mContext,msg);
                            }
                        });


            }
        });
    }

    private void gotoDetail() {

        Log.d("syb","id="+id);
        Log.d("syb", LoginInfo.getInstance().getId());
        HttpUtils http = new HttpUtils();
        RequestParams param = new RequestParams();
        param.addQueryStringParameter("UId", "yesicity2015");
        param.addQueryStringParameter("Id", id);
        param.addQueryStringParameter("Field_YHID", LoginInfo.getInstance().getId());
        param.addQueryStringParameter("Yesicity", "1");
        http.send(HttpRequest.HttpMethod.GET,
                "http://www.icityto.com/X_UserLogic/yesicity2015/MyActiveinfo_Page",
                param,
                new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.d("syb","MyActiveinfo_Page 2"+ responseInfo.result);
                        try {
                            Gson gson = new Gson();
                            Resp_OrderLocalCityList.Rows rows = gson.fromJson(responseInfo.result, Resp_OrderLocalCityList.Rows.class);
                            Intent intent = new Intent(mContext, OrderLocalCityDetailActivity.class);
                            rows.X6_Product_Id = id;
                            intent.putExtra(Constants.KEY_PARCELABLE, rows);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtils.show(mContext,s);
                    }
                }
    );


    }

    Context mContext;

    @Override
    protected View createContentView(ViewGroup parent) {
        View view = inflate(R.layout.activity_top_pay, parent);
        mContext = this;
        return view;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        lytTitle.setBackgroundResource(R.color.city_background);
        setTitle("参加");
        mTime = getIntent().getStringExtra(EXTRA_TIME);

        mPrice = getIntent().getStringExtra(EXTRA_PRICE);
        pid = getIntent().getStringExtra(EXTRA_ID);
        setTitleLeft(R.string.back);
        mTimeRB1.setText(mTime);
        mTimeRB2.setText(mTime);
        mTimeRB3.setText(mTime);
        mPriceRB1.setText(mPrice);
        mPriceRB2.setText(mPrice);
        mPriceRB3.setText(mPrice);
        mMinus.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mNum.setText(String.valueOf(num));
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_base_title_left) {
            finish();
        } else if (v.getId() == R.id.minus) {
            if (num <= 1) {
                ToastUtils.show(this, "已经是最小数量");
                return;
            }
            num = num - 1;
            mNum.setText(String.valueOf(num));
        } else if (v.getId() == R.id.add) {
            num = num + 1;
            mNum.setText(String.valueOf(num));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 只有resultCode == RESULT_OK，intent data才不是null
         */
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_PAY:
                    String msg = "";
                    /**
                     * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
                     **/
                    String str = data.getExtras().getString("pay_result");
                    if (str.equalsIgnoreCase("success")) {
                        msg = "支付成功！";
                        finish();
                    } else if (str.equalsIgnoreCase("fail")) {
                        msg = "支付失败！";
                    } else if (str.equalsIgnoreCase("cancel")) {
                        msg = "取消支付";
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
