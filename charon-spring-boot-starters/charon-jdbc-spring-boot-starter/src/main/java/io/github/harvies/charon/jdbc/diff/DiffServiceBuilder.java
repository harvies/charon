package io.github.harvies.charon.jdbc.diff;

import io.github.harvies.charon.jdbc.diff.convertor.DiffConvertor;
import io.github.harvies.charon.jdbc.diff.impl.DiffServiceImpl;
import io.github.harvies.charon.jdbc.mybatis.mapper.BaseMapperPlus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DiffServiceBuilder<DTO extends BasePlusDTO, PO extends BasePlusPO> {
    private BaseMapperPlus<PO> baseMapper;

    private DiffConvertor<PO, DTO> diffConvert;

    public DiffService<DTO, PO> build() {
        DiffServiceImpl<DTO, PO> diffService = new DiffServiceImpl<>();
        diffService.setBaseMapper(this.getBaseMapper());
        diffService.setDiffConvertor(this.getDiffConvert());
        return diffService;
    }
}
