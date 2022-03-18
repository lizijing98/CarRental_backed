package com.lizijing.carrental.exception;

import com.lizijing.carrental.result.ResultCode;

/**
 * <p> 自定义系统异常 </p>
 *
 * @author LiZijing
 * @date 2022/3/18
 */
public class SysException extends BaseException {

    public static final long serialVersionUID = 1L;

    public SysException(ResultCode resultCode) {
        super(resultCode.getMessage(), resultCode.getCode());
    }

    public SysException(ResultCode resultCode, String msg) {
        super(resultCode.getMessage() + "\n" + msg, resultCode.getCode());
    }
}