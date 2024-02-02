package com.alibaba.sdk.android.utils.crashdefend;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.irdeto.android.sdk.O000000o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
/* compiled from: CrashDefendManager.java */
/* loaded from: classes.dex */
public class b {
    private static b b;
    private com.alibaba.sdk.android.utils.c a;

    /* renamed from: a  reason: collision with other field name */
    private c f168a;
    private ExecutorService c;
    private Context context;

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.utils.crashdefend.a f167a = new com.alibaba.sdk.android.utils.crashdefend.a();

    /* renamed from: a  reason: collision with other field name */
    private final List<c> f169a = new ArrayList();
    private Map<String, String> h = new HashMap();

    /* renamed from: a  reason: collision with other field name */
    private final int[] f170a = new int[5];

    public void d(String str, String str2) {
    }

    public static synchronized b a(Context context, com.alibaba.sdk.android.utils.c cVar) {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b(context, cVar);
            }
            bVar = b;
        }
        return bVar;
    }

    private b(Context context, com.alibaba.sdk.android.utils.c cVar) {
        this.c = null;
        this.context = context;
        this.a = cVar;
        this.c = new f().a();
        for (int i = 0; i < 5; i++) {
            this.f170a[i] = (i * 5) + 5;
        }
        this.h.put("sdkId", "utils");
        this.h.put("sdkVersion", O000000o.O00000oo);
        try {
            a();
            b();
        } catch (Exception e) {
            Log.d("UtilsSDK", e.getMessage(), e);
        }
    }

    private void a() {
        if (e.m81a(this.context, this.f167a, this.f169a)) {
            this.f167a.a++;
            return;
        }
        this.f167a.a = 1L;
    }

    private void b() {
        this.f168a = null;
        ArrayList arrayList = new ArrayList();
        synchronized (this.f169a) {
            for (c cVar : this.f169a) {
                if (cVar.crashCount >= cVar.b) {
                    arrayList.add(cVar);
                }
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                c cVar2 = (c) it.next();
                if (cVar2.d < 5) {
                    long j = this.f167a.a - this.f170a[cVar2.d];
                    g.a("UtilsSDK", "after restart " + ((cVar2.a - j) + 1) + " times, sdk will be restore");
                    if (cVar2.a < j) {
                        this.f168a = cVar2;
                        break;
                    }
                } else {
                    Log.i("UtilsSDK", "SDK " + cVar2.f173c + " has been closed");
                }
            }
            if (this.f168a == null) {
                Log.i("UtilsSDK", "NO SDK restore");
            } else {
                this.f168a.d++;
                Log.i("UtilsSDK", this.f168a.f173c + " will restore --- startSerialNumber:" + this.f168a.a + "   crashCount:" + this.f168a.crashCount);
            }
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m80a(c cVar, SDKMessageCallback sDKMessageCallback) {
        c a2;
        if (cVar != null && sDKMessageCallback != null) {
            try {
                if (TextUtils.isEmpty(cVar.f174d) || TextUtils.isEmpty(cVar.f173c) || (a2 = a(cVar, sDKMessageCallback)) == null) {
                    return false;
                }
                boolean m79a = m79a(a2);
                if (a2.crashCount == a2.b) {
                    a(a2.f173c, a2.f174d, a2.crashCount, a2.b);
                }
                a2.crashCount++;
                e.a(this.context, this.f167a, this.f169a);
                if (m79a) {
                    a(a2);
                    Log.i("UtilsSDK", "START:" + a2.f173c + " --- limit:" + a2.b + "  count:" + (a2.crashCount - 1) + "  restore:" + a2.d + "  startSerialNumber:" + a2.a + "  registerSerialNumber:" + a2.f172b);
                } else {
                    sDKMessageCallback.crashDefendMessage(a2.b, a2.crashCount - 1);
                    Log.i("UtilsSDK", "STOP:" + a2.f173c + " --- limit:" + a2.b + "  count:" + (a2.crashCount - 1) + "  restore:" + a2.d + "  startSerialNumber:" + a2.a + "  registerSerialNumber:" + a2.f172b);
                }
                return true;
            } catch (Exception e) {
                Log.d("UtilsSDK", e.getMessage(), e);
            }
        }
        return false;
    }

    private c a(c cVar, SDKMessageCallback sDKMessageCallback) {
        synchronized (this.f169a) {
            c cVar2 = null;
            if (this.f169a != null && this.f169a.size() > 0) {
                Iterator<c> it = this.f169a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    c next = it.next();
                    if (next != null && next.f173c.equals(cVar.f173c)) {
                        if (!next.f174d.equals(cVar.f174d)) {
                            next.f174d = cVar.f174d;
                            next.b = cVar.b;
                            next.c = cVar.c;
                            next.crashCount = 0;
                            next.d = 0;
                        }
                        if (next.f175d) {
                            Log.i("UtilsSDK", "SDK " + cVar.f173c + " has been registered");
                            return null;
                        }
                        next.f175d = true;
                        next.f171a = sDKMessageCallback;
                        next.f172b = this.f167a.a;
                        cVar2 = next;
                    }
                }
            }
            if (cVar2 == null) {
                cVar2 = (c) cVar.clone();
                cVar2.f175d = true;
                cVar2.f171a = sDKMessageCallback;
                cVar2.crashCount = 0;
                cVar2.f172b = this.f167a.a;
                this.f169a.add(cVar2);
            }
            return cVar2;
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    private boolean m79a(c cVar) {
        if (cVar.crashCount < cVar.b) {
            cVar.a = cVar.f172b;
            return true;
        }
        c cVar2 = this.f168a;
        if (cVar2 == null || !cVar2.f173c.equals(cVar.f173c)) {
            return false;
        }
        cVar.crashCount = cVar.b - 1;
        cVar.a = cVar.f172b;
        return true;
    }

    private void a(c cVar) {
        if (cVar == null) {
            return;
        }
        d dVar = new d();
        dVar.b = cVar;
        dVar.e = cVar.c;
        a(dVar);
        if (cVar.f171a != null) {
            cVar.f171a.crashDefendMessage(cVar.b, cVar.crashCount - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(c cVar) {
        if (cVar == null) {
            return;
        }
        if (cVar.d > 0) {
            b(cVar.f173c, cVar.f174d, cVar.d, 5);
        }
        cVar.crashCount = 0;
        cVar.d = 0;
    }

    private void a(d dVar) {
        if (dVar == null || dVar.b == null) {
            return;
        }
        this.c.execute(new a(dVar));
    }

    private void a(String str, String str2, int i, int i2) {
        if (this.a == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.h);
        hashMap.put("crashSdkId", str);
        hashMap.put("crashSdkVer", str2);
        hashMap.put("curCrashCount", String.valueOf(i));
        hashMap.put("crashThreshold", String.valueOf(i2));
        this.a.sendCustomHit("utils_biz_crash", 0L, hashMap);
    }

    private void b(String str, String str2, int i, int i2) {
        if (this.a == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.h);
        hashMap.put("crashSdkId", str);
        hashMap.put("crashSdkVer", str2);
        hashMap.put("recoverCount", String.valueOf(i));
        hashMap.put("recoverThreshold", String.valueOf(i2));
        this.a.sendCustomHit("utils_biz_recover", 0L, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CrashDefendManager.java */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        private d a;

        a(d dVar) {
            this.a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            do {
                try {
                    Thread.sleep(1000L);
                    d dVar = this.a;
                    dVar.e--;
                } catch (InterruptedException unused) {
                    return;
                } catch (Exception e) {
                    Log.d("UtilsSDK", e.getMessage(), e);
                }
            } while (this.a.e > 0);
            if (this.a.e <= 0) {
                b.this.b(this.a.b);
                e.a(b.this.context, b.this.f167a, (List<c>) b.this.f169a);
            }
        }
    }
}
