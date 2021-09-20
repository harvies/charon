package io.github.harvies.charon.result;

/**
 * @author harvies
 */
public enum ResultEnum {
    UNKNOWN_ERROR("-1", "系统异常"),
    SUCCESS("20000", "请求成功"),
    FAILED("1", "请求失败");
    /**
     * 状态码
     */
    private final String code;
    /**
     * 返回信息
     */
    private final String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
