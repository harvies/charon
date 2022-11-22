package io.github.harvies.charon.mybatis.diff;

import io.github.harvies.charon.mybatis.BaseTest;
import io.github.harvies.charon.mybatis.diff.convertor.impl.UserDiffConvertor;
import io.github.harvies.charon.mybatis.diff.dto.UserDTO;
import io.github.harvies.charon.mybatis.mapper.UserMapper;
import io.github.harvies.charon.mybatis.po.UserPO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class DiffTest extends BaseTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDiffConvertor userDiffConvertor;

    @Test
    public void test() {

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
