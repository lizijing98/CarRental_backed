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
    PROCESSED(3, "PROCESSED");

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
