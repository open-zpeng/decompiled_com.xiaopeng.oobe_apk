package com.xiaopeng.speech.protocol.query.system;

import com.xiaopeng.speech.IQueryCaller;
/* loaded from: classes2.dex */
public interface IControlPanelCaller extends IQueryCaller {
    default int onBlueToothVolumeSet(int i) {
        return 0;
    }

    default int onBlueToothVolumeSetValue(int i) {
        return 0;
    }
}
