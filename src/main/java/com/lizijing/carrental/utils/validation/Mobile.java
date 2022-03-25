package com.lizijing.carrental.utils.validation;

import cn.hutool.core.lang.Validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <p> 自定义手机号校验 </p>
 *
 * @author LiZijing
 * @date 2022/3/25
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {Mobile.Validate.class})
public @interface Mobile {
    String message() default "手机号格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validate implements ConstraintValidator<Mobile, String> {

        @Override
        public void initialize(Mobile constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            return Validator.isMobile(s);
        }
    }
}