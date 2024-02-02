package com.xiaopeng.speech.protocol.node.error;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.ErrorEvent;
/* loaded from: classes2.dex */
public class ErrorNode extends SpeechNode<IErrorListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ErrorEvent.ERROR_ASR_RESULT)
    public void onErrorAsrResult(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((IErrorListener) obj).onErrorAsrResult();
            }
        }
    }
}
