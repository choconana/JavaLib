package com.example.javautil.utils;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class LoggerClient {

    private static final String FORMAT = "[{}] [{}] [{}] [{}] [{}]\n{}";


    public static void debug(String msg) {
        Marker marker = getDefaultMaker();
        Logger logger = getLogger(marker.getModule());
        logger.debug(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), "", "", msg);
    }

    //----------info ------------

    public static void info(Marker marker, String msg) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.info(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), "", "", msg);
    }

    public static void info(Marker marker, String msg, Throwable e) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.info(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), "", "", msg, e);
    }

    public static void info(Marker marker, String msg, Throwable e, String filter1, String filter2) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.info(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), filter1, filter2, msg, e);
    }

    public static void info(Marker marker, String msg, String filter1, String filter2) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.info(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), filter1, filter2, msg);
    }

    public static void info(String msg) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.info(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), "", "", msg);
    }

    public static void info(String format, Object... args) {
        Integer size = args.length;
        for (int i = 0; i < size; i++) {
            format = format.replaceFirst("\\{\\}", "%s");
        }
        if (format.contains("{}")) {
            format = format.replace("{}", "(no args provided)");
        }
        String message = String.format(format, args);
        info(message);
    }

    public static void info(String msg, Exception e) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.info(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), "", "", msg, e);
    }

    public static void info(String msg, Exception e, String filter1, String filter2) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.info(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), filter1, filter2, msg, e);
    }

    //----------warn ------------
    public static void warn(Marker marker, String msg) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.warn(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), "", "", msg);
    }

    public static void warn(Marker marker, String msg, Throwable e) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.warn(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), "", "", msg, e);
    }

    public static void warn(Marker marker, String msg, Throwable e, String filter1, String filter2) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.warn(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), filter1, filter2, msg, e);
    }

    public static void warn(Marker marker, String msg, String filter1, String filter2) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.warn(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), filter1, filter2, msg);
    }

    public static void warn(String msg) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.warn(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), "", "", msg);
    }

    public static void warn(String format, Object... args) {
        Integer size = args.length;
        for (int i = 0; i < size; i++) {
            format = format.replaceFirst("\\{\\}", "%s");
        }
        if (format.contains("{}")) {
            format = format.replace("{}", "(no args provided)");
        }
        String message = String.format(format, args);
        warn(message);
    }

    public static void warn(String msg, Exception e) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.warn(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), "", "", msg, e);
    }

    public static void warn(String msg, Exception e, String filter1, String filter2) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.warn(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), filter1, filter2, msg, e);
    }


    /**
     * 输出警告信息
     *
     * @param e 异常对象
     */
    public static void warn(Throwable e) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.warn(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), "", "", e != null ? e.getMessage() : "", e);
    }


    //----------error ------------
    public static void error(Marker marker, String msg) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.error(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), "", "", msg);
    }

    public static void error(Marker marker, String msg, Throwable e) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.error(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), "", "", msg, e);
    }

    public static void error(Marker marker, String msg, Throwable e, String filter1, String filter2) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.error(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), filter1, filter2, msg, e);
    }

    public static void error(Marker marker, String msg, String filter1, String filter2) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.error(FORMAT, marker.getModule(), marker.getCategory(), marker.getSubCategory(), filter1, filter2, msg);
    }

    public static void error(String msg) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.error(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), "", "", msg);
    }

    public static void error(String format, Object... args) {
        Integer size = args.length;
        for (int i = 0; i < size; i++) {
            format = format.replaceFirst("\\{\\}", "%s");
        }
        if (format.contains("{}")) {
            format = format.replace("{}", "(no args provided)");
        }
        String message = String.format(format, args);
        error(message);
    }

    public static void error(String msg, Exception e) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.error(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), "", "", msg, e);
    }

    public static void error(Exception e) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.error(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), "", "", e != null ? e.getMessage() : "", e);
    }

    public static void error(String msg, Exception e, String filter1, String filter2) {
        Marker mk = getDefaultMaker();
        Logger logger = getLogger(mk.getModule());
        logger.error(FORMAT, mk.getModule(), mk.getCategory(), mk.getSubCategory(), filter1, filter2, msg, e);
    }

    //-------------------------------------------
    private static Logger getLogger(String callerClassName) {
        return log;
    }

    private static Marker getDefaultMaker() {
        Throwable t = new Throwable();
        StackTraceElement[] tes = t.getStackTrace();
        String methodName = tes[2].getMethodName();
        String className = tes[2].getClassName();
        int pos = className.lastIndexOf(".");
        String module = className.substring(pos + 1);
        Marker marker = new Marker();
        marker.setModule(module);
        marker.setCategory(methodName);
        marker.setClazzName(className);
        return marker;
    }

}
