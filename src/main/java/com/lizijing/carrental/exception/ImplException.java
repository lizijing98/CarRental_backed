package com.lizijing.carrental.exception;

import com.lizijing.carrental.result.ResultCode;
import lombok.Data;

import java.util.Objects;

/**
 * <p> Impl 异常 </p>
 *
 * @author LiZijing
 * @date 2022/3/6
 */
@Data
public class ImplException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    private Long code;

    public ImplException(ResultCode resultCode) {
        super(resultCode.getMessage());
        setCode(resultCode.getCode());
    }

    public ImplException(ResultCode resultCode, String errorMsg) {
        super(errorMsg + "\n" + resultCode.getMessage());
        setCode(resultCode.getCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImplException that = (ImplException) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
