package com.yunchengke.app.bean.dynamic;

import com.yunchengke.app.bean.BaseEntity;

/**
 * 名称: AddDynamicCommentResult <br/>
 * 描述: 新增评论结果 <br/>
 * 创建时间：2016/1/29 11:51
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class AddDynamicCommentResult extends BaseEntity {

    public final static String RESULT_SUCCESS = "success";
    public final static String RESULT_ERROR = "error";

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
