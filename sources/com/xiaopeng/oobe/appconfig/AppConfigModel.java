package com.xiaopeng.oobe.appconfig;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.utils.ContextUtils;
import com.xiaopeng.oobe.utils.FileUtils;
import com.xiaopeng.xuimanager.XUIManager;
import com.xiaopeng.xuimanager.XUIServiceNotConnectedException;
import com.xiaopeng.xuimanager.xapp.XAppManager;
import java.io.File;
import java.io.IOException;
/* loaded from: classes.dex */
public class AppConfigModel {
    private static final String TAG = "OOBE-AppConfigModel";
    private XAppManager mAppManager;
    private Context mContext;
    private XUIManager mXUIManager;

    public AppConfigModel(Context context) {
        this.mContext = context;
        this.mXUIManager = XUIManager.createXUIManager(context, new ServiceConnection() { // from class: com.xiaopeng.oobe.appconfig.AppConfigModel.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                LogUtils.i(AppConfigModel.TAG, " XUIManager Connected:");
                AppConfigModel.this.initAppManager();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                LogUtils.i(AppConfigModel.TAG, " XUIManager onServiceDisconnected:");
            }
        });
    }

    private void connect() {
        this.mXUIManager.connect();
    }

    public static void uploadConfig(Context context) {
        new AppConfigModel(context).connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAppManager() {
        try {
            this.mAppManager = (XAppManager) this.mXUIManager.getXUIServiceManager("xapp");
            ThreadUtils.execute(new Runnable() { // from class: com.xiaopeng.oobe.appconfig.-$$Lambda$AppConfigModel$L7nwc-0LhuIMDVLk0KSOBKdzVPY
                @Override // java.lang.Runnable
                public final void run() {
                    AppConfigModel.this.lambda$initAppManager$0$AppConfigModel();
                }
            });
        } catch (XUIServiceNotConnectedException e) {
            LogUtils.d(TAG, "initAppManager " + e.getMessage());
        }
    }

    private String getAppConfigDir() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.Inter.XP_APP_CONFIG_DIR);
        sb.append(File.separator);
        sb.append(ContextUtils.isPortrait(this.mContext) ? "port" : "land");
        return sb.toString();
    }

    /* renamed from: uploadAppConfig */
    public void lambda$initAppManager$0$AppConfigModel() {
        AssetManager assets = this.mContext.getAssets();
        try {
            try {
                String appConfigDir = getAppConfigDir();
                String[] list = assets.list(appConfigDir);
                int i = 0;
                while (list != null) {
                    if (i >= list.length) {
                        break;
                    }
                    String str = list[i];
                    String readFromAssets = FileUtils.readFromAssets(this.mContext, appConfigDir + File.separator + str);
                    if (!TextUtils.isEmpty(readFromAssets)) {
                        setAppConfig(str.substring(0, str.lastIndexOf(".")), readFromAssets);
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            this.mXUIManager.disconnect();
        }
    }

    private boolean setAppConfig(String str, String str2) {
        if (this.mAppManager != null) {
            try {
                LogUtils.i(TAG, "setAppConfig pkg=" + str + " config=" + str2);
                this.mAppManager.onAppConfigUpload(str, str2);
                return true;
            } catch (XUIServiceNotConnectedException e) {
                LogUtils.e(TAG, "setAppConfig: xui service not connected. pn=%s, msg=%s", str, e.getMessage());
            }
        } else {
            LogUtils.i(TAG, "setAppConfig: appMgr is null. config=%s", str2);
        }
        return false;
    }
}
