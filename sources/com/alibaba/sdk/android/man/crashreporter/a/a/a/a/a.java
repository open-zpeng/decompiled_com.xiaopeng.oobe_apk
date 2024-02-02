package com.alibaba.sdk.android.man.crashreporter.a.a.a.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import java.util.Map;
/* loaded from: classes.dex */
public final class a implements com.alibaba.sdk.android.man.crashreporter.a.a.a.b {
    private ComponentName a;

    /* renamed from: a  reason: collision with other field name */
    private Context f121a;

    /* renamed from: a  reason: collision with other field name */
    com.alibaba.sdk.android.man.crashreporter.a.b f122a;

    /* renamed from: a  reason: collision with other field name */
    com.alibaba.sdk.android.man.crashreporter.d.c f123a;

    /* renamed from: a  reason: collision with other field name */
    private Object f124a = new Object();
    private String q;

    public a(Context context, com.alibaba.sdk.android.man.crashreporter.d.c cVar, com.alibaba.sdk.android.man.crashreporter.a.b bVar) {
        this.f123a = null;
        this.f122a = null;
        this.f121a = context;
        a();
        this.f123a = cVar;
        this.f122a = bVar;
    }

    @TargetApi(14)
    private void a() {
        if (Build.VERSION.SDK_INT >= 14) {
            if (this.f121a.getApplicationContext() instanceof Application) {
                ((Application) this.f121a.getApplicationContext()).registerActivityLifecycleCallbacks(new C0014a());
                return;
            }
            return;
        }
        com.alibaba.sdk.android.man.crashreporter.b.a.g(String.format("build version %s not suppert registerActivityLifecycleCallbacks, registerActivityLifecycleCallbacks failed", Integer.valueOf(Build.VERSION.SDK_INT)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(14)
    /* renamed from: com.alibaba.sdk.android.man.crashreporter.a.a.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0014a implements Application.ActivityLifecycleCallbacks {
        C0014a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            a.this.a = activity.getComponentName();
            a.this.q = "onActivityCreated";
            a.this.q = String.format("%s:%s", "onActivityCreated", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityCreated");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = "onActivityDestroyed";
            a.this.q = String.format("%s:%s", "onActivityDestroyed", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityDestroyed");
            synchronized (a.this.f124a) {
                if (a.this.f123a != null) {
                    a.this.a(2);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = "onActivityStopped";
            a.this.q = String.format("%s:%s", "onActivityStopped", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityStopped");
            synchronized (a.this.f124a) {
                if (a.this.f123a != null) {
                    a.this.a(2);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = "onActivityResumed";
            a.this.q = String.format("%s:%s", "onActivityResumed", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityResumed");
            synchronized (a.this.f124a) {
                if (a.this.f123a != null) {
                    a.this.a(1);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = String.format("%s:%s", "onActivityStarted", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivityStarted");
            synchronized (a.this.f124a) {
                if (a.this.f123a != null) {
                    a.this.a(1);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            a.this.a = activity.getComponentName();
            a.this.q = String.format("%s:%s", "onActivityPaused", Long.valueOf(System.currentTimeMillis()));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            a.this.a = activity.getComponentName();
            a.this.q = String.format("%s:%s", "onActivitySaveInstanceState", Long.valueOf(System.currentTimeMillis()));
            com.alibaba.sdk.android.man.crashreporter.b.a.e("onActivitySaveInstanceState");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        try {
            BaseDataContent a = this.f123a.a();
            if (a == null) {
                a = new BaseDataContent();
            }
            if (i == 2) {
                this.f122a.a(MotuCrashReporter.getInstance().getConfigure(), a, 2);
            } else if (i == 1) {
                this.f122a.a(MotuCrashReporter.getInstance().getConfigure(), a, 1);
            }
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("write app status err", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String c() {
        /*
            r4 = this;
            android.content.Context r0 = r4.f121a
            java.lang.String r1 = ""
            if (r0 == 0) goto L34
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch: java.lang.Exception -> L2e
            if (r0 == 0) goto L34
            android.content.ComponentName r2 = r4.a     // Catch: java.lang.Exception -> L2e
            if (r2 == 0) goto L34
            android.content.ComponentName r2 = r4.a     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L27 java.lang.Exception -> L2e
            r3 = 128(0x80, float:1.8E-43)
            android.content.pm.ActivityInfo r0 = r0.getActivityInfo(r2, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L27 java.lang.Exception -> L2e
            if (r0 == 0) goto L34
            android.os.Bundle r2 = r0.metaData     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L27 java.lang.Exception -> L2e
            if (r2 == 0) goto L34
            android.os.Bundle r0 = r0.metaData     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L27 java.lang.Exception -> L2e
            java.lang.String r2 = "bundleLocation"
            java.lang.String r0 = r0.getString(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L27 java.lang.Exception -> L2e
            goto L35
        L27:
            r0 = move-exception
            java.lang.String r2 = "get bundle failed."
            com.alibaba.sdk.android.man.crashreporter.b.a.d(r2, r0)     // Catch: java.lang.Exception -> L2e
            goto L34
        L2e:
            r0 = move-exception
            java.lang.String r2 = "system error, getBundle failed"
            com.alibaba.sdk.android.man.crashreporter.b.a.d(r2, r0)
        L34:
            r0 = r1
        L35:
            if (r0 == 0) goto L38
            goto L39
        L38:
            r0 = r1
        L39:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.man.crashreporter.a.a.a.a.a.c():java.lang.String");
    }

    private String d() {
        ComponentName componentName = this.a;
        return componentName != null ? componentName.getClassName() : "";
    }

    public String b() {
        return !i.a((CharSequence) this.q) ? this.q : "";
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.a.a.c
    public void a(Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.ACTIVITY, d());
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.ACTIVITY_STATUS, b());
        map.put(com.alibaba.sdk.android.man.crashreporter.global.a.BUNDLE, c());
    }
}
