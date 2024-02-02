package com.xiaopeng.oobe.module.login;

import com.xiaopeng.oobe.bean.LoginResultBean;
/* loaded from: classes.dex */
public interface IOnLoginCallback {
    void onLoginResult(boolean z, LoginResultBean.UserInfo userInfo);

    void onQRCodeLoadFailed();

    void onQRCodeOverdue();

    void onReturnQRCode(String str);
}
