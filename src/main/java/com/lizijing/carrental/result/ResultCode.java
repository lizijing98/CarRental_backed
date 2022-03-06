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
    SYS_ERROR(5000001, "未知错误，请联系管理员"),
    // 数据库异常枚举
    DB_ERROR(5001001, "数据库异常"),
    DB_Duplicate_ERROR(5001002, "存在已入库数据"),
    // 门店库存异常
    STORE_ERROR(5002001, "库存异常"),
    STORE_MORE_ERROR(5002002, "库存已满"),
    STORE_ZERO_ERROR(5002003, "库存已空"),
    // 车辆异常
    CAR_ERROR(5003001, "车辆操作异常"),
    CAR_STATUS_ERROR(5003002, "车辆状态异常");


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
