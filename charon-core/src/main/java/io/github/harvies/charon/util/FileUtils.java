package io.github.harvies.charon.util;

public class FileUtils {
    /**
     * 获取当前用户家目录
     *
     * @return 路径
     */
    public static String getCurrentUserHomePath() {
        return System.getProperty("user.home");
    }
}
