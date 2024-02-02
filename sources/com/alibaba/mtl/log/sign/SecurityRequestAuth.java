package com.alibaba.mtl.log.sign;

import com.alibaba.mtl.log.d.i;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
/* loaded from: classes.dex */
public class SecurityRequestAuth implements IRequestAuth {
    private String ad;
    private String g;
    private Object b = null;
    private Object c = null;
    private Class a = null;

    /* renamed from: a  reason: collision with other field name */
    private Field f68a = null;

    /* renamed from: b  reason: collision with other field name */
    private Field f70b = null;

    /* renamed from: c  reason: collision with other field name */
    private Field f71c = null;

    /* renamed from: a  reason: collision with other field name */
    private Method f69a = null;
    private int z = 1;
    private boolean F = false;

    @Override // com.alibaba.mtl.log.sign.IRequestAuth
    public String getAppkey() {
        return this.g;
    }

    public SecurityRequestAuth(String str, String str2) {
        this.g = null;
        this.g = str;
        this.ad = str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004a A[Catch: Throwable -> 0x00c9, TRY_ENTER, TRY_LEAVE, TryCatch #3 {, blocks: (B:3:0x0001, B:39:0x00d1, B:15:0x0041, B:17:0x004a, B:23:0x0084, B:35:0x00ad, B:28:0x009b, B:21:0x007a), top: B:49:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized void D() {
        /*
            Method dump skipped, instructions count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.sign.SecurityRequestAuth.D():void");
    }

    @Override // com.alibaba.mtl.log.sign.IRequestAuth
    public String getSign(String str) {
        Class cls;
        if (!this.F) {
            D();
        }
        if (this.g == null) {
            i.a("SecurityRequestAuth", "There is no appkey,please check it!");
            return null;
        } else if (str == null) {
            return null;
        } else {
            if (this.b != null && (cls = this.a) != null && this.f68a != null && this.f70b != null && this.f71c != null && this.f69a != null && this.c != null) {
                try {
                    Object newInstance = cls.newInstance();
                    this.f68a.set(newInstance, this.g);
                    ((Map) this.f70b.get(newInstance)).put("INPUT", str);
                    this.f71c.set(newInstance, Integer.valueOf(this.z));
                    return (String) this.f69a.invoke(this.c, newInstance, this.ad);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
