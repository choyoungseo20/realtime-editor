package com.realtimeeditor.dto;

public class LoginResponse {

    private String userId;

    public LoginResponse(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
