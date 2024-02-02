package com.xiaopeng.privacyservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IPrivacyUICallback extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IPrivacyUICallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
        public void onNegativeButtonClick(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
        public void onPageAccidentClose(String str, int i) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
        public void onPageClose(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
        public void onPageLoadFailed(String str, int i, String str2) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
        public void onPageLoadFinished(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
        public void onPageLoadStart(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
        public void onPositiveButtonClick(String str) throws RemoteException {
        }
    }

    void onNegativeButtonClick(String str) throws RemoteException;

    void onPageAccidentClose(String str, int i) throws RemoteException;

    void onPageClose(String str) throws RemoteException;

    void onPageLoadFailed(String str, int i, String str2) throws RemoteException;

    void onPageLoadFinished(String str) throws RemoteException;

    void onPageLoadStart(String str) throws RemoteException;

    void onPositiveButtonClick(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPrivacyUICallback {
        private static final String DESCRIPTOR = "com.xiaopeng.privacyservice.IPrivacyUICallback";
        static final int TRANSACTION_onNegativeButtonClick = 7;
        static final int TRANSACTION_onPageAccidentClose = 5;
        static final int TRANSACTION_onPageClose = 4;
        static final int TRANSACTION_onPageLoadFailed = 3;
        static final int TRANSACTION_onPageLoadFinished = 2;
        static final int TRANSACTION_onPageLoadStart = 1;
        static final int TRANSACTION_onPositiveButtonClick = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPrivacyUICallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPrivacyUICallback)) {
                return (IPrivacyUICallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPageLoadStart(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPageLoadFinished(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPageLoadFailed(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPageClose(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPageAccidentClose(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPositiveButtonClick(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNegativeButtonClick(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPrivacyUICallback {
            public static IPrivacyUICallback sDefaultImpl;
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

            @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
            public void onPageLoadStart(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPageLoadStart(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
            public void onPageLoadFinished(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPageLoadFinished(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
            public void onPageLoadFailed(String str, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPageLoadFailed(str, i, str2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
            public void onPageClose(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPageClose(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
            public void onPageAccidentClose(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPageAccidentClose(str, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
            public void onPositiveButtonClick(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPositiveButtonClick(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUICallback
            public void onNegativeButtonClick(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNegativeButtonClick(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrivacyUICallback iPrivacyUICallback) {
            if (Proxy.sDefaultImpl != null || iPrivacyUICallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPrivacyUICallback;
            return true;
        }

        public static IPrivacyUICallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
