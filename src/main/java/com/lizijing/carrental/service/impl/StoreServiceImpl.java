package com.lizijing.carrental.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.Store;
import com.lizijing.carrental.mapper.StoreMapper;
import com.lizijing.carrental.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 门店表 服务实现类
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-01
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Resource
    private StoreMapper storeMapper;

    @Override
    public boolean addCarStock(String storeName) {
        Store store = storeMapper.selectOneByStoreName(storeName);
        // 若增加车辆库存超过现有库存则返回 false
        if (store.getStockNow() + 1 <= store.getStockLimit()) {
            store.setStockNow(store.getStockNow() + 1);
            store.setStockLast(store.getStockLimit() - store.getStockNow());
            this.updateById(store);
            return true;
        }
        return false;
    }

    @Override
    public boolean reduceCarStock(String storeName) {
        Store store = storeMapper.selectOneByStoreName(storeName);
        // 当前库存为 0 则返回 false
        if (store.getStockNow() == 0) {
            return false;
        }
        store.setStockNow(store.getStockNow() - 1);
        store.setStockLast(store.getStockLimit() - store.getStockNow());
        this.updateById(store);
        return true;
    }
}
