package com.yunchengke.app.ui.activity.daemon;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.order.Resp_OrderCateringList;
import com.yunchengke.app.bean.daemon.order.Resp_OrderLocalCityList;
import com.yunchengke.app.bean.daemon.order.Resp_OrderTicketList;
import com.yunchengke.app.bean.daemon.order.Resp_OrderTicketQueryInfo;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.interfaces.FragmentListener;
import com.yunchengke.app.model.OrderModel;
import com.yunchengke.app.ui.fragments.order.OrderCateringFragment;
import com.yunchengke.app.ui.fragments.order.OrderLocalCityFragment;
import com.yunchengke.app.ui.fragments.order.OrderTicketFragment;
import com.yunchengke.app.utils.daemon.AutoLoadingUtil;
import com.yunchengke.app.utils.daemon.DialogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.yunchengke.app.consts.Constants.MODEL_ORDER_TICKET_QUERY;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_PAY_CATERING;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_PAY_LOCAL_CITY;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_PAY_TICKET;
import static com.yunchengke.app.consts.Constants.VIEW_ORDER_CATERING_QUERY;
import static com.yunchengke.app.consts.Constants.VIEW_ORDER_LOCAL_CITY_QUERY;
import static com.yunchengke.app.consts.Constants.VIEW_ORDER_TICKET_QUERY;

public class MyConsumeActivity extends BaseActivity implements FragmentListener{

    private Button btn_my_consume_shopping,btn_my_consume_catering,
            btn_my_consume_localCity,btn_my_consume_hotel,
            btn_my_consume_airTicket;

    private List<Button> buttonList;

    private List<Resp_OrderTicketQueryInfo> OrderTicketQueryInfos;
    private Resp_OrderCateringList resp_orderCateringList;
    private Resp_OrderLocalCityList resp_orderLocalCityList;
    private Resp_OrderTicketList resp_orderTicketList;

    private int page_catering = 1;
    private int page_localCity = 1;
    private int page_ticket = 1;
    private List<Resp_OrderCateringList.Rows> resp_orderCateringListRows;
    private List<Resp_OrderLocalCityList.Rows> resp_orderLocalCityListRows;
    private List<Resp_OrderTicketList.Rows> resp_orderTicketListRows;


