package com.xiaopeng.speech.protocol.node.oobe;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.ErrorEvent;
import com.xiaopeng.speech.protocol.event.OOBEEvent;
/* loaded from: classes2.dex */
public class OOBENode extends SpeechNode<OOBEListener> {
    private static final String TAG = "OOBENode";

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OOBEEvent.OOBE_RECORD_RESULT)
    public void onRecordResult(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onRecordResult(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OOBEEvent.OOBE_RECORD_INPUT)
    public void onRecordInput(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onRecordInput(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OOBEEvent.OOBE_ADDRESS_SET)
    public void onAddressSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onAddressSet(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OOBEEvent.OOBE_SKIP)
    public void onSkip(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onSkip();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OOBEEvent.OOBE_SEARCH_ERROR)
    public void onSearchError(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onSearchError();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ErrorEvent.ERROR_ASR_RESULT)
    public void onNetError(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onASRError();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OOBEEvent.OOBE_ASR_ERROR)
    public void onASRError(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onASRError();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OOBEEvent.OOBE_OTHER_ERROR)
    public void onError(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onError(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OOBEEvent.OOBE_VOLUME)
    public void onVolumeChange(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onVolumeChange(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OOBEEvent.OOBE_NETWORK_ERROR)
    public void onNetWorkError(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OOBEListener) obj).onNetWorkError();
            }
        }
    }
}
