package com.xiaopeng.speech.protocol.node.avatar;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.jarvisproto.AvatarExpressionEvent;
import com.xiaopeng.speech.jarvisproto.AvatarStatus;
import com.xiaopeng.speech.jarvisproto.AvatarWakeupDisable;
import com.xiaopeng.speech.jarvisproto.AvatarWakeupEnable;
import com.xiaopeng.speech.jarvisproto.DMListening;
import com.xiaopeng.speech.jarvisproto.DMPersonalRun;
import com.xiaopeng.speech.jarvisproto.DMPersonalRunExit;
import com.xiaopeng.speech.jarvisproto.DMRecognized;
import com.xiaopeng.speech.jarvisproto.DMSpeaking;
import com.xiaopeng.speech.jarvisproto.DMUnderstanding;
import com.xiaopeng.speech.protocol.event.ContextEvent;
/* loaded from: classes2.dex */
public class AvatarNode extends SpeechNode<AvatarListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMRecognized.EVENT)
    public void onSilence(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        DMRecognized fromJson = DMRecognized.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onSilence(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMListening.EVENT)
    public void onListening(String str, String str2) {
        DMListening fromJson = DMListening.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onListening(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMSpeaking.EVENT)
    public void onSpeaking(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onSpeaking();
            }
        }
    }

    protected void onStandby(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onStandby();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMUnderstanding.EVENT)
    public void onUnderstanding(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onUnderstanding();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMPersonalRun.EVENT)
    public void onPersonalRunning(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onPersonalRun();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = DMPersonalRunExit.EVENT)
    public void onPersonalExit(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onPersonalRunExit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = AvatarExpressionEvent.EVENT)
    public void onAvatarExpression(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onAvatarExpression(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_CUSTOM)
    public void onWidgetCustom(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onWidgetCustom(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_TEXT)
    public void onWidgetText(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onWidgetText(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_LIST)
    public void onWidgetList(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onWidgetList(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_MEDIA)
    public void onWidgetMedia(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onWidgetMedia(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_CARD)
    public void onWidgetCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onWidgetCard(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_SEARCH)
    public void onWidgetSearch(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onWidgetSearch(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SpeechAnnotation(event = AvatarWakeupEnable.EVENT)
    public void onAvatarWakerupEnable(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onAvatarWakerupEnable(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SpeechAnnotation(event = AvatarWakeupDisable.EVENT)
    public void onAvatarWakerupDisable(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onAvatarWakerupDisable(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = AvatarStatus.EVENT)
    public void onAvatarStatus(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        AvatarStatus fromJson = AvatarStatus.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AvatarListener) obj).onAvatarStatusChanged(fromJson);
            }
        }
    }
}
