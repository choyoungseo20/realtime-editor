package com.realtimeeditor.dto;

import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    @Builder
    public static class SignUpRequest {
        private String nickname;
        private String password;
    }

    @Getter
    @Builder
    public static class NicknameCheckResponse {
        private boolean exists;
    }

    @Getter
    @Builder
    public static class LoginRequest {
        private String nickname;
        private String password;
    }

    @Getter
    @Builder
    public static class LoginResponse {
        private String userId;
    }
}
