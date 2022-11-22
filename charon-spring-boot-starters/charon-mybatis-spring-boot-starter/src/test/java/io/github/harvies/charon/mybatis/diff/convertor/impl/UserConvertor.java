package io.github.harvies.charon.mybatis.diff.convertor.impl;

import io.github.harvies.charon.mybatis.diff.dto.UserDTO;
import io.github.harvies.charon.mybatis.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvertor {

    UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);

    @Mappings({
    })
    UserPO toPO(UserDTO userDTO);

    List<UserPO> toPO(List<UserDTO> userDTOList);

    @Mappings({
    })
    UserDTO toDtoObject(UserPO userDO);

    List<UserDTO> toDtoObject(List<UserPO> userDOList);

}
