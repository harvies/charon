package io.github.harvies.charon.picture.compress.strategy;


import io.github.harvies.charon.picture.compress.AbstractImageCompress;
import io.github.harvies.charon.picture.compress.FileCompress;
import io.github.harvies.charon.picture.compress.FileCompressUtils;
import io.github.harvies.charon.picture.compress.FileContext;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class BMPCompress extends AbstractImageCompress implements FileCompress {

    public static final String IMAGE_BMP = "image/bmp";

    @Override
    public void compress0(FileContext fileContext) throws IOException {
        FileCompressUtils.fillOriginFileToOutputFile(fileContext);
    }

    @Override
    public String getMimeType() {
        return IMAGE_BMP;
    }
}
