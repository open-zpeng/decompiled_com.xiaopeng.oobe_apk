package com.alibaba.sdk.android.utils.crashdefend;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: CrashDefendUtils.java */
/* loaded from: classes.dex */
class e {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context, a aVar, List<c> list) {
        String str;
        String str2;
        if (context == null) {
            return;
        }
        synchronized (list) {
            FileOutputStream fileOutputStream = null;
            try {
                JSONObject jSONObject = new JSONObject();
                if (aVar != null) {
                    jSONObject.put("startSerialNumber", aVar.a);
                }
                if (list != null) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        for (c cVar : list) {
                            if (cVar != null) {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("sdkId", cVar.f173c);
                                jSONObject2.put("sdkVersion", cVar.f174d);
                                jSONObject2.put("crashLimit", cVar.b);
                                jSONObject2.put("crashCount", cVar.crashCount);
                                jSONObject2.put("waitTime", cVar.c);
                                jSONObject2.put("registerSerialNumber", cVar.f172b);
                                jSONObject2.put("startSerialNumber", cVar.a);
                                jSONObject2.put("restoreCount", cVar.d);
                                jSONArray.put(jSONObject2);
                            }
                        }
                        jSONObject.put("sdkList", jSONArray);
                    } catch (JSONException e) {
                        Log.e("CrashUtils", "save sdk json fail:", e);
                    }
                }
                String jSONObject3 = jSONObject.toString();
                fileOutputStream = a(context) ? context.openFileOutput("com_alibaba_aliyun_crash_defend_sdk_info", 0) : context.openFileOutput("com_alibaba_aliyun_crash_defend_sdk_info_" + b(context), 0);
                fileOutputStream.write(jSONObject3.getBytes());
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e = e2;
                        str = "CrashUtils";
                        str2 = "save sdk io fail:";
                        Log.e(str, str2, e);
                    }
                }
            } catch (IOException e3) {
                Log.e("CrashUtils", "save sdk io fail:", e3);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e = e4;
                        str = "CrashUtils";
                        str2 = "save sdk io fail:";
                        Log.e(str, str2, e);
                    }
                }
            } catch (Exception e5) {
                Log.e("CrashUtils", "save sdk exception:", e5);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        e = e6;
                        str = "CrashUtils";
                        str2 = "save sdk io fail:";
                        Log.e(str, str2, e);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:52:0x009b A[Catch: all -> 0x013f, DONT_GENERATE, TRY_LEAVE, TryCatch #11 {, blocks: (B:19:0x0049, B:50:0x0095, B:52:0x009b, B:54:0x009d, B:55:0x00b7, B:57:0x00bd, B:59:0x00c3, B:61:0x0116, B:67:0x012d, B:64:0x011d, B:66:0x0126, B:23:0x0052, B:30:0x0063, B:38:0x0077, B:46:0x008b, B:8:0x000c, B:10:0x0012, B:13:0x0034, B:14:0x0038, B:16:0x003e, B:12:0x001a, B:28:0x005a, B:36:0x006e, B:44:0x0082), top: B:89:0x000c, inners: #4, #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x009d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a  reason: collision with other method in class */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean m81a(android.content.Context r9, com.alibaba.sdk.android.utils.crashdefend.a r10, java.util.List<com.alibaba.sdk.android.utils.crashdefend.c> r11) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.utils.crashdefend.e.m81a(android.content.Context, com.alibaba.sdk.android.utils.crashdefend.a, java.util.List):boolean");
    }

    private static boolean a(Context context) {
        return context.getPackageName().equalsIgnoreCase(b(context));
    }

    private static String b(Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        String d = d(context);
        if (TextUtils.isEmpty(d)) {
            String c = c();
            return !TextUtils.isEmpty(c) ? c : c(context);
        }
        return d;
    }

    private static String c(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return "";
        }
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String c() {
        /*
            int r0 = android.os.Process.myPid()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r3.<init>()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            java.lang.String r4 = "/proc/"
            r3.append(r4)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r3.append(r0)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            java.lang.String r0 = "/cmdline"
            r3.append(r0)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            boolean r0 = r2.exists()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            if (r0 == 0) goto L3b
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            java.lang.String r2 = r0.readLine()     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L67
            java.lang.String r1 = r2.trim()     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L67
            goto L3c
        L39:
            r2 = move-exception
            goto L4b
        L3b:
            r0 = r1
        L3c:
            if (r0 == 0) goto L66
            r0.close()     // Catch: java.io.IOException -> L42
            goto L66
        L42:
            r0 = move-exception
            r0.printStackTrace()
            goto L66
        L47:
            r0 = move-exception
            goto L6b
        L49:
            r2 = move-exception
            r0 = r1
        L4b:
            java.lang.String r3 = "CrashUtils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67
            r4.<init>()     // Catch: java.lang.Throwable -> L67
            java.lang.String r5 = "getProcessNameByPid error: "
            r4.append(r5)     // Catch: java.lang.Throwable -> L67
            r4.append(r2)     // Catch: java.lang.Throwable -> L67
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Throwable -> L67
            android.util.Log.d(r3, r2)     // Catch: java.lang.Throwable -> L67
            if (r0 == 0) goto L66
            r0.close()     // Catch: java.io.IOException -> L42
        L66:
            return r1
        L67:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L6b:
            if (r1 == 0) goto L75
            r1.close()     // Catch: java.io.IOException -> L71
            goto L75
        L71:
            r1 = move-exception
            r1.printStackTrace()
        L75:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.utils.crashdefend.e.c():java.lang.String");
    }

    private static String d(Context context) {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, context.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.d("CrashUtils", "getProcessNameByActivityThread error: " + e);
            return null;
        }
    }
}
