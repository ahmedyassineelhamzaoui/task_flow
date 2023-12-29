package com.app.taskflow.common.validation.interfaces;

import com.app.taskflow.common.validation.EmailValidator;
import com.app.taskflow.common.validation.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {


        String message() default "password must contain at least 8 characters, 1 uppercase, 1 lowercase, 1 number and 1 special character";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}
