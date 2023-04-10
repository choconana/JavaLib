package com.example.javautil.utils;

import org.slf4j.MDC;

import java.util.UUID;

public class TraceNoUtil {

    /**
     * key为apmTrace的apm会负责维护
     */
    private static final String APM_TRACE = "apmTxId";

    public static void initTraceNo() {
        String traceId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        MDC.put(APM_TRACE, traceId);
    }

    /**
     * 获取当前线程的跟踪id
     *
     * @return
     */
    public static String getTraceNo() {
        return MDC.get(APM_TRACE);
    }

    /**
     * 为子线程创建关联ID
     *
     * @param traceNo
     */
    public static void newTraceNo(String traceNo) {
        String newTraceNo = traceNo + "-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
        MDC.put(APM_TRACE, newTraceNo);
    }

    /**
     * 移除堆栈信息
     */
    public static void clearTraceNo() {
        MDC.remove(APM_TRACE);
    }
}
