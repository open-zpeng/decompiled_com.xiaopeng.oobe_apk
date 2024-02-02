package com.xiaopeng.oobe.module.login;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Observer;
import com.google.gson.Gson;
import com.xiaopeng.lib.bughunter.anr.UILooperObserver;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.NetUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.audio.SoundPlayHelper;
import com.xiaopeng.oobe.base.R;
import com.xiaopeng.oobe.bean.LoginResultBean;
import com.xiaopeng.oobe.module.IWebpVisibility;
import com.xiaopeng.oobe.module.login.LoginContract;
import com.xiaopeng.oobe.speech.OnSpeakEndCallback;
import com.xiaopeng.oobe.speech.SpeechHelper;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.SPUtils;
import com.xiaopeng.oobe.viewmodel.IBaseViewControl;
import com.xiaopeng.oobe.viewmodel.IView;
/* loaded from: classes.dex */
public class LoginControl extends IBaseViewControl implements LoginContract.ViewControl {
    private static final int MSG_LOADQRCODE_DELAYED = 30000;
    private static final int MSG_LOADQRCODE_TIMEOUT = 2000;
    private static final int MSG_RETRY = 1000;
    private static final int MSG_RETRY_TIME = 5000;
    private static final String TAG = "LoginControl";
    private Handler mHandler;
    private boolean mHasLoadQRCode;
    private boolean mIsLoginSuccess;
    private boolean mIsReturnLoadFailed;
    private boolean mLoginSuccess;
    private Observer<? super Boolean> mNetConnectObserve;

    public /* synthetic */ void lambda$new$0$LoginControl(Boolean bool) {
        String string;
        LogUtils.i(TAG, "getNetConnect: " + bool);
        if (this.mLoginSuccess) {
            return;
        }
        if (bool.booleanValue()) {
            string = App.getInstance().getString(R.string.connection_tips_ok);
        } else {
            string = App.getInstance().getString(R.string.connection_tips_error);
        }
        speak(string, null);
        if (this.mLoginSuccess) {
            if (bool.booleanValue()) {
                return;
            }
            networkError();
        } else if (this.mRootView != null && !bool.booleanValue() && !this.mHasLoadQRCode) {
            networkError();
        } else {
            loadQRCode();
        }
    }

