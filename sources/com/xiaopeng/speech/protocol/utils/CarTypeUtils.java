package com.xiaopeng.speech.protocol.utils;

import android.os.SystemProperties;
import android.text.TextUtils;
import com.xiaopeng.speech.common.util.LogUtils;
/* loaded from: classes2.dex */
public class CarTypeUtils {
    private static final String CAR_COUNTRY_ZH = "ZH";
    private static final String CAR_PLATFORM_Q1 = "Q1";
    private static final String CAR_PLATFORM_Q5 = "Q5";
    private static final String CAR_PLATFORM_Q8 = "Q8";
    private static final String CAR_TYPE_D21EU = "D21EU";
    private static final String CAR_TYPE_D21ZH = "D21ZH";
    private static final String CAR_TYPE_D22EU = "D22EU";
    private static final String CAR_TYPE_D55EU = "D55EU";
    private static final String CAR_TYPE_D55ZH = "D55ZH";
    private static final String CAR_TYPE_E28AEU = "E28AEU";
    private static final String CAR_TYPE_E28EU = "E28EU";
    private static final String CAR_TYPE_E28ZH = "E28ZH";
    private static final String CAR_TYPE_E38EU = "E38EU";
    private static final String CAR_TYPE_E38ZH = "E38ZH";
    private static final String CAR_TYPE_F30ZH = "F30ZH";
    private static final String CAR_TYPE_H93ZH = "H93ZH";
    private static final String TAG = "CarTypeUtils";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean isOverseasCarType() {
        char c;
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isOverseasCarType, carType: " + str);
        switch (str.hashCode()) {
            case 64338291:
                if (str.equals(CAR_TYPE_D21EU)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 64339252:
                if (str.equals(CAR_TYPE_D22EU)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 64431508:
                if (str.equals(CAR_TYPE_D55EU)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 65268539:
                if (str.equals(CAR_TYPE_E28EU)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 65298330:
                if (str.equals(CAR_TYPE_E38EU)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c == 0 || c == 1 || c == 2 || c == 3 || c == 4;
    }

    public static boolean is3DCarType() {
        LogUtils.d(TAG, "is3DCarType");
        return isOverseasCarType() ? isE28AEU() || isE38EU() : isE38ZH() || isE28AZH() || isF30ZH();
    }

    public static boolean isXmart5() {
        LogUtils.d(TAG, "is Xmart5");
        return isH93ZH();
    }

    public static boolean isD21EU() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isD21EU, carType: " + str);
        return CAR_TYPE_D21EU.equalsIgnoreCase(str);
    }

    public static boolean isD22EU() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isD22EU, carType: " + str);
        return CAR_TYPE_D22EU.equalsIgnoreCase(str);
    }

    public static boolean isD55EU() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isD55EU, carType: " + str);
        return CAR_TYPE_D55EU.equalsIgnoreCase(str);
    }

    public static boolean isE28EU() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isE28EU, carType: " + str);
        return CAR_TYPE_E28EU.equalsIgnoreCase(str);
    }

    public static boolean isE38EU() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isE38EU, carType: " + str);
        return CAR_TYPE_E38EU.equalsIgnoreCase(str);
    }

    public static boolean isE38ZH() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isE38ZH, carType: " + str);
        return CAR_TYPE_E38ZH.equalsIgnoreCase(str);
    }

    public static boolean isD55ZH() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isD55ZH, carType: " + str);
        return CAR_TYPE_D55ZH.equalsIgnoreCase(str);
    }

    public static boolean isE28AZH() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        String carPlatform = getCarPlatform();
        LogUtils.d(TAG, "isE28AZH, carType: " + str + ", carPlatform: " + carPlatform);
        return CAR_TYPE_E28ZH.equalsIgnoreCase(str) && CAR_PLATFORM_Q8.equalsIgnoreCase(carPlatform);
    }

    public static boolean isE28ZH() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        String carPlatform = getCarPlatform();
        LogUtils.d(TAG, "isE28ZH, carType: " + str + ", carPlatform: " + carPlatform);
        return CAR_TYPE_E28ZH.equalsIgnoreCase(str) && CAR_PLATFORM_Q1.equalsIgnoreCase(carPlatform);
    }

    public static boolean isF30ZH() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isF30ZH, carType: " + str);
        return CAR_TYPE_F30ZH.equalsIgnoreCase(str);
    }

    public static boolean isH93ZH() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        LogUtils.d(TAG, "isH93ZH, carType: " + str);
        return CAR_TYPE_H93ZH.equalsIgnoreCase(str);
    }

    public static boolean isE28AEU() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        String carPlatform = getCarPlatform();
        LogUtils.d(TAG, "isE28AEU, carType: " + str + ", carPlatform: " + carPlatform);
        return CAR_TYPE_E28EU.equalsIgnoreCase(str) && CAR_PLATFORM_Q8.equalsIgnoreCase(carPlatform);
    }

    public static boolean isD21ZH() {
        String str = getHardwareCarType() + getVersionInCountryCode();
        String carPlatform = getCarPlatform();
        LogUtils.d(TAG, "isD21ZH, carType: " + str + ", carPlatform: " + carPlatform);
        return CAR_TYPE_D21ZH.equalsIgnoreCase(str) || (CAR_PLATFORM_Q5.equalsIgnoreCase(carPlatform) && CAR_COUNTRY_ZH.equalsIgnoreCase(getVersionInCountryCode()));
    }

    private static String getVersionInCountryCode() {
        String str = SystemProperties.get("ro.xiaopeng.software", "");
        return !TextUtils.isEmpty(str) ? str.substring(7, 9) : str;
    }

    private static String getHardwareCarType() {
        String str = SystemProperties.get("ro.xiaopeng.software", "");
        return !TextUtils.isEmpty(str) ? str.substring(9, 12) : str;
    }

    private static String getCarPlatform() {
        String str = SystemProperties.get("ro.xiaopeng.software", "");
        return !TextUtils.isEmpty(str) ? str.substring(5, 7) : str;
    }
}
