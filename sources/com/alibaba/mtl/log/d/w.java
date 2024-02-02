package com.alibaba.mtl.log.d;

import android.text.TextUtils;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
/* compiled from: UtHostnameVerifier.java */
/* loaded from: classes.dex */
class w implements HostnameVerifier {
    public String ak;

    public w(String str) {
        this.ak = str;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(this.ak, sSLSession);
    }

    public boolean equals(Object obj) {
        if (TextUtils.isEmpty(this.ak) || !(obj instanceof w)) {
            return false;
        }
        String str = ((w) obj).ak;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.ak.equals(str);
    }

    public String getHost() {
        return this.ak;
    }
}
