package com.xiaopeng.speech.protocol.node.record;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.jarvisproto.AsrCloundResult;
import com.xiaopeng.speech.jarvisproto.RecordBegin;
import com.xiaopeng.speech.jarvisproto.RecordEnd;
import com.xiaopeng.speech.jarvisproto.RecordError;
import com.xiaopeng.speech.jarvisproto.RecordMaxLength;
import com.xiaopeng.speech.jarvisproto.SpeechBegin;
import com.xiaopeng.speech.jarvisproto.SpeechEnd;
import com.xiaopeng.speech.jarvisproto.SpeechVolume;
import com.xiaopeng.speech.protocol.node.record.bean.AsrResult;
import com.xiaopeng.speech.protocol.node.record.bean.RecordErrReason;
import com.xiaopeng.speech.protocol.node.record.bean.Volume;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class RecordNode extends SpeechNode<RecordListener> {
    @SpeechAnnotation(event = AsrCloundResult.EVENT)
    public void onAsrResult(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        AsrResult fromJson = AsrResult.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((RecordListener) obj).onAsrResult(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = RecordBegin.EVENT)
    public void onRecordBegin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((RecordListener) obj).onRecordBegin();
            }
        }
    }

    @SpeechAnnotation(event = RecordEnd.EVENT)
    public void onRecordEnd(String str, String str2) {
        boolean z;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            z = new JSONObject(str2).optBoolean("isStopRecord");
        } catch (JSONException e) {
            e.printStackTrace();
            z = false;
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((RecordListener) obj).onRecordEnd(z);
            }
        }
    }

    @SpeechAnnotation(event = RecordError.EVENT)
    public void onRecordError(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        RecordErrReason fromJson = RecordErrReason.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((RecordListener) obj).onRecordError(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = SpeechBegin.EVENT)
    public void onSpeechBegin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((RecordListener) obj).onSpeechBegin();
            }
        }
    }

    @SpeechAnnotation(event = SpeechEnd.EVENT)
    public void onSpeechEnd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((RecordListener) obj).onSpeechEnd();
            }
        }
    }

    @SpeechAnnotation(event = SpeechVolume.EVENT)
    public void onSpeechVolume(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        Volume fromJson = Volume.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((RecordListener) obj).onSpeechVolume(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = RecordMaxLength.EVENT)
    public void onRecordMaxLength(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((RecordListener) obj).onRecordMaxLength();
            }
        }
    }
}
