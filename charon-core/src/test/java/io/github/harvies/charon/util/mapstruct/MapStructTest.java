package io.github.harvies.charon.util.mapstruct;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MapStructTest {

    @Test
    public void test() {
        String password = "bbb";
        UserDTO userDTO = new UserDTO("aaa").setId(1L).setPassword(password).setStatusList(Arrays.asList(1L, 2L));
        UserVO userVO = UserConvert.INSTANCE.convert(userDTO);
        assertThat(userVO.getStatusList().get(0), is("1"));
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);
        List<UserVO> userVOList = UserConvert.INSTANCE.convert(userDTOList);
        assertThat(userVOList.get(0).getPassWord(), is(password));
    }
}
