package io.github.harvies.charon.mybatis.diff.convertor;

import java.util.List;

public interface DiffConvertor<PO, DTO> {
    PO convertToPO(DTO t);

    List<PO> convertToPO(List<DTO> dtoList);

    DTO convertToDTO(PO r);

    List<DTO> convertToDTO(List<PO> doList);
}
