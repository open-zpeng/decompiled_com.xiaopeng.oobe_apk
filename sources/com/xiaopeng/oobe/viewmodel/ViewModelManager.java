package com.xiaopeng.oobe.viewmodel;

import androidx.annotation.NonNull;
import com.xiaopeng.oobe.viewmodel.main.IMainViewModel;
import com.xiaopeng.oobe.viewmodel.main.MainViewModel;
import java.util.Hashtable;
/* loaded from: classes.dex */
public class ViewModelManager {
    private static final String TAG = "ViewModelManager";
    private final Hashtable<Class<?>, IBaseViewModel> mViewModelsCache;

    /* loaded from: classes.dex */
    private static class SingleHolder {
        private static final ViewModelManager sInstance = new ViewModelManager();

        private SingleHolder() {
        }
    }

    public static ViewModelManager getInstance() {
        return SingleHolder.sInstance;
    }

    private ViewModelManager() {
        this.mViewModelsCache = new Hashtable<>();
    }

    @NonNull
    public <T extends IBaseViewModel> T getViewModelImpl(@NonNull Class<?> cls) throws IllegalArgumentException {
        synchronized (ViewModelManager.class) {
            T t = (T) this.mViewModelsCache.get(cls);
            if (cls.isInstance(t)) {
                return t;
            }
            T t2 = (T) createViewModel(cls);
            this.mViewModelsCache.put(cls, t2);
            return t2;
        }
    }

    @NonNull
    private <T extends IBaseViewModel> T createViewModel(@NonNull Class<?> cls) {
        if (cls == IMainViewModel.class) {
            return new MainViewModel();
        }
        throw new IllegalArgumentException("Unknown view model class: " + cls.getSimpleName());
    }
}
