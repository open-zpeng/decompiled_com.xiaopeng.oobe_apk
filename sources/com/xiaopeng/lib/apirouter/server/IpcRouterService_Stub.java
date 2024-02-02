package com.xiaopeng.lib.apirouter.server;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.oobe.IpcRouterService;
/* loaded from: classes.dex */
public class IpcRouterService_Stub extends Binder implements IInterface {
    public IpcRouterService provider = new IpcRouterService();
    public IpcRouterService_Manifest manifest = new IpcRouterService_Manifest();

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 0) {
            if (i == 1598968902) {
                parcel2.writeString(IpcRouterService_Manifest.DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel.enforceInterface(IpcRouterService_Manifest.DESCRIPTOR);
        Uri uri = (Uri) Uri.CREATOR.createFromParcel(parcel);
        try {
            this.provider.onReceiverData(((Integer) TransactTranslator.read(uri.getQueryParameter("id"), "int")).intValue(), (String) TransactTranslator.read(uri.getQueryParameter("bundle"), "java.lang.String"));
            parcel2.writeNoException();
            TransactTranslator.reply(parcel2, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            parcel2.writeException(new IllegalStateException(e.getMessage()));
            return true;
        }
    }
}
