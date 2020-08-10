package io.github.harvies.charon.spring.boot.web.result;

/**
 * @author harvies
 */
public enum ResultEnum {
    UNKNOWN_ERROR("-1", "未知错误"),
    SUCCESS("0", "请求成功"),
    FAILED("1", "请求失败"),
    ;
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;

    private ResultEnum(String code, String msg) {
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
