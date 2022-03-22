package com.lizijing.carrental.entity.enums;

import com.lizijing.carrental.exception.SysException;
import com.lizijing.carrental.result.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p> 状态枚举 </p>
 *
 * @author LiZijing
 * @date 2022/3/17
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    /*状态枚举*/
    // 未处理
    UNPROCESSED(1, "UNPROCESSED"),
    // 处理中
    PROCESSING(2, "PROCESSING"),
    // 已处理
    PROCESSED(3, "PROCESSED"),
    // 订单进行中
    ORD_IN_PROGRESS(1, "IN_PROGRESS"),
    // 订单完成
    ORD_COMPLETED(2, "COMPLETED"),
    // 订单转事故
    ORD_ACCIDENT(3, "ACCIDENT"),
    // 车辆正常
    CAR_NORMAL(1, "NORMAL"),
    // 车辆使用中
    CAR_USING(2, "USING"),
    // 车辆故障
    CAR_FAULT(3, "FAULT"),
    // 车辆不可用
    CAR_UNAVAILABLE(4, "UNAVAILABLE");

    public final Integer code;
    public final String status;

    public static String getStatus(Integer code) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getStatus();
            }
        }
        throw new SysException(ResultCode.SYS_PARAMS_ERROR, "无法匹配状态");
    }
}
