package com.xiaopeng.oobe.carmanager.controller;

import com.xiaopeng.oobe.carmanager.IBaseCallback;
import com.xiaopeng.oobe.carmanager.IBaseCarController;
/* loaded from: classes.dex */
public interface IScuController extends IBaseCarController<Callback> {
    public static final int DSM_ST_OFF = 0;
    public static final int DSM_SWITCH_OFF = 0;
    public static final int DSM_SWITCH_ON = 1;

    /* loaded from: classes.dex */
    public interface Callback extends IBaseCallback {
        void onDsmSwStateChanged(int i);
    }

    int getDsmSwStatus();

    void setDsmStatus(boolean z);
}
