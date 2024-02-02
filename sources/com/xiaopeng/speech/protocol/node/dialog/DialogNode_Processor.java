package com.xiaopeng.speech.protocol.node.dialog;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.jarvisproto.DMActive;
import com.xiaopeng.speech.jarvisproto.DMEnd;
import com.xiaopeng.speech.jarvisproto.DMExit;
import com.xiaopeng.speech.jarvisproto.DMPrepare;
import com.xiaopeng.speech.jarvisproto.DMStart;
import com.xiaopeng.speech.jarvisproto.DMWait;
import com.xiaopeng.speech.jarvisproto.DialogSoundAreaStatus;
import com.xiaopeng.speech.jarvisproto.SoundAreaStatus;
import com.xiaopeng.speech.jarvisproto.SwitchStatus;
import com.xiaopeng.speech.protocol.event.DialogEvent;
/* loaded from: classes2.dex */
public class DialogNode_Processor implements ICommandProcessor {
    private DialogNode mTarget;

    public DialogNode_Processor(DialogNode dialogNode) {
        this.mTarget = dialogNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1578930679:
                if (str.equals(SwitchStatus.EVENT)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1434480195:
                if (str.equals(DialogEvent.DIALOG_STATUS_CHANGED)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1411139995:
                if (str.equals(DialogEvent.VAD_END)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1293262773:
                if (str.equals(DialogEvent.WAKEUP_RESULT)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -931531412:
                if (str.equals(DialogEvent.DIALOG_CONTINUE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -772037990:
                if (str.equals(DMExit.EVENT)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -771523855:
                if (str.equals(DMWait.EVENT)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -206130037:
                if (str.equals(SoundAreaStatus.EVENT)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -153794717:
                if (str.equals(DialogEvent.DIALOG_ERROR)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 561561751:
                if (str.equals(DialogSoundAreaStatus.EVENT)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 763224587:
                if (str.equals(DMPrepare.EVENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 967244162:
                if (str.equals(DMActive.EVENT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1083473887:
                if (str.equals(DMEnd.EVENT)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1101097907:
                if (str.equals(DialogEvent.VAD_BEGIN)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1849428582:
                if (str.equals(DMStart.EVENT)) {
                    c = 0;
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
                this.mTarget.onDialogStart(str, str2);
                return;
            case 1:
                this.mTarget.onDialogActive(str, str2);
                return;
            case 2:
                this.mTarget.onDialogPrepare(str, str2);
                return;
            case 3:
                this.mTarget.onDialogError(str, str2);
                return;
            case 4:
                this.mTarget.onDialogExit(str, str2);
                return;
            case 5:
                this.mTarget.onDialogEnd(str, str2);
                return;
            case 6:
                this.mTarget.onDialogWait(str, str2);
                return;
            case 7:
                this.mTarget.onDialogContinue(str, str2);
                return;
            case '\b':
                this.mTarget.onWakeupResult(str, str2);
                return;
            case '\t':
                this.mTarget.onVadBegin(str, str2);
                return;
            case '\n':
                this.mTarget.onVadEnd(str, str2);
                return;
            case 11:
                this.mTarget.onDialogStatusChanged(str, str2);
                return;
            case '\f':
                this.mTarget.onSoundAreaStatusChanged(str, str2);
                return;
            case '\r':
                this.mTarget.onDialogState(str, str2);
                return;
            case 14:
                this.mTarget.onDialogSwitchStatus(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{DMStart.EVENT, DMActive.EVENT, DMPrepare.EVENT, DialogEvent.DIALOG_ERROR, DMExit.EVENT, DMEnd.EVENT, DMWait.EVENT, DialogEvent.DIALOG_CONTINUE, DialogEvent.WAKEUP_RESULT, DialogEvent.VAD_BEGIN, DialogEvent.VAD_END, DialogEvent.DIALOG_STATUS_CHANGED, SoundAreaStatus.EVENT, DialogSoundAreaStatus.EVENT, SwitchStatus.EVENT};
    }
}
