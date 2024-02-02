package com.xiaopeng.oobe.module.vuiauthorizationos5;

import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.audio.SoundPlayHelper;
import com.xiaopeng.oobe.base.R;
import com.xiaopeng.oobe.module.IWebpVisibility;
import com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Contract;
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
public class VuiAuthorizationOS5Control extends IBaseViewControl implements VuiAuthorizationOS5Contract.ViewControl {
    private static final String TAG = "oobe-VuiAuthorizationOS5Control";

    public VuiAuthorizationOS5Control(IView iView) {
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

    @Override // com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Contract.ViewControl
    public void vuiAuthorization(final boolean z) {
        LogUtils.i(TAG, "vuiAuthorization confirmOpen:" + z);
        if (!z) {
            setUserChecked(false);
        }
        SpeechHelper.getInstance().setVuiAuthorization(z, new SpeechHelper.OnVuiListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5Control$cdGenWFYKZOfVc6QhE7qoqx2eeE
            @Override // com.xiaopeng.oobe.speech.SpeechHelper.OnVuiListener
            public final void onConfigSetResult(boolean z2, String str) {
                VuiAuthorizationOS5Control.this.lambda$vuiAuthorization$2$VuiAuthorizationOS5Control(z, z2, str);
            }
        });
    }

    public /* synthetic */ void lambda$vuiAuthorization$2$VuiAuthorizationOS5Control(boolean z, boolean z2, String str) {
        LogUtils.i(TAG, "vuiAuthorization connect:" + z2 + ",rootView:" + this.mRootView);
        if (this.mRootView != null) {
            if (z2) {
                BIHelper.getInstance().sendData("P30005", "B008");
                ((VuiAuthorizationOS5Contract.IVuiAuthorization) this.mRootView).onVuiAuthorization(z);
                return;
            }
            BIHelper.getInstance().sendData(BIHelper.PageId.OS5.VUI_JUMPER_NOT_NET_WORK, "B010");
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5Control$1HLVfxKEeoQW5UQwuEkX1BzsW0A
                @Override // java.lang.Runnable
                public final void run() {
                    XToast.showShort(R.string.vui_authorized_no_net_work_tts);
                }
            });
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5Control$ixhDd7PqgvoEVmi5BVjCHHFqBWQ
                @Override // java.lang.Runnable
                public final void run() {
                    VuiAuthorizationOS5Control.this.lambda$null$1$VuiAuthorizationOS5Control();
                }
            }, 3000L);
        }
    }

    public /* synthetic */ void lambda$null$1$VuiAuthorizationOS5Control() {
        if (this.mRootView != null) {
            ((VuiAuthorizationOS5Contract.IVuiAuthorization) this.mRootView).onVuiAuthorization(false);
        }
    }

    @Override // com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Contract.ViewControl
    public void setOpenMicrophoneMute() {
        BIHelper.getInstance().sendData("P30005", "B008");
        OOBEHelper.getInstance().setMicrophoneMute(false);
    }

    @Override // com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Contract.ViewControl
    public void setUseVoiceService(boolean z) {
        LogUtils.i(TAG, "setUseVoiceService:" + z);
        BIHelper.getInstance().sendData("P30003", z ? "B004" : "B005");
        SpeechHelper.getInstance().setVoiceAuthorization(z);
    }

    @Override // com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Contract.ViewControl
    public void setScuDsmSwState(boolean z) {
        this.mMainViewModel.setScuDsmSwState(z);
    }

    @Override // com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Contract.ViewControl
    public void showSuccess() {
        LogUtils.i(TAG, "showSuccess: ");
        SpeechHelper.getInstance().stop();
        if (this.mRootView != null) {
            this.mRootView.onWebp(8);
            this.mRootView.updateTips(App.getInstance().getString(R.string.oobe_setting_finish_tips), "");
        }
        SoundPlayHelper.instance().play(R.raw.audio_oobe_ok);
        final String string = App.getInstance().getString(R.string.oobe_setting_finish_speech);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5Control$W4ns8BgiNHefoDHWDjeWquqVH24
            @Override // java.lang.Runnable
            public final void run() {
                VuiAuthorizationOS5Control.this.lambda$showSuccess$4$VuiAuthorizationOS5Control(string);
            }
        });
    }

    public /* synthetic */ void lambda$showSuccess$4$VuiAuthorizationOS5Control(String str) {
        speak(str, new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5Control$GzsNSR9yTo9MA9rCApP9H20B8ao
            @Override // java.lang.Runnable
            public final void run() {
                VuiAuthorizationOS5Control.this.lambda$null$3$VuiAuthorizationOS5Control();
            }
        });
    }

    public /* synthetic */ void lambda$null$3$VuiAuthorizationOS5Control() {
        if (this.mRootView != null) {
            this.mRootView.onShowNext();
        }
    }

    @Override // com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Contract.ViewControl
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
        SpeechHelper.getInstance().speak(str, new OnSpeakEndCallback() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Control.1
            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onStart(String str2) {
                super.onStart(str2);
                LogUtils.i(VuiAuthorizationOS5Control.TAG, "speak onStart: " + str2);
                if (VuiAuthorizationOS5Control.this.mRootView != null) {
                    VuiAuthorizationOS5Control.this.mRootView.onWebp(2);
                }
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onDone(String str2) {
                super.onDone(str2);
                LogUtils.i(VuiAuthorizationOS5Control.TAG, "speak onDone: " + str2);
                if (VuiAuthorizationOS5Control.this.mRootView != null) {
                    VuiAuthorizationOS5Control.this.mRootView.onWebp(1);
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.postBackground(runnable2);
                }
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onError(String str2) {
                super.onError(str2);
                LogUtils.i(VuiAuthorizationOS5Control.TAG, "speak onError: " + str2);
                if (VuiAuthorizationOS5Control.this.mRootView != null) {
                    VuiAuthorizationOS5Control.this.mRootView.onWebp(1);
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.postBackground(runnable2);
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onStop(String str2, boolean z) {
                super.onStop(str2, z);
                LogUtils.i(VuiAuthorizationOS5Control.TAG, "onStop: " + str2);
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
