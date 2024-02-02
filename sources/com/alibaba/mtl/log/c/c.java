package com.alibaba.mtl.log.c;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;
import com.alibaba.mtl.log.upload.UploadEngine;
import com.xiaopeng.lib.bughunter.anr.UILooperObserver;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: LogStoreMgr.java */
/* loaded from: classes.dex */
public class c {
    private static int A;
    private static c a;
    private static final Object d = new Object();
    private List<com.alibaba.mtl.log.model.a> l = new CopyOnWriteArrayList();
    private Runnable b = new Runnable() { // from class: com.alibaba.mtl.log.c.c.1
        @Override // java.lang.Runnable
        public void run() {
            c.this.E();
        }
    };

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.mtl.log.c.a f59a = new com.alibaba.mtl.log.c.b(com.alibaba.mtl.log.a.getContext());

    private c() {
        UploadEngine.getInstance().start();
        s.a().b(new a());
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    public void a(com.alibaba.mtl.log.model.a aVar) {
        i.a("LogStoreMgr", "[add] :", aVar.ab);
        com.alibaba.mtl.log.b.a.n(aVar.X);
        this.l.add(aVar);
        if (this.l.size() >= 100) {
            s.a().f(1);
            s.a().a(1, this.b, 0L);
        } else if (!s.a().b(1)) {
            s.a().a(1, this.b, UILooperObserver.ANR_TRIGGER_TIME);
        }
        synchronized (d) {
            A++;
            if (A > 5000) {
                A = 0;
                s.a().b(new b());
            }
        }
    }

    public int a(List<com.alibaba.mtl.log.model.a> list) {
        i.a("LogStoreMgr", list);
        return this.f59a.a(list);
    }

    public List<com.alibaba.mtl.log.model.a> a(String str, int i) {
        List<com.alibaba.mtl.log.model.a> a2 = this.f59a.a(str, i);
        i.a("LogStoreMgr", "[get]", a2);
        return a2;
    }

    public synchronized void E() {
        i.a("LogStoreMgr", "[store]");
        ArrayList arrayList = null;
        try {
            synchronized (this.l) {
                if (this.l.size() > 0) {
                    arrayList = new ArrayList(this.l);
                    this.l.clear();
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                this.f59a.mo24a((List<com.alibaba.mtl.log.model.a>) arrayList);
            }
        } catch (Throwable unused) {
        }
    }

    public void clear() {
        i.a("LogStoreMgr", "[clear]");
        this.f59a.clear();
        this.l.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -3);
        this.f59a.c("time", String.valueOf(calendar.getTimeInMillis()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (i > 9000) {
            this.f59a.e((i - 9000) + 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LogStoreMgr.java */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i.a("LogStoreMgr", "CleanLogTask");
            int g = c.this.f59a.g();
            if (g > 9000) {
                c.this.e(g);
            }
        }
    }

    /* compiled from: LogStoreMgr.java */
    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.F();
            int g = c.this.f59a.g();
            if (g > 9000) {
                c.this.e(g);
            }
        }
    }
}
