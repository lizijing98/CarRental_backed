package com.lizijing.carrental.result;

import lombok.Data;

/**
 * <p> 结果类 </p>
 *
 * @author LiZijing
 * @date 2022/2/27
 */
@Data
public class CommonResult<T> {
    /**
     * 状态码
     */
    private long code;

    /**
     * 结果信息
     */
    private String message;

    /**
     * 结果数据，不一定都有
     */
    private T data;

    protected CommonResult() {
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    protected CommonResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    protected CommonResult(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    protected CommonResult(ResultCode resultCode, String message, T data) {
        this.code = resultCode.getCode();
        this.message = message;
        this.data = data;
    }

    /**
     * 操作成功返回结果
     *
     * @param msg  结果信息
     * @param data 结果数据
     */
    public static <T> CommonResult<T> success(String msg, T data) {
        return new CommonResult<>(ResultCode.SUCCESS, msg, data);
    }

    /**
     * 操作失败返回结果
     *
     * @param result 失败情况枚举
     * @param msg    失败自定信息
     * @param data   结果数据
     */
    public static <T> CommonResult<T> failed(ResultCode result, String msg, T data) {
        return new CommonResult<>(result, msg, data);
    }

    /**
     * 操作失败返回结果
     *
     * @param result 失败情况枚举
     * @param data   结果数据
     */
    public static <T> CommonResult<T> failed(ResultCode result, T data) {
        return new CommonResult<>(result, data);
    }

    /**
     * 操作失败返回结果
     *
     * @param result 失败情况枚举
     */
    public static <T> CommonResult<T> failed(ResultCode result) {
        return new CommonResult<>(result);
    }
}
