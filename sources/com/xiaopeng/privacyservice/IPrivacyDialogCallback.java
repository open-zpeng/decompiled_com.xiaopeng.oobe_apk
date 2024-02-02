package com.xiaopeng.privacyservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IPrivacyDialogCallback extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IPrivacyDialogCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyDialogCallback
        public void onDialogClosed(int i, boolean z, int i2) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyDialogCallback
        public void onDialogShown(int i) throws RemoteException {
        }
    }

    void onDialogClosed(int i, boolean z, int i2) throws RemoteException;

    void onDialogShown(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPrivacyDialogCallback {
        private static final String DESCRIPTOR = "com.xiaopeng.privacyservice.IPrivacyDialogCallback";
        static final int TRANSACTION_onDialogClosed = 1;
        static final int TRANSACTION_onDialogShown = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPrivacyDialogCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPrivacyDialogCallback)) {
                return (IPrivacyDialogCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onDialogClosed(parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                onDialogShown(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPrivacyDialogCallback {
            public static IPrivacyDialogCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyDialogCallback
            public void onDialogClosed(int i, boolean z, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDialogClosed(i, z, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyDialogCallback
            public void onDialogShown(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDialogShown(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrivacyDialogCallback iPrivacyDialogCallback) {
            if (Proxy.sDefaultImpl != null || iPrivacyDialogCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPrivacyDialogCallback;
            return true;
        }

        public static IPrivacyDialogCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
