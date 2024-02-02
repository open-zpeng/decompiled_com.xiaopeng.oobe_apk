package com.xiaopeng.oobe.module.xpilot;

import com.xiaopeng.oobe.viewmodel.IViewControl;
/* loaded from: classes.dex */
public class XpilotUserExperienceContract {

    /* loaded from: classes.dex */
    public interface IXpilotView extends View {
    }

    /* loaded from: classes.dex */
    public interface View {
    }

    /* loaded from: classes.dex */
    public interface ViewControl extends IViewControl {
        void agree();

        void setXpilotPrivacyRead();
    }
}
