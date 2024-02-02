package com.xiaopeng.speech.jarvisproto;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SwitchStatus extends JarvisProto implements Parcelable {
    public static final Parcelable.Creator<SwitchStatus> CREATOR = new Parcelable.Creator<SwitchStatus>() { // from class: com.xiaopeng.speech.jarvisproto.SwitchStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SwitchStatus createFromParcel(Parcel parcel) {
            return new SwitchStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SwitchStatus[] newArray(int i) {
            return new SwitchStatus[i];
        }
    };
    public static final String EVENT = "jarvis.speech.switch.status";
    public int sceneType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    public SwitchStatus(int i) {
        this.sceneType = i;
    }

    protected SwitchStatus(Parcel parcel) {
        this.sceneType = parcel.readInt();
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sceneType", this.sceneType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static SwitchStatus fromJson(String str) {
        int i = 0;
        try {
            i = new JSONObject(str).optInt("sceneType", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new SwitchStatus(i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.sceneType);
    }

    public void readFromParcel(Parcel parcel) {
        this.sceneType = parcel.readInt();
    }
}
