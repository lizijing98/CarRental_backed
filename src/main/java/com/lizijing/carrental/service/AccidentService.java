package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.Accident;
import com.lizijing.carrental.entity.vo.AccidentAddVO;
import com.lizijing.carrental.entity.vo.AccidentUpdateVO;
import com.lizijing.carrental.result.CommonResult;

import java.util.Map;

/**
 * <p>
 * 事故单表 服务类
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-14
 */
public interface AccidentService extends IService<Accident> {

    /**
     * 增加事故单接口
     *
     * @param accidentAddVO 增加事故单接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> addOne(AccidentAddVO accidentAddVO);

    /**
     * 删除事故单接口
     *
     * @param accidentNum 事故单编号
     * @param operatorId  操作者 ID
     * @return 结果
     */
    CommonResult<Map<Object, Object>> delOne(String accidentNum, Integer operatorId);

    /**
     * 获取所有维修单接口
     *
     * @param pageSize  分页数
     * @param pageIndex 页码
     * @return 结果
     */
    CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex);

    /**
     * 筛选事故单信息接口
     *
     * @param id          事故单 ID
     * @param accidentNum 事故单编号
     * @param userId      用户 ID
     * @param carId       车辆 ID
     * @param operatorId  操作员 ID
     * @param status      事故单状态
     * @param hasRepair   是否有对应维修单
     * @return 结果
     */
    CommonResult<Map<Object, Object>> select(Integer id, String accidentNum, Integer userId, Integer carId, Integer operatorId, String status, Boolean hasRepair);

    /**
     * 修改事故单信息接口
     *
     * @param accidentUpdateVO 修改事故单信息接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> updateOne(AccidentUpdateVO accidentUpdateVO);

    /**
     * 修改事故单状态
     *
     * @param accidentNum 事故单编号
     * @param status      事故单状态
     * @param operatorId  操作员 ID
     * @return 结果
     */
    CommonResult<Map<Object, Object>> modifyStatus(String accidentNum, Integer status, Integer operatorId);
}
