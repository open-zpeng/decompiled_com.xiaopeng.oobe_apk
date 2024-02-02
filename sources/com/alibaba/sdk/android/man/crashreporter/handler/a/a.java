package com.alibaba.sdk.android.man.crashreporter.handler.a;

import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class a implements Thread.UncaughtExceptionHandler {
    private Integer a = 20;

    /* renamed from: a  reason: collision with other field name */
    private Thread.UncaughtExceptionHandler f148a = Thread.getDefaultUncaughtExceptionHandler();
    private com.alibaba.sdk.android.man.crashreporter.handler.a crashReportManager;
    private final AtomicBoolean crashing;

    public a(AtomicBoolean atomicBoolean, com.alibaba.sdk.android.man.crashreporter.handler.a aVar) {
        this.crashReportManager = null;
        this.crashing = atomicBoolean;
        this.crashReportManager = aVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (this.crashing.compareAndSet(false, true)) {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("uncaughtException start.");
            try {
                MotuCrashReporter.getInstance().setCrashReporterState(0);
                String b = b(th);
                String a = a(th);
                if (b != null && a != null) {
                    if (this.crashReportManager != null) {
                        this.crashReportManager.a(th, thread, b, a);
                    } else {
                        com.alibaba.sdk.android.man.crashreporter.b.a.e("java: crash manager is null!");
                    }
                } else {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("uncaughtException exception or backtrace is null!");
                }
            } catch (Throwable th2) {
                try {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("uncaughtException error.", th2);
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("uncaughtException end.");
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f148a;
                    if (uncaughtExceptionHandler == null) {
                    }
                } finally {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("uncaughtException end.");
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.f148a;
                    if (uncaughtExceptionHandler2 != null) {
                        uncaughtExceptionHandler2.uncaughtException(thread, th);
                    }
                }
            }
        }
    }

    public void b() {
        Thread.setDefaultUncaughtExceptionHandler(this.f148a);
    }

    private String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            th.printStackTrace(printWriter);
            return stringWriter.toString();
        } finally {
            printWriter.close();
            try {
                stringWriter.close();
            } catch (IOException e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("close StringWriter failed.", e);
            }
        }
    }

    private String b(Throwable th) {
        Throwable m72a = m72a(th);
        return m72a != null ? m72a.getClass().getSimpleName() : "";
    }

    /* renamed from: a  reason: collision with other method in class */
    private Throwable m72a(Throwable th) {
        int i = 1;
        while (th.getCause() != null && th != th.getCause() && i <= this.a.intValue()) {
            i++;
            th = th.getCause();
        }
        return th;
    }
}
