package com.realtimeeditor.dto;

import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    @Builder
    public static class SignUpDto {
        private String nickname;
        private String password;
    }
}
