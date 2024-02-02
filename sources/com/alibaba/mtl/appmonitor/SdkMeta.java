package com.alibaba.mtl.appmonitor;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.mtl.log.d.i;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class SdkMeta {
    public static final String SDK_VERSION = "2.6.4.10_for_bc";
    private static final Map<String, String> d = new HashMap();

    public static Map<String, String> getSDKMetaData() {
        com.alibaba.mtl.log.a.getContext();
        if (!d.containsKey("sdk-version")) {
            d.put("sdk-version", SDK_VERSION);
        }
        return d;
    }

    static {
        d.put("sdk-version", SDK_VERSION);
    }

    public static void setExtra(Map<String, String> map) {
        if (map != null) {
            d.putAll(map);
        }
    }

    public static String getString(Context context, String str) {
        if (context == null) {
            return null;
        }
        int i = 0;
        try {
            i = context.getResources().getIdentifier(str, TypedValues.Custom.S_STRING, context.getPackageName());
        } catch (Throwable th) {
            i.a("SdkMeta", "getString Id error", th);
        }
        if (i != 0) {
            return context.getString(i);
        }
        return null;
    }
}
