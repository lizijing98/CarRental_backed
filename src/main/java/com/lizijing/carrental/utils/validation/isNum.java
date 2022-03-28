package com.lizijing.carrental.utils.validation;

import cn.hutool.core.util.ReUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.regex.Pattern;

/**
 * <p> 自定义编号校验 </p>
 *
 * @author LiZijing
 * @date 2022/3/28
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {isNum.Validate.class})
public @interface isNum {

    Pattern IS_NUM = Pattern.compile("^\\[ACC|ORD|REP]{1,3}[0-9]+\\$");

    String message() default "编号格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validate implements ConstraintValidator<isNum, String> {
        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            return ReUtil.isMatch(IS_NUM, s);
        }
    }
}
