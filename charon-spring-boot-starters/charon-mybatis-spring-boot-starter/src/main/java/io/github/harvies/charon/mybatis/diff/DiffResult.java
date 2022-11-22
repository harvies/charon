package io.github.harvies.charon.mybatis.diff;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class DiffResult<DTO, PO> {

    private boolean diff;

    private List<DTO> needDeleteDTOList = Lists.newArrayList();

    private List<DTO> needAddDTOList = Lists.newArrayList();

    private List<DTO> needUpdateDTOList = Lists.newArrayList();

    private Map<Serializable, PO> poMapFromDB;
}
