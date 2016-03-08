package com.yunchengke.app.bean;

import android.content.Context;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/21 14:23
 * 描述：
 * 版本：1.0
 */
public class ThirdLoginResult {
    /**
     * resultState : success
     * message : 登录成功！
     * Id : 299
     */

    private String resultState;
    private String message;
    private String Id;

    public static final String SUCCEED = "success";

    public boolean isSucceed(Context context, String resultState, String message) {
        return SUCCEED.equals(resultState);
    }
    public void setResultState(String resultState) {
        this.resultState = resultState;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getResultState() {
        return resultState;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return Id;
    }
}
