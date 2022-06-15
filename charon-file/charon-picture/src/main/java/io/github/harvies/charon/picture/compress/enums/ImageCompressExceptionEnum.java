package io.github.harvies.charon.picture.compress.enums;

public enum ImageCompressExceptionEnum {

    HEIGHT_EXCEEDS_MAXIMUM(1, "高度超过最大值");

    public final int code;
    public final String desc;

    ImageCompressExceptionEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
