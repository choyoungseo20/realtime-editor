package com.realtimeeditor.api;

import com.realtimeeditor.dto.SignUpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpApi {
    @POST("/users")
    Call<Void> signUp(@Body SignUpRequest signUpRequest);
}
