package io.github.harvies.charon.model.api;

public interface ApiErrorCode {

    default String getCode() {
        return this.getProjectCode() + this.getModularCode();
    }

    String getProjectCode();

    String getModularCode();

    String getMsg();

    default String toString(String code, String message) {
        return code + "：" + message;
    }
}
