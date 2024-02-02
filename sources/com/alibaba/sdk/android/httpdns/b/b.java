package com.alibaba.sdk.android.httpdns.b;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class b {
    private static f a;

    /* renamed from: a  reason: collision with other field name */
    private static com.alibaba.sdk.android.httpdns.c.a f82a;

    /* renamed from: a  reason: collision with other field name */
    private static boolean f83a;

    public static List<e> a() {
        ArrayList arrayList = new ArrayList();
        if (f83a) {
            arrayList.addAll(a.a());
            return arrayList;
        }
        return arrayList;
    }

    public static void a(Context context) {
        if (context != null) {
            f82a.m38b(context);
        }
    }

    public static void a(Context context, com.alibaba.sdk.android.httpdns.c.a aVar) {
        a = new a(context);
        f82a = aVar;
        if (f82a == null) {
            f82a = com.alibaba.sdk.android.httpdns.c.a.a();
        }
    }

    public static void a(e eVar) {
        if (eVar == null) {
            return;
        }
        a.a(eVar);
    }

    public static void a(boolean z) {
        f83a = z;
    }

    /* renamed from: a  reason: collision with other method in class */
    public static boolean m33a() {
        return f83a;
    }

    public static void b(e eVar) {
        if (eVar == null) {
            return;
        }
        a.b(eVar);
    }

    public static String g() {
        return f82a.g();
    }

    public static void init(Context context) {
        a(context, null);
    }
}
