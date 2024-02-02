package com.xiaopeng.oobe.window;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.google.gson.JsonObject;
import com.xiaopeng.lib.apirouter.ApiRouter;
import com.xiaopeng.lib.bughunter.anr.UILooperObserver;
import com.xiaopeng.lib.framework.moduleinterface.ipcmodule.IIpcService;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.NetUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.libconfig.ipc.IpcConfig;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.BuildConfig;
import com.xiaopeng.oobe.bean.LoginResultBean;
import com.xiaopeng.oobe.bean.QrCodeResultBean;
import com.xiaopeng.oobe.module.login.IOnLoginCallback;
import com.xiaopeng.oobe.network.IOnConnectionCheckedListener;
import com.xiaopeng.oobe.network.NetworkChangeReceiver;
import com.xiaopeng.oobe.utils.GsonUtil;
import com.xiaopeng.oobe.utils.SPUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
/* loaded from: classes.dex */
public class OOBEModel {
    private static final int DELAY_CHECK_CONNECTION = 5000;
    private static final int MSG_CHECK_CONNECTION = 1000;
    private static final String TAG = "OOBEModel";
    private final Handler mHandler;
    private IOnConnectionCheckedListener mListener;
    NetworkChangeReceiver mNetWorkStateReceiver;
    private IOnLoginCallback mOnLoginCallback;

