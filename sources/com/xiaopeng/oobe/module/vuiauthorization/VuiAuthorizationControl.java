package com.xiaopeng.oobe.module.vuiauthorization;

import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.base.R;
import com.xiaopeng.oobe.module.IWebpVisibility;
import com.xiaopeng.oobe.module.vuiauthorization.VuiAuthorizationContract;
import com.xiaopeng.oobe.speech.OnSpeakEndCallback;
import com.xiaopeng.oobe.speech.SpeechHelper;
import com.xiaopeng.oobe.utils.BIHelper;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.utils.SettingsUtil;
import com.xiaopeng.oobe.viewmodel.IBaseViewControl;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.xui.app.XToast;
/* loaded from: classes.dex */
public class VuiAuthorizationControl extends IBaseViewControl implements VuiAuthorizationContract.ViewControl {
    private static final String TAG = "oobe-VuiAuthorizationControl";

    public VuiAuthorizationControl(IView iView) {
        super(iView);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        super.onStart();
        speak(App.getInstance().getString(R.string.vui_authorized_start_tts), null);
        setUserChecked(false);
        onWebp(8);
        if (this.mRootView != null) {
            setWebpVisibility(0);
            this.mRootView.updateTips("", "");
        }
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy USER_CHECKED:" + SettingsUtil.getSpeechPlanProtocol());
        SpeechHelper.getInstance().stop();
    }

    @Override // com.xiaopeng.oobe.module.vuiauthorization.VuiAuthorizationContract.ViewControl
    public void vuiAuthorization(final boolean z) {
        LogUtils.i(TAG, "vuiAuthorization confirmOpen:" + z);
        if (!z) {
            setUserChecked(false);
        }
        BIHelper.getInstance().sendData("P30001", z ? "B001" : "B002");
        if (OOBEHelper.isNotSupportVuiAuthorization() && this.mRootView != null) {
            LogUtils.i(TAG, "H93 is not support");
            ((VuiAuthorizationContract.IVuiAuthorization) this.mRootView).onVuiAuthorization(z);
            return;
        }
        SpeechHelper.getInstance().setVuiAuthorization(z, new SpeechHelper.OnVuiListener() { // from class: com.xiaopeng.oobe.module.vuiauthorization.-$$Lambda$VuiAuthorizationControl$3xse1L1oUt65p9j9FJX6B54_D8Y
            @Override // com.xiaopeng.oobe.speech.SpeechHelper.OnVuiListener
            public final void onConfigSetResult(boolean z2, String str) {
                VuiAuthorizationControl.this.lambda$vuiAuthorization$2$VuiAuthorizationControl(z, z2, str);
            }
        });
    }

    public /* synthetic */ void lambda$vuiAuthorization$2$VuiAuthorizationControl(final boolean z, final boolean z2, String str) {
        LogUtils.i(TAG, "vuiAuthorization connect:" + z2 + ",rootView:" + this.mRootView);
        if (this.mRootView != null) {
            if (z2) {
                ((VuiAuthorizationContract.IVuiAuthorization) this.mRootView).onVuiAuthorization(z);
                return;
            }
            BIHelper.getInstance().sendData("P30001", "B010");
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorization.-$$Lambda$VuiAuthorizationControl$zu7BbkkfAIw9VD8HAUqGrz5O0zY
                @Override // java.lang.Runnable
                public final void run() {
                    XToast.showShort(R.string.vui_authorized_no_net_work_tts);
                }
            });
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorization.-$$Lambda$VuiAuthorizationControl$yyVJpARLJ9e1jHvGjJrrShd6560
                @Override // java.lang.Runnable
                public final void run() {
                    VuiAuthorizationControl.this.lambda$null$1$VuiAuthorizationControl(z2, z);
                }
            }, 3000L);
        }
    }

    public /* synthetic */ void lambda$null$1$VuiAuthorizationControl(boolean z, boolean z2) {
        if (this.mRootView != null) {
            ((VuiAuthorizationContract.IVuiAuthorization) this.mRootView).onVuiAuthorization(z && z2);
        }
    }

    @Override // com.xiaopeng.oobe.module.vuiauthorization.VuiAuthorizationContract.ViewControl
    public void setUserChecked(boolean z) {
        LogUtils.i(TAG, "setUserChecked check:" + z);
        SettingsUtil.setSpeechPlanProtocol(String.valueOf(z));
        PrivacyHelper.getInstance().setSpeechPlanRead(z);
        if (z) {
            PrivacyHelper.getInstance().setSpeechPlanSign();
        }
        SpeechHelper.getInstance().setSpeechPlan(z);
    }

    private void speak(String str, final Runnable runnable) {
        SpeechHelper.getInstance().speak(str, new OnSpeakEndCallback() { // from class: com.xiaopeng.oobe.module.vuiauthorization.VuiAuthorizationControl.1
            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onStart(String str2) {
                super.onStart(str2);
                LogUtils.i(VuiAuthorizationControl.TAG, "speak onStart: " + str2);
                if (VuiAuthorizationControl.this.mRootView != null) {
                    VuiAuthorizationControl.this.mRootView.onWebp(2);
                }
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onDone(String str2) {
                super.onDone(str2);
                LogUtils.i(VuiAuthorizationControl.TAG, "speak onDone: " + str2);
                if (VuiAuthorizationControl.this.mRootView != null) {
                    VuiAuthorizationControl.this.mRootView.onWebp(1);
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.postBackground(runnable2);
                }
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onError(String str2) {
                super.onError(str2);
                LogUtils.i(VuiAuthorizationControl.TAG, "speak onError: " + str2);
                if (VuiAuthorizationControl.this.mRootView != null) {
                    VuiAuthorizationControl.this.mRootView.onWebp(1);
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.postBackground(runnable2);
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onStop(String str2, boolean z) {
                super.onStop(str2, z);
                LogUtils.i(VuiAuthorizationControl.TAG, "onStop: " + str2);
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.postBackground(runnable2);
                }
            }
        });
    }

    private void setWebpVisibility(int i) {
        if (this.mRootView != null) {
            ((IWebpVisibility) this.mRootView).setWebpVisibility(i);
        }
    }

    private void onWebp(int i) {
        if (this.mRootView != null) {
            this.mRootView.onWebp(i);
        }
    }
}
