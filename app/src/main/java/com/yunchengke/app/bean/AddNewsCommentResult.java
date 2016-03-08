package com.yunchengke.app.bean;

/**
 * 类名：AddNewsCommentResult <br/>
 * 描述：添加对新闻的评论结果
 * 创建时间：2016/01/17 0:04
 *
 * @author hanter
 * @version 1.0
 */
public class AddNewsCommentResult extends BaseEntity {

    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_ERROR = "error";

    /**
     * resultState : success
     * message : 保存成功
     */

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
