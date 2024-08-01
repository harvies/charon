package io.github.harvies.charon.model.constant;

import io.github.harvies.charon.model.api.ApiErrorCode;

public enum CharonErrorCode implements ApiErrorCode {
    SUCCESS("200", "请求成功"),
    SYSTEM_ERROR("40001", "系统异常"),
    PATH_ERROR("40004", "请求路径错误"),
    ;

    private String code;
    private String msg;

    private CharonErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getProjectCode() {
        return "SYS";
    }

    public String getModularCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String toString() {
        return this.toString(this.getCode(), this.getMsg());
    }
}
