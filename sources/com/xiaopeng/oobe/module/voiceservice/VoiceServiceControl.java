package com.xiaopeng.oobe.module.voiceservice;

import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.audio.SoundPlayHelper;
import com.xiaopeng.oobe.base.R;
import com.xiaopeng.oobe.module.IWebpVisibility;
import com.xiaopeng.oobe.module.voiceservice.VoiceServiceContract;
import com.xiaopeng.oobe.speech.OnSpeakEndCallback;
import com.xiaopeng.oobe.speech.SpeechHelper;
import com.xiaopeng.oobe.utils.BIHelper;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.viewmodel.IBaseViewControl;
import com.xiaopeng.oobe.viewmodel.IView;
/* loaded from: classes.dex */
public class VoiceServiceControl extends IBaseViewControl implements VoiceServiceContract.ViewControl {
    private static final String TAG = "oobe-VoiceServiceControl";

    public VoiceServiceControl(IView iView) {
        super(iView);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        super.onStart();
        speak(App.getInstance().getString(R.string.authorized_start_tts), null);
        onWebp(8);
        if (this.mRootView != null) {
            setWebpVisibility(0);
            this.mRootView.updateTips("", "");
        }
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy");
        SpeechHelper.getInstance().stop();
    }

    @Override // com.xiaopeng.oobe.module.voiceservice.VoiceServiceContract.ViewControl
    public void openMicrophoneMuteAndUseVoiceService(boolean z) {
        LogUtils.i(TAG, "openMicrophoneMuteAndUseVoiceService:" + z);
        BIHelper.getInstance().sendData("P30004", z ? "B006" : "B007");
        BIHelper.getInstance().sendData("P30005", "B008");
        OOBEHelper.getInstance().setMicrophoneMute(false);
        SpeechHelper.getInstance().setVoiceAuthorization(z);
    }

    @Override // com.xiaopeng.oobe.module.voiceservice.VoiceServiceContract.ViewControl
    public void showSuccess() {
        LogUtils.i(TAG, "showSuccess: ");
        SpeechHelper.getInstance().stop();
        if (this.mRootView != null) {
            this.mRootView.onWebp(8);
            this.mRootView.updateTips(App.getInstance().getString(R.string.oobe_setting_finish_tips), "");
        }
        SoundPlayHelper.instance().play(R.raw.audio_oobe_ok);
        final String string = App.getInstance().getString(R.string.oobe_setting_finish_speech);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.module.voiceservice.-$$Lambda$VoiceServiceControl$wc-qXaljNL8dUMeV-DAsW6rUcSc
            @Override // java.lang.Runnable
            public final void run() {
                VoiceServiceControl.this.lambda$showSuccess$1$VoiceServiceControl(string);
            }
        });
    }

    public /* synthetic */ void lambda$showSuccess$1$VoiceServiceControl(String str) {
        speak(str, new Runnable() { // from class: com.xiaopeng.oobe.module.voiceservice.-$$Lambda$VoiceServiceControl$kkLvjaurtJrmcgUbOF_HojYxXCs
            @Override // java.lang.Runnable
            public final void run() {
                VoiceServiceControl.this.lambda$null$0$VoiceServiceControl();
            }
        });
    }

    public /* synthetic */ void lambda$null$0$VoiceServiceControl() {
        if (this.mRootView != null) {
            this.mRootView.onShowNext();
        }
    }

    private void speak(String str, final Runnable runnable) {
        SpeechHelper.getInstance().speak(str, new OnSpeakEndCallback() { // from class: com.xiaopeng.oobe.module.voiceservice.VoiceServiceControl.1
            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onStart(String str2) {
                super.onStart(str2);
                LogUtils.i(VoiceServiceControl.TAG, "speak onStart: " + str2);
                if (VoiceServiceControl.this.mRootView != null) {
                    VoiceServiceControl.this.mRootView.onWebp(2);
                }
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onDone(String str2) {
                super.onDone(str2);
                LogUtils.i(VoiceServiceControl.TAG, "speak onDone: " + str2);
                if (VoiceServiceControl.this.mRootView != null) {
                    VoiceServiceControl.this.mRootView.onWebp(1);
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.postBackground(runnable2);
                }
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onError(String str2) {
                super.onError(str2);
                LogUtils.i(VoiceServiceControl.TAG, "speak onError: " + str2);
                if (VoiceServiceControl.this.mRootView != null) {
                    VoiceServiceControl.this.mRootView.onWebp(1);
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.postBackground(runnable2);
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onStop(String str2, boolean z) {
                super.onStop(str2, z);
                LogUtils.i(VoiceServiceControl.TAG, "onStop: " + str2);
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
