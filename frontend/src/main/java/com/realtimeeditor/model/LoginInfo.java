package com.realtimeeditor.model;

public class LoginInfo {

    private String nickname;
    private String password;

    public LoginInfo(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
