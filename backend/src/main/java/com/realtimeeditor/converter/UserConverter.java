package com.realtimeeditor.converter;

import com.realtimeeditor.domain.User;
import com.realtimeeditor.dto.UserDto.NicknameCheckResponse;

public class UserConverter {

    public static User toUser(String nickname, String encodedPassword) {
        return User.builder()
                .nickname(nickname)
                .password(encodedPassword)
                .build();
    }

    public static NicknameCheckResponse toNicknameCheckResponse(boolean exists) {
        return NicknameCheckResponse.builder()
                .exists(exists)
                .build();
    }
}
