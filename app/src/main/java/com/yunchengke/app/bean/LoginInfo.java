package com.yunchengke.app.bean;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/21 20:46
 * 描述：
 * 版本：1.0
 */
public class LoginInfo {
    String id;
    String username;
    private static LoginInfo loginInfo;

    public static synchronized LoginInfo getInstance(){
        if (loginInfo == null){
            loginInfo = new LoginInfo();
        }
        return loginInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
