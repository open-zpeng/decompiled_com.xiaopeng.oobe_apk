package com.xiaopeng.oobe.carmanager.impl;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.hardware.CarEcuManager;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.scu.CarScuManager;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.carmanager.BaseCarController;
import com.xiaopeng.oobe.carmanager.CarClientWrapper;
import com.xiaopeng.oobe.carmanager.controller.IScuController;
import com.xiaopeng.oobe.utils.OOBEHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class ScuController extends BaseCarController<CarScuManager, IScuController.Callback> implements IScuController {
    protected static final String TAG = "ScuController";
    private final CarScuManager.CarScuEventCallback mCarScuEventCallback = new CarScuManager.CarScuEventCallback() { // from class: com.xiaopeng.oobe.carmanager.impl.ScuController.1
        public void onErrorEvent(int i, int i2) {
        }

        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            String str = ScuController.TAG;
            LogUtils.i(str, "onChangeEvent: " + carPropertyValue, false);
            ScuController.this.handleCarEventsUpdate(carPropertyValue);
        }
    };

    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    protected void initXuiManager() {
    }

    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    protected CarEcuManager.CarEcuEventCallback getCarEventCallback() {
        return this.mCarScuEventCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    public void initCarManager(Car car) {
        LogUtils.i(TAG, "Init start");
        try {
            this.mCarManager = (CarScuManager) car.getCarManager(CarClientWrapper.XP_SCU_SERVICE);
            if (this.mCarManager != 0) {
                this.mCarManager.registerPropCallback(this.mPropertyIds, this.mCarScuEventCallback);
            }
        } catch (CarNotConnectedException e) {
            LogUtils.e(TAG, (String) null, e, false);
        }
        LogUtils.i(TAG, "Init end");
    }

    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    protected List<Integer> getRegisterPropertyIds() {
        ArrayList arrayList = new ArrayList();
        addExtPropertyIds(arrayList);
        return arrayList;
    }

    protected void addExtPropertyIds(List<Integer> list) {
        if (OOBEHelper.isSupportDsmCamera()) {
            if (OOBEHelper.isSupportDsmTiredDistraction()) {
                list.add(557852414);
            } else {
                list.add(557852395);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    public void disconnect() {
        if (this.mCarManager != 0) {
            try {
                this.mCarManager.unregisterPropCallback(this.mPropertyIds, this.mCarScuEventCallback);
            } catch (CarNotConnectedException e) {
                LogUtils.e(TAG, (String) null, e, false);
            }
        }
    }

    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    protected void handleEventsUpdate(CarPropertyValue<?> carPropertyValue) {
        String str = TAG;
        LogUtils.e(str, "handleEventsUpdate: " + carPropertyValue.getPropertyId() + ",value:" + carPropertyValue.getValue(), false);
        int propertyId = carPropertyValue.getPropertyId();
        if (propertyId == 557852395 || propertyId == 557852414) {
            handleDsmSwStatusChanged(((Integer) getValue(carPropertyValue)).intValue());
            return;
        }
        String str2 = TAG;
        LogUtils.w(str2, "handle unknown event: " + carPropertyValue);
    }

    private void handleDsmSwStatusChanged(int i) {
        String str = TAG;
        LogUtils.e(str, "handleDsmSwStatusChanged: " + i + ",mCallbacks:" + this.mCallbacks, false);
        synchronized (this.mCallbackLock) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((IScuController.Callback) it.next()).onDsmSwStateChanged(i);
            }
        }
    }

    @Override // com.xiaopeng.oobe.carmanager.controller.IScuController
    public void setDsmStatus(final boolean z) {
        String str = TAG;
        LogUtils.i(str, "setDsmStatus: " + z, false);
        if (OOBEHelper.isSupportDsmCamera()) {
            if (OOBEHelper.isSupportDsmTiredDistraction()) {
                setValue(new BaseCarController.ThrowingConsumer() { // from class: com.xiaopeng.oobe.carmanager.impl.-$$Lambda$ScuController$4t9iQphI1qWmq0bgipgavsWbgOg
                    @Override // com.xiaopeng.oobe.carmanager.BaseCarController.ThrowingConsumer
                    public final void accept(Object obj) {
                        ScuController.this.lambda$setDsmStatus$0$ScuController(z, (Void) obj);
                    }
                });
            } else {
                setValue(new BaseCarController.ThrowingConsumer() { // from class: com.xiaopeng.oobe.carmanager.impl.-$$Lambda$ScuController$hL9EQ4jtalsos-EAfErpX7G498k
                    @Override // com.xiaopeng.oobe.carmanager.BaseCarController.ThrowingConsumer
                    public final void accept(Object obj) {
                        ScuController.this.lambda$setDsmStatus$1$ScuController(z, (Void) obj);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$setDsmStatus$0$ScuController(boolean z, Void r2) throws Exception {
        this.mCarManager.setDsmCameraStatus(z ? 1 : 0);
    }

    public /* synthetic */ void lambda$setDsmStatus$1$ScuController(boolean z, Void r2) throws Exception {
        this.mCarManager.setDsmStatus(z ? 1 : 0);
    }

    @Override // com.xiaopeng.oobe.carmanager.controller.IScuController
    public int getDsmSwStatus() {
        int i;
        if (!OOBEHelper.isSupportDsmCamera()) {
            i = 0;
        } else if (OOBEHelper.isSupportDsmTiredDistraction()) {
            i = ((Integer) getValue(557852414, new BaseCarController.ThrowingFunction() { // from class: com.xiaopeng.oobe.carmanager.impl.-$$Lambda$ScuController$uXI66vNMQEHwbNdFM6lwhVfXDp8
                @Override // com.xiaopeng.oobe.carmanager.BaseCarController.ThrowingFunction
                public final Object apply(Object obj) {
                    return ScuController.this.lambda$getDsmSwStatus$2$ScuController((Void) obj);
                }
            }, 0)).intValue();
        } else {
            i = ((Integer) getValue(557852395, new BaseCarController.ThrowingFunction() { // from class: com.xiaopeng.oobe.carmanager.impl.-$$Lambda$ScuController$qGZ11eScmWW9BlGj35NjMjZXn0k
                @Override // com.xiaopeng.oobe.carmanager.BaseCarController.ThrowingFunction
                public final Object apply(Object obj) {
                    return ScuController.this.lambda$getDsmSwStatus$3$ScuController((Void) obj);
                }
            }, 0)).intValue();
        }
        LogUtils.i(TAG, "getDsmSwStatus: " + i, false);
        return i;
    }

    public /* synthetic */ Integer lambda$getDsmSwStatus$2$ScuController(Void r1) throws Exception {
        return Integer.valueOf(this.mCarManager.getDsmCameraStatus());
    }

    public /* synthetic */ Integer lambda$getDsmSwStatus$3$ScuController(Void r1) throws Exception {
        return Integer.valueOf(this.mCarManager.getDsmStatus());
    }
}
