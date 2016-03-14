package com.yunchengke.app.bean;

import android.content.Context;

import com.yunchengke.app.utils.ToastUtils;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/13 16:04
 * 描述：登录数据
 * 版本：1.0
 */
public class LoginResult extends BaseEntity{

    /**
     * resultState : success
     * message : 登录成功！
     * useInfo : {"username":"andy","userpassword":"123456","Id":"169"}
     */

    private String resultState;
    private String message;

    public static final String SUCCEED = "success";

    public boolean isSucceed(Context context, String resultState, String message) {
        return SUCCEED.equals(resultState);
    }

    public boolean isSucceed(Context context, String resultState, String message, boolean isToast) {
        if(SUCCEED.equals(resultState)) {
            return true;
        } else {
            if(isToast) {
                ToastUtils.show(context,message);
            }
            return false;
        }
    }

    /**
     * username : andy
     * userpassword : 123456
     * Id : 169
     */

    private UseInfoEntity useInfo;

    public void setResultState(String resultState) {
        this.resultState = resultState;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUseInfo(UseInfoEntity useInfo) {
        this.useInfo = useInfo;
    }

    public String getResultState() {
        return resultState;
    }

    public String getMessage() {
        return message;
    }

    public UseInfoEntity getUseInfo() {
        return useInfo;
    }

    public static class UseInfoEntity {
        private String username;
        private String userpassword;
        private String Id;
        private String Mobile;

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String mobile) {
            Mobile = mobile;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setUserpassword(String userpassword) {
            this.userpassword = userpassword;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getUsername() {
            return username;
        }

        public String getUserpassword() {
            return userpassword;
        }

        public String getId() {
            return Id;
        }
    }
}
