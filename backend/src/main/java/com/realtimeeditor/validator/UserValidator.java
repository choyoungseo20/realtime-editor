package com.realtimeeditor.validator;

import com.realtimeeditor.dto.UserDto.SignUpRequest;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private static final String NICKNAME_REGEX = "^[a-zA-Z0-9]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,}$";

    public void validate(SignUpRequest signUpRequest) {
        validateNickname(signUpRequest.getNickname());
        validatePassword(signUpRequest.getPassword());
    }

    private void validateNickname(String nickname) {
        if (!nickname.matches(NICKNAME_REGEX)) {
            throw new IllegalArgumentException(UserValidationMessage.NICKNAME_FORMAT_ERROR);
        }
    }

    private void validatePassword(String password) {
        if (!password.matches(PASSWORD_REGEX)) {
            throw new IllegalArgumentException(UserValidationMessage.PASSWORD_FORMAT_ERROR);
        }
    }
}