    public LoginControl(IView iView) {
        super(iView);
        this.mLoginSuccess = false;
        this.mHasLoadQRCode = false;
        this.mNetConnectObserve = new Observer() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginControl$AIBKtodoVsx7rdZX6SJwg7n7gYQ
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LoginControl.this.lambda$new$0$LoginControl((Boolean) obj);
            }
        };
        this.mIsReturnLoadFailed = false;
        this.mHandler = new Handler(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper()) { // from class: com.xiaopeng.oobe.module.login.LoginControl.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Log.e(LoginControl.TAG, "msg.what = " + message.what);
                int i = message.what;
                if (i == 1000) {
                    if (OOBEHelper.isLandScreen() || !LoginControl.this.mIsLoginSuccess) {
                        LoginControl.this.loadQRCode();
                    }
                } else if (i != 2000) {
                } else {
                    Log.e(LoginControl.TAG, "loadqrcode timeout ,mIsLoginSuccess = " + LoginControl.this.mIsLoginSuccess + " & mIsReturnLoadFailed = " + LoginControl.this.mIsReturnLoadFailed);
                    if (LoginControl.this.mIsLoginSuccess) {
                        return;
                    }
                    if (LoginControl.this.mIsReturnLoadFailed) {
                        if (LoginControl.this.mRootView != null) {
                            ((LoginContract.View) LoginControl.this.mRootView).setQrCodeView(2, null);
                            return;
                        }
                        return;
                    }
                    LoginControl.this.loadQRCodeFailed();
                }
            }
        };
    }

    private void loadQrcodeTimeOut() {
        Log.e(TAG, "loadQrcodeTimeOut 30 seconds");
        this.mHandler.removeMessages(2000);
        this.mHandler.sendEmptyMessageDelayed(2000, 30000L);
    }

    public void networkError() {
        this.mHandler.removeMessages(1000);
        this.mHandler.sendEmptyMessageDelayed(1000, UILooperObserver.ANR_TRIGGER_TIME);
    }

    public void loginFailed() {
        Log.e(TAG, "loginFailed");
        speak(App.getInstance().getString(R.string.login_failed), null);
        if (this.mRootView != null) {
            ((LoginContract.View) this.mRootView).showLoginSuccess(false, "", "");
        }
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        super.onStart();
        if (this.mMainViewModel.isLoginAlready()) {
            LoginResultBean.UserInfo loginUser = this.mMainViewModel.getLoginUser();
            this.mLoginSuccess = true;
            loginSuccess(loginUser);
            return;
        }
        this.mMainViewModel.startLoginStep();
        LogUtils.i(TAG, "onStart: mMainViewModel " + this.mMainViewModel);
        SoundPlayHelper.instance().play(SoundPlayHelper.instance().getLoginScanRes(), (MediaPlayer.OnCompletionListener) null);
        if (this.mRootView != null) {
            setWebpVisibility(8);
            this.mRootView.updateTips(App.getInstance().getString(R.string.login_title), App.getInstance().getString(R.string.init_text_sub_2));
            String loginQrcode = SPUtils.getLoginQrcode();
            LogUtils.i(TAG, "onCompletion :loginQrcode=" + loginQrcode);
            if ("-1".equals(loginQrcode)) {
                Log.e(TAG, "onCompletion:  loginqrcode is -1");
                this.mRootView.updateTips(App.getInstance().getString(R.string.login_refresh_failed), App.getInstance().getString(R.string.init_text_sub_2));
                ((LoginContract.View) this.mRootView).setQrCodeView(2, null);
            } else if (TextUtils.isEmpty(loginQrcode)) {
                Log.e(TAG, "onCompletion:  loginqrcode is empty");
                ((LoginContract.View) this.mRootView).setQrCodeView(0, null);
                loadQrcodeTimeOut();
            } else {
                Log.e(TAG, "onCompletion: showqrcode ");
                showQRCode(loginQrcode);
            }
        }
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl
    public void observeData() {
        super.observeData();
        this.mMainViewModel.getNetConnect().observe(this, this.mNetConnectObserve);
        this.mMainViewModel.getQrCodeUrl().observe(this, new Observer() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginControl$e8J34LX1aUr-QNmCyWcvli0d5f4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LoginControl.this.lambda$observeData$1$LoginControl((String) obj);
            }
        });
        this.mMainViewModel.getQrCodeOverDue().observe(this, new Observer() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginControl$XphmKBC8LCsSNOf05AmguMcxdGo
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LoginControl.this.lambda$observeData$2$LoginControl((Boolean) obj);
            }
        });
        this.mMainViewModel.getLoginSuccess().observe(this, new Observer() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginControl$9Q3fDscwJayDxuEzTE6GCeygMVE
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LoginControl.this.lambda$observeData$3$LoginControl((Boolean) obj);
            }
        });
        this.mMainViewModel.getMcuIgOff().observe(this, new Observer() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginControl$ynt9IVFOl4_GUl2ouRLGQYLzY_Y
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LoginControl.this.lambda$observeData$5$LoginControl((Boolean) obj);
            }
        });
    }

    public /* synthetic */ void lambda$observeData$1$LoginControl(String str) {
        LogUtils.i(TAG, "onReturnQRCode: " + str);
        if (this.mLoginSuccess) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.mHasLoadQRCode = false;
            loadQRCodeFailed();
            return;
        }
        this.mHasLoadQRCode = true;
        showQRCode(str);
    }

    public /* synthetic */ void lambda$observeData$2$LoginControl(Boolean bool) {
        LogUtils.i(TAG, "onQRCodeOverdue aBoolean:" + bool);
        if (!bool.booleanValue() || this.mLoginSuccess) {
            return;
        }
        this.mHasLoadQRCode = false;
        showQROverdue();
    }

    public /* synthetic */ void lambda$observeData$3$LoginControl(Boolean bool) {
        LoginResultBean.UserInfo loginUser = this.mMainViewModel.getLoginUser();
        LogUtils.i(TAG, "onLoginResult() called with: success = [" + bool + "], userInfo = [" + new Gson().toJson(loginUser) + "]");
        if (bool.booleanValue()) {
            this.mLoginSuccess = true;
            loginSuccess(loginUser);
            return;
        }
        loginFailed();
    }

    public /* synthetic */ void lambda$null$4$LoginControl(Boolean bool) {
        onMcuIgOff(bool.booleanValue());
    }

    public /* synthetic */ void lambda$observeData$5$LoginControl(final Boolean bool) {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginControl$y30WeGhyesAsyFSJrEaDwToOZls
            @Override // java.lang.Runnable
            public final void run() {
                LoginControl.this.lambda$null$4$LoginControl(bool);
            }
        }, 1000L);
    }

    private void onMcuIgOff(boolean z) {
        if (z || !this.mIsLoginSuccess || this.mMainViewModel == null) {
            return;
        }
        LoginResultBean.UserInfo loginUser = this.mMainViewModel.getLoginUser();
        LogUtils.i(TAG, "on mcu is IGon userInfo = [" + new Gson().toJson(loginUser) + "]");
        loginSuccess(loginUser);
    }

    public void showQRCode(String str) {
        LogUtils.i(TAG, "showQRCode codeUrl:" + str);
        if (this.mHandler.hasMessages(2000)) {
            this.mHandler.removeMessages(2000);
        }
        if (this.mRootView != null) {
            this.mRootView.updateTips(App.getInstance().getString(R.string.login_title), App.getInstance().getString(R.string.init_text_sub_2));
            ((LoginContract.View) this.mRootView).setQrCodeView(1, str);
        }
    }

    public void showQROverdue() {
        LogUtils.i(TAG, "showQROverdue mIsLoginSuccess:" + this.mIsLoginSuccess);
        if (this.mIsLoginSuccess) {
            return;
        }
        if (this.mHandler.hasMessages(2000)) {
            this.mHandler.removeMessages(2000);
        }
        if (this.mRootView != null) {
            this.mRootView.updateTips(App.getInstance().getString(R.string.login_overdue), App.getInstance().getString(R.string.init_text_sub_2));
            ((LoginContract.View) this.mRootView).setQrCodeView(2, null);
            speak(App.getInstance().getString(R.string.login_overdue) + App.getInstance().getString(R.string.login_refresh), null);
        }
    }

    public void loadQRCodeFailed() {
        LogUtils.i(TAG, "loadQRCodeFailed mIsLoginSuccess:" + this.mIsLoginSuccess);
        if (this.mIsLoginSuccess) {
            return;
        }
        this.mIsReturnLoadFailed = true;
        speak(App.getInstance().getString(R.string.login_load_failed), null);
        if (this.mRootView != null) {
            this.mRootView.updateTips(App.getInstance().getString(R.string.login_refresh_failed), App.getInstance().getString(R.string.init_text_sub_2));
            ((LoginContract.View) this.mRootView).setQrCodeView(2, null);
        }
    }

    public void loginSuccess(LoginResultBean.UserInfo userInfo) {
        final String string;
        StringBuilder sb = new StringBuilder();
        sb.append("loginSuccess: ");
        sb.append(userInfo == null ? "" : userInfo.getName());
        LogUtils.i(TAG, sb.toString());
        if (this.mHandler.hasMessages(2000)) {
            this.mHandler.removeMessages(2000);
        }
        this.mIsLoginSuccess = true;
        String string2 = App.getInstance().getString(R.string.login_tips_2);
        Object[] objArr = new Object[1];
        objArr[0] = userInfo == null ? "" : userInfo.getName();
        String format = String.format(string2, objArr);
        if (this.mRootView != null) {
            ((LoginContract.View) this.mRootView).showLoginSuccess(true, format, userInfo != null ? userInfo.getImageUrl() : "");
            setWebpVisibility(8);
        }
        if (this.mMainViewModel.isLoginAlready()) {
            string = App.getInstance().getString(R.string.login_already_speech);
        } else {
            string = App.getInstance().getString(R.string.login_success_speech);
        }
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginControl$edvuHR9ANGH8mmPpqgH4SSHRJG0
            @Override // java.lang.Runnable
            public final void run() {
                LoginControl.this.lambda$loginSuccess$7$LoginControl(string);
            }
        });
    }

    public /* synthetic */ void lambda$loginSuccess$7$LoginControl(String str) {
        speak(str, new Runnable() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginControl$xyOzdssyed9vM9AR8sSREM_KpqE
            @Override // java.lang.Runnable
            public final void run() {
                LoginControl.this.lambda$null$6$LoginControl();
            }
        });
    }

    public /* synthetic */ void lambda$null$6$LoginControl() {
        if (this.mRootView != null) {
            this.mRootView.onShowNext();
            setWebpVisibility(0);
        }
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onDestroy() {
        super.onDestroy();
        this.mIsReturnLoadFailed = false;
        this.mIsLoginSuccess = false;
        this.mHandler.removeCallbacksAndMessages(null);
        SoundPlayHelper.instance().stop();
        SpeechHelper.getInstance().stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadQRCode() {
        if (NetUtils.isNetworkAvailable(App.getInstance())) {
            if (this.mMainViewModel != null) {
                this.mMainViewModel.getLoginQRCode();
                return;
            }
            return;
        }
        LogUtils.i(TAG, "loadQRCode network error");
        networkError();
    }

    @Override // com.xiaopeng.oobe.module.login.LoginContract.ViewControl
    public void refreshQrCode() {
        if (!NetUtils.isNetworkAvailable(App.getInstance())) {
            speak(App.getInstance().getString(R.string.connection_tips_error), null);
        } else {
            loadQRCode();
        }
    }

    private void speak(String str, final Runnable runnable) {
        SpeechHelper.getInstance().speak(str, new OnSpeakEndCallback() { // from class: com.xiaopeng.oobe.module.login.LoginControl.2
            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onStart(String str2) {
                super.onStart(str2);
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onDone(String str2) {
                super.onDone(str2);
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.execute(runnable2);
                }
            }

            @Override // com.xiaopeng.oobe.speech.OnSpeakEndCallback, android.speech.tts.UtteranceProgressListener
            public void onError(String str2) {
                super.onError(str2);
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.execute(runnable2);
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onStop(String str2, boolean z) {
                super.onStop(str2, z);
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    ThreadUtils.execute(runnable2);
                }
            }
        });
    }

    private void setWebpVisibility(int i) {
        if (this.mRootView != null) {
            ((IWebpVisibility) this.mRootView).setWebpVisibility(i);
        }
    }
}
