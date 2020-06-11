package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.user.UserRequestDto;
import com.dev.cinema.security.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/registration")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping
    public void registration(@RequestBody UserRequestDto requestDto) {
        service.register(requestDto.getEmail(), requestDto.getPassword());
    }
}
