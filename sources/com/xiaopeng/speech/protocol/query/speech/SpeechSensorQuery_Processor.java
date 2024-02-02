package com.xiaopeng.speech.protocol.query.speech;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.speech.SpeechQueryEvent;
/* loaded from: classes2.dex */
public class SpeechSensorQuery_Processor implements IQueryProcessor {
    private SpeechSensorQuery mTarget;

    public SpeechSensorQuery_Processor(SpeechSensorQuery speechSensorQuery) {
        this.mTarget = speechSensorQuery;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1914865600:
                if (str.equals(SpeechQueryEvent.SOUND_LOCATION)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1900477815:
                if (str.equals(SpeechQueryEvent.SUPPORT_MULTI_SPEECH)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1792080358:
                if (str.equals(SpeechQueryEvent.SCREEN_IS_DESKTOP)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -1584155906:
                if (str.equals(SpeechQueryEvent.GET_SCENE_SWITCH_STATUS)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1563492784:
                if (str.equals(SpeechQueryEvent.IS_USEREXPRESSION_OPENED)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1538822035:
                if (str.equals(SpeechQueryEvent.IS_FULL_TIME_SPEECH_OPEN)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1472227677:
                if (str.equals(SpeechQueryEvent.SCREEN_GO_HOME)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -470310403:
                if (str.equals(SpeechQueryEvent.IS_FAST_SPEECH_OPEN)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -442423409:
                if (str.equals(SpeechQueryEvent.IS_NAPA_TOP)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -414885244:
                if (str.equals(SpeechQueryEvent.GET_CURRENT_MODE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -323965442:
                if (str.equals(SpeechQueryEvent.IS_WAKEUP_ENABLE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -263260335:
                if (str.equals(SpeechQueryEvent.IS_NAVI_TOP)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -225376174:
                if (str.equals(SpeechQueryEvent.GET_CAR_LEVEL)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -146831651:
                if (str.equals(SpeechQueryEvent.IS_SCREEN_ON)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -145453996:
                if (str.equals(SpeechQueryEvent.SUPPORT_WAIT_AWAKE)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -63943572:
                if (str.equals(SpeechQueryEvent.SUPPORT_FAST_SPEECH)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 386767985:
                if (str.equals(SpeechQueryEvent.GET_CAR_PLATFORM)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 907076707:
                if (str.equals(SpeechQueryEvent.CURRENT_TTS_ENGINE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1204667546:
                if (str.equals(SpeechQueryEvent.CAR_HAS_SCREEN)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 1205362780:
                if (str.equals(SpeechQueryEvent.IS_ACCOUNT_LOGIN)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1352584367:
                if (str.equals(SpeechQueryEvent.IS_APP_FOREGROUND)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1717410403:
                if (str.equals(SpeechQueryEvent.APP_IS_INSTALLED)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1753533112:
                if (str.equals(SpeechQueryEvent.IF_ADVANCE_SETTINGS_OPEN)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 1965905439:
                if (str.equals(SpeechQueryEvent.IS_SUPPROT_XSPORT)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 1988122650:
                if (str.equals(SpeechQueryEvent.IS_MULTI_SPEECH_OPEN)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 1990811260:
                if (str.equals(SpeechQueryEvent.GET_FIRST_SPEECH_STATUS)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return Integer.valueOf(this.mTarget.getSoundLocation(str, str2));
            case 1:
                return Boolean.valueOf(this.mTarget.isAppForeground(str, str2));
            case 2:
                return Boolean.valueOf(this.mTarget.isAccountLogin(str, str2));
            case 3:
                return Boolean.valueOf(this.mTarget.isEnableGlobalWakeup(str, str2));
            case 4:
                return Integer.valueOf(this.mTarget.getCurrentMode(str, str2));
            case 5:
                return this.mTarget.getCarPlatform(str, str2);
            case 6:
                return Integer.valueOf(this.mTarget.getVuiSceneSwitchStatus(str, str2));
            case 7:
                return Integer.valueOf(this.mTarget.getFirstSpeechState(str, str2));
            case '\b':
                return Integer.valueOf(this.mTarget.getCurrentTtsEngine(str, str2));
            case '\t':
                return Boolean.valueOf(this.mTarget.appIsInstalled(str, str2));
            case '\n':
                return Boolean.valueOf(this.mTarget.isUserExpressionOpened(str, str2));
            case 11:
                return Boolean.valueOf(this.mTarget.isNapaTop(str, str2));
            case '\f':
                return Boolean.valueOf(this.mTarget.isNaviTop(str, str2));
            case '\r':
                return Boolean.valueOf(this.mTarget.is(str, str2));
            case 14:
                return Integer.valueOf(this.mTarget.getCfcVehicleLevel());
            case 15:
                return Integer.valueOf(this.mTarget.ifSupportFastSpeech());
            case 16:
                return Integer.valueOf(this.mTarget.ifSupportWaitAwake());
            case 17:
                return Integer.valueOf(this.mTarget.ifSupportMulti());
            case 18:
                return Boolean.valueOf(this.mTarget.isSupportXSport());
            case 19:
                return Integer.valueOf(this.mTarget.isFastSpeechOpen());
            case 20:
                return Integer.valueOf(this.mTarget.isMultiSpeechOpen());
            case 21:
                return Integer.valueOf(this.mTarget.isFullTimeSpeechOpen());
            case 22:
                return Integer.valueOf(this.mTarget.isAdvancedSettingsOpen());
            case 23:
                return Integer.valueOf(this.mTarget.carHasScreen(str, str2));
            case 24:
                return Integer.valueOf(this.mTarget.screenisDesktop(str, str2));
            case 25:
                return Integer.valueOf(this.mTarget.screenGoHome(str, str2));
            default:
                return null;
        }
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{SpeechQueryEvent.SOUND_LOCATION, SpeechQueryEvent.IS_APP_FOREGROUND, SpeechQueryEvent.IS_ACCOUNT_LOGIN, SpeechQueryEvent.IS_WAKEUP_ENABLE, SpeechQueryEvent.GET_CURRENT_MODE, SpeechQueryEvent.GET_CAR_PLATFORM, SpeechQueryEvent.GET_SCENE_SWITCH_STATUS, SpeechQueryEvent.GET_FIRST_SPEECH_STATUS, SpeechQueryEvent.CURRENT_TTS_ENGINE, SpeechQueryEvent.APP_IS_INSTALLED, SpeechQueryEvent.IS_USEREXPRESSION_OPENED, SpeechQueryEvent.IS_NAPA_TOP, SpeechQueryEvent.IS_NAVI_TOP, SpeechQueryEvent.IS_SCREEN_ON, SpeechQueryEvent.GET_CAR_LEVEL, SpeechQueryEvent.SUPPORT_FAST_SPEECH, SpeechQueryEvent.SUPPORT_WAIT_AWAKE, SpeechQueryEvent.SUPPORT_MULTI_SPEECH, SpeechQueryEvent.IS_SUPPROT_XSPORT, SpeechQueryEvent.IS_FAST_SPEECH_OPEN, SpeechQueryEvent.IS_MULTI_SPEECH_OPEN, SpeechQueryEvent.IS_FULL_TIME_SPEECH_OPEN, SpeechQueryEvent.IF_ADVANCE_SETTINGS_OPEN, SpeechQueryEvent.CAR_HAS_SCREEN, SpeechQueryEvent.SCREEN_IS_DESKTOP, SpeechQueryEvent.SCREEN_GO_HOME};
    }
}
