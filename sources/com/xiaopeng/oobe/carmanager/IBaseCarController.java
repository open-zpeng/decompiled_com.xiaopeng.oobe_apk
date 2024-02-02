package com.xiaopeng.oobe.carmanager;

import com.xiaopeng.oobe.carmanager.IBaseCallback;
/* loaded from: classes.dex */
public interface IBaseCarController<T extends IBaseCallback> {
    void registerBusiness(String... strArr);

    void registerCallback(T t);

    void unregisterBusiness(String... strArr);

    void unregisterCallback(T t);
}
