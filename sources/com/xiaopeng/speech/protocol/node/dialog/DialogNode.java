package com.xiaopeng.speech.protocol.node.dialog;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
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
import com.xiaopeng.speech.protocol.node.dialog.bean.DialogActiveReason;
import com.xiaopeng.speech.protocol.node.dialog.bean.DialogEndReason;
import com.xiaopeng.speech.protocol.node.dialog.bean.DialogExitReason;
import com.xiaopeng.speech.protocol.node.dialog.bean.PrepareReason;
import com.xiaopeng.speech.protocol.node.dialog.bean.WakeupReason;
/* loaded from: classes2.dex */
public class DialogNode extends SpeechNode<DialogListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMStart.EVENT)
    public void onDialogStart(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        WakeupReason fromJson = WakeupReason.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogStart(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMActive.EVENT)
    public void onDialogActive(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        DialogActiveReason fromJson = DialogActiveReason.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogActive(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMPrepare.EVENT)
    public void onDialogPrepare(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        PrepareReason fromJson = PrepareReason.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogPrepare(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.DIALOG_ERROR)
    public void onDialogError(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogError();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMExit.EVENT)
    public void onDialogExit(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        DialogExitReason fromJson = DialogExitReason.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogExit(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMEnd.EVENT)
    public void onDialogEnd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        DialogEndReason fromJson = DialogEndReason.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogEnd(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMWait.EVENT)
    public void onDialogWait(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        DMWait dMWait = new DMWait();
        if (!TextUtils.isEmpty(str2)) {
            dMWait = DMWait.fromJson(str2);
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogWait(dMWait);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.DIALOG_CONTINUE)
    public void onDialogContinue(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogContinue();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.WAKEUP_RESULT)
    public void onWakeupResult(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onWakeupResult();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.VAD_BEGIN)
    public void onVadBegin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onVadBegin();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.VAD_END)
    public void onVadEnd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onVadEnd();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogEvent.DIALOG_STATUS_CHANGED)
    public void onDialogStatusChanged(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogStatusChanged(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SoundAreaStatus.EVENT)
    public void onSoundAreaStatusChanged(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        SoundAreaStatus fromJson = SoundAreaStatus.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onSoundAreaStatusChanged(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DialogSoundAreaStatus.EVENT)
    public void onDialogState(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        DialogSoundAreaStatus fromJson = DialogSoundAreaStatus.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogSoundAreaStatusChanged(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SwitchStatus.EVENT)
    public void onDialogSwitchStatus(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        SwitchStatus fromJson = SwitchStatus.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((DialogListener) obj).onDialogSwitchStatusChanged(fromJson);
            }
        }
    }
}
