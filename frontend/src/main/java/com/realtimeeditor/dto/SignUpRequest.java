package com.realtimeeditor.dto;

public class SignUpRequest {

    private String nickname;
    private String password;

    public SignUpRequest(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
