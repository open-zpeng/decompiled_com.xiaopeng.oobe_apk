package com.xiaopeng.oobe.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.utils.Preconditions;
import com.xiaopeng.oobe.viewmodel.main.IMainViewModel;
import com.xiaopeng.oobe.viewmodel.main.MainViewModel;
/* loaded from: classes.dex */
public class IBaseViewControl implements IViewControl, LifecycleOwner {
    protected final String TAG = getClass().getSimpleName();
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    protected MainViewModel mMainViewModel;
    protected IView mRootView;

    public void observeData() {
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public IBaseViewControl(IView iView) {
        Preconditions.checkNotNull(iView, "cannot be null", IView.class.getName());
        this.mRootView = iView;
    }

    @Override // com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        this.mMainViewModel = (MainViewModel) ViewModelManager.getInstance().getViewModelImpl(IMainViewModel.class);
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.oobe.viewmodel.-$$Lambda$IBaseViewControl$k7Zp7qzGvsGYCsvo7vNn1xNeB0g
            @Override // java.lang.Runnable
            public final void run() {
                IBaseViewControl.this.lambda$onStart$0$IBaseViewControl();
            }
        });
    }

    public /* synthetic */ void lambda$onStart$0$IBaseViewControl() {
        LogUtils.i(this.TAG, "onStart");
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        observeData();
    }

    @Override // com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStop() {
        MainViewModel mainViewModel = this.mMainViewModel;
        if (mainViewModel != null) {
            mainViewModel.clearNetConnect();
        }
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.oobe.viewmodel.-$$Lambda$IBaseViewControl$lFTwHwgPEDehXywGr_eDIfHpn5s
            @Override // java.lang.Runnable
            public final void run() {
                IBaseViewControl.this.lambda$onStop$1$IBaseViewControl();
            }
        });
    }

    public /* synthetic */ void lambda$onStop$1$IBaseViewControl() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IViewControl
    public void onDestroy() {
        this.mRootView = null;
        MainViewModel mainViewModel = this.mMainViewModel;
        if (mainViewModel != null) {
            mainViewModel.clearNetConnect();
        }
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.xiaopeng.oobe.viewmodel.-$$Lambda$IBaseViewControl$7SEw0gh9-oeocVeVXH01eQqgtLo
            @Override // java.lang.Runnable
            public final void run() {
                IBaseViewControl.this.lambda$onDestroy$2$IBaseViewControl();
            }
        });
    }

    public /* synthetic */ void lambda$onDestroy$2$IBaseViewControl() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IViewControl
    public boolean isDestroy() {
        return this.mRootView == null;
    }
}
