package io.github.harvies.charon.result;

import java.util.List;

public class Results {

    public static <T> ResultDTO<T> success(T t) {
        ResultDTO<T> resultDTO = new ResultDTO<>(t);
        resultDTO.setCode(ResultEnum.SUCCESS.getCode());
        resultDTO.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultDTO;
    }

    /**
     *
     * @param msg 失败原因
     * @return
     */
    public static <T> ResultDTO<T> failed(String msg) {
        ResultDTO<T> resultDTO = new ResultDTO<>(null);
        resultDTO.setCode(ResultEnum.FAILED.getCode());
        resultDTO.setMsg(msg);
        return resultDTO;
    }

    public static <T> ResultDTO<T> unknownError(T t) {
        ResultDTO<T> resultDTO = new ResultDTO<>(t);
        resultDTO.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
        resultDTO.setMsg(ResultEnum.UNKNOWN_ERROR.getMsg());
        return resultDTO;
    }

    public static ResultDTO<String> unknownError() {
        return unknownError("system exception!");
    }

    public static <T> PageResultDTO<T> success(List<T> list, long totalCount) {
        PageResultDTO<T> resultDTO = new PageResultDTO<>(list);
        resultDTO.setTotal(totalCount);
        resultDTO.setCode(ResultEnum.SUCCESS.getCode());
        resultDTO.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultDTO;
    }


}
