package com.realtimeeditor.api;

import com.realtimeeditor.dto.LoginRequest;
import com.realtimeeditor.dto.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("/users/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
