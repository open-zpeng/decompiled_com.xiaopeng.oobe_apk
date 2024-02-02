package com.alibaba.sdk.android.httpdns.probe;

import androidx.annotation.NonNull;
import com.alibaba.sdk.android.httpdns.i;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
/* loaded from: classes.dex */
class g implements Runnable {
    private ConcurrentHashMap<String, Long> a;

    /* renamed from: a  reason: collision with other field name */
    private CountDownLatch f108a;
    private String k;
    private int port;

    public g(@NonNull String str, int i, CountDownLatch countDownLatch, ConcurrentHashMap<String, Long> concurrentHashMap) {
        this.f108a = null;
        this.k = str;
        this.port = i;
        this.f108a = countDownLatch;
        this.a = concurrentHashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long a(java.lang.String r8, int r9) {
        /*
            r7 = this;
            java.lang.String r0 = "socket close failed:"
            long r1 = java.lang.System.currentTimeMillis()
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r5 = 0
            java.net.Socket r6 = new java.net.Socket     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            r6.<init>()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.net.InetSocketAddress r5 = new java.net.InetSocketAddress     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3c
            r5.<init>(r8, r9)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3c
            r8 = 5000(0x1388, float:7.006E-42)
            r6.connect(r5, r8)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3c
            long r8 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3c
            r6.close()     // Catch: java.io.IOException -> L21
            goto L78
        L21:
            r5 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r0)
            java.lang.String r0 = r5.toString()
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            com.alibaba.sdk.android.httpdns.i.f(r0)
            goto L78
        L39:
            r8 = move-exception
            r5 = r6
            goto L7f
        L3c:
            r8 = move-exception
            r5 = r6
            goto L42
        L3f:
            r8 = move-exception
            goto L7f
        L41:
            r8 = move-exception
        L42:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r9.<init>()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r6 = "connect failed:"
            r9.append(r6)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L3f
            r9.append(r8)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r8 = r9.toString()     // Catch: java.lang.Throwable -> L3f
            com.alibaba.sdk.android.httpdns.i.f(r8)     // Catch: java.lang.Throwable -> L3f
            if (r5 == 0) goto L77
            r5.close()     // Catch: java.io.IOException -> L60
            goto L77
        L60:
            r8 = move-exception
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r0)
            java.lang.String r8 = r8.toString()
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            com.alibaba.sdk.android.httpdns.i.f(r8)
        L77:
            r8 = r3
        L78:
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 != 0) goto L7d
            return r3
        L7d:
            long r8 = r8 - r1
            return r8
        L7f:
            if (r5 == 0) goto L9c
            r5.close()     // Catch: java.io.IOException -> L85
            goto L9c
        L85:
            r9 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r9 = r9.toString()
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            com.alibaba.sdk.android.httpdns.i.f(r9)
        L9c:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.probe.g.a(java.lang.String, int):long");
    }

    private boolean a(int i) {
        return i >= 1 && i <= 65535;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.k == null || !a(this.port)) {
            i.f("invalid params, give up");
        } else {
            long a = a(this.k, this.port);
            i.d("connect cost for ip:" + this.k + " is " + a);
            ConcurrentHashMap<String, Long> concurrentHashMap = this.a;
            if (concurrentHashMap != null) {
                concurrentHashMap.put(this.k, Long.valueOf(a));
            }
        }
        CountDownLatch countDownLatch = this.f108a;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
}
