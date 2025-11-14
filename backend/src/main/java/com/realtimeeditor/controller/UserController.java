package com.realtimeeditor.controller;

import com.realtimeeditor.converter.UserConverter;
import com.realtimeeditor.dto.UserDto.NicknameCheckResponse;
import com.realtimeeditor.dto.UserDto.SignUpRequest;
import com.realtimeeditor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request) {
        userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/nickname-exists")
    public ResponseEntity<NicknameCheckResponse> nicknameExists(@RequestParam("nickname") String nickname) {
        boolean isNicknameExists = userService.isNicknameExists(nickname);
        return ResponseEntity.ok(UserConverter.toNicknameCheckResponse(isNicknameExists));
    }
}
