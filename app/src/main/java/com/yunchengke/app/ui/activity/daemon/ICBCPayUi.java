package com.yunchengke.app.ui.activity.daemon;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yunchengke.app.R;
import com.yunchengke.app.pay.icbcpay.ICBCPay;
import com.yunchengke.app.utils.RegexUtils;
import com.yunchengke.app.utils.TimeCountUtil;
import com.yunchengke.app.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 作者：Tinchy
 * 创建时间：2016/2/23 23:31
 * 描述：
 * 版本：1.0
 */
public class ICBCPayUi extends com.yunchengke.app.ui.base.BaseActivity implements View.OnClickListener {

    @Bind(R.id.et_pay_card_phone)
    EditText etPayCardPhone;
    @Bind(R.id.et_pay_card_id)
    EditText etPayCardId;
    @Bind(R.id.pay_code)
    EditText payCode;
    @Bind(R.id.get_paycode)
    Button getPaycode;
    @Bind(R.id.bt_pay_money)
    Button btPayMoney;

    private String price;
    @Override
    protected View createContentView(ViewGroup parent) {
        View view = inflate(R.layout.activity_icbc_pay_ui, parent);
        return view;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        String orderprice =   getIntent().getStringExtra("orderPrice");
        if(!TextUtils.isEmpty(orderprice)){
            Float price_f = Float.parseFloat(orderprice)*100;
            price = String.valueOf(price_f.intValue());
            Log.d("orderPriceICBC", price+"");
        }

        lytTitle.setBackgroundResource(R.color.login_background);
        setTitle("工行支付");
        setTitleLeft(R.string.back);
    }
    @Override
    @OnClick({R.id.tv_base_title_left, R.id.get_paycode,R.id.bt_pay_money})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;
            case R.id.get_paycode:
                if (RegexUtils.checkPhone(etPayCardPhone.getText().toString())
                        && !TextUtils.isEmpty(etPayCardId.getText().toString())) {
                    TimeCountUtil timeCountUtil = new TimeCountUtil(this, 60000, 1000, getPaycode);
                    timeCountUtil.start();
                    ICBCPay.startPayApply(this, etPayCardPhone.getText().toString(),etPayCardId.getText().toString(),price);
                } else {
                    ToastUtils.show(this, "请输入正确的手机号");
                }

                break;
            case R.id.bt_pay_money:

                if (RegexUtils.checkPhone(etPayCardPhone.getText().toString())
                        && !TextUtils.isEmpty(etPayCardId.getText().toString()) &&
                        !TextUtils.isEmpty(payCode.getText().toString())) {
                    ICBCPay.startPayPost(this, payCode.getText().toString());

                } else {
                    ToastUtils.show(this, "请输入完整信息");
            }
                break;

            default:
                break;
        }

    }



}
