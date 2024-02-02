package com.xiaopeng.oobe.unity;

import com.xiaopeng.oobe.module.IQuit;
import com.xiaopeng.oobe.module.IWebpVisibility;
import com.xiaopeng.oobe.module.init.InitContract;
import com.xiaopeng.oobe.module.login.LoginContract;
import com.xiaopeng.oobe.module.vuiauthorization.VuiAuthorizationContract;
import com.xiaopeng.oobe.viewmodel.IView;
/* loaded from: classes.dex */
public interface UnityListener extends IView, IWebpVisibility, IQuit, InitContract.View, LoginContract.View, VuiAuthorizationContract.IVuiAuthorization {
    void disableOOBE();

    void setPrivacyName();

    void showOOBE(int i);
}
