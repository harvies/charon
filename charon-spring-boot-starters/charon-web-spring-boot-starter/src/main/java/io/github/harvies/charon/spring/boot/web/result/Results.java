package io.github.harvies.charon.spring.boot.web.result;

import java.util.List;

public class Results {

    public static <T> ResultDTO<T> success(T t) {
        ResultDTO<T> resultDTO = new ResultDTO<T>(t);
        resultDTO.setCode(ResultEnum.SUCCESS.getCode());
        resultDTO.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultDTO;
    }

    public static <T> ResultDTO<T> failed(T t) {
        ResultDTO<T> resultDTO = new ResultDTO<>(t);
        resultDTO.setCode(ResultEnum.FAILED.getCode());
        resultDTO.setMsg(ResultEnum.FAILED.getMsg());
        return resultDTO;
    }

    public static <T> PageResultDTO<T> success(List<T> list, long totalCount) {
        PageResultDTO<T> resultDTO = new PageResultDTO<>(list);
        resultDTO.setTotal(totalCount);
        resultDTO.setCode(ResultEnum.SUCCESS.getCode());
        resultDTO.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultDTO;
    }
    
    
}
