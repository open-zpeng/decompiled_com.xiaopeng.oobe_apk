package com.xiaopeng.oobe.bean;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes.dex */
public class LoginResultBean {
    boolean result;
    @SerializedName("user_info")
    UserInfo userInfo;

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /* loaded from: classes.dex */
    public static class UserInfo {
        @SerializedName("image_url")
        String imageUrl;
        String name;

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }
    }
}
