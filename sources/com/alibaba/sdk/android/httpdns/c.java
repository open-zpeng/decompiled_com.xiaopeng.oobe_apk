package com.alibaba.sdk.android.httpdns;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with other field name */
    private static final TimeUnit f85a = TimeUnit.SECONDS;

    /* renamed from: a  reason: collision with other field name */
    private static final ThreadFactory f84a = new ThreadFactory() { // from class: com.alibaba.sdk.android.httpdns.c.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("httpdns worker");
            thread.setDaemon(false);
            thread.setUncaughtExceptionHandler(new j());
            return thread;
        }
    };
    private static final ExecutorService a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1, f85a, new SynchronousQueue(), f84a);

    public static ExecutorService a() {
        return a;
    }
}
