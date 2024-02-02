package com.xiaopeng.speech.jarvisproto;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class AvatarStatus extends JarvisProto implements Parcelable {
    public static final Parcelable.Creator<AvatarStatus> CREATOR = new Parcelable.Creator<AvatarStatus>() { // from class: com.xiaopeng.speech.jarvisproto.AvatarStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AvatarStatus createFromParcel(Parcel parcel) {
            return new AvatarStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AvatarStatus[] newArray(int i) {
            return new AvatarStatus[i];
        }
    };
    public static final String EVENT = "jarvis.speech.avatar.status";
    public boolean backScreenAvatarShow;
    public boolean homeScreenAvatarShow;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getEvent() {
        return EVENT;
    }

    public AvatarStatus(boolean z) {
        this.homeScreenAvatarShow = z;
    }

    public AvatarStatus(boolean z, boolean z2) {
        this.homeScreenAvatarShow = z;
        this.backScreenAvatarShow = z2;
    }

    protected AvatarStatus(Parcel parcel) {
        this.homeScreenAvatarShow = parcel.readBoolean();
        this.backScreenAvatarShow = parcel.readBoolean();
    }

    @Override // com.xiaopeng.speech.jarvisproto.JarvisProto
    public String getJsonData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("homeScreenAvatarShow", this.homeScreenAvatarShow);
            jSONObject.put("backScreenAvatarShow", this.backScreenAvatarShow);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static AvatarStatus fromJson(String str) {
        boolean z;
        boolean z2 = false;
        try {
            JSONObject jSONObject = new JSONObject(str);
            z = jSONObject.optBoolean("homeScreenAvatarShow", false);
            try {
                z2 = jSONObject.optBoolean("backScreenAvatarShow", false);
            } catch (JSONException e) {
                e = e;
                e.printStackTrace();
                return new AvatarStatus(z, z2);
            }
        } catch (JSONException e2) {
            e = e2;
            z = false;
        }
        return new AvatarStatus(z, z2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.homeScreenAvatarShow);
        parcel.writeBoolean(this.backScreenAvatarShow);
    }

    public void readFromParcel(Parcel parcel) {
        this.homeScreenAvatarShow = parcel.readBoolean();
        this.backScreenAvatarShow = parcel.readBoolean();
    }
}
