package com.yunchengke.app.ui.activity.daemon;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.CateringModel;
import com.yunchengke.app.utils.daemon.DialogUtil;

import java.util.HashMap;

import static com.yunchengke.app.consts.Constants.KEY_ID_COMMENT;

public class CommentActivity extends BaseActivity {
    private EditText et_comment_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setModelDelegate(new CateringModel(handler, this));
        setViewChangeListener(this);

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("评论");

        if(getIntent().hasExtra(KEY_ID_COMMENT)) {
            Button btn_title_right = (Button) findViewById(R.id.btn_title_right);
            btn_title_right.setText("发送");
            btn_title_right.setVisibility(View.VISIBLE);
            btn_title_right.setOnClickListener(this);

            et_comment_content = (EditText)findViewById(R.id.et_comment_content);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    et_comment_content.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(et_comment_content, 0);
                }
            },500);
        }
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
            case R.id.btn_title_right:
                if(TextUtils.isEmpty(et_comment_content.getText().toString())){
                    showTip("评论内容不能为空哦!");
                }else{
                    DialogUtil.showDialog(CommentActivity.this, "提示！", "确定要发表该评论吗？", new Commands() {
                        @Override
                        public void executeCommand(Message msg_params) {
                            String comment = et_comment_content.getText().toString();

                            HashMap<String, String> params_map = new HashMap<String, String>();
                            params_map.put("UId", "yesicity2015");
                            /**
                             *  Field_PLNR对应的值一定要加上""
                             *
                             */
                            params_map.put("param", "{Id:" + getIntent().getStringExtra(KEY_ID_COMMENT) + ",Field_PLNR:\"" + comment + "\"}");
                            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
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
        switch (msg.what){
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
                            DialogUtil.showDialog(CommentActivity.this, "提示！", (String) msg.obj, new Commands() {
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
