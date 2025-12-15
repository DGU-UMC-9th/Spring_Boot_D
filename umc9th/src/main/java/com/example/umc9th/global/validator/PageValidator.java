package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "page는 1 이상의 정수여야 합니다."
            ).addConstraintViolation();
            return false;
        }

        boolean isValid = value >= 1;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "page는 1 이상의 정수여야 합니다."
            ).addConstraintViolation();
        }

        return isValid;
    }
}