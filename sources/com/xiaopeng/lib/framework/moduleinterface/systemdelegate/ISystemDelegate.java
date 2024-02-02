package com.xiaopeng.lib.framework.moduleinterface.systemdelegate;

import android.os.RemoteException;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public interface ISystemDelegate {
    @Nullable
    String getCertificate() throws RemoteException;

    void setSystemProperty(String str, String str2) throws RemoteException;
}
