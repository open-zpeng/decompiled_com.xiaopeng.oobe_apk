package com.alibaba.sdk.android.man.crashreporter.e;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Map;
/* loaded from: classes.dex */
public class g {
    public static byte[] c(Map map) {
        if (map != null && map.size() > 0) {
            try {
                byte[] a = a(map);
                if (a != null) {
                    return a;
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static byte[] a(Map map) {
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

    public static Map a(byte[] bArr) {
        try {
            return b(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Map b(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Map map = (Map) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return map;
        } catch (StreamCorruptedException | IOException | ClassNotFoundException unused) {
            return null;
        }
    }
}
