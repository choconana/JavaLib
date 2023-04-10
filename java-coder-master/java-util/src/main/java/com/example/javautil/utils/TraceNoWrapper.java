package com.example.javautil.utils;

public class TraceNoWrapper implements Runnable {
    private Runnable runnable;

    private String traceNo;

    private Thread parent;

    TraceNoWrapper(Runnable runnable) {
        this.runnable = runnable;
        this.traceNo = TraceNoUtil.getTraceNo();
        this.parent = Thread.currentThread();
    }

    @Override
    public void run() {
        if (isChildThread()) {
            TraceNoUtil.newTraceNo(traceNo);
        }
        try {
            runnable.run();
        } catch (Exception e) {
            TraceNoUtil.clearTraceNo();
        }
    }

    private boolean isChildThread() {
        return Thread.currentThread() != this.parent;
    }
}
