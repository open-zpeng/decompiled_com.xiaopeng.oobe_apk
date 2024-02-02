package com.xiaopeng.speech.protocol.node.dialog.bean;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DialogActiveReason {
    public int soundArea;

    public DialogActiveReason(int i) {
        this.soundArea = -1;
        this.soundArea = i;
    }

    public static DialogActiveReason fromJson(String str) {
        int i;
        try {
            i = new JSONObject(str).optInt("soundArea");
        } catch (JSONException e) {
            e.printStackTrace();
            i = -1;
        }
        return new DialogActiveReason(i);
    }
}
