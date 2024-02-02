package com.xiaopeng.oobe.viewmodel.main;

import android.accounts.Account;
import android.accounts.AccountManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.audio.SoundPlayHelper;
import com.xiaopeng.oobe.bean.LoginResultBean;
import com.xiaopeng.oobe.carmanager.CarClientWrapper;
import com.xiaopeng.oobe.carmanager.controller.IMcuController;
import com.xiaopeng.oobe.carmanager.controller.IScuController;
import com.xiaopeng.oobe.speech.SpeechHelper;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.utils.SPUtils;
import com.xiaopeng.oobe.utils.SettingsUtil;
import com.xiaopeng.oobe.window.OOBEModel;
/* loaded from: classes.dex */
public class MainViewModel extends ViewModel implements IMainViewModel {
    private static final String TAG = "MainViewModel";
    private AccountManager mAccountManager;
    private IMcuController mIMcuController;
    private IScuController mIScuController;
    private LoginResultBean.UserInfo mUserInfo;
    private MutableLiveData<Boolean> mNetConnect = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoginSuccess = new MutableLiveData<>();
    private MutableLiveData<String> mQrCodeUrl = new MutableLiveData<>();
    private MutableLiveData<Boolean> mQrCodeOverDue = new MutableLiveData<>();
    private MutableLiveData<Boolean> mMcuIgOff = new MutableLiveData<>();
    private MutableLiveData<Boolean> mScuDsmSwOff = new MutableLiveData<>();
    private IMcuController.Callback mMcuIgState = new IMcuController.Callback() { // from class: com.xiaopeng.oobe.viewmodel.main.MainViewModel.1
        @Override // com.xiaopeng.oobe.carmanager.controller.IMcuController.Callback
        public void onIgStatusChanged(int i) {
            if (i == 0) {
                MainViewModel.this.onMcuIgOff();
            } else if (i == 1) {
                MainViewModel.this.onMcuIgOn();
            }
            LogUtils.i(MainViewModel.TAG, "mIMcuController onIgStatusChanged=" + i, false);
        }
    };
    private IScuController.Callback mScuDsmSwState = new IScuController.Callback() { // from class: com.xiaopeng.oobe.viewmodel.main.MainViewModel.2
        @Override // com.xiaopeng.oobe.carmanager.controller.IScuController.Callback
        public void onDsmSwStateChanged(int i) {
            if (i == 0) {
                MainViewModel.this.onScuDsmSwOff();
            } else if (i == 1) {
                MainViewModel.this.onScuDsmSwOn();
            }
            LogUtils.i(MainViewModel.TAG, "IScuController onDsmSwStateChanged=" + i, false);
        }
    };
    private OOBEModel mOOBEModel = OOBEModel.getInstance();

    public MutableLiveData<Boolean> getNetConnect() {
        return this.mNetConnect;
    }

