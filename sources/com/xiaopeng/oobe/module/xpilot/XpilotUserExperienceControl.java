package com.xiaopeng.oobe.module.xpilot;

import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.base.R;
import com.xiaopeng.oobe.module.xpilot.XpilotUserExperienceContract;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.utils.SPUtils;
import com.xiaopeng.oobe.utils.SettingsUtil;
import com.xiaopeng.oobe.viewmodel.IBaseViewControl;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.xui.app.XToast;
/* loaded from: classes.dex */
public class XpilotUserExperienceControl extends IBaseViewControl implements XpilotUserExperienceContract.ViewControl {
    private static final String TAG = "XpilotUserExperienceControl";

    public XpilotUserExperienceControl(IView iView) {
        super(iView);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        LogUtils.i(TAG, "onStart");
        super.onStart();
        SettingsUtil.setXpilotUserExperiencePlan(false);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl
    public void observeData() {
        super.observeData();
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy");
        reboot();
    }

    public void reboot() {
        String systemLan = App.getInstance().getSystemLan();
        String recordSystemLan = App.getInstance().getRecordSystemLan();
        Log.i(TAG, "reboot curLan=" + systemLan + ",recordLan:" + recordSystemLan);
        if (TextUtils.equals(systemLan, recordSystemLan)) {
            return;
        }
        final int i = R.string.lan_change_toast;
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.xpilot.-$$Lambda$XpilotUserExperienceControl$gJMTYiLH2GloensKSUlKHbj2plg
            @Override // java.lang.Runnable
            public final void run() {
                XpilotUserExperienceControl.this.lambda$reboot$0$XpilotUserExperienceControl(i);
            }
        });
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.xpilot.-$$Lambda$XpilotUserExperienceControl$gHqUABIZ59rlfDd1a5eZv7wGN_Q
            @Override // java.lang.Runnable
            public final void run() {
                XpilotUserExperienceControl.lambda$reboot$1();
            }
        }, 3500L);
    }

    public /* synthetic */ void lambda$reboot$0$XpilotUserExperienceControl(int i) {
        XToast.show(i);
        SPUtils.setOobeFinishStep(6);
        if (this.mMainViewModel != null) {
            this.mMainViewModel.onDestroy();
        }
        Log.i(TAG, "set finish step,viewMode destroy");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$reboot$1() {
        Log.i(TAG, "oobe reboot");
        ((PowerManager) App.getInstance().getSystemService(PowerManager.class)).reboot("oobe reboot");
    }

    @Override // com.xiaopeng.oobe.module.xpilot.XpilotUserExperienceContract.ViewControl
    public void setXpilotPrivacyRead() {
        LogUtils.i(TAG, "setXpilotPrivacyRead");
        PrivacyHelper.getInstance().setXpilotPrivacyRead();
    }

    @Override // com.xiaopeng.oobe.module.xpilot.XpilotUserExperienceContract.ViewControl
    public void agree() {
        LogUtils.i(TAG, "agree");
        SettingsUtil.setXpilotUserExperiencePlan(true);
        if (this.mRootView != null) {
            this.mRootView.onShowNext();
        }
    }
}
