package com.yunchengke.app.bean.city;

import android.content.Context;

import com.yunchengke.app.bean.BaseEntity;

/**
 * 作者：Tinchy
 * 创建时间：2016/2/26 17:04
 * 描述：
 * 版本：1.0
 */
public class FreeJoinResult extends BaseEntity {

    /**
     * resultState : success
     * message : 保存成功
     * Id : 11
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
