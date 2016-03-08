package com.yunchengke.app.bean;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/14 13:21
 * 描述：忘记密码param数据
 * 版本：1.0
 */
public class ForgetEntity extends BaseEntity{
    private String username;
    private String usepassword;
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsepassword() {
        return usepassword;
    }

    public void setUsepassword(String usepassword) {
        this.usepassword = usepassword;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString(){
        return "{tel:\"" + this.username + "\",password:\"" + this.usepassword + "\",code:\"" + this.code + "\"}";
    }
}
