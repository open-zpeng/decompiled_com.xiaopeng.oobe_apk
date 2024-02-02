package com.alibaba.sdk.android.httpdns;

import com.alibaba.sdk.android.httpdns.probe.IPProbeItem;
import com.alibaba.sdk.android.httpdns.probe.IPProbeService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d {
    private static d a = new d();

    /* renamed from: a  reason: collision with other field name */
    private static IPProbeService f86a = com.alibaba.sdk.android.httpdns.probe.d.a(new com.alibaba.sdk.android.httpdns.probe.b() { // from class: com.alibaba.sdk.android.httpdns.d.1
        @Override // com.alibaba.sdk.android.httpdns.probe.b
        public void a(String str, String[] strArr) {
            e eVar;
            if (str == null || strArr == null || strArr.length == 0 || (eVar = (e) d.f87a.get(str)) == null) {
                return;
            }
            e eVar2 = new e(str, strArr, eVar.a(), eVar.b());
            d.f87a.put(str, eVar2);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < eVar2.m50a().length; i++) {
                sb.append(eVar2.m50a()[i] + ",");
            }
            i.f("optimized host:" + str + ", ip:" + sb.toString());
        }
    });

    /* renamed from: a  reason: collision with other field name */
    private static ConcurrentMap<String, e> f87a;

    /* renamed from: a  reason: collision with other field name */
    private static ConcurrentSkipListSet<String> f88a;

    private d() {
        f87a = new ConcurrentHashMap();
        f88a = new ConcurrentSkipListSet<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d a() {
        return a;
    }

    private IPProbeItem a(String str) {
        List<IPProbeItem> list = f.f91a;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (str.equals(list.get(i).getHostName())) {
                    return list.get(i);
                }
            }
            return null;
        }
        return null;
    }

    private boolean a(com.alibaba.sdk.android.httpdns.b.e eVar) {
        return (System.currentTimeMillis() / 1000) - com.alibaba.sdk.android.httpdns.b.c.a(eVar.j) > 604800;
    }

    private boolean a(String str, e eVar) {
        IPProbeItem a2;
        if (eVar == null || eVar.m50a() == null || eVar.m50a().length <= 1 || f86a == null || (a2 = a(str)) == null) {
            return false;
        }
        if (f86a.getProbeStatus(str) == IPProbeService.a.PROBING) {
            f86a.stopIPProbeTask(str);
        }
        i.f("START PROBE");
        f86a.launchIPProbeTask(str, a2.getPort(), eVar.m50a());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        List<com.alibaba.sdk.android.httpdns.b.e> a2 = com.alibaba.sdk.android.httpdns.b.b.a();
        String g = com.alibaba.sdk.android.httpdns.b.b.g();
        for (com.alibaba.sdk.android.httpdns.b.e eVar : a2) {
            if (a(eVar)) {
                com.alibaba.sdk.android.httpdns.b.b.b(eVar);
            } else if (g.equals(eVar.i)) {
                eVar.j = String.valueOf(System.currentTimeMillis() / 1000);
                e eVar2 = new e(eVar);
                f87a.put(eVar.h, eVar2);
                com.alibaba.sdk.android.httpdns.b.b.b(eVar);
                a(eVar.h, eVar2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public int m41a() {
        return f87a.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public e m42a(String str) {
        return f87a.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public ArrayList<String> m43a() {
        return new ArrayList<>(f87a.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public void m44a() {
        if (com.alibaba.sdk.android.httpdns.b.b.m33a()) {
            c.a().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.2
                @Override // java.lang.Runnable
                public void run() {
                    d.this.b();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public void m45a(String str) {
        f88a.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public void m46a(String str, e eVar) {
        f87a.put(str, eVar);
        if (com.alibaba.sdk.android.httpdns.b.b.m33a()) {
            com.alibaba.sdk.android.httpdns.b.e m49a = eVar.m49a();
            if (m49a.a == null || m49a.a.size() <= 0) {
                com.alibaba.sdk.android.httpdns.b.b.b(m49a);
            } else {
                com.alibaba.sdk.android.httpdns.b.b.a(m49a);
            }
        }
        a(str, eVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public boolean m47a(String str) {
        return f88a.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        f88a.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        f87a.clear();
        f88a.clear();
    }
}
