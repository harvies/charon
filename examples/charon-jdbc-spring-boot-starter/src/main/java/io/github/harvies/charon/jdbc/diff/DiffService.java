package io.github.harvies.charon.jdbc.diff;

import java.util.List;

public interface DiffService<DTO extends BasePlusDTO, PO extends BasePlusPO> {
    DiffResult<DTO, PO> get(List<DTO> rList);

    void persistence(DiffResult<DTO, PO> diffResult);
}
