package io.github.bloxxxx.etaclient.util;

import io.github.bloxxxx.etaclient.Etaclient;

public final class LogUtil {
    private LogUtil() {}

    public static void log(String message) {
        Etaclient.LOGGER.info(message);
    }
    public static void log(Object obj) {
        log(obj.toString());
    }

    public static void warn(String message) {
        Etaclient.LOGGER.warn(message);
    }
    public static void warn(Object obj) {
        warn(obj.toString());
    }

    public static void error(String message) {
        Etaclient.LOGGER.error(message);
    }
    public static void error(Object obj) {
        error(obj.toString());
    }
}
