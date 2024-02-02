package com.xiaopeng.speech.protocol.node.dialog.bean;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DialogExitReason {
    public String reason;
    public int soundArea;

    public DialogExitReason(String str, int i) {
        this.soundArea = -1;
        this.reason = str;
        this.soundArea = i;
    }

    public static DialogExitReason fromJson(String str) {
        int i;
        String str2 = "normal";
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.optString("reason");
            i = jSONObject.optInt("soundArea");
        } catch (JSONException e) {
            e.printStackTrace();
            i = -1;
        }
        return new DialogExitReason(str2, i);
    }
}
