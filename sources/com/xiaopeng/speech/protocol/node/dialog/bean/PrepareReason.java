package com.xiaopeng.speech.protocol.node.dialog.bean;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PrepareReason {
    public boolean isPreparing;
    public int screen;

    public PrepareReason(boolean z) {
        this.isPreparing = z;
    }

    public PrepareReason(int i, boolean z) {
        this.screen = i;
        this.isPreparing = z;
    }

    public static PrepareReason fromJson(String str) {
        boolean z;
        int i = 0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            i = jSONObject.optInt("screen");
            z = jSONObject.optBoolean("isPreparing");
        } catch (JSONException e) {
            e.printStackTrace();
            z = true;
        }
        return new PrepareReason(i, z);
    }
}
