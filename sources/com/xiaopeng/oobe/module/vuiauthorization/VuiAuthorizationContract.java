package com.xiaopeng.oobe.module.vuiauthorization;

import com.xiaopeng.oobe.module.IWebpVisibility;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.oobe.viewmodel.IViewControl;
/* loaded from: classes.dex */
public class VuiAuthorizationContract {

    /* loaded from: classes.dex */
    public interface IVuiAuthorization {
        void onVuiAuthorization(boolean z);
    }

    /* loaded from: classes.dex */
    public interface View extends IVuiAuthorization, IView, IWebpVisibility {
    }

    /* loaded from: classes.dex */
    public interface ViewControl extends IViewControl {
        void setUserChecked(boolean z);

        void vuiAuthorization(boolean z);
    }
}
