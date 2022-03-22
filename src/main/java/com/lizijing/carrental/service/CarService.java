package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.Car;
import com.lizijing.carrental.entity.vo.CarAddVO;
import com.lizijing.carrental.entity.vo.CarUpdateVO;
import com.lizijing.carrental.result.CommonResult;

import java.time.LocalDateTime;
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

    /**
     * 筛选车辆信息接口
     *
     * @param id         车辆 ID
     * @param carNumber  车牌号
     * @param model      型号
     * @param storeName  所在位置
     * @param type       车型
     * @param createTime 入库时间
     * @param isUsable   是否可用
     * @return 结果
     */
    CommonResult<Map<Object, Object>> select(Integer id, String carNumber, String model, String storeName, String type, LocalDateTime createTime, Boolean isUsable);

    /**
     * 修改车辆状态
     *
     * @param carId  车辆 ID
     * @param status 车辆状态
     * @return 修改结果
     */
    boolean changeStatus(Long carId, String status);

    /**
     * 修改车辆所在门店
     *
     * @param carId   车辆 ID
     * @param storeId 门店 ID
     * @return 结果
     */
    boolean changeStore(Long carId, Long storeId);
}
