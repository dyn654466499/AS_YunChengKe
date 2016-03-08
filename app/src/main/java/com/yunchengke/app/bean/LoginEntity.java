package com.yunchengke.app.bean;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/13 22:25
 * 描述：登录param数据
 * 版本：1.0
 */
public class LoginEntity extends BaseEntity{
    private String username;
    private String usepassword;

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
    public String toString(){
        return "{username:\"" + this.username + "\",usepassword:\"" + this.usepassword + "\"}";
    }
}
