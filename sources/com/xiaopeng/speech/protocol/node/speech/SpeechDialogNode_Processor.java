package com.xiaopeng.speech.protocol.node.speech;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.SpeechDialogEvent;
/* loaded from: classes2.dex */
public class SpeechDialogNode_Processor implements ICommandProcessor {
    private SpeechDialogNode mTarget;

    public SpeechDialogNode_Processor(SpeechDialogNode speechDialogNode) {
        this.mTarget = speechDialogNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1928182778:
                if (str.equals(SpeechDialogEvent.SET_SCREEN_ON)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1866559770:
                if (str.equals(SpeechDialogEvent.FAST_SPEECH_OFF)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1817558719:
                if (str.equals(SpeechDialogEvent.REFRESH_CARSPEECHSERVICE_UI)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1676326235:
                if (str.equals(SpeechDialogEvent.SWITCH_SPEECH_CONTINUOUS_ENABLE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1440570874:
                if (str.equals(SpeechDialogEvent.SWITCH_SPEECH_CONTINUOUS_DISABLED)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1181317093:
                if (str.equals(SpeechDialogEvent.SYSTEM_LISTENTIME_ACCOMPANY_CLOSE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -829008729:
                if (str.equals(SpeechDialogEvent.MULTI_SPEECH_OFF)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -719478873:
                if (str.equals(SpeechDialogEvent.MULTI_SPEECH_ON)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -608339784:
                if (str.equals(SpeechDialogEvent.SCRIPT_WIDGET)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -412443633:
                if (str.equals(SpeechDialogEvent.ROUTE_OPEN_SCENE_GUIDE_WINDOW)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -265464658:
                if (str.equals(SpeechDialogEvent.ROUTE_CLOSE_SCENE_GUIDE_WINDOW)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -176140078:
                if (str.equals(SpeechDialogEvent.ROUTE_CLOSE_SPEECH_WINDOW)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -45507067:
                if (str.equals(SpeechDialogEvent.SOUND_AREA_CLOSE)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 239348679:
                if (str.equals(SpeechDialogEvent.SYSTEM_LISTENTIME_ACCOMPANY_OPEN)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 287100808:
                if (str.equals(SpeechDialogEvent.FULL_TIME_SPEECH_ON)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 310190310:
                if (str.equals(SpeechDialogEvent.FULL_TIME_SPEECH_OFF)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 771072392:
                if (str.equals(SpeechDialogEvent.FAST_SPEECH_ON)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1245819037:
                if (str.equals(SpeechDialogEvent.SOUND_AREA_OPEN)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1448374819:
                if (str.equals(SpeechDialogEvent.SPEECH_CONTINUE_DIALOGUE_OFF)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 1664069701:
                if (str.equals(SpeechDialogEvent.INJECT_TRIGGER_WORDS)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 1765586190:
                if (str.equals(SpeechDialogEvent.SHOW_CHILDWATCH_LOCATION)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 1788977038:
                if (str.equals(SpeechDialogEvent.GLOBAL_SPEECH_EXIT)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1929924035:
                if (str.equals(SpeechDialogEvent.SCRIPT_QUIT)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1975136465:
                if (str.equals(SpeechDialogEvent.ROUTE_OPEN_SPEECH_WINDOW)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2124931755:
                if (str.equals(SpeechDialogEvent.SPEECH_CONTINUE_DIALOGUE_ON)) {
                    c = 23;
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
                this.mTarget.onCloseWindow(str, str2);
                return;
            case 1:
                this.mTarget.onOpenWindow(str, str2);
                return;
            case 2:
                this.mTarget.onOpenSceneGuideWindow(str, str2);
                return;
            case 3:
                this.mTarget.onCloseSceneGuideWindow(str, str2);
                return;
            case 4:
                this.mTarget.onOpenSpeechSceneSetting(str, str2);
                return;
            case 5:
                this.mTarget.onCloseSpeechSceneSetting(str, str2);
                return;
            case 6:
                this.mTarget.onOpenSuperDialogue(str, str2);
                return;
            case 7:
                this.mTarget.onCloseSuperDialogue(str, str2);
                return;
            case '\b':
                this.mTarget.onRefreshUi(str, str2);
                return;
            case '\t':
                this.mTarget.onGlobalSpeechExit(str, str2);
                return;
            case '\n':
                this.mTarget.setScreenOn(str, str2);
                return;
            case 11:
                this.mTarget.onScriptWidget(str, str2);
                return;
            case '\f':
                this.mTarget.onScriptQuit(str, str2);
                return;
            case '\r':
                this.mTarget.showChildwatchLocation(str, str2);
                return;
            case 14:
                this.mTarget.onSoundAreaOpen(str, str2);
                return;
            case 15:
                this.mTarget.onSoundAreaClose(str, str2);
                return;
            case 16:
                this.mTarget.openFastSpeech(str, str2);
                return;
            case 17:
                this.mTarget.closeFastSpeech(str, str2);
                return;
            case 18:
                this.mTarget.openMultiSpeech(str, str2);
                return;
            case 19:
                this.mTarget.closeMultiSpeech(str, str2);
                return;
            case 20:
                this.mTarget.openFullTimeSpeech(str, str2);
                return;
            case 21:
                this.mTarget.closeFullTimeSpeech(str, str2);
                return;
            case 22:
                this.mTarget.injectTriggerWords(str, str2);
                return;
            case 23:
                this.mTarget.onContinueDialogueOpen(str, str2);
                return;
            case 24:
                this.mTarget.onContinueDialogueClose(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{SpeechDialogEvent.ROUTE_CLOSE_SPEECH_WINDOW, SpeechDialogEvent.ROUTE_OPEN_SPEECH_WINDOW, SpeechDialogEvent.ROUTE_OPEN_SCENE_GUIDE_WINDOW, SpeechDialogEvent.ROUTE_CLOSE_SCENE_GUIDE_WINDOW, SpeechDialogEvent.SWITCH_SPEECH_CONTINUOUS_ENABLE, SpeechDialogEvent.SWITCH_SPEECH_CONTINUOUS_DISABLED, SpeechDialogEvent.SYSTEM_LISTENTIME_ACCOMPANY_OPEN, SpeechDialogEvent.SYSTEM_LISTENTIME_ACCOMPANY_CLOSE, SpeechDialogEvent.REFRESH_CARSPEECHSERVICE_UI, SpeechDialogEvent.GLOBAL_SPEECH_EXIT, SpeechDialogEvent.SET_SCREEN_ON, SpeechDialogEvent.SCRIPT_WIDGET, SpeechDialogEvent.SCRIPT_QUIT, SpeechDialogEvent.SHOW_CHILDWATCH_LOCATION, SpeechDialogEvent.SOUND_AREA_OPEN, SpeechDialogEvent.SOUND_AREA_CLOSE, SpeechDialogEvent.FAST_SPEECH_ON, SpeechDialogEvent.FAST_SPEECH_OFF, SpeechDialogEvent.MULTI_SPEECH_ON, SpeechDialogEvent.MULTI_SPEECH_OFF, SpeechDialogEvent.FULL_TIME_SPEECH_ON, SpeechDialogEvent.FULL_TIME_SPEECH_OFF, SpeechDialogEvent.INJECT_TRIGGER_WORDS, SpeechDialogEvent.SPEECH_CONTINUE_DIALOGUE_ON, SpeechDialogEvent.SPEECH_CONTINUE_DIALOGUE_OFF};
    }
}
