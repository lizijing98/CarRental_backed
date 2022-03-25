package com.lizijing.carrental.result;

/**
 * <p> 接口结果状态枚举 </p>
 *
 * @author LiZijing
 * @date 2022/2/27
 */
public enum ResultCode {
    // 系统通用异常
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    SYS_ERROR(5000001, "未知错误，请联系管理员"),
    SYS_PARAMS_ERROR(5000002, "参数错误"),
    // 数据库异常
    DB_ERROR(5001001, "数据库异常"),
    DB_Duplicate_ERROR(5001002, "存在已入库数据"),
    // 门店异常
    STORE_ERROR(5002001, "库存异常"),
    STORE_MORE_ERROR(5002002, "库存已满"),
    STORE_ZERO_ERROR(5002003, "库存已空"),
    STORE_EXIST_ERROR(5002004, "不存在的门店信息"),
    // 车辆异常
    CAR_ERROR(5003001, "车辆操作异常"),
    CAR_STATUS_ERROR(5003002, "车辆状态异常"),
    CAR_EXIST_ERROR(5003003, "不存在的车辆信息"),
    CAR_WAREHOUSING_ERROR(5003004, "车辆入库异常"),
    // 人员异常
    USER_ERROR(5004001, "用户操作异常"),
    USER_STATUS_ERROR(5004002, "用户状态异常"),
    USER_EXIST_ERROR(5004003, "不存在的用户信息"),
    // 订单异常
    ORDER_ERROR(5005001, "订单操作异常"),
    ORDER_STATUS_ERROR(5005002, "订单状态异常"),
    ORDER_EXIST_ERROR(5005003, "不存在的订单信息"),
    ORDER_LOCATE_ERROR(5005004, "缺少必要的订单信息"),
    ORDER_NOT_IN_STORE(5005005, "不在此门店的车辆"),
    ORDER_IS_FINISHED(5005006, "已完成的订单"),
    // 维修单异常
    REPAIR_ERROR(5006001, "维修单操作异常"),
    REPAIR_STATUS_ERROR(5006002, "维修单状态异常"),
    REPAIR_EXIST_ERROR(5006003, "不存在的维修单信息"),
    REPAIR_LOCATE_ERROR(5006004, "缺少必要的维修单信息"),
    REPAIR_NOT_PROCESSED(5006005, "维修单未处理完成"),
    // 事故单异常
    ACCIDENT_ERROR(5007001, "事故单操作异常"),
    ACCIDENT_STATUS_ERROR(5007002, "事故单状态异常"),
    ACCIDENT_EXIST_ERROR(5007003, "不存在的事故单信息"),
    ACCIDENT_LOCATE_ERROR(5007004, "缺少必要的订单信息"),
    ACCIDENT_ADD_REPAIR_ERROR(5007005, "新增对应维修单操作异常");


    public final long code;
    public final String message;

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
