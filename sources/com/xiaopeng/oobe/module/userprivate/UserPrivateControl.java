package com.xiaopeng.oobe.module.userprivate;

import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.utils.SettingsUtil;
import com.xiaopeng.oobe.viewmodel.IBaseViewControl;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.oobe.viewmodel.IViewControl;
/* loaded from: classes.dex */
public class UserPrivateControl extends IBaseViewControl implements IViewControl {
    private static final String TAG = "UserPrivateControl";

    public UserPrivateControl(IView iView) {
        super(iView);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        LogUtils.i(TAG, "onStart");
        super.onStart();
    }

    public void signPrivacyProtocol(boolean z) {
        if (z) {
            PrivacyHelper.getInstance().setPrivacyRead();
            PrivacyHelper.getInstance().setPrivacySign();
            SettingsUtil.setPrivacyReadyAgree(String.valueOf(true));
        }
    }

    public void fetchLatestProtocol() {
        PrivacyHelper.getInstance().fetchLatestProtocol();
    }

    public void showPrivacy() {
        PrivacyHelper.getInstance().showPrivacy(null);
    }
}
