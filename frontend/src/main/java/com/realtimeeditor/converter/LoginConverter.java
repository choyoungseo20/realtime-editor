package com.realtimeeditor.converter;

import com.realtimeeditor.dto.LoginRequest;
import com.realtimeeditor.dto.LoginResponse;
import com.realtimeeditor.model.LoginInfo;
import com.realtimeeditor.model.User;

public class LoginConverter {

    public static LoginRequest toLoginRequest(LoginInfo loginInfo) {
        return new LoginRequest(loginInfo.getNickname(), loginInfo.getPassword());
    }

    public static User toUser(LoginResponse loginResponse) {
        return new User(loginResponse.getUserId());
    }
}
