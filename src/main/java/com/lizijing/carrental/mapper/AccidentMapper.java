package com.lizijing.carrental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizijing.carrental.entity.bean.Accident;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 事故单表 Mapper 接口
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-14
 */
@Mapper
public interface AccidentMapper extends BaseMapper<Accident> {

    /**
     * 根据事故单编号查询事故单
     *
     * @param accidentNum 事故单编号
     * @return 事故单实体
     */
    Accident selectByAccidentNum(String accidentNum);

    /**
     * 根据事故单编号查询事故单 ID
     *
     * @param accidentNum 事故单编号
     * @return 事故单 ID
     */
    Long getIdByAccidentNum(String accidentNum);
}
