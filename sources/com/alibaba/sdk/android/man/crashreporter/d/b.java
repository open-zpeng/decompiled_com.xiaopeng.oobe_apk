package com.alibaba.sdk.android.man.crashreporter.d;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import java.io.File;
/* loaded from: classes.dex */
public class b implements c {
    private final String TOMBSTONE_PATH = "tombstone";
    private final String MOTU_PATH = "motu";
    private final String t = ".stacktrace";
    private final String u = "-waitsend";
    private Context a = null;

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public BaseDataContent a() {
        return null;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public void a(BaseDataContent baseDataContent) {
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public void b(boolean z) {
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String h() {
        return "";
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String j() {
        return ".stacktrace";
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public boolean c(Context context) {
        try {
            this.a = context;
            return true;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("init storer err", e);
            return false;
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String[] a(int i) {
        Context context = this.a;
        if (context == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to load crash report but context is null.");
            return null;
        } else if (i == 0 || i == 2) {
            return com.alibaba.sdk.android.man.crashreporter.d.a.a.a(this.a, "tombstone");
        } else {
            if (i == 1) {
                return com.alibaba.sdk.android.man.crashreporter.d.a.a.a(this.a, String.format("%s/%s", context.getDir("tombstone", 0).getAbsolutePath(), "motu"), ".stacktrace");
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0069  */
    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave b(java.lang.String r6) {
        /*
            r5 = this;
            android.content.Context r0 = r5.a
            r1 = 0
            if (r0 != 0) goto Lb
            java.lang.String r6 = "Trying to load crash report but context is null."
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r6)
            return r1
        Lb:
            r2 = 0
            java.lang.String r3 = "tombstone"
            java.io.File r0 = r0.getDir(r3, r2)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            r3.<init>()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            java.lang.String r0 = r0.getPath()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            r3.append(r0)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            java.lang.String r0 = "/"
            r3.append(r0)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            r3.append(r6)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
            java.io.File r0 = new java.io.File     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L48
            r0.<init>(r6)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L48
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L48
            r3.<init>(r0)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L48
            java.lang.Object r0 = com.alibaba.sdk.android.man.crashreporter.d.a.a.a(r3)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L65
            boolean r4 = r0 instanceof com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L65
            if (r4 == 0) goto L42
            com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave r0 = (com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave) r0     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L65
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r3)
            return r0
        L42:
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r3)
            return r1
        L46:
            r3 = r1
            goto L4c
        L48:
            r6 = move-exception
            goto L67
        L4a:
            r6 = r1
            r3 = r6
        L4c:
            java.lang.String r0 = "Trying to load crash report but file:%s not found."
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L65
            r4[r2] = r6     // Catch: java.lang.Throwable -> L65
            java.lang.String r0 = java.lang.String.format(r0, r4)     // Catch: java.lang.Throwable -> L65
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r0)     // Catch: java.lang.Throwable -> L65
            if (r6 == 0) goto L5f
            com.alibaba.sdk.android.man.crashreporter.e.e.i(r6)     // Catch: java.lang.Throwable -> L65
        L5f:
            if (r3 == 0) goto L64
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r3)
        L64:
            return r1
        L65:
            r6 = move-exception
            r1 = r3
        L67:
            if (r1 == 0) goto L6c
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r1)
        L6c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.man.crashreporter.d.b.b(java.lang.String):com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave");
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public boolean a(CrashReportDataForSave crashReportDataForSave, int i) {
        if (crashReportDataForSave.path == null && i == 1) {
            return true;
        }
        if (crashReportDataForSave != null && !i.a((CharSequence) crashReportDataForSave.path)) {
            String str = crashReportDataForSave.path;
            File file = new File(str);
            if (!file.exists()) {
                file = new File(str + "-waitsend");
                if (file.exists() && file.isFile()) {
                    crashReportDataForSave.path = str + "-waitsend";
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("file exists!");
                    return true;
                }
            }
            if (file.exists() && file.isFile()) {
                String str2 = str + "-waitsend";
                if (file.renameTo(new File(str2))) {
                    crashReportDataForSave.path = str2;
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("file exists!");
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public void b(CrashReportDataForSave crashReportDataForSave) {
        if (this.a == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to load crash report but context is null.");
            return;
        }
        try {
            File a = a(crashReportDataForSave.fileName);
            if (a != null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.b("save crash file: ", a.getAbsolutePath());
                com.alibaba.sdk.android.man.crashreporter.d.a.a.a(crashReportDataForSave, a);
                com.alibaba.sdk.android.man.crashreporter.b.a.e("save crash file succ ");
            } else {
                com.alibaba.sdk.android.man.crashreporter.b.a.h("store crash report file failure!");
            }
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("crash data save error.", e);
        }
    }

    private File a(String str) {
        File dir = this.a.getDir("tombstone", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (dir.canWrite()) {
            try {
                return new File(str.contains(".stacktrace") ? String.format("%s/%s", dir.getPath(), str) : String.format("%s/%s%s", dir.getPath(), str, ".stacktrace"));
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("data build error.", e);
                return null;
            }
        }
        return null;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String i() {
        File dir = this.a.getDir("tombstone", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (dir.canWrite()) {
            return dir.getPath();
        }
        return null;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String a(long j) {
        return String.format("%s_%s", Integer.toString(i.a(i.a(com.alibaba.sdk.android.man.crashreporter.e.a.a(this.a), ""))), Long.valueOf(j));
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L75
            int r1 = r5.length()
            if (r1 != 0) goto Lb
            goto L75
        Lb:
            java.io.File r1 = new java.io.File
            r1.<init>(r5)
            boolean r5 = r1.exists()
            if (r5 == 0) goto L74
            boolean r5 = r1.isFile()
            if (r5 == 0) goto L74
            boolean r5 = r1.canRead()
            if (r5 == 0) goto L74
            boolean r5 = r1.canWrite()
            if (r5 == 0) goto L74
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3f java.io.FileNotFoundException -> L42
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L3f java.io.FileNotFoundException -> L42
            java.lang.Object r2 = com.alibaba.sdk.android.man.crashreporter.d.a.a.a(r5)     // Catch: java.io.FileNotFoundException -> L43 java.lang.Throwable -> L6d
            boolean r3 = r2 instanceof com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave     // Catch: java.io.FileNotFoundException -> L43 java.lang.Throwable -> L6d
            if (r3 == 0) goto L3b
            com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave r2 = (com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave) r2     // Catch: java.io.FileNotFoundException -> L43 java.lang.Throwable -> L6d
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r5)
            goto L55
        L3b:
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r5)
            return r0
        L3f:
            r6 = move-exception
            r5 = r0
            goto L6e
        L42:
            r5 = r0
        L43:
            java.lang.String r2 = "Trying to load deduplication crash report but file not found."
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L6d
            java.lang.String r2 = java.lang.String.format(r2, r3)     // Catch: java.lang.Throwable -> L6d
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r2)     // Catch: java.lang.Throwable -> L6d
            if (r5 == 0) goto L54
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r5)
        L54:
            r2 = r0
        L55:
            if (r2 == 0) goto L74
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L66
            r2.times = r5     // Catch: java.lang.Exception -> L66
            com.alibaba.sdk.android.man.crashreporter.d.a.a.a(r2, r1)     // Catch: java.lang.Exception -> L66
            java.lang.String r5 = "save deduplication file succ "
            com.alibaba.sdk.android.man.crashreporter.b.a.e(r5)     // Catch: java.lang.Exception -> L66
            return r2
        L66:
            r5 = move-exception
            java.lang.String r6 = "deduplicationFile build error."
            com.alibaba.sdk.android.man.crashreporter.b.a.d(r6, r5)
            goto L74
        L6d:
            r6 = move-exception
        L6e:
            if (r5 == 0) goto L73
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r5)
        L73:
            throw r6
        L74:
            return r0
        L75:
            java.lang.String r5 = "load file failure"
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.man.crashreporter.d.b.a(java.lang.String, int):com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave");
    }
}
