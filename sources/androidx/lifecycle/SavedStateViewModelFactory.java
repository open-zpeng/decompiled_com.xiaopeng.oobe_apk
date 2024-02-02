package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class SavedStateViewModelFactory extends ViewModelProvider.KeyedFactory {
    private static final Class<?>[] ANDROID_VIEWMODEL_SIGNATURE = {Application.class, SavedStateHandle.class};
    private static final Class<?>[] VIEWMODEL_SIGNATURE = {SavedStateHandle.class};
    private final Application mApplication;
    private final Bundle mDefaultArgs;
    private final ViewModelProvider.Factory mFactory;
    private final Lifecycle mLifecycle;
    private final SavedStateRegistry mSavedStateRegistry;

    public SavedStateViewModelFactory(@Nullable Application application, @NonNull SavedStateRegistryOwner owner) {
        this(application, owner, null);
    }

    @SuppressLint({"LambdaLast"})
    public SavedStateViewModelFactory(@Nullable Application application, @NonNull SavedStateRegistryOwner owner, @Nullable Bundle defaultArgs) {
        ViewModelProvider.Factory newInstanceFactory;
        this.mSavedStateRegistry = owner.getSavedStateRegistry();
        this.mLifecycle = owner.getLifecycle();
        this.mDefaultArgs = defaultArgs;
        this.mApplication = application;
        if (application != null) {
            newInstanceFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        } else {
            newInstanceFactory = ViewModelProvider.NewInstanceFactory.getInstance();
        }
        this.mFactory = newInstanceFactory;
    }

    @Override // androidx.lifecycle.ViewModelProvider.KeyedFactory
    @NonNull
    public <T extends ViewModel> T create(@NonNull String key, @NonNull Class<T> modelClass) {
        Constructor findMatchingConstructor;
        T t;
        boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(modelClass);
        if (isAssignableFrom && this.mApplication != null) {
            findMatchingConstructor = findMatchingConstructor(modelClass, ANDROID_VIEWMODEL_SIGNATURE);
        } else {
            findMatchingConstructor = findMatchingConstructor(modelClass, VIEWMODEL_SIGNATURE);
        }
        if (findMatchingConstructor == null) {
            return (T) this.mFactory.create(modelClass);
        }
        SavedStateHandleController create = SavedStateHandleController.create(this.mSavedStateRegistry, this.mLifecycle, key, this.mDefaultArgs);
        if (isAssignableFrom) {
            try {
                if (this.mApplication != null) {
                    t = (T) findMatchingConstructor.newInstance(this.mApplication, create.getHandle());
                    t.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", create);
                    return t;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access " + modelClass, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("A " + modelClass + " cannot be instantiated.", e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("An exception happened in constructor of " + modelClass, e3.getCause());
            }
        }
        t = (T) findMatchingConstructor.newInstance(create.getHandle());
        t.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", create);
        return t;
    }

    @Override // androidx.lifecycle.ViewModelProvider.KeyedFactory, androidx.lifecycle.ViewModelProvider.Factory
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return (T) create(canonicalName, modelClass);
    }

    private static <T> Constructor<T> findMatchingConstructor(Class<T> modelClass, Class<?>[] signature) {
        for (Constructor<?> constructor : modelClass.getConstructors()) {
            Constructor<T> constructor2 = (Constructor<T>) constructor;
            if (Arrays.equals(signature, constructor2.getParameterTypes())) {
                return constructor2;
            }
        }
        return null;
    }

    @Override // androidx.lifecycle.ViewModelProvider.OnRequeryFactory
    void onRequery(@NonNull ViewModel viewModel) {
        SavedStateHandleController.attachHandleIfNeeded(viewModel, this.mSavedStateRegistry, this.mLifecycle);
    }
}
