package com.yunchengke.app.ui.activity.daemon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringBookClassify;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDishesList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.CateringModel;
import com.yunchengke.app.ui.adapter.daemon.catering.CateringBookClassifyAdapter;
import com.yunchengke.app.ui.adapter.daemon.catering.CateringBookDishesAdapter;
import com.yunchengke.app.utils.daemon.CommonUtil;
import com.yunchengke.app.utils.daemon.DialogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.yunchengke.app.consts.Constants.KEY_ID;
import static com.yunchengke.app.consts.Constants.VIEW_CATERING_BOOK_CLASSIFY;
import static com.yunchengke.app.consts.Constants.VIEW_CATERING_BOOK_DISHES;

public class CateringBookActivity extends BaseActivity {
    private List<ArrayList<Resp_CateringDishesList.Rows>> resp_cateringDishesListRows;
    private CateringBookDishesAdapter cateringBookDishesAdapter;
    private List<ArrayList<Integer>> dishes_nums;
    private TextView tv_catering_book_total;
    private int position_classify=0;
    private float total_price = 0;
    private int total_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_book);

        setModelDelegate(new CateringModel(handler, this));
        setViewChangeListener(this);

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("预订点餐");

        if(getIntent().hasExtra(KEY_ID)){
            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("param", "{Id:" + getIntent().getStringExtra(KEY_ID) + "}");
            notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_BOOK_CLASSIFY, params_map));


        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_title_back:
                finish();
                break;

            case R.id.btn_catering_book_commit:
                Intent intent = new Intent(CateringBookActivity.this,CateringBookResultActivity.class);
                /**
                 * KEY判断是否是外卖
                 */
                intent.putExtra(Constants.KEY,getIntent().getStringExtra(Constants.KEY));
                /**
                 * 商店名
                 */
                intent.putExtra(Constants.KEY_TITLE,getIntent().getStringExtra(Constants.KEY_TITLE));
                intent.putExtra(Constants.KEY_CATERING_PRICE, total_price);
                intent.putExtra(Constants.KEY_ID,getIntent().getStringExtra(KEY_ID));
                int count_dishes = 0;
                ArrayList<Integer> nums = new ArrayList<Integer>();
                for (int i = 0;i<dishes_nums.size();i++) {
                    if(dishes_nums.get(i)!=null) {
                        for (int j = 0; j < dishes_nums.get(i).size(); j++) {
                            if (dishes_nums.get(i).get(j) > 0) {
                                nums.add(dishes_nums.get(i).get(j));
                                intent.putExtra(String.valueOf(count_dishes), resp_cateringDishesListRows.get(i).get(j));
                                count_dishes++;
                            }
                        }
                    }
                }
                if(count_dishes>0) {
                    intent.putExtra("count_dishes", count_dishes);
                    intent.putIntegerArrayListExtra("nums", nums);
                    startActivity(intent);
                }else{
                    showTip("至少选择一个菜品！");
                }
                break;
        }
    }

    @Override
    public void onViewChange(Message msg) {
       switch (msg.what){
           /**
            * 通知类别界面
            */
           case VIEW_CATERING_BOOK_CLASSIFY:
               if (!isDestroyed) {
               if(msg.obj instanceof String){
                   DialogUtil.showDialog(CateringBookActivity.this, "预订点餐", (String) msg.obj, new Commands() {
                       @Override
                       public void executeCommand(Message msg_params) {

                       }
                   });
               }else {

                       final Resp_CateringBookClassify resp_cateringBookClassify = (Resp_CateringBookClassify)msg.obj;
                       if (resp_cateringBookClassify.rows.size() > 0) {
                           tv_catering_book_total = (TextView)findViewById(R.id.tv_catering_book_total);
                           tv_catering_book_total.setText(String.format(
                                   getString(R.string.catering_book_total),
                                   String.valueOf(total_price),
                                   String.valueOf(total_count)));
                           Button btn_catering_book_commit = (Button)findViewById(R.id.btn_catering_book_commit);
                           btn_catering_book_commit.setOnClickListener(this);

                           resp_cateringDishesListRows = new ArrayList<ArrayList<Resp_CateringDishesList.Rows>>();
                           dishes_nums = new ArrayList<ArrayList<Integer>>();
                           /**
                            * 构造一个和类别个数相同的list
                            */
                           for (Resp_CateringBookClassify.Rows row : resp_cateringBookClassify.rows) {
                               resp_cateringDishesListRows.add(null);
                               dishes_nums.add(null);
                           }
                           ListView lv_catering_book_dishes = (ListView) findViewById(R.id.lv_catering_book_dishes);
                           cateringBookDishesAdapter = new CateringBookDishesAdapter(this, new ArrayList<Resp_CateringDishesList.Rows>());
                           lv_catering_book_dishes.setAdapter(cateringBookDishesAdapter);
                           /**
                            * 监听菜品数量增或减
                            */
                           cateringBookDishesAdapter.setDishesChangeCommand(new Commands() {
                               @Override
                               public void executeCommand(Message msg_params) {
                                   Float unit_price = (Float)msg_params.obj;
                                   //0代表加
                                   if(msg_params.what ==0){
                                       total_count ++;
                                       total_price += unit_price;
                                   }else{
                                       total_count --;
                                       total_price -= unit_price;
                                   }
                                   cateringBookDishesAdapter.setTotal_price(total_price);
                                   cateringBookDishesAdapter.setTotal_count(total_count);

                                   tv_catering_book_total.setText(String.format(
                                           getString(R.string.catering_book_total),
                                            CommonUtil.getFormatPrice(total_price, 2),
                                            String.valueOf(total_count)));

                               }
                           });

                           ListView lv_catering_book_classify = (ListView) findViewById(R.id.lv_catering_book_classify);
                           final CateringBookClassifyAdapter cateringBookClassifyAdapter = new CateringBookClassifyAdapter(this, resp_cateringBookClassify.rows);
                           lv_catering_book_classify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                               @Override
                               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                   cateringBookDishesAdapter.setTotal_price(total_price);
                                   cateringBookDishesAdapter.setTotal_count(total_count);
                                   position_classify = position;

                                   cateringBookClassifyAdapter.setPosition(position);
                                   cateringBookClassifyAdapter.notifyDataSetChanged();
                                   if (resp_cateringDishesListRows.get(position) != null) {
                                       cateringBookDishesAdapter.setPosition(position);
                                       cateringBookDishesAdapter.setData(resp_cateringDishesListRows.get(position), dishes_nums);
                                       cateringBookDishesAdapter.notifyDataSetChanged();
                                   } else {

                                       HashMap<String, String> params_map = new HashMap<String, String>();
                                       params_map.put("UId", "yesicity2015");
                                       params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                                       params_map.put("Yesicity", "1");
                                       params_map.put("param", "{Id:" + resp_cateringBookClassify.rows.get(position).X6_Product_Id + "}");
                                       notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_BOOK_DISHES, position, 0, params_map));

                                   }
                               }
                           });
                           lv_catering_book_classify.performItemClick(lv_catering_book_classify, 0, 0);
                           lv_catering_book_classify.setAdapter(cateringBookClassifyAdapter);
                       }

                   }
               }
               break;
