package com.realtimeeditor.model;

public class SignUpInfo {

    private static final String NICKNAME_REGEX = "^[a-zA-Z0-9]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,}$";

    private String nickname;
    private String password;
    private String passwordConfirm;

    public SignUpInfo(String nickname, String password, String passwordConfirm) {
        validate(nickname, password, passwordConfirm);
        this.nickname = nickname;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    private void validate(String nickname, String password, String passwordConfirm) {
        if (!nickname.matches(NICKNAME_REGEX)) {
            throw new IllegalArgumentException("닉네임은 영문자 + 숫자로 이루어진 1자리 이상의 문자열입니다.");
        }

        if (!password.matches(PASSWORD_REGEX)) {
            throw new IllegalArgumentException("비밀번호는 특수문자 + 영문자 + 숫자로 이루어진 8자리 이상의 문자열입니다.");
        }

        if (!passwordConfirm.equals(password)) {
            throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        }
    }
}
