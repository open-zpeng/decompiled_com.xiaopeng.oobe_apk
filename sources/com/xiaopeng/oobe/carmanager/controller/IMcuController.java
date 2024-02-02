package com.xiaopeng.oobe.carmanager.controller;

import com.xiaopeng.oobe.carmanager.IBaseCallback;
import com.xiaopeng.oobe.carmanager.IBaseCarController;
/* loaded from: classes.dex */
public interface IMcuController extends IBaseCarController<Callback> {
    public static final int MCU_IG_STATUS_IG_OFF = 0;
    public static final int MCU_IG_STATUS_LOCAL_IG_ON = 1;
    public static final int MCU_IG_STATUS_REMOTE_IG_ON = 2;

    /* loaded from: classes.dex */
    public interface Callback extends IBaseCallback {
        default void onIgStatusChanged(int i) {
        }
    }

    int getIgStatusFromMcu();
}
