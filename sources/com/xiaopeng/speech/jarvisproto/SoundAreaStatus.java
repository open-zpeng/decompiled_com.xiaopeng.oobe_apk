package com.xiaopeng.speech.jarvisproto;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SoundAreaStatus extends JarvisProto {
    public static final String EVENT = "jarvis.sound.area.status";
    public boolean displayStatus;
    public boolean enableStatus;
    public boolean listeningStatus;
    public int soundArea;
    public boolean waitAwakeListeningStatus;

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    public SoundAreaStatus(int i, boolean z) {
        this.soundArea = i;
        this.displayStatus = z;
    }

    public SoundAreaStatus(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        this.soundArea = i;
        this.displayStatus = z;
        this.enableStatus = z2;
        this.listeningStatus = z3;
        this.waitAwakeListeningStatus = z4;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("soundArea", this.soundArea);
            jSONObject.put("displayStatus", this.displayStatus);
            jSONObject.put("enableStatus", this.enableStatus);
            jSONObject.put("listeningStatus", this.listeningStatus);
            jSONObject.put("waitAwakeListeningStatus", this.waitAwakeListeningStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static SoundAreaStatus fromJson(String str) {
        boolean z;
        boolean z2;
        int i;
        boolean z3 = true;
        boolean z4 = false;
        try {
            JSONObject jSONObject = new JSONObject(str);
            z3 = jSONObject.optBoolean("displayStatus", true);
            z = jSONObject.optBoolean("enableStatus", false);
            try {
                z2 = jSONObject.optBoolean("listeningStatus", false);
                try {
                    z4 = jSONObject.optBoolean("waitAwakeListeningStatus", false);
                    i = jSONObject.optInt("soundArea");
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                    i = -1;
                    return new SoundAreaStatus(i, z3, z, z2, z4);
                }
            } catch (JSONException e2) {
                e = e2;
                z2 = false;
            }
        } catch (JSONException e3) {
            e = e3;
            z = false;
            z2 = false;
        }
        return new SoundAreaStatus(i, z3, z, z2, z4);
    }
}
