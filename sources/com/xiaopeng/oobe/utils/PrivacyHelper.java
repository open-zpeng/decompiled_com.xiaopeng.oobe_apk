package com.xiaopeng.oobe.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.privacymanager.PrivacyDialogListener;
import com.xiaopeng.privacymanager.PrivacyServiceManager;
import com.xiaopeng.privacymanager.PrivacySettings;
import com.xiaopeng.privacyservice.IFetchProtocolCallback;
import java.util.HashMap;
/* loaded from: classes.dex */
public class PrivacyHelper {
    private static final long CLICK_INTERVAL = 1000;
    public static final Integer[] SUPPORT_PROTOCOL_TYPE = {201, 202, 203, 204};
    public static final Integer[] SUPPORT_PROTOCOL_TYPE_E38 = {201, 202, 203, 204, 205};
    private static final String TAG = "com.xiaopeng.oobe.utils.PrivacyHelper";
    private static PrivacyHelper instance;
    private String mDialogId;
    private IPrivacyServiceCallback mIPrivacyServiceCallback;
    private PrivacyServiceManager mPrivacyServiceManager;
    private Integer[] mSupportProtocolArray;
    private HashMap<Integer, Boolean> sFetchProtocolMap = new HashMap<>();
    private int mFlags = 24;

    /* loaded from: classes.dex */
    public interface IFetchProtocolListener {
        void callback(boolean z);
    }

    /* loaded from: classes.dex */
    public interface IPrivacyServiceCallback {
        void connect(boolean z);
    }

    private PrivacyHelper() {
    }

    public void setConnectCallback(IPrivacyServiceCallback iPrivacyServiceCallback) {
        this.mIPrivacyServiceCallback = iPrivacyServiceCallback;
    }

    public static PrivacyHelper getInstance() {
        if (instance == null) {
            instance = new PrivacyHelper();
        }
        return instance;
    }

    public boolean isConnected() {
        PrivacyServiceManager privacyServiceManager = this.mPrivacyServiceManager;
        if (privacyServiceManager == null) {
            LogUtils.i(TAG, "mPrivacyServiceManager is null");
            return false;
        }
        return privacyServiceManager.isConnected();
    }

    public void disConnected() {
        if (!isConnected()) {
            LogUtils.i(TAG, "mPrivacyServiceManager is disConnected");
        } else {
            this.mPrivacyServiceManager.disconnect();
        }
    }

    public void init(Context context) {
        LogUtils.i(TAG, Constants.Unity.INIT);
        this.mPrivacyServiceManager = new PrivacyServiceManager(context);
        this.mPrivacyServiceManager.connect(new ServiceConnection() { // from class: com.xiaopeng.oobe.utils.PrivacyHelper.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                String str = PrivacyHelper.TAG;
                LogUtils.i(str, "onServiceConnected name:" + componentName + ",service" + iBinder);
                if (PrivacyHelper.this.mIPrivacyServiceCallback != null) {
                    PrivacyHelper.this.mIPrivacyServiceCallback.connect(true);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                String str = PrivacyHelper.TAG;
                LogUtils.i(str, "onServiceDisconnected name:" + componentName);
                if (PrivacyHelper.this.mIPrivacyServiceCallback != null) {
                    PrivacyHelper.this.mIPrivacyServiceCallback.connect(false);
                }
            }
        });
    }

    private void showProtocolDialog(int i, PrivacyDialogListener privacyDialogListener) {
        if (this.mPrivacyServiceManager == null) {
            LogUtils.i(TAG, "mPrivacyServiceManager  is null");
        } else if (ClickUtils.getInstance().isQuickClick(CLICK_INTERVAL)) {
            LogUtils.i(TAG, "mPrivacyServiceManager  fast click");
        } else {
            this.mDialogId = this.mPrivacyServiceManager.showPrivacyDialog(i, this.mFlags, privacyDialogListener);
            String str = TAG;
            LogUtils.i(str, "showPolicyDlg type:" + i + " mDialogId:" + this.mDialogId);
        }
    }

    public void cancelProtocolDialog() {
        String str = TAG;
        LogUtils.i(str, "cancelPrivacyDialog" + this.mDialogId);
        if (TextUtils.isEmpty(this.mDialogId)) {
            return;
        }
        PrivacyServiceManager privacyServiceManager = this.mPrivacyServiceManager;
        if (privacyServiceManager == null) {
            LogUtils.i(TAG, "mPrivacyServiceManager is null");
        } else {
            privacyServiceManager.cancelPrivacyDialog(this.mDialogId);
        }
    }

    public void setPrivacyRead() {
        if (!CarStatusUtils.isEURegion()) {
            readProtocol(101, true);
            readProtocol(102, true);
            return;
        }
        readProtocol(201, true);
    }

    public void setXpilotPrivacyRead() {
        readProtocol(209, true);
        signProtocol(209);
    }

    public void setPrivacySign() {
        if (!CarStatusUtils.isEURegion()) {
            signProtocol(101);
            signProtocol(102);
            return;
        }
        signProtocol(201);
    }

    public void showPrivacy(PrivacyDialogListener privacyDialogListener) {
        showProtocolDialog(CarStatusUtils.isEURegion() ? 201 : 101, privacyDialogListener);
    }

    public void showUserExpericence(PrivacyDialogListener privacyDialogListener) {
        showProtocolDialog(102, privacyDialogListener);
    }

    public void showXpilotUserExpericence(PrivacyDialogListener privacyDialogListener) {
        showProtocolDialog(209, privacyDialogListener);
    }

    public void setSpeechPlanRead(boolean z) {
        readProtocol(CarStatusUtils.isEURegion() ? 205 : 103, z);
    }

