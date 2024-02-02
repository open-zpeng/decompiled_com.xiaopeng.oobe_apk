package com.xiaopeng.privacyservice;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class CheckTip implements Parcelable {
    public static final Parcelable.Creator<CheckTip> CREATOR = new Parcelable.Creator<CheckTip>() { // from class: com.xiaopeng.privacyservice.CheckTip.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CheckTip createFromParcel(Parcel parcel) {
            return new CheckTip(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CheckTip[] newArray(int i) {
            return new CheckTip[i];
        }
    };
    private String associatedFileUrl;
    private boolean clickable;
    private String color;
    private String text;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CheckTip() {
    }

    protected CheckTip(Parcel parcel) {
        try {
            CheckTip checkTip = (CheckTip) GsonUtil.fromJson(parcel.readString(), CheckTip.class);
            if (checkTip != null) {
                this.text = checkTip.getText();
                this.color = checkTip.getColor();
                this.clickable = checkTip.isClickable();
                this.associatedFileUrl = checkTip.getAssociatedFileUrl();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeString(GsonUtil.toJson(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public void setClickable(boolean z) {
        this.clickable = z;
    }

    public String getAssociatedFileUrl() {
        return this.associatedFileUrl;
    }

    public void setAssociatedFileUrl(String str) {
        this.associatedFileUrl = str;
    }
}
