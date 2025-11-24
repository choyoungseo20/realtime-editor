package com.realtimeeditor.validator;

import com.realtimeeditor.dto.UserDto.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserValidatorTest {

    private UserValidator userValidator = new UserValidator();

    @DisplayName("회원가입 시 닉네임이 영문자나 숫자 이외의 문자일 경우 예외가 발생한다.")
    @Test
    void 회원가입_시_닉네임이_영문자나_숫자_이외의_문자일_경우_예외가_발생한다() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .nickname("영서")
                .password("@@112233aa")
                .build();

        assertThatThrownBy(() -> userValidator.validate(signUpRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("회원가입 시 비밀번호에 특수문자가 없을 경우 예외가 발생한다.")
    @Test
    void 회원가입_시_비밀번호에_특수문자가_없을_경우_예외가_발생한다() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .nickname("youngseo")
                .password("112233aa")
                .build();

        assertThatThrownBy(() -> userValidator.validate(signUpRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("회원가입 시 비밀번호에 영문자가 없을 경우 예외가 발생한다.")
    @Test
    void 회원가입_시_비밀번호에_영문자가_없을_경우_예외가_발생한다() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .nickname("youngseo")
                .password("@@112233")
                .build();

        assertThatThrownBy(() -> userValidator.validate(signUpRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("회원가입 시 비밀번호에 숫자가 없을 경우 예외가 발생한다.")
    @Test
    void 회원가입_시_비밀번호에_숫자가_없을_경우_예외가_발생한다() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .nickname("youngseo")
                .password("@@@@aaaa")
                .build();

        assertThatThrownBy(() -> userValidator.validate(signUpRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("회원가입 시 비밀번호가 8자 미만일 경우 예외가 발생한다.")
    @Test
    void 회원가입_시_비밀번호가_8자_미만일_경우_예외가_발생한다() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .nickname("youngseo")
                .password("@@111aa")
                .build();

        assertThatThrownBy(() -> userValidator.validate(signUpRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
