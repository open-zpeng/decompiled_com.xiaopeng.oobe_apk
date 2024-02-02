package com.alibaba.mtl.log.d;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.alibaba.mtl.appmonitor.AppMonitor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: TaskExecutor.java */
/* loaded from: classes.dex */
public class s {
    private static int G = 1;
    private static int H = 3;
    private static int I = 10;
    private static int J = 60;
    public static s a;

    /* renamed from: a  reason: collision with other field name */
    private static ThreadPoolExecutor f67a;
    private static final AtomicInteger f = new AtomicInteger();
    private HandlerThread b = new HandlerThread(AppMonitor.TAG);
    private Handler mHandler;

    static /* synthetic */ ThreadPoolExecutor b() {
        return m29a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TaskExecutor.java */
    /* loaded from: classes.dex */
    public static class a implements ThreadFactory {
        private int priority;

        public a(int i) {
            this.priority = i;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            int andIncrement = s.f.getAndIncrement();
            Thread thread = new Thread(runnable, "AppMonitor:" + andIncrement);
            thread.setPriority(this.priority);
            return thread;
        }
    }

    @TargetApi(9)
    private static ThreadPoolExecutor a(int i, int i2, int i3, int i4, int i5) {
        LinkedBlockingQueue linkedBlockingQueue;
        if (i5 > 0) {
            linkedBlockingQueue = new LinkedBlockingQueue(i5);
        } else {
            linkedBlockingQueue = new LinkedBlockingQueue();
        }
        a aVar = new a(i);
        return new ThreadPoolExecutor(i2, i3, i4, TimeUnit.SECONDS, linkedBlockingQueue, aVar, new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    /* renamed from: a  reason: collision with other method in class */
    private static synchronized ThreadPoolExecutor m29a() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (s.class) {
            if (f67a == null) {
                f67a = a(G, H, I, J, 500);
            }
            threadPoolExecutor = f67a;
        }
        return threadPoolExecutor;
    }

    public static synchronized s a() {
        s sVar;
        synchronized (s.class) {
            if (a == null) {
                a = new s();
            }
            sVar = a;
        }
        return sVar;
    }

    private s() {
        this.b.start();
        this.mHandler = new Handler(this.b.getLooper()) { // from class: com.alibaba.mtl.log.d.s.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                try {
                    if (message.obj == null || !(message.obj instanceof Runnable)) {
                        return;
                    }
                    s.b().submit((Runnable) message.obj);
                } catch (Throwable unused) {
                }
            }
        };
    }

    public final void a(int i, Runnable runnable, long j) {
        try {
            Message obtain = Message.obtain(this.mHandler, i);
            obtain.obj = runnable;
            this.mHandler.sendMessageDelayed(obtain, j);
        } catch (Exception e) {
            com.alibaba.mtl.appmonitor.b.b.m20a((Throwable) e);
        }
    }

    public final void f(int i) {
        this.mHandler.removeMessages(i);
    }

    public final boolean b(int i) {
        return this.mHandler.hasMessages(i);
    }

    public void b(Runnable runnable) {
        try {
            m29a().submit(runnable);
        } catch (Throwable unused) {
        }
    }
}
