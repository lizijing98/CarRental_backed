package com.lizijing.carrental.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.Store;
import com.lizijing.carrental.entity.vo.StoreAddVO;
import com.lizijing.carrental.mapper.StoreMapper;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
        if (store.getStockNow() != 0) {
            store.setStockNow(store.getStockNow() - 1);
            store.setStockLast(store.getStockLimit() - store.getStockNow());
            this.updateById(store);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCar(String oldStore, String newStore) {
        // 减少原门店库存成功且新增新门店库存成功才成功，否则失败
        // todo:需要考虑回滚
        return reduceCarStock(oldStore) && addCarStock(newStore);
    }

    @Override
    public CommonResult<Map<Object, Object>> addOne(StoreAddVO storeAddVO) {
        Map<Object, Object> res = new HashMap<>(8);
        Store addStore = BeanUtil.copyProperties(storeAddVO, Store.class);
        this.save(addStore);
        res.put("storeInfo", addStore);
        return CommonResult.success("add store success", res);
    }
}
