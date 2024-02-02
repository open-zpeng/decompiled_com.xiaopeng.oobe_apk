package com.xiaopeng.oobe.utils;

import android.content.ComponentName;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.OOBEActivity;
/* loaded from: classes.dex */
public class ComponentUtil {
    private static final String TAG = "ComPonentUtil";

    public static void disableOOBEActivity() {
        App.getInstance().getPackageManager().setComponentEnabledSetting(new ComponentName(App.getInstance(), OOBEActivity.class), 2, 1);
        LogUtils.i(TAG, "OOBEActivity component disabled");
    }
}
