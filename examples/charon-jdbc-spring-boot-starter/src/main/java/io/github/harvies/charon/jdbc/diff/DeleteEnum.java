package io.github.harvies.charon.jdbc.diff;

public enum DeleteEnum {
    NOT_DELETE(0L, "未删除");

    private Long code;
    private String desc;

    private DeleteEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public Long getCode() {
        return this.code;
    }
}
