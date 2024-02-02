package com.xiaopeng.speech.protocol.node.tts;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class TtsEchoValue {
    public boolean positive;
    public int soundArea;
    public String text;

    public TtsEchoValue(String str, int i, boolean z) {
        this.text = str;
        this.soundArea = i;
        this.positive = z;
    }

    public static TtsEchoValue fromJson(String str) {
        boolean z;
        String str2 = "";
        int i = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.optString("text");
            i = jSONObject.optInt("soundArea");
            z = jSONObject.optBoolean("positive");
        } catch (JSONException e) {
            e.printStackTrace();
            z = false;
        }
        return new TtsEchoValue(str2, i, z);
    }
}
