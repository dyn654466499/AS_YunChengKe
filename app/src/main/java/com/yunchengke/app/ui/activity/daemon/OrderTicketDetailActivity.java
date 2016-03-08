package com.yunchengke.app.ui.activity.daemon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.order.Resp_OrderTicketQueryInfo;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.OrderModel;
import com.yunchengke.app.utils.daemon.DialogUtil;
import com.yunchengke.app.utils.daemon.SPUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.yunchengke.app.consts.Constants.KEY_PARCELABLE;
import static com.yunchengke.app.consts.Constants.KEY_TYPE;
import static com.yunchengke.app.consts.Constants.MODEL_ORDER_TICKET_ENDORSE;
import static com.yunchengke.app.consts.Constants.MODEL_ORDER_TICKET_RETURN;
import static com.yunchengke.app.consts.Constants.MODEL_ORDER_TICKET_UPDATE;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_PAY;
import static com.yunchengke.app.consts.Constants.VIEW_ORDER_TICKET_UPDATE;

public class OrderTicketDetailActivity extends BaseActivity {
   private Resp_OrderTicketQueryInfo resp_orderTicketQueryInfo;
    private RelativeLayout relativeLayout_order_ticket_detail_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ticket_detail);
        setModelDelegate(new OrderModel(handler,this));
        setViewChangeListener(this);

        relativeLayout_order_ticket_detail_pay = (RelativeLayout)findViewById(R.id.relativeLayout_order_ticket_detail_pay);

        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText(getString(R.string.title_order_detail));

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        if(getIntent().hasExtra(KEY_PARCELABLE)){
            resp_orderTicketQueryInfo = getIntent().getParcelableExtra(KEY_PARCELABLE);

            TextView tv_order_ticket_detail_orderNo = (TextView)findViewById(R.id.tv_order_ticket_detail_orderNo);
            tv_order_ticket_detail_orderNo.setText(resp_orderTicketQueryInfo.OrderNo);

            TextView tv_order_ticket_detail_status = (TextView)findViewById(R.id.tv_order_ticket_detail_status);
            tv_order_ticket_detail_status.setText(SPUtil.getOrderStatus(this).getString(resp_orderTicketQueryInfo.Status,""));
            /**
             *  0是等待付款
             */
            if("0".equals(resp_orderTicketQueryInfo.Status)) {
                relativeLayout_order_ticket_detail_pay.setVisibility(View.VISIBLE);
                Button btn_order_ticket_detail_pay_now = (Button) findViewById(R.id.btn_order_ticket_detail_pay_now);
                btn_order_ticket_detail_pay_now.setOnClickListener(this);
            }
                /**
                 *  -1是等待确认
                 */
            else if("-1".equals(resp_orderTicketQueryInfo.Status)){
                relativeLayout_order_ticket_detail_pay.setVisibility(View.VISIBLE);
                Button btn_order_ticket_detail_pay = (Button)findViewById(R.id.btn_order_ticket_detail_pay_now);
                btn_order_ticket_detail_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                btn_order_ticket_detail_pay.setText("确认订单");

            }
            /**
             *  2是出票成功，若成功则显示改签和退票按钮
             */
            else if("2".equals(resp_orderTicketQueryInfo.Status)){
                Button btn_order_ticket_detail_endorse = (Button)findViewById(R.id.btn_order_ticket_detail_endorse);
                btn_order_ticket_detail_endorse.setVisibility(View.VISIBLE);
                btn_order_ticket_detail_endorse.setOnClickListener(this);

                Button btn_order_ticket_detail_return = (Button)findViewById(R.id.btn_order_ticket_detail_return);
                btn_order_ticket_detail_return.setVisibility(View.VISIBLE);
                btn_order_ticket_detail_return.setOnClickListener(this);
            }

            TextView tv_order_ticket_detail_price = (TextView)findViewById(R.id.tv_order_ticket_detail_price);
            tv_order_ticket_detail_price.setText("￥"+resp_orderTicketQueryInfo.PayMoney);

            /**
             *  --------------------------------------     乘机人信息列表 start         -------------------------------------------
             */
            ListView lv_order_ticket_detail_passengerInfo = (ListView)findViewById(R.id.lv_order_ticket_detail_passengerInfo);
            String[] from = new String[]{"name","certType","certNo"};
            int[] to = new int[]{R.id.tv_order_ticket_detail_passengerInfo_name,
                    R.id.tv_order_ticket_detail_passengerInfo_certType,
                    R.id.tv_order_ticket_detail_passengerInfo_certNo,
            };
            String[] names = resp_orderTicketQueryInfo.PName.split("\\|");
            String[] certNos = resp_orderTicketQueryInfo.CardNo.split("\\|");

            List<Map<String,Object>> data = new LinkedList<Map<String,Object>>();
            for (int i = 0; i< names.length;i++){
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("name",names[i]);
                map.put("certType","身份证");
                map.put("certNo",certNos[i]);
                data.add(map);
            }
            SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.item_order_ticket_detail_passenger_info,from,to);
            lv_order_ticket_detail_passengerInfo.setAdapter(adapter);

            /**
             *  --------------------------------------     乘机人信息列表  end        -------------------------------------------
             */

            TextView tv_order_ticket_detail_flight_date = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_date);
            tv_order_ticket_detail_flight_date.setText(resp_orderTicketQueryInfo.Date);

            TextView tv_order_ticket_detail_flight_day = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_day);
            tv_order_ticket_detail_flight_day.setText(getWeekOfDate(resp_orderTicketQueryInfo.Date));

            TextView tv_order_ticket_detail_flight_Scity = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_Scity);
            tv_order_ticket_detail_flight_Scity.setText(SPUtil.getCity(this).getString(resp_orderTicketQueryInfo.Scity,""));

            TextView tv_order_ticket_detail_flight_Ecity = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_Ecity);
            tv_order_ticket_detail_flight_Ecity.setText(SPUtil.getCity(this).getString(resp_orderTicketQueryInfo.Ecity,""));

            TextView tv_order_ticket_detail_flight_cabin = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_cabin);
            tv_order_ticket_detail_flight_cabin.setText(SPUtil.getCabin(this).getString(resp_orderTicketQueryInfo.Cabin,""));


            TextView tv_order_ticket_detail_flight_Stime = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_Stime);
            tv_order_ticket_detail_flight_Stime.setText(getFormatTime(resp_orderTicketQueryInfo.Stime));

            TextView tv_order_ticket_detail_flight_Etime = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_Etime);
            tv_order_ticket_detail_flight_Etime.setText(getFormatTime(resp_orderTicketQueryInfo.Etime));

            String str_interval = "0h";
            try {
                str_interval = getTimeInterval(resp_orderTicketQueryInfo.Stime,resp_orderTicketQueryInfo.Etime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            TextView tv_order_ticket_detail_flight_interval = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_interval);
            tv_order_ticket_detail_flight_interval.setText(str_interval);

            TextView tv_order_ticket_detail_flight_Sport = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_Sport);
            tv_order_ticket_detail_flight_Sport.setText(SPUtil.getAirPort(this).getString(resp_orderTicketQueryInfo.Scity,""));

            TextView tv_order_ticket_detail_flight_Eport = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_Eport);
            tv_order_ticket_detail_flight_Eport.setText(SPUtil.getAirPort(this).getString(resp_orderTicketQueryInfo.Ecity, ""));

            TextView tv_order_ticket_detail_flight_airLine = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_airLine);
            tv_order_ticket_detail_flight_airLine.setText(SPUtil.getAirLine(this).getString(resp_orderTicketQueryInfo.Flight.substring(0,2),""));

            TextView tv_order_ticket_detail_flight_flightNo = (TextView)findViewById(R.id.tv_order_ticket_detail_flight_flightNo);
            tv_order_ticket_detail_flight_flightNo.setText(resp_orderTicketQueryInfo.Flight);


        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
     switch (v.getId()){
        case R.id.btn_title_back:
         finish();
         break;

         case R.id.btn_order_ticket_detail_endorse:
             intent = new Intent(OrderTicketDetailActivity.this,OrderReturnInfoActivity.class);
             intent.putExtra(KEY_PARCELABLE,resp_orderTicketQueryInfo);
             intent.putExtra(KEY_TYPE,MODEL_ORDER_TICKET_ENDORSE);
             startActivity(intent);
             overridePendingTransition(0,0);
             break;

         case R.id.btn_order_ticket_detail_return:
             intent = new Intent(OrderTicketDetailActivity.this,OrderReturnInfoActivity.class);
             intent.putExtra(KEY_PARCELABLE,resp_orderTicketQueryInfo);
             intent.putExtra(KEY_TYPE,MODEL_ORDER_TICKET_RETURN);
             startActivity(intent);
             overridePendingTransition(0,0);
             break;

         case R.id.btn_order_ticket_detail_pay_now:
             new AlertDialog.Builder(OrderTicketDetailActivity.this)
                     .setTitle("请选择支付方式")
                     .setSingleChoiceItems(new String[]{"银联支付", "工银支付", "支付宝支付","微信支付"}, -1, new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             Intent intent;
                             switch (which){
                                 case 0:
                                     intent = new Intent(OrderTicketDetailActivity.this, com.yunchengke.app.pay.unionpay.UnionPayActivity.class);
                                     intent.putExtra("price", resp_orderTicketQueryInfo.PayMoney);
                                     intent.putExtra("orderId", resp_orderTicketQueryInfo.OrderNo);
                                     startActivityForResult(intent, REQUEST_CODE_PAY);
                                     break;
                                 case 1:
                                     String orderPrice = resp_orderTicketQueryInfo.PayMoney;
                                     Log.d("orderPriceDETL", "" + orderPrice);
                                     intent= new Intent(OrderTicketDetailActivity.this,ICBCPayUi.class);
                                     intent.putExtra("orderPrice",orderPrice);
                                     startActivityForResult(intent, REQUEST_CODE_PAY);
                                     break;

                                 case 2:
                                     new DialogUtil().showDialog(OrderTicketDetailActivity.this, "提示", "对不起，此功能暂未开通！", new Commands() {
                                         @Override
                                         public void executeCommand(Message msg_params) {

                                         }
                                     });
                                     break;

                                 case 3:
                                     new DialogUtil().showDialog(OrderTicketDetailActivity.this, "提示", "对不起，此功能暂未开通！", new Commands() {
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
             case VIEW_ORDER_TICKET_UPDATE:
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
                         if (!isDestroyed) {
                             DialogUtil.showDialog(OrderTicketDetailActivity.this, getString(R.string.title_order_detail), "订单更新失败", new Commands() {
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
                         * 更新订单状态,并通知同城列表刷新数据
                         */
                        HashMap<String, String> params_map = new HashMap<String, String>();
                        params_map.put("UId", "yesicity2015");
                        params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                        params_map.put("Yesicity", "1");
                        params_map.put("OrderId", resp_orderTicketQueryInfo.OrderNo);
                        notifyModelChange(Message.obtain(handler, MODEL_ORDER_TICKET_UPDATE, params_map));
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

    private String getTimeInterval(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long interval = (time2 - time1)/1000;
        String str_time = String.valueOf(interval/3600)+"h"+
                ((interval/60)%60 < 10 ? "0"+String.valueOf((interval/60)%60):String.valueOf((interval/60)%60))+"m";
        return str_time;
    }

    private String getFormatTime(String smdate){
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(smdate));
        } catch (ParseException e) {
            e.printStackTrace();
            cal.setTime(new Date(System.currentTimeMillis()));
        }
        long time = cal.getTimeInMillis();
        sdf =  new SimpleDateFormat("HH:mm");
        String str_time = sdf.format(new Date(time));
        return str_time;
    }

    private String getWeekOfDate(String date) {
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = cal.getTimeInMillis();
        return  dateFm.format(new Date(time));
    }
}
