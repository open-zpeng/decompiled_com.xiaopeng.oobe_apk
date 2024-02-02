package com.xiaopeng.xui.utils;

import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class XKeyEventUtils {
    private static final int KEYCODE_CAR_ENTER = 1015;

    public static boolean isCancel(int i) {
        return i == 4 || i == 111;
    }

    public static boolean isEnter(int i) {
        return i == 1015 || i == 66;
    }
}
