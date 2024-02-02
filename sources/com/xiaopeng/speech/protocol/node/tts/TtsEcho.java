package com.xiaopeng.speech.protocol.node.tts;

import android.text.TextUtils;
import com.xiaopeng.libconfig.ipc.AccountConfig;
import com.xiaopeng.speech.jarvisproto.JarvisProto;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class TtsEcho extends JarvisProto {
    public static final String EVENT_TTS_ECHO = "jarvis.tts.echo";
    public static final int TYPE_FAIL = 2;
    public static final int TYPE_LIGHTNING = 3;
    public static final int TYPE_SQUARE = 4;
    public static final int TYPE_SUCCESS = 1;
    public String msgId;
    public int screen;
    public int soundArea;
    public String text;
    public long timestamp;
    public int type;

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT_TTS_ECHO;
    }

    public TtsEcho(int i) {
        this(0, i, null, null, 0, 0L);
    }

    public TtsEcho(int i, String str, String str2, int i2, long j) {
        this(0, i, str, str2, i2, j);
    }

    public TtsEcho(int i, int i2, String str, String str2, int i3, long j) {
        this.screen = i;
        this.soundArea = i2;
        this.msgId = str;
        this.text = str2;
        this.type = i3;
        this.timestamp = j;
    }

    public TtsEcho(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.screen = jSONObject.optInt("screen");
            this.soundArea = jSONObject.optInt("soundArea");
            this.msgId = jSONObject.optString("msgId");
            this.text = jSONObject.optString("text");
            this.type = jSONObject.optInt("type");
            this.timestamp = jSONObject.optLong(AccountConfig.KEY_TIMESTAMP);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TtsEcho) {
            TtsEcho ttsEcho = (TtsEcho) obj;
            return this.screen == ttsEcho.screen && this.soundArea == ttsEcho.soundArea && this.type == ttsEcho.type && this.timestamp == ttsEcho.timestamp && Objects.equals(this.msgId, ttsEcho.msgId) && Objects.equals(this.text, ttsEcho.text);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.screen), Integer.valueOf(this.soundArea), this.msgId, this.text, Integer.valueOf(this.type), Long.valueOf(this.timestamp));
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("screen", this.screen);
            jSONObject.put("soundArea", this.soundArea);
            jSONObject.put("msgId", this.msgId);
            jSONObject.put("text", this.text);
            jSONObject.put("type", this.type);
            jSONObject.put(AccountConfig.KEY_TIMESTAMP, this.timestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static boolean isEquals(TtsEcho ttsEcho, TtsEcho ttsEcho2) {
        if (ttsEcho == null) {
            return ttsEcho2 == null;
        }
        return ttsEcho.equals(ttsEcho2);
    }

    public static TtsEcho fromJson(String str) {
        String str2;
        int i;
        String str3 = "";
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i2 = -1;
        long j = 0;
        int i3 = 0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            i = jSONObject.optInt("screen");
            try {
                i2 = jSONObject.optInt("soundArea");
                str2 = jSONObject.optString("msgId");
                try {
                    str3 = jSONObject.optString("text");
                    i3 = jSONObject.optInt("type");
                    j = jSONObject.optLong(AccountConfig.KEY_TIMESTAMP);
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                    return new TtsEcho(i, i2, str2, str3, i3, j);
                }
            } catch (JSONException e2) {
                e = e2;
                str2 = "";
            }
        } catch (JSONException e3) {
            e = e3;
            str2 = "";
            i = 0;
        }
        return new TtsEcho(i, i2, str2, str3, i3, j);
    }
}
