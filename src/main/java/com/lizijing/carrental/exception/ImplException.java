package com.lizijing.carrental.exception;

import com.lizijing.carrental.result.ResultCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * <p> Impl 异常 </p>
 *
 * @author LiZijing
 * @date 2022/3/6
 */
@Getter
@Setter
public class ImplException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    private Long code;

    private ResultCode resultCode;

    public ImplException(ResultCode resultCode) {
        super(resultCode.getMessage());
        setCode(resultCode.getCode());
        setResultCode(resultCode);
    }

    public ImplException(ResultCode resultCode, String errorMsg) {
        super(errorMsg + "\n" + resultCode.getMessage());
        setCode(resultCode.getCode());
        setResultCode(resultCode);
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
