package com.realtimeeditor.dto;

public class LoginRequest {

    private String nickname;
    private String password;

    public LoginRequest(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
