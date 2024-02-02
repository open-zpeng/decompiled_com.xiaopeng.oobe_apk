package com.xiaopeng.speech.jarvisproto;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DMPrepare extends JarvisProto {
    public static final String EVENT = "jarvis.dm.prepare";
    public boolean isPreparing;

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isPreparing", this.isPreparing);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
