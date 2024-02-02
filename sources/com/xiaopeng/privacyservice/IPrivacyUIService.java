package com.xiaopeng.privacyservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.privacyservice.IPrivacyUICallback;
/* loaded from: classes.dex */
public interface IPrivacyUIService extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IPrivacyUIService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUIService
        public void dismissAllDisplay() throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUIService
        public void dismissDisplay(String str) throws RemoteException {
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUIService
        public boolean isAnyProtocolInShowing() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUIService
        public boolean isPrivacyOnShowing(String str) throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.privacyservice.IPrivacyUIService
        public void showSystemDialog(ProtocolInfo protocolInfo, IPrivacyUICallback iPrivacyUICallback) throws RemoteException {
        }
    }

    void dismissAllDisplay() throws RemoteException;

    void dismissDisplay(String str) throws RemoteException;

    boolean isAnyProtocolInShowing() throws RemoteException;

    boolean isPrivacyOnShowing(String str) throws RemoteException;

    void showSystemDialog(ProtocolInfo protocolInfo, IPrivacyUICallback iPrivacyUICallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPrivacyUIService {
        private static final String DESCRIPTOR = "com.xiaopeng.privacyservice.IPrivacyUIService";
        static final int TRANSACTION_dismissAllDisplay = 5;
        static final int TRANSACTION_dismissDisplay = 4;
        static final int TRANSACTION_isAnyProtocolInShowing = 2;
        static final int TRANSACTION_isPrivacyOnShowing = 3;
        static final int TRANSACTION_showSystemDialog = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPrivacyUIService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPrivacyUIService)) {
                return (IPrivacyUIService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                showSystemDialog(parcel.readInt() != 0 ? ProtocolInfo.CREATOR.createFromParcel(parcel) : null, IPrivacyUICallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isAnyProtocolInShowing = isAnyProtocolInShowing();
                parcel2.writeNoException();
                parcel2.writeInt(isAnyProtocolInShowing ? 1 : 0);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isPrivacyOnShowing = isPrivacyOnShowing(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(isPrivacyOnShowing ? 1 : 0);
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                dismissDisplay(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                dismissAllDisplay();
                parcel2.writeNoException();
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPrivacyUIService {
            public static IPrivacyUIService sDefaultImpl;
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

            @Override // com.xiaopeng.privacyservice.IPrivacyUIService
            public void showSystemDialog(ProtocolInfo protocolInfo, IPrivacyUICallback iPrivacyUICallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (protocolInfo != null) {
                        obtain.writeInt(1);
                        protocolInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPrivacyUICallback != null ? iPrivacyUICallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showSystemDialog(protocolInfo, iPrivacyUICallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUIService
            public boolean isAnyProtocolInShowing() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAnyProtocolInShowing();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUIService
            public boolean isPrivacyOnShowing(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPrivacyOnShowing(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUIService
            public void dismissDisplay(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissDisplay(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.privacyservice.IPrivacyUIService
            public void dismissAllDisplay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissAllDisplay();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrivacyUIService iPrivacyUIService) {
            if (Proxy.sDefaultImpl != null || iPrivacyUIService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPrivacyUIService;
            return true;
        }

        public static IPrivacyUIService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
