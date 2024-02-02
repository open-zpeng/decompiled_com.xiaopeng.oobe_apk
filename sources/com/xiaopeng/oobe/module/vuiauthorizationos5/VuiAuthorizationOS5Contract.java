package com.xiaopeng.oobe.module.vuiauthorizationos5;

import com.xiaopeng.oobe.module.IWebpVisibility;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.oobe.viewmodel.IViewControl;
/* loaded from: classes.dex */
public class VuiAuthorizationOS5Contract {

    /* loaded from: classes.dex */
    public interface IVuiAuthorization {
        void onVuiAuthorization(boolean z);
    }

    /* loaded from: classes.dex */
    public interface View extends IVuiAuthorization, IView, IWebpVisibility {
    }

    /* loaded from: classes.dex */
    public interface ViewControl extends IViewControl {
        void setOpenMicrophoneMute();

        void setScuDsmSwState(boolean z);

        void setUseVoiceService(boolean z);

        void setUserChecked(boolean z);

        void showSuccess();

        void vuiAuthorization(boolean z);
    }
}
