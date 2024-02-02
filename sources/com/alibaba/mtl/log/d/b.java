package com.alibaba.mtl.log.d;

import com.alibaba.mtl.log.e.a;
import com.alibaba.mtl.log.e.e;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.l;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: UploadTask.java */
/* loaded from: classes.dex */
public abstract class b implements Runnable {
    static int A;
    private static volatile boolean F;
    private static boolean G;
    int B = -1;
    float a = 200.0f;
    int C = 0;

    public abstract void J();

    public abstract void K();

    @Override // java.lang.Runnable
    public void run() {
        try {
            L();
            J();
        } catch (Throwable unused) {
        }
    }

    public static boolean isRunning() {
        return F;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x016a, code lost:
        com.alibaba.mtl.log.e.k.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01ec, code lost:
        com.alibaba.mtl.log.d.b.F = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void L() {
        /*
            Method dump skipped, instructions count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.d.b.L():void");
    }

    private int b(List<com.alibaba.mtl.log.model.a> list) {
        if (list == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = list.get(i2).T;
            if (str != null && "6005".equalsIgnoreCase(str.toString())) {
                i++;
            }
        }
        return i;
    }

    private int h() {
        if (this.B == -1) {
            String w = l.w();
            if ("wifi".equalsIgnoreCase(w)) {
                this.B = 20;
            } else if ("4G".equalsIgnoreCase(w)) {
                this.B = 16;
            } else if ("3G".equalsIgnoreCase(w)) {
                this.B = 12;
            } else {
                this.B = 8;
            }
        }
        return this.B;
    }

    private a.C0008a a(String str, Map<String, Object> map) {
        if (str != null) {
            byte[] bArr = e.a(2, str, map, false).e;
            i.a("UploadTask", "url:", str);
            if (bArr != null) {
                String str2 = null;
                try {
                    str2 = new String(bArr, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (str2 != null) {
                    i.a("UploadTask", "result:", str2);
                    return com.alibaba.mtl.log.e.a.a(str2);
                }
            }
        }
        return a.C0008a.a;
    }

    private int a(Boolean bool, long j) {
        if (j < 0) {
            return this.B;
        }
        float f = this.C / ((float) j);
        if (!bool.booleanValue()) {
            this.B /= 2;
            A++;
        } else if (j > 45000) {
            return this.B;
        } else {
            this.B = (int) (((f * 45000.0f) / this.a) - A);
        }
        int i = this.B;
        if (i < 1) {
            this.B = 1;
            A = 0;
        } else if (i > 350) {
            this.B = 350;
        }
        i.a("UploadTask", "winsize:", Integer.valueOf(this.B));
        return this.B;
    }

    private Map<String, Object> a(List<com.alibaba.mtl.log.model.a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            List<String> a = a(list.get(i));
            if (a != null) {
                for (int i2 = 0; i2 < a.size(); i2++) {
                    StringBuilder sb = (StringBuilder) hashMap.get(a.get(i2));
                    if (sb == null) {
                        sb = new StringBuilder();
                        hashMap.put(a.get(i2), sb);
                    } else {
                        sb.append("\n");
                    }
                    sb.append(list.get(i).k());
                }
            }
        }
        HashMap hashMap2 = new HashMap();
        this.C = 0;
        for (String str : hashMap.keySet()) {
            byte[] a2 = a(((StringBuilder) hashMap.get(str)).toString());
            hashMap2.put(str, a2);
            this.C += a2.length;
        }
        this.a = this.C / list.size();
        i.a("UploadTask", "averagePackageSize:", Float.valueOf(this.a));
        return hashMap2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|2|3|4|(3:6|7|8)|9|11|12|13|14|15|(1:(0))) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private byte[] a(java.lang.String r4) {
        /*
            r3 = this;
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            java.lang.String r1 = "UTF-8"
            byte[] r4 = r4.getBytes(r1)     // Catch: java.lang.Throwable -> L1b java.io.IOException -> L1d
            r2.write(r4)     // Catch: java.lang.Throwable -> L1b java.io.IOException -> L1d
            r2.flush()     // Catch: java.lang.Throwable -> L1b java.io.IOException -> L1d
            r2.close()     // Catch: java.lang.Exception -> L2c
            goto L2c
        L1b:
            r4 = move-exception
            goto L3a
        L1d:
            r4 = move-exception
            r1 = r2
            goto L24
        L20:
            r4 = move-exception
            r2 = r1
            goto L3a
        L23:
            r4 = move-exception
        L24:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L20
            if (r1 == 0) goto L2c
            r1.close()     // Catch: java.lang.Exception -> L2c
        L2c:
            byte[] r4 = r0.toByteArray()
            java.lang.String r1 = "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK"
            byte[] r4 = com.alibaba.mtl.log.e.n.a(r4, r1)
            r0.close()     // Catch: java.lang.Exception -> L39
        L39:
            return r4
        L3a:
            if (r2 == 0) goto L3f
            r2.close()     // Catch: java.lang.Exception -> L3f
        L3f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.d.b.a(java.lang.String):byte[]");
    }

    private List<String> a(com.alibaba.mtl.log.model.a aVar) {
        return com.alibaba.mtl.log.a.a.m21a(aVar.T);
    }
}
