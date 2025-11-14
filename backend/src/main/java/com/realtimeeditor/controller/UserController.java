package com.realtimeeditor.controller;

import com.realtimeeditor.dto.UserDto;
import com.realtimeeditor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void signUp(@RequestBody UserDto.SignUpDto request) {
        userService.signUp(request);
    }
}
