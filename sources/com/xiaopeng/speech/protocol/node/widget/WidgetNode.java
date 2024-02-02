package com.xiaopeng.speech.protocol.node.widget;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.WidgetEvent;
/* loaded from: classes2.dex */
public class WidgetNode extends SpeechNode<WidgetListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = WidgetEvent.AC_WIDGET_ON)
    public void onAcWidgetOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((WidgetListener) obj).onAcWidgetOn();
            }
        }
    }
}
