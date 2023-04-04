package com.poly.datn.common;

import org.springframework.messaging.handler.annotation.Payload;

import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { ValidEmailValidator.class })
@Target({ FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidEmail {
    String message() default "Email không hợp lệ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
