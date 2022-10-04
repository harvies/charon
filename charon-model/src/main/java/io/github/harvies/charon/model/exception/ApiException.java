package io.github.harvies.charon.model.exception;

import io.github.harvies.charon.model.api.ApiErrorCode;
import io.github.harvies.charon.model.constant.CharonErrorCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ApiException extends RuntimeException {
    private String code;
    private Object[] params;

    public static void throwException(String code, String msg, Object... params) {
        throw new ApiException(code, msg, params);
    }

    public static void throwException(ApiErrorCode error, Object... params) {
        throw new ApiException(error, params);
    }

    public static void throwException(String msg) {
        throw new ApiException(msg);
    }

    public static void throwException(String msg, ApiErrorCode error, Object... params) {
        throw new ApiException(msg, error, params);
    }

    public ApiException(ApiErrorCode error, Object... params) {
        this(error.getCode(), error.getMsg(), params);
    }

    public ApiException(String msg) {
        this(CharonErrorCode.SYSTEM_ERROR.getCode(), msg);
    }

    public ApiException(String msg, ApiErrorCode error, Object... params) {
        this(error.getCode(), msg, params);
    }

    public ApiException(String code, String msg, Object... params) {
        super(msg);
        this.code = code;
        this.params = params;
    }
}
