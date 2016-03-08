package com.yunchengke.app.ui.activity.daemon;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDishesList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.ui.adapter.daemon.catering.CateringBookDishesLargeAdapter;
import com.yunchengke.app.ui.view.daemon.HorizontalListView;

import java.util.ArrayList;

/**
 *  餐饮点餐查看菜品大图
 */
public class CateringBookDishesLargeActivity extends BaseActivity {
    private ArrayList<Resp_CateringDishesList.Rows> resp_cateringDishesListRows;
    private ArrayList<Integer> dishes_num;
    private HorizontalListView lv_catering_book_dishes_large_list;
    private CateringBookDishesLargeAdapter adapter;
    private int position_item = 0;
    private float total_price = 0;
    private int total_count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootLayout = LayoutInflater.from(this).inflate(R.layout.activity_catering_book_dishes_large,null,false);
        setContentView(rootLayout);


        if(getIntent().hasExtra(Constants.KEY_PARCELABLE)){
            resp_cateringDishesListRows = getIntent().getParcelableArrayListExtra(Constants.KEY_PARCELABLE);
            dishes_num = getIntent().getIntegerArrayListExtra(Constants.KEY_INTEGER_LIST);
            total_price = getIntent().getFloatExtra(Constants.KEY_PRICE, 0);
            total_count = getIntent().getIntExtra(Constants.KEY_COUNT,0);

            lv_catering_book_dishes_large_list = (HorizontalListView)findViewById(R.id.lv_catering_book_dishes_large_list);

             adapter = new CateringBookDishesLargeAdapter(this,resp_cateringDishesListRows);
            ArrayList<ArrayList<Integer>> dishes_nums = new ArrayList<ArrayList<Integer>>();
            dishes_nums.add(dishes_num);
            adapter.setData(resp_cateringDishesListRows, dishes_nums);
            lv_catering_book_dishes_large_list.setAdapter(adapter);

            /**
             * 监听菜品数量增或减
             */
            adapter.setDishesChangeCommand(new Commands() {
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
                }
            });


            position_item = getIntent().getIntExtra(Constants.KEY_POSITION, 0);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) lv_catering_book_dishes_large_list.getLayoutParams();
                    params.height = (Application.getScreenHeight() + 100) / 2;
                    lv_catering_book_dishes_large_list.setLayoutParams(params);
                }
            });

            rootLayout.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    /**
                     * 获取frameLayout的矩形区域，判断点击是否在该区域内，若不在，则finish。
                     */
                    Rect rect = new Rect(0, 0, lv_catering_book_dishes_large_list.getMeasuredWidth(),
                            lv_catering_book_dishes_large_list.getMeasuredHeight());
                    if (!rect.contains((int) event.getX(), (int) event.getY())) {
                         finish();
                    }
                    return false;
                }
            });

            ImageView imageView_catering_book_dishes_left_arrow = (ImageView)findViewById(R.id.imageView_catering_book_dishes_left_arrow);
            imageView_catering_book_dishes_left_arrow.setOnClickListener(this);
            ImageView imageView_catering_book_dishes_right_arrow = (ImageView)findViewById(R.id.imageView_catering_book_dishes_right_arrow);
            imageView_catering_book_dishes_right_arrow.setOnClickListener(this);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    lv_catering_book_dishes_large_list.scrollTo(lv_catering_book_dishes_large_list.getMeasuredWidth() * position_item);
                }
            }, 200);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            Intent intent = new Intent();
//            intent.putParcelableArrayListExtra(Constants.KEY_PARCELABLE,resp_cateringDishesListRows);
//            intent.putIntegerArrayListExtra(Constants.KEY_INTEGER_LIST, dishes_num);
//            intent.putExtra(Constants.KEY_PRICE, total_price);
//            intent.putExtra(Constants.KEY_COUNT, total_count);
//            setResult(RESULT_OK,intent);
//            finish();
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.imageView_catering_book_dishes_left_arrow:
//                        if (position_item > 0) {
//                            lv_catering_book_dishes_large_list.scrollTo((lv_catering_book_dishes_large_list.getMeasuredWidth()) * --position_item);
//                            Log.e(getTAG(), "position = " + position_item);
//                        }
//                break;
//
//            case R.id.imageView_catering_book_dishes_right_arrow:
//                        if(position_item<adapter.getCount()-1) {
//                            lv_catering_book_dishes_large_list.scrollTo(lv_catering_book_dishes_large_list.getMeasuredWidth() * ++position_item);
//                            Log.e(getTAG(),"position = "+position_item);
//                        }
//                break;
        }
    }

    @Override
    public void onViewChange(Message msg) {

    }
}
