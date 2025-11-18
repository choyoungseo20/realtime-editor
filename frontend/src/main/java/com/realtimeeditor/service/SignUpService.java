package com.realtimeeditor.service;

import com.realtimeeditor.api.SignUpApiService;
import com.realtimeeditor.converter.SignUpConverter;
import com.realtimeeditor.dto.SignUpRequest;
import com.realtimeeditor.model.SignUpInfo;
import java.io.IOException;

public class SignUpService {

    private final SignUpApiService signUpApiService = new SignUpApiService();

    public boolean signUp(SignUpInfo signUpInfo) {
        SignUpRequest request = SignUpConverter.toSignUpRequest(signUpInfo);
        try {
            return signUpApiService.callSignUpApi(request);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
