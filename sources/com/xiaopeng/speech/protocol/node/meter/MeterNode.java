package com.xiaopeng.speech.protocol.node.meter;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.MeterEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class MeterNode extends SpeechNode<MeterListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MeterEvent.SET_LEFT_CARD)
    public void setLeftCard(String str, String str2) {
        int i;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            i = new JSONObject(str2).optInt("index");
        } catch (JSONException e) {
            e.printStackTrace();
            i = -1;
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MeterListener) obj).setLeftCard(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MeterEvent.SET_RIGHTT_CARD)
    public void setRightCard(String str, String str2) {
        int i;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            i = new JSONObject(str2).optInt("index");
        } catch (JSONException e) {
            e.printStackTrace();
            i = -1;
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MeterListener) obj).setRightCard(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MeterEvent.DASHBOARD_LIGHTS_STATUS)
    public void onDashboardLightsStatus(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MeterListener) obj).onDashboardLightsStatus();
            }
        }
    }
}
