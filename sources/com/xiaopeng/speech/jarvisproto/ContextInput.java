package com.xiaopeng.speech.jarvisproto;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ContextInput extends JarvisProto {
    public static final String EVENT = "jarvis.context.input";
    public boolean invalid;
    public boolean isEof;
    public boolean isInterrupted;
    public String pinyin;
    public int screen;
    public int soundArea;
    public String text;

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    public ContextInput() {
        this.pinyin = "";
    }

    public ContextInput(int i, int i2, String str, boolean z, boolean z2, boolean z3, String str2) {
        this.pinyin = "";
        this.screen = i;
        this.soundArea = i2;
        this.text = str;
        this.isEof = z;
        this.invalid = z2;
        this.isInterrupted = z3;
        this.pinyin = str2;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("text", this.text);
            jSONObject.put("isEof", this.isEof);
            jSONObject.put("isInterrupted", this.isInterrupted);
            jSONObject.put("pinyin", this.pinyin);
            jSONObject.put("soundArea", this.soundArea);
            jSONObject.put("invalid", this.invalid);
            jSONObject.put("screen", this.screen);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static ContextInput fromJson(String str) {
        String str2;
        boolean z;
        boolean z2;
        boolean z3;
        String str3 = "";
        int i = 0;
        int i2 = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.optString("text");
            try {
                z = jSONObject.optBoolean("isEof");
                try {
                    z2 = jSONObject.optBoolean("isInterrupted");
                    try {
                        str3 = jSONObject.optString("pinyin");
                        i2 = jSONObject.optInt("soundArea");
                        z3 = jSONObject.optBoolean("invalid");
                        try {
                            i = jSONObject.optInt("screen");
                        } catch (JSONException e) {
                            e = e;
                            e.printStackTrace();
                            return new ContextInput(i, i2, str2, z, z3, z2, str3);
                        }
                    } catch (JSONException e2) {
                        e = e2;
                        z3 = false;
                    }
                } catch (JSONException e3) {
                    e = e3;
                    z2 = false;
                    z3 = z2;
                    e.printStackTrace();
                    return new ContextInput(i, i2, str2, z, z3, z2, str3);
                }
            } catch (JSONException e4) {
                e = e4;
                z = false;
                z2 = false;
                z3 = z2;
                e.printStackTrace();
                return new ContextInput(i, i2, str2, z, z3, z2, str3);
            }
        } catch (JSONException e5) {
            e = e5;
            str2 = "";
        }
        return new ContextInput(i, i2, str2, z, z3, z2, str3);
    }
}
