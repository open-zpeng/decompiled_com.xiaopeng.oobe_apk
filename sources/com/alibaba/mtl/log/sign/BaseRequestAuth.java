package com.alibaba.mtl.log.sign;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.j;
/* loaded from: classes.dex */
public class BaseRequestAuth implements IRequestAuth {
    private boolean E;
    private String ac;
    private String g;

    @Override // com.alibaba.mtl.log.sign.IRequestAuth
    public String getAppkey() {
        return this.g;
    }

    public String getAppSecret() {
        return this.ac;
    }

    public BaseRequestAuth(String str, String str2) {
        this.g = null;
        this.ac = null;
        this.E = false;
        this.g = str;
        this.ac = str2;
    }

    public BaseRequestAuth(String str, String str2, boolean z) {
        this.g = null;
        this.ac = null;
        this.E = false;
        this.g = str;
        this.ac = str2;
        this.E = z;
    }

    public boolean isEncode() {
        return this.E;
    }

    @Override // com.alibaba.mtl.log.sign.IRequestAuth
    public String getSign(String str) {
        if (this.g == null || this.ac == null) {
            i.a("BaseRequestAuth", "There is no appkey,please check it!");
            return null;
        } else if (str == null) {
            return null;
        } else {
            return j.a(j.m27a((str + this.ac).getBytes()));
        }
    }
}
