package com.xiaopeng.speech.protocol.node.userbook;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.CarcontrolEvent;
import com.xiaopeng.speech.protocol.node.carcontrol.CarcontrolListener;
import com.xiaopeng.speech.protocol.node.carcontrol.bean.UserBookValue;
/* loaded from: classes2.dex */
public class UserBookNode extends SpeechNode<UserBookListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CHECK_USER_BOOK)
    public void onCheckUserBook(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        UserBookValue fromJson = UserBookValue.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onCheckUserBook(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.OPEN_USER_BOOK)
    public void onOpenUserBook(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onOpenUserBook();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CLOSE_USER_BOOK)
    public void onCloseUserBook(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onCloseUserBook();
            }
        }
    }
}
