package com.alibaba.sdk.android.man.crashreporter.handler;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.IUTCrashCaughtListener;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.alibaba.sdk.android.man.crashreporter.d.c;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import com.alibaba.sdk.android.man.crashreporter.handler.nativeCrashHandler.NativeCrashHandler;
import com.alibaba.sdk.android.man.util.UTWrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class a implements b {
    private static int w = 61005;
    private Context a = null;

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.man.crashreporter.a.b f143a = null;
    private c b = null;

    /* renamed from: a  reason: collision with other field name */
    private c f145a = null;

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.man.crashreporter.c.b f144a = null;

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.man.crashreporter.handler.a.a f146a = null;
    private NativeCrashHandler nativeCrashHandler = null;

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.man.crashreporter.handler.b.a f147a = null;
    private AtomicBoolean crashing = new AtomicBoolean(false);

    @Override // com.alibaba.sdk.android.man.crashreporter.handler.b
    public boolean a(Context context, ReporterConfigure reporterConfigure, com.alibaba.sdk.android.man.crashreporter.c cVar) {
        String[] split;
        try {
            if (context != null) {
                this.a = context;
                this.f143a = new com.alibaba.sdk.android.man.crashreporter.a.a();
                this.b = new com.alibaba.sdk.android.man.crashreporter.d.b();
                this.f145a = new com.alibaba.sdk.android.man.crashreporter.d.a(context, cVar);
                this.f144a = new com.alibaba.sdk.android.man.crashreporter.c.a();
                if (this.f143a.a(context, reporterConfigure, cVar, this.b, this.f145a) && this.b.c(context) && this.f144a.a(context, this.f143a, this.b, this.f145a)) {
                    if (reporterConfigure.enableCatchUncaughtException) {
                        this.f146a = new com.alibaba.sdk.android.man.crashreporter.handler.a.a(this.crashing, this);
                    }
                    if (reporterConfigure.enableCatchNativeException) {
                        this.nativeCrashHandler = NativeCrashHandler.init(context);
                        if (this.nativeCrashHandler.regist(this.crashing, this, reporterConfigure.enableDebug, cVar)) {
                            com.alibaba.sdk.android.man.crashreporter.b.a.e("native crash handler regist succ!");
                        }
                    }
                    if (cVar.appVersion != null && (split = cVar.appVersion.split("\\.")) != null && split.length >= 4) {
                        if (reporterConfigure.enableCatchANRException) {
                            this.f147a = new com.alibaba.sdk.android.man.crashreporter.handler.b.a(context, this, this.crashing, reporterConfigure.enabeANRTimeoutInterval, reporterConfigure.enableANRMainThreadOnly);
                        }
                        if (reporterConfigure.enableDebug) {
                            com.alibaba.sdk.android.man.crashreporter.handler.c.a.a("isDebug", cVar.appVersion);
                        }
                    }
                    this.f144a.b(this.f143a.a(0, 0, 0, 0));
                    return true;
                }
                return false;
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.e("init handler failure!");
            return false;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("init handler err", e);
            return false;
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.handler.b
    public boolean c() {
        com.alibaba.sdk.android.man.crashreporter.handler.a.a aVar = this.f146a;
        if (aVar != null) {
            aVar.b();
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Java crash handler is removed success.");
        } else {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Java crash handler is null.");
        }
        NativeCrashHandler nativeCrashHandler = this.nativeCrashHandler;
        if (nativeCrashHandler != null) {
            if (!nativeCrashHandler.removeNativeCrashHandler()) {
                return false;
            }
        } else {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Native crash handler is null.");
        }
        com.alibaba.sdk.android.man.crashreporter.handler.b.a aVar2 = this.f147a;
        if (aVar2 != null) {
            aVar2.c();
            return true;
        }
        com.alibaba.sdk.android.man.crashreporter.b.a.e("Stuck crash handler is null.");
        return true;
    }

    public void a(Throwable th, Thread thread, String str, String str2) {
        try {
            if (this.f143a == null || this.b == null || this.a == null) {
                return;
            }
            com.alibaba.sdk.android.man.crashreporter.handler.c.b.a("TBCRASH_REPORTER_SDK", 0, w);
            com.alibaba.sdk.android.man.crashreporter.b.a.e("crash handler start.");
            Map a = a(th, thread);
            this.f143a.a(str, str2, b(str2), a);
            UTWrapper.commitCrashEvent();
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.a("handleJavaCrash err!", e);
        }
    }

    public void a(String str, String str2, String str3) {
        try {
            if (this.f143a == null || this.b == null || this.a == null) {
                return;
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.e("native crash handler start.");
            this.f143a.b(str2, str, str3, a(null, null));
            UTWrapper.commitCrashEvent();
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("handle native stackTrace failure", e);
        }
    }

    /* renamed from: b  reason: collision with other method in class */
    public void m71b(String str) {
        try {
            if (this.f143a == null || this.b == null || this.a == null) {
                return;
            }
            com.alibaba.sdk.android.man.crashreporter.handler.c.b.a("TBCRASH_REPORTER_SDK", 2, w);
            com.alibaba.sdk.android.man.crashreporter.b.a.e("ANR handler start.");
            CrashReportDataForSave a = this.f143a.a(str);
            if (a != null && !a(a.content)) {
                this.f144a.a(a, this.f143a.a(0, 0, 0, 0), 2);
            }
            UTWrapper.commitCrashEvent();
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("handle stuck failure", e);
        }
    }

    private String b(String str) {
        try {
            return !i.b(str) ? str.replaceAll("\n", "++") : str;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getMessageToUTArgs err.", e);
            return str;
        }
    }

    private boolean a(String str) {
        List mySenderListenerList = MotuCrashReporter.getInstance().getMySenderListenerList();
        if (mySenderListenerList != null) {
            try {
                if (mySenderListenerList.size() != 0) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("start call sender listener!");
                    for (int i = 0; i < mySenderListenerList.size(); i++) {
                        ((com.alibaba.sdk.android.man.crashreporter.a) mySenderListenerList.get(i)).a(str);
                    }
                    return true;
                }
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("call sender listener err", e);
            }
        }
        return false;
    }

    private Map a(Throwable th, Thread thread) {
        List myListenerList = MotuCrashReporter.getInstance().getMyListenerList();
        String strExtraInfo = MotuCrashReporter.getInstance().getStrExtraInfo();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < myListenerList.size(); i++) {
            try {
                IUTCrashCaughtListener iUTCrashCaughtListener = (IUTCrashCaughtListener) myListenerList.get(i);
                com.alibaba.sdk.android.man.crashreporter.b.a.b("ext listener is:", iUTCrashCaughtListener.toString());
                Map<String, Object> onCrashCaught = iUTCrashCaughtListener.onCrashCaught(thread, th);
                if (onCrashCaught != null) {
                    for (Map.Entry<String, Object> entry : onCrashCaught.entrySet()) {
                        hashMap.put(entry.getKey(), entry.getValue().toString());
                    }
                }
            } catch (Throwable th2) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("Listener's extraMsg store error.", th2);
                return null;
            }
        }
        if (strExtraInfo != null) {
            hashMap.put("exaInfo", strExtraInfo);
        }
        if (hashMap.size() > 0) {
            return hashMap;
        }
        return null;
    }
}
