package com.lizijing.carrental.exception;

import lombok.*;

/**
 * <p> 自定义异常基类 </p>
 *
 * @author LiZijing
 * @date 2022/3/18
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    private Long code;

    public BaseException(String message, Long code) {
        super(message);
    }
}
