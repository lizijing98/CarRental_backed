package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.Car;
import com.lizijing.carrental.entity.vo.CarAddVO;
import com.lizijing.carrental.entity.vo.CarUpdateVO;
import com.lizijing.carrental.result.CommonResult;

import java.util.Map;

/**
 * <p>
 * 车辆表 服务类
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
public interface CarService extends IService<Car> {

    /**
     * 增加车辆接口
     *
     * @param carAddVO 增加车辆接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> addOne(CarAddVO carAddVO);

    /**
     * 删除车辆接口
     *
     * @param carId      车辆 ID
     * @param operatorId 操作者 ID
     * @return 结果
     */
    CommonResult<Map<Object, Object>> delOne(Integer carId, Integer operatorId);

    /**
     * 获取所有车辆接口
     *
     * @param pageSize  分页数
     * @param pageIndex 页码
     * @return 结果
     */
    CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex);

    /**
     * 修改车辆信息接口
     *
     * @param carUpdateVO 修改车辆信息接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> updateOne(CarUpdateVO carUpdateVO);

}
