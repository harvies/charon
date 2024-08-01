package io.github.harvies.charon.model.exception;

import io.github.harvies.charon.model.api.ApiErrorCode;
import io.github.harvies.charon.model.constant.CharonErrorCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SystemException extends RuntimeException {
    private String code;

    public static SystemException throwException(ApiErrorCode error) {
        throw new SystemException(error);
    }

    public SystemException(ApiErrorCode error) {
        super(error.getMsg());
        this.code = error.getCode();
    }

    public static void throwException(ApiErrorCode error, Throwable throwable) {
        throw new SystemException(error, throwable);
    }

    public SystemException(ApiErrorCode error, Throwable throwable) {
        super(error.getMsg(), throwable);
        this.code = error.getCode();
    }

    public static void throwException(String errMsg) {
        throw new SystemException(errMsg);
    }

    public SystemException(String errMsg) {
        super(errMsg);
        this.code = CharonErrorCode.SYSTEM_ERROR.getCode();
    }

    public static void throwException(String errMsg, Throwable throwable) {
        throw new SystemException(errMsg, throwable);
    }

    public SystemException(String errMsg, Throwable throwable) {
        super(errMsg, throwable);
        this.code = CharonErrorCode.SYSTEM_ERROR.getCode();
    }

    public static void throwException(String errCode, String errorMsg) {
        throw new SystemException(errCode, errorMsg);
    }

    public SystemException(String code, String errorMsg) {
        super(errorMsg);
        this.code = code;
    }

    public static void throwException(String errorCode, String errorMsg, Throwable throwable) {
        throw new SystemException(errorCode, errorMsg, throwable);
    }

    public SystemException(String code, String errorMsg, Throwable throwable) {
        super(errorMsg, throwable);
        this.code = code;
    }
}