    private OOBEModel() {
        this.mHandler = new Handler(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper()) { // from class: com.xiaopeng.oobe.window.OOBEModel.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (1000 == message.what) {
                    OOBEModel.this.notifyNetChange(NetUtils.isNetworkAvailable(App.getInstance()));
                }
            }
        };
        this.mNetWorkStateReceiver = null;
        LogUtils.i(TAG, TAG);
        EventBus.getDefault().register(this);
        registerNetWorkReceiver();
        openGPSIfNeed();
    }

    public static final OOBEModel getInstance() {
        return Holder.Instance;
    }

    public void setOnLoginCallback(IOnLoginCallback iOnLoginCallback) {
        this.mOnLoginCallback = iOnLoginCallback;
    }

    public void setIOnConnectionCheckedListener(IOnConnectionCheckedListener iOnConnectionCheckedListener) {
        this.mListener = iOnConnectionCheckedListener;
    }

    public void onDestroy() {
        LogUtils.i(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
        unRegisterNetWorkReceiver();
    }

    public void getLoginQRCode() {
        LogUtils.i(TAG, ">>>> getLoginQRCode");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("senderPackageName", BuildConfig.APPLICATION_ID);
        jsonObject.addProperty("receiverPackageName", IpcConfig.App.CAR_ACCOUNT);
        jsonObject.addProperty(String.valueOf((int) IpcConfig.AIAssistantConfig.IPC_ACCOUNT_GET_QR_CODE), "getLoginQRCode");
        sendData(IpcConfig.AIAssistantConfig.IPC_ACCOUNT_GET_QR_CODE, jsonObject.toString(), IpcConfig.App.CAR_ACCOUNT);
    }

    private void sendData(int i, @Nullable String str, String str2) {
        boolean checkAppInstalled = checkAppInstalled(App.getInstance(), str2);
        String str3 = str2 + ".IpcRouterService";
        if (!checkAppInstalled) {
            str3 = "com.xiaopeng.napa.AllInIpcRouterService";
        }
        LogUtils.d(TAG, "sendData id:" + i + ",bundle:" + str + ",pkgName:" + str2 + ",isExistPkg:" + checkAppInstalled + ",authority:" + str3);
        try {
            ApiRouter.route(new Uri.Builder().authority(str3).path("onReceiverData").appendQueryParameter("id", String.valueOf(i)).appendQueryParameter("bundle", str).build());
        } catch (RemoteException e) {
            LogUtils.d(TAG, "sendData e:" + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean checkAppInstalled(Context context, String str) {
        PackageInfo packageInfo;
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            LogUtils.d(TAG, "sendData e:" + e.getMessage());
            packageInfo = null;
        }
        return packageInfo != null;
    }

    @Subscribe
    public void onEvent(IIpcService.IpcMessageEvent ipcMessageEvent) {
        if (ipcMessageEvent == null) {
            LogUtils.i(TAG, "onEvent event is null");
            return;
        }
        int msgID = ipcMessageEvent.getMsgID();
        String senderPackageName = ipcMessageEvent.getSenderPackageName();
        Bundle payloadData = ipcMessageEvent.getPayloadData();
        LogUtils.i(TAG, "onEvent messageId=" + msgID + "  packageName=" + senderPackageName + " payLoadData=" + payloadData);
        switch (msgID) {
            case IpcConfig.AIAssistantConfig.IPC_ACCOUNT_RETURN_QR_CODE /* 11002 */:
                if (payloadData != null) {
                    accountReturnQrCode(payloadData.getString(IpcConfig.IPCKey.STRING_MSG));
                    return;
                }
                return;
            case IpcConfig.AIAssistantConfig.IPC_ACCOUNT_QR_CODE_OVERDUE /* 11003 */:
                accountQrCodeOverDue();
                return;
            case IpcConfig.AIAssistantConfig.IPC_ACCOUNT_LOGIN_RESULT /* 11004 */:
                if (payloadData != null) {
                    accountLoginResult(payloadData.getString(IpcConfig.IPCKey.STRING_MSG));
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void accountQrCodeOverDue() {
        LogUtils.i(TAG, "accountQrCodeOverDue");
        IOnLoginCallback iOnLoginCallback = this.mOnLoginCallback;
        if (iOnLoginCallback != null) {
            iOnLoginCallback.onQRCodeOverdue();
            return;
        }
        if (!TextUtils.isEmpty(SPUtils.getLoginQrcode())) {
            SPUtils.setLoginQrcode("-1");
        }
        getLoginQRCode();
    }

    private void accountLoginResult(String str) {
        LogUtils.i(TAG, "accountLoginResult data:" + str);
        if (this.mOnLoginCallback != null) {
            try {
                LoginResultBean loginResultBean = (LoginResultBean) GsonUtil.fromJson(str, LoginResultBean.class);
                if (loginResultBean != null) {
                    this.mOnLoginCallback.onLoginResult(loginResultBean.isResult(), loginResultBean.getUserInfo());
                } else {
                    this.mOnLoginCallback.onLoginResult(false, null);
                }
            } catch (Exception e) {
                LogUtils.e(TAG, e);
            }
        }
    }

    private void accountReturnQrCode(String str) {
        LogUtils.i(TAG, "accountReturnQrCode data:" + str);
        try {
            QrCodeResultBean qrCodeResultBean = (QrCodeResultBean) GsonUtil.fromJson(str, QrCodeResultBean.class);
            if (qrCodeResultBean != null && qrCodeResultBean.getErrorCode() == 0) {
                SPUtils.setLoginQrcode(qrCodeResultBean.getQrCodeUrl());
                if (this.mOnLoginCallback != null) {
                    this.mOnLoginCallback.onReturnQRCode(qrCodeResultBean.getQrCodeUrl());
                }
            } else {
                SPUtils.setLoginQrcode("-1");
                if (this.mOnLoginCallback != null) {
                    this.mOnLoginCallback.onQRCodeLoadFailed();
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Holder {
        private static final OOBEModel Instance = new OOBEModel();

        private Holder() {
        }
    }

    public void registerNetWorkReceiver() {
        if (this.mNetWorkStateReceiver == null) {
            this.mNetWorkStateReceiver = new NetworkChangeReceiver(new IOnConnectionCheckedListener() { // from class: com.xiaopeng.oobe.window.-$$Lambda$OOBEModel$eQP3ASlwr50OX3e_OXee1c8ZNEU
                @Override // com.xiaopeng.oobe.network.IOnConnectionCheckedListener
                public final void onNetConnectionChecked(boolean z) {
                    OOBEModel.this.lambda$registerNetWorkReceiver$0$OOBEModel(z);
                }
            });
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            App.getInstance().registerReceiver(this.mNetWorkStateReceiver, intentFilter);
        }
    }

    public /* synthetic */ void lambda$registerNetWorkReceiver$0$OOBEModel(boolean z) {
        LogUtils.i(TAG, "onNetConnectionChecked available:" + z);
        if (z) {
            getLoginQRCode();
        }
        clearCheckConnection();
        notifyNetChange(z);
        checkConnection();
    }

    public void unRegisterNetWorkReceiver() {
        if (this.mNetWorkStateReceiver != null) {
            App.getInstance().unregisterReceiver(this.mNetWorkStateReceiver);
            this.mNetWorkStateReceiver = null;
        }
        clearCheckConnection();
    }

    public void checkConnection() {
        if (NetUtils.isNetworkAvailable(App.getInstance()) || isCheckingConnection()) {
            return;
        }
        this.mHandler.removeMessages(1000);
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(Message.obtain(handler, 1000), UILooperObserver.ANR_TRIGGER_TIME);
    }

    public boolean isCheckingConnection() {
        return this.mHandler.hasMessages(1000);
    }

    public void clearCheckConnection() {
        if (isCheckingConnection()) {
            this.mHandler.removeMessages(1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNetChange(boolean z) {
        LogUtils.i(TAG, "notifyNetChange available:" + z);
        IOnConnectionCheckedListener iOnConnectionCheckedListener = this.mListener;
        if (iOnConnectionCheckedListener != null) {
            iOnConnectionCheckedListener.onNetConnectionChecked(z);
        }
    }

    private void openGPSIfNeed() {
        ThreadUtils.execute(new Runnable() { // from class: com.xiaopeng.oobe.window.-$$Lambda$OOBEModel$nKNgwsrvWaVT6BT-vcud01u6snI
            @Override // java.lang.Runnable
            public final void run() {
                OOBEModel.lambda$openGPSIfNeed$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$openGPSIfNeed$1() {
        boolean isProviderEnabled = ((LocationManager) App.getInstance().getSystemService(RequestParameters.SUBRESOURCE_LOCATION)).isProviderEnabled("gps");
        boolean hasSystemFeature = App.getInstance().getPackageManager().hasSystemFeature("android.hardware.location.gps");
        LogUtils.i(TAG, "openGPSIfNeed: isGpsEnable=" + isProviderEnabled + "; withGPSFeature=" + hasSystemFeature);
        if (isProviderEnabled || !hasSystemFeature) {
            return;
        }
        Settings.Secure.putInt(App.getInstance().getContentResolver(), "location_mode", 3);
        LogUtils.i(TAG, "openGPSIfNeed: do open!");
    }
}
