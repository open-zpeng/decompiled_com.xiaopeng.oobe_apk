package com.xiaopeng.speech.protocol.node.triggerbot;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.jarvisproto.WakeupResult;
import com.xiaopeng.speech.protocol.event.TriggerBotEvent;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class TriggerBotNode extends SpeechNode<TriggerBotListener> {
    @SpeechAnnotation(event = TriggerBotEvent.TRIGGER_BOT_RESULT)
    public void onTriggerResult(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString(WakeupResult.REASON_COMMAND);
            String optString2 = jSONObject.optString("param");
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((TriggerBotListener) obj).onTriggerResult(optString, optString2);
                }
            }
        } catch (Exception unused) {
        }
    }
}
