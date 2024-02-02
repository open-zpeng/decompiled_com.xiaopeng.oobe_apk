package com.xiaopeng.oobe.viewmodel.main;

import com.xiaopeng.oobe.module.login.IOnLoginCallback;
import com.xiaopeng.oobe.network.IOnConnectionCheckedListener;
import com.xiaopeng.oobe.viewmodel.IBaseViewModel;
/* loaded from: classes.dex */
public interface IMainViewModel extends IBaseViewModel, IOnLoginCallback, IOnConnectionCheckedListener {
    void checkConnection();

    void getLoginQRCode();
}
