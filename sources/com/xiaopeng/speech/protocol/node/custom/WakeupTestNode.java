package com.xiaopeng.speech.protocol.node.custom;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.WakeupTestEvent;
/* loaded from: classes2.dex */
public class WakeupTestNode extends SpeechNode<WakeupTestListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = WakeupTestEvent.VAD_BEGIN)
    public void onVADBeginSpeech(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((WakeupTestListener) obj).onVADBeginSpeech();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = WakeupTestEvent.VAD_END)
    public void onVADEndSpeech(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((WakeupTestListener) obj).onVADEndSpeech();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = WakeupTestEvent.WAKEUP_SUCC)
    public void onWakeupSucced(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((WakeupTestListener) obj).onWakeupSucced(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = WakeupTestEvent.WAKEUP_FAILED)
    public void onWakeupFailed(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((WakeupTestListener) obj).onWakeupFailed(str2);
            }
        }
    }
}
