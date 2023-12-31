package com.app.taskflow.common.validation.interfaces;

import com.app.taskflow.common.validation.TimestampFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimestampFormatValidator.class)
public @interface TimesTamp {
    String message() default "Invalid timestamp format. Please use 'yyyy-MM-dd HH:mm:ss' format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
