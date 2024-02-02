package com.xiaopeng.speech.protocol.node.message;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.MessageEvent;
import com.xiaopeng.speech.protocol.node.message.bean.IndexBean;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class MessageNode extends SpeechNode<MessageListener> {
    public static final String EVENT = "jarvis.message.engine.status";

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MessageEvent.CANCEL)
    public void onCancel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MessageListener) obj).onCancel();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MessageEvent.PARKING_SELECT)
    public void onParkingSelected(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        IndexBean fromJson = IndexBean.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MessageListener) obj).onParkingSelected(fromJson.getIndex());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MessageEvent.PATH_CHANGE)
    public void onPathChanged(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MessageListener) obj).onPathChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MessageEvent.MESSAGE_AI_TO_SPEECH)
    public void onAIMessage(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        int parseInt = Integer.parseInt(str2);
        for (Object obj : collectCallbacks) {
            ((MessageListener) obj).onAIMessage(parseInt);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MessageEvent.COMMON_MESSAGE_AI_TO_SPEECH)
    public void onCommonAIMessage(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MessageListener) obj).onCommonAIMessage(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MessageEvent.COMMON_MESSAGE_SUBMIT)
    public void onCommonSubmit(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MessageListener) obj).onCommonSubmit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MessageEvent.COMMON_MESSAGE_CANCEL)
    public void onCommonCancel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MessageListener) obj).onCommonCancel();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MessageEvent.AI_MESSAGE_DISABLE)
    public void onAIMessageDisable(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MessageListener) obj).onAIMessageDisable();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MessageEvent.AI_MESSAGE_DISABLE_SEVEN_DAYS)
    public void onAIMessageDisableSevenDays(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MessageListener) obj).onAIMessageDisableSevenDays();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = EVENT)
    public void onHotWordEngineOK(String str, String str2) {
        boolean fromJson = getFromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MessageListener) obj).onHotWordEngineOK(fromJson);
            }
        }
    }

    private boolean getFromJson(String str) {
        try {
            return new JSONObject(str).optBoolean("isReady");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
