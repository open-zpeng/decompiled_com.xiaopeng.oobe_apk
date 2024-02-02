package com.xiaopeng.speech.protocol.node.ota;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.OtaEvent;
/* loaded from: classes2.dex */
public class OtaNode extends SpeechNode<OtaListener> {
    private static final String TAG = "OtaNode";

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OtaEvent.OTA_PAGE_OPEN)
    public void onOpenOtaPage(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OtaListener) obj).onOpenOtaPage();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = OtaEvent.OTARESERVATION_PAGE_OPEN)
    public void onOpenReservationPage(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((OtaListener) obj).onOpenReservationPage();
            }
        }
    }
}
