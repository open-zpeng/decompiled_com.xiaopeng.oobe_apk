package com.xiaopeng.oobe.module.voiceservice;

import com.xiaopeng.oobe.module.IWebpVisibility;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.oobe.viewmodel.IViewControl;
/* loaded from: classes.dex */
public class VoiceServiceContract {

    /* loaded from: classes.dex */
    public interface View extends IView, IWebpVisibility {
    }

    /* loaded from: classes.dex */
    public interface ViewControl extends IViewControl {
        void openMicrophoneMuteAndUseVoiceService(boolean z);

        void showSuccess();
    }
}
