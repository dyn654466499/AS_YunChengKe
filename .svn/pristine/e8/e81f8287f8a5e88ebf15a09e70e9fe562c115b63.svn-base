package com.yunchengke.app.bean;

import android.content.Context;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/14 11:36
 * 描述：获取验证码数据
 * 版本：1.0
 */
public class CodeResult extends BaseEntity {

    /**
     * errorCode : 0
     * message : 发送成功
     */

    private String errorCode;
    private String message;

    private static final String ERRORCODE= "0";

    public boolean isSucceed(Context context, String errorCode, String message) {
        return ERRORCODE.equals(errorCode);
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
