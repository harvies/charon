package io.github.harvies.charon.picture.compress;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileConfig {

    public static String TMP_PATH = System.getProperty("java.io.tmpdir") + "/fileupload";

    /**
     * 压缩压缩最大次数
     */
    public static int RECURSION_COMPRESS_TIME = 3;

    public static boolean debug = false;

}
