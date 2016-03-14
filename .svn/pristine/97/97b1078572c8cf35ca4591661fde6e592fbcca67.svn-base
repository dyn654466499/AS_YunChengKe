package com.yunchengke.app.ui.activity.daemon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.order.Resp_OrderCateringDetail;
import com.yunchengke.app.bean.daemon.order.Resp_OrderCateringDishesDetail;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.OrderModel;
import com.yunchengke.app.ui.adapter.daemon.order.OrderCateringDishesAdapter;
import com.yunchengke.app.utils.daemon.DialogUtil;

import java.util.HashMap;

import static com.yunchengke.app.consts.Constants.KEY_ID;
import static com.yunchengke.app.consts.Constants.KEY_TYPE;
import static com.yunchengke.app.consts.Constants.MODEL_ORDER_CATERING_GOODS_DETAIL_QUERY;
import static com.yunchengke.app.consts.Constants.MODEL_ORDER_CATERING_UPDATE;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_PAY;
import static com.yunchengke.app.consts.Constants.VIEW_ORDER_CATERING_UPDATE;

/**
 * 餐饮订单详情
 */
public class OrderCateringDetailActivity extends BaseActivity {
    private RelativeLayout relativeLayout_order_catering_detail_pay;
    private Button btn_order_catering_detail_pay_now,btn_order_catering_detail_pay_toShop,btn_order_catering_detail_pay_deposit;

    private Resp_OrderCateringDetail resp_orderCateringDetail;
    private String updatePayStatus = "";

