package com.yunchengke.app.ui.activity.daemon;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringBookAdd;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDishesList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.CateringModel;
import com.yunchengke.app.ui.adapter.daemon.catering.CateringBookDishesAdapter;
import com.yunchengke.app.utils.daemon.CommonUtil;
import com.yunchengke.app.utils.daemon.DialogUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static com.yunchengke.app.consts.Constants.KEY_ID;

public class CateringBookResultActivity extends BaseActivity {
    private float total_price = 0;
    private int total_count = 0;
    private EditText et_catering_book_result_address ,et_catering_book_result_personCount,
            et_catering_book_result_personName,et_catering_book_result_phone,
            et_catering_book_result_beizhu;
    private Button btn_catering_book_result_time;

    private ArrayList<Resp_CateringDishesList.Rows> resp_cateringDishesListRows;
    private ArrayList<ArrayList<Integer>> dishes_nums;
    private boolean isTimeSelected = false;

    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_book_result);

        setModelDelegate(new CateringModel(handler, this));
        setViewChangeListener(this);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("预订点餐");

        Button btn_back = (Button) findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        if (getIntent().hasExtra(Constants.KEY_TITLE)) {
            EditText et_catering_book_result_storeName = (EditText) findViewById(R.id.et_catering_book_result_storeName);
            et_catering_book_result_storeName.setText(getIntent().getStringExtra(Constants.KEY_TITLE));

            Button btn_catering_book_result_pay = (Button)findViewById(R.id.btn_catering_book_result_pay);
            btn_catering_book_result_pay.setOnClickListener(this);

            if(TextUtils.isEmpty(getIntent().getStringExtra(Constants.KEY))){
                LinearLayout linearLayout_catering_book_result_personCount = (LinearLayout)findViewById(R.id.linearLayout_catering_book_result_personCount);
                linearLayout_catering_book_result_personCount.setVisibility(View.VISIBLE);
                et_catering_book_result_personCount = (EditText)findViewById(R.id.et_catering_book_result_personCount);
            }else{
                LinearLayout linearLayout_catering_book_result_address = (LinearLayout)findViewById(R.id.linearLayout_catering_book_result_address);
                linearLayout_catering_book_result_address.setVisibility(View.VISIBLE);
                et_catering_book_result_address = (EditText)findViewById(R.id.et_catering_book_result_address);
            }

            final TextView tv_catering_book_result_totalPrice = (TextView)findViewById(R.id.tv_catering_book_result_totalPrice);
            total_price = getIntent().getFloatExtra(Constants.KEY_CATERING_PRICE, 0);
            tv_catering_book_result_totalPrice.setText("￥" + CommonUtil.getFormatPrice(total_price,2));

            resp_cateringDishesListRows = new ArrayList<Resp_CateringDishesList.Rows>();
              dishes_nums = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> nums = getIntent().getIntegerArrayListExtra(String.valueOf("nums"));
            dishes_nums.add(nums);

            int count_dishes = getIntent().getIntExtra("count_dishes",0);
            for (int i = 0;i < count_dishes;i++) {
                Resp_CateringDishesList.Rows row = getIntent().getParcelableExtra(String.valueOf(i));
                resp_cateringDishesListRows.add(row);
            }

            CateringBookDishesAdapter cateringBookDishesAdapter = new CateringBookDishesAdapter(this,resp_cateringDishesListRows);
            ListView lv_catering_book_result_dishes_detail = (ListView)findViewById(R.id.lv_catering_book_result_dishes_detail);
            lv_catering_book_result_dishes_detail.setAdapter(cateringBookDishesAdapter);
            cateringBookDishesAdapter.setDishesChangeCommand(new Commands() {
                @Override
                public void executeCommand(Message msg_params) {
                    Float unit_price = (Float) msg_params.obj;
                    //0代表加
                    if (msg_params.what == 0) {
                        total_count++;
                        total_price += unit_price;
                    } else {
                        total_count--;
                        total_price -= unit_price;
                    }
                    tv_catering_book_result_totalPrice.setText("￥" + CommonUtil.getFormatPrice(total_price, 2));
                }
            });
            cateringBookDishesAdapter.setData(resp_cateringDishesListRows, dishes_nums);
            cateringBookDishesAdapter.notifyDataSetChanged();

            SharedPreferences pref = getSharedPreferences("login_info",MODE_PRIVATE);

            et_catering_book_result_personName = (EditText)findViewById(R.id.et_catering_book_result_personName);
            if(!TextUtils.isEmpty(pref.getString("username","")))et_catering_book_result_personName.setText(pref.getString("username",""));

            et_catering_book_result_phone = (EditText)findViewById(R.id.et_catering_book_result_phone);
            if(!TextUtils.isEmpty(pref.getString("mobile","")))et_catering_book_result_phone.setText(pref.getString("mobile",""));

            btn_catering_book_result_time = (Button)findViewById(R.id.btn_catering_book_result_time);
            btn_catering_book_result_time.setOnClickListener(this);
            et_catering_book_result_beizhu = (EditText)findViewById(R.id.et_catering_book_result_beizhu);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title_back:
                finish();
                break;

            case R.id.btn_catering_book_result_time:
                final Calendar calendar = Calendar.getInstance();
                //通过自定义控件AlertDialog实现
                AlertDialog.Builder builder = new AlertDialog.Builder(CateringBookResultActivity.this);
                View view = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_date_picker, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
                //设置日期简略显示 否则详细显示 包括:星期周
                datePicker.setCalendarViewShown(false);
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                long time = calendar.getTimeInMillis();
                datePicker.setMinDate(time);
                //初始化当前日期
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH), null);
                //设置date布局
                builder.setView(view);
                builder.setTitle("设置日期信息");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //日期格式
                        final String date = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth();
                        //赋值后面闹钟使用
                        int mYear = datePicker.getYear();
                        int mMonth = datePicker.getMonth();
                        int mDay = datePicker.getDayOfMonth();

                        dialog.cancel();

                        //自定义控件
                        AlertDialog.Builder builder = new AlertDialog.Builder(CateringBookResultActivity.this);
                        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_time_picker, null);
                        final TimePicker timePicker = (TimePicker) view.findViewById(R.id.time_picker);
                        //初始化时间
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        timePicker.setIs24HourView(true);

                        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
                        timePicker.setCurrentMinute(Calendar.MINUTE);
                        //设置time布局
                        builder.setView(view);
                        builder.setTitle("设置时间信息");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int mHour = timePicker.getCurrentHour();
                                int mMinute = timePicker.getCurrentMinute();
                                //时间小于10的数字 前面补0 如01:12:00
                                btn_catering_book_result_time.setText(date + " " + new StringBuilder().append(mHour < 10 ? 0 + mHour : mHour).append(":")
                                        .append(mMinute < 10 ? 0 + mMinute : mMinute).append(":00"));
                                dialog.cancel();
                                isTimeSelected = true;
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.create().show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
                break;

            case R.id.btn_catering_book_result_pay:
                if(resp_cateringDishesListRows.size()<1){
                     showTip("至少选择一个菜品");
                     return;
                }
                else if(TextUtils.isEmpty(et_catering_book_result_personName.getText().toString())){
                    showTip("姓名不能为空");
                    return;
                }else if(TextUtils.isEmpty(et_catering_book_result_phone.getText().toString())){
                    showTip("手机号不能为空");
                    return;
                }else if(et_catering_book_result_personCount!=null&&TextUtils.isEmpty(et_catering_book_result_personCount.getText().toString())){
                    showTip("人数不能为空");
                    return;
                }else if(!isTimeSelected){
                    showTip("用餐时间不能为空");
                    return;
                }else if(et_catering_book_result_address!=null&&TextUtils.isEmpty(et_catering_book_result_address.getText().toString())){
                    showTip("送餐地址不能为空");
                    return;
                }
                DialogUtil.showDialog(CateringBookResultActivity.this, "提示！", "确定下单了吗？", new Commands() {
                    @Override
                    public void executeCommand(Message msg_params) {
                        String jsonString = "";
                        Resp_CateringBookAdd resp_cateringBookAdd = new Resp_CateringBookAdd();
                        List<Resp_CateringBookAdd.billdetail> billdetails = new ArrayList<Resp_CateringBookAdd.billdetail>();
                        /**
                         * 如果是点餐
                         */
                        if (TextUtils.isEmpty(getIntent().getStringExtra(Constants.KEY))) {
                            resp_cateringBookAdd.Field_RS = et_catering_book_result_personCount.getText().toString();
                        }
                        /**
                         * 如果是外卖
                         */
                        else {
                            resp_cateringBookAdd.Field_SCDZ = et_catering_book_result_address.getText().toString();
                        }
                        for (int i = 0; i < resp_cateringDishesListRows.size(); i++) {
                            if (dishes_nums.get(0).get(i) > 0) {
                                Resp_CateringBookAdd.billdetail billdetail = new Resp_CateringBookAdd.billdetail();
                                billdetail.Field_CPDJ = resp_cateringDishesListRows.get(i).Field_CPDJ;
                                billdetail.Field_CPJS = "";
                                billdetail.Field_CPPM = resp_cateringDishesListRows.get(i).Field_CPPM;
                                billdetail.Field_CPSL = String.valueOf(dishes_nums.get(0).get(i));
                                billdetail.X6_Product_Id = resp_cateringDishesListRows.get(i).X6_Product_Id;
                                billdetails.add(billdetail);
                            }
                        }

                        resp_cateringBookAdd.Field_SJID = getIntent().getStringExtra(KEY_ID);

                        resp_cateringBookAdd.Field_DWXM = et_catering_book_result_personName.getText().toString();
                        resp_cateringBookAdd.Field_YHSJ = et_catering_book_result_phone.getText().toString();
                        resp_cateringBookAdd.Field_DWYCSJ = btn_catering_book_result_time.getText().toString();
                        resp_cateringBookAdd.Field_DWBZ = et_catering_book_result_beizhu.getText().toString();
                        resp_cateringBookAdd.billdetail = billdetails;
                        if(billdetails.size()>0) {
                            // 利用gson对象生成json字符串
                            Gson gson = new Gson();
                            jsonString = gson.toJson(resp_cateringBookAdd);

                            HashMap<String, String> params_map = new HashMap<String, String>();
                            params_map.put("UId", "yesicity2015");
                            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                            params_map.put("Yesicity", "1");
                            params_map.put("param", jsonString);
                            //Log.e("sdfsdfsd", jsonString);

                            dialog = new ProgressDialog(CateringBookResultActivity.this);
                            dialog.setMessage("正在创建订单，请稍等...");
                            dialog.setCancelable(false);
                            dialog.show();
                            /**
                             * 如果是点餐
                             */
                            if (TextUtils.isEmpty(getIntent().getStringExtra(Constants.KEY))) {
                                notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_BOOK_ADD, params_map));
                            }
                            /**
                             * 如果是外卖
                             */
                            else {
                                notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_TAKE_OUT_ADD, params_map));
                            }
                        }else {
                            showTip("菜品数量至少选择一份以上");
                            return;
                        }
                    }
                });
                break;

                    }
    }

    @Override
    public void onViewChange(Message msg) {
        switch (msg.what){
            case Constants.VIEW_CATERING_BOOK_ADD:
                if(msg.obj instanceof String) {
                    if ("success".equals(msg.obj)) {
                        if(dialog.isShowing())dialog.dismiss();
                        Intent intent = new Intent(CateringBookResultActivity.this, OrderCateringDetailActivity.class);
                        intent.putExtra(Constants.KEY_ID, String.valueOf(msg.arg1));
                        intent.putExtra(Constants.KEY_TYPE, "订餐");
                        startActivity(intent);
                        finish();
                    } else {
                        if (!isDestroyed) {
                            DialogUtil.showDialog(CateringBookResultActivity.this, "提示！", (String) msg.obj, new Commands() {
                                @Override
                                public void executeCommand(Message msg_params) {

                                }
                            });
                        }
                    }
                }
                break;

            case Constants.VIEW_CATERING_TAKE_OUT_ADD:
                if(msg.obj instanceof String) {
                    if ("success".equals(msg.obj)) {
                        if(dialog.isShowing())dialog.dismiss();
                        Intent intent = new Intent(CateringBookResultActivity.this, OrderCateringDetailActivity.class);
                        intent.putExtra(Constants.KEY_ID, String.valueOf(msg.arg1));
                        intent.putExtra(Constants.KEY_TYPE, "外卖");
                        startActivity(intent);
                        finish();
                    } else {
                        if (!isDestroyed) {
                            DialogUtil.showDialog(CateringBookResultActivity.this, "提示！", (String) msg.obj, new Commands() {
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
}
