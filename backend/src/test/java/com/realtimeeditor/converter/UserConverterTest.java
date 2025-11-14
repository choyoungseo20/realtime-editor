package com.realtimeeditor.converter;

import com.realtimeeditor.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserConverterTest {

    @DisplayName("유저 객체로 변환이 되었는지 확인한다.")
    @Test
    void 유저_객체로_변환이_되었는지_확인한다() {
        String nickname = "youngseo";
        String encodedPassword = "encodedPassword";

        User user = UserConverter.toUser(nickname, encodedPassword);

        assertThat(user.getNickname()).isEqualTo(nickname);
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
    }
}
