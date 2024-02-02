package com.xiaopeng.privacyservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import java.util.UUID;
/* loaded from: classes.dex */
public class ProtocolInfo implements Parcelable {
    public static final Parcelable.Creator<ProtocolInfo> CREATOR = new Parcelable.Creator<ProtocolInfo>() { // from class: com.xiaopeng.privacyservice.ProtocolInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProtocolInfo createFromParcel(Parcel parcel) {
            return new ProtocolInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProtocolInfo[] newArray(int i) {
            return new ProtocolInfo[i];
        }
    };
    private List<CheckTip> checkTips;
    private String negativeBtnText;
    private String positiveBtnText;
    private boolean showCheckBox;
    private String title;
    private int type;
    private DisplayUIStyle uiStyle;
    private String url;
    private String uuid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ProtocolInfo() {
        this.uuid = UUID.randomUUID().toString();
    }

    public ProtocolInfo(int i, String str, String str2) {
        this.uuid = UUID.randomUUID().toString();
        this.title = str;
        this.url = str2;
    }

    protected ProtocolInfo(Parcel parcel) {
        try {
            ProtocolInfo protocolInfo = (ProtocolInfo) GsonUtil.fromJson(parcel.readString(), ProtocolInfo.class);
            if (protocolInfo != null) {
                this.uuid = protocolInfo.getUuid();
                this.type = protocolInfo.getType();
                this.title = protocolInfo.getTitle();
                this.url = protocolInfo.getUrl();
                this.positiveBtnText = protocolInfo.getPositiveBtnText();
                this.negativeBtnText = protocolInfo.getNegativeBtnText();
                this.showCheckBox = protocolInfo.isShowCheckBox();
                this.checkTips = protocolInfo.getCheckTips();
                this.uiStyle = protocolInfo.getUiStyle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getPositiveBtnText() {
        return this.positiveBtnText;
    }

    public void setPositiveBtnText(String str) {
        this.positiveBtnText = str;
    }

    public String getNegativeBtnText() {
        return this.negativeBtnText;
    }

    public void setNegativeBtnText(String str) {
        this.negativeBtnText = str;
    }

    public boolean isShowCheckBox() {
        return this.showCheckBox;
    }

    public void setShowCheckBox(boolean z) {
        this.showCheckBox = z;
    }

    public List<CheckTip> getCheckTips() {
        return this.checkTips;
    }

    public void setCheckTips(List<CheckTip> list) {
        this.checkTips = list;
    }

    public DisplayUIStyle getUiStyle() {
        return this.uiStyle;
    }

    public void setUiStyle(DisplayUIStyle displayUIStyle) {
        this.uiStyle = displayUIStyle;
    }

    public boolean isShowAsSupperPanel() {
        DisplayUIStyle displayUIStyle = this.uiStyle;
        return displayUIStyle != null && displayUIStyle.isShowAsSupperPanel();
    }

    public int getDisplayWindowScreenId() {
        DisplayUIStyle displayUIStyle = this.uiStyle;
        if (displayUIStyle != null) {
            return displayUIStyle.getScreenId();
        }
        return 0;
    }

    public String getDisplayWindowArea() {
        DisplayUIStyle displayUIStyle = this.uiStyle;
        if (displayUIStyle != null) {
            return displayUIStyle.getAreaType();
        }
        return null;
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
