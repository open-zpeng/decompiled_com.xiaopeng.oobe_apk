package com.xiaopeng.oobe.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: classes.dex */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private IOnConnectionCheckedListener mListener;

    public NetworkChangeReceiver(IOnConnectionCheckedListener iOnConnectionCheckedListener) {
        this.mListener = iOnConnectionCheckedListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        IOnConnectionCheckedListener iOnConnectionCheckedListener = this.mListener;
        if (iOnConnectionCheckedListener == null || activeNetworkInfo == null) {
            return;
        }
        iOnConnectionCheckedListener.onNetConnectionChecked(activeNetworkInfo.isAvailable());
    }
}
