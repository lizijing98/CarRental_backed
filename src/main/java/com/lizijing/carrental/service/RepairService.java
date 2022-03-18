package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.Repair;
import com.lizijing.carrental.entity.vo.RepairAddVO;
import com.lizijing.carrental.entity.vo.RepairUpdateVO;
import com.lizijing.carrental.result.CommonResult;

import java.util.Map;

/**
 * <p>
 * 维修单表 服务类
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-17
 */
public interface RepairService extends IService<Repair> {

    /**
     * 增加维修单接口
     *
     * @param repairAddVO 增加维修单接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> addOne(RepairAddVO repairAddVO);

    /**
     * 删除维修单接口
     *
     * @param repairNum  维修单编号
     * @param operatorId 操作者 ID
     * @return 结果
     */
    CommonResult<Map<Object, Object>> delOne(String repairNum, Integer operatorId);

    /**
     * 获取所有维修单接口
     *
     * @param pageSize  分页数
     * @param pageIndex 页码
     * @return 结果
     */
    CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex);

    /**
     * 筛选维修单信息接口
     *
     * @param id         维修单 ID
     * @param repairNum  维修单编号
     * @param userId     用户 ID
     * @param carId      车辆 ID
     * @param operatorId 操作员 ID
     * @param status     订单状态
     * @return 结果
     */
    CommonResult<Map<Object, Object>> select(Integer id, String repairNum, Integer userId, Integer carId, Integer operatorId, String status);

    /**
     * 修改维修单信息接口
     *
     * @param repairUpdateVO 修改维修单信息接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> updateOne(RepairUpdateVO repairUpdateVO);

    /**
     * 修改维修单状态
     *
     * @param repairNum  维修单编号
     * @param status     维修单状态
     * @param operatorId 操作员 ID
     * @return 结果
     */
    CommonResult<Map<Object, Object>> modifyStatus(String repairNum, Integer status, Integer operatorId);
}
