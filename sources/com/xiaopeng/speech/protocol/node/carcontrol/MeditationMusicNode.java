package com.xiaopeng.speech.protocol.node.carcontrol;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.MeditationMusicEvent;
/* loaded from: classes2.dex */
public class MeditationMusicNode extends SpeechNode<MeditationMusicListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MeditationMusicEvent.MEDITATION_MUSIC_CONTROL_PREV)
    public void onPrev(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MeditationMusicListener) obj).onPrev();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MeditationMusicEvent.MEDITATION_MUSIC_CONTROL_NEXT)
    public void onNext(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MeditationMusicListener) obj).onNext();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MeditationMusicEvent.MEDITATION_MUSIC_CONTROL_PAUSE)
    public void onPause(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MeditationMusicListener) obj).onPause();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MeditationMusicEvent.MEDITATION_MUSIC_CONTROL_RESUME)
    public void onResume(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MeditationMusicListener) obj).onResume();
            }
        }
    }
}
