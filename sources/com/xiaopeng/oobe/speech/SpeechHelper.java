package com.xiaopeng.oobe.speech;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.lib.bughunter.anr.UILooperObserver;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.privacymanager.PrivacySettings;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.common.SpeechConstant;
import com.xiaopeng.speech.coreapi.ISpeechConfigCallback;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class SpeechHelper {
    private static final int MAX_FAIL_COUNT = 10;
    private static final String TAG = "SpeechHelper";
    private volatile boolean isInitState;
    private int mFailCount;
    private long mInitTTSTime;
    private TextToSpeech.OnInitListener mOnInitListener;
    private OnSpeakEndCallback mRestoreCallBack;
    private String mRestoreSpeakText;
    private CallbackTimeout mRunCallbackTimeout;
    private Runnable mRunInitSpeech;
    private Map<String, OnSpeakEndCallback> mSpeakCallbackMap;
    private volatile int mStatus;
    private TextToSpeech mTextToSpeech;
    private UtteranceProgressListener mUtteranceProgressListener;

    /* loaded from: classes.dex */
    public interface OnVuiListener {
        void onConfigSetResult(boolean z, String str);
    }

    static /* synthetic */ int access$504(SpeechHelper speechHelper) {
        int i = speechHelper.mFailCount + 1;
        speechHelper.mFailCount = i;
        return i;
    }

    public boolean isInit() {
        return this.mStatus == 0;
    }

    private SpeechHelper() {
        this.mStatus = -1;
        this.mSpeakCallbackMap = new ConcurrentHashMap();
        this.mRunCallbackTimeout = null;
        this.mUtteranceProgressListener = new UtteranceProgressListener() { // from class: com.xiaopeng.oobe.speech.SpeechHelper.1
            @Override // android.speech.tts.UtteranceProgressListener
            public void onStart(String str) {
                LogUtils.d(SpeechHelper.TAG, "onStart: " + str, false);
                OnSpeakEndCallback onSpeakEndCallback = (OnSpeakEndCallback) SpeechHelper.this.mSpeakCallbackMap.get(str);
                if (onSpeakEndCallback != null) {
                    onSpeakEndCallback.onStart(str);
                }
                if (SpeechHelper.this.mRunCallbackTimeout != null) {
                    ThreadUtils.removeRunnable(SpeechHelper.this.mRunCallbackTimeout);
                    SpeechHelper.this.mRunCallbackTimeout = null;
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onDone(String str) {
                LogUtils.d(SpeechHelper.TAG, "onDone: " + str);
                OnSpeakEndCallback onSpeakEndCallback = (OnSpeakEndCallback) SpeechHelper.this.mSpeakCallbackMap.get(str);
                if (onSpeakEndCallback != null) {
                    onSpeakEndCallback.onDone(str);
                    SpeechHelper.this.mSpeakCallbackMap.remove(str);
                }
                if (SpeechHelper.this.mRunCallbackTimeout != null) {
                    ThreadUtils.removeRunnable(SpeechHelper.this.mRunCallbackTimeout);
                    SpeechHelper.this.mRunCallbackTimeout = null;
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onError(String str) {
                LogUtils.e(SpeechHelper.TAG, "onError: " + str);
                OnSpeakEndCallback onSpeakEndCallback = (OnSpeakEndCallback) SpeechHelper.this.mSpeakCallbackMap.get(str);
                if (onSpeakEndCallback != null) {
                    onSpeakEndCallback.onError(str);
                    SpeechHelper.this.mSpeakCallbackMap.remove(str);
                }
                if (SpeechHelper.this.mRunCallbackTimeout != null) {
                    ThreadUtils.removeRunnable(SpeechHelper.this.mRunCallbackTimeout);
                    SpeechHelper.this.mRunCallbackTimeout = null;
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onStop(String str, boolean z) {
                super.onStop(str, z);
                OnSpeakEndCallback onSpeakEndCallback = (OnSpeakEndCallback) SpeechHelper.this.mSpeakCallbackMap.get(str);
                LogUtils.e(SpeechHelper.TAG, "onStop:" + str + ",speakCallback:" + onSpeakEndCallback);
                if (onSpeakEndCallback != null) {
                    onSpeakEndCallback.onStop(str, z);
                    SpeechHelper.this.mSpeakCallbackMap.remove(str);
                }
                if (SpeechHelper.this.mRunCallbackTimeout != null) {
                    ThreadUtils.removeRunnable(SpeechHelper.this.mRunCallbackTimeout);
                    SpeechHelper.this.mRunCallbackTimeout = null;
                }
            }
        };
        this.mOnInitListener = new TextToSpeech.OnInitListener() { // from class: com.xiaopeng.oobe.speech.SpeechHelper.2
            @Override // android.speech.tts.TextToSpeech.OnInitListener
            public void onInit(int i) {
                LogUtils.i(SpeechHelper.TAG, "OnInitListener status:" + i);
                SpeechHelper.this.mStatus = i;
                SpeechHelper.this.isInitState = false;
                if (SpeechHelper.this.mStatus == 0) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    Log.e("Time_SpeechHelper", "oobe speech tts success time interval = " + (elapsedRealtime - SpeechHelper.this.mInitTTSTime) + "  successTTSTime" + elapsedRealtime + "mFailCount =" + SpeechHelper.this.mFailCount);
                    SpeechHelper.this.mTextToSpeech.setOnUtteranceProgressListener(SpeechHelper.this.mUtteranceProgressListener);
                    SpeechHelper.this.restoreSpeak();
                    return;
                }
                LogUtils.e(SpeechHelper.TAG, "TextToSpeech init error");
                SpeechHelper.access$504(SpeechHelper.this);
                ThreadUtils.removeRunnable(SpeechHelper.this.mRunInitSpeech);
                if (SpeechHelper.this.mFailCount < 10) {
                    ThreadUtils.postMainThread(SpeechHelper.this.mRunInitSpeech, 1000L);
                }
            }
        };
        this.mRunInitSpeech = new Runnable() { // from class: com.xiaopeng.oobe.speech.-$$Lambda$SpeechHelper$OBBZ2iuRFsnv27VvIE2jSBSQNBw
            @Override // java.lang.Runnable
            public final void run() {
                SpeechHelper.this.lambda$new$0$SpeechHelper();
            }
        };
    }

    public static SpeechHelper getInstance() {
        return SingletonHolder.sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreSpeak() {
        LogUtils.i(TAG, "restoreSpeak: mRestoreSpeakText=" + this.mRestoreSpeakText + " mRestoreCallBack=" + this.mRestoreCallBack);
        if (!TextUtils.isEmpty(this.mRestoreSpeakText)) {
            speak(this.mRestoreSpeakText, this.mRestoreCallBack);
        }
        this.mRestoreCallBack = null;
        this.mRestoreSpeakText = null;
    }

    public void init() {
        LogUtils.i(TAG, Constants.Unity.INIT);
        if (SystemProperties.getInt("service.bootanim.exit", 0) == 1) {
            LogUtils.i(TAG, "init bootanim.exit :");
            ThreadUtils.postMainThread(this.mRunInitSpeech);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initSpeech */
    public void lambda$new$0$SpeechHelper() {
        LogUtils.i(TAG, "initSpeech mStatus :" + this.mStatus);
        if (!App.getInstance().isCanCreateTTS()) {
            LogUtils.i(TAG, "isCanCreateTTS false");
        } else if (this.isInitState) {
            LogUtils.i(TAG, "isInitState is true");
        } else if (this.mStatus != 0 || this.mTextToSpeech == null) {
            if (this.mTextToSpeech != null) {
                LogUtils.i(TAG, "initSpeech retry stop old");
                this.mTextToSpeech.stop();
            }
            this.isInitState = true;
            this.mInitTTSTime = SystemClock.elapsedRealtime();
            this.mTextToSpeech = new TextToSpeech(App.getInstance(), this.mOnInitListener);
            this.mTextToSpeech.setOnInitExt(new TextToSpeech.OnInitListenerExt() { // from class: com.xiaopeng.oobe.speech.SpeechHelper.3
                public void onInit(int i, String str) {
                    LogUtils.i(SpeechHelper.TAG, "OnInitListenerExt status:" + i + ",engine:" + str);
                    SpeechHelper.this.isInitState = false;
                    SpeechHelper.this.mStatus = i;
                }
            });
        }
    }

    public void reInitSpeech() {
        this.mFailCount = 0;
        LogUtils.i(TAG, "reInitSpeech");
        ThreadUtils.removeRunnable(this.mRunInitSpeech);
        ThreadUtils.postMainThread(this.mRunInitSpeech);
    }

    public String speak(String str) {
        return speak(str, true, null);
    }

    public String speak(String str, OnSpeakEndCallback onSpeakEndCallback) {
        return speak(str, true, onSpeakEndCallback);
    }

    private String speak(String str, boolean z, OnSpeakEndCallback onSpeakEndCallback) {
        LogUtils.i(TAG, "speak text:" + str);
        if (this.mTextToSpeech == null) {
            LogUtils.e(TAG, "speak error: mTextToSpeech is null---callback : " + onSpeakEndCallback);
            if (onSpeakEndCallback != null) {
                onSpeakEndCallback.onError(str);
            }
            this.mRestoreSpeakText = str;
            this.mRestoreCallBack = onSpeakEndCallback;
            reInitSpeech();
            return str;
        }
        this.mRunCallbackTimeout = new CallbackTimeout(str);
        ThreadUtils.postMainThread(this.mRunCallbackTimeout, UILooperObserver.ANR_TRIGGER_TIME);
        Bundle bundle = new Bundle();
        if (onSpeakEndCallback != null) {
            this.mSpeakCallbackMap.put(str, onSpeakEndCallback);
            LogUtils.i("speak callback size:" + this.mSpeakCallbackMap.size());
        }
        if (z) {
            this.mTextToSpeech.speak(str, 0, bundle, str);
        } else {
            this.mTextToSpeech.speak(str, 1, bundle, str);
        }
        return str;
    }

    public void stop() {
        TextToSpeech textToSpeech = this.mTextToSpeech;
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
        this.mSpeakCallbackMap.clear();
        CallbackTimeout callbackTimeout = this.mRunCallbackTimeout;
        if (callbackTimeout != null) {
            ThreadUtils.removeRunnable(callbackTimeout);
            this.mRunCallbackTimeout = null;
        }
    }

    public void release() {
        TextToSpeech textToSpeech = this.mTextToSpeech;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
        this.mSpeakCallbackMap.clear();
        this.mFailCount = 0;
        ThreadUtils.removeRunnable(this.mRunInitSpeech);
        this.mTextToSpeech = null;
        this.mRunInitSpeech = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SingletonHolder {
        static SpeechHelper sInstance = new SpeechHelper();

        private SingletonHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CallbackTimeout implements Runnable {
        private String id;

        public CallbackTimeout(String str) {
            this.id = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            OnSpeakEndCallback onSpeakEndCallback = (OnSpeakEndCallback) SpeechHelper.this.mSpeakCallbackMap.get(this.id);
            if (onSpeakEndCallback != null) {
                onSpeakEndCallback.onDone(this.id);
                SpeechHelper.this.mSpeakCallbackMap.remove(this.id);
                SpeechHelper.this.reInitSpeech();
            }
        }
    }

    public void setVuiAuthorization(boolean z, final OnVuiListener onVuiListener) {
        LogUtils.i(TAG, "setVuiAuthorization authorization:" + z + ",listener:" + onVuiListener);
        if (SpeechClient.instance().getAgent() == null) {
            SpeechClient.instance().init(App.getInstance());
        }
        try {
            SpeechClient.instance().getAgent().setConfigDataWithCallback(SpeechConstant.KEY_SPEECH_SCENE_ENABLE_WITH_CALLBACK, String.valueOf(z), new ISpeechConfigCallback.Stub() { // from class: com.xiaopeng.oobe.speech.SpeechHelper.4
                @Override // com.xiaopeng.speech.coreapi.ISpeechConfigCallback
                public void onConfigSetResult(int i, String str) {
                    boolean z2 = i == 0;
                    OnVuiListener onVuiListener2 = onVuiListener;
                    if (onVuiListener2 != null) {
                        onVuiListener2.onConfigSetResult(z2, str);
                    }
                    if (z2 && OOBEHelper.isVui2Version()) {
                        SpeechHelper.this.speedConversation();
                        SpeechHelper.this.multiPersonConversation();
                    }
                    LogUtils.i(SpeechHelper.TAG, "onConfigSetResult code:" + i + ",s:" + str);
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
            LogUtils.i(TAG, "onConfigSetResult eee:" + e.getMessage());
        }
    }

    public void speedConversation() {
        LogUtils.i(TAG, "speedConversation");
        if (SpeechClient.instance().getAgent() == null) {
            SpeechClient.instance().init(App.getInstance());
        }
        SpeechClient.instance().getAgent().setConfigData(SpeechConstant.KEY_ENABLE_EXTREME_SPEECH_DIALOG, String.valueOf(true));
    }

    public void multiPersonConversation() {
        LogUtils.i(TAG, "multiPersonConversation");
        if (SpeechClient.instance().getAgent() == null) {
            SpeechClient.instance().init(App.getInstance());
        }
        SpeechClient.instance().getAgent().setConfigData(SpeechConstant.KEY_ENABLE_DOPPELGANGER, String.valueOf(true));
    }

    public void setVoiceAuthorization(boolean z) {
        LogUtils.i(TAG, "setVoiceAuthorization oneYear:" + z);
        if (SpeechClient.instance().getAgent() == null) {
            SpeechClient.instance().init(App.getInstance());
        }
        SpeechClient.instance().getAgent().setConfigData(z ? SpeechConstant.KEY_DATA_AUTHORIZED : SpeechConstant.KEY_DATA_AUTHORIZED_ONCE, String.valueOf(true));
    }

    public void setSpeechPlan(boolean z) {
        LogUtils.i(TAG, "setSpeechPlan check:" + z);
        if (SpeechClient.instance().getAgent() == null) {
            SpeechClient.instance().init(App.getInstance());
        }
        SpeechClient.instance().getAgent().setConfigData(SpeechConstant.KEY_USER_EXPRESSION_ENABLE, String.valueOf(z));
        PrivacySettings.setEnabled(App.getInstance(), 103, z);
        PrivacySettings.setAgreed(App.getInstance(), 103, z);
    }

    public void vuiAllTimeAwake() {
        LogUtils.i(TAG, Constants.Unity.VUI_ALL_TIME_AWAKE);
        if (SpeechClient.instance().getAgent() == null) {
            SpeechClient.instance().init(App.getInstance());
        }
        SpeechClient.instance().getAgent().setConfigData(SpeechConstant.KEY_ENABLE_WAIT_AWAKE, String.valueOf(true));
    }
}
