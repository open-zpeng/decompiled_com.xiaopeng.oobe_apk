package com.xiaopeng.speech.proxy;

import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaopeng.speech.ConnectManager;
import com.xiaopeng.speech.ISpeechEngine;
import com.xiaopeng.speech.common.bean.DisableInfoBean;
import com.xiaopeng.speech.common.bean.SuspendInfoBean;
import com.xiaopeng.speech.common.util.IPCRunner;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.common.util.WorkerHandler;
import com.xiaopeng.speech.coreapi.IWakeupEngine;
import com.xiaopeng.speech.jarvisproto.AvatarStatus;
import com.xiaopeng.speech.jarvisproto.SoundAreaStatus;
import com.xiaopeng.speech.jarvisproto.SwitchStatus;
import com.xiaopeng.speech.protocol.utils.CarTypeUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class WakeupEngineProxy extends IWakeupEngine.Stub implements ConnectManager.OnConnectCallback {
    private static final String TAG = "WakeupEngineProxy";
    private final IPCRunner<IWakeupEngine> mIpcRunner = new IPCRunner<>(TAG);
    private final Map<String, DisableInfoBean> disableInfoCache = new ConcurrentHashMap();
    private final Map<String, DisableInfoBean> disableSpeechInfoCache = new ConcurrentHashMap();
    private final Map<String, List<SuspendInfoBean>> suspendInfoCache = new ConcurrentHashMap();

    @Override // com.xiaopeng.speech.ConnectManager.OnConnectCallback
    public void onDisconnect() {
    }

    @Override // com.xiaopeng.speech.ConnectManager.OnConnectCallback
    public void onConnect(ISpeechEngine iSpeechEngine) {
        try {
            this.mIpcRunner.setProxy(iSpeechEngine.getWakeupEngine());
            this.mIpcRunner.fetchAll();
            LogUtils.i(TAG, "reset:   onConnect");
            resumeCarSpeechStatus();
        } catch (RemoteException e) {
            LogUtils.e(this, "onConnect exception ", e);
        }
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void startDialog() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.1
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.startDialog();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void stopDialog() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.2
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.stopDialog();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void avatarClick(final String str) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.3
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.avatarClick(str);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void avatarPress() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.4
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.avatarPress();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void avatarRelease() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.5
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.avatarRelease();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableWakeup() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.6
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.enableWakeup();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableWakeup() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.7
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.disableWakeup();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public String[] getWakeupWords() {
        return (String[]) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, String[]>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.8
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public String[] run(IWakeupEngine iWakeupEngine) throws RemoteException {
                return iWakeupEngine.getWakeupWords();
            }
        }, new String[0]);
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void updateMinorWakeupWord(final String str, final String str2, final String str3, final String[] strArr) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.9
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.updateMinorWakeupWord(str, str2, str3, strArr);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public String getMinorWakeupWord() {
        return (String) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, String>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.10
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public String run(IWakeupEngine iWakeupEngine) throws RemoteException {
                return iWakeupEngine.getMinorWakeupWord();
            }
        }, null);
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void updateCommandWakeupWord(final String[] strArr, final String[] strArr2, final String[] strArr3, final String[] strArr4, final String[] strArr5) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.11
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.updateCommandWakeupWord(strArr, strArr2, strArr3, strArr4, strArr5);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void clearCommandWakeupWord() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.12
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.clearCommandWakeupWord();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void addCommandWakeupWord(final String[] strArr, final String[] strArr2, final String[] strArr3, final String[] strArr4, final String[] strArr5) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.13
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.addCommandWakeupWord(strArr, strArr2, strArr3, strArr4, strArr5);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void removeCommandWakeupWord(final String[] strArr) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.14
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.removeCommandWakeupWord(strArr);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void updateShortcutWakeupWord(final String[] strArr, final String[] strArr2, final String[] strArr3) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.15
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.updateShortcutWakeupWord(strArr, strArr2, strArr3);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void clearShortCutWakeupWord() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.16
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.clearShortCutWakeupWord();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void addShortcutWakeupWord(final String[] strArr, final String[] strArr2, final String[] strArr3) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.17
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.addShortcutWakeupWord(strArr, strArr2, strArr3);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void removeShortcutWakeupWord(final String[] strArr) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.18
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.removeShortcutWakeupWord(strArr);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void pauseDialog() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.19
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.pauseDialog();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void resumeDialog() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.20
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.resumeDialog();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public boolean isEnableWakeup() {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.21
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(IWakeupEngine iWakeupEngine) throws RemoteException {
                return Boolean.valueOf(iWakeupEngine.isEnableWakeup());
            }
        }, false)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public boolean isDefaultEnableWakeup() {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.22
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(IWakeupEngine iWakeupEngine) throws RemoteException {
                return Boolean.valueOf(iWakeupEngine.isDefaultEnableWakeup());
            }
        }, true)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void setDefaultWakeupEnabled(final boolean z) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.23
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.setDefaultWakeupEnabled(z);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableWakeupEnhance(final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.24
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.enableWakeupEnhance(iWakeupEngine.asBinder());
                    return null;
                }
                iWakeupEngine.enableWakeupEnhance(iBinder2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableWakeupEnhance(final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.25
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.disableWakeupEnhance(iWakeupEngine.asBinder());
                    return null;
                }
                iWakeupEngine.disableWakeupEnhance(iBinder2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public boolean isWheelWakeupEnabled() {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.26
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(IWakeupEngine iWakeupEngine) throws RemoteException {
                return Boolean.valueOf(iWakeupEngine.isWheelWakeupEnabled());
            }
        }, true)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void setWheelWakeupEnabled(final IBinder iBinder, final boolean z) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.27
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.setWheelWakeupEnabled(iWakeupEngine.asBinder(), z);
                    return null;
                }
                iWakeupEngine.setWheelWakeupEnabled(iBinder2, z);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public boolean isDefaultEnableOneshot() {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.28
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(IWakeupEngine iWakeupEngine) throws RemoteException {
                return Boolean.valueOf(iWakeupEngine.isDefaultEnableOneshot());
            }
        }, false)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void setDefaultOneshotEnabled(final boolean z) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.29
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.setDefaultOneshotEnabled(z);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableOneshot() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.30
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.enableOneshot();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableOneshot() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.31
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.disableOneshot();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public boolean isDefaultEnableFastWake() {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.32
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(IWakeupEngine iWakeupEngine) throws RemoteException {
                return Boolean.valueOf(iWakeupEngine.isDefaultEnableFastWake());
            }
        }, false)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void setDefaultFastWakeEnabled(final boolean z) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.33
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.setDefaultFastWakeEnabled(z);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableFastWake() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.34
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.enableFastWake();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableFastWake() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.35
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.disableFastWake();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void stopDialogMessage() {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.36
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.stopDialogMessage();
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void stopDialogReason(final String str) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.37
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.stopDialogReason(str);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableMainWakeupWord(final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.38
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.enableMainWakeupWord(iWakeupEngine.asBinder());
                    return null;
                }
                iWakeupEngine.enableMainWakeupWord(iBinder2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableMainWakeupWord(final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.39
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.disableMainWakeupWord(iWakeupEngine.asBinder());
                    return null;
                }
                iWakeupEngine.disableMainWakeupWord(iBinder2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableFastWakeEnhance(final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.40
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.enableFastWakeEnhance(iWakeupEngine.asBinder());
                    return null;
                }
                iWakeupEngine.enableFastWakeEnhance(iBinder2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableFastWakeEnhance(final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.41
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.disableFastWakeEnhance(iWakeupEngine.asBinder());
                    return null;
                }
                iWakeupEngine.disableFastWakeEnhance(iBinder2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableInterruptWake(final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.42
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.enableInterruptWake(iWakeupEngine.asBinder());
                    return null;
                }
                iWakeupEngine.enableInterruptWake(iBinder2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableInterruptWake(final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.43
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.disableInterruptWake(iWakeupEngine.asBinder());
                    return null;
                }
                iWakeupEngine.disableInterruptWake(iBinder2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void startDialogFrom(final String str) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.44
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.startDialogFrom(str);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void startDialogWithSoundArea(final String str, final int i) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.45
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                iWakeupEngine.startDialogWithSoundArea(str, i);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableWakeupWithInfo(final IBinder iBinder, final int i, final String str, final String str2, final int i2) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.46
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) {
                try {
                    if (iBinder == null) {
                        iWakeupEngine.disableWakeupWithInfo(iWakeupEngine.asBinder(), i, str, str2, i2);
                    } else {
                        iWakeupEngine.disableWakeupWithInfo(iBinder, i, str, str2, i2);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                WakeupEngineProxy wakeupEngineProxy = WakeupEngineProxy.this;
                IBinder iBinder2 = iBinder;
                wakeupEngineProxy.setDisableInfoCache(iBinder2 == null ? iWakeupEngine.asBinder() : iBinder2, i, str, str2, i2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableWakeupWithInfo(final IBinder iBinder, final int i, final String str, final int i2) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.47
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) {
                try {
                    if (iBinder == null) {
                        iWakeupEngine.enableWakeupWithInfo(iWakeupEngine.asBinder(), i, str, i2);
                    } else {
                        iWakeupEngine.enableWakeupWithInfo(iBinder, i, str, i2);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                WakeupEngineProxy wakeupEngineProxy = WakeupEngineProxy.this;
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iBinder2 = iWakeupEngine.asBinder();
                }
                wakeupEngineProxy.removeDisableInfoCache(iBinder2, i, str, i2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableWheelWakeupWithInfo(final IBinder iBinder, final String str, final String str2, final int i) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.48
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) {
                try {
                    if (iBinder == null) {
                        iWakeupEngine.disableWheelWakeupWithInfo(iWakeupEngine.asBinder(), str, str2, i);
                    } else {
                        iWakeupEngine.disableWheelWakeupWithInfo(iBinder, str, str2, i);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                WakeupEngineProxy wakeupEngineProxy = WakeupEngineProxy.this;
                IBinder iBinder2 = iBinder;
                wakeupEngineProxy.setDisableInfoCache(iBinder2 == null ? iWakeupEngine.asBinder() : iBinder2, -1, str, str2, i);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableWheelWakeupWithInfo(final IBinder iBinder, final String str, final int i) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.49
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) {
                try {
                    if (iBinder == null) {
                        iWakeupEngine.enableWheelWakeupWithInfo(iWakeupEngine.asBinder(), str, i);
                    } else {
                        iWakeupEngine.enableWheelWakeupWithInfo(iBinder, str, i);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                WakeupEngineProxy wakeupEngineProxy = WakeupEngineProxy.this;
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iBinder2 = iWakeupEngine.asBinder();
                }
                wakeupEngineProxy.removeDisableInfoCache(iBinder2, -1, str, i);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void suspendDialogWithReason(final IBinder iBinder, final String str, final String str2) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.50
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.suspendDialogWithReason(iWakeupEngine.asBinder(), str, str2);
                    return null;
                }
                iWakeupEngine.suspendDialogWithReason(iBinder2, str, str2);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void resumeDialogWithReason(final IBinder iBinder, final String str, final String str2) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.51
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) throws RemoteException {
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iWakeupEngine.resumeDialogWithReason(iWakeupEngine.asBinder(), str, str2);
                    return null;
                }
                iWakeupEngine.resumeDialogWithReason(iBinder2, str, str2);
                return null;
            }
        });
    }

    public void setHandler(WorkerHandler workerHandler) {
        this.mIpcRunner.setWorkerHandler(workerHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisableInfoCache(IBinder iBinder, int i, String str, String str2, int i2) {
        String generateKey = generateKey(iBinder, i, str, i2);
        LogUtils.i(TAG, "setDisableInfoCache :  " + generateKey);
        if (TextUtils.isEmpty(generateKey)) {
            return;
        }
        DisableInfoBean disableInfoBean = new DisableInfoBean(iBinder, i, str, str2, i2);
        if (this.disableInfoCache.containsKey(generateKey)) {
            return;
        }
        LogUtils.i(TAG, "put data  :  " + generateKey + ": " + disableInfoBean.toString());
        this.disableInfoCache.put(generateKey, disableInfoBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDisableInfoCache(IBinder iBinder, int i, String str, int i2) {
        String generateKey = generateKey(iBinder, i, str, i2);
        LogUtils.i(TAG, "removeDisableInfoCache :  " + generateKey);
        if (TextUtils.isEmpty(generateKey) || !this.disableInfoCache.containsKey(generateKey)) {
            return;
        }
        LogUtils.i(TAG, "remove  :  " + generateKey + ": " + this.disableInfoCache.size());
        this.disableInfoCache.remove(generateKey);
        LogUtils.i(TAG, "remove after :  " + generateKey + ": " + this.disableInfoCache.size());
    }

    private String generateKey(IBinder iBinder, int i, String str, int i2) {
        String str2;
        if (iBinder != null) {
            String obj = iBinder.toString();
            if (i == -1) {
                str2 = obj + "_" + str;
            } else {
                str2 = obj + "_" + i + "_" + str;
            }
            if (i2 != -1) {
                return str2 + "_" + i2;
            }
            return str2;
        }
        return null;
    }

    private void resumeCarSpeechStatus() {
        LogUtils.i(TAG, "resumeCarSpeechStatus  disableInfoCache size " + this.disableInfoCache.size() + ",suspendInfoCache size " + this.suspendInfoCache.size() + ",disableSpeechInfoCache size " + this.disableSpeechInfoCache.size());
        if (this.disableInfoCache.size() > 0) {
            for (Map.Entry<String, DisableInfoBean> entry : this.disableInfoCache.entrySet()) {
                DisableInfoBean value = entry.getValue();
                if (value != null) {
                    LogUtils.i(TAG, "disable from cache:    = ====  " + value.toString());
                    if (value.getType() == -1) {
                        disableWheelWakeupWithInfo(value.getBinder(), value.getByWho(), value.getInfo(), value.getNotifyType());
                    } else {
                        disableWakeupWithInfo(value.getBinder(), value.getType(), value.getByWho(), value.getInfo(), value.getNotifyType());
                    }
                }
            }
        }
        if (this.suspendInfoCache.size() > 0) {
            for (Map.Entry<String, List<SuspendInfoBean>> entry2 : this.suspendInfoCache.entrySet()) {
                List<SuspendInfoBean> value2 = entry2.getValue();
                for (int i = 0; i < value2.size(); i++) {
                    SuspendInfoBean suspendInfoBean = value2.get(i);
                    if (suspendInfoBean != null) {
                        LogUtils.i(TAG, "suspend from cache:    = ====  " + suspendInfoBean.toString());
                        suspendSpeechWithInfo(suspendInfoBean.getBinder(), suspendInfoBean.getByWho(), suspendInfoBean.getInfo(), suspendInfoBean.getNotifyType(), suspendInfoBean.isNeedMic(), false);
                    }
                }
            }
        }
        if (this.disableSpeechInfoCache.size() > 0) {
            for (Map.Entry<String, DisableInfoBean> entry3 : this.disableSpeechInfoCache.entrySet()) {
                DisableInfoBean value3 = entry3.getValue();
                if (value3 != null) {
                    LogUtils.i(TAG, "disable speech from cache:    = ====  " + value3.toString());
                    disableSpeechWithInfo(value3.getBinder(), value3.getByWho(), value3.getInfo(), value3.getNotifyType());
                }
            }
        }
    }

    private void suspendSpeechWithInfo(final IBinder iBinder, final String str, final String str2, final int i, final boolean z, final boolean z2) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.52
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) {
                try {
                    if (iBinder == null) {
                        iWakeupEngine.suspendSpeechWithInfo(iWakeupEngine.asBinder(), str, str2, i, z);
                    } else {
                        iWakeupEngine.suspendSpeechWithInfo(iBinder, str, str2, i, z);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (z2) {
                    WakeupEngineProxy wakeupEngineProxy = WakeupEngineProxy.this;
                    IBinder iBinder2 = iBinder;
                    wakeupEngineProxy.setSuspendInfoCache(iBinder2 == null ? iWakeupEngine.asBinder() : iBinder2, str, str2, i, z);
                    return null;
                }
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void suspendSpeechWithInfo(IBinder iBinder, String str, String str2, int i, boolean z) {
        suspendSpeechWithInfo(iBinder, str, str2, i, z, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSuspendInfoCache(IBinder iBinder, String str, String str2, int i, boolean z) {
        String generateKey = generateKey(iBinder, i, str, -1);
        LogUtils.i(TAG, "setSuspendInfoCache :  " + generateKey);
        if (TextUtils.isEmpty(generateKey)) {
            return;
        }
        SuspendInfoBean suspendInfoBean = new SuspendInfoBean(iBinder, str, str2, i, z);
        List<SuspendInfoBean> list = this.suspendInfoCache.get(generateKey);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(suspendInfoBean);
        this.suspendInfoCache.put(generateKey, list);
        LogUtils.i(TAG, "put data  :  " + generateKey + ": " + suspendInfoBean.toString() + ",suspendInfoCache:" + this.suspendInfoCache.size());
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void resumeSpeechWithInfo(IBinder iBinder, String str) {
        resumeSpeechWithTypeInfo(iBinder, str, -1, false);
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public String getSoundAreaStatus(final int i) {
        if (i < 0) {
            if (CarTypeUtils.isE38ZH() || CarTypeUtils.isE28AZH() || CarTypeUtils.isF30ZH()) {
                return new SoundAreaStatus(i, true).getJsonData();
            }
            return new SoundAreaStatus(i, false).getJsonData();
        }
        return (String) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, String>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.53
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public String run(IWakeupEngine iWakeupEngine) throws RemoteException {
                return iWakeupEngine.getSoundAreaStatus(i);
            }
        }, new SoundAreaStatus(i, true).getJsonData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeSuspendInfoCache(IBinder iBinder, int i, String str, boolean z) {
        String generateKey = generateKey(iBinder, i, str, -1);
        LogUtils.i(TAG, "removeSuspendInfoCache :  " + generateKey);
        if (TextUtils.isEmpty(generateKey) || !this.suspendInfoCache.containsKey(generateKey)) {
            return;
        }
        LogUtils.i(TAG, "remove  :  " + generateKey + ": " + this.suspendInfoCache.size());
        if (z) {
            this.suspendInfoCache.remove(generateKey);
        } else {
            List<SuspendInfoBean> list = this.suspendInfoCache.get(generateKey);
            if (list.size() > 0) {
                list.remove(0);
            }
            LogUtils.i(TAG, "remove after :  " + generateKey + ": infoBeans" + list.size());
            if (list.size() > 0) {
                this.suspendInfoCache.put(generateKey, list);
            } else {
                this.suspendInfoCache.remove(generateKey);
            }
        }
        LogUtils.i(TAG, "remove after :  " + generateKey + ": " + this.suspendInfoCache.size());
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void disableSpeechWithInfo(final IBinder iBinder, final String str, final String str2, final int i) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.54
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) {
                try {
                    if (iBinder == null) {
                        iWakeupEngine.disableSpeechWithInfo(iWakeupEngine.asBinder(), str, str2, i);
                    } else {
                        iWakeupEngine.disableSpeechWithInfo(iBinder, str, str2, i);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                WakeupEngineProxy wakeupEngineProxy = WakeupEngineProxy.this;
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iBinder2 = iWakeupEngine.asBinder();
                }
                wakeupEngineProxy.setDisableSpeechInfoCache(iBinder2, str, str2, i);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void enableSpeechWithInfo(final IBinder iBinder, final String str, final int i) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.55
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) {
                try {
                    if (iBinder == null) {
                        iWakeupEngine.enableSpeechWithInfo(iWakeupEngine.asBinder(), str, i);
                    } else {
                        iWakeupEngine.enableSpeechWithInfo(iBinder, str, i);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                WakeupEngineProxy wakeupEngineProxy = WakeupEngineProxy.this;
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iBinder2 = iWakeupEngine.asBinder();
                }
                wakeupEngineProxy.removeDisableSpeechInfoCache(iBinder2, str, i);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisableSpeechInfoCache(IBinder iBinder, String str, String str2, int i) {
        String generateKey = generateKey(iBinder, i, str, -1);
        LogUtils.i(TAG, "setDisableSpeechInfoCache :  " + generateKey);
        if (TextUtils.isEmpty(generateKey)) {
            return;
        }
        DisableInfoBean disableInfoBean = new DisableInfoBean(iBinder, -1, str, str2, i);
        if (this.disableSpeechInfoCache.containsKey(generateKey)) {
            return;
        }
        LogUtils.i(TAG, "put data  :  " + generateKey + ": " + disableInfoBean.toString());
        this.disableSpeechInfoCache.put(generateKey, disableInfoBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDisableSpeechInfoCache(IBinder iBinder, String str, int i) {
        String generateKey = generateKey(iBinder, i, str, -1);
        LogUtils.i(TAG, "removeDisableSpeechInfoCache :  " + generateKey);
        if (TextUtils.isEmpty(generateKey) || !this.disableSpeechInfoCache.containsKey(generateKey)) {
            return;
        }
        LogUtils.i(TAG, "remove  :  " + generateKey + ": " + this.disableSpeechInfoCache.size());
        this.disableSpeechInfoCache.remove(generateKey);
        LogUtils.i(TAG, "remove after :  " + generateKey + ": " + this.disableSpeechInfoCache.size());
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void resumeSpeechWithTypeInfo(final IBinder iBinder, final String str, final int i, final boolean z) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.56
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) {
                try {
                    if (iBinder == null) {
                        iWakeupEngine.resumeSpeechWithTypeInfo(iWakeupEngine.asBinder(), str, i, z);
                    } else {
                        iWakeupEngine.resumeSpeechWithTypeInfo(iBinder, str, i, z);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                WakeupEngineProxy wakeupEngineProxy = WakeupEngineProxy.this;
                IBinder iBinder2 = iBinder;
                if (iBinder2 == null) {
                    iBinder2 = iWakeupEngine.asBinder();
                }
                wakeupEngineProxy.removeSuspendInfoCache(iBinder2, i, str, z);
                return null;
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public void subscribeMultiTask(final String str, final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, Object>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.57
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(IWakeupEngine iWakeupEngine) {
                try {
                    iWakeupEngine.subscribeMultiTask(str, iBinder);
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public AvatarStatus getAvatarStatus() {
        return (AvatarStatus) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, AvatarStatus>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.58
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public AvatarStatus run(IWakeupEngine iWakeupEngine) {
                try {
                    return iWakeupEngine.getAvatarStatus();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }, null);
    }

    @Override // com.xiaopeng.speech.coreapi.IWakeupEngine
    public SwitchStatus getDialogSwitchStatus() {
        return (SwitchStatus) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<IWakeupEngine, SwitchStatus>() { // from class: com.xiaopeng.speech.proxy.WakeupEngineProxy.59
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public SwitchStatus run(IWakeupEngine iWakeupEngine) {
                try {
                    return iWakeupEngine.getDialogSwitchStatus();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }, null);
    }
}
