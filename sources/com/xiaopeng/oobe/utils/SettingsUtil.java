package com.xiaopeng.oobe.utils;

import android.provider.Settings;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.speech.protocol.event.OOBEEvent;
/* loaded from: classes.dex */
public class SettingsUtil {
    public static final String KEY_OOBE_SHOW = "xp_oobe_show";
    public static final String KEY_PRIVACY_READY_AGREE = "xp_oobe_privacy_ready_agree";
    public static final String KEY_XPILOT_USER_EXPERIENCE_PLAN = "xp_xpilot_user_experience_plan";
    private static final String TAG = "SettingsUtil";
    public static final String USER_CHECKED = "user_checked";

    public static void enterOOBE() {
        Settings.Global.putString(App.getInstance().getContentResolver(), KEY_OOBE_SHOW, OOBEEvent.STRING_TRUE);
        LogUtils.i(TAG, "enterOOBE", false);
    }

    public static void exitOOBE() {
        Settings.Global.putString(App.getInstance().getContentResolver(), KEY_OOBE_SHOW, OOBEEvent.STRING_FALSE);
        LogUtils.i(TAG, "exitOOBE", false);
    }

    public static void setXpilotUserExperiencePlan(boolean z) {
        LogUtils.d(TAG, "setXpilotUserExperiencePlan state:" + z, false);
        Settings.Global.putString(App.getInstance().getContentResolver(), KEY_XPILOT_USER_EXPERIENCE_PLAN, String.valueOf(z));
    }

    public static void getXpilotUserExperiencePlan() {
        String string = Settings.Global.getString(App.getInstance().getContentResolver(), KEY_XPILOT_USER_EXPERIENCE_PLAN);
        LogUtils.d(TAG, "getXpilotUserExperiencePlan value:" + string, false);
    }

    public static void setPrivacyReadyAgree(String str) {
        LogUtils.d(TAG, "setPrivacyReadyAgree state:" + str, false);
        Settings.Global.putString(App.getInstance().getContentResolver(), getPrivacyProtocolKey(), str);
    }

    public static void setSetupComplete() {
        LogUtils.d(TAG, "setSetupComplete", false);
        Settings.Global.putInt(App.getInstance().getContentResolver(), "device_provisioned", 1);
        Settings.Secure.putInt(App.getInstance().getContentResolver(), "user_setup_complete", 1);
    }

    public static void setSpeechPlanProtocol(String str) {
        LogUtils.d(TAG, "setSpeechPlanProtocol state:" + str, false);
        Settings.Global.putString(App.getInstance().getContentResolver(), getSpeechPlanProtocolKey(), str);
    }

    public static String getSpeechPlanProtocol() {
        LogUtils.d(TAG, "getSpeechPlanProtocol");
        return Settings.Global.getString(App.getInstance().getContentResolver(), getSpeechPlanProtocolKey());
    }

    public static void setMapProtocol(String str) {
        LogUtils.d(TAG, "setMapProtocol state:" + str, false);
        Settings.Global.putString(App.getInstance().getContentResolver(), Constants.Inter.XP_PROTOCAL_MAP_ACCEPT_FLAG, str);
    }

    private static String getPrivacyProtocolKey() {
        return CarStatusUtils.isEURegion() ? Constants.Inter.XP_PROTOCAL_ACCEPT_FLAG : KEY_PRIVACY_READY_AGREE;
    }

    private static String getSpeechPlanProtocolKey() {
        return CarStatusUtils.isEURegion() ? Constants.Inter.XP_PROTOCAL_SPEECH_ACCEPT_FLAG : USER_CHECKED;
    }
}