    public void clearNetConnect() {
        this.mNetConnect = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getLoginSuccess() {
        return this.mLoginSuccess;
    }

    public MutableLiveData<Boolean> getQrCodeOverDue() {
        return this.mQrCodeOverDue;
    }

    public MutableLiveData<String> getQrCodeUrl() {
        return this.mQrCodeUrl;
    }

    public MutableLiveData<Boolean> getMcuIgOff() {
        this.mMcuIgOff = new MutableLiveData<>();
        return this.mMcuIgOff;
    }

    public MutableLiveData<Boolean> getScuDsmSwState() {
        return this.mScuDsmSwOff;
    }

    public void setScuDsmSwState(boolean z) {
        this.mIScuController.setDsmStatus(z);
    }

    public LoginResultBean.UserInfo getLoginUser() {
        return this.mUserInfo;
    }

    public MainViewModel() {
        this.mOOBEModel.setIOnConnectionCheckedListener(this);
        if (SPUtils.getOobeFinishStep() == 6) {
            return;
        }
        this.mAccountManager = AccountManager.get(App.getInstance());
        SettingsUtil.enterOOBE();
        ThreadUtils.execute(new Runnable() { // from class: com.xiaopeng.oobe.viewmodel.main.-$$Lambda$MainViewModel$GcOe8sQVTrWcl-n7AFbkrh03i98
            @Override // java.lang.Runnable
            public final void run() {
                MainViewModel.this.lambda$new$1$MainViewModel();
            }
        });
    }

    public /* synthetic */ void lambda$new$1$MainViewModel() {
        OOBEHelper.getInstance().setMicrophoneMute(true);
        if (CarClientWrapper.getInstance().isCarServiceConnected()) {
            LogUtils.d(TAG, "Car service connected ", false);
            CarClientWrapper carClientWrapper = CarClientWrapper.getInstance();
            this.mIMcuController = (IMcuController) carClientWrapper.getController(CarClientWrapper.XP_MCU_SERVICE);
            this.mIScuController = (IScuController) carClientWrapper.getController(CarClientWrapper.XP_SCU_SERVICE);
            ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.viewmodel.main.-$$Lambda$MainViewModel$V9nPKdXydDg2Q0jT-Wbqala-TgY
                @Override // java.lang.Runnable
                public final void run() {
                    MainViewModel.this.lambda$null$0$MainViewModel();
                }
            }, 200L);
        }
    }

    public /* synthetic */ void lambda$null$0$MainViewModel() {
        IMcuController iMcuController = this.mIMcuController;
        if (iMcuController != null) {
            iMcuController.registerCallback(this.mMcuIgState);
            LogUtils.i(TAG, "init mIMcuController=" + this.mIMcuController.getIgStatusFromMcu());
        }
        IScuController iScuController = this.mIScuController;
        if (iScuController != null) {
            iScuController.registerCallback(this.mScuDsmSwState);
            LogUtils.i(TAG, "init mIScuController=" + this.mIScuController.getDsmSwStatus());
        }
    }

    public boolean isMcuIgOn() {
        IMcuController iMcuController = this.mIMcuController;
        return iMcuController == null || iMcuController.getIgStatusFromMcu() == 1;
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewModel
    public void onDestroy() {
        SettingsUtil.exitOOBE();
        this.mAccountManager = null;
        PrivacyHelper.getInstance().cancelProtocolDialog();
        resetSpeechMediaPlayer();
        this.mOOBEModel.onDestroy();
        IMcuController iMcuController = this.mIMcuController;
        if (iMcuController != null) {
            iMcuController.unregisterCallback(this.mMcuIgState);
            LogUtils.i(TAG, "onDestroy mIMcuController=" + this.mIMcuController.getIgStatusFromMcu());
        }
        IScuController iScuController = this.mIScuController;
        if (iScuController != null) {
            iScuController.unregisterCallback(this.mScuDsmSwState);
            LogUtils.i(TAG, "onDestroy mIScuController=" + this.mIScuController.getDsmSwStatus());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMcuIgOn() {
        this.mMcuIgOff.postValue(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMcuIgOff() {
        this.mMcuIgOff.postValue(true);
        resetSpeechMediaPlayer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScuDsmSwOn() {
        this.mScuDsmSwOff.postValue(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScuDsmSwOff() {
        this.mScuDsmSwOff.postValue(true);
    }

    public void resetSpeechMediaPlayer() {
        LogUtils.i(TAG, "resetSpeechMediaPlayer");
        SpeechHelper.getInstance().stop();
        SoundPlayHelper.instance().stop();
    }

    public void startLoginStep() {
        this.mOOBEModel.setOnLoginCallback(this);
    }

    @Override // com.xiaopeng.oobe.module.login.IOnLoginCallback
    public void onReturnQRCode(String str) {
        if (this.mQrCodeUrl.hasActiveObservers()) {
            this.mQrCodeUrl.postValue(str);
        }
        if (this.mQrCodeOverDue.hasActiveObservers()) {
            this.mQrCodeOverDue.postValue(false);
        }
    }

    @Override // com.xiaopeng.oobe.module.login.IOnLoginCallback
    public void onQRCodeOverdue() {
        if (this.mQrCodeOverDue.hasActiveObservers()) {
            this.mQrCodeOverDue.postValue(true);
        }
    }

    @Override // com.xiaopeng.oobe.module.login.IOnLoginCallback
    public void onQRCodeLoadFailed() {
        if (this.mQrCodeUrl.hasActiveObservers()) {
            this.mQrCodeUrl.postValue(null);
        }
    }

    @Override // com.xiaopeng.oobe.module.login.IOnLoginCallback
    public void onLoginResult(boolean z, LoginResultBean.UserInfo userInfo) {
        this.mUserInfo = userInfo;
        this.mLoginSuccess.postValue(Boolean.valueOf(z));
    }

    @Override // com.xiaopeng.oobe.network.IOnConnectionCheckedListener
    public void onNetConnectionChecked(boolean z) {
        LogUtils.i(TAG, "onNetConnectionChecked available:" + z + ",isMcuIgOn:" + isMcuIgOn());
        if (isMcuIgOn()) {
            this.mNetConnect.postValue(Boolean.valueOf(z));
        }
    }

    @Override // com.xiaopeng.oobe.viewmodel.main.IMainViewModel
    public void getLoginQRCode() {
        OOBEModel oOBEModel = this.mOOBEModel;
        if (oOBEModel != null) {
            oOBEModel.getLoginQRCode();
        }
    }

    @Override // com.xiaopeng.oobe.viewmodel.main.IMainViewModel
    public void checkConnection() {
        OOBEModel oOBEModel = this.mOOBEModel;
        if (oOBEModel != null) {
            oOBEModel.checkConnection();
        }
    }

    public Account getCurrentAccountInfo() {
        Account[] accountsByType = this.mAccountManager.getAccountsByType(Constants.ACCOUNT_TYPE_XP_VEHICLE);
        if (accountsByType.length > 0) {
            LogUtils.i(TAG, "getCurrentAccountInfo accounts.length=" + accountsByType.length);
            Account account = accountsByType[0];
            try {
                String substring = account.name.substring(0, account.name.lastIndexOf("-"));
                String userData = this.mAccountManager.getUserData(account, Constants.USER_DATA_EXTRA_AVATAR);
                LogUtils.i(TAG, "getCurrentAccountInfo: name=" + substring + ";avatar=" + userData);
                this.mUserInfo = new LoginResultBean.UserInfo();
                this.mUserInfo.setName(substring);
                this.mUserInfo.setImageUrl(userData);
                LoginResultBean loginResultBean = new LoginResultBean();
                loginResultBean.setResult(true);
                loginResultBean.setUserInfo(this.mUserInfo);
            } catch (Exception e) {
                LogUtils.i(TAG, "getCurrentAccountInfo Exception=" + e.getMessage());
            }
            return account;
        }
        LogUtils.i(TAG, "getCurrentAccountInfo account is empty");
        return null;
    }

    public boolean isLoginAlready() {
        boolean z = getCurrentAccountInfo() != null;
        LogUtils.i(TAG, "isLoginAlready:" + z);
        return z;
    }
}
