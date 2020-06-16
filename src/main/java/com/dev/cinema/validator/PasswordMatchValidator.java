package com.dev.cinema.validator;

import com.dev.cinema.anotation.PasswordMatch;
import com.dev.cinema.model.dto.user.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRequestDto> {
    private String password;
    private String passwordConfirm;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        password = constraintAnnotation.passwordValue();
        passwordConfirm = constraintAnnotation.confirmPasswordValue();
    }

    @Override
    public boolean isValid(UserRequestDto requestDto, ConstraintValidatorContext context) {
        final String firstValue =
                (String) new BeanWrapperImpl(requestDto).getPropertyValue(password);
        final String secondValue =
                (String) new BeanWrapperImpl(requestDto).getPropertyValue(passwordConfirm);
        return firstValue == secondValue || firstValue != null && firstValue.equals(secondValue);
    }
}
