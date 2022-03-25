package com.lizijing.carrental.utils.validation;

import cn.hutool.core.util.ArrayUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <p> 自定义角色名称校验 </p>
 *
 * @author LiZijing
 * @date 2022/3/25
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RoleName.Validate.class})
public @interface RoleName {
    String[] ROLE_NAMES = {"SUPERADMIN", "ADMIN", "SALESMAN", "TROUBLESHOOTER", "USER"};

    String message() default "角色名称不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validate implements ConstraintValidator<RoleName, String> {
        @Override
        public void initialize(RoleName constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            return ArrayUtil.contains(ROLE_NAMES, s);
        }
    }
}
