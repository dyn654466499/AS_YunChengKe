package com.yunchengke.app.ui.activity.daemon;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetailComment;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.CateringModel;
import com.yunchengke.app.ui.adapter.daemon.catering.CateringCommentAdapter;
import com.yunchengke.app.ui.view.daemon.CustomEditText;
import com.yunchengke.app.utils.daemon.DialogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.yunchengke.app.consts.Constants.KEY_ID;
import static com.yunchengke.app.consts.Constants.KEY_ID_COMMENT;

public class CateringCommentActivity extends BaseActivity {

    private PullToRefreshListView lv_catering_comment;
    private int page = 1;
    private Resp_CateringDetailComment resp_cateringDishesComment;
    private CateringCommentAdapter adapter;
    private List<Resp_CateringDetailComment.Rows> resp_cateringDishesCommentRows;

    private CustomEditText et_catering_comment_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_comment);
        setModelDelegate(new CateringModel(handler, this));
        setViewChangeListener(this);

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("网友点评");
        if(getIntent().hasExtra(KEY_ID)) {
            getCommentList();

            resp_cateringDishesCommentRows = new ArrayList<Resp_CateringDetailComment.Rows>();


            lv_catering_comment = (PullToRefreshListView) findViewById(R.id.lv_catering_comment);
            lv_catering_comment.setPullLoadEnabled(true);
            lv_catering_comment.getRefreshableView().setHorizontalScrollBarEnabled(false);
            lv_catering_comment.getRefreshableView().setVerticalScrollBarEnabled(false);

            lv_catering_comment.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    resp_cateringDishesCommentRows.clear();
                    page = 1;
                    getCommentList();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    if (page < Integer.valueOf(resp_cateringDishesComment.PageCount)) {
                        page++;
                        getCommentList();
                    } else {
                        lv_catering_comment.onPullRefreshComplete();
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            adapter = new CateringCommentAdapter(CateringCommentActivity.this, resp_cateringDishesCommentRows);
            lv_catering_comment.setAdapter(adapter);

            Button btn_catering_comment_publish = (Button)findViewById(R.id.btn_catering_comment_publish);
            btn_catering_comment_publish.setOnClickListener(this);

            et_catering_comment_content = (CustomEditText)findViewById(R.id.et_catering_comment_content);
        }
    }

    private void getCommentList(){
        HashMap<String, String> params_map = new HashMap<String, String>();
        params_map.put("UId", "yesicity2015");
        params_map.put("Field_YHID", LoginInfo.getInstance().getId());
        params_map.put("Yesicity", "1");
        /**
         *  这个是店铺ID
         */
        params_map.put("Id", getIntent().getStringExtra(KEY_ID));
        params_map.put("page", String.valueOf(page));
        notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_COMMENT, params_map));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_title_back:
                finish();
                break;

            /**
             *  新增评论
             */
            case R.id.btn_catering_comment_publish:
                if(TextUtils.isEmpty(et_catering_comment_content.getText().toString())){
                    showTip("评论内容不能为空哦!");
                }else{
                    DialogUtil.showDialog(CateringCommentActivity.this, "提示！", "确定要发表该评论吗？", new Commands() {
                        @Override
                        public void executeCommand(Message msg_params) {
                            String comment = et_catering_comment_content.getText().toString();

                            HashMap<String, String> params_map = new HashMap<String, String>();
                            params_map.put("UId", "yesicity2015");
                            /**
                             *  Field_PLNR对应的值一定要加上""
                             *
                             */
                            params_map.put("param","{Id:"+getIntent().getStringExtra(KEY_ID_COMMENT)+",Field_PLNR:\""+comment+"\"}");
                            params_map.put("Field_YHID",LoginInfo.getInstance().getId());
                            params_map.put("Yesicity", "1");
                            notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_COMMENT_ADD, params_map));
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onViewChange(Message msg) {
        switch (msg.what) {
            /**
             * 评论列表
             */
            case Constants.VIEW_CATERING_DETAIL_COMMENT:
                if (!isDestroyed) {
                if (msg.obj instanceof String) {
                    DialogUtil.showDialog(CateringCommentActivity.this, "网友点评", (String) msg.obj, new Commands() {
                       @Override
                       public void executeCommand(Message msg_params) {

                       }
                    });
                    resp_cateringDishesCommentRows.clear();
                    lv_catering_comment.onPullRefreshComplete();
                    adapter.notifyDataSetChanged();
                } else {

                         resp_cateringDishesComment = (Resp_CateringDetailComment) msg.obj;
                         resp_cateringDishesCommentRows.addAll(resp_cateringDishesComment.rows);
                         lv_catering_comment.onPullRefreshComplete();
                         adapter.notifyDataSetChanged();
                    }
                }
                break;

            /**
             *  新增评论
             */
            case Constants.VIEW_CATERING_DETAIL_COMMENT_ADD:

                if(msg.obj instanceof String) {

                    if ("success".equals(msg.obj)) {
                        showTip("已成功发表评论！");
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        if (!isDestroyed) {
                        DialogUtil.showDialog(CateringCommentActivity.this, "提示！", (String) msg.obj, new Commands() {
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
