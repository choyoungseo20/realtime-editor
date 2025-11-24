package com.realtimeeditor.service;

import com.realtimeeditor.api.LoginApiService;
import com.realtimeeditor.converter.LoginConverter;
import com.realtimeeditor.dto.LoginRequest;
import com.realtimeeditor.dto.LoginResponse;
import com.realtimeeditor.model.LoginInfo;
import com.realtimeeditor.model.User;
import java.io.IOException;

public class LoginService {

    private final LoginApiService loginApiService = new LoginApiService();

    public boolean login(LoginInfo loginInfo) {
        LoginRequest request = LoginConverter.toLoginRequest(loginInfo);
        try {
            LoginResponse dto = loginApiService.callLoginApi(request);
            if (dto != null) {
                User user = LoginConverter.toUser(dto);
                SessionManager.getInstance().setCurrentUser(user);
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
