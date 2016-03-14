package com.yunchengke.app.ui.activity.daemon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.order.Resp_OrderLocalCityDetail;
import com.yunchengke.app.bean.daemon.order.Resp_OrderLocalCityList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.OrderModel;
import com.yunchengke.app.utils.daemon.DialogUtil;
import com.yunchengke.app.utils.daemon.SPUtil;

import java.util.HashMap;

import static com.yunchengke.app.consts.Constants.KEY_PARCELABLE;
import static com.yunchengke.app.consts.Constants.MODEL_ORDER_LOCAL_CITY_UPDATE;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_PAY;
import static com.yunchengke.app.consts.Constants.VIEW_ORDER_LOCAL_CITY_UPDATE;

/**
 * 同城订单详情
 */
public class OrderLocalCityDetailActivity extends BaseActivity {
    private Resp_OrderLocalCityList.Rows row;
    private RelativeLayout relativeLayout_order_localCity_detail_pay;
    private Button btn_order_localCity_detail_pay_now;
    private  Resp_OrderLocalCityDetail resp_OrderLocalCityDetail;
    private String updatePayStatus = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_local_city_detail);
        relativeLayout_order_localCity_detail_pay = (RelativeLayout)findViewById(R.id.relativeLayout_order_localCity_detail_pay);

        setModelDelegate(new OrderModel(handler, this));
        setViewChangeListener(this);

        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText(getString(R.string.title_order_detail));

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        if(getIntent().hasExtra(KEY_PARCELABLE)){
            row = getIntent().getParcelableExtra(KEY_PARCELABLE);
            Log.d("syb","MyActiveinfo_Page 3"+row.Field_HDBMFY);
            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("Id", row.X6_Product_Id);

            Log.d("syb","params_map="+params_map.toString());
            notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_LOCAL_CITY_DETAIL_QUERY, params_map));
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
     switch (v.getId()){
        case R.id.btn_title_back:
         finish();
         break;

         case R.id.btn_order_localCity_detail_pay_now:
             new AlertDialog.Builder(OrderLocalCityDetailActivity.this)
                     .setTitle("请选择支付方式")
                     .setSingleChoiceItems(new String[]{"银联支付", "工银支付", "支付宝支付","微信支付"}, -1, new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             Intent intent;
                             switch (which){
                                 case 0:
                                     intent = new Intent(OrderLocalCityDetailActivity.this, com.yunchengke.app.pay.unionpay.UnionPayActivity.class);
                                     intent.putExtra("price", resp_OrderLocalCityDetail.rows.get(0).Field_HDBMFY);
                                     intent.putExtra("orderId", resp_OrderLocalCityDetail.rows.get(0).Field_DDBH);
                                     startActivityForResult(intent, REQUEST_CODE_PAY);
                                     break;
                                 case 1:
                                     String orderPrice =resp_OrderLocalCityDetail.rows.get(0).Field_HDBMFY;
                                     Log.d("orderPriceDETL",""+orderPrice);
                                     intent = new Intent(OrderLocalCityDetailActivity.this,ICBCPayUi.class);
                                     intent.putExtra("orderPrice",orderPrice);
                                     startActivityForResult(intent, REQUEST_CODE_PAY);
                                     break;

                                 case 2:
                                     new DialogUtil().showDialog(OrderLocalCityDetailActivity.this, "提示", "对不起，此功能暂未开通！", new Commands() {
                                         @Override
                                         public void executeCommand(Message msg_params) {

                                         }
                                     });
                                     break;

                                 case 3:
                                     new DialogUtil().showDialog(OrderLocalCityDetailActivity.this, "提示", "对不起，此功能暂未开通！", new Commands() {
                                         @Override
                                         public void executeCommand(Message msg_params) {

                                         }
                                     });
                                     break;
                             }
                             dialog.dismiss();
                         }
                     })
                     .create()
                     .show();
             break;
     }
    }

    @Override
    public void onViewChange(Message msg) {

        try {
            switch (msg.what){
                case Constants.VIEW_ORDER_LOCAL_CITY_DETAIL_QUERY:
                    if(!isDestroyed) {
                    if(msg.obj instanceof String){
                        DialogUtil.showDialog(OrderLocalCityDetailActivity.this, getString(R.string.title_order_detail), (String) msg.obj, new Commands() {
                            @Override
                            public void executeCommand(Message msg_params) {

                            }
                        });
                    }else {

                            resp_OrderLocalCityDetail = (Resp_OrderLocalCityDetail) msg.obj;
                            TextView tv_order_localCity_detail_status = (TextView)findViewById(R.id.tv_order_localCity_detail_status);
                            tv_order_localCity_detail_status.setText(SPUtil.getPayStatus(this).getString(resp_OrderLocalCityDetail.rows.get(0).X6_Product_Recommend, ""));

                            TextView tv_order_localCity_detail_name = (TextView)findViewById(R.id.tv_order_localCity_detail_name);
                            tv_order_localCity_detail_name.setText(resp_OrderLocalCityDetail.rows.get(0).Field_HDMC);

                            TextView tv_order_localCity_detail_personCount = (TextView)findViewById(R.id.tv_order_localCity_detail_personCount);
                            tv_order_localCity_detail_personCount.setText(resp_OrderLocalCityDetail.rows.get(0).Field_HDBCBMRS);

                            TextView tv_order_localCity_detail_price = (TextView)findViewById(R.id.tv_order_localCity_detail_price);
                            tv_order_localCity_detail_price.setText("￥" +resp_OrderLocalCityDetail.rows.get(0).Field_HDBMFY);
                            tv_order_localCity_detail_price.setTextColor(getResources().getColor(R.color.ticket_title_color));

                            TextView tv_order_localCity_detail_orderNo = (TextView)findViewById(R.id.tv_order_localCity_detail_orderNo);
                            tv_order_localCity_detail_orderNo.setText(resp_OrderLocalCityDetail.rows.get(0).Field_DDBH);

                            TextView tv_order_localCity_detail_orderTime = (TextView)findViewById(R.id.tv_order_localCity_detail_orderTime);
                            tv_order_localCity_detail_orderTime.setText(resp_OrderLocalCityDetail.rows.get(0).Field_DDSJ);

                            relativeLayout_order_localCity_detail_pay.setVisibility(View.VISIBLE);

                            btn_order_localCity_detail_pay_now = (Button)findViewById(R.id.btn_order_localCity_detail_pay_now);

                            /**
                             *
                             ,1, ：待付款
                             ,2, ：已付款待确认
                             ,3, ：已完成
                             ,4, ：已作废
                             */
                            if(",1,".equals(resp_OrderLocalCityDetail.rows.get(0).X6_Product_Recommend)){
                                btn_order_localCity_detail_pay_now.setTextColor(Color.WHITE);
                                btn_order_localCity_detail_pay_now.setBackgroundDrawable(getResources().getDrawable(R.drawable.ticket_title_corner));
                                btn_order_localCity_detail_pay_now.setOnClickListener(this);

                                TextView tv_order_localCity_detail_tipStatus = (TextView)findViewById(R.id.tv_order_localCity_detail_tipStatus);
                                TextView tv_order_localCity_detail_tip = (TextView)findViewById(R.id.tv_order_localCity_detail_tip);
                                tv_order_localCity_detail_tipStatus.setVisibility(View.VISIBLE);
                                tv_order_localCity_detail_tip.setVisibility(View.VISIBLE);

                                updatePayStatus = "2";
                            }else if(",2,".equals(resp_OrderLocalCityDetail.rows.get(0).X6_Product_Recommend)){
                                btn_order_localCity_detail_pay_now.setTextColor(Color.WHITE);
                                btn_order_localCity_detail_pay_now.setBackgroundDrawable(getResources().getDrawable(R.drawable.ticket_title_corner));
                                btn_order_localCity_detail_pay_now.setText("已付款待确认");
                                /**
                                 *  待确认的逻辑还没定义
                                 */
                                btn_order_localCity_detail_pay_now.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                updatePayStatus = "3";
                            }else if(",3,".equals(resp_OrderLocalCityDetail.rows.get(0).X6_Product_Recommend)){
                                btn_order_localCity_detail_pay_now.setTextColor(getResources().getColor(R.color.ticket_font_gray));
                                btn_order_localCity_detail_pay_now.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_disable_corner));
                                btn_order_localCity_detail_pay_now.setText("已支付");
                            }else{
                                btn_order_localCity_detail_pay_now.setTextColor(getResources().getColor(R.color.ticket_font_gray));
                                btn_order_localCity_detail_pay_now.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_disable_corner));
                                btn_order_localCity_detail_pay_now.setText("已作废");
                            }
                        }
                    }
                    break;
                case VIEW_ORDER_LOCAL_CITY_UPDATE:
                    if(msg.obj instanceof String){
                        String errorCode = (String)msg.obj;
                        /**
                         * 更新成功
                         */
                        if("0".equals(errorCode)){
                            setResult(RESULT_OK,null);
                            finish();
                        }
                        /**
                         * 如果支付成功，而更新失败，则如何处理？待定
                         */
                        else{
                            if(!isDestroyed) {
                                DialogUtil.showDialog(OrderLocalCityDetailActivity.this, getString(R.string.title_order_detail), "订单更新失败", new Commands() {
                                    @Override
                                    public void executeCommand(Message msg_params) {

                                    }
                                });
                            }
                        }
                    }
                    break;

            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 只有resultCode == RESULT_OK，intent data才不是null
         */
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_PAY:
                    String msg = "";
                    /**
                     * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
                     **/
                    String str = data.getExtras().getString("pay_result");
                    if (str.equalsIgnoreCase("success")) {
                        msg = "支付成功！";
                        /**
                         * 更新订单状态,并通知同城列表刷新数据
                         */
                        HashMap<String, String> params_map = new HashMap<String, String>();
                        params_map.put("UId", "yesicity2015");
                        params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                        params_map.put("Yesicity", "1");
                        params_map.put("OrderId", resp_OrderLocalCityDetail.rows.get(0).Field_DDBH);
                        params_map.put("Status", updatePayStatus);
                        notifyModelChange(Message.obtain(handler, MODEL_ORDER_LOCAL_CITY_UPDATE,params_map));
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
