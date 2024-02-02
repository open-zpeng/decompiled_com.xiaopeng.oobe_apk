package com.xiaopeng.speech;

import android.content.Context;
import android.os.HandlerThread;
import com.xiaopeng.speech.ConnectManager;
import com.xiaopeng.speech.actor.Actor;
import com.xiaopeng.speech.actor.ActorBridge;
import com.xiaopeng.speech.asr.Recognizer;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.common.util.WorkerHandler;
import com.xiaopeng.speech.protocol.BuildConfig;
import com.xiaopeng.speech.proxy.ASREngineProxy;
import com.xiaopeng.speech.proxy.AgentProxy;
import com.xiaopeng.speech.proxy.AppMgrProxy;
import com.xiaopeng.speech.proxy.CarSystemPropertyProxy;
import com.xiaopeng.speech.proxy.HotwordEngineProxy;
import com.xiaopeng.speech.proxy.QueryInjectorProxy;
import com.xiaopeng.speech.proxy.RecordEngineProxy;
import com.xiaopeng.speech.proxy.SoundLockStateProxy;
import com.xiaopeng.speech.proxy.SpeechStateProxy;
import com.xiaopeng.speech.proxy.SubscriberProxy;
import com.xiaopeng.speech.proxy.TTSEngineProxy;
import com.xiaopeng.speech.proxy.VADEngineProxy;
import com.xiaopeng.speech.proxy.WakeupEngineProxy;
import com.xiaopeng.speech.proxy.WindowEngineProxy;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes.dex */
public class SpeechClient implements ConnectManager.OnConnectCallback {
    private final AtomicBoolean initSucceeded = new AtomicBoolean(false);
    private final ReentrantLock lock = new ReentrantLock();
    private volatile ASREngineProxy mASREngineProxy;
    private volatile ActorBridge mActorBridge;
    private volatile AgentProxy mAgentProxy;
    private volatile AppMgrProxy mAppMgrProxy;
    private volatile CarSystemPropertyProxy mCarSystemPropertyProxy;
    private volatile ConnectManager mConnectManager;
    private volatile Context mContext;
    private volatile HotwordEngineProxy mHotwordEngineProxy;
    private volatile NodeManager mNodeManager;
    private volatile QueryInjectorProxy mQueryInjectorProxy;
    private volatile QueryManager mQueryManager;
    private volatile Recognizer mRecognizer;
    private volatile RecordEngineProxy mRecordEngineProxy;
    private volatile SoundLockStateProxy mSoundLockStateProxy;
    private volatile SpeechStateProxy mSpeechStateProxy;
    private volatile SubscriberProxy mSubscriberProxy;
    private volatile TTSEngineProxy mTTSEngineProxy;
    private volatile VADEngineProxy mVADEngineProxy;
    private volatile WakeupEngineProxy mWakeupEngineProxy;
    private volatile WindowEngineProxy mWindowEngineProxy;

    @Override // com.xiaopeng.speech.ConnectManager.OnConnectCallback
    public void onConnect(ISpeechEngine iSpeechEngine) {
    }

    @Override // com.xiaopeng.speech.ConnectManager.OnConnectCallback
    public void onDisconnect() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Holder {
        private static final SpeechClient Instance = new SpeechClient();

        private Holder() {
        }
    }

    public static SpeechClient instance() {
        return Holder.Instance;
    }

    public void init(Context context) {
        init(context, null);
    }

