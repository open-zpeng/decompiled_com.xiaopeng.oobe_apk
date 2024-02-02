package com.xiaopeng.speech.jarvisproto;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DialogSoundAreaStatus extends JarvisProto {
    public static final String EVENT = "jarvis.dialog.sound.area.status";
    public boolean listeningStatus;
    public boolean recognizingStatus;
    public int screen;
    public int soundArea;
    public boolean ttsPlayingStatus;
    public boolean underStandingStatus;

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    public DialogSoundAreaStatus(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        this(0, i, z, z2, z3, z4);
    }

    public DialogSoundAreaStatus(int i, int i2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.screen = i;
        this.soundArea = i2;
        this.listeningStatus = z;
        this.recognizingStatus = z2;
        this.underStandingStatus = z3;
        this.ttsPlayingStatus = z4;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("screen", this.screen);
            jSONObject.put("soundArea", this.soundArea);
            jSONObject.put("listeningStatus", this.listeningStatus);
            jSONObject.put("recognizingStatus", this.recognizingStatus);
            jSONObject.put("underStandingStatus", this.underStandingStatus);
            jSONObject.put("ttsPlayingStatus", this.ttsPlayingStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static DialogSoundAreaStatus fromJson(String str) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i = 0;
        int i2 = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            z = jSONObject.optBoolean("listeningStatus", false);
            try {
                z2 = jSONObject.optBoolean("recognizingStatus", false);
                try {
                    z3 = jSONObject.optBoolean("underStandingStatus", false);
                    try {
                        z4 = jSONObject.optBoolean("ttsPlayingStatus", false);
                        try {
                            i2 = jSONObject.optInt("soundArea");
                            i = jSONObject.optInt("screen");
                        } catch (JSONException e) {
                            e = e;
                            e.printStackTrace();
                            return new DialogSoundAreaStatus(i, i2, z, z2, z3, z4);
                        }
                    } catch (JSONException e2) {
                        e = e2;
                        z4 = false;
                    }
                } catch (JSONException e3) {
                    e = e3;
                    z3 = false;
                    z4 = z3;
                    e.printStackTrace();
                    return new DialogSoundAreaStatus(i, i2, z, z2, z3, z4);
                }
            } catch (JSONException e4) {
                e = e4;
                z2 = false;
                z3 = z2;
                z4 = z3;
                e.printStackTrace();
                return new DialogSoundAreaStatus(i, i2, z, z2, z3, z4);
            }
        } catch (JSONException e5) {
            e = e5;
            z = false;
            z2 = false;
        }
        return new DialogSoundAreaStatus(i, i2, z, z2, z3, z4);
    }

    public boolean reset() {
        if (this.listeningStatus || this.recognizingStatus || this.underStandingStatus || this.ttsPlayingStatus) {
            this.listeningStatus = false;
            this.recognizingStatus = false;
            this.underStandingStatus = false;
            this.ttsPlayingStatus = false;
            return true;
        }
        return false;
    }
}
