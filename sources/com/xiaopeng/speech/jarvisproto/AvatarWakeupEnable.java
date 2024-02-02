package com.xiaopeng.speech.jarvisproto;

import androidx.core.app.NotificationCompat;
import com.lzy.okgo.cache.CacheEntity;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class AvatarWakeupEnable extends JarvisProto {
    public static final String EVENT = "jarvis.avatar.wakeup.enable";
    public String data;

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    public AvatarWakeupEnable(String str) {
        this.data = str;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NotificationCompat.CATEGORY_EVENT, EVENT);
            jSONObject.put(CacheEntity.DATA, this.data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
