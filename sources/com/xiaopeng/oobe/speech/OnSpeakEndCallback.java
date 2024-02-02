package com.xiaopeng.oobe.speech;

import android.speech.tts.UtteranceProgressListener;
/* loaded from: classes.dex */
public abstract class OnSpeakEndCallback extends UtteranceProgressListener {
    @Override // android.speech.tts.UtteranceProgressListener
    public void onDone(String str) {
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onError(String str) {
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onStart(String str) {
    }
}
