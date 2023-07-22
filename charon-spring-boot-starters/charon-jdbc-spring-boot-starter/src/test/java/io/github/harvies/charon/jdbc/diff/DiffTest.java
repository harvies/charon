package io.github.harvies.charon.jdbc.diff;

import io.github.harvies.charon.jdbc.BaseTest;
import io.github.harvies.charon.jdbc.diff.convertor.impl.UserDiffConvertor;
import io.github.harvies.charon.jdbc.diff.dto.UserDTO;
import io.github.harvies.charon.jdbc.mapper.UserMapper;
import io.github.harvies.charon.jdbc.po.UserPO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DiffTest extends BaseTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDiffConvertor userDiffConvertor;

    @Test
    public void test() {

        userMapper.dropTableIfExists();
        userMapper.createTableIfNotExists();

        UserPO userPO = new UserPO();
        userPO.setId(null);
        userPO.setBizId(1L);
        userPO.setDeleted(0L);
        userMapper.insert(userPO);


        List<UserDTO> userDTOList = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(null);
        userDTO.setBizId(1L);
        userDTO.setDeleted(0L);
        userDTOList.add(userDTO);

        DiffService<UserDTO, UserPO> diffService = new DiffServiceBuilder<>(userMapper, userDiffConvertor)
                .build();
        DiffResult<UserDTO, UserPO> diffResult = diffService.get(userDTOList);
        if (!diffResult.isDiff()) {
            return;
        }
        diffService.persistence(diffResult);
    }
}
