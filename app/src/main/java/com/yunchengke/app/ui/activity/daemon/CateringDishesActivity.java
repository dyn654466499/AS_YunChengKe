package com.yunchengke.app.ui.activity.daemon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshGridView;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDishesList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.CateringModel;
import com.yunchengke.app.ui.adapter.daemon.catering.CateringDishesAdapter;
import com.yunchengke.app.utils.daemon.DialogUtil;
import com.yunchengke.app.utils.daemon.ScreenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.yunchengke.app.consts.Constants.KEY_ID;
import static com.yunchengke.app.consts.Constants.KEY_TYPE;
import static com.yunchengke.app.consts.Constants.VIEW_CATERING_DETAIL_ALL_DISHES;

public class CateringDishesActivity extends BaseActivity {
    private int page = 1;
    private CateringDishesAdapter adapter;
    private PullToRefreshGridView gv_catering_dishes;
    private Resp_CateringDishesList resp_cateringDishesList;
    private List<Resp_CateringDishesList.Rows> resp_cateringDishesListRows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_dishes);
        setModelDelegate(new CateringModel(handler, this));
        setViewChangeListener(this);

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        if(getIntent().hasExtra(KEY_TYPE)){
            TextView tv_title = (TextView)findViewById(R.id.tv_title);
            if("0".equals(getIntent().getStringExtra(KEY_TYPE))){
                tv_title.setText("热门菜品");
            }else{
                tv_title.setText("本店菜品");
            }


            resp_cateringDishesListRows = new ArrayList<Resp_CateringDishesList.Rows>();

            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("type", getIntent().getStringExtra(KEY_TYPE));
            params_map.put("Id", getIntent().getStringExtra(KEY_ID));
            params_map.put("page", String.valueOf(page));
            notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_ALL_DISHES, params_map));


             gv_catering_dishes = (PullToRefreshGridView)findViewById(R.id.gv_catering_dishes);
             gv_catering_dishes.setPullLoadEnabled(true);
             gv_catering_dishes.getRefreshableView().setHorizontalScrollBarEnabled(false);
             gv_catering_dishes.getRefreshableView().setVerticalScrollBarEnabled(false);
             gv_catering_dishes.getRefreshableView().setNumColumns(2);
             gv_catering_dishes.getRefreshableView().setVerticalSpacing(ScreenUtil.dip2px(this, 15));
             gv_catering_dishes.getRefreshableView().setHorizontalSpacing(ScreenUtil.dip2px(this, 15));

             gv_catering_dishes.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
                 @Override
                 public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                     resp_cateringDishesListRows.clear();
                     page = 1;
                     HashMap<String, String> params_map = new HashMap<String, String>();
                     params_map.put("UId", "yesicity2015");
                     params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                     params_map.put("Yesicity", "1");
                     params_map.put("type", getIntent().getStringExtra(KEY_TYPE));
                     params_map.put("Id", getIntent().getStringExtra(KEY_ID));
                     params_map.put("page", "1");
                     notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_ALL_DISHES, params_map));
                 }

                 @Override
                 public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                     if (page < Integer.valueOf(resp_cateringDishesList.PageCount)) {
                         page++;
                         HashMap<String, String> params_map = new HashMap<String, String>();
                         params_map.put("UId", "yesicity2015");
                         params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                         params_map.put("Yesicity", "1");
                         params_map.put("type", getIntent().getStringExtra(KEY_TYPE));
                         params_map.put("Id", getIntent().getStringExtra(KEY_ID));
                         params_map.put("page", String.valueOf(page));
                         notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_ALL_DISHES, params_map));
                     } else {
                         gv_catering_dishes.onPullRefreshComplete();
                         adapter.notifyDataSetChanged();
                     }

                 }
             });
             adapter = new CateringDishesAdapter(CateringDishesActivity.this,resp_cateringDishesListRows);
             gv_catering_dishes.getRefreshableView().setAdapter(adapter);
             adapter.setItemCommands(new Commands() {
                @Override
                public void executeCommand(Message msg_params) {
                    Intent intent = new Intent(CateringDishesActivity.this,CateringBookActivity.class);
                    intent.putExtra(KEY_ID,getIntent().getStringExtra(KEY_ID));
                    intent.putExtra(Constants.KEY_TITLE, getIntent().getStringExtra(Constants.KEY_TITLE));
                    startActivity(intent);
                }
            });

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_title_back:
                finish();
                break;
        }
    }

    @Override
    public void onViewChange(Message msg) {
        switch (msg.what){
            case VIEW_CATERING_DETAIL_ALL_DISHES:
                if (!isDestroyed) {
                if(msg.obj instanceof String){
                    DialogUtil.showDialog(CateringDishesActivity.this, "提示！", (String) msg.obj, new Commands() {
                        @Override
                        public void executeCommand(Message msg_params) {

                        }
                    });
                    resp_cateringDishesListRows.clear();
                    gv_catering_dishes.onPullRefreshComplete();
                    adapter.notifyDataSetChanged();
                }else {

                        resp_cateringDishesList = (Resp_CateringDishesList) msg.obj;
                        resp_cateringDishesListRows.addAll(resp_cateringDishesList.rows);
                        gv_catering_dishes.onPullRefreshComplete();
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }
}
