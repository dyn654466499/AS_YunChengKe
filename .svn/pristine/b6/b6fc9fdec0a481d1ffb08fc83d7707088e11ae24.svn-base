package com.yunchengke.app.ui.activity.city;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.city.YCKAddCommentResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.YCKAddCommentRequest;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.utils.ToastUtils;

import butterknife.Bind;

public class YCKAddCommentActivity extends BaseActivity {

    public final static String EXTRA_CITY_ID = "EXTRA_CITY_ID";
    private int mId;

    @Bind(R.id.edt_publish_comment_content)
    EditText mEdt_publish_comment_content;
    @Override
    protected View createContentView(ViewGroup parent) {
        View view = inflate(R.layout.activity_yckadd_comment,parent);
        return view;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        lytTitle.setBackgroundResource(R.color.city_background);
        setTitle("评价");
        setTitleLeft(R.string.back);
        setTitleRight(R.string.publish_dynamic_send);
        mId = getIntent().getIntExtra(EXTRA_CITY_ID,0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_base_title_left:
                finish();
            break;
            case R.id.tv_base_title_right:
                publish();
                break;
        }
    }

    private void publish() {

        if(TextUtils.isEmpty(mEdt_publish_comment_content.getText())){
            ToastUtils.show(this,"请填写评论");
            return;
        }
        startDialog(R.string.loading);
        YCKAddCommentRequest request = new YCKAddCommentRequest(new HttpRequestListener<YCKAddCommentResult>(){
            @Override
            public void onResponse(YCKAddCommentResult response) {
                closeDialog();
                ToastUtils.show(YCKAddCommentActivity.this,response.getMessage());
                if(response.isSucceed(YCKAddCommentActivity.this,response.getResultState(),response.getMessage())){
                    setResult(RESULT_OK);
                    finish();
                }
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);

                closeDialog();
            }
        });

        request.setRequestParams(mId,mEdt_publish_comment_content.getText().toString());
        HttpRequestQueue.addToRequestQueue(request);

    }
}
