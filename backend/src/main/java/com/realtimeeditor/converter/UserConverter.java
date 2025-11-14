package com.realtimeeditor.converter;

import com.realtimeeditor.domain.User;

public class UserConverter {

    public static User toUser(String nickname, String encodedPassword) {
        return User.builder()
                .nickname(nickname)
                .password(encodedPassword)
                .build();
    }
}
