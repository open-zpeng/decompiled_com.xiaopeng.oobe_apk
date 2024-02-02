package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;
import java.net.SocketTimeoutException;
/* loaded from: classes.dex */
public class p {
    private static p a = null;
    private static long e = 0;

    /* renamed from: e  reason: collision with other field name */
    private static boolean f99e = false;
    private static String f = "https://";
    private static String g;

    /* renamed from: e  reason: collision with other field name */
    private int f101e = 0;

    /* renamed from: a  reason: collision with other field name */
    private SharedPreferences f100a = null;

    /* renamed from: f  reason: collision with other field name */
    private long f102f = 0;

    private p() {
    }

    public static p a() {
        if (a == null) {
            synchronized (p.class) {
                if (a == null) {
                    a = new p();
                }
            }
        }
        return a;
    }

    private void a(String str, long j) {
        com.alibaba.sdk.android.httpdns.d.a a2 = com.alibaba.sdk.android.httpdns.d.a.a();
        if (a2 != null) {
            a2.a(str, j, com.alibaba.sdk.android.httpdns.d.b.b());
        }
    }

    private void d() {
        this.f101e = this.f101e < f.c.length + (-1) ? this.f101e + 1 : 0;
    }

    private void d(Throwable th) {
        com.alibaba.sdk.android.httpdns.d.a a2 = com.alibaba.sdk.android.httpdns.d.a.a();
        if (a2 != null) {
            int a3 = com.alibaba.sdk.android.httpdns.d.b.a(th);
            a2.a(f(), String.valueOf(a3), th.getMessage(), com.alibaba.sdk.android.httpdns.d.b.b());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(q qVar, long j) {
        a(f(), j);
        this.f101e = 0;
        HttpDns.switchDnsService(qVar.isEnabled());
        if (a(qVar.c())) {
            i.d("Scheduler center update success");
            this.f102f = System.currentTimeMillis();
            t.g();
        }
    }

    synchronized boolean a(String[] strArr) {
        if (f.a(strArr)) {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
                sb.append(";");
            }
            sb.deleteCharAt(sb.length() - 1);
            if (this.f100a != null) {
                SharedPreferences.Editor edit = this.f100a.edit();
                edit.putString("httpdns_server_ips", sb.toString());
                edit.putLong("schedule_center_last_request_time", System.currentTimeMillis());
                edit.commit();
                return true;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void c() {
        if (System.currentTimeMillis() - this.f102f >= 300000) {
            i.d("update server ips from schedule center.");
            this.f101e = 0;
            c.a().submit(new n(f.c.length - 1));
        } else {
            i.d("update server ips from schedule center too often, give up. ");
            t.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void c(Throwable th) {
        d(th);
        if (th instanceof SocketTimeoutException) {
            d();
            if (this.f101e == 0) {
                this.f102f = System.currentTimeMillis();
                i.f("Scheduler center update failed");
                t.h();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String f() {
        return f + f.c[this.f101e] + "/sc/httpdns_config?account_id=" + f.f90a + "&platform=android&sdk_version=1.2.3";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void init(Context context) {
        if (!f99e) {
            synchronized (p.class) {
                if (!f99e) {
                    if (context != null) {
                        this.f100a = context.getSharedPreferences("httpdns_config_cache", 0);
                    }
                    g = this.f100a.getString("httpdns_server_ips", null);
                    if (g != null) {
                        f.a(g.split(";"));
                    }
                    e = this.f100a.getLong("schedule_center_last_request_time", 0L);
                    if (e == 0 || System.currentTimeMillis() - e >= 86400000) {
                        s.a().b(false);
                        c();
                    }
                    f99e = true;
                }
            }
        }
    }
}
