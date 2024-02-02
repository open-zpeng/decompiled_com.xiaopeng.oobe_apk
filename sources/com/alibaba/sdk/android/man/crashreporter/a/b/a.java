package com.alibaba.sdk.android.man.crashreporter.a.b;

import android.os.Looper;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes.dex */
public class a extends Error {
    private static final long serialVersionUID = 1;
    private final Map<Thread, StackTraceElement[]> b;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.alibaba.sdk.android.man.crashreporter.a.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0015a {
        private final StackTraceElement[] a;
        private final String r;

        /* renamed from: com.alibaba.sdk.android.man.crashreporter.a.b.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private class C0016a extends Throwable {
            private C0016a(C0016a c0016a) {
                super(C0015a.this.r, c0016a);
            }

            @Override // java.lang.Throwable
            public Throwable fillInStackTrace() {
                setStackTrace(C0015a.this.a);
                return this;
            }
        }

        private C0015a(String str, StackTraceElement[] stackTraceElementArr) {
            this.r = str;
            this.a = stackTraceElementArr;
        }
    }

    private a(C0015a.C0016a c0016a, Map<Thread, StackTraceElement[]> map) {
        super("Application Not Responding", c0016a);
        this.b = map;
    }

    public Map<Thread, StackTraceElement[]> c() {
        return this.b;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public static a a(String str, boolean z) {
        final Thread thread = Looper.getMainLooper().getThread();
        TreeMap treeMap = new TreeMap(new Comparator<Thread>() { // from class: com.alibaba.sdk.android.man.crashreporter.a.b.a.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(Thread thread2, Thread thread3) {
                if (thread2 == thread3) {
                    return 0;
                }
                Thread thread4 = thread;
                if (thread2 == thread4) {
                    return 1;
                }
                if (thread3 == thread4) {
                    return -1;
                }
                return thread3.getName().compareTo(thread2.getName());
            }
        });
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            if (entry.getKey() == thread || (entry.getKey().getName().startsWith(str) && (z || entry.getValue().length > 0))) {
                treeMap.put(entry.getKey(), entry.getValue());
            }
        }
        C0015a.C0016a c0016a = null;
        for (Map.Entry entry2 : treeMap.entrySet()) {
            C0015a c0015a = new C0015a(((Thread) entry2.getKey()).getName(), (StackTraceElement[]) entry2.getValue());
            c0015a.getClass();
            c0016a = new C0015a.C0016a(c0016a);
        }
        return new a(c0016a, treeMap);
    }

    public static a a() {
        Thread thread = Looper.getMainLooper().getThread();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        HashMap hashMap = new HashMap(1);
        hashMap.put(thread, stackTrace);
        C0015a c0015a = new C0015a(thread.getName(), stackTrace);
        c0015a.getClass();
        return new a(new C0015a.C0016a(null), hashMap);
    }

    public static Map<Thread, StackTraceElement[]> d() {
        Thread currentThread = Thread.currentThread();
        if (currentThread == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        hashMap.put(currentThread, stackTrace);
        return hashMap;
    }

    public static Map<Thread, StackTraceElement[]> e() {
        Thread thread = Looper.getMainLooper().getThread();
        if (thread == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        hashMap.put(thread, stackTrace);
        return hashMap;
    }
}
