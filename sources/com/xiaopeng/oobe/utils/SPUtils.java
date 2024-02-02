package com.xiaopeng.oobe.utils;

import android.content.Context;
import android.util.Log;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.Constants;
/* loaded from: classes.dex */
public class SPUtils {
    private static final String KEY_OOBE_FINISH_STEP = "oobe_finish_step";
    private static final String KEY_OOBE_LAN_SELECTED = "oobe_lan_selected";
    private static final String KEY_OOBE_LOGIN_QRCODE = "key_oobe_login_qrcode";
    private static final String KEY_OOBE_REG_SELECTED = "oobe_reg_selected";
    private static final String PREF_APP = "pref_xp_e28_oobe";
    private static final String TAG = "SPUtils";
    private static Context mContext;

    private SPUtils() {
        throw new UnsupportedOperationException("Should not create getInstance of Util class. Please use as static..");
    }

    public static void init(Context context) {
        mContext = context;
        Log.i(TAG, Constants.Unity.INIT);
    }

    public static String getLoginQrcode() {
        String stringData = getStringData(KEY_OOBE_LOGIN_QRCODE);
        LogUtils.i(TAG, "getLoginQrcode: " + stringData);
        return stringData;
    }

    public static void setLoginQrcode(String str) {
        LogUtils.i(TAG, "setLoginQrcode: " + str);
        saveData(KEY_OOBE_LOGIN_QRCODE, str);
    }

    public static void setLanSelected(String str) {
        LogUtils.i(TAG, "setLanSelected: " + str);
        saveData(KEY_OOBE_LAN_SELECTED, str);
    }

    public static String getLanSelected() {
        String stringData = getStringData(KEY_OOBE_LAN_SELECTED);
        LogUtils.i(TAG, "getLanSelected: " + stringData);
        return stringData;
    }

    public static void setRegSelected(String str) {
        LogUtils.i(TAG, "setRegSelected: " + str);
        saveData(KEY_OOBE_REG_SELECTED, str);
    }

    public static String getRegSelected() {
        String stringData = getStringData(KEY_OOBE_REG_SELECTED);
        LogUtils.i(TAG, "getRegSelected: " + stringData);
        return stringData;
    }

    public static void setOobeFinishStep(int i) {
        LogUtils.i(TAG, "setOobeFinishStep:" + i);
        saveData(KEY_OOBE_FINISH_STEP, i);
    }

    public static int getOobeFinishStep() {
        int intData = getIntData(KEY_OOBE_FINISH_STEP);
        LogUtils.i(TAG, "getOobeFinishStep:" + intData);
        return intData;
    }

    public static int getIntData(String str) {
        return mContext.getSharedPreferences(PREF_APP, 0).getInt(str, -1);
    }

    public static String getStringData(String str) {
        return mContext.getSharedPreferences(PREF_APP, 0).getString(str, null);
    }

    public static void saveData(String str, String str2) {
        mContext.getSharedPreferences(PREF_APP, 0).edit().putString(str, str2).apply();
    }

    public static void saveData(String str, int i) {
        mContext.getSharedPreferences(PREF_APP, 0).edit().putInt(str, i).commit();
    }
}
