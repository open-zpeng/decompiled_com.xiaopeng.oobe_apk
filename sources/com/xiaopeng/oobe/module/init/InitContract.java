package com.xiaopeng.oobe.module.init;

import com.xiaopeng.oobe.module.IQuit;
import com.xiaopeng.oobe.viewmodel.IView;
/* loaded from: classes.dex */
public class InitContract {

    /* loaded from: classes.dex */
    public interface IInitView extends View, IView, IQuit {
    }

    /* loaded from: classes.dex */
    public interface View {
        void cancelLoading();

        void setLoading();
    }
}
