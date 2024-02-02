package com.alibaba.sdk.android.man.crashreporter.a.c.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class a {
    public Map<String, Object> c = new HashMap();

    public byte[] a() {
        Map<String, Object> map = this.c;
        if (map != null && map.size() > 0) {
            try {
                byte[] a = a(this.c);
                if (a != null) {
                    return a;
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private byte[] a(Map map) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            return null;
        }
    }
}
