package com.realtimeeditor.api;

import com.realtimeeditor.dto.LoginRequest;
import com.realtimeeditor.dto.LoginResponse;
import java.io.IOException;
import retrofit2.Response;

public class LoginApiService {

    public LoginResponse callLoginApi(LoginRequest request) throws IOException {
        Response<LoginResponse> response = ApiClient.loginApi().login(request).execute();

        if (response.isSuccessful() && response.body() != null) {
            return response.body();
        } else {
            return null;
        }
    }
}
