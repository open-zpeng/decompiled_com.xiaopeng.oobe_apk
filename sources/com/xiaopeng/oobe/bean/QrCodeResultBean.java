package com.xiaopeng.oobe.bean;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes.dex */
public class QrCodeResultBean {
    public static final int CODE_SUCCESS = 0;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("qr_code_url")
    private String qrCodeUrl;

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public String getQrCodeUrl() {
        return this.qrCodeUrl;
    }

    public void setQrCodeUrl(String str) {
        this.qrCodeUrl = str;
    }
}
