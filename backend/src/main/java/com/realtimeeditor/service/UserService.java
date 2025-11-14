package com.realtimeeditor.service;

import com.realtimeeditor.converter.UserConverter;
import com.realtimeeditor.domain.User;
import com.realtimeeditor.dto.UserDto.SignUpRequest;
import com.realtimeeditor.repository.UserRepository;
import com.realtimeeditor.validator.UserValidationMessage;
import com.realtimeeditor.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest signUpRequest) {
        userValidator.validate(signUpRequest);
        checkNicknameDuplicate(signUpRequest.getNickname());
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        User user = UserConverter.toUser(signUpRequest.getNickname(), encodedPassword);
        userRepository.save(user);
    }

    public boolean isNicknameExists(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    private void checkNicknameDuplicate(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException(UserValidationMessage.NICKNAME_DUPLICATE);
        }
    }
}
