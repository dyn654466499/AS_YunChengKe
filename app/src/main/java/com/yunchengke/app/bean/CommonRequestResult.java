package com.yunchengke.app.bean;

/**
 * 名称: CommonRequestResult <br/>
 * 描述: 通用返回结果 <br/>
 * 创建时间：2016/1/29 13:56
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class CommonRequestResult extends BaseEntity {

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
