package io.github.harvies.charon.result;

/**
 * @author harvies
 */
public enum ResultEnum {
    UNKNOWN_ERROR(-1, "系统异常"),
    SUCCESS(20000, "请求成功"),
    FAILED(1, "请求失败");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 返回信息
     */
    private final String message;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
