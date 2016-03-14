package com.yunchengke.app.ui.activity.common;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;
import com.yunchengke.app.bean.CommonRequestResult;
import com.yunchengke.app.bean.MyUserInfo;
import com.yunchengke.app.bean.dynamic.FollowUserResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.FollowUserRequest;
import com.yunchengke.app.http.request.MyUserInfoRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.daemon.CircleImageView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 名称: UserInfoActivity <br/>
 * 描述: 通用用户信息 <br/>
 * 创建时间：2016/1/28 15:24
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class UserInfoActivity extends BaseActivity {
    public static final String EXTRA_USER_ID = "EXTRA_USER_ID";

    @Bind(R.id.iv_user_info_portrait)
    CircleImageView ivUserInfoPortrait;
    @Bind(R.id.tv_user_info_name)
    TextView tvUserInfoName;
    @Bind(R.id.tv_user_info_location)
    TextView tvUserInfoLocation;
    @Bind(R.id.tv_user_info_follow_number)
    TextView tvUserInfoFollowNumber;
    @Bind(R.id.tv_user_info_fans_number)
    TextView tvUserInfoFansNumber;
    @Bind(R.id.btn_user_info_follow)
    Button btnUserInfoFollow;
    @Bind(R.id.btn_user_info_message)
    Button btnUserInfoMessage;
    @Bind(R.id.lyt_user_info_state)
    ViewGroup lytUserInfoState;

    @Bind(R.id.lyt_user_info_follow)
    RelativeLayout lytUserInfoFollow;
    @Bind(R.id.lyt_user_info_fans)
    RelativeLayout lytUserInfoFans;

    private long mUserId;
    private boolean mSelf; // 自己
    MyUserInfo.RowsEntity mUserInfo;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_user_info, parent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_USER_ID, mUserId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (savedInstanceState == null) {
            mUserId = getIntent().getLongExtra(EXTRA_USER_ID, 0);
        } else {
            mUserId = savedInstanceState.getLong(EXTRA_USER_ID, 0);
        }

        lytTitle.setBackgroundResource(R.color.user_info_title_background);
        setTitle("");
        setTitleLeft(R.string.back);

        // 头像
        String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + mUserId;
        Glide.with(this).load(url).placeholder(R.drawable.ic_default_portrait).into(ivUserInfoPortrait);

        updateUserInfo();
    }

    private void updateUserInfo() {
        MyUserInfoRequest req = new MyUserInfoRequest(new HttpRequestListener<MyUserInfo>() {
            @Override
            public void onResponse(MyUserInfo response) {
                super.onResponse(response);

                if (!String.valueOf(mUserId).equals(Application.loginInfo.getId())) {
                    mSelf = false;
                    setTitleRight("");
                    lytUserInfoState.setVisibility(View.VISIBLE);
                } else {
                    mSelf = true;
                    setTitleRight("编辑资料");
                    lytUserInfoState.setVisibility(View.GONE);
                }

                try {
                    mUserInfo = response.getRows().get(0);

                    if (mUserInfo == null) {
                        return;
                    }

                    tvUserInfoLocation.setText(mUserInfo.getField_SZCS());
                    tvUserInfoFollowNumber.setText(mUserInfo.getFollowed() + "");
                    tvUserInfoFansNumber.setText(mUserInfo.getFollow_Me() + "");

                    if (mUserInfo.getIsFollow() == 0) {
                        btnUserInfoFollow.setText("关注");
                        btnUserInfoFollow.setEnabled(true);
                    } else {
                        btnUserInfoFollow.setText("取消关注");
                        btnUserInfoFollow.setEnabled(true);
                    }
                } catch (Exception e) {
                    // 异常处理
                }

            }
        });
        req.setRequestParams(mUserId);
        HttpRequestQueue.addToRequestQueue(req);
    }

    @Override
    @OnClick({R.id.lyt_user_info_follow, R.id.lyt_user_info_fans, R.id.btn_user_info_follow,
            R.id.btn_user_info_message})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            case R.id.tv_base_title_right:
                if (mSelf) {
                    UIHelper.gotoEditPersonInfoActivity(UserInfoActivity.this);
                }
                break;

            case R.id.lyt_user_info_follow:
                if (mSelf) {
                    UIHelper.gotoFollowListActivity(UserInfoActivity.this, mUserId);
                }
                break;

            case R.id.lyt_user_info_fans:
                if (mSelf) {
                    UIHelper.gotoFansListActivity(UserInfoActivity.this, mUserId);
                }
                break;

            case R.id.btn_user_info_follow:
                followUser();
                break;

            case R.id.btn_user_info_message:
                break;

            default:
                super.onClick(v);
                break;
        }
    }

    private void followUser() {

        if (mUserInfo == null) {
            return;
        }

        FollowUserRequest req = new FollowUserRequest(new HttpRequestListener<CommonRequestResult>() {
            @Override
            public void onResponse(CommonRequestResult response) {
                super.onResponse(response);

                String result = response.getResultState();

                if (FollowUserResult.FOLLOW_SUCCESS.equals(result)) {
                    btnUserInfoFollow.setText("取消关注");

                    if (mUserInfo != null) {
                        mUserInfo.setIsFollow(1);
                    }
                } else if (FollowUserResult.CANCEL_SUCCESS.equals(result)) {
                    btnUserInfoFollow.setText("关注");

                    if (mUserInfo != null) {
                        mUserInfo.setIsFollow(0);
                    }
                } else {
                    Toast.makeText(UserInfoActivity.this.getApplicationContext(), response.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

                updateUserInfo();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });

        req.setRequestParams(mUserInfo.getX6_Admin_UserType(), mUserId, mUserInfo.getIsFollow() == 0);
        HttpRequestQueue.addToRequestQueue(req);
    }

}
