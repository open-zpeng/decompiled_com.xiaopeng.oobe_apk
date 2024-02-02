package com.alibaba.sdk.android.utils;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public class AMSDevReporter {
    private static Context context;

    /* renamed from: a  reason: collision with other field name */
    private static final ExecutorService f157a = Executors.newSingleThreadExecutor(new a());
    private static ConcurrentHashMap<AMSSdkTypeEnum, AMSReportStatusEnum> a = new ConcurrentHashMap<>();

    /* renamed from: a  reason: collision with other field name */
    private static boolean f158a = false;
    private static String TAG = "AMSDevReporter";

    /* loaded from: classes.dex */
    public enum AMSReportStatusEnum {
        UNREPORTED,
        REPORTED
    }

    /* loaded from: classes.dex */
    public enum AMSSdkTypeEnum {
        AMS_MAN("MAN"),
        AMS_HTTPDNS("HTTPDNS"),
        AMS_MPUSH("MPUSH"),
        AMS_MAC("MAC"),
        AMS_API("API"),
        AMS_HOTFIX("HOTFIX"),
        AMS_FEEDBACK("FEEDBACK"),
        AMS_IM("IM");
        
        private String description;

        AMSSdkTypeEnum(String str) {
            this.description = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.description;
        }
    }

    /* loaded from: classes.dex */
    public enum AMSSdkExtInfoKeyEnum {
        AMS_EXTINFO_KEY_VERSION("SdkVersion"),
        AMS_EXTINFO_KEY_PACKAGE("PackageName");
        
        private String description;

        AMSSdkExtInfoKeyEnum(String str) {
            this.description = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.description;
        }
    }

    static {
        for (AMSSdkTypeEnum aMSSdkTypeEnum : AMSSdkTypeEnum.values()) {
            a.put(aMSSdkTypeEnum, AMSReportStatusEnum.UNREPORTED);
        }
    }

    public static void setLogEnabled(boolean z) {
        d.setLogEnabled(z);
    }

    public static AMSReportStatusEnum getReportStatus(AMSSdkTypeEnum aMSSdkTypeEnum) {
        return a.get(aMSSdkTypeEnum);
    }

    public static void asyncReport(Context context2, AMSSdkTypeEnum aMSSdkTypeEnum) {
        asyncReport(context2, aMSSdkTypeEnum, null);
    }

    public static void asyncReport(Context context2, final AMSSdkTypeEnum aMSSdkTypeEnum, final Map<String, Object> map) {
        if (context2 == null) {
            d.c(TAG, "Context is null, return.");
            return;
        }
        context = context2;
        String str = TAG;
        d.b(str, "Add [" + aMSSdkTypeEnum.toString() + "] to report queue.");
        f158a = false;
        f157a.execute(new Runnable() { // from class: com.alibaba.sdk.android.utils.AMSDevReporter.1
            @Override // java.lang.Runnable
            public void run() {
                if (AMSDevReporter.f158a) {
                    d.c(AMSDevReporter.TAG, "Unable to execute remain task in queue, return.");
                    return;
                }
                String str2 = AMSDevReporter.TAG;
                d.b(str2, "Get [" + AMSSdkTypeEnum.this.toString() + "] from report queue.");
                AMSDevReporter.a(AMSSdkTypeEnum.this, (Map<String, Object>) map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(AMSSdkTypeEnum aMSSdkTypeEnum, Map<String, Object> map) {
        String aMSSdkTypeEnum2 = aMSSdkTypeEnum.toString();
        if (a.get(aMSSdkTypeEnum) != AMSReportStatusEnum.UNREPORTED) {
            d.b(TAG, "[" + aMSSdkTypeEnum2 + "] already reported, return.");
            return;
        }
        int i = 0;
        int i2 = 5;
        while (true) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Report [");
            sb.append(aMSSdkTypeEnum2);
            sb.append("], times: [");
            i++;
            sb.append(i);
            sb.append("].");
            d.b(str, sb.toString());
            if (!m74a(aMSSdkTypeEnum, map)) {
                if (i <= 10) {
                    d.b(TAG, "Report [" + aMSSdkTypeEnum2 + "] failed, wait for [" + i2 + "] seconds.");
                    e.a((double) i2);
                    i2 *= 2;
                    if (i2 >= 60) {
                        i2 = 60;
                    }
                } else {
                    d.c(TAG, "Report [" + aMSSdkTypeEnum2 + "] stat failed, exceed max retry times, return.");
                    a.put(aMSSdkTypeEnum, AMSReportStatusEnum.UNREPORTED);
                    f158a = true;
                    break;
                }
            } else {
                d.b(TAG, "Report [" + aMSSdkTypeEnum2 + "] stat success.");
                a.put(aMSSdkTypeEnum, AMSReportStatusEnum.REPORTED);
                break;
            }
        }
        if (f158a) {
            d.c(TAG, "Report [" + aMSSdkTypeEnum2 + "] failed, clear remain report in queue.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01e4 A[Catch: IOException -> 0x01e0, TRY_LEAVE, TryCatch #11 {IOException -> 0x01e0, blocks: (B:75:0x01dc, B:79:0x01e4), top: B:90:0x01dc }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01dc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a  reason: collision with other method in class */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean m74a(com.alibaba.sdk.android.utils.AMSDevReporter.AMSSdkTypeEnum r10, java.util.Map<java.lang.String, java.lang.Object> r11) {
        /*
            Method dump skipped, instructions count: 494
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.utils.AMSDevReporter.m74a(com.alibaba.sdk.android.utils.AMSDevReporter$AMSSdkTypeEnum, java.util.Map):boolean");
    }

    private static String a(AMSSdkTypeEnum aMSSdkTypeEnum, String str, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(aMSSdkTypeEnum);
        sb.append("-");
        sb.append(str);
        if (map != null) {
            String str2 = (String) map.get(AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_VERSION.toString());
            if (!e.m82a(str2)) {
                sb.append("-");
                sb.append(str2);
            }
            String str3 = (String) map.get(AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_PACKAGE.toString());
            if (!e.m82a(str3)) {
                sb.append("-");
                sb.append(str3);
            }
        }
        return sb.toString();
    }
}
