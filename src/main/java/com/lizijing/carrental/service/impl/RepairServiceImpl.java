package com.lizijing.carrental.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.Repair;
import com.lizijing.carrental.entity.enums.StatusEnum;
import com.lizijing.carrental.entity.vo.RepairAddVO;
import com.lizijing.carrental.entity.vo.RepairUpdateVO;
import com.lizijing.carrental.exception.ImplException;
import com.lizijing.carrental.mapper.RepairMapper;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.result.ResultCode;
import com.lizijing.carrental.service.RepairService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 维修单表 服务实现类
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-17
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

    @Resource
    private RepairMapper repairMapper;

    @Override
    public CommonResult<Map<Object, Object>> addOne(RepairAddVO repairAddVO) {
        Map<Object, Object> res = new HashMap<>(8);
        Repair addRepair = BeanUtil.copyProperties(repairAddVO, Repair.class);
        this.save(addRepair);
        res.put("repairInfo", this.getById(addRepair.getId()));
        return CommonResult.success("add repair success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> delOne(String repairNum, Integer operatorId) {
        Map<Object, Object> res = new HashMap<>(8);
        // todo:验证操作者
        Repair delRepair = repairMapper.selectByRepairNum(repairNum);
        if (delRepair == null) {
            throw new ImplException(ResultCode.REPAIR_EXIST_ERROR);
        }
        this.removeById(delRepair);
        res.put("delRepairId", delRepair.getId());
        res.put("delRepairNum", delRepair.getRepairNum());
        return CommonResult.success("delete repair success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex) {
        Map<Object, Object> res = new LinkedHashMap<>();
        IPage<Repair> repairInfo = repairMapper.selectPage(new Page<>(pageIndex, pageSize), null);
        res.put("totalRecords", repairInfo.getTotal());
        res.put("dataList", repairInfo.getRecords());
        res.put("pageSize", repairInfo.getSize());
        res.put("pageNums", repairInfo.getPages());
        res.put("currentIndex", repairInfo.getCurrent());
        return CommonResult.success("get repairs info success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> select(Integer id, String repairNum, Integer userId, Integer carId, Integer operatorId, String status) {
        Map<Object, Object> res = new LinkedHashMap<>();
        LambdaQueryWrapper<Repair> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id != null, Repair::getId, id)
                .eq(StrUtil.isNotBlank(repairNum), Repair::getRepairNum, repairNum)
                .eq(userId != null, Repair::getUserId, userId)
                .eq(carId != null, Repair::getCarId, carId)
                .eq(operatorId != null, Repair::getOperatorId, operatorId)
                .eq(StrUtil.isNotBlank(status), Repair::getStatus, status);
        res.put("repairInfos", repairMapper.selectList(queryWrapper));
        return CommonResult.success("get infos success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> updateOne(RepairUpdateVO repairUpdateVO) {
        Map<Object, Object> res = new HashMap<>(8);
        if (repairUpdateVO.getId() == null && repairUpdateVO.getRepairNum() == null) {
            throw new ImplException(ResultCode.REPAIR_LOCATE_ERROR);
        }
        Repair updateRepair = BeanUtil.copyProperties(repairUpdateVO, Repair.class);
        if (updateRepair.getId() != null) {
            this.updateById(updateRepair);
            res.put("repairInfo", this.getById(updateRepair));
        } else {
            updateRepair.setId(repairMapper.getIdByRepairNum(updateRepair.getRepairNum()));
            this.updateById(updateRepair);
            res.put("repairInfo", repairMapper.selectByRepairNum(updateRepair.getRepairNum()));
        }
        return CommonResult.success("update repair info success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> modifyStatus(String repairNum, Integer status, Integer operatorId) {
        Map<Object, Object> res = new HashMap<>(8);
        // todo:验证操作者
        Repair modifyRep = repairMapper.selectByRepairNum(repairNum);
        if (modifyRep == null) {
            throw new ImplException(ResultCode.REPAIR_EXIST_ERROR);
        }
        modifyRep.setStatus(StatusEnum.getStatus(status));
        this.updateById(modifyRep);
        res.put("delRepairId", modifyRep.getId());
        res.put("delRepairNum", modifyRep.getRepairNum());
        return CommonResult.success("modify repair status success", res);
    }
}
