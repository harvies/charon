package io.github.harvies.eris.base.mapstruct;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    @Test
    public void test() {
        UserDTO userDTO = new UserDTO("aaa").setId(1L).setPassword("bbb").setStatusList(Arrays.asList(1L, 2L));
        UserVO userVO = UserConvert.INSTANCE.convert(userDTO);
        System.err.println(userVO.getStatusList().get(0));

        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);
        List<UserVO> userVOList = UserConvert.INSTANCE.convert(userDTOList);
        System.err.println(userVOList);
    }

    @SneakyThrows
    @Test
    public void test2() {
        UserDTO userDTO = new UserDTO("aaa").setId(1L).setPassword("bbb").setStatusList(Arrays.asList(1L, 2L));
        UserVO userVO = new UserVO();
        //反射copy有坑
        BeanUtils.copyProperties(userVO, userDTO);
        System.err.println(userVO.getStatusList().get(0));
    }

    @SneakyThrows
    @Test
    public void test3() {
        UserDTO userDTO = new UserDTO("aaa").setId(1L).setPassword("bbb").setStatusList(Arrays.asList(1L, 2L));
        UserVO userVO = new UserVO();
        System.err.println(userVO);
        org.springframework.beans.BeanUtils.copyProperties(userDTO, userVO);
        System.err.println(userVO.getStatusList().get(0));
    }
}
