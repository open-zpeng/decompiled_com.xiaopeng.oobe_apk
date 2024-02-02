package com.xiaopeng.privacymanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.xiaopeng.privacymanager.feature.BaseFeatureOption;
import com.xiaopeng.privacyservice.IFetchProtocolCallback;
import com.xiaopeng.privacyservice.IPrivacyDialogCallback;
import com.xiaopeng.privacyservice.IPrivacyService;
/* loaded from: classes.dex */
public class PrivacyServiceManager {
    private static final String TAG = "PrivacyServiceManager";
    private PrivacyServiceConnection mConnection;
    private final Context mContext;
    private final Handler mHandler;
    private IPrivacyService mPrivacyService;
    private static final String PRIVACY_SERVICE_PACKAGE_NAME = BaseFeatureOption.getInstance().getPrivacyServicePackage();
    private static final String PRIVACY_SERVICE_ACTION_NAME = BaseFeatureOption.getInstance().getPrivacyServiceAction();
    private boolean isDisconnectedByClient = false;
    private int mRetryConnectServiceCount = 1;
    private final Runnable mRetryJob = new Runnable() { // from class: com.xiaopeng.privacymanager.PrivacyServiceManager.1
        @Override // java.lang.Runnable
        public void run() {
            if (PrivacyServiceManager.this.isDisconnectedByClient || PrivacyServiceManager.this.isConnected()) {
                PrivacyServiceManager.this.mRetryConnectServiceCount = 1;
            } else {
                PrivacyServiceManager.this.bindInternalService();
            }
        }
    };

    public PrivacyServiceManager(Context context) {
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
    }

    public void connect(ServiceConnection serviceConnection) {
        if (this.mConnection == null) {
            Log.i(TAG, "call connect by client, mConnection is null. ServiceConnection callback: " + serviceConnection);
            this.mConnection = new PrivacyServiceConnection(serviceConnection);
        } else {
            Log.i(TAG, "call connect by client, mConnection is " + this.mConnection + ", ServiceConnection callback: " + serviceConnection);
            this.mConnection.setPrivacyServiceConnection(serviceConnection);
        }
        this.isDisconnectedByClient = false;
        bindServiceImmediately();
    }

