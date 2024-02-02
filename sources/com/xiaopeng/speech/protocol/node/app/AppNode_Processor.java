package com.xiaopeng.speech.protocol.node.app;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.AppEvent;
/* loaded from: classes2.dex */
public class AppNode_Processor implements ICommandProcessor {
    private AppNode mTarget;

    public AppNode_Processor(AppNode appNode) {
        this.mTarget = appNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -2098758070:
                if (str.equals(AppEvent.START_PAGE)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1740381936:
                if (str.equals(AppEvent.APP_LAUNCHER_EXIT)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1284156689:
                if (str.equals(AppEvent.APP_OPEN_YOUKU_SEARCH)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1266760065:
                if (str.equals(AppEvent.GUI_CARSPEECH_DEBUG_OPEN)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -210825502:
                if (str.equals(AppEvent.AI_HOMEPAGE_CLOSE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -210679123:
                if (str.equals(AppEvent.App_PAGE_OPEN)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -135994987:
                if (str.equals(AppEvent.DEBUG_OPEN)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 277054473:
                if (str.equals(AppEvent.APPSTORE_OPEN)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 636922058:
                if (str.equals(AppEvent.KEYEVENT_BACK)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 963391520:
                if (str.equals(AppEvent.AI_HOMEPAGE_OPEN)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1150874049:
                if (str.equals(AppEvent.TRIGGER_INTENT)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1242343382:
                if (str.equals(AppEvent.APP_ACTIVE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1440847352:
                if (str.equals(AppEvent.QUERY)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1863838566:
                if (str.equals(AppEvent.APP_OPEN)) {
                    c = 1;
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
                this.mTarget.onQuery(str, str2);
                return;
            case 1:
                this.mTarget.onAppOpen(str, str2);
                return;
            case 2:
                this.mTarget.onAppPageOpen(str, str2);
                return;
            case 3:
                this.mTarget.onKeyeventBack(str, str2);
                return;
            case 4:
                this.mTarget.onAppStoreOpen(str, str2);
                return;
            case 5:
                this.mTarget.onTriggerIntent(str, str2);
                return;
            case 6:
                this.mTarget.onDebugOpen(str, str2);
                return;
            case 7:
                this.mTarget.onAppActive(str, str2);
                return;
            case '\b':
                this.mTarget.onAiHomepageOpen(str, str2);
                return;
            case '\t':
                this.mTarget.onAiHomepageClose(str, str2);
                return;
            case '\n':
                this.mTarget.onAppLauncherExit(str, str2);
                return;
            case 11:
                this.mTarget.onStartPage(str, str2);
                return;
            case '\f':
                this.mTarget.onGuiSpeechDebugPage(str, str2);
                return;
            case '\r':
                this.mTarget.onOpenYoukuSearch(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{AppEvent.QUERY, AppEvent.APP_OPEN, AppEvent.App_PAGE_OPEN, AppEvent.KEYEVENT_BACK, AppEvent.APPSTORE_OPEN, AppEvent.TRIGGER_INTENT, AppEvent.DEBUG_OPEN, AppEvent.APP_ACTIVE, AppEvent.AI_HOMEPAGE_OPEN, AppEvent.AI_HOMEPAGE_CLOSE, AppEvent.APP_LAUNCHER_EXIT, AppEvent.START_PAGE, AppEvent.GUI_CARSPEECH_DEBUG_OPEN, AppEvent.APP_OPEN_YOUKU_SEARCH};
    }
}
