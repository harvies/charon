package io.github.harvies.charon.mybatis.diff.convertor.impl;

import io.github.harvies.charon.mybatis.diff.convertor.DiffConvertor;
import io.github.harvies.charon.mybatis.diff.dto.UserDTO;
import io.github.harvies.charon.mybatis.po.UserPO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDiffConvertor implements DiffConvertor<UserPO, UserDTO> {
    @Override
    public UserPO convertToPO(UserDTO userDTO) {
        return UserConvertor.INSTANCE.toPO(userDTO);
    }

    @Override
    public List<UserPO> convertToPO(List<UserDTO> userDTOList) {
        return UserConvertor.INSTANCE.toPO(userDTOList);
    }

    @Override
    public UserDTO convertToDTO(UserPO r) {
        return UserConvertor.INSTANCE.toDtoObject(r);
    }

    @Override
    public List<UserDTO> convertToDTO(List<UserPO> userDOList) {
        return UserConvertor.INSTANCE.toDtoObject(userDOList);
    }
}
