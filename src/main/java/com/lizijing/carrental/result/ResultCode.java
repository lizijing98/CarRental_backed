package com.lizijing.carrental.result;

/**
 * <p> 接口结果状态枚举 </p>
 *
 * @author LiZijing
 * @date 2022/2/27
 */
public enum ResultCode {
    // 通用枚举
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    SYS_ERROR(5000001, "未知错误，请联系嘎管理员");

    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
