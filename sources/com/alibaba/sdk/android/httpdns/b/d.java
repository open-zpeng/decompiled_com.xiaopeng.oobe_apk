package com.alibaba.sdk.android.httpdns.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.lzy.okgo.cookie.SerializableCookie;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
class d extends SQLiteOpenHelper {
    private static final Object a = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Context context) {
        super(context, "aliclound_httpdns.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    private long a(SQLiteDatabase sQLiteDatabase, g gVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("host_id", Long.valueOf(gVar.h));
        contentValues.put("ip", gVar.k);
        contentValues.put("ttl", gVar.l);
        try {
            return sQLiteDatabase.insert("ip", null, contentValues);
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0086, code lost:
        if (r5 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009e, code lost:
        if (r5 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a0, code lost:
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a3, code lost:
        return r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.alibaba.sdk.android.httpdns.b.g> a(long r9) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)
            java.lang.String r2 = "ip"
            r1.append(r2)
            java.lang.String r3 = " WHERE "
            r1.append(r3)
            java.lang.String r3 = "host_id"
            r1.append(r3)
            java.lang.String r4 = " =? ;"
            r1.append(r4)
            r4 = 0
            android.database.sqlite.SQLiteDatabase r5 = r8.getWritableDatabase()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L98
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            r6 = 1
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            r7 = 0
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            r6[r7] = r9     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            android.database.Cursor r4 = r5.rawQuery(r1, r6)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            if (r4 == 0) goto L81
            int r9 = r4.getCount()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            if (r9 <= 0) goto L81
            r4.moveToFirst()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
        L45:
            com.alibaba.sdk.android.httpdns.b.g r9 = new com.alibaba.sdk.android.httpdns.b.g     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            r9.<init>()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            java.lang.String r10 = "id"
            int r10 = r4.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            int r10 = r4.getInt(r10)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            long r6 = (long) r10     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            r9.id = r6     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            int r10 = r4.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            int r10 = r4.getInt(r10)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            long r6 = (long) r10     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            r9.h = r6     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            int r10 = r4.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            java.lang.String r10 = r4.getString(r10)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            r9.k = r10     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            java.lang.String r10 = "ttl"
            int r10 = r4.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            java.lang.String r10 = r4.getString(r10)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            r9.l = r10     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            r0.add(r9)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            boolean r9 = r4.moveToNext()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L99
            if (r9 != 0) goto L45
        L81:
            if (r4 == 0) goto L86
            r4.close()
        L86:
            if (r5 == 0) goto La3
            goto La0
        L89:
            r9 = move-exception
            goto L8d
        L8b:
            r9 = move-exception
            r5 = r4
        L8d:
            if (r4 == 0) goto L92
            r4.close()
        L92:
            if (r5 == 0) goto L97
            r5.close()
        L97:
            throw r9
        L98:
            r5 = r4
        L99:
            if (r4 == 0) goto L9e
            r4.close()
        L9e:
            if (r5 == 0) goto La3
        La0:
            r5.close()
        La3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.b.d.a(long):java.util.List");
    }

    private List<g> a(e eVar) {
        return a(eVar.id);
    }

    /* renamed from: a  reason: collision with other method in class */
    private void m34a(long j) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.delete(SerializableCookie.HOST, "id = ?", new String[]{String.valueOf(j)});
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (Exception unused) {
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
        sQLiteDatabase.close();
    }

    private void a(g gVar) {
        b(gVar.id);
    }

    private void b(long j) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.delete("ip", "id = ?", new String[]{String.valueOf(j)});
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (Exception unused) {
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
        sQLiteDatabase.close();
    }

    private void c(e eVar) {
        m34a(eVar.id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public long m35a(e eVar) {
        SQLiteDatabase sQLiteDatabase;
        synchronized (a) {
            b(eVar.i, eVar.h);
            ContentValues contentValues = new ContentValues();
            try {
                sQLiteDatabase = getWritableDatabase();
                try {
                    sQLiteDatabase.beginTransaction();
                    contentValues.put(SerializableCookie.HOST, eVar.h);
                    contentValues.put("sp", eVar.i);
                    contentValues.put("time", c.c(eVar.j));
                    long insert = sQLiteDatabase.insert(SerializableCookie.HOST, null, contentValues);
                    eVar.id = insert;
                    if (eVar.a != null) {
                        Iterator<g> it = eVar.a.iterator();
                        while (it.hasNext()) {
                            g next = it.next();
                            next.h = insert;
                            next.id = a(sQLiteDatabase, next);
                        }
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                    return insert;
                } catch (Exception unused) {
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                    return 0L;
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            } catch (Exception unused2) {
                sQLiteDatabase = null;
            } catch (Throwable th2) {
                th = th2;
                sQLiteDatabase = null;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x009e, code lost:
        if (r3 == null) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009b A[Catch: all -> 0x00c5, TRY_ENTER, TryCatch #3 {, blocks: (B:4:0x0003, B:19:0x009b, B:21:0x00a0, B:37:0x00c3, B:34:0x00bd, B:28:0x00af, B:30:0x00b4, B:31:0x00b7), top: B:46:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.alibaba.sdk.android.httpdns.b.e a(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.Object r0 = com.alibaba.sdk.android.httpdns.b.d.a
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc5
            r1.<init>()     // Catch: java.lang.Throwable -> Lc5
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc5
            java.lang.String r2 = "host"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc5
            java.lang.String r2 = " WHERE "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc5
            java.lang.String r2 = "sp"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc5
            java.lang.String r2 = " =? "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc5
            java.lang.String r2 = " AND "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc5
            java.lang.String r2 = "host"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc5
            java.lang.String r2 = " =? ;"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc5
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r6.getReadableDatabase()     // Catch: java.lang.Throwable -> Laa java.lang.Exception -> Lb8
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
            r5 = 0
            r4[r5] = r7     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
            r7 = 1
            r4[r7] = r8     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
            android.database.Cursor r7 = r3.rawQuery(r1, r4)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
            if (r7 == 0) goto L98
            int r8 = r7.getCount()     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            if (r8 <= 0) goto L98
            r7.moveToFirst()     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            com.alibaba.sdk.android.httpdns.b.e r8 = new com.alibaba.sdk.android.httpdns.b.e     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            r8.<init>()     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            java.lang.String r1 = "id"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            int r1 = r7.getInt(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            r8.id = r1     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            java.lang.String r1 = "host"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            r8.h = r1     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            java.lang.String r1 = "sp"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            r8.i = r1     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            java.lang.String r1 = "time"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            java.lang.String r1 = com.alibaba.sdk.android.httpdns.b.c.d(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            r8.j = r1     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            java.util.List r1 = r6.a(r8)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            r8.a = r1     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> Lbb
            goto L99
        L94:
            r8 = move-exception
            goto Lad
        L96:
            r8 = r2
            goto Lbb
        L98:
            r8 = r2
        L99:
            if (r7 == 0) goto L9e
            r7.close()     // Catch: java.lang.Throwable -> Lc5
        L9e:
            if (r3 == 0) goto Lc3
        La0:
            r3.close()     // Catch: java.lang.Throwable -> Lc5
            goto Lc3
        La4:
            r8 = move-exception
            r7 = r2
            goto Lad
        La7:
            r7 = r2
            r8 = r7
            goto Lbb
        Laa:
            r8 = move-exception
            r7 = r2
            r3 = r7
        Lad:
            if (r7 == 0) goto Lb2
            r7.close()     // Catch: java.lang.Throwable -> Lc5
        Lb2:
            if (r3 == 0) goto Lb7
            r3.close()     // Catch: java.lang.Throwable -> Lc5
        Lb7:
            throw r8     // Catch: java.lang.Throwable -> Lc5
        Lb8:
            r7 = r2
            r8 = r7
            r3 = r8
        Lbb:
            if (r7 == 0) goto Lc0
            r7.close()     // Catch: java.lang.Throwable -> Lc5
        Lc0:
            if (r3 == 0) goto Lc3
            goto La0
        Lc3:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc5
            return r8
        Lc5:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc5
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.b.d.a(java.lang.String, java.lang.String):com.alibaba.sdk.android.httpdns.b.e");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0084, code lost:
        if (r4 == null) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0081 A[Catch: all -> 0x00a4, TRY_ENTER, TryCatch #0 {, blocks: (B:4:0x0003, B:15:0x0081, B:17:0x0086, B:32:0x00a2, B:23:0x0090, B:25:0x0095, B:26:0x0098, B:29:0x009c), top: B:38:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.alibaba.sdk.android.httpdns.b.e> b() {
        /*
            r7 = this;
            java.lang.Object r0 = com.alibaba.sdk.android.httpdns.b.d.a
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La4
            r1.<init>()     // Catch: java.lang.Throwable -> La4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La4
            r2.<init>()     // Catch: java.lang.Throwable -> La4
            java.lang.String r3 = "SELECT * FROM "
            r2.append(r3)     // Catch: java.lang.Throwable -> La4
            java.lang.String r3 = "host"
            r2.append(r3)     // Catch: java.lang.Throwable -> La4
            java.lang.String r3 = " ; "
            r2.append(r3)     // Catch: java.lang.Throwable -> La4
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r7.getReadableDatabase()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L99
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            android.database.Cursor r3 = r4.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            if (r3 == 0) goto L7f
            int r2 = r3.getCount()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            if (r2 <= 0) goto L7f
            r3.moveToFirst()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
        L34:
            com.alibaba.sdk.android.httpdns.b.e r2 = new com.alibaba.sdk.android.httpdns.b.e     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            r2.<init>()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.lang.String r5 = "id"
            int r5 = r3.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            int r5 = r3.getInt(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            long r5 = (long) r5     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            r2.id = r5     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.lang.String r5 = "host"
            int r5 = r3.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            r2.h = r5     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.lang.String r5 = "sp"
            int r5 = r3.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            r2.i = r5     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.lang.String r5 = "time"
            int r5 = r3.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.lang.String r5 = com.alibaba.sdk.android.httpdns.b.c.d(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            r2.j = r5     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.util.List r5 = r7.a(r2)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            r2.a = r5     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            r1.add(r2)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            boolean r2 = r3.moveToNext()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L9a
            if (r2 != 0) goto L34
        L7f:
            if (r3 == 0) goto L84
            r3.close()     // Catch: java.lang.Throwable -> La4
        L84:
            if (r4 == 0) goto La2
        L86:
            r4.close()     // Catch: java.lang.Throwable -> La4
            goto La2
        L8a:
            r1 = move-exception
            goto L8e
        L8c:
            r1 = move-exception
            r4 = r3
        L8e:
            if (r3 == 0) goto L93
            r3.close()     // Catch: java.lang.Throwable -> La4
        L93:
            if (r4 == 0) goto L98
            r4.close()     // Catch: java.lang.Throwable -> La4
        L98:
            throw r1     // Catch: java.lang.Throwable -> La4
        L99:
            r4 = r3
        L9a:
            if (r3 == 0) goto L9f
            r3.close()     // Catch: java.lang.Throwable -> La4
        L9f:
            if (r4 == 0) goto La2
            goto L86
        La2:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La4
            return r1
        La4:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La4
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.b.d.b():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str, String str2) {
        synchronized (a) {
            e a2 = a(str, str2);
            if (a2 != null) {
                c(a2);
                if (a2.a != null) {
                    Iterator<g> it = a2.a.iterator();
                    while (it.hasNext()) {
                        a(it.next());
                    }
                }
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE host (id INTEGER PRIMARY KEY,host TEXT,sp TEXT,time TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE ip (id INTEGER PRIMARY KEY,host_id INTEGER,ip TEXT,ttl TEXT);");
        } catch (Exception unused) {
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != i2) {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS host;");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ip;");
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                onCreate(sQLiteDatabase);
            } catch (Exception unused) {
            }
        }
    }
}
