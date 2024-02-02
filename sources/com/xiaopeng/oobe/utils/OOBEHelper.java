package com.xiaopeng.oobe.utils;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.backup.BackupManager;
import android.car.Car;
import android.car.XpCarFeatures;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.speech.speechwidget.ListWidget;
import com.xiaopeng.xui.utils.XuiUtils;
import java.util.Locale;
/* loaded from: classes.dex */
public class OOBEHelper {
    private static final int SECONDARY_NAVIGATION = 2060;
    private static final String TAG = "OOBEHelper";
    private static Boolean mIsEU;
    private AudioManager mAudioManager;

    public static boolean isSupportLanSelect() {
        return false;
    }

    public static boolean isSupportRegSelect() {
        return false;
    }

    public static boolean isSupportSpeechUserAdPlan() {
        return false;
    }

    public static boolean isSupportXP() {
        return false;
    }

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        static OOBEHelper sInstance = new OOBEHelper();

        private SingletonHolder() {
        }
    }

    private OOBEHelper() {
        this.mAudioManager = (AudioManager) App.getInstance().getSystemService(ListWidget.EXTRA_TYPE_AUDIO);
    }

    public void setMicrophoneMute(boolean z) {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            Log.i(TAG, "mAudioManager is null");
            return;
        }
        audioManager.setMicrophoneMute(z);
        Log.i(TAG, "setMicrophoneMute mute:" + z);
    }

    public static OOBEHelper getInstance() {
        return SingletonHolder.sInstance;
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
        return mIsEU.booleanValue();
    }

    public static boolean isLandScreen() {
        return XuiUtils.getDisplayWidth() > XuiUtils.getDisplayHeight();
    }

    public static void changeSystemLocale(Locale locale) {
        if (locale != null) {
            try {
                IActivityManager service = ActivityManager.getService();
                Configuration configuration = service.getConfiguration();
                configuration.setLocale(locale);
                configuration.userSetLocale = true;
                service.updatePersistentConfiguration(configuration);
                BackupManager.dataChanged("com.android.providers.settings");
                Log.d(TAG, "changelocale success!");
            } catch (Exception e) {
                Log.d(TAG, "changelocale error-->", e);
            }
        }
    }

    public static boolean isNotSupportVuiAuthorization() {
        return isSupportOS5();
    }

    public static boolean isSupportOS5() {
        return "QB".equals(getXpCduType());
    }

    public static boolean isSupportXpu() {
        return XpCarFeatures.hasXPU() == 1;
    }

    public static boolean isSupportDsmCamera() {
        boolean z = false;
        if (CarStatusUtils.isEURegion()) {
            return false;
        }
        String xpCduType = getXpCduType();
        if (xpCduType.hashCode() != 2565 || !xpCduType.equals("Q6")) {
            z = true;
        }
        if (!z) {
            return CarStatusUtils.hasFeature(CarStatusUtils.PROPERTY_DSM_CAMERA);
        }
        return isSupportXpu();
    }

    public static boolean isSupportDsmTiredDistraction() {
        if (isSupportOS5()) {
            return isSupportDsmCamera();
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean isVui2Version() {
        char c;
        String xpCduType = getXpCduType();
        int hashCode = xpCduType.hashCode();
        if (hashCode != 2577) {
            switch (hashCode) {
                case 2560:
                    if (xpCduType.equals("Q1")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 2561:
                    if (xpCduType.equals("Q2")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 2562:
                    if (xpCduType.equals("Q3")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    switch (hashCode) {
                        case 2564:
                            if (xpCduType.equals("Q5")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2565:
                            if (xpCduType.equals("Q6")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2566:
                            if (xpCduType.equals("Q7")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2567:
                            if (xpCduType.equals("Q8")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2568:
                            if (xpCduType.equals("Q9")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
            }
        } else {
            if (xpCduType.equals("QB")) {
                c = '\b';
            }
            c = 65535;
        }
        return (c == 0 || c == 1 || c == 2 || c == 3 || c == 4) ? false : true;
    }

    public static String getXpCduType() {
        try {
            return Car.getXpCduType();
        } catch (Exception e) {
            LogUtils.e(TAG, "can not get XpCduType error = " + e);
            return "";
        }
    }

    public static void addSecondaryWindow(WindowManager windowManager, View view) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.setTitle("secondaryWindow");
        layoutParams.gravity = 49;
        layoutParams.type = SECONDARY_NAVIGATION;
        layoutParams.flags = 16777256;
        layoutParams.format = -2;
        layoutParams.height = -1;
        layoutParams.width = -1;
        windowManager.addView(view, layoutParams);
    }

    public static void removeSecondaryWindow(WindowManager windowManager, View view) {
        windowManager.removeView(view);
    }
}
