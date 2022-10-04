package io.github.harvies.charon.model;

import io.github.harvies.charon.model.api.ApiErrorCode;
import io.github.harvies.charon.model.constant.CharonErrorCode;
import io.github.harvies.charon.model.page.PageResponse;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean success = false;
    private String code;
    private String msg;
    private String traceId;
    private T data;

    public ApiResult() {
    }

    public static <T> ApiResult<T> success() {
        ApiResult<T> apiResult = ApiResult.build(CharonErrorCode.SUCCESS.getModularCode(), CharonErrorCode.SUCCESS.getMsg(), new Object[0]);
        apiResult.setSuccess(true);
        return apiResult;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> response = success();
        response.setData(data);
        return response;
    }

    public static <T, R extends PageResponse<T>> ApiResult<R> successPage(List<T> data) {
        ApiResult<R> response = success();
        return response;
    }

    public static <T> ApiResult<T> error(String code, String message, Object... params) {
        ApiResult<T> apiResult = ApiResult.build(code, message, params);
        apiResult.setSuccess(false);
        return apiResult;
    }

    public static <T> ApiResult<T> error() {
        return error(CharonErrorCode.SYSTEM_ERROR);
    }

    public static <T> ApiResult<T> error(String message) {
        return error(CharonErrorCode.SYSTEM_ERROR.getCode(), message);
    }

    public static <T> ApiResult<T> error(ApiErrorCode error, Object... params) {
        return error(error.getCode(), error.getMsg(), params);
    }

    public static <T> ApiResult<T> error(String message, ApiErrorCode error, Object... params) {
        return error(error.getCode(), message, params);
    }

    private static ApiResult build(String code, String message, Object... params) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMsg(message);
        result.setTraceId(TraceIdGenerator.generate());
        return result;
    }

    @Transient
    public Boolean isOk() {
        return this.success == null ? false : this.success;
    }

    @Transient
    public Boolean isNotOk() {
        return !this.isOk();
    }

    @Transient
    public Boolean isSucceed() {
        return this.success != null && this.success && this.data != null;
    }

    @Transient
    public Boolean isNotSucceed() {
        return !this.isSucceed();
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public T getData() {
        return this.data;
    }

    public ApiResult<T> setSuccess(final Boolean success) {
        this.success = success;
        return this;
    }

    public ApiResult<T> setCode(final String code) {
        this.code = code;
        return this;
    }

    public ApiResult<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    public ApiResult<T> setTraceId(final String traceId) {
        this.traceId = traceId;
        return this;
    }

    public ApiResult<T> setData(final T data) {
        this.data = data;
        return this;
    }
}
