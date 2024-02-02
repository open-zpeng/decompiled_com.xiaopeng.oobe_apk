package com.alibaba.sdk.android.man.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class ToolKit {
    private static final String TAG = "MAN_ToolKit";
    private static final String validIp = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    private static Pattern patternIp = Pattern.compile(validIp);
    private static final String validHostnameRegex = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
    private static Pattern patternHost = Pattern.compile(validHostnameRegex);

    public static Object checkNotNull(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new IllegalArgumentException(String.valueOf(obj2));
    }

    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    public static long convertStr2Long(String str) {
        try {
            return Long.valueOf(str).longValue();
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    public static String getMetaDataAppKey(Context context) {
        String str;
        String str2 = "";
        if (context == null) {
            return "";
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                str = "";
            } else {
                Object obj = applicationInfo.metaData.get(MANConfig.MAN_APPKEY_META_DATA_KEY);
                try {
                    if (!(obj instanceof String) && obj != null) {
                        str = obj.toString();
                        MANLog.Logi(TAG, "appKey : " + str);
                    }
                    MANLog.Logi(TAG, "appKey : " + str);
                } catch (PackageManager.NameNotFoundException e) {
                    str2 = str;
                    e = e;
                    e.printStackTrace();
                    return str2;
                }
                str = (String) obj;
            }
            if (str != null) {
                return str;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
        }
        return str2;
    }

    public static String getMetaDataAppSecret(Context context) {
        String str;
        String str2 = "";
        if (context == null) {
            return "";
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                str = "";
            } else {
                Object obj = applicationInfo.metaData.get(MANConfig.MAN_APPSECRET_META_DATA_KEY);
                try {
                    if (!(obj instanceof String) && obj != null) {
                        str = obj.toString();
                        MANLog.Logi(TAG, "appSecret : " + str);
                    }
                    MANLog.Logi(TAG, "appSecret : " + str);
                } catch (PackageManager.NameNotFoundException e) {
                    str2 = str;
                    e = e;
                    e.printStackTrace();
                    return str2;
                }
                str = (String) obj;
            }
            if (str != null) {
                return str;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
        }
        return str2;
    }

    public static String getMetaDataChannel(Context context) {
        String str;
        String str2 = "";
        if (context == null) {
            return "";
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                str = "";
            } else {
                Object obj = applicationInfo.metaData.get(MANConfig.MAN_CHANNEL_META_DATA_KEY);
                try {
                    if (!(obj instanceof String) && obj != null) {
                        str = obj.toString();
                        MANLog.Logi(TAG, "channel : " + str);
                    }
                    MANLog.Logi(TAG, "channel : " + str);
                } catch (PackageManager.NameNotFoundException e) {
                    str2 = str;
                    e = e;
                    e.printStackTrace();
                    return str2;
                }
                str = (String) obj;
            }
            if (str != null) {
                return str;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
        }
        return str2;
    }

    public static String getMetaDataAppVersion(Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            str = "Unknown";
        }
        return isNullOrEmpty(str) ? "-" : str;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isHost(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return patternHost.matcher(str).matches();
    }

    public static boolean isIp(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return patternIp.matcher(str).matches();
    }
}
