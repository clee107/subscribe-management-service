package com.subscribe.management.config.annotation;

import com.subscribe.management.config.validation.PhoneNumberValidator;
import com.subscribe.management.util.StringUtils;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PhoneNumberValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "연락처 양식에 맞지 않습니다.";
    String regexp() default StringUtils.REGEX_PHONE_NUMBER;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