    private OrderTicketFragment fragment_order_ticket;
    private OrderCateringFragment fragment_order_catering;
    private OrderLocalCityFragment fragment_order_localCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_consume);

        resp_orderCateringListRows = new ArrayList<Resp_OrderCateringList.Rows>();
        resp_orderLocalCityListRows = new ArrayList<Resp_OrderLocalCityList.Rows>();
        resp_orderTicketListRows = new ArrayList<Resp_OrderTicketList.Rows>();


        setModelDelegate(new OrderModel(handler, this));
        setViewChangeListener(this);

        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText(getString(R.string.title_my_consume));

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        btn_my_consume_shopping = (Button)findViewById(R.id.btn_my_consume_shopping);
        btn_my_consume_shopping.setOnClickListener(this);

        btn_my_consume_catering = (Button)findViewById(R.id.btn_my_consume_catering);
        btn_my_consume_catering.setOnClickListener(this);

        btn_my_consume_localCity = (Button)findViewById(R.id.btn_my_consume_localCity);
        btn_my_consume_localCity.setOnClickListener(this);

        btn_my_consume_hotel = (Button)findViewById(R.id.btn_my_consume_hotel);
        btn_my_consume_hotel.setOnClickListener(this);

        btn_my_consume_airTicket = (Button)findViewById(R.id.btn_my_consume_airTicket);
        btn_my_consume_airTicket.setOnClickListener(this);

        OrderTicketQueryInfos = new ArrayList<Resp_OrderTicketQueryInfo>();

        buttonList = new LinkedList<Button>();
        buttonList.add(btn_my_consume_shopping);
        buttonList.add(btn_my_consume_catering);
        buttonList.add(btn_my_consume_localCity);
        buttonList.add(btn_my_consume_hotel);
        buttonList.add(btn_my_consume_airTicket);

        btn_my_consume_localCity.callOnClick();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_title_back:
                finish();
                break;

            case R.id.btn_my_consume_shopping:
                setButtonClick(v.getId());
                break;
            /**
             * 餐饮列表查询
             */
            case R.id.btn_my_consume_catering:
                setButtonClick(v.getId());
                if(resp_orderCateringList==null){
                    setButtonClickDisable();
                    RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout_fragment_content);
                    AutoLoadingUtil.setAutoLoadingView(layout);
                    HashMap<String, String> params_map = new HashMap<String, String>();
                    params_map.put("UId", "yesicity2015");
                    params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                    params_map.put("Yesicity", "1");
                    params_map.put("page", "1");
                    notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_CATERING_QUERY, params_map));
                }else {
                    if (fragment_order_catering!=null&&!fragment_order_catering.isAdded()) {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.relativeLayout_fragment_content, fragment_order_catering);
                        transaction.commitAllowingStateLoss();
                    }
                }
                break;
            /**
             * 同城列表查询
             */
            case R.id.btn_my_consume_localCity:
                setButtonClick(v.getId());
                if(resp_orderLocalCityList==null){
                    setButtonClickDisable();
                    RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout_fragment_content);
                    AutoLoadingUtil.setAutoLoadingView(layout);
                    HashMap<String, String> params_map = new HashMap<String, String>();
                    params_map.put("UId", "yesicity2015");
                    params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                    params_map.put("Yesicity", "1");
                    params_map.put("page", "1");
                    notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_LOCAL_CITY_QUERY, params_map));
                }else {
                    if (fragment_order_localCity!=null&&!fragment_order_localCity.isAdded()) {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.relativeLayout_fragment_content, fragment_order_localCity);
                        transaction.commitAllowingStateLoss();
                    }
                }
                break;

            case R.id.btn_my_consume_hotel:
                setButtonClick(v.getId());
                break;
            /**
             * 机票列表查询
             */
            case R.id.btn_my_consume_airTicket:
                setButtonClick(v.getId());
                /**
                 * 如果为null，则请求网络数据；否则直接显示列表
                 */
                if(resp_orderTicketList == null) {

                    setButtonClickDisable();
                    RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout_fragment_content);
                    AutoLoadingUtil.setAutoLoadingView(layout);
                    HashMap<String, String> params_map = new HashMap<String, String>();
                    params_map.put("UId", "yesicity2015");
                    params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                    params_map.put("Yesicity", "1");
                    params_map.put("page", "1");
                    notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_TICKET_ORDER_NO_QUERY, params_map));
                }else{
                    if(fragment_order_ticket!=null&&!fragment_order_ticket.isAdded()) {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.relativeLayout_fragment_content, fragment_order_ticket);
                        transaction.commitAllowingStateLoss();
                    }
                }

                break;
        }
    }

    private void setButtonClick(int resId){
        for (Button button:buttonList) {
            if(button.getId()==resId){
                if(button.getId() == R.id.btn_my_consume_shopping){
                    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_conner_left_press));
                }else if(button.getId() == R.id.btn_my_consume_airTicket){
                    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_conner_right_press));
                }else{
                    button.setBackgroundColor(getResources().getColor(R.color.ticket_white));
                }
                button.setTextColor(getResources().getColor(R.color.ticket_title_color));
            }else{
                if(button.getId() == R.id.btn_my_consume_shopping){
                    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_conner_left_unpress_blue));
                }else if(button.getId() == R.id.btn_my_consume_airTicket){
                    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_conner_right_unpress_blue));
                }else{
                    button.setBackgroundColor(getResources().getColor(R.color.ticket_title_color));
                }
                button.setTextColor(getResources().getColor(R.color.ticket_white));
            }
        }
    }

    private void setButtonClickDisable(){
        for (Button button:buttonList) {
                button.setClickable(false);
        }
    }

    private void setButtonClickEnable(){
        for (Button button:buttonList) {
            button.setClickable(true);
        }
    }

    @Override
    public void onViewChange(Message msg) {

        switch (msg.what){
                /**
                 * 返回后台数据库的机票订单列表（主要是订单号），接下来去访问第三方的后台查询订单详情
                 */
                case Constants.VIEW_ORDER_TICKET_ORDER_NO_QUERY:
                    if(!isDestroyed) {
                    if (msg.obj instanceof String) {
                        AutoLoadingUtil.cancelAutoLoadingView();
                        DialogUtil.showDialog(MyConsumeActivity.this, getString(R.string.title_my_consume), (String) msg.obj, new Commands() {
                            @Override
                            public void executeCommand(Message msg_params) {

                            }
                        });
                        RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout_fragment_content);
                        layout.removeAllViewsInLayout();


                        setButtonClickEnable();
                        if (fragment_order_ticket != null) {
                            OrderTicketQueryInfos.clear();
                            fragment_order_ticket.notifyDataSetChanged();
                        }
                    } else {
                        resp_orderTicketList = (Resp_OrderTicketList) msg.obj;
                        //Log.e("ssdfsdfsdf","resp_orderTicketList.rows.size()="+resp_orderTicketList.rows.size());
                        notifyModelChange(Message.obtain(handler, MODEL_ORDER_TICKET_QUERY, resp_orderTicketList));
                    }
            }
                break;
            /**
             * 通知机票界面
             */
            case VIEW_ORDER_TICKET_QUERY:
                if(!isDestroyed) {
                AutoLoadingUtil.cancelAutoLoadingView();
                if(msg.obj instanceof String){
//                        DialogUtil.showDialog(MyConsumeActivity.this, getString(R.string.title_my_consume), (String)msg.obj, new Commands() {
//                            @Override
//                            public void executeCommand(Message msg_params) {
//
//                            }
//                        });
//                    if(fragment_order_ticket!=null){
//                        OrderTicketQueryInfos.clear();
//                        fragment_order_ticket.notifyDataSetChanged();
//                    }
                    RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout_fragment_content);
                    layout.removeAllViewsInLayout();

                    resp_orderTicketList = null;
                }else {
                        if (OrderTicketQueryInfos.size() == 0 && fragment_order_ticket == null) {
                            OrderTicketQueryInfos.addAll((List<Resp_OrderTicketQueryInfo>) msg.obj);
                            fragment_order_ticket = OrderTicketFragment.newInstance(OrderTicketQueryInfos);
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction transaction = fm.beginTransaction();
                            transaction.replace(R.id.relativeLayout_fragment_content, fragment_order_ticket);
                            transaction.commitAllowingStateLoss();
                        }else{
                            OrderTicketQueryInfos.addAll((List<Resp_OrderTicketQueryInfo>) msg.obj);
                            fragment_order_ticket.notifyDataSetChanged();
                        }
                    }
                }
                setButtonClickEnable();
                break;
            /**
             * 通知餐饮界面
             */
            case VIEW_ORDER_CATERING_QUERY:
                if(!isDestroyed) {
                AutoLoadingUtil.cancelAutoLoadingView();
                if(msg.obj instanceof String){
                        DialogUtil.showDialog(MyConsumeActivity.this, getString(R.string.title_my_consume), (String)msg.obj, new Commands() {
                            @Override
                            public void executeCommand(Message msg_params) {

                            }
                        });
                    RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout_fragment_content);
                    layout.removeAllViewsInLayout();


                        if(fragment_order_catering!=null){
                            resp_orderCateringListRows.clear();
                            fragment_order_catering.notifyDataSetChanged();
                        }
                }else {

                        resp_orderCateringList = (Resp_OrderCateringList) msg.obj;

                        if(resp_orderCateringListRows.size()==0 && fragment_order_catering == null){
                            resp_orderCateringListRows.addAll(resp_orderCateringList.rows);
                            fragment_order_catering = OrderCateringFragment.newInstance(resp_orderCateringListRows);
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction transaction = fm.beginTransaction();
                            transaction.replace(R.id.relativeLayout_fragment_content, fragment_order_catering);
                            transaction.commitAllowingStateLoss();
                        }else{
                            resp_orderCateringListRows.addAll(resp_orderCateringList.rows);
                            fragment_order_catering.notifyDataSetChanged();
                        }

                    }
                }
                setButtonClickEnable();
                break;
            /**
             * 通知同城界面
             */
            case VIEW_ORDER_LOCAL_CITY_QUERY:
                if(!isDestroyed) {
                AutoLoadingUtil.cancelAutoLoadingView();
                if(msg.obj instanceof String){
                    DialogUtil.showDialog(MyConsumeActivity.this, getString(R.string.title_my_consume), (String) msg.obj, new Commands() {
                        @Override
                        public void executeCommand(Message msg_params) {

                        }
                    });
                    RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout_fragment_content);
                    layout.removeAllViewsInLayout();


                    if(fragment_order_localCity!=null){
                        resp_orderLocalCityListRows.clear();
                        fragment_order_localCity.notifyDataSetChanged();
                    }
                }else {

                        resp_orderLocalCityList = (Resp_OrderLocalCityList) msg.obj;

                        if(resp_orderLocalCityListRows.size()==0 && fragment_order_localCity == null){
                            resp_orderLocalCityListRows.addAll(resp_orderLocalCityList.rows);
                            fragment_order_localCity = OrderLocalCityFragment.newInstance(resp_orderLocalCityListRows);
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction transaction = fm.beginTransaction();
                            transaction.replace(R.id.relativeLayout_fragment_content, fragment_order_localCity);
                            transaction.commitAllowingStateLoss();
                        }else{
                            resp_orderLocalCityListRows.addAll(resp_orderLocalCityList.rows);
                            fragment_order_localCity.notifyDataSetChanged();
                        }
                    }
                }
                setButtonClickEnable();
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
                case REQUEST_CODE_PAY_CATERING:
                    resp_orderCateringList = null;
                    resp_orderCateringListRows.clear();
                    btn_my_consume_catering.callOnClick();
                    break;
                case REQUEST_CODE_PAY_LOCAL_CITY:
                    resp_orderLocalCityList = null;
                    resp_orderLocalCityListRows.clear();
                    btn_my_consume_localCity.callOnClick();
                    break;
                case REQUEST_CODE_PAY_TICKET:
                    OrderTicketQueryInfos = null;
                    btn_my_consume_airTicket.callOnClick();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPullDownToRefresh() {
        if(fragment_order_catering!=null&&fragment_order_catering.isAdded()){
            resp_orderCateringListRows.clear();
            page_catering = 1;
            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("page", String.valueOf(page_catering));
            notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_CATERING_QUERY, params_map));

        }else if (fragment_order_localCity!=null&&fragment_order_localCity.isAdded()){
            resp_orderLocalCityListRows.clear();
            page_localCity = 1;
            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("page", String.valueOf(page_localCity));
            notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_LOCAL_CITY_QUERY, params_map));

        }else if(fragment_order_ticket!=null&&fragment_order_ticket.isAdded()){
            OrderTicketQueryInfos.clear();
            page_ticket = 1;
            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID",LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("page", String.valueOf(page_ticket));
            notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_TICKET_ORDER_NO_QUERY, params_map));
        }
    }

    @Override
    public void onPullUpToRefresh() {
        if(fragment_order_catering!=null&&fragment_order_catering.isAdded()){
            if(page_catering < Integer.valueOf(resp_orderCateringList.PageCount)) {
                page_catering++;
                HashMap<String, String> params_map = new HashMap<String, String>();
                params_map.put("UId", "yesicity2015");
                params_map.put("Field_YHID",LoginInfo.getInstance().getId());
                params_map.put("Yesicity", "1");
                params_map.put("page", String.valueOf(page_catering));
                notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_CATERING_QUERY, params_map));

            }else{
                fragment_order_catering.notifyDataSetChanged();
            }
        }else if (fragment_order_localCity!=null&&fragment_order_localCity.isAdded()){
            if(page_localCity < Integer.valueOf(resp_orderLocalCityList.PageCount)) {
                page_localCity++;
                HashMap<String, String> params_map = new HashMap<String, String>();
                params_map.put("UId", "yesicity2015");
                params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                        params_map.put("Yesicity", "1");
                params_map.put("page", String.valueOf(page_localCity));
                notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_LOCAL_CITY_QUERY, params_map));
            }else{
                fragment_order_localCity.notifyDataSetChanged();
            }
        }else if(fragment_order_ticket!=null&&fragment_order_ticket.isAdded()){
            if(resp_orderTicketList!=null&&page_ticket < Integer.valueOf(resp_orderTicketList.PageCount)) {
                page_ticket++;
                HashMap<String, String> params_map = new HashMap<String, String>();
                params_map.put("UId", "yesicity2015");
                params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                params_map.put("Yesicity", "1");
                params_map.put("page", String.valueOf(page_ticket));
                notifyModelChange(Message.obtain(handler, Constants.MODEL_ORDER_TICKET_ORDER_NO_QUERY, params_map));
            }else{
                fragment_order_ticket.notifyDataSetChanged();
            }
        }
    }
}
