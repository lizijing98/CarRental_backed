package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.Store;
import com.lizijing.carrental.entity.vo.StoreAddVO;
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
}
