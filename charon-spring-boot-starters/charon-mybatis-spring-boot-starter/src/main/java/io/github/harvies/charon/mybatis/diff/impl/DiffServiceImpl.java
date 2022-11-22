package io.github.harvies.charon.mybatis.diff.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.google.common.collect.Lists;
import io.github.harvies.charon.mybatis.diff.*;
import io.github.harvies.charon.mybatis.diff.convertor.DiffConvertor;
import io.github.harvies.charon.mybatis.mapper.MyBaseMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DiffServiceImpl<DTO extends BasePlusDTO, PO extends BasePlusPO> implements DiffService<DTO, PO> {
    @Getter
    @Setter
    private MyBaseMapper<PO> baseMapper;

    @Getter
    @Setter
    private DiffConvertor<PO, DTO> diffConvertor;

    public DiffResult<DTO, PO> get(List<DTO> rList) {
        if (CollectionUtils.isEmpty(rList)) {
            return new DiffResult<DTO, PO>().setDiff(false);
        }
        List<PO> PoList = Lists.newArrayList();
        for (DTO r : rList) {
            PO t = diffConvertor.convertToPO(r);
            PoList.add(t);
        }
        DiffResult<DTO, PO> tDiff = new DiffResult<>();

        Set<Serializable> idSet = PoList.stream().map(PO::getId).collect(Collectors.toSet());
        Long bizId = PoList.get(0).getBizId();

        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put(BasePlusPO.BIZ_ID, bizId);
        columnMap.put(BasePlusPO.DELETED, DeleteEnum.NOT_DELETE.getCode());
        List<PO> tListFromDB = baseMapper.selectByMap(columnMap);
        Map<Serializable, PO> tMapFromDB = tListFromDB.stream().collect(Collectors.toMap(PO::getId, t -> t, (o, o2) -> o2));
        tDiff.setPoMapFromDB(tMapFromDB);

        List<DTO> needDeleteDTOList = Lists.newArrayList();
        List<DTO> needAddDTOList = Lists.newArrayList();
        List<DTO> needUpdateDTOList = Lists.newArrayList();

        for (DTO dto : rList) {
            //新增
            if (dto.getId() == null) {

                needAddDTOList.add(dto);
                continue;
            }
            if (tMapFromDB.containsKey(dto.getId())) {
                //更新
                needUpdateDTOList.add(dto);
            }
        }
        // 删除
        for (PO t : tListFromDB) {
            if (!idSet.contains(t.getId())) {
                needDeleteDTOList.add(diffConvertor.convertToDTO(t));
            }
        }
        tDiff.setNeedAddDTOList(needAddDTOList);
        tDiff.setNeedUpdateDTOList(needUpdateDTOList);
        tDiff.setNeedDeleteDTOList(needDeleteDTOList);

        if (CollectionUtils.isNotEmpty(needAddDTOList) || CollectionUtils.isNotEmpty(needUpdateDTOList) || CollectionUtils.isNotEmpty(needDeleteDTOList)) {
            tDiff.setDiff(true);
        }
        return tDiff;
    }

    @Override
    public void persistence(DiffResult<DTO, PO> diffResult) {
        //新增
        List<PO> needAddPoList = diffConvertor.convertToPO(diffResult.getNeedAddDTOList());

        if (CollectionUtils.isNotEmpty(needAddPoList)) {
            for (PO aDo : needAddPoList) {
                baseMapper.insert(aDo);
            }
        }
        //修改
        List<DTO> needUpdateDTOList = diffResult.getNeedUpdateDTOList();
        List<PO> PoList = diffConvertor.convertToPO(needUpdateDTOList);
        PoList.forEach(aDo -> {
            PO poFromDB = diffResult.getPoMapFromDB().get(aDo.getId());
            BeanUtil.copyProperties(aDo, poFromDB, CopyOptions.create().ignoreNullValue());
            baseMapper.updateById(poFromDB);
        });

        //删除
        List<DTO> needDeleteDTOList = diffResult.getNeedDeleteDTOList();
        if (CollectionUtils.isNotEmpty(needDeleteDTOList)) {
            baseMapper.deleteBatchIds(needDeleteDTOList.stream().map(BasePlusDTO::getId).collect(Collectors.toList()));
        }
    }

}
