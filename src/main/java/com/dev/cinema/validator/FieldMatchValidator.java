package com.dev.cinema.validator;


import com.dev.cinema.anotation.FieldsValueMatch;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldsValueMatch, Object>
{
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Object firstValue = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
            final Object seccondValue = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);
            return firstValue == null && seccondValue == null || firstValue != null && firstValue.equals(seccondValue);
        }
        catch (final Exception ignore)
        {
        }
        return true;
    }
}