    private String X6_Product_Id = "";
    private String Field_Type = "";
    private OrderCateringDetailActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_order_catering_detail);
        relativeLayout_order_catering_detail_pay = (RelativeLayout)findViewById(R.id.relativeLayout_order_catering_detail_pay);

        setModelDelegate(new OrderModel(handler, this));
        setViewChangeListener(this);

        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText(getString(R.string.title_order_detail));

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        if(getIntent().hasExtra(KEY_ID)){
            X6_Product_Id = getIntent().getStringExtra(KEY_ID);
            Field_Type = getIntent().getStringExtra(KEY_TYPE);

            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID",LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("type", Field_Type);
            params_map.put("Id", X6_Product_Id);

            notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_CATERING_DETAIL_QUERY, params_map));

            notifyModelChange(Message.obtain(handler, MODEL_ORDER_CATERING_GOODS_DETAIL_QUERY,params_map));
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
     switch (v.getId()){
        case R.id.btn_title_back:
         finish();
         break;

         case R.id.btn_order_catering_detail_pay_now:
             new AlertDialog.Builder(OrderCateringDetailActivity.this)
                     .setTitle("请选择支付方式")
                     .setSingleChoiceItems(new String[]{"银联支付", "工银支付", "支付宝支付","微信支付"}, -1, new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             Intent intent;
                             switch (which){
                                 case 0:
                                      intent = new Intent(OrderCateringDetailActivity.this, com.yunchengke.app.pay.unionpay.UnionPayActivity.class);
                                     intent.putExtra("price",   (String) resp_orderCateringDetail.rows.get(0).Field_CYDDJE);
                                     intent.putExtra("orderId", (String) resp_orderCateringDetail.rows.get(0).Field_CYDDBH);
                                     startActivityForResult(intent, REQUEST_CODE_PAY);
                                     break;
                                 case 1:
                                     String orderPrice =resp_orderCateringDetail.rows.get(0).Field_CYDDJE;
                                     Log.d("orderPriceDETL", "" + orderPrice);
                                     intent= new Intent(context,ICBCPayUi.class);
                                     intent.putExtra("orderPrice", orderPrice);
                                     startActivityForResult(intent, REQUEST_CODE_PAY);
                                     break;

                                 case 2:
                                     new DialogUtil().showDialog(OrderCateringDetailActivity.this, "提示", "对不起，此功能暂未开通！", new Commands() {
                                         @Override
                                         public void executeCommand(Message msg_params) {

                                         }
                                     });
                                     break;

                                 case 3:
                                     new DialogUtil().showDialog(OrderCateringDetailActivity.this, "提示", "对不起，此功能暂未开通！", new Commands() {
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
        switch (msg.what){
            case Constants.VIEW_ORDER_CATERING_DETAIL_QUERY:
                if(!isDestroyed) {
                if(msg.obj instanceof String){
                    DialogUtil.showDialog(OrderCateringDetailActivity.this, getString(R.string.title_order_detail), (String) msg.obj, new Commands() {
                        @Override
                        public void executeCommand(Message msg_params) {

                        }
                    });
                }else {

                         resp_orderCateringDetail = (Resp_OrderCateringDetail) msg.obj;
                        TextView tv_order_catering_personName = (TextView)findViewById(R.id.tv_order_catering_personName);
                        tv_order_catering_personName.setText(resp_orderCateringDetail.rows.get(0).Field_DWXM);

                        TextView tv_order_catering_phone = (TextView)findViewById(R.id.tv_order_catering_phone);
                        tv_order_catering_phone.setText(resp_orderCateringDetail.rows.get(0).Field_YHSJ);

                    if(TextUtils.isEmpty(resp_orderCateringDetail.rows.get(0).Field_SCDZ)){
                        LinearLayout linearLayout_order_catering_detail_personCount = (LinearLayout)findViewById(R.id.linearLayout_order_catering_detail_personCount);
                        linearLayout_order_catering_detail_personCount.setVisibility(View.VISIBLE);
                        TextView tv_order_catering_personCount = (TextView)findViewById(R.id.tv_order_catering_personCount);
                        tv_order_catering_personCount.setText(resp_orderCateringDetail.rows.get(0).Field_RS);
                    }else{
                        LinearLayout linearLayout_order_catering_detail_address = (LinearLayout)findViewById(R.id.linearLayout_order_catering_detail_address);
                        linearLayout_order_catering_detail_address.setVisibility(View.VISIBLE);
                        TextView tv_order_catering_address = (TextView)findViewById(R.id.tv_order_catering_address);
                        tv_order_catering_address.setText(resp_orderCateringDetail.rows.get(0).Field_SCDZ);
                    }

                        TextView tv_order_catering_time = (TextView)findViewById(R.id.tv_order_catering_time);
                        tv_order_catering_time.setText(resp_orderCateringDetail.rows.get(0).Field_DWYCSJ);

                        TextView tv_order_catering_beizhu = (TextView)findViewById(R.id.tv_order_catering_beizhu);
                        tv_order_catering_beizhu.setText(resp_orderCateringDetail.rows.get(0).Field_DWBZ);

                        EditText et_order_catering_storeName = (EditText)findViewById(R.id.et_order_catering_storeName);
                        et_order_catering_storeName.setText(resp_orderCateringDetail.rows.get(0).Field_DPMC);

                        TextView tv_order_catering_totalPrice = (TextView)findViewById(R.id.tv_order_catering_totalPrice);
                        tv_order_catering_totalPrice.setText("￥" + resp_orderCateringDetail.rows.get(0).Field_CYDDJE);

                        relativeLayout_order_catering_detail_pay.setVisibility(View.VISIBLE);
                        btn_order_catering_detail_pay_now = (Button)findViewById(R.id.btn_order_catering_detail_pay_now);

                        btn_order_catering_detail_pay_toShop = (Button)findViewById(R.id.btn_order_catering_detail_pay_toShop);
                        btn_order_catering_detail_pay_deposit = (Button)findViewById(R.id.btn_order_catering_detail_pay_deposit);
                        /**
                         *
                         ,1, ：待付款
                         ,2, ：已付款待确认
                         ,3, ：已完成
                         ,4, ：已作废
                         */
                        if(",1,".equals(resp_orderCateringDetail.rows.get(0).X6_Product_Recommend)){
                            btn_order_catering_detail_pay_toShop.setVisibility(View.VISIBLE);
                            btn_order_catering_detail_pay_toShop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new DialogUtil().showDialog(OrderCateringDetailActivity.this, "提示", "对不起，此功能暂未开通！", new Commands() {
                                        @Override
                                        public void executeCommand(Message msg_params) {

                                        }
                                    });
                                }
                            });
                            btn_order_catering_detail_pay_deposit.setVisibility(View.VISIBLE);
                            btn_order_catering_detail_pay_deposit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new DialogUtil().showDialog(OrderCateringDetailActivity.this, "提示", "对不起，此功能暂未开通！", new Commands() {
                                        @Override
                                        public void executeCommand(Message msg_params) {

                                        }
                                    });
                                }
                            });
                            btn_order_catering_detail_pay_toShop.setTextColor(Color.WHITE);
                            btn_order_catering_detail_pay_toShop.setBackgroundDrawable(getResources().getDrawable(R.drawable.ticket_title_corner));

                            btn_order_catering_detail_pay_deposit.setTextColor(Color.WHITE);
                            btn_order_catering_detail_pay_deposit.setBackgroundDrawable(getResources().getDrawable(R.drawable.ticket_title_corner));

                            btn_order_catering_detail_pay_now.setTextColor(Color.WHITE);
                            btn_order_catering_detail_pay_now.setBackgroundDrawable(getResources().getDrawable(R.drawable.ticket_title_corner));
                            btn_order_catering_detail_pay_now.setOnClickListener(this);
                            updatePayStatus = "2";
                        }else if(",2,".equals(resp_orderCateringDetail.rows.get(0).X6_Product_Recommend)) {
                            btn_order_catering_detail_pay_now.setTextColor(Color.WHITE);
                            btn_order_catering_detail_pay_now.setBackgroundDrawable(getResources().getDrawable(R.drawable.ticket_title_corner));
                            /**
                             *  待确认的逻辑还没定义
                             */
                            btn_order_catering_detail_pay_now.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            btn_order_catering_detail_pay_now.setText("已付款待确认");
                            updatePayStatus = "3";
                        }else if(",3,".equals(resp_orderCateringDetail.rows.get(0).X6_Product_Recommend)) {
                            btn_order_catering_detail_pay_now.setTextColor(getResources().getColor(R.color.ticket_font_gray));
                            btn_order_catering_detail_pay_now.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_disable_corner));
                            btn_order_catering_detail_pay_now.setText("已支付");
                        } else{
                            btn_order_catering_detail_pay_now.setTextColor(getResources().getColor(R.color.ticket_font_gray));
                            btn_order_catering_detail_pay_now.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_disable_corner));
                            btn_order_catering_detail_pay_now.setText("已作废");
                        }
                    }
                }
                break;

            case Constants.VIEW_ORDER_CATERING_GOODS_DETAIL_QUERY:
                if(!isDestroyed) {
                if(msg.obj instanceof String){
//                    DialogUtil.showDialog(OrderCateringDetailActivity.this, getString(R.string.title_order_detail), (String) msg.obj, new Commands() {
//                        @Override
//                        public void executeCommand(Message msg_params) {
//
//                        }
//                    });
                }else {

                        Resp_OrderCateringDishesDetail resp_orderCateringDishesDetail = (Resp_OrderCateringDishesDetail) msg.obj;
                        /**
                         *  --------------------------------------     菜品列表 start         -------------------------------------------
                         */
                        ListView lv_order_catering_dishes_detail = (ListView)findViewById(R.id.lv_order_catering_dishes_detail);
                        OrderCateringDishesAdapter adapter = new OrderCateringDishesAdapter(this,resp_orderCateringDishesDetail);
                        lv_order_catering_dishes_detail.setAdapter(adapter);
                        /**
                         *  --------------------------------------     菜品列表  end        -------------------------------------------
                         */

                    }
                }
                break;

            case VIEW_ORDER_CATERING_UPDATE:

                if(msg.obj instanceof String) {
                    String errorCode = (String) msg.obj;
                    /**
                     * 更新成功
                     */
                    if ("0".equals(errorCode)) {
                        setResult(RESULT_OK, null);
                        finish();
                    }
                    /**
                     * 如果支付成功，而更新失败，则如何处理？待定
                     */
                    else {
                        if(!isDestroyed) {
                        DialogUtil.showDialog(OrderCateringDetailActivity.this, getString(R.string.title_order_detail), "订单更新失败", new Commands() {
                            @Override
                            public void executeCommand(Message msg_params) {

                            }
                        });
                    }
                }
                }
                break;
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
                         * 通知后台更新订单状态,并通知餐饮列表刷新数据
                         */
                        HashMap<String, String> params_map = new HashMap<String, String>();
                        params_map.put("UId", "yesicity2015");
                        params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                        params_map.put("Yesicity", "1");
                        params_map.put("type", Field_Type);
                        params_map.put("OrderId", resp_orderCateringDetail.rows.get(0).Field_CYDDBH);
                        params_map.put("Status", updatePayStatus);
                        notifyModelChange(Message.obtain(handler, MODEL_ORDER_CATERING_UPDATE,params_map));
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
