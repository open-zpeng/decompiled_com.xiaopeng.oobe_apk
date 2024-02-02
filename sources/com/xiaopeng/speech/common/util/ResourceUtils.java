package com.xiaopeng.speech.common.util;

import android.app.ActivityThread;
import android.content.Context;
/* loaded from: classes.dex */
public class ResourceUtils {
    public static String getString(int i) {
        return getContext().getString(i);
    }

    public static Context getContext() {
        return ActivityThread.currentApplication();
    }
}
