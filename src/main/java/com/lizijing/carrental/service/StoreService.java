package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.Store;
import com.lizijing.carrental.entity.vo.StoreAddVO;
import com.lizijing.carrental.entity.vo.StoreUpdateVO;
import com.lizijing.carrental.result.CommonResult;

import java.util.Map;

/**
 * <p>
 * 门店表 服务类
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-01
 */
public interface StoreService extends IService<Store> {

    /**
     * 增加车辆库存
     *
     * @param storeName 门店名称
     * @return 增加成功或失败
     */
    boolean addCarStock(String storeName);

    /**
     * 减少车辆库存
     *
     * @param storeName 门店名称
     * @return 减少成功或失败
     */
    boolean reduceCarStock(String storeName);


    /**
     * 修改车辆所在门店
     *
     * @param oldStore 原门店店名
     * @param newStore 新门店店名
     * @return 修改成功或失败
     */
    boolean removeCar(String oldStore, String newStore);

    /**
     * 新增门店接口
     *
     * @param storeAddVO 新增门店接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> addOne(StoreAddVO storeAddVO);

    /**
     * 删除门店接口
     *
     * @param storeId    门店 ID
     * @param operatorId 操作者 ID
     * @return 结果
     */
    CommonResult<Map<Object, Object>> delOne(Integer storeId, Integer operatorId);

    /**
     * 获取所有门店接口
     *
     * @param pageSize  分页数
     * @param pageIndex 页码
     * @return 结果
     */
    CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex);

    /**
     * 筛选门店信息接口
     *
     * @param id        门店 ID
     * @param storeName 门店名称
     * @param address   门店地址
     * @param stockLast 当前可用存放量
     * @param managerId 店长 ID
     * @param isDisable 是否禁用
     * @return 结果
     */
    CommonResult<Map<Object, Object>> select(Integer id, String storeName, String address, Integer stockLast, Integer managerId, Boolean isDisable);

    /**
     * 修改门店信息接口
     *
     * @param storeUpdateVO 修改门店信息接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> updateOne(StoreUpdateVO storeUpdateVO);

    /**
     * 通过门店 ID 获取门店名
     *
     * @param storeId 门店 ID
     * @return 门店名
     */
    String getNameById(Long storeId);
}
