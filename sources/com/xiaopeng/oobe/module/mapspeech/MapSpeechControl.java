package com.xiaopeng.oobe.module.mapspeech;

import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.speech.SpeechHelper;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.utils.SettingsUtil;
import com.xiaopeng.oobe.viewmodel.IBaseViewControl;
import com.xiaopeng.oobe.viewmodel.IView;
/* loaded from: classes.dex */
public class MapSpeechControl extends IBaseViewControl {
    private static final String TAG = "oobe-MapSpeechControl";

    public MapSpeechControl(IView iView) {
        super(iView);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        super.onStart();
        LogUtils.i(TAG, "onStart mRootView:" + this.mRootView);
    }

    public void setMicrophoneMute(boolean z) {
        LogUtils.i(TAG, "setMicrophoneMute: " + z);
        OOBEHelper.getInstance().setMicrophoneMute(z);
        SpeechHelper.getInstance().setVoiceAuthorization(z ^ true);
    }

    public void signMapProtocol(boolean z) {
        PrivacyHelper.getInstance().setMapGroupRead(z);
        if (z) {
            SettingsUtil.setMapProtocol(String.valueOf(z));
            PrivacyHelper.getInstance().setMapGroupSign();
        }
    }

    public void signSpeechDataProtocol(boolean z) {
        PrivacyHelper.getInstance().setSpeechDataRead(z);
        if (z) {
            PrivacyHelper.getInstance().setSpeechDataSign();
        }
    }

    public void signSpeechPlanProtocol(boolean z) {
        PrivacyHelper.getInstance().setSpeechPlanRead(z);
        if (z) {
            SettingsUtil.setSpeechPlanProtocol(String.valueOf(z));
            PrivacyHelper.getInstance().setSpeechPlanSign();
        }
        SpeechHelper.getInstance().setSpeechPlan(z);
    }

    public void cancelProtocolFetch() {
        PrivacyHelper.getInstance().cancelFetchTask();
    }

    public void showMapProtocol(boolean z) {
        if (z) {
            PrivacyHelper.getInstance().showMapUser(null);
        } else {
            PrivacyHelper.getInstance().showMapLocation(null);
        }
    }

    public void showSpeechDataProtocol() {
        PrivacyHelper.getInstance().showSpeechData(null);
    }

    public void showSpeechPlanProtocol() {
        PrivacyHelper.getInstance().showSpeechPlanPolicy(null);
    }
}
