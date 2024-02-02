package com.xiaopeng.oobe.carmanager;

import android.car.Car;
import android.car.hardware.CarEcuManager;
import android.car.hardware.CarPropertyValue;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.carmanager.IBaseCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public abstract class BaseCarController<C extends CarEcuManager, T extends IBaseCallback> implements IBaseCarController<T> {
    protected C mCarManager;
    protected final Object mCallbackLock = new Object();
    protected final CopyOnWriteArrayList<T> mCallbacks = new CopyOnWriteArrayList<>();
    protected final ConcurrentHashMap<Integer, CarPropertyValue<?>> mCarPropertyMap = new ConcurrentHashMap<>();
    protected final List<Integer> mPropertyIds = getRegisterPropertyIds();

    /* loaded from: classes.dex */
    public interface ThrowingConsumer<T, E extends Exception> {
        void accept(T t) throws Exception;
    }

    /* loaded from: classes.dex */
    public interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws Exception;
    }

    protected void buildPropIdList(List<Integer> list, String str) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void disconnect();

    protected CarEcuManager.CarEcuEventCallback getCarEventCallback() {
        return null;
    }

    protected abstract List<Integer> getRegisterPropertyIds();

    protected abstract void handleEventsUpdate(CarPropertyValue<?> carPropertyValue);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void initCarManager(Car car);

    protected abstract void initXuiManager();

    @Override // com.xiaopeng.oobe.carmanager.IBaseCarController
    public final void registerCallback(T t) {
        if (t != null) {
            this.mCallbacks.add(t);
        }
    }

    @Override // com.xiaopeng.oobe.carmanager.IBaseCarController
    public final void unregisterCallback(T t) {
        this.mCallbacks.remove(t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleCarEventsUpdate(@NonNull CarPropertyValue<?> carPropertyValue) {
        this.mCarPropertyMap.put(Integer.valueOf(carPropertyValue.getPropertyId()), carPropertyValue);
        handleEventsUpdate(carPropertyValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getIntProperty(int i) throws Exception {
        return ((Integer) getValue(getCarProperty(i))).intValue();
    }

    protected final int[] getIntArrayProperty(int i) throws Exception {
        return getIntArrayProperty(getCarProperty(i));
    }

    protected final int[] getIntArrayProperty(CarPropertyValue<?> carPropertyValue) {
        Object[] objArr = (Object[]) getValue(carPropertyValue);
        if (objArr != null) {
            int[] iArr = new int[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                Object obj = objArr[i];
                if (obj instanceof Integer) {
                    iArr[i] = ((Integer) obj).intValue();
                }
            }
            return iArr;
        }
        return null;
    }

    protected final float getFloatProperty(int i) throws Exception {
        return ((Float) getValue(getCarProperty(i))).floatValue();
    }

    protected final float[] getFloatArrayProperty(int i) throws Exception {
        return getFloatArrayProperty(getCarProperty(i));
    }

    protected final float[] getFloatArrayProperty(CarPropertyValue<?> carPropertyValue) {
        Object[] objArr = (Object[]) getValue(carPropertyValue);
        if (objArr != null) {
            float[] fArr = new float[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                Object obj = objArr[i];
                if (obj instanceof Float) {
                    fArr[i] = ((Float) obj).floatValue();
                }
            }
            return fArr;
        }
        return null;
    }

    @NonNull
    private CarPropertyValue<?> getCarProperty(int i) throws Exception {
        CarPropertyValue<?> carPropertyValue = this.mCarPropertyMap.get(Integer.valueOf(i));
        if (carPropertyValue != null) {
            return carPropertyValue;
        }
        throw new Exception("Car property not found");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setValue(@NonNull ThrowingConsumer<Void, Exception> throwingConsumer) {
        try {
            throwingConsumer.accept(null);
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            LogUtils.e(simpleName, "setValue failed: " + th.getMessage(), false);
        }
    }

    protected <R> R getValue(@NonNull ThrowingFunction<Void, R, Exception> throwingFunction, R r) {
        return (R) getValue(null, -1, throwingFunction, r);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <R> R getValue(int i, @NonNull ThrowingFunction<Void, R, Exception> throwingFunction, R r) {
        return (R) getValue(Integer.class, i, throwingFunction, r);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected <R> R getValue(Class<?> cls, int i, @NonNull ThrowingFunction<Void, R, Exception> throwingFunction, R r) {
        Object obj;
        try {
            try {
                if (cls == null && i < 0) {
                    throw new Exception("Car property not found");
                }
                if (Integer.class.equals(cls)) {
                    obj = Integer.valueOf(getIntProperty(i));
                } else if (Integer[].class.equals(cls)) {
                    obj = getIntArrayProperty(i);
                } else if (Float.class.equals(cls)) {
                    obj = Float.valueOf(getFloatProperty(i));
                } else if (!Float[].class.equals(cls)) {
                    return null;
                } else {
                    obj = getFloatArrayProperty(i);
                }
                return obj;
            } catch (Throwable unused) {
                return throwingFunction.apply(null);
            }
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            LogUtils.e(simpleName, "getValue failed: " + th.getMessage(), false);
            return r;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <E> E getValue(CarPropertyValue<?> carPropertyValue) {
        return (E) carPropertyValue.getValue();
    }

    private List<Integer> getPropIdList(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            LogUtils.e(getClass().getSimpleName(), "getPropIdList error with keys=" + Arrays.toString(strArr), false);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                LogUtils.d(getClass().getSimpleName(), "can not buildPropIdList with null key", false);
            } else {
                buildPropIdList(arrayList, str);
            }
        }
        return arrayList;
    }

    @Override // com.xiaopeng.oobe.carmanager.IBaseCarController
    public void registerBusiness(final String... strArr) {
        ThreadUtils.execute(new Runnable() { // from class: com.xiaopeng.oobe.carmanager.-$$Lambda$BaseCarController$XCSg5cWQsUIxeOpFIocZSElRs-U
            @Override // java.lang.Runnable
            public final void run() {
                BaseCarController.this.lambda$registerBusiness$0$BaseCarController(strArr);
            }
        });
    }

    public /* synthetic */ void lambda$registerBusiness$0$BaseCarController(String[] strArr) {
        C c = this.mCarManager;
        if (c != null) {
            try {
                c.registerPropCallback(getPropIdList(strArr), getCarEventCallback());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.xiaopeng.oobe.carmanager.IBaseCarController
    public void unregisterBusiness(final String... strArr) {
        ThreadUtils.execute(new Runnable() { // from class: com.xiaopeng.oobe.carmanager.-$$Lambda$BaseCarController$w8VerXB-Xjg1ElzDS7l7elX52sI
            @Override // java.lang.Runnable
            public final void run() {
                BaseCarController.this.lambda$unregisterBusiness$1$BaseCarController(strArr);
            }
        });
    }

    public /* synthetic */ void lambda$unregisterBusiness$1$BaseCarController(String[] strArr) {
        C c = this.mCarManager;
        if (c != null) {
            try {
                c.unregisterPropCallback(getPropIdList(strArr), getCarEventCallback());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
