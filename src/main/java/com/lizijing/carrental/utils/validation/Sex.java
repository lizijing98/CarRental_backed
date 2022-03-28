package com.lizijing.carrental.utils.validation;

import cn.hutool.core.util.ArrayUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <p> 自定义性别校验 </p>
 *
 * @author LiZijing
 * @date 2022/3/28
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {Sex.Validate.class})
public @interface Sex {
    String[] SEX = {"男", "女", "保密", ""};

    String message() default "不合法的性别输入";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validate implements ConstraintValidator<Sex, String> {
        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            return ArrayUtil.contains(SEX, s);
        }
    }
}
