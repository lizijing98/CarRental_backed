package com.lizijing.carrental.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.Accident;
import com.lizijing.carrental.entity.bean.Repair;
import com.lizijing.carrental.entity.enums.StatusEnum;
import com.lizijing.carrental.entity.vo.AccidentAddVO;
import com.lizijing.carrental.entity.vo.AccidentUpdateVO;
import com.lizijing.carrental.exception.ImplException;
import com.lizijing.carrental.mapper.AccidentMapper;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.result.ResultCode;
import com.lizijing.carrental.service.AccidentService;
import com.lizijing.carrental.service.RepairService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 事故单表 服务实现类
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-14
 */
@Service
public class AccidentServiceImpl extends ServiceImpl<AccidentMapper, Accident> implements AccidentService {

    @Resource
    private AccidentMapper accidentMapper;

    @Resource
    private RepairService repairService;

    @Override
    public CommonResult<Map<Object, Object>> addOne(AccidentAddVO accidentAddVO) {
        Map<Object, Object> res = new HashMap<>(8);
        Accident addAccident = BeanUtil.copyProperties(accidentAddVO, Accident.class);
        // 是否添加对应的维修单
        if (accidentAddVO.getIsCreateRepair()) {
            Repair addRepair = BeanUtil.copyProperties(accidentAddVO, Repair.class);
            if (!repairService.save(addRepair)) {
                throw new ImplException(ResultCode.ACCIDENT_ADD_REPAIR_ERROR);
            }
            addRepair = repairService.getById(addRepair.getId());
            addAccident.setRepairNum(addRepair.getRepairNum());
            res.put("repairInfo", addRepair);
        }
        this.save(addAccident);
        res.put("accidentInfo", this.getById(addAccident.getId()));
        return CommonResult.success("add accident success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> delOne(String accidentNum, Integer operatorId) {
        Map<Object, Object> res = new HashMap<>(8);
        // todo:验证操作者
        Accident delAccident = accidentMapper.selectByAccidentNum(accidentNum);
        if (delAccident == null) {
            throw new ImplException(ResultCode.REPAIR_EXIST_ERROR);
        }
        // 判断对应维修单是否存在
        if (delAccident.getRepairNum() != null) {
            Repair repair = repairService.getOneByRepairNum(delAccident.getRepairNum());
            // 如果维修单未处理完则不允许删除
            if (!repair.getStatus().equals(StatusEnum.PROCESSED.getStatus())) {
                throw new ImplException(ResultCode.REPAIR_NOT_PROCESSED);
            }
        }
        this.removeById(delAccident);
        res.put("delAccidentId", delAccident.getId());
        res.put("delAccidentNum", delAccident.getRepairNum());
        return CommonResult.success("delete accident success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex) {
        Map<Object, Object> res = new LinkedHashMap<>();
        IPage<Accident> accidentInfo = accidentMapper.selectPage(new Page<>(pageIndex, pageSize), null);
        res.put("totalRecords", accidentInfo.getTotal());
        res.put("dataList", accidentInfo.getRecords());
        res.put("pageSize", accidentInfo.getSize());
        res.put("pageNums", accidentInfo.getPages());
        res.put("currentIndex", accidentInfo.getCurrent());
        return CommonResult.success("get accidents info success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> select(Integer id, String accidentNum, Integer userId, Integer carId, Integer operatorId, String status, Boolean hasRepair) {
        Map<Object, Object> res = new LinkedHashMap<>();
        LambdaQueryWrapper<Accident> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id != null, Accident::getId, id)
                .eq(StrUtil.isNotBlank(accidentNum), Accident::getAccidentNum, accidentNum)
                .eq(userId != null, Accident::getUserId, userId)
                .eq(carId != null, Accident::getCarId, carId)
                .eq(operatorId != null, Accident::getOperatorId, operatorId)
                .eq(StrUtil.isNotBlank(status), Accident::getStatus, status)
                .ne(hasRepair == null || !hasRepair, Accident::getRepairNum, null);
        res.put("accidentInfos", accidentMapper.selectList(queryWrapper));
        return CommonResult.success("get infos success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> updateOne(AccidentUpdateVO accidentUpdateVO) {
        Map<Object, Object> res = new HashMap<>(8);
        if (accidentUpdateVO.getId() == null && accidentUpdateVO.getAccidentNum() == null) {
            throw new ImplException(ResultCode.REPAIR_LOCATE_ERROR);
        }
        Accident updateAccident = BeanUtil.copyProperties(accidentUpdateVO, Accident.class);
        if (updateAccident.getId() != null) {
            this.updateById(updateAccident);
            res.put("accidentInfo", this.getById(updateAccident));
        } else {
            updateAccident.setId(accidentMapper.getIdByAccidentNum(updateAccident.getAccidentNum()));
            this.updateById(updateAccident);
            res.put("accidentInfo", accidentMapper.selectByAccidentNum(updateAccident.getAccidentNum()));
        }
        return CommonResult.success("update accident info success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> modifyStatus(String accidentNum, Integer status, Integer operatorId) {
        Map<Object, Object> res = new HashMap<>(8);
        // todo:验证操作者
        Accident modifyAcc = accidentMapper.selectByAccidentNum(accidentNum);
        if (modifyAcc == null) {
            throw new ImplException(ResultCode.REPAIR_EXIST_ERROR);
        }
        // 判断对应维修单是否存在
        if (modifyAcc.getRepairNum() != null && status.equals(StatusEnum.PROCESSED.getCode())) {
            Repair repair = repairService.getOneByRepairNum(modifyAcc.getRepairNum());
            // 如果维修单未处理完则不允许将事故单标注为已处理
            if (!repair.getStatus().equals(StatusEnum.PROCESSED.getStatus())) {
                throw new ImplException(ResultCode.REPAIR_NOT_PROCESSED);
            }
        }
        modifyAcc.setStatus(StatusEnum.getStatus(status));
        this.updateById(modifyAcc);
        res.put("modifyAccidentId", modifyAcc.getId());
        res.put("modifyAccidentNum", modifyAcc.getAccidentNum());
        return CommonResult.success("modify accident status success", res);
    }
}
