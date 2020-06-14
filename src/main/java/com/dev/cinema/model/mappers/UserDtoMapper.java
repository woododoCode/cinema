package com.dev.cinema.model.mappers;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.user.UserRequestDto;
import com.dev.cinema.model.dto.user.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public User dtoToUser(UserRequestDto requestDto) {
        var user = new User();
        user.setPassword(requestDto.getPassword());
        user.setEmail(requestDto.getEmail());
        return user;
    }

    public UserResponseDto userToDto(User user) {
        var userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUserId(user.getId());
        return userResponseDto;
    }

}