    public void init(Context context, ConnectManager.OnConnectCallback onConnectCallback) {
        LogUtils.i(this, "SpeechClient(%s) Start In %s, connectCallback: %s, build version: %s, build time: ", "1.0", context.getPackageName(), onConnectCallback, BuildConfig.BUILD_VERSION, BuildConfig.BUILD_TIME);
        this.lock.lock();
        try {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.initSucceeded.get()) {
                this.mConnectManager.addCallback(onConnectCallback);
                return;
            }
            HandlerThread handlerThread = new HandlerThread("NodeWorker");
            handlerThread.start();
            WorkerHandler workerHandler = new WorkerHandler(handlerThread.getLooper());
            this.mContext = context;
            this.mConnectManager = new ConnectManager(context);
            this.mConnectManager.init(workerHandler);
            this.mNodeManager = new NodeManager();
            this.mNodeManager.init(workerHandler);
            this.mConnectManager.addCallback(this.mNodeManager);
            this.mQueryManager = new QueryManager();
            this.mQueryManager.init(workerHandler);
            this.mConnectManager.addCallback(this.mQueryManager);
            this.mActorBridge = new ActorBridge(context);
            this.mActorBridge.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mActorBridge);
            this.mTTSEngineProxy = new TTSEngineProxy();
            this.mTTSEngineProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mTTSEngineProxy);
            this.mWakeupEngineProxy = new WakeupEngineProxy();
            this.mWakeupEngineProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mWakeupEngineProxy);
            this.mHotwordEngineProxy = new HotwordEngineProxy();
            this.mHotwordEngineProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mHotwordEngineProxy);
            this.mAgentProxy = new AgentProxy();
            this.mAgentProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mAgentProxy);
            this.mSubscriberProxy = new SubscriberProxy();
            this.mSubscriberProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mSubscriberProxy);
            this.mAppMgrProxy = new AppMgrProxy(context);
            this.mAppMgrProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mAppMgrProxy);
            this.mSpeechStateProxy = new SpeechStateProxy();
            this.mConnectManager.addCallback(this.mSpeechStateProxy);
            this.mSoundLockStateProxy = new SoundLockStateProxy();
            this.mConnectManager.addCallback(this.mSoundLockStateProxy);
            this.mASREngineProxy = new ASREngineProxy();
            this.mASREngineProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mASREngineProxy);
            this.mRecordEngineProxy = new RecordEngineProxy();
            this.mRecordEngineProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mRecordEngineProxy);
            this.mQueryInjectorProxy = new QueryInjectorProxy();
            this.mQueryInjectorProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mQueryInjectorProxy);
            this.mWindowEngineProxy = new WindowEngineProxy();
            this.mConnectManager.addCallback(this.mWindowEngineProxy);
            this.mRecognizer = new Recognizer(workerHandler);
            this.mConnectManager.addCallback(this.mRecognizer.getConnectCallback());
            this.mCarSystemPropertyProxy = new CarSystemPropertyProxy();
            this.mConnectManager.addCallback(this.mCarSystemPropertyProxy);
            this.mVADEngineProxy = new VADEngineProxy();
            this.mVADEngineProxy.setHandler(workerHandler);
            this.mConnectManager.addCallback(this.mVADEngineProxy);
            if (onConnectCallback == null) {
                this.mConnectManager.addCallback(this);
            } else {
                this.mConnectManager.addCallback(onConnectCallback);
            }
            this.mConnectManager.connect();
            this.mConnectManager.registerReceiver();
            this.initSucceeded.set(true);
        } finally {
            this.lock.unlock();
        }
    }

    public synchronized void setAppName(String... strArr) {
        for (String str : strArr) {
            getAppMgr().registerApp(this.mContext.getPackageName(), str);
        }
    }

    public ActorBridge getActorBridge() {
        return this.mActorBridge;
    }

    public SubscriberProxy getSubscriber() {
        return this.mSubscriberProxy;
    }

    public TTSEngineProxy getTTSEngine() {
        return this.mTTSEngineProxy;
    }

    public WakeupEngineProxy getWakeupEngine() {
        return this.mWakeupEngineProxy;
    }

    public HotwordEngineProxy getHotwordEngine() {
        return this.mHotwordEngineProxy;
    }

    public AgentProxy getAgent() {
        return this.mAgentProxy;
    }

    public AppMgrProxy getAppMgr() {
        return this.mAppMgrProxy;
    }

    public SpeechStateProxy getSpeechState() {
        return this.mSpeechStateProxy;
    }

    public SoundLockStateProxy getSoundLockState() {
        return this.mSoundLockStateProxy;
    }

    public ASREngineProxy getASREngine() {
        return this.mASREngineProxy;
    }

    public QueryInjectorProxy getQueryInjector() {
        return this.mQueryInjectorProxy;
    }

    public RecordEngineProxy getRecordEngine() {
        return this.mRecordEngineProxy;
    }

    public WindowEngineProxy getWindowEngine() {
        return this.mWindowEngineProxy;
    }

    public Recognizer getRecognizer() {
        return this.mRecognizer;
    }

    public VADEngineProxy getVadEngine() {
        return this.mVADEngineProxy;
    }

    public NodeManager getNodeManager() {
        return this.mNodeManager;
    }

    public QueryManager getQueryManager() {
        return this.mQueryManager;
    }

    public CarSystemPropertyProxy getCarSystemProperty() {
        return this.mCarSystemPropertyProxy;
    }

    public void sendActor(Actor actor) {
        this.mActorBridge.send(actor);
    }
}
