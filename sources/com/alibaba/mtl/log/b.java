package com.alibaba.mtl.log;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.mtl.log.d.i;
import com.ut.mini.UTAnalytics;
import com.ut.mini.core.appstatus.UTMCAppStatusRegHelper;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import java.io.UnsupportedEncodingException;
/* compiled from: UTMCStatConfig.java */
/* loaded from: classes.dex */
public class b {
    private static b a = new b();
    private Context mContext = null;
    private String H = null;
    private String I = null;
    private String J = null;
    private String K = null;

    /* renamed from: a  reason: collision with other field name */
    private Application f53a = null;
    private String L = null;

    /* renamed from: a  reason: collision with other field name */
    private IUTRequestAuthentication f54a = null;
    private boolean t = false;
    private boolean u = false;

    private b() {
    }

    public static b a() {
        return a;
    }

    public void setAppVersion(String str) {
        this.L = str;
    }

    public String f() {
        return this.L;
    }

    public void turnOnDebug() {
        i.d(true);
    }

    private void c(String str) {
        Context context;
        this.H = str;
        if (!TextUtils.isEmpty(str)) {
            this.I = str;
        }
        if (TextUtils.isEmpty(str) || (context = this.mContext) == null) {
            return;
        }
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("UTCommon", 0).edit();
            edit.putString("_lun", new String(com.alibaba.mtl.log.d.c.encode(str.getBytes("UTF-8"), 2)));
            edit.commit();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void d(String str) {
        Context context;
        this.J = str;
        if (!TextUtils.isEmpty(str)) {
            this.K = str;
        }
        if (TextUtils.isEmpty(str) || (context = this.mContext) == null) {
            return;
        }
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("UTCommon", 0).edit();
            edit.putString("_luid", new String(com.alibaba.mtl.log.d.c.encode(str.getBytes("UTF-8"), 2)));
            edit.commit();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void updateUserAccount(String str, String str2) {
        c(str);
        d(str2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UTAnalytics.getInstance().getDefaultTracker().send(new UTOriginalCustomHitBuilder("UT", 1007, str, str2, null, null).build());
    }

    public void setContext(Context context) {
        if (context != null) {
            this.mContext = context;
            SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("UTCommon", 0);
            String string = sharedPreferences.getString("_lun", "");
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.I = new String(com.alibaba.mtl.log.d.c.decode(string.getBytes(), 2), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            String string2 = sharedPreferences.getString("_luid", "");
            if (!TextUtils.isEmpty(string2)) {
                try {
                    this.K = new String(com.alibaba.mtl.log.d.c.decode(string2.getBytes(), 2), "UTF-8");
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }
        n();
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setAppApplicationInstance(Application application) {
        this.f53a = application;
        n();
    }

    private void n() {
        if (this.t || Build.VERSION.SDK_INT < 14) {
            return;
        }
        try {
            if (a().m22a() != null) {
                UTMCAppStatusRegHelper.registeActivityLifecycleCallbacks(a().m22a());
                this.t = true;
            } else {
                UTMCAppStatusRegHelper.registeActivityLifecycleCallbacks((Application) a().getContext().getApplicationContext());
                this.t = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("UTEngine", "You need set a application instance for UT.");
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public Application m22a() {
        return this.f53a;
    }
}
