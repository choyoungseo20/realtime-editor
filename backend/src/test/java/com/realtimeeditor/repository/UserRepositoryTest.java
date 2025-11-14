package com.realtimeeditor.repository;

import com.realtimeeditor.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll();
    }

    @DisplayName("유저를 DB에 저장 후 닉네임이 존재하는지 확인한다.")
    @Test
    void 유저를_DB에_저장_후_닉네임이_존재하는지_확인한다() {
        User user = User.builder()
                .nickname("youngseo")
                .password("encodedPassword")
                .build();
        userRepository.save(user);

        boolean exists = userRepository.existsByNickname("youngseo");

        assertThat(exists).isTrue();
    }

    @DisplayName("유저를 DB에 저장하지 않고 닉네임이 존재하는지 확인한다.")
    @Test
    void 유저를_DB에_저장하지_않고_닉네임이_존재하는지_확인한다() {
        boolean exists = userRepository.existsByNickname("youngseo");

        assertThat(exists).isFalse();
    }
}
