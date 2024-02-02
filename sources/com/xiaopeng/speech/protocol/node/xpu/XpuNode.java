package com.xiaopeng.speech.protocol.node.xpu;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.XpuEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class XpuNode extends SpeechNode<IXpuListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = XpuEvent.XPU_VOICE_CHANGE_LANE)
    public void laneChange(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("direction")) {
                String optString = jSONObject.optString("direction");
                if (TextUtils.isEmpty(optString)) {
                    return;
                }
                int parseInt = Integer.parseInt(optString);
                Object[] collectCallbacks = this.mListenerList.collectCallbacks();
                if (collectCallbacks != null) {
                    for (Object obj : collectCallbacks) {
                        ((IXpuListener) obj).laneChange(parseInt);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
