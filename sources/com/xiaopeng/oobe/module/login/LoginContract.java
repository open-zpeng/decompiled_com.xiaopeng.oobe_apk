package com.xiaopeng.oobe.module.login;

import com.xiaopeng.oobe.module.IWebpVisibility;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.oobe.viewmodel.IViewControl;
/* loaded from: classes.dex */
public class LoginContract {
    public static final int STATE_FAIL = 2;
    public static final int STATE_LOADING = 0;
    public static final int STATE_SUCCESS = 1;

    /* loaded from: classes.dex */
    public interface ILoginView extends View, IView, IWebpVisibility {
    }

    /* loaded from: classes.dex */
    public interface View {
        void setQrCodeView(int i, String str);

        void showLoginSuccess(boolean z, String str, String str2);
    }

    /* loaded from: classes.dex */
    public interface ViewControl extends IViewControl {
        void refreshQrCode();
    }
}
