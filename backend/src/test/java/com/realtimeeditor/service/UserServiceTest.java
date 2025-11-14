package com.realtimeeditor.service;

import com.realtimeeditor.dto.UserDto.SignUpDto;
import com.realtimeeditor.repository.UserRepository;
import com.realtimeeditor.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataMongoTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    private UserValidator userValidator = new UserValidator();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userService = new UserService(userRepository, userValidator, passwordEncoder);
    }

    @DisplayName("회원가입 기능을 확인한다.")
    @Test
    void 회원가입_기능을_확인한다() {
        SignUpDto signUpDto = SignUpDto.builder()
                .nickname("youngseo")
                .password("@@112233aa")
                .build();
        userService.signUp(signUpDto);

        boolean exists = userRepository.existsByNickname("youngseo");

        assertThat(exists).isTrue();
    }

    @DisplayName("닉네임이 이미 존재할 경우 예외가 발생한다.")
    @Test
    void 닉네임이_이미_존재할_경우_예외가_발생한다() {
        SignUpDto signUpDto1 = SignUpDto.builder()
                .nickname("youngseo")
                .password("@@112233aa")
                .build();
        userService.signUp(signUpDto1);

        SignUpDto signUpDto2 = SignUpDto.builder()
                .nickname("youngseo")
                .password("aa112233@@")
                .build();

        assertThatThrownBy(() -> userService.signUp(signUpDto2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
