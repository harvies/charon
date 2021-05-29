package io.github.harvies.charon.util.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * harvies
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({
            @Mapping(source = "password", target = "passWord")
    })
    UserVO convert(UserDTO userDTO);

    List<UserVO> convert(List<UserDTO> userDTOList);
}
