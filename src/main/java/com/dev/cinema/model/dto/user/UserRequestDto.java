package com.dev.cinema.model.dto.user;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private String email;
    @Size
    private String password;

    private String passwordConfirm;
}
