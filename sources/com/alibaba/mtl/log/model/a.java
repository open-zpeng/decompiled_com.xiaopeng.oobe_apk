package com.alibaba.mtl.log.model;

import android.text.TextUtils;
import com.alibaba.mtl.log.d.c;
import com.alibaba.mtl.log.d.h;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.n;
import java.io.UnsupportedEncodingException;
import java.util.Map;
/* compiled from: Log.java */
/* loaded from: classes.dex */
public class a {
    public String X;
    public String Y;
    private String Z;
    public String aa;
    public String ab;
    public int id;
    private Map<String, String> m;
    private String u;
    private String v;
    private String w;
    private String x;

    public a() {
        this.Y = "3";
        this.aa = null;
        this.ab = "";
    }

    public a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        this.Y = "3";
        this.aa = null;
        this.ab = "";
        this.X = str2;
        this.u = str;
        this.v = str3;
        this.w = str4;
        this.x = str5;
        this.m = map;
        this.aa = String.valueOf(System.currentTimeMillis());
        r();
    }

    public String i() {
        try {
            byte[] decode = c.decode(this.Z.getBytes("UTF-8"), 2);
            if (decode != null) {
                return new String(n.a(decode, "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK"));
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public String j() {
        return this.Z;
    }

    public void k(String str) {
        if (str != null) {
            try {
                this.Z = new String(c.encode(n.a(str.getBytes(), "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK"), 2), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public void l(String str) {
        this.Z = str;
    }

    public String toString() {
        return "Log [id=" + this.id + ", eventId=" + this.X + ", index=" + this.ab + "]";
    }

    public void r() {
        if (TextUtils.isEmpty(this.aa)) {
            this.aa = String.valueOf(System.currentTimeMillis());
        }
        String a = h.a(this.u, this.X, this.v, this.w, this.x, this.m, this.ab, this.aa);
        i.a("UTLog", this, a);
        k(a);
    }
}
