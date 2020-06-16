package com.dev.cinema.model.dto.user;

import com.dev.cinema.anotation.EmailConstraint;
import com.dev.cinema.anotation.PasswordMatch;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatch(passwordValue = "password",
        confirmPasswordValue = "passwordConfirm")
public class UserRequestDto {
    @EmailConstraint
    private String email;
    @NotEmpty
    @Size(min = 1, max = 128)
    private String password;
    private String passwordConfirm;
}
