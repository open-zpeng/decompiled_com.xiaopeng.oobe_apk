package com.xiaopeng.privacymanager;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.lib.apirouter.ApiRouter;
import com.xiaopeng.lib.apirouter.server.IServicePublisher;
import com.xiaopeng.privacymanager.feature.BaseFeatureOption;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class PrivacySettings implements IServicePublisher {
    private static final String TAG = "[PrivacySettings] ";
    public static final String SETTING_SERVER_AUTHORITY = BaseFeatureOption.getInstance().getPrivacySettingServer();
    public static final String AUTHORITY = BaseFeatureOption.getInstance().getContentProviderAuthority();
    public static final String PROTOCOL_URI = "content://" + AUTHORITY + "/protocol";

    public static Uri getProtocolUri() {
        return Uri.parse(PROTOCOL_URI);
    }

    public static void setAgreed(Context context, int i, boolean z) {
        String packageName = context.getPackageName();
        int[] subTypes = ProtocolType.getSubTypes(i);
        if (subTypes != null) {
            try {
                for (int i2 : subTypes) {
                    Uri build = new Uri.Builder().authority(SETTING_SERVER_AUTHORITY).path("setAgreed").appendQueryParameter("type", String.valueOf(i2)).appendQueryParameter("agreed", String.valueOf(z)).build();
                    try {
                        Log.i(TAG, "Calling package: " + packageName + ", uri:" + build);
                        ApiRouter.route(build);
                    } catch (RemoteException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage());
                Log.e(TAG, "maybe no PrivacyService apk in ROM");
            }
        }
    }

    public static boolean isAgreed(Context context, int i) {
        String packageName = context.getPackageName();
        int[] subTypes = ProtocolType.getSubTypes(i);
        if (subTypes == null || subTypes.length <= 0) {
            return false;
        }
        boolean z = true;
        for (int i2 : subTypes) {
            Uri build = new Uri.Builder().authority(SETTING_SERVER_AUTHORITY).path("isAgreed").appendQueryParameter("type", String.valueOf(i2)).build();
            try {
                boolean booleanValue = ((Boolean) ApiRouter.route(build)).booleanValue();
                Log.i(TAG, "Calling package: " + packageName + ", uri:" + build + ", isTypeAgreed:" + booleanValue);
                z &= booleanValue;
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return z;
    }

    public static void setEnabled(Context context, int i, boolean z) {
        String packageName = context.getPackageName();
        int[] subTypes = ProtocolType.getSubTypes(i);
        if (subTypes != null) {
            try {
                for (int i2 : subTypes) {
                    Uri build = new Uri.Builder().authority(SETTING_SERVER_AUTHORITY).path("setEnabled").appendQueryParameter("type", String.valueOf(i2)).appendQueryParameter("enabled", String.valueOf(z)).build();
                    try {
                        Log.i(TAG, "Calling package: " + packageName + ", uri:" + build);
                        ApiRouter.route(build);
                    } catch (RemoteException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage());
                Log.e(TAG, "maybe no PrivacyService apk in ROM");
            }
        }
    }

    public static boolean isEnabled(Context context, int i) {
        String packageName = context.getPackageName();
        int[] subTypes = ProtocolType.getSubTypes(i);
        if (subTypes == null || subTypes.length <= 0) {
            return false;
        }
        boolean z = true;
        for (int i2 : subTypes) {
            Uri build = new Uri.Builder().authority(SETTING_SERVER_AUTHORITY).path("isEnabled").appendQueryParameter("type", String.valueOf(i2)).build();
            try {
                boolean booleanValue = ((Boolean) ApiRouter.route(build)).booleanValue();
                Log.i(TAG, "Calling package: " + packageName + ", uri:" + build + ", isTypeEnabled:" + booleanValue);
                z &= booleanValue;
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return z;
    }

    public static boolean isNeedReconfirm(Context context, int i) {
        String packageName = context.getPackageName();
        int[] subTypes = ProtocolType.getSubTypes(i);
        if (subTypes == null || subTypes.length <= 0) {
            return false;
        }
        boolean z = true;
        for (int i2 : subTypes) {
            Uri build = new Uri.Builder().authority(SETTING_SERVER_AUTHORITY).path("isNeedReconfirm").appendQueryParameter("type", String.valueOf(i2)).build();
            try {
                boolean booleanValue = ((Boolean) ApiRouter.route(build)).booleanValue();
                Log.i(TAG, "Calling package: " + packageName + ", uri:" + build + ", isTypeNeedReconfirm:" + booleanValue);
                z &= booleanValue;
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return z;
    }

    public static void setNeedReconfirm(Context context, int i, boolean z) {
        String packageName = context.getPackageName();
        int[] subTypes = ProtocolType.getSubTypes(i);
        if (subTypes != null) {
            try {
                for (int i2 : subTypes) {
                    Uri build = new Uri.Builder().authority(SETTING_SERVER_AUTHORITY).path("setNeedReconfirm").appendQueryParameter("type", String.valueOf(i2)).appendQueryParameter("needReconfirm", String.valueOf(z)).build();
                    try {
                        Log.i(TAG, "Calling package: " + packageName + ", uri:" + build);
                        ApiRouter.route(build);
                    } catch (RemoteException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage());
                Log.e(TAG, "maybe no PrivacyService apk in ROM");
            }
        }
    }

    public static boolean hasUnsignedPrivacy(Context context) {
        List<Integer> unsignedPrivacy = getUnsignedPrivacy(context);
        return unsignedPrivacy != null && unsignedPrivacy.size() > 0;
    }

    public static boolean hasUnsignedPrivacy(Context context, int i) {
        List<Integer> unsignedPrivacy = getUnsignedPrivacy(context);
        return unsignedPrivacy != null && unsignedPrivacy.contains(Integer.valueOf(i));
    }

    public static List<Integer> getUnsignedPrivacy(Context context) {
        List<Integer> list;
        String packageName = context.getPackageName();
        ArrayList arrayList = new ArrayList();
        Uri build = new Uri.Builder().authority(SETTING_SERVER_AUTHORITY).path("getUnsignedPrivacy").build();
        try {
            list = (List) GsonUtil.fromJson((String) ApiRouter.route(build), new TypeToken<List<Integer>>() { // from class: com.xiaopeng.privacymanager.PrivacySettings.1
            });
        } catch (RemoteException e) {
            e = e;
            list = arrayList;
        }
        try {
            Log.i(TAG, "Calling package: " + packageName + ", uri:" + build + ", list:" + list);
        } catch (RemoteException e2) {
            e = e2;
            Log.e(TAG, e.getMessage());
            return list;
        }
        return list;
    }

    public static List<Integer> getUnsignedPrivacyForPowerOn(Context context) {
        List<Integer> list;
        String packageName = context.getPackageName();
        ArrayList arrayList = new ArrayList();
        Uri build = new Uri.Builder().authority(SETTING_SERVER_AUTHORITY).path("getUnsignedPrivacyForPowerOn").build();
        try {
            list = (List) GsonUtil.fromJson((String) ApiRouter.route(build), new TypeToken<List<Integer>>() { // from class: com.xiaopeng.privacymanager.PrivacySettings.2
            });
        } catch (RemoteException e) {
            e = e;
            list = arrayList;
        }
        try {
            Log.i(TAG, "Calling package: " + packageName + ", uri:" + build + ", list:" + list);
        } catch (RemoteException e2) {
            e = e2;
            Log.e(TAG, e.getMessage());
            return list;
        }
        return list;
    }

    public static String getProtocolName(Context context, int i) {
        String packageName = context.getPackageName();
        Uri build = new Uri.Builder().authority(SETTING_SERVER_AUTHORITY).path("getProtocolName").appendQueryParameter("type", String.valueOf(i)).build();
        try {
            String str = (String) ApiRouter.route(build);
            Log.i(TAG, "Calling package: " + packageName + ", uri:" + build + ", getProtocolName: " + i + ", result name: " + str);
            return str;
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
