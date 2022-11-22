package io.github.harvies.charon.mybatis.diff;

import io.github.harvies.charon.mybatis.diff.convertor.DiffConvertor;
import io.github.harvies.charon.mybatis.diff.impl.DiffServiceImpl;
import io.github.harvies.charon.mybatis.mapper.MyBaseMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DiffServiceBuilder<DTO extends BasePlusDTO, PO extends BasePlusPO> {
    private MyBaseMapper<PO> baseMapper;

    private DiffConvertor<PO, DTO> diffConvert;

    public DiffService<DTO, PO> build() {
        DiffServiceImpl<DTO, PO> diffService = new DiffServiceImpl<>();
        diffService.setBaseMapper(this.getBaseMapper());
        diffService.setDiffConvertor(this.getDiffConvert());
        return diffService;
    }
}
