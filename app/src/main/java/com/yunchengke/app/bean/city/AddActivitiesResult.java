package com.yunchengke.app.bean.city;

import com.yunchengke.app.bean.BaseEntity;

/**
 * Created by Administrator on 2016/1/30.
 */
public class AddActivitiesResult extends BaseEntity {
    /**
     * resultState : success
     * message : 保存成功
     */
    public final static String RESULT_SUCCESS = "success";
    public final static String RESULT_ERROR = "error";
    public final static String RESULT_EXITS = "Exits";

    private String resultState;
    private String message;

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
