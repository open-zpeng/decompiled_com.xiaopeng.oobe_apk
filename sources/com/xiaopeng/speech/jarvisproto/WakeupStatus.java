package com.xiaopeng.speech.jarvisproto;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class WakeupStatus extends JarvisProto {
    public static final String EVENT = "jarvis.wakeup.status";
    public static final int WAKEUP_DISABLED = 1;
    public static final int WAKEUP_ENABLED = 0;
    public static final int WAKEUP_STATUS_NOTIFY_TYPE_INFOFLOW = 1;
    public static final int WAKEUP_STATUS_NOTIFY_TYPE_NONE = 0;
    public static final int WAKEUP_STATUS_NOTIFY_TYPE_TOAST = 2;
    public static final int WAKEUP_STATUS_NOTIFY_TYPE_TTS = 4;
    public static final int WAKEUP_TYPE_ALL = 1;
    public static final int WAKEUP_TYPE_INTERRUPT_WAKEUP_WORD = 8;
    public static final int WAKEUP_TYPE_MAIN_WAKEUP_WORD = 2;
    public static final int WAKEUP_TYPE_WHEEL_WAKEUP = 4;
    public String mInfo;
    public int mStatus;
    public int mType;

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    public WakeupStatus(int i, int i2, String str) {
        this.mStatus = -1;
        this.mType = -1;
        this.mInfo = null;
        this.mStatus = i;
        this.mType = i2;
        this.mInfo = str;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", this.mStatus);
            jSONObject.put("type", this.mType);
            jSONObject.put("info", this.mInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static WakeupStatus fromJson(String str) {
        int i;
        String str2;
        int i2 = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            i = jSONObject.optInt("status");
            try {
                i2 = jSONObject.optInt("type");
                str2 = jSONObject.optString("info");
            } catch (JSONException e) {
                e = e;
                e.printStackTrace();
                str2 = null;
                return new WakeupStatus(i, i2, str2);
            }
        } catch (JSONException e2) {
            e = e2;
            i = -1;
        }
        return new WakeupStatus(i, i2, str2);
    }
}
