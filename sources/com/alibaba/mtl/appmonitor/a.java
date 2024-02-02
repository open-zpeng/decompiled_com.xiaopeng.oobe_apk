package com.alibaba.mtl.appmonitor;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;
import com.lzy.okgo.OkGo;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BackgroundTrigger.java */
/* loaded from: classes.dex */
public class a implements Runnable {
    private static boolean j;
    private static boolean l;
    private Application b;
    private boolean k = true;

    @TargetApi(14)
    public static void init(Application application) {
        if (j) {
            return;
        }
        i.a("BackgroundTrigger", "init BackgroundTrigger");
        l = a(application.getApplicationContext());
        a aVar = new a(application);
        if (l) {
            s.a().a(4, aVar, OkGo.DEFAULT_MILLISECONDS);
        } else if (Build.VERSION.SDK_INT >= 14) {
            aVar.getClass();
            application.registerActivityLifecycleCallbacks(new C0007a(aVar));
        }
        j = true;
    }

    public a(Application application) {
        this.b = application;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = 0;
        i.a("BackgroundTrigger", "[bg check]");
        boolean b = com.alibaba.mtl.log.d.b.b(this.b.getApplicationContext());
        if (this.k != b) {
            this.k = b;
            if (b) {
                j.a().j();
                f[] values = f.values();
                int length = values.length;
                while (i < length) {
                    f fVar = values[i];
                    AppMonitorDelegate.setStatisticsInterval(fVar, fVar.c());
                    i++;
                }
                com.alibaba.mtl.log.a.l();
            } else {
                f[] values2 = f.values();
                int length2 = values2.length;
                while (i < length2) {
                    f fVar2 = values2[i];
                    AppMonitorDelegate.setStatisticsInterval(fVar2, fVar2.d());
                    i++;
                }
                AppMonitorDelegate.triggerUpload();
                com.alibaba.mtl.log.a.k();
            }
        }
        if (l) {
            s.a().a(4, this, OkGo.DEFAULT_MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BackgroundTrigger.java */
    @TargetApi(14)
    /* renamed from: com.alibaba.mtl.appmonitor.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0007a implements Application.ActivityLifecycleCallbacks {

        /* renamed from: a  reason: collision with other field name */
        private Runnable f39a;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        C0007a(Runnable runnable) {
            this.f39a = runnable;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            s.a().f(4);
            s.a().a(4, this.f39a, OkGo.DEFAULT_MILLISECONDS);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            s.a().f(4);
            s.a().a(4, this.f39a, OkGo.DEFAULT_MILLISECONDS);
        }
    }

    private static boolean a(Context context) {
        String a = com.alibaba.mtl.log.d.b.a(context);
        i.a("BackgroundTrigger", "[checkRuningProcess]:", a);
        return (TextUtils.isEmpty(a) || a.indexOf(":") == -1) ? false : true;
    }
}
