package com.xiaopeng.oobe.utils;

import android.os.Process;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.App;
/* loaded from: classes.dex */
public class CheckDataUtils {
    private static final String KEY_CHECK_STEP6_DATA_AFTER_OTA = "key_check_step6_data_after_ota";
    private static final String PREF_APP = "xp_oobe_check_data_after_ota";
    private static final String TAG = "CheckDataUtils";

    public static boolean shouldCheckDataAfterOta() {
        boolean isHasCheckDataFlag = isHasCheckDataFlag();
        int oobeFinishStep = SPUtils.getOobeFinishStep();
        boolean z = (isHasCheckDataFlag || oobeFinishStep == -1) ? false : true;
        LogUtils.i(TAG, "step:" + oobeFinishStep + ",isHasCheckDataFlag:" + isHasCheckDataFlag + ",shouldCheck:" + z + ",pid:" + Process.myPid());
        if (z) {
            SPUtils.setOobeFinishStep(6);
        }
        return z;
    }

    private static boolean isHasCheckDataFlag() {
        boolean z = App.getInstance().getSharedPreferences(PREF_APP, 0).getBoolean(KEY_CHECK_STEP6_DATA_AFTER_OTA, false);
        if (!z) {
            App.getInstance().getSharedPreferences(PREF_APP, 0).edit().putBoolean(KEY_CHECK_STEP6_DATA_AFTER_OTA, true).apply();
        }
        return z;
    }
}
