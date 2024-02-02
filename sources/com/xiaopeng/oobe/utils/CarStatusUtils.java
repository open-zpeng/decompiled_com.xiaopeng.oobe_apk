package com.xiaopeng.oobe.utils;

import android.os.SystemProperties;
import com.xiaopeng.lib.utils.LogUtils;
import java.util.HashMap;
/* loaded from: classes.dex */
public class CarStatusUtils {
    public static final String PROPERTY_DSM_CAMERA = "persist.sys.xiaopeng.dsm";
    static final String PROPERTY_NOT_SUPPORT = "0";
    public static final String PROPERTY_OOBE_MOCK_EU = "persist.sys.xiaopeng.OOBE_MOCK_EU";
    static final String PROPERTY_SUPPORT = "1";
    private static final String TAG = "CarStatusUtils";
    protected static HashMap<String, Boolean> sFeatureSupport = new HashMap<>();
    private static Boolean mIsEU = null;

    public static boolean hasFeature(String str) {
        Boolean bool = sFeatureSupport.get(str);
        if (bool == null) {
            String str2 = SystemProperties.get(str, "0");
            LogUtils.i(TAG, "Read prop: " + str + " value: " + str2, false);
            bool = Boolean.valueOf("1".equals(str2));
            sFeatureSupport.put(str, bool);
        }
        return bool.booleanValue();
    }

    public static String getRegion() {
        try {
            String str = SystemProperties.get("ro.xiaopeng.software", "");
            return (str == null || str.isEmpty()) ? str : str.substring(7, 9);
        } catch (Exception e) {
            LogUtils.e(TAG, "can not get getRegion error = " + e);
            return "";
        }
    }

    public static boolean isEURegion() {
        if (mIsEU == null) {
            mIsEU = Boolean.valueOf("EU".equals(getRegion()));
        }
        return mIsEU.booleanValue() || isMockEURegion();
    }

    public static boolean isMockEURegion() {
        return hasFeature(PROPERTY_OOBE_MOCK_EU);
    }
}
