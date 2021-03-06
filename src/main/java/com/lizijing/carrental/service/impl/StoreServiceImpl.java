package com.lizijing.carrental.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.Store;
import com.lizijing.carrental.entity.vo.StoreAddVO;
import com.lizijing.carrental.entity.vo.StoreUpdateVO;
import com.lizijing.carrental.exception.ImplException;
import com.lizijing.carrental.mapper.StoreMapper;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.result.ResultCode;
import com.lizijing.carrental.service.StoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    @Transactional(rollbackFor = ImplException.class)
    public boolean addCarStock(String storeName) {
        Store store = storeMapper.selectOneByStoreName(storeName);
        if (store == null) {
            throw new ImplException(ResultCode.STORE_EXIST_ERROR);
        }
        // 若增加车辆库存超过现有库存则返回 false
        if (store.getStockNow() + 1 <= store.getStockLimit()) {
            store.setStockNow(store.getStockNow() + 1);
            store.setStockLast(store.getStockLimit() - store.getStockNow());
            this.updateById(store);
            return true;
        }
        throw new ImplException(ResultCode.STORE_MORE_ERROR, storeName + ResultCode.STORE_MORE_ERROR.message);
    }

    @Override
    @Transactional(rollbackFor = ImplException.class)
    public boolean reduceCarStock(String storeName) {
        Store store = storeMapper.selectOneByStoreName(storeName);
        if (store == null) {
            throw new ImplException(ResultCode.STORE_EXIST_ERROR);
        }
        // 当前库存为 0 则返回 false
        if (store.getStockNow() != 0) {
            store.setStockNow(store.getStockNow() - 1);
            store.setStockLast(store.getStockLimit() - store.getStockNow());
            this.updateById(store);
            return true;
        }
        throw new ImplException(ResultCode.STORE_ZERO_ERROR, storeName + ResultCode.STORE_ZERO_ERROR.message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeCar(String oldStore, String newStore) {
        // 减少原门店库存成功且新增新门店库存成功才成功，否则失败
        try {
            Store newOne = storeMapper.selectOneByStoreName(newStore);
            if (newOne == null) {
                throw new ImplException(ResultCode.STORE_EXIST_ERROR);
            }
            if (newOne.getStockLast() == 0) {
                throw new ImplException(ResultCode.STORE_MORE_ERROR);
            }
            this.addCarStock(newStore);
            this.reduceCarStock(oldStore);
            return true;
        } catch (Exception e) {
            throw new ImplException(e);
        }
    }

    @Override
    public CommonResult<Map<Object, Object>> addOne(StoreAddVO storeAddVO) {
        Map<Object, Object> res = new HashMap<>(8);
        Store addStore = BeanUtil.copyProperties(storeAddVO, Store.class);
        this.save(addStore);
        res.put("storeInfo", storeMapper.selectById(addStore.getId()));
        return CommonResult.success("add store success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> delOne(Integer storeId, Integer operatorId) {
        Map<Object, Object> res = new HashMap<>(8);
        // todo:验证操作者
        //
        // 验证门店状态
        Store delStore = this.getById(storeId);
        if (delStore == null) {
            throw new ImplException(ResultCode.STORE_EXIST_ERROR);
        }
        this.removeById(delStore);
        res.put("delStoreId", delStore.getId());
        res.put("delStoreName", delStore.getStoreName());
        return CommonResult.success("delete store success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex) {
        Map<Object, Object> res = new LinkedHashMap<>();
        IPage<Store> carInfos = storeMapper.selectPage(new Page<>(pageIndex, pageSize), null);
        res.put("totalRecords", carInfos.getTotal());
        res.put("dataList", carInfos.getRecords());
        res.put("pageSize", carInfos.getSize());
        res.put("pageNums", carInfos.getPages());
        res.put("currentIndex", carInfos.getCurrent());
        return CommonResult.success("get stores info success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> select(Integer id, String storeName, String address, Integer stockLast, Integer managerId, Boolean isDisable) {
        Map<Object, Object> res = new LinkedHashMap<>();
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id != null, Store::getId, id)
                .like(StrUtil.isNotBlank(storeName), Store::getStoreName, storeName)
                .like(StrUtil.isNotBlank(address), Store::getAddress, address)
                .ge(stockLast != null, Store::getStockLast, stockLast)
                .eq(managerId != null, Store::getManagerId, managerId)
                .eq(isDisable != null, Store::getIsDisable, isDisable);
        res.put("storeInfos", storeMapper.selectList(queryWrapper));
        return CommonResult.success("get infos success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> updateOne(StoreUpdateVO storeUpdateVO) {
        Map<Object, Object> res = new HashMap<>(8);
        Store updateStore = BeanUtil.copyProperties(storeUpdateVO, Store.class);
        this.updateById(updateStore);
        res.put("storeInfo", updateStore);
        return CommonResult.success("update store info success", res);
    }

    @Override
    public String getNameById(Long storeId) {
        return storeMapper.getNameById(storeId);
    }

    @Override
    public Store getByName(String storeName) {
        return storeMapper.selectOneByStoreName(storeName);
    }
}
