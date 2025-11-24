package com.realtimeeditor.converter;

import com.realtimeeditor.dto.SignUpRequest;
import com.realtimeeditor.model.SignUpInfo;

public class SignUpConverter {

    public static SignUpRequest toSignUpRequest(SignUpInfo signUpInfo) {
        return new SignUpRequest(signUpInfo.getNickname(), signUpInfo.getPassword());
    }
}