/**
 * 通知菜品界面
 */
           case VIEW_CATERING_BOOK_DISHES:
               if (!isDestroyed) {
               if(msg.obj instanceof String){
                   DialogUtil.showDialog(CateringBookActivity.this, "预订点餐", (String) msg.obj, new Commands() {
                       @Override
                       public void executeCommand(Message msg_params) {

                       }
                   });
               }else {

                       /**
                        * msg.arg1是类别列表传过来的点击位置,
                        */
                       Resp_CateringDishesList resp_cateringDishesList = (Resp_CateringDishesList)msg.obj;
                       /**
                        *  用于给每个类别的菜品记录点菜数量
                        */
                       if(dishes_nums.get(msg.arg1)==null) {
                           ArrayList<Integer> positions = new ArrayList<Integer>();
                           for (Resp_CateringDishesList.Rows row : resp_cateringDishesList.rows) {
                               positions.add(0);
                           }
                           dishes_nums.set(msg.arg1, positions);
                       }
                   cateringBookDishesAdapter.setTotal_price(total_price);
                   cateringBookDishesAdapter.setTotal_count(total_count);
                       cateringBookDishesAdapter.setPosition(msg.arg1);
                       cateringBookDishesAdapter.setData(resp_cateringDishesList.rows, dishes_nums);
                       cateringBookDishesAdapter.notifyDataSetChanged();

                       resp_cateringDishesListRows.set(msg.arg1, resp_cateringDishesList.rows);
                   }
               }

       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case Constants.REQUEST_CODE_CATERING_BOOK_DISHES_LARGE:
//                            ArrayList<Resp_CateringDishesList.Rows> resp_cateringDishesListRows = data.getParcelableArrayListExtra(Constants.KEY_PARCELABLE);
//                            ArrayList<Integer> dishes_num = data.getIntegerArrayListExtra(Constants.KEY_INTEGER_LIST);
//                            total_price = data.getFloatExtra(Constants.KEY_PRICE, 0);
//                            total_count = data.getIntExtra(Constants.KEY_COUNT, 0);
//
//                            cateringBookDishesAdapter.setPosition(position_classify);
//                            dishes_nums.set(position_classify, dishes_num);
//                            cateringBookDishesAdapter.setData(resp_cateringDishesListRows, dishes_nums);
//                            cateringBookDishesAdapter.notifyDataSetChanged();
//
//
//                            tv_catering_book_total.setText(String.format(
//                                    getString(R.string.catering_book_total),
//                                    CommonUtil.getFormatPrice(total_price, 2),
//                                    String.valueOf(total_count)));

                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
