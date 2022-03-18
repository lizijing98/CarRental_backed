package com.lizijing.carrental.exception;

import com.lizijing.carrental.result.ResultCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p> Impl 异常 </p>
 *
 * @author LiZijing
 * @date 2022/3/6
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ImplException extends BaseException {

    public static final long serialVersionUID = 1L;

    private ResultCode resultCode;

    public ImplException(ResultCode resultCode) {
        super(resultCode.getMessage(), resultCode.getCode());
        setResultCode(resultCode);
    }

    public ImplException(ResultCode resultCode, String errorMsg) {
        super(errorMsg + "\n" + resultCode.getMessage(), resultCode.getCode());
        setResultCode(resultCode);
    }

}
