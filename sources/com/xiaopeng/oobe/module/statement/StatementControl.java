package com.xiaopeng.oobe.module.statement;

import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.base.R;
import com.xiaopeng.oobe.module.statement.StatementContract;
import com.xiaopeng.oobe.speech.OnSpeakEndCallback;
import com.xiaopeng.oobe.speech.SpeechHelper;
import com.xiaopeng.oobe.utils.CarStatusUtils;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.utils.SettingsUtil;
import com.xiaopeng.oobe.viewmodel.IBaseViewControl;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.oobe.viewmodel.IViewControl;
import com.xiaopeng.xui.app.XToast;
/* loaded from: classes.dex */
public class StatementControl extends IBaseViewControl implements IViewControl {
    private static final String TAG = "StatementControl";

    public StatementControl(IView iView) {
        super(iView);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        LogUtils.i(TAG, "onStart");
        super.onStart();
        SettingsUtil.setPrivacyReadyAgree(String.valueOf(false));
        checkPrivacyServiceConnection();
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl
    public void observeData() {
        super.observeData();
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onDestroy() {
        super.onDestroy();
        PrivacyHelper.getInstance().setPrivacyRead();
        PrivacyHelper.getInstance().setPrivacySign();
        SettingsUtil.setPrivacyReadyAgree(String.valueOf(true));
        SpeechHelper.getInstance().stop();
    }

    public void speakNeedSelectCheck() {
        if (CarStatusUtils.isEURegion()) {
            return;
        }
        final int i = R.string.statement_toast;
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.statement.-$$Lambda$StatementControl$2-EmVtVudPqAzh89B3iup4ebaWg
            @Override // java.lang.Runnable
            public final void run() {
                XToast.show(i);
            }
        });
        speak(App.getInstance().getString(i));
    }

    private void speak(String str) {
        SpeechHelper.getInstance().speak(str, new OnSpeakEndCallback() { // from class: com.xiaopeng.oobe.module.statement.StatementControl.1
            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onStart(String str2) {
                super.onStart(str2);
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onDone(String str2) {
                super.onDone(str2);
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onError(String str2) {
                super.onError(str2);
            }
        });
    }

    private void checkPrivacyServiceConnection() {
        PrivacyHelper.getInstance().setConnectCallback(new PrivacyHelper.IPrivacyServiceCallback() { // from class: com.xiaopeng.oobe.module.statement.-$$Lambda$StatementControl$3oXMd5PpL1S1ojl5ngoncFPFhqw
            @Override // com.xiaopeng.oobe.utils.PrivacyHelper.IPrivacyServiceCallback
            public final void connect(boolean z) {
                StatementControl.this.lambda$checkPrivacyServiceConnection$1$StatementControl(z);
            }
        });
    }

    public /* synthetic */ void lambda$checkPrivacyServiceConnection$1$StatementControl(boolean z) {
        if (!z || this.mRootView == null) {
            return;
        }
        ((StatementContract.View) this.mRootView).hideLoadingDialog();
    }
}
