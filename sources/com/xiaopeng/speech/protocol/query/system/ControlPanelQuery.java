package com.xiaopeng.speech.protocol.query.system;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.QuerySystemEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ControlPanelQuery extends SpeechQuery<IControlPanelCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.BLUETOOTH_VOLUME_SET)
    public int onBlueToothVolumeSet(String str, String str2) {
        int i = 0;
        try {
            i = new JSONObject(str2).optInt("type", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((IControlPanelCaller) this.mQueryCaller).onBlueToothVolumeSet(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.BLUETOOTH_VOLUME_SET_VALUE)
    public int onBlueToothVolumeSetValue(String str, String str2) {
        int i = 0;
        try {
            i = new JSONObject(str2).optInt("value", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((IControlPanelCaller) this.mQueryCaller).onBlueToothVolumeSetValue(i);
    }
}
