package com.alibaba.sdk.android.man.crashreporter.e;

import androidx.core.view.MotionEventCompat;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
/* loaded from: classes.dex */
public class b {
    public static final int H = 0;
    public static final int I = 1;
    public static final int J = 0;
    public static final int K = 2;
    public static final int L = 4;
    public static final int M = 8;
    public static final int N = 32;
    private static final int O = 76;
    public static final int URL_SAFE = 16;
    private static final byte b = 10;
    private static final String w = "US-ASCII";

    /* renamed from: c  reason: collision with other field name */
    static final /* synthetic */ boolean f140c = !b.class.desiredAssertionStatus();

    /* renamed from: a  reason: collision with other field name */
    private static final byte[] f138a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 43, 47};
    private static final byte c = -5;
    private static final byte a = 61;
    private static final byte d = -1;

    /* renamed from: b  reason: collision with other field name */
    private static final byte[] f139b = {-9, -9, -9, -9, -9, -9, -9, -9, -9, c, c, -9, -9, c, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, c, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 58, 59, 60, a, -9, -9, -9, d, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, MqttWireMessage.MESSAGE_TYPE_UNSUBACK, MqttWireMessage.MESSAGE_TYPE_PINGREQ, MqttWireMessage.MESSAGE_TYPE_PINGRESP, MqttWireMessage.MESSAGE_TYPE_DISCONNECT, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: c  reason: collision with other field name */
    private static final byte[] f141c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 45, 95};

    /* renamed from: d  reason: collision with other field name */
    private static final byte[] f142d = {-9, -9, -9, -9, -9, -9, -9, -9, -9, c, c, -9, -9, c, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, c, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 58, 59, 60, a, -9, -9, -9, d, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, MqttWireMessage.MESSAGE_TYPE_UNSUBACK, MqttWireMessage.MESSAGE_TYPE_PINGREQ, MqttWireMessage.MESSAGE_TYPE_PINGRESP, MqttWireMessage.MESSAGE_TYPE_DISCONNECT, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] e = {45, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 95, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122};
    private static final byte[] f = {-9, -9, -9, -9, -9, -9, -9, -9, -9, c, c, -9, -9, c, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, c, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, d, -9, -9, -9, MqttWireMessage.MESSAGE_TYPE_UNSUBACK, MqttWireMessage.MESSAGE_TYPE_PINGREQ, MqttWireMessage.MESSAGE_TYPE_PINGRESP, MqttWireMessage.MESSAGE_TYPE_DISCONNECT, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 58, 59, 60, a, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    private static final byte[] a(int i) {
        if ((i & 16) == 16) {
            return f141c;
        }
        if ((i & 32) == 32) {
            return e;
        }
        return f138a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] b(int i) {
        if ((i & 16) == 16) {
            return f142d;
        }
        if ((i & 32) == 32) {
            return f;
        }
        return f139b;
    }

    private b() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] a(byte[] bArr, byte[] bArr2, int i, int i2) {
        a(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] a2 = a(i4);
        int i5 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 2 ? (bArr[i + 2] << 24) >>> 24 : 0);
        if (i2 == 1) {
            bArr2[i3] = a2[i5 >>> 18];
            bArr2[i3 + 1] = a2[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = a;
            bArr2[i3 + 3] = a;
            return bArr2;
        } else if (i2 == 2) {
            bArr2[i3] = a2[i5 >>> 18];
            bArr2[i3 + 1] = a2[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = a2[(i5 >>> 6) & 63];
            bArr2[i3 + 3] = a;
            return bArr2;
        } else if (i2 != 3) {
            return bArr2;
        } else {
            bArr2[i3] = a2[i5 >>> 18];
            bArr2[i3 + 1] = a2[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = a2[(i5 >>> 6) & 63];
            bArr2[i3 + 3] = a2[i5 & 63];
            return bArr2;
        }
    }

    public static void a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(bArr, 0, min);
            a(bArr2, bArr, min, 0);
            byteBuffer2.put(bArr2);
        }
    }

    public static void a(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(bArr, 0, min);
            a(bArr2, bArr, min, 0);
            for (int i = 0; i < 4; i++) {
                charBuffer.put((char) (bArr2[i] & d));
            }
        }
    }

    public static String a(Serializable serializable) throws IOException {
        return a(serializable, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r6v18, types: [java.io.OutputStream, java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public static String a(Serializable serializable, int i) throws IOException {
        ?? r6;
        ?? r1;
        C0018b c0018b;
        ObjectOutputStream objectOutputStream;
        if (serializable == null) {
            throw new NullPointerException("Cannot serialize a null object.");
        }
        ObjectOutputStream objectOutputStream2 = null;
        try {
            r1 = new ByteArrayOutputStream();
            try {
                c0018b = new C0018b(r1, i | 1);
                try {
                    if ((i & 2) != 0) {
                        r6 = new GZIPOutputStream(c0018b);
                        try {
                            objectOutputStream2 = new ObjectOutputStream(r6);
                            r6 = r6;
                        } catch (IOException e2) {
                            e = e2;
                            objectOutputStream = objectOutputStream2;
                            objectOutputStream2 = r1;
                            r6 = r6;
                            try {
                                throw e;
                            } catch (Throwable th) {
                                th = th;
                                ObjectOutputStream objectOutputStream3 = objectOutputStream;
                                r1 = objectOutputStream2;
                                objectOutputStream2 = objectOutputStream3;
                                try {
                                    objectOutputStream2.close();
                                } catch (Exception unused) {
                                }
                                try {
                                    r6.close();
                                } catch (Exception unused2) {
                                }
                                try {
                                    c0018b.close();
                                } catch (Exception unused3) {
                                }
                                try {
                                    r1.close();
                                } catch (Exception unused4) {
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            objectOutputStream2.close();
                            r6.close();
                            c0018b.close();
                            r1.close();
                            throw th;
                        }
                    } else {
                        objectOutputStream2 = new ObjectOutputStream(c0018b);
                        r6 = 0;
                    }
                    objectOutputStream2.writeObject(serializable);
                    try {
                        objectOutputStream2.close();
                    } catch (Exception unused5) {
                    }
                    try {
                        r6.close();
                    } catch (Exception unused6) {
                    }
                    try {
                        c0018b.close();
                    } catch (Exception unused7) {
                    }
                    try {
                        r1.close();
                    } catch (Exception unused8) {
                    }
                    try {
                        return new String(r1.toByteArray(), "US-ASCII");
                    } catch (UnsupportedEncodingException unused9) {
                        return new String(r1.toByteArray());
                    }
                } catch (IOException e3) {
                    e = e3;
                    ObjectOutputStream objectOutputStream4 = objectOutputStream2;
                    objectOutputStream2 = r1;
                    objectOutputStream = objectOutputStream4;
                    r6 = objectOutputStream4;
                } catch (Throwable th3) {
                    th = th3;
                    r6 = objectOutputStream2;
                }
            } catch (IOException e4) {
                e = e4;
                r6 = 0;
                c0018b = null;
                objectOutputStream2 = r1;
                objectOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                r6 = 0;
                c0018b = null;
            }
        } catch (IOException e5) {
            e = e5;
            r6 = 0;
            objectOutputStream = null;
            c0018b = null;
        } catch (Throwable th5) {
            th = th5;
            r6 = 0;
            r1 = 0;
            c0018b = null;
        }
    }

    public static String a(byte[] bArr) {
        String str;
        try {
            str = a(bArr, 0, bArr.length, 0);
        } catch (IOException e2) {
            if (!f140c) {
                throw new AssertionError(e2.getMessage());
            }
            str = null;
        }
        if (f140c || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String a(byte[] bArr, int i) throws IOException {
        return a(bArr, 0, bArr.length, i);
    }

    public static String a(byte[] bArr, int i, int i2) {
        String str;
        try {
            str = a(bArr, i, i2, 0);
        } catch (IOException e2) {
            if (!f140c) {
                throw new AssertionError(e2.getMessage());
            }
            str = null;
        }
        if (f140c || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String a(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] m70a = m70a(bArr, i, i2, i3);
        try {
            return new String(m70a, "US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            return new String(m70a);
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public static byte[] m69a(byte[] bArr) {
        try {
            return m70a(bArr, 0, bArr.length, 0);
        } catch (IOException e2) {
            if (f140c) {
                return null;
            }
            throw new AssertionError("IOExceptions only come from GZipping, which is turned off: " + e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a  reason: collision with other method in class */
    public static byte[] m70a(byte[] bArr, int i, int i2, int i3) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        C0018b c0018b;
        GZIPOutputStream gZIPOutputStream;
        if (bArr != null) {
            if (i < 0) {
                throw new IllegalArgumentException("Cannot have negative offset: " + i);
            } else if (i2 < 0) {
                throw new IllegalArgumentException("Cannot have length offset: " + i2);
            } else if (i + i2 <= bArr.length) {
                if ((i3 & 2) != 0) {
                    GZIPOutputStream gZIPOutputStream2 = null;
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            c0018b = new C0018b(byteArrayOutputStream, i3 | 1);
                            try {
                                gZIPOutputStream = new GZIPOutputStream(c0018b);
                            } catch (IOException e2) {
                                e = e2;
                                gZIPOutputStream = null;
                            } catch (Throwable th) {
                                th = th;
                                try {
                                    gZIPOutputStream2.close();
                                } catch (Exception unused) {
                                }
                                try {
                                    c0018b.close();
                                } catch (Exception unused2) {
                                }
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception unused3) {
                                }
                                throw th;
                            }
                        } catch (IOException e3) {
                            e = e3;
                            c0018b = null;
                            gZIPOutputStream = null;
                        } catch (Throwable th2) {
                            th = th2;
                            c0018b = null;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        c0018b = null;
                        gZIPOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        byteArrayOutputStream = 0;
                        c0018b = null;
                    }
                    try {
                        gZIPOutputStream.write(bArr, i, i2);
                        gZIPOutputStream.close();
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception unused4) {
                        }
                        try {
                            c0018b.close();
                        } catch (Exception unused5) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused6) {
                        }
                        return byteArrayOutputStream.toByteArray();
                    } catch (IOException e5) {
                        e = e5;
                        gZIPOutputStream2 = byteArrayOutputStream;
                        try {
                            throw e;
                        } catch (Throwable th4) {
                            th = th4;
                            byteArrayOutputStream = gZIPOutputStream2;
                            gZIPOutputStream2 = gZIPOutputStream;
                            gZIPOutputStream2.close();
                            c0018b.close();
                            byteArrayOutputStream.close();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        gZIPOutputStream2 = gZIPOutputStream;
                        gZIPOutputStream2.close();
                        c0018b.close();
                        byteArrayOutputStream.close();
                        throw th;
                    }
                }
                boolean z = (i3 & 8) != 0;
                int i4 = ((i2 / 3) * 4) + (i2 % 3 > 0 ? 4 : 0);
                if (z) {
                    i4 += i4 / 76;
                }
                byte[] bArr2 = new byte[i4];
                int i5 = i2 - 2;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                while (i6 < i5) {
                    a(bArr, i6 + i, 3, bArr2, i7, i3);
                    int i9 = i8 + 4;
                    if (!z || i9 < 76) {
                        i8 = i9;
                    } else {
                        bArr2[i7 + 4] = 10;
                        i7++;
                        i8 = 0;
                    }
                    i6 += 3;
                    i7 += 4;
                }
                if (i6 < i2) {
                    a(bArr, i6 + i, i2 - i6, bArr2, i7, i3);
                    i7 += 4;
                }
                int i10 = i7;
                if (i10 <= bArr2.length - 1) {
                    byte[] bArr3 = new byte[i10];
                    System.arraycopy(bArr2, 0, bArr3, 0, i10);
                    return bArr3;
                }
                return bArr2;
            } else {
                throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)));
            }
        }
        throw new NullPointerException("Cannot serialize a null array.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5;
        if (bArr != null) {
            if (bArr2 == null) {
                throw new NullPointerException("Destination array was null.");
            }
            if (i < 0 || (i4 = i + 3) >= bArr.length) {
                throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i)));
            }
            if (i2 < 0 || (i5 = i2 + 2) >= bArr2.length) {
                throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i2)));
            }
            byte[] b2 = b(i3);
            int i6 = i + 2;
            if (bArr[i6] == 61) {
                bArr2[i2] = (byte) ((((b2[bArr[i + 1]] & d) << 12) | ((b2[bArr[i]] & d) << 18)) >>> 16);
                return 1;
            } else if (bArr[i4] == 61) {
                int i7 = ((b2[bArr[i6]] & d) << 6) | ((b2[bArr[i + 1]] & d) << 12) | ((b2[bArr[i]] & d) << 18);
                bArr2[i2] = (byte) (i7 >>> 16);
                bArr2[i2 + 1] = (byte) (i7 >>> 8);
                return 2;
            } else {
                int i8 = (b2[bArr[i4]] & d) | ((b2[bArr[i + 1]] & d) << 12) | ((b2[bArr[i]] & d) << 18) | ((b2[bArr[i6]] & d) << 6);
                bArr2[i2] = (byte) (i8 >> 16);
                bArr2[i2 + 1] = (byte) (i8 >> 8);
                bArr2[i5] = (byte) i8;
                return 3;
            }
        }
        throw new NullPointerException("Source array was null.");
    }

    public static byte[] decode(byte[] bArr) throws IOException {
        return decode(bArr, 0, bArr.length, 0);
    }

    public static String b(byte[] bArr) {
        return (bArr == null || bArr.length <= 0) ? "" : a(bArr);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4;
        if (bArr == null) {
            throw new NullPointerException("Cannot decode null source array.");
        }
        if (i < 0 || (i4 = i + i2) > bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        if (i2 == 0) {
            return new byte[0];
        }
        if (i2 < 4) {
            throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + i2);
        }
        byte[] b2 = b(i3);
        byte[] bArr2 = new byte[(i2 * 3) / 4];
        byte[] bArr3 = new byte[4];
        int i5 = 0;
        int i6 = 0;
        while (i < i4) {
            byte b3 = b2[bArr[i] & d];
            if (b3 < -5) {
                throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(bArr[i] & d), Integer.valueOf(i)));
            }
            if (b3 >= -1) {
                int i7 = i5 + 1;
                bArr3[i5] = bArr[i];
                if (i7 > 3) {
                    i6 += a(bArr3, 0, bArr2, i6, i3);
                    if (bArr[i] == 61) {
                        break;
                    }
                    i5 = 0;
                } else {
                    i5 = i7;
                }
            }
            i++;
        }
        byte[] bArr4 = new byte[i6];
        System.arraycopy(bArr2, 0, bArr4, 0, i6);
        return bArr4;
    }

    /* renamed from: a  reason: collision with other method in class */
    public static byte[] m68a(String str) throws IOException {
        return decode(str, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0059 -> B:70:0x0059). Please submit an issue!!! */
    public static byte[] decode(String str, int i) throws IOException {
        byte[] bytes;
        ?? length;
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPInputStream gZIPInputStream;
        if (str == null) {
            throw new NullPointerException("Input string was null.");
        }
        try {
            bytes = str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        byte[] decode = decode(bytes, 0, bytes.length, i);
        boolean z = (i & 4) != 0;
        if (decode != null && (length = decode.length) >= 4 && !z && 35615 == ((decode[0] & d) | ((decode[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
            byte[] bArr = new byte[2048];
            GZIPInputStream gZIPInputStream2 = null;
            gZIPInputStream2 = null;
            gZIPInputStream2 = null;
            ByteArrayOutputStream byteArrayOutputStream2 = null;
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        length = new ByteArrayInputStream(decode);
                        try {
                            gZIPInputStream = new GZIPInputStream(length);
                            while (true) {
                                try {
                                    int read = gZIPInputStream.read(bArr);
                                    if (read < 0) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(bArr, 0, read);
                                } catch (IOException e2) {
                                    e = e2;
                                    byteArrayOutputStream2 = byteArrayOutputStream;
                                    length = length;
                                    try {
                                        e.printStackTrace();
                                        byteArrayOutputStream2.close();
                                        gZIPInputStream.close();
                                        length.close();
                                        return decode;
                                    } catch (Throwable th) {
                                        th = th;
                                        byteArrayOutputStream = byteArrayOutputStream2;
                                        gZIPInputStream2 = gZIPInputStream;
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Exception unused2) {
                                        }
                                        try {
                                            gZIPInputStream2.close();
                                        } catch (Exception unused3) {
                                        }
                                        try {
                                            length.close();
                                        } catch (Exception unused4) {
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    gZIPInputStream2 = gZIPInputStream;
                                    byteArrayOutputStream.close();
                                    gZIPInputStream2.close();
                                    length.close();
                                    throw th;
                                }
                            }
                            decode = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                        } catch (IOException e3) {
                            e = e3;
                            gZIPInputStream = null;
                        } catch (Throwable th3) {
                            th = th3;
                            byteArrayOutputStream.close();
                            gZIPInputStream2.close();
                            length.close();
                            throw th;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        length = 0;
                        gZIPInputStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        length = 0;
                    }
                } catch (Exception unused5) {
                }
            } catch (IOException e5) {
                e = e5;
                length = 0;
                gZIPInputStream = null;
            } catch (Throwable th5) {
                th = th5;
                byteArrayOutputStream = null;
                length = 0;
            }
            try {
                gZIPInputStream.close();
            } catch (Exception unused6) {
            }
            try {
                length.close();
            } catch (Exception unused7) {
            }
        }
        return decode;
    }

    public static Object a(String str) throws IOException, ClassNotFoundException {
        return a(str, 0, (ClassLoader) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2 */
    public static Object a(String str, int i, final ClassLoader classLoader) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        byte[] decode = decode(str, i);
        ObjectInputStream objectInputStream2 = 0;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(decode);
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = null;
                objectInputStream2 = classLoader;
            }
            try {
                if (classLoader == null) {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                } else {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream) { // from class: com.alibaba.sdk.android.man.crashreporter.e.b.1
                        @Override // java.io.ObjectInputStream
                        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                            Class<?> cls = Class.forName(objectStreamClass.getName(), false, classLoader);
                            return cls == null ? super.resolveClass(objectStreamClass) : cls;
                        }
                    };
                }
                objectInputStream2 = objectInputStream;
                Object readObject = objectInputStream2.readObject();
                try {
                    byteArrayInputStream.close();
                } catch (Exception unused) {
                }
                try {
                    objectInputStream2.close();
                } catch (Exception unused2) {
                }
                return readObject;
            } catch (IOException e2) {
                e = e2;
                throw e;
            } catch (ClassNotFoundException e3) {
                e = e3;
                throw e;
            } catch (Throwable th2) {
                th = th2;
                try {
                    byteArrayInputStream.close();
                } catch (Exception unused3) {
                }
                try {
                    objectInputStream2.close();
                } catch (Exception unused4) {
                }
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
        } catch (ClassNotFoundException e5) {
            e = e5;
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream = null;
        }
    }

    public static void a(byte[] bArr, String str) throws IOException {
        C0018b c0018b;
        if (bArr == null) {
            throw new NullPointerException("Data to encode was null.");
        }
        C0018b c0018b2 = null;
        try {
            try {
                c0018b = new C0018b(new FileOutputStream(str), 1);
            } catch (Throwable th) {
                th = th;
            }
            try {
                c0018b.write(bArr);
                try {
                    c0018b.close();
                } catch (Exception unused) {
                }
            } catch (IOException e2) {
            } catch (Throwable th2) {
                th = th2;
                c0018b2 = c0018b;
                try {
                    c0018b2.close();
                } catch (Exception unused2) {
                }
                throw th;
            }
        } catch (IOException e3) {
            throw e3;
        }
    }

    public static void f(String str, String str2) throws IOException {
        C0018b c0018b;
        C0018b c0018b2 = null;
        try {
            try {
                c0018b = new C0018b(new FileOutputStream(str2), 0);
            } catch (IOException e2) {
                throw e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            c0018b.write(str.getBytes("US-ASCII"));
            try {
                c0018b.close();
            } catch (Exception unused) {
            }
        } catch (IOException e3) {
        } catch (Throwable th2) {
            th = th2;
            c0018b2 = c0018b;
            try {
                c0018b2.close();
            } catch (Exception unused2) {
            }
            throw th;
        }
    }

    public static byte[] b(String str) throws IOException {
        a aVar = null;
        try {
            try {
                File file = new File(str);
                if (file.length() > 2147483647L) {
                    throw new IOException("File is too big for this convenience method (" + file.length() + " bytes).");
                }
                byte[] bArr = new byte[(int) file.length()];
                a aVar2 = new a(new BufferedInputStream(new FileInputStream(file)), 0);
                int i = 0;
                while (true) {
                    try {
                        int read = aVar2.read(bArr, i, 4096);
                        if (read < 0) {
                            break;
                        }
                        i += read;
                    } catch (IOException e2) {
                        throw e2;
                    } catch (Throwable th) {
                        th = th;
                        aVar = aVar2;
                        try {
                            aVar.close();
                        } catch (Exception unused) {
                        }
                        throw th;
                    }
                }
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                try {
                    aVar2.close();
                } catch (Exception unused2) {
                }
                return bArr2;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            throw e3;
        }
    }

    public static String c(String str) throws IOException {
        a aVar = null;
        try {
            try {
                File file = new File(str);
                byte[] bArr = new byte[Math.max((int) ((file.length() * 1.4d) + 1.0d), 40)];
                a aVar2 = new a(new BufferedInputStream(new FileInputStream(file)), 1);
                int i = 0;
                while (true) {
                    try {
                        int read = aVar2.read(bArr, i, 4096);
                        if (read < 0) {
                            break;
                        }
                        i += read;
                    } catch (IOException e2) {
                        throw e2;
                    } catch (Throwable th) {
                        th = th;
                        aVar = aVar2;
                        try {
                            aVar.close();
                        } catch (Exception unused) {
                        }
                        throw th;
                    }
                }
                String str2 = new String(bArr, 0, i, "US-ASCII");
                try {
                    aVar2.close();
                } catch (Exception unused2) {
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            throw e3;
        }
    }

    public static void g(String str, String str2) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        String c2 = c(str);
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            } catch (Throwable th) {
                th = th;
            }
            try {
                bufferedOutputStream.write(c2.getBytes("US-ASCII"));
                try {
                    bufferedOutputStream.close();
                } catch (Exception unused) {
                }
            } catch (IOException e2) {
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream2 = bufferedOutputStream;
                try {
                    bufferedOutputStream2.close();
                } catch (Exception unused2) {
                }
                throw th;
            }
        } catch (IOException e3) {
            throw e3;
        }
    }

    public static void h(String str, String str2) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        byte[] b2 = b(str);
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            } catch (Throwable th) {
                th = th;
            }
            try {
                bufferedOutputStream.write(b2);
                try {
                    bufferedOutputStream.close();
                } catch (Exception unused) {
                }
            } catch (IOException e2) {
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream2 = bufferedOutputStream;
                try {
                    bufferedOutputStream2.close();
                } catch (Exception unused2) {
                }
                throw th;
            }
        } catch (IOException e3) {
            throw e3;
        }
    }

    /* loaded from: classes.dex */
    public static class a extends FilterInputStream {
        private int P;
        private int Q;
        private int R;
        private int S;
        private byte[] buffer;
        private boolean d;
        private boolean e;
        private byte[] g;
        private int position;

        public a(InputStream inputStream) {
            this(inputStream, 0);
        }

        public a(InputStream inputStream, int i) {
            super(inputStream);
            this.S = i;
            this.e = (i & 8) > 0;
            this.d = (i & 1) > 0;
            this.P = this.d ? 4 : 3;
            this.buffer = new byte[this.P];
            this.position = -1;
            this.R = 0;
            this.g = b.b(i);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int read;
            if (this.position < 0) {
                if (this.d) {
                    byte[] bArr = new byte[3];
                    int i = 0;
                    for (int i2 = 0; i2 < 3; i2++) {
                        int read2 = this.in.read();
                        if (read2 < 0) {
                            break;
                        }
                        bArr[i2] = (byte) read2;
                        i++;
                    }
                    if (i <= 0) {
                        return -1;
                    }
                    b.a(bArr, 0, i, this.buffer, 0, this.S);
                    this.position = 0;
                    this.Q = 4;
                } else {
                    byte[] bArr2 = new byte[4];
                    int i3 = 0;
                    while (i3 < 4) {
                        do {
                            read = this.in.read();
                            if (read < 0) {
                                break;
                            }
                        } while (this.g[read & 127] <= -5);
                        if (read < 0) {
                            break;
                        }
                        bArr2[i3] = (byte) read;
                        i3++;
                    }
                    if (i3 != 4) {
                        if (i3 == 0) {
                            return -1;
                        }
                        throw new IOException("Improperly padded Base64 input.");
                    }
                    this.Q = b.a(bArr2, 0, this.buffer, 0, this.S);
                    this.position = 0;
                }
            }
            int i4 = this.position;
            if (i4 >= 0) {
                if (i4 >= this.Q) {
                    return -1;
                }
                if (this.d && this.e && this.R >= 76) {
                    this.R = 0;
                    return 10;
                }
                this.R++;
                byte[] bArr3 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                byte b = bArr3[i5];
                if (this.position >= this.P) {
                    this.position = -1;
                }
                return b & b.d;
            }
            throw new IOException("Error in Base64 code reading stream.");
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                int read = read();
                if (read >= 0) {
                    bArr[i + i3] = (byte) read;
                    i3++;
                } else if (i3 == 0) {
                    return -1;
                }
            }
            return i3;
        }
    }

    /* renamed from: com.alibaba.sdk.android.man.crashreporter.e.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0018b extends FilterOutputStream {
        private int P;
        private int R;
        private int S;
        private byte[] buffer;
        private boolean d;
        private boolean e;
        private boolean f;
        private byte[] g;
        private byte[] h;
        private int position;

        public C0018b(OutputStream outputStream) {
            this(outputStream, 1);
        }

        public C0018b(OutputStream outputStream, int i) {
            super(outputStream);
            this.e = (i & 8) != 0;
            this.d = (i & 1) != 0;
            this.P = this.d ? 3 : 4;
            this.buffer = new byte[this.P];
            this.position = 0;
            this.R = 0;
            this.f = false;
            this.h = new byte[4];
            this.S = i;
            this.g = b.b(i);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            if (this.f) {
                this.out.write(i);
            } else if (this.d) {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                if (this.position >= this.P) {
                    this.out.write(b.a(this.h, this.buffer, this.P, this.S));
                    this.R += 4;
                    if (this.e && this.R >= 76) {
                        this.out.write(10);
                        this.R = 0;
                    }
                    this.position = 0;
                }
            } else {
                byte[] bArr2 = this.g;
                int i3 = i & 127;
                if (bArr2[i3] > -5) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    bArr3[i4] = (byte) i;
                    if (this.position >= this.P) {
                        this.out.write(this.h, 0, b.a(bArr3, 0, this.h, 0, this.S));
                        this.position = 0;
                    }
                } else if (bArr2[i3] != -5) {
                    throw new IOException("Invalid character in Base64 data.");
                }
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.f) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void f() throws IOException {
            if (this.position > 0) {
                if (this.d) {
                    this.out.write(b.a(this.h, this.buffer, this.position, this.S));
                    this.position = 0;
                    return;
                }
                throw new IOException("Base64 input not properly padded.");
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            f();
            super.close();
            this.buffer = null;
            this.out = null;
        }

        public void g() throws IOException {
            f();
            this.f = true;
        }

        public void h() {
            this.f = false;
        }
    }
}
