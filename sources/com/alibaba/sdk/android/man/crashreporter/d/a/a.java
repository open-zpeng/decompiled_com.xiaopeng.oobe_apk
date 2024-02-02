package com.alibaba.sdk.android.man.crashreporter.d.a;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.e.f;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectOutputStream;
/* loaded from: classes.dex */
public class a {
    public static String[] a(final Context context, String str) {
        try {
            if (context == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to get crash reports but MotuCrashReporter is not initialized.");
                return new String[0];
            }
            File dir = context.getDir(str, 0);
            if (dir == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.g("Application files directory does not exist! The application may not be installed correctly. Please try reinstalling.");
                return new String[0];
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Looking for error files in " + dir.getAbsolutePath());
            String[] list = dir.list(new FilenameFilter() { // from class: com.alibaba.sdk.android.man.crashreporter.d.a.a.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    String a = com.alibaba.sdk.android.man.crashreporter.e.a.a(context);
                    if (a == null) {
                        return str2.startsWith("FAILURE");
                    }
                    return str2.startsWith(Integer.toString(i.a(i.a(a, ""))));
                }
            });
            return list == null ? new String[0] : list;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("find file error.", e);
            return null;
        }
    }

    public static String[] a(Context context, String str, final String str2) {
        try {
            if (context == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to get crash reports but MotuCrashReporter is not initialized.");
                return new String[0];
            }
            File file = new File(str);
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Looking for error files in " + file.getAbsolutePath());
            String[] list = file.list(new FilenameFilter() { // from class: com.alibaba.sdk.android.man.crashreporter.d.a.a.2
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str3) {
                    return str3.endsWith(str2);
                }
            });
            return list == null ? new String[0] : list;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("find file error.", e);
            return null;
        }
    }

    public static void a(Object obj, File file) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        if (obj == null || file == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("store file error:object or file is null!");
            return;
        }
        ObjectOutputStream objectOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                try {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                objectOutputStream.writeObject(obj);
                f.a(objectOutputStream);
            } catch (Exception e2) {
                e = e2;
                objectOutputStream2 = objectOutputStream;
                com.alibaba.sdk.android.man.crashreporter.b.a.d("store file error.", e);
                if (objectOutputStream2 != null) {
                    f.a(objectOutputStream2);
                }
                if (fileOutputStream == null) {
                    return;
                }
                f.a(fileOutputStream);
            } catch (Throwable th2) {
                th = th2;
                objectOutputStream2 = objectOutputStream;
                if (objectOutputStream2 != null) {
                    f.a(objectOutputStream2);
                }
                if (fileOutputStream != null) {
                    f.a(fileOutputStream);
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
        f.a(fileOutputStream);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0024, code lost:
        if (r1 == null) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object a(java.io.InputStream r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L9
            java.lang.String r3 = "load file error:input stream is null!"
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r3)
            return r0
        L9:
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L1a java.lang.Exception -> L1d
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L1a java.lang.Exception -> L1d
            java.lang.Object r3 = r1.readObject()     // Catch: java.lang.Exception -> L18 java.lang.Throwable -> L2a
            if (r3 == 0) goto L26
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r1)
            return r3
        L18:
            r3 = move-exception
            goto L1f
        L1a:
            r3 = move-exception
            r1 = r0
            goto L2b
        L1d:
            r3 = move-exception
            r1 = r0
        L1f:
            java.lang.String r2 = "load reports error."
            com.alibaba.sdk.android.man.crashreporter.b.a.d(r2, r3)     // Catch: java.lang.Throwable -> L2a
            if (r1 == 0) goto L29
        L26:
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r1)
        L29:
            return r0
        L2a:
            r3 = move-exception
        L2b:
            if (r1 == 0) goto L30
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r1)
        L30:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.man.crashreporter.d.a.a.a(java.io.InputStream):java.lang.Object");
    }
}
