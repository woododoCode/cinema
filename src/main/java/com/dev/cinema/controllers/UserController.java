package com.dev.cinema.controllers;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.user.UserResponseDto;
import com.dev.cinema.model.mappers.UserDtoMapper;
import com.dev.cinema.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDtoMapper mapper;
    private final UserService userService;

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return mapper.userToDto(user);
    }
}
