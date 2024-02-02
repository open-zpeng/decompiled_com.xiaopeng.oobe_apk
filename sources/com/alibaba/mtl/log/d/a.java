package com.alibaba.mtl.log.d;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ApiResponseParse.java */
/* loaded from: classes.dex */
public class a {
    public static C0008a a(String str) {
        C0008a c0008a = new C0008a();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("success")) {
                String string = jSONObject.getString("success");
                if (!TextUtils.isEmpty(string) && string.equals("success")) {
                    c0008a.I = true;
                }
            }
            if (jSONObject.has("ret")) {
                c0008a.ah = jSONObject.getString("ret");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return c0008a;
    }

    /* compiled from: ApiResponseParse.java */
    /* renamed from: com.alibaba.mtl.log.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0008a {
        public static C0008a a = new C0008a();
        public boolean I = false;
        public String ah = null;

        public boolean g() {
            return "E0102".equalsIgnoreCase(this.ah);
        }

        public boolean h() {
            return "E0111".equalsIgnoreCase(this.ah) || "E0112".equalsIgnoreCase(this.ah);
        }
    }
}
