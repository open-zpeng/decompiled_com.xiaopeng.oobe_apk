package com.xiaopeng.speech.protocol.node.media;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.actorapi.SupportActor;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.jarvisproto.WakeupResult;
import com.xiaopeng.speech.protocol.event.MediaEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class MediaTriggerNode extends SpeechNode<MediaTriggerListener> {
    private static final String TAG = "MediaTriggerNode";

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.TRIGGER_MESSAGE_SUBMIT)
    public void onSubmit(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            new JSONObject(str2).optString("value");
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((MediaTriggerListener) obj).onSubmit();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.TRIGGER_MESSAGE_CANCEL)
    public void onCancel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaTriggerListener) obj).onCancel();
            }
        }
    }

    public void sendTrigger(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("triggerID", 20001);
            jSONObject.put("tts", str);
            jSONObject.put("wordList", getWordList(str2, str3));
            jSONObject.put("ifMergeWords", false);
            jSONObject.put("repeatNum", 0);
            SpeechClient.instance().getAgent().sendApiRoute("media.message.trigger.send", jSONObject.toString());
            LogUtils.i(TAG, "sendTrigger :" + jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leaveTrigger() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("triggerID", 20001);
            SpeechClient.instance().getAgent().sendApiRoute("media.message.trigger.leave", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getWordList(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                for (String str3 : str.split("\\|")) {
                    jSONObject.put(str3, getSubmitObj());
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                for (String str4 : str2.split("\\|")) {
                    jSONObject.put(str4, getCancelObj());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject getSubmitObj() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(WakeupResult.REASON_COMMAND, MediaEvent.TRIGGER_MESSAGE_SUBMIT);
            jSONObject.put("type", "all");
            jSONObject.put("tts", SupportActor.TTS_DEFAULT_SUPPORT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject getCancelObj() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(WakeupResult.REASON_COMMAND, MediaEvent.TRIGGER_MESSAGE_CANCEL);
            jSONObject.put("type", "all");
            jSONObject.put("tts", SupportActor.TTS_DEFAULT_SUPPORT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
