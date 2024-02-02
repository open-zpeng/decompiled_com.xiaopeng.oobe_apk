package com.xiaopeng.oobe.utils;

import android.content.Context;
/* loaded from: classes.dex */
public class ContextUtils {
    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }
}
