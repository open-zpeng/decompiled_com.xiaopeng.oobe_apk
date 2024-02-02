package com.xiaopeng.oobe;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.SystemProperties;
import android.speech.tts.TtsEngines;
import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.lib.apirouter.server.ApiPublisherProvider;
import com.xiaopeng.lib.apirouter.server.IManifestHandler;
import com.xiaopeng.lib.apirouter.server.IManifestHelper;
import com.xiaopeng.lib.apirouter.server.ManifestHelper_OOBEApp;
import com.xiaopeng.lib.bughunter.StartPerfUtils;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.lib.utils.info.AppInfoUtils;
import com.xiaopeng.oobe.carmanager.CarClientWrapper;
import com.xiaopeng.oobe.speech.SpeechHelper;
import com.xiaopeng.oobe.utils.BIHelper;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.utils.SPUtils;
import com.xiaopeng.util.FeatureOption;
import com.xiaopeng.xui.Xui;
import java.util.Locale;
/* loaded from: classes.dex */
public class App extends Application {
    private static final String TAG = "com.xiaopeng.oobe.App";
    protected static App sInstance;
    private Activity mActivity;
    private String mCurLan;
    private String mCurrentEngine;
    private TtsEngines mTtsEngines;

    public static App getInstance() {
        return sInstance;
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        sInstance = this;
        SPUtils.init(this);
        if (OOBEHelper.isEURegion()) {
            return;
        }
        Log.i(TAG, "init addManifestHandler=");
        ApiPublisherProvider.addManifestHandler(new IManifestHandler() { // from class: com.xiaopeng.oobe.-$$Lambda$App$UuAyIHbtv9WywTzZfjYex7tL9ec
            @Override // com.xiaopeng.lib.apirouter.server.IManifestHandler
            public final IManifestHelper[] getManifestHelpers() {
                return App.lambda$attachBaseContext$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ IManifestHelper[] lambda$attachBaseContext$0() {
        return new IManifestHelper[]{new ManifestHelper_OOBEApp()};
    }

    @Override // android.app.Application
    public void onCreate() {
        StartPerfUtils.appOnCreateBegin();
        super.onCreate();
        if (OOBEHelper.isSupportLanSelect() && OOBEHelper.isSupportRegSelect() && Locale.getDefault().toString().equals("en_GB")) {
            OOBEHelper.changeSystemLocale(new Locale("en", "NO"));
        }
        this.mCurLan = getSystemLan();
        String str = TAG;
        Log.i(str, "onCreate mCurLan=" + this.mCurLan);
        BIHelper.getInstance().init(this);
        init();
        StartPerfUtils.appOnCreateEnd();
    }

    public void init() {
        sInstance = this;
        Xui.init(this);
        if (!OOBEHelper.isEURegion()) {
            CarClientWrapper.getInstance().connectToCar();
        }
        initXp();
        String str = TAG;
        Log.i(str, "init sInstance=" + sInstance);
    }

    private void initXp() {
        this.mCurrentEngine = Constants.TTS_ENGINE_MS;
        String str = TAG;
        Log.i(str, "init currentEngine=" + this.mCurrentEngine);
        PrivacyHelper.getInstance().init(sInstance);
        ThreadUtils.execute(new Runnable() { // from class: com.xiaopeng.oobe.-$$Lambda$App$70DTfmOGLnk_vO4myV-WwYIW9d0
            @Override // java.lang.Runnable
            public final void run() {
                App.this.lambda$initXp$1$App();
            }
        });
    }

    public /* synthetic */ void lambda$initXp$1$App() {
        int versionCode = AppInfoUtils.getVersionCode(this, getPackageName());
        String versionName = AppInfoUtils.getVersionName(this, getPackageName());
        String str = TAG;
        Log.i(str, "initXp versionCode:" + versionCode + ",versionName:" + versionName + ",bootCompleted:" + isBootCompleted());
        if (OOBEHelper.isEURegion()) {
            Log.i(TAG, "initXp channel_inter");
            return;
        }
        this.mTtsEngines = new TtsEngines(this);
        if (SPUtils.getOobeFinishStep() == -1) {
            this.mTtsEngines.setDefaultEngine(Constants.TTS_ENGINE_MS);
            String str2 = TAG;
            Log.i(str2, "setDefaultEngine=" + this.mCurrentEngine);
        } else {
            this.mCurrentEngine = this.mTtsEngines.getDefaultEngine();
        }
        String str3 = TAG;
        Log.i(str3, "currentEngine=" + this.mCurrentEngine);
        SpeechHelper.getInstance().init();
    }

    public String getRecordSystemLan() {
        return this.mCurLan;
    }

    public String getSystemLan() {
        return Locale.getDefault().getLanguage();
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public boolean isMsTtsEngines() {
        return TextUtils.equals(this.mCurrentEngine, Constants.TTS_ENGINE_MS);
    }

    public void updateTts() {
        TtsEngines ttsEngines = this.mTtsEngines;
        if (ttsEngines != null) {
            this.mCurrentEngine = ttsEngines.getDefaultEngine();
        }
        String str = TAG;
        Log.i(str, "updateTts currentEngine=" + this.mCurrentEngine);
    }

    public boolean isCanCreateTTS() {
        return OOBEHelper.isEURegion() || this.mTtsEngines != null;
    }

    public boolean isBootCompleted() {
        boolean z;
        if (FeatureOption.FO_BOOT_POLICY_ENABLED) {
            z = SystemProperties.getInt("sys.xp.boot_completed", 0) == 1;
            String str = TAG;
            LogUtils.i(str, "sys.xp.boot_completed:" + z);
        } else {
            z = SystemProperties.getInt("service.bootanim.exit", 0) == 1;
            String str2 = TAG;
            LogUtils.i(str2, "service.bootanim.exit:" + z);
        }
        return z;
    }
}
