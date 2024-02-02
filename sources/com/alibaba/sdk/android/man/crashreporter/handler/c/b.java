package com.alibaba.sdk.android.man.crashreporter.handler.c;

import com.alibaba.sdk.android.man.crashreporter.e.h;
/* loaded from: classes.dex */
public class b {
    public static void a(String str, int i, int i2) {
        String str2 = i == 0 ? "CRASH_HANDLE" : i == 1 ? "NATIVE_CRASH_HANDLE" : i == 2 ? "ANR_HANDLE" : "";
        Class<?> cls = null;
        try {
            try {
                cls = Class.forName("com.taobao.statistis.TBS$Ext");
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("watchDog error.", e);
                return;
            }
        } catch (ClassNotFoundException unused) {
        }
        if (cls == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("com.taobao.stdatistis.TBS.Ext is null");
            return;
        }
        h.a((Class) cls, "commitEvent", new Object[]{"", Integer.valueOf(i2), str, str2}, new Class[0]);
        com.alibaba.sdk.android.man.crashreporter.b.a.e("commitEvent call succ");
    }
}
