package io.github.harvies.charon.util;

import java.io.File;

public class FileUtils extends org.apache.commons.io.FileUtils {
    /**
     * 获取当前用户家目录 例如/Users/harvies
     *
     * @return 路径
     */
    public static String getCurrentUserHomePath() {
        return System.getProperty("user.home");
    }

    /**
     * 分隔同一个路径字符串中的目录的，例如：
     * C:/Program Files/Common Files
     * 就是指“/”
     * <p>
     * unix下是/ windows下是\\
     *
     * @return
     */
    public static String getSeparator() {
        return File.separator;
    }

    /**
     * 分隔连续多个路径字符串的分隔符，例如:
     * java   -cp   test.jar;abc.jar   HelloWorld
     * 就是指“;”
     * <p>
     * linux下是: windows下是;
     *
     * @return
     */
    public static String getPathSeparator() {
        return File.pathSeparator;
    }

    /**
     * 获取操作系统临时目录
     *
     * @return 临时目录
     */
    public static String getTmpDir() {
        return System.getProperty("java.io.tmpdir");
    }
}
