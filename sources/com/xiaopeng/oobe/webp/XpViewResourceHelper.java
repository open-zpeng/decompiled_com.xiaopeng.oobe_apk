package com.xiaopeng.oobe.webp;

import android.util.Pair;
import android.util.SparseArray;
/* loaded from: classes.dex */
public class XpViewResourceHelper {
    public static void put(SparseArray<Pair<Boolean, String>> sparseArray, XpViewStatus xpViewStatus, String str, boolean z) {
        sparseArray.put(xpViewStatus.value, new Pair<>(Boolean.valueOf(z), str));
    }

    public static void put(SparseArray<Pair<Boolean, String>> sparseArray, Object[][] objArr) {
        if (objArr == null) {
            return;
        }
        for (Object[] objArr2 : objArr) {
            if (objArr2 != null && objArr2.length > 0) {
                put(sparseArray, (XpViewStatus) objArr2[0], (String) objArr2[1], objArr2.length > 2 ? ((Boolean) objArr2[2]).booleanValue() : true);
            }
        }
    }
}
