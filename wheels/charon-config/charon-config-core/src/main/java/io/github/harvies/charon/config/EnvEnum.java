package io.github.harvies.charon.config;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum EnvEnum {
    DEV("dev", "开发"),
    TEST("test", "测试"),
    PRE("pre", "预发"),
    PROD("prod", "生产");

    private String code;
    private String name;

    EnvEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EnvEnum of(String code) {
        EnvEnum[] values = EnvEnum.values();
        for (EnvEnum envEnum : values) {
            if (Objects.equals(code, envEnum.getCode())) {
                return envEnum;
            }
        }
        throw new RuntimeException("环境不存在!");
    }
}
