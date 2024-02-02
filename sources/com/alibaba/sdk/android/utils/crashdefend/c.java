package com.alibaba.sdk.android.utils.crashdefend;

import android.util.Log;
/* compiled from: CrashDefendSDKInfo.java */
/* loaded from: classes.dex */
public class c implements Cloneable {
    public long a;
    public int b;

    /* renamed from: b  reason: collision with other field name */
    public long f172b;
    public int c;

    /* renamed from: c  reason: collision with other field name */
    public String f173c;
    public int crashCount;

    /* renamed from: d  reason: collision with other field name */
    public String f174d;
    public int d = 0;

    /* renamed from: d  reason: collision with other field name */
    public volatile boolean f175d = false;

    /* renamed from: a  reason: collision with other field name */
    public SDKMessageCallback f171a = null;

    public Object clone() {
        try {
            return (c) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e("CrashSDK", "clone fail:", e);
            return null;
        }
    }
}
