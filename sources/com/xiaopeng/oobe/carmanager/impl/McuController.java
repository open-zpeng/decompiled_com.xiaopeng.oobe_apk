package com.xiaopeng.oobe.carmanager.impl;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.mcu.CarMcuManager;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.carmanager.BaseCarController;
import com.xiaopeng.oobe.carmanager.CarClientWrapper;
import com.xiaopeng.oobe.carmanager.controller.IMcuController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class McuController extends BaseCarController<CarMcuManager, IMcuController.Callback> implements IMcuController {
    protected static final String TAG = "McuController";
    private final CarMcuManager.CarMcuEventCallback mCarMcuEventCallback = new CarMcuManager.CarMcuEventCallback() { // from class: com.xiaopeng.oobe.carmanager.impl.McuController.1
        public void onErrorEvent(int i, int i2) {
        }

        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            String str = McuController.TAG;
            LogUtils.i(str, "onChangeEvent: " + carPropertyValue, false);
            McuController.this.handleCarEventsUpdate(carPropertyValue);
        }
    };

    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    protected void initXuiManager() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    public void initCarManager(Car car) {
        LogUtils.d(TAG, "Init start");
        try {
            this.mCarManager = (CarMcuManager) car.getCarManager(CarClientWrapper.XP_MCU_SERVICE);
            if (this.mCarManager != 0) {
                this.mCarManager.registerPropCallback(this.mPropertyIds, this.mCarMcuEventCallback);
            }
        } catch (CarNotConnectedException e) {
            LogUtils.e(TAG, (String) null, e, false);
        }
        LogUtils.d(TAG, "Init end");
    }

    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    protected List<Integer> getRegisterPropertyIds() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(557847561);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    public void disconnect() {
        if (this.mCarManager != 0) {
            try {
                this.mCarManager.unregisterPropCallback(this.mPropertyIds, this.mCarMcuEventCallback);
            } catch (CarNotConnectedException e) {
                LogUtils.e(TAG, (String) null, e, false);
            }
        }
    }

    @Override // com.xiaopeng.oobe.carmanager.BaseCarController
    protected void handleEventsUpdate(CarPropertyValue<?> carPropertyValue) {
        String str = TAG;
        LogUtils.e(str, "handleEventsUpdate: " + carPropertyValue.getPropertyId() + ",value:" + carPropertyValue.getValue(), false);
        if (carPropertyValue.getPropertyId() == 557847561) {
            handleIgStatusChanged(((Integer) getValue(carPropertyValue)).intValue());
            return;
        }
        String str2 = TAG;
        LogUtils.w(str2, "handle unknown event: " + carPropertyValue);
    }

    @Override // com.xiaopeng.oobe.carmanager.controller.IMcuController
    public int getIgStatusFromMcu() {
        try {
            try {
                return getIntProperty(557847561);
            } catch (Exception unused) {
                return this.mCarManager.getIgStatusFromMcu();
            }
        } catch (Exception e) {
            String str = TAG;
            LogUtils.e(str, "getIgStatusFromMcu: " + e.getMessage(), false);
            return 0;
        }
    }

    private void handleIgStatusChanged(int i) {
        String str = TAG;
        LogUtils.e(str, "handleIgStatusChanged: " + i + ",mCallbacks:" + this.mCallbacks, false);
        synchronized (this.mCallbackLock) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((IMcuController.Callback) it.next()).onIgStatusChanged(i);
            }
        }
    }
}
