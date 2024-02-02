package com.xiaopeng.privacyservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.privacyservice.IFetchProtocolCallback;
import com.xiaopeng.privacyservice.IPrivacyDialogCallback;
/* loaded from: classes.dex */
public interface IPrivacyService extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IPrivacyService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyService
        public void cancelFetchTask(int i) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyService
        public void cancelPrivacyDialog(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyService
        public void dismissPrivacyDialog(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyService
        public void fetchProtocol(int i, IFetchProtocolCallback iFetchProtocolCallback) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyService
        public String getPrivacyContent(int i) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyService
        public String getPrivacyPath(int i) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyService
        public boolean haveDialogOnScreen() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyService
        public String showPrivacyDialog(int i, int i2, IPrivacyDialogCallback iPrivacyDialogCallback) throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyService
        public void signProtocol(int i) throws RemoteException {
        }
    }

    void cancelFetchTask(int i) throws RemoteException;

    void cancelPrivacyDialog(String str) throws RemoteException;

    void dismissPrivacyDialog(String str) throws RemoteException;

    void fetchProtocol(int i, IFetchProtocolCallback iFetchProtocolCallback) throws RemoteException;

    String getPrivacyContent(int i) throws RemoteException;

    String getPrivacyPath(int i) throws RemoteException;

    boolean haveDialogOnScreen() throws RemoteException;

    String showPrivacyDialog(int i, int i2, IPrivacyDialogCallback iPrivacyDialogCallback) throws RemoteException;

    void signProtocol(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPrivacyService {
        private static final String DESCRIPTOR = "com.xiaopeng.privacyservice.IPrivacyService";
        static final int TRANSACTION_cancelFetchTask = 9;
        static final int TRANSACTION_cancelPrivacyDialog = 5;
        static final int TRANSACTION_dismissPrivacyDialog = 4;
        static final int TRANSACTION_fetchProtocol = 8;
        static final int TRANSACTION_getPrivacyContent = 3;
        static final int TRANSACTION_getPrivacyPath = 2;
        static final int TRANSACTION_haveDialogOnScreen = 6;
        static final int TRANSACTION_showPrivacyDialog = 1;
        static final int TRANSACTION_signProtocol = 7;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPrivacyService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPrivacyService)) {
                return (IPrivacyService) queryLocalInterface;
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
                    String showPrivacyDialog = showPrivacyDialog(parcel.readInt(), parcel.readInt(), IPrivacyDialogCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeString(showPrivacyDialog);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String privacyPath = getPrivacyPath(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(privacyPath);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String privacyContent = getPrivacyContent(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(privacyContent);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissPrivacyDialog(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelPrivacyDialog(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean haveDialogOnScreen = haveDialogOnScreen();
                    parcel2.writeNoException();
                    parcel2.writeInt(haveDialogOnScreen ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    signProtocol(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    fetchProtocol(parcel.readInt(), IFetchProtocolCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelFetchTask(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPrivacyService {
            public static IPrivacyService sDefaultImpl;
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

            @Override // com.xiaopeng.privacyservice.IPrivacyService
            public String showPrivacyDialog(int i, int i2, IPrivacyDialogCallback iPrivacyDialogCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iPrivacyDialogCallback != null ? iPrivacyDialogCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showPrivacyDialog(i, i2, iPrivacyDialogCallback);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyService
            public String getPrivacyPath(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPrivacyPath(i);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyService
            public String getPrivacyContent(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPrivacyContent(i);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyService
            public void dismissPrivacyDialog(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissPrivacyDialog(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyService
            public void cancelPrivacyDialog(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelPrivacyDialog(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyService
            public boolean haveDialogOnScreen() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().haveDialogOnScreen();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyService
            public void signProtocol(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().signProtocol(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyService
            public void fetchProtocol(int i, IFetchProtocolCallback iFetchProtocolCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iFetchProtocolCallback != null ? iFetchProtocolCallback.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().fetchProtocol(i, iFetchProtocolCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyService
            public void cancelFetchTask(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelFetchTask(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrivacyService iPrivacyService) {
            if (Proxy.sDefaultImpl != null || iPrivacyService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPrivacyService;
            return true;
        }

        public static IPrivacyService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