    public void setSpeechPlanSign() {
        signProtocol(CarStatusUtils.isEURegion() ? 205 : 103);
    }

    public void showSpeechPlanPolicy(PrivacyDialogListener privacyDialogListener) {
        showProtocolDialog(CarStatusUtils.isEURegion() ? 205 : 103, privacyDialogListener);
    }

    public void showVuiSpeechPolicy(PrivacyDialogListener privacyDialogListener) {
        showProtocolDialog(104, privacyDialogListener);
    }

    public void showSpeechPrivacyDisclaimer(PrivacyDialogListener privacyDialogListener) {
        showProtocolDialog(106, privacyDialogListener);
    }

    public void setSpeechDataRead(boolean z) {
        readProtocol(204, z);
    }

    public void setSpeechDataSign() {
        signProtocol(204);
    }

    public void showSpeechData(PrivacyDialogListener privacyDialogListener) {
        showProtocolDialog(204, privacyDialogListener);
    }

    public void setMapGroupRead(boolean z) {
        readProtocol(2001, z);
    }

    public void setMapGroupSign() {
        signProtocol(2001);
    }

    public void showMapUser(PrivacyDialogListener privacyDialogListener) {
        showProtocolDialog(202, privacyDialogListener);
    }

    public void showMapLocation(PrivacyDialogListener privacyDialogListener) {
        showProtocolDialog(203, privacyDialogListener);
    }

    public String getProtocolName(int i) {
        return PrivacySettings.getProtocolName(App.getInstance(), i);
    }

    public String getProtocolPath(int i) {
        LogUtils.i(TAG, "getProtocolPath");
        PrivacyServiceManager privacyServiceManager = this.mPrivacyServiceManager;
        if (privacyServiceManager == null) {
            LogUtils.i(TAG, "mPrivacyServiceManager  is null");
            return null;
        }
        return privacyServiceManager.getPrivacyPath(i);
    }

    private void readProtocol(int i, boolean z) {
        String str = TAG;
        LogUtils.i(str, "setProtocolRead " + i + " check:" + z);
        PrivacySettings.setAgreed(App.getInstance(), i, z);
        PrivacySettings.setEnabled(App.getInstance(), i, z);
    }

    private void signProtocol(int i) {
        PrivacyServiceManager privacyServiceManager = this.mPrivacyServiceManager;
        if (privacyServiceManager == null) {
            LogUtils.i(TAG, "mPrivacyServiceManager  is null");
            return;
        }
        privacyServiceManager.signProtocol(i);
        String str = TAG;
        LogUtils.i(str, "signProtocol:" + i);
    }

    private void fetchLatestProtocol(final int i, final IFetchProtocolListener iFetchProtocolListener) {
        PrivacyServiceManager privacyServiceManager = this.mPrivacyServiceManager;
        if (privacyServiceManager == null) {
            LogUtils.i(TAG, "mPrivacyServiceManager  is null");
            return;
        }
        privacyServiceManager.fetchProtocol(i, new IFetchProtocolCallback.Stub() { // from class: com.xiaopeng.oobe.utils.PrivacyHelper.2
            @Override // com.xiaopeng.privacyservice.IFetchProtocolCallback
            public void callback(boolean z) {
                PrivacyHelper.this.sFetchProtocolMap.put(Integer.valueOf(i), Boolean.valueOf(z));
                iFetchProtocolListener.callback(z);
            }
        });
        String str = TAG;
        LogUtils.i(str, "protocol type is:" + i);
    }

    public void cancelFetchTask(int i) {
        PrivacyServiceManager privacyServiceManager = this.mPrivacyServiceManager;
        if (privacyServiceManager == null) {
            LogUtils.i(TAG, "mPrivacyServiceManager  is null");
            return;
        }
        privacyServiceManager.cancelFetchTask(i);
        String str = TAG;
        LogUtils.i(str, "To cancel fetch task, protocol type is:" + i);
    }

    public void fetchLatestProtocol() {
        Integer[] numArr;
        if (OOBEHelper.isSupportXP()) {
            this.mSupportProtocolArray = SUPPORT_PROTOCOL_TYPE;
        } else if (OOBEHelper.isSupportSpeechUserAdPlan()) {
            this.mSupportProtocolArray = SUPPORT_PROTOCOL_TYPE_E38;
        } else {
            this.mSupportProtocolArray = SUPPORT_PROTOCOL_TYPE;
        }
        for (final Integer num : this.mSupportProtocolArray) {
            fetchLatestProtocol(num.intValue(), new IFetchProtocolListener() { // from class: com.xiaopeng.oobe.utils.-$$Lambda$PrivacyHelper$tPVAIFKli7mn6htN_oyXqtLj1Cc
                @Override // com.xiaopeng.oobe.utils.PrivacyHelper.IFetchProtocolListener
                public final void callback(boolean z) {
                    PrivacyHelper.lambda$fetchLatestProtocol$0(num, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$fetchLatestProtocol$0(Integer num, boolean z) {
        String str = TAG;
        Log.d(str, "To get Latest protocol, type is:" + num + " success:" + z);
    }

    public void cancelFetchTask() {
        Integer[] numArr;
        for (Integer num : OOBEHelper.isSupportSpeechUserAdPlan() ? SUPPORT_PROTOCOL_TYPE_E38 : SUPPORT_PROTOCOL_TYPE) {
            Boolean bool = this.sFetchProtocolMap.get(num);
            if (bool == null || !bool.booleanValue()) {
                getInstance().cancelFetchTask(num.intValue());
            }
        }
    }
}
