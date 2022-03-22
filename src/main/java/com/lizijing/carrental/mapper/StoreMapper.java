package com.lizijing.carrental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizijing.carrental.entity.bean.Store;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 门店表 Mapper 接口
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-01
 */
@Mapper
public interface StoreMapper extends BaseMapper<Store> {

    /**
     * 根据门店名查找门店
     *
     * @param storeName 门店名
     * @return 门店实体
     */
    Store selectOneByStoreName(String storeName);

    /**
     * 根据门店 ID 获取门店名
     *
     * @param storeId 门店 ID
     * @return 门店名
     */
    String getNameById(Long storeId);
}
