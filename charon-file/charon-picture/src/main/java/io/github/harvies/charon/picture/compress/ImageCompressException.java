package io.github.harvies.charon.picture.compress;

import io.github.harvies.charon.picture.compress.enums.ImageCompressExceptionEnum;
import lombok.Getter;

@Getter
public class ImageCompressException extends RuntimeException {

    private ImageCompressExceptionEnum exceptionEnum;
    private FileContext fileContext;

    public ImageCompressException(ImageCompressExceptionEnum exceptionEnum, FileContext fileContext) {
        this.exceptionEnum = exceptionEnum;
        this.fileContext = fileContext;
    }
}
