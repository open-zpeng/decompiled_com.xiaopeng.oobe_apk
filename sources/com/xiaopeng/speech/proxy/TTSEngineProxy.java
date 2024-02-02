package com.xiaopeng.speech.proxy;

import android.os.IBinder;
import android.os.RemoteException;
import com.xiaopeng.speech.ConnectManager;
import com.xiaopeng.speech.ISpeechEngine;
import com.xiaopeng.speech.common.SpeechConstant;
import com.xiaopeng.speech.common.util.IPCRunner;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.common.util.WorkerHandler;
import com.xiaopeng.speech.coreapi.ITTSCallback;
import com.xiaopeng.speech.coreapi.ITTSEngine;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class TTSEngineProxy extends ITTSEngine.Stub implements ConnectManager.OnConnectCallback {
    private WorkerHandler mWorkerHandler;
    private Map<String, ISpeakCallback> mSpeakCallbackMap = new ConcurrentHashMap();
    private volatile boolean mHadSetCallback = false;
    private IPCRunner<ITTSEngine> mIpcRunner = new IPCRunner<>("TTSEngineProxy");
    private ITTSCallback mTTSCallback = new ITTSCallback.Stub() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.1
        @Override // com.xiaopeng.speech.coreapi.ITTSCallback
        public void beginning(final String str, final String str2) {
            TTSEngineProxy.this.mWorkerHandler.optPost(new Runnable() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.1.1
                @Override // java.lang.Runnable
                public void run() {
                    ISpeakCallback iSpeakCallback = (ISpeakCallback) TTSEngineProxy.this.mSpeakCallbackMap.get(str);
                    if (iSpeakCallback != null) {
                        iSpeakCallback.beginning(str, str2);
                    }
                }
            });
        }

        @Override // com.xiaopeng.speech.coreapi.ITTSCallback
        public void received(final byte[] bArr) {
            TTSEngineProxy.this.mWorkerHandler.optPost(new Runnable() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.1.2
                @Override // java.lang.Runnable
                public void run() {
                    for (Map.Entry entry : TTSEngineProxy.this.mSpeakCallbackMap.entrySet()) {
                        ((ISpeakCallback) entry.getValue()).received(bArr);
                    }
                }
            });
        }

        @Override // com.xiaopeng.speech.coreapi.ITTSCallback
        public void end(final String str, final int i, final String str2) {
            TTSEngineProxy.this.mWorkerHandler.optPost(new Runnable() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.1.3
                @Override // java.lang.Runnable
                public void run() {
                    ISpeakCallback iSpeakCallback = (ISpeakCallback) TTSEngineProxy.this.mSpeakCallbackMap.get(str);
                    if (iSpeakCallback != null) {
                        iSpeakCallback.end(str, i, str2);
                        TTSEngineProxy.this.mSpeakCallbackMap.remove(str);
                    }
                }
            });
        }

        @Override // com.xiaopeng.speech.coreapi.ITTSCallback
        public void error(final String str, final String str2) {
            TTSEngineProxy.this.mWorkerHandler.optPost(new Runnable() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.1.4
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = TTSEngineProxy.this.mSpeakCallbackMap.entrySet().iterator();
                    while (it.hasNext()) {
                        ((ISpeakCallback) ((Map.Entry) it.next()).getValue()).error(str, str2);
                        it.remove();
                    }
                }
            });
        }
    };

    public void setHandler(WorkerHandler workerHandler) {
        this.mWorkerHandler = workerHandler;
        this.mIpcRunner.setWorkerHandler(this.mWorkerHandler);
    }

    public String speak(final String str) {
        this.mWorkerHandler.optPostDelay(new Runnable() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.2
            @Override // java.lang.Runnable
            public void run() {
                TTSEngineProxy.this.speak(str, 1, 3, -1, null);
            }
        }, 50L);
        return "";
    }

    public String speak(String str, int i) {
        return speak(str, i, 3);
    }

    public String speak(String str, int i, int i2) {
        return speak(str, i, i2, (ISpeakCallback) null);
    }

    public String speak(String str, ISpeakCallback iSpeakCallback) {
        return speak(str, 1, iSpeakCallback);
    }

    public String speak(String str, int i, ISpeakCallback iSpeakCallback) {
        return speak(str, i, 3, iSpeakCallback);
    }

    public String speak(String str, int i, int i2, ISpeakCallback iSpeakCallback) {
        return speak(str, i, i2, -1, iSpeakCallback);
    }

    public String speak(String str, int i, int i2, int i3, ISpeakCallback iSpeakCallback) {
        String uuid = UUID.randomUUID().toString();
        speakEnhance(str, i, uuid, i2, i3, SpeechConstant.TTS_DEFAULT_MODEL, SpeechConstant.TTS_TIMEOUT);
        addTSSListener();
        if (iSpeakCallback != null) {
            this.mSpeakCallbackMap.put(uuid, iSpeakCallback);
            LogUtils.i("speak callback size:" + this.mSpeakCallbackMap.size());
        }
        return uuid;
    }

    public void removeSpeakCallback(String str) {
        this.mSpeakCallbackMap.remove(str);
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public boolean speakEnhance(final String str, final int i, final String str2, final int i2, final int i3, final int i4, final long j) {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.3
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    return Boolean.valueOf(iTTSEngine.speakEnhance(str, i, str2, i2, i3, i4, j));
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return false;
                }
            }
        }, false)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public boolean speak(final String str, final int i, final String str2, final int i2) {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.4
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    return Boolean.valueOf(iTTSEngine.speak(str, i, str2, i2));
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return false;
                }
            }
        }, false)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void setSpeaker(final String str) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.5
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.setSpeaker(str);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public String getSpeaker() {
        return (String) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, String>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.6
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public String run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    return iTTSEngine.getSpeaker();
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        }, null);
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void setSpeed(final float f) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.7
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.setSpeed(f);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public float getSpeed() {
        return ((Float) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Float>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.8
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Float run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    return Float.valueOf(iTTSEngine.getSpeed());
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return Float.valueOf(0.0f);
                }
            }
        }, Float.valueOf(0.0f))).floatValue();
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void setVolume(final int i) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.9
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.setVolume(i);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public int getVolume() {
        return ((Integer) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Integer>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.10
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Integer run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    return Integer.valueOf(iTTSEngine.getVolume());
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return 0;
                }
            }
        }, 0)).intValue();
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void setMode(final int i) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.11
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.setMode(i);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void setSoloMode(final boolean z) throws RemoteException {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.12
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.setSoloMode(z);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void shutupByReason(final String str, final String str2) throws RemoteException {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.13
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.shutupByReason(str, str2);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public boolean speakEnhanceByVoicePosition(final String str, final int i, final String str2, final int i2, final int i3, final int i4, final long j, final int i5) throws RemoteException {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.14
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    return Boolean.valueOf(iTTSEngine.speakEnhanceByVoicePosition(str, i, str2, i2, i3, i4, j, i5));
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return false;
                }
            }
        }, false)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public boolean speakParam(final String str, final String str2) throws RemoteException {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.15
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    return Boolean.valueOf(iTTSEngine.speakParam(str, str2));
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return false;
                }
            }
        }, false)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void shutupByChannel(final int i) throws RemoteException {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.16
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.shutupByChannel(i);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void setSoloModeByChannel(final int i, final boolean z) throws RemoteException {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.17
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.setSoloModeByChannel(i, z);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void shutup(final String str) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.18
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.shutup(str);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void addListener(final ITTSCallback iTTSCallback) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.19
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.addListener(iTTSCallback);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void removeListener(final ITTSCallback iTTSCallback) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.20
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.removeListener(iTTSCallback);
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public void setSingleTTS(final boolean z, final IBinder iBinder) {
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.21
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    if (iBinder == null) {
                        iTTSEngine.setSingleTTS(z, iTTSEngine.asBinder());
                    } else {
                        iTTSEngine.setSingleTTS(z, iBinder);
                    }
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public boolean isSingleTTS() {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.22
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    return Boolean.valueOf(iTTSEngine.isSingleTTS());
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return false;
                }
            }
        }, false)).booleanValue();
    }

    @Override // com.xiaopeng.speech.coreapi.ITTSEngine
    public boolean isTTSSupportSpell() {
        return ((Boolean) this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Boolean>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.23
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Boolean run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    return Boolean.valueOf(iTTSEngine.isTTSSupportSpell());
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return false;
                }
            }
        }, false)).booleanValue();
    }

    private synchronized void addTSSListener() {
        if (this.mHadSetCallback) {
            return;
        }
        this.mIpcRunner.runFunc(new IPCRunner.IIPCFunc<ITTSEngine, Object>() { // from class: com.xiaopeng.speech.proxy.TTSEngineProxy.24
            @Override // com.xiaopeng.speech.common.util.IPCRunner.IIPCFunc
            public Object run(ITTSEngine iTTSEngine) throws RemoteException {
                try {
                    iTTSEngine.addListener(TTSEngineProxy.this.mTTSCallback);
                    TTSEngineProxy.this.mHadSetCallback = true;
                    return null;
                } catch (Throwable th) {
                    LogUtils.e("IPC Excption:" + th.getMessage());
                    return null;
                }
            }
        });
    }

    @Override // com.xiaopeng.speech.ConnectManager.OnConnectCallback
    public void onConnect(ISpeechEngine iSpeechEngine) {
        try {
            this.mIpcRunner.setProxy(iSpeechEngine.getTTSEngine());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
        this.mIpcRunner.fetchAll();
    }

    @Override // com.xiaopeng.speech.ConnectManager.OnConnectCallback
    public void onDisconnect() {
        this.mHadSetCallback = false;
        this.mIpcRunner.setProxy(null);
    }

    public String speak(String str, String str2, ISpeakCallback iSpeakCallback) throws RemoteException {
        String uuid = UUID.randomUUID().toString();
        int i = SpeechConstant.TTS_DEFAULT_MODEL;
        speakParam(str, str2);
        addTSSListener();
        if (iSpeakCallback != null) {
            this.mSpeakCallbackMap.put(uuid, iSpeakCallback);
            LogUtils.i("speak callback size:" + this.mSpeakCallbackMap.size());
        }
        return uuid;
    }
}
