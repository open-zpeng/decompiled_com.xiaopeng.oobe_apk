package com.xiaopeng.privacyservice;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class DisplayUIStyle implements Parcelable {
    public static final Parcelable.Creator<DisplayUIStyle> CREATOR = new Parcelable.Creator<DisplayUIStyle>() { // from class: com.xiaopeng.privacyservice.DisplayUIStyle.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayUIStyle createFromParcel(Parcel parcel) {
            return new DisplayUIStyle(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayUIStyle[] newArray(int i) {
            return new DisplayUIStyle[i];
        }
    };
    private String areaType;
    private boolean darkMode;
    private int screenId;
    private boolean showAsSupperPanel;
    private boolean showCloseBtn;
    private boolean supportCancelDialogWhenTouchOutside;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DisplayUIStyle() {
        this.showCloseBtn = true;
        this.supportCancelDialogWhenTouchOutside = true;
    }

    protected DisplayUIStyle(Parcel parcel) {
        this.showCloseBtn = true;
        this.supportCancelDialogWhenTouchOutside = true;
        try {
            DisplayUIStyle displayUIStyle = (DisplayUIStyle) GsonUtil.fromJson(parcel.readString(), DisplayUIStyle.class);
            if (displayUIStyle != null) {
                this.showCloseBtn = displayUIStyle.isShowCloseBtn();
                this.darkMode = displayUIStyle.isDarkMode();
                this.showAsSupperPanel = displayUIStyle.isShowAsSupperPanel();
                this.screenId = displayUIStyle.getScreenId();
                this.areaType = displayUIStyle.getAreaType();
                this.supportCancelDialogWhenTouchOutside = displayUIStyle.isSupportCancelDialogWhenTouchOutside();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShowCloseBtn() {
        return this.showCloseBtn;
    }

    public void setShowCloseBtn(boolean z) {
        this.showCloseBtn = z;
    }

    public boolean isDarkMode() {
        return this.darkMode;
    }

    public void setDarkMode(boolean z) {
        this.darkMode = z;
    }

    public int getScreenId() {
        return this.screenId;
    }

    public void setScreenId(int i) {
        this.screenId = i;
    }

    public String getAreaType() {
        return this.areaType;
    }

    public void setAreaType(String str) {
        this.areaType = str;
    }

    public boolean isShowAsSupperPanel() {
        return this.showAsSupperPanel;
    }

    public void setShowAsSupperPanel(boolean z) {
        this.showAsSupperPanel = z;
    }

    public boolean isSupportCancelDialogWhenTouchOutside() {
        return this.supportCancelDialogWhenTouchOutside;
    }

    public void setSupportCancelDialogWhenTouchOutside(boolean z) {
        this.supportCancelDialogWhenTouchOutside = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeString(GsonUtil.toJson(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
