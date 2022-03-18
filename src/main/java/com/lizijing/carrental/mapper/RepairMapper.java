package com.lizijing.carrental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizijing.carrental.entity.bean.Repair;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 维修单表 Mapper 接口
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-14
 */
@Mapper
public interface RepairMapper extends BaseMapper<Repair> {

    /**
     * 根据维修单编号查询维修单
     *
     * @param repairNum 维修单编号
     * @return 维修单实体
     */
    Repair selectByRepairNum(String repairNum);

    /**
     * 根据维修单编号获取维修单 ID
     *
     * @param repairNum 维修单编号
     * @return 维修单 ID
     */
    Long getIdByRepairNum(String repairNum);
}
