package com.alibaba.mtl.log.d;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.SdkMeta;
import com.alibaba.mtl.log.model.LogField;
import com.alibaba.mtl.log.sign.BaseRequestAuth;
import com.alibaba.mtl.log.sign.IRequestAuth;
import com.alibaba.mtl.log.sign.SecurityRequestAuth;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
/* compiled from: UrlWrapper.java */
/* loaded from: classes.dex */
public class u {
    private static final String TAG = "u";

    public static String a(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String a;
        String str2;
        String str3 = "";
        if (map2 != null && map2.size() > 0) {
            Set<String> keySet = map2.keySet();
            String[] strArr = new String[keySet.size()];
            keySet.toArray(strArr);
            for (String str4 : g.a().a(strArr, true)) {
                str3 = str3 + str4 + j.b((byte[]) map2.get(str4));
            }
        }
        try {
            a = a(str, null, null, str3);
        } catch (Throwable unused) {
            a = a(com.alibaba.mtl.log.a.a.g(), null, null, str3);
        }
        if (TextUtils.isEmpty(com.alibaba.mtl.log.a.a.S)) {
            return a;
        }
        return a + "&dk=" + URLEncoder.encode(str2, "UTF-8");
    }

    private static String a(String str, String str2, String str3, String str4) throws Exception {
        String str5;
        Context context = com.alibaba.mtl.log.a.getContext();
        String appkey = b.getAppkey();
        String m = b.m();
        if (m == null) {
            m = "";
        }
        String str6 = d.a(context).get(LogField.APPVERSION.toString());
        String str7 = d.a(context).get(LogField.OS.toString());
        String str8 = d.a(context).get(LogField.UTDID.toString());
        String valueOf = String.valueOf(System.currentTimeMillis());
        IRequestAuth a = com.alibaba.mtl.log.a.a();
        String str9 = "1";
        String str10 = "0";
        if (a instanceof SecurityRequestAuth) {
            str10 = "1";
            str9 = "0";
        } else if (!(a instanceof BaseRequestAuth) || !((BaseRequestAuth) a).isEncode()) {
            str9 = "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(appkey);
        sb.append(m);
        sb.append(str6);
        sb.append(str7);
        sb.append(SdkMeta.SDK_VERSION);
        sb.append(str8);
        sb.append(valueOf);
        sb.append("3.0");
        sb.append(str10);
        sb.append(str3 == null ? "" : str3);
        sb.append(str4 == null ? "" : str4);
        String sign = a.getSign(j.b(sb.toString().getBytes()));
        if (TextUtils.isEmpty(str2)) {
            str5 = "";
        } else {
            str5 = str2 + "&";
        }
        return String.format("%s?%sak=%s&av=%s&c=%s&v=%s&s=%s&d=%s&sv=%s&p=%s&t=%s&u=%s&is=%s&k=%s", str, str5, c(appkey), c(str6), c(m), c("3.0"), c(sign), c(str8), SdkMeta.SDK_VERSION, str7, valueOf, "", str10, str9);
    }

    private static String c(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
