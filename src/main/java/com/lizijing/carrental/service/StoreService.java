package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.Store;

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

}