    private void bindServiceImmediately() {
        this.mRetryConnectServiceCount = 1;
        this.mHandler.removeCallbacks(this.mRetryJob);
        bindInternalService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindInternalService() {
        if (this.mConnection == null) {
            Log.i(TAG, "PrivacyService bindInternalService unexpected flow, mConnection is null");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(PRIVACY_SERVICE_PACKAGE_NAME);
        intent.setAction(PRIVACY_SERVICE_ACTION_NAME);
        intent.putExtra("callingPackage", this.mContext.getPackageName());
        boolean bindService = this.mContext.bindService(intent, this.mConnection, 1);
        Log.i(TAG, "PrivacyService bindInternalService result: " + bindService + ", retryCount: " + this.mRetryConnectServiceCount);
        if (!bindService) {
            Handler handler = this.mHandler;
            Runnable runnable = this.mRetryJob;
            int i = this.mRetryConnectServiceCount;
            this.mRetryConnectServiceCount = i + 1;
            handler.postDelayed(runnable, i * 2000);
            return;
        }
        this.mRetryConnectServiceCount = 1;
    }

    private boolean checkInternalServiceAlive() {
        if (this.isDisconnectedByClient) {
            Log.i(TAG, "PrivacyService disconnected by client ");
            return false;
        } else if (!isConnected()) {
            Log.i(TAG, "PrivacyService is not alive , bind it immediately !");
            this.mPrivacyService = null;
            bindServiceImmediately();
            return false;
        } else {
            Log.i(TAG, "PrivacyService is alive");
            return true;
        }
    }

    public void disconnect() {
        Log.i(TAG, "call disconnect by client. ");
        this.isDisconnectedByClient = true;
        this.mPrivacyService = null;
        this.mRetryConnectServiceCount = 1;
        this.mHandler.removeCallbacksAndMessages(null);
        this.mContext.unbindService(this.mConnection);
    }

    public boolean isConnected() {
        IPrivacyService iPrivacyService;
        return (this.isDisconnectedByClient || (iPrivacyService = this.mPrivacyService) == null || iPrivacyService.asBinder() == null || !this.mPrivacyService.asBinder().isBinderAlive()) ? false : true;
    }

    public String showPrivacyDialog(int i, int i2, PrivacyDialogListener privacyDialogListener) {
        Log.i(TAG, "call showPrivacyDialog by client. type: " + i + " flags : " + i2);
        checkInternalServiceAlive();
        IPrivacyService iPrivacyService = this.mPrivacyService;
        if (iPrivacyService != null) {
            try {
                if (privacyDialogListener != null) {
                    return iPrivacyService.showPrivacyDialog(i, i2, new AnonymousClass2(privacyDialogListener));
                }
                return iPrivacyService.showPrivacyDialog(i, i2, null);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return null;
    }

    /* renamed from: com.xiaopeng.privacymanager.PrivacyServiceManager$2  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass2 extends IPrivacyDialogCallback.Stub {
        final /* synthetic */ PrivacyDialogListener val$listener;

        AnonymousClass2(PrivacyDialogListener privacyDialogListener) {
            this.val$listener = privacyDialogListener;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyDialogCallback
        public void onDialogClosed(final int i, final boolean z, final int i2) {
            Handler handler = PrivacyServiceManager.this.mHandler;
            final PrivacyDialogListener privacyDialogListener = this.val$listener;
            handler.post(new Runnable() { // from class: com.xiaopeng.privacymanager.-$$Lambda$PrivacyServiceManager$2$pJlBmAVD_jwjMxpyEqvOTmhmBuQ
                @Override // java.lang.Runnable
                public final void run() {
                    PrivacyDialogListener.this.onDialogClosed(i, z, i2);
                }
            });
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyDialogCallback
        public void onDialogShown(final int i) {
            Handler handler = PrivacyServiceManager.this.mHandler;
            final PrivacyDialogListener privacyDialogListener = this.val$listener;
            handler.post(new Runnable() { // from class: com.xiaopeng.privacymanager.-$$Lambda$PrivacyServiceManager$2$2_khuqW_2C49XAgE0FvFs-cCQUw
                @Override // java.lang.Runnable
                public final void run() {
                    PrivacyDialogListener.this.onDialogShown(i);
                }
            });
        }
    }

    public String getPrivacyPath(int i) {
        Log.i(TAG, "call getPrivacyPath by client. type: " + i);
        checkInternalServiceAlive();
        IPrivacyService iPrivacyService = this.mPrivacyService;
        if (iPrivacyService != null) {
            try {
                return iPrivacyService.getPrivacyPath(i);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
                return null;
            }
        }
        return null;
    }

    public String getPrivacyContent(int i) {
        Log.i(TAG, "call getPrivacyContent by client. type: " + i);
        checkInternalServiceAlive();
        IPrivacyService iPrivacyService = this.mPrivacyService;
        if (iPrivacyService != null) {
            try {
                return iPrivacyService.getPrivacyContent(i);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
                return null;
            }
        }
        return null;
    }

    public void dismissPrivacyDialog(String str) {
        Log.i(TAG, "call dismissPrivacyDialog by client. dialogId: " + str);
        checkInternalServiceAlive();
        IPrivacyService iPrivacyService = this.mPrivacyService;
        if (iPrivacyService != null) {
            try {
                iPrivacyService.dismissPrivacyDialog(str);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public void cancelPrivacyDialog(String str) {
        Log.i(TAG, "call cancelPrivacyDialog by client. dialogId: " + str);
        checkInternalServiceAlive();
        IPrivacyService iPrivacyService = this.mPrivacyService;
        if (iPrivacyService != null) {
            try {
                iPrivacyService.cancelPrivacyDialog(str);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public boolean haveDialogOnScreen() {
        Log.i(TAG, "call haveDialogOnScreen by client.");
        checkInternalServiceAlive();
        IPrivacyService iPrivacyService = this.mPrivacyService;
        if (iPrivacyService != null) {
            try {
                return iPrivacyService.haveDialogOnScreen();
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
                return false;
            }
        }
        return false;
    }

    public void signProtocol(int i) {
        Log.i(TAG, "call signProtocol by client. type: " + i);
        checkInternalServiceAlive();
        IPrivacyService iPrivacyService = this.mPrivacyService;
        if (iPrivacyService != null) {
            try {
                iPrivacyService.signProtocol(i);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void fetchProtocol(int i, IFetchProtocolCallback iFetchProtocolCallback) {
        Log.i(TAG, "call fetchProtocol by client. type: " + i);
        checkInternalServiceAlive();
        IPrivacyService iPrivacyService = this.mPrivacyService;
        if (iPrivacyService != null) {
            try {
                iPrivacyService.fetchProtocol(i, iFetchProtocolCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelFetchTask(int i) {
        Log.i(TAG, "call cancelFetchTask by client. type: " + i);
        checkInternalServiceAlive();
        IPrivacyService iPrivacyService = this.mPrivacyService;
        if (iPrivacyService != null) {
            try {
                iPrivacyService.cancelFetchTask(i);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class PrivacyServiceConnection implements ServiceConnection {
        private ServiceConnection mConnCallback;

        public PrivacyServiceConnection(ServiceConnection serviceConnection) {
            this.mConnCallback = serviceConnection;
        }

        public void setPrivacyServiceConnection(ServiceConnection serviceConnection) {
            this.mConnCallback = serviceConnection;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(PrivacyServiceManager.TAG, "onServiceConnected ComponentName: " + componentName + "IBinder : " + iBinder + ", thread: " + Thread.currentThread());
            PrivacyServiceManager.this.mPrivacyService = IPrivacyService.Stub.asInterface(iBinder);
            ServiceConnection serviceConnection = this.mConnCallback;
            if (serviceConnection != null) {
                serviceConnection.onServiceConnected(componentName, iBinder);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(PrivacyServiceManager.TAG, "onServiceDisconnected ComponentName: " + componentName + ", thread: " + Thread.currentThread());
            PrivacyServiceManager.this.mPrivacyService = null;
            ServiceConnection serviceConnection = this.mConnCallback;
            if (serviceConnection != null) {
                serviceConnection.onServiceDisconnected(componentName);
            }
        }
    }
}
