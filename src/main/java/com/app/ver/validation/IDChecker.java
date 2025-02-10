package com.app.ver.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IDCheckerImpl.class)
public @interface IDChecker {
    String message() default "ID is not valid. Must contains only NUMERIC parameter";
}