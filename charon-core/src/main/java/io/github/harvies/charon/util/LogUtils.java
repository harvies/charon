package io.github.harvies.charon.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * 日志工具类(包含查看日志级别信息和动态设置日志级别)
 */
public class LogUtils {

    /**
     * 获取所有日志级别信息
     */
    public static List<? extends Logger> list() {
        ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
        if (iLoggerFactory instanceof LoggerContext) {
            return ((LoggerContext) iLoggerFactory).getLoggerList();
        }
        return Collections.emptyList();
    }

    /**
     * 设置日志级别
     */
    public static void set(String name, String level) {
        ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
        if (iLoggerFactory instanceof LoggerContext) {
            Level levelTmp = null;
            if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(level, "trace")) {
                levelTmp = Level.TRACE;
            } else if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(level, "debug")) {
                levelTmp = Level.DEBUG;
            } else if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(level, "info")) {
                levelTmp = Level.INFO;
            } else if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(level, "warn")) {
                levelTmp = Level.WARN;
            } else if (StringUtils.equalsIgnoreCase(level, "error")) {
                levelTmp = Level.ERROR;
            }
            if (levelTmp != null) {
                ((LoggerContext) iLoggerFactory).getLogger(name).setLevel(levelTmp);
            }
        }
    }
}
