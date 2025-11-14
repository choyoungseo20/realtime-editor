package com.realtimeeditor.service;

import com.realtimeeditor.converter.UserConverter;
import com.realtimeeditor.domain.User;
import com.realtimeeditor.dto.UserDto;
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

    public void signUp(UserDto.SignUpDto signUpDto) {
        userValidator.validate(signUpDto);
        checkNicknameDuplicate(signUpDto.getNickname());
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        User user = UserConverter.toUser(signUpDto.getNickname(), encodedPassword);
        userRepository.save(user);
    }

    private void checkNicknameDuplicate(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException(UserValidationMessage.NICKNAME_DUPLICATE);
        }
    }
}
