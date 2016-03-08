package com.yunchengke.app.bean;

import android.content.Context;

/**
 * 类名：FollowMediaResult <br/>
 * 描述：关注结果
 * 创建时间：2016/01/19 21:15
 *
 * @author hanter
 * @version 1.0
 */
public class FollowMediaResult {

    /**
     * resultState : follow:关注，cancel:取消，error：错误
     * message : 取消关注
     */

    private String resultState;
    private String message;
    public static final String FOLLOW = "follow";
    public static final String CANCEL = "cancel";
    public static final String ERROR = "error";

    public boolean isFollow(Context context, String resultState) {
        return FOLLOW.equals(resultState);
    }
    public boolean isCancel(Context context, String resultState) {
        return CANCEL.equals(resultState);
    }
    public boolean isError(Context context, String resultState) {
        return ERROR.equals(resultState);
    }
    public void setResultState(String resultState) {
        this.resultState = resultState;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultState() {
        return resultState;
    }

    public String getMessage() {
        return message;
    }
}
