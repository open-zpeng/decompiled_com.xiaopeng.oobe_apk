package com.xiaopeng.speech.protocol.query.speech;

import com.xiaopeng.speech.IQueryCaller;
/* loaded from: classes2.dex */
public interface ISpeechQueryCaller extends IQueryCaller {
    default boolean appIsInstalled(String str) {
        return false;
    }

    default int carHasScreen(int i) {
        return 1;
    }

    String getCarPlatForm();

    default int getCfcVehicleLevel() {
        return -1;
    }

    int getCurrentMode();

    default int getCurrentTtsEngine() {
        return 1;
    }

    default int getFirstSpeechState() {
        return -1;
    }

    int getSoundLocation();

    default int getVuiSceneSwitchStatus() {
        return -1;
    }

    default int ifAdvancedSettingsOpen() {
        return 0;
    }

    default int ifFastSpeechEnable() {
        return 0;
    }

    default int ifFullTimeSpeechEnable() {
        return 0;
    }

    default int ifMultiSpeechEnable() {
        return 0;
    }

    default int ifSupportFastSpeech() {
        return 0;
    }

    default int ifSupportMulti() {
        return 0;
    }

    default int ifSupportWaitAwake() {
        return 0;
    }

    boolean isAccountLogin();

    boolean isAppForeground(String str);

    boolean isEnableGlobalWakeup();

    default boolean isNapaTop(int i) {
        return true;
    }

    default boolean isNaviTop(int i) {
        return false;
    }

    default boolean isScreenOn(int i) {
        return false;
    }

    default boolean isSupportXSport() {
        return false;
    }

    default boolean isUserExpressionOpened() {
        return false;
    }

    default int screenGoHome(int i) {
        return 1;
    }

    default int screenisDesktop(int i) {
        return 1;
    }
}
