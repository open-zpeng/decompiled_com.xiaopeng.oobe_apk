package com.alibaba.sdk.android.httpdns.probe;
/* loaded from: classes.dex */
public final class d {
    private static IPProbeService a;

    public static synchronized IPProbeService a(b bVar) {
        IPProbeService iPProbeService;
        synchronized (d.class) {
            if (a == null) {
                a = new e();
                a.setIPListUpdateCallback(bVar);
            }
            iPProbeService = a;
        }
        return iPProbeService;
    }
}
