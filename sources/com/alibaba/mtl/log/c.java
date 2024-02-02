package com.alibaba.mtl.log;

import java.util.Map;
/* compiled from: UTMCVariables.java */
/* loaded from: classes.dex */
public class c {
    public static final c a = new c();
    private boolean v = false;
    private boolean w = false;
    private String M = null;
    private Map<String, String> t = null;
    private boolean x = false;
    private boolean y = false;
    private String N = null;
    private String O = null;
    private String P = null;
    private boolean z = false;

    public static c a() {
        return a;
    }

    public synchronized void e(String str) {
        this.N = str;
    }

    public synchronized void o() {
        this.y = true;
    }

    public synchronized boolean d() {
        return this.y;
    }

    public synchronized void c(Map<String, String> map) {
        this.t = map;
    }

    /* renamed from: a  reason: collision with other method in class */
    public synchronized Map<String, String> m23a() {
        return this.t;
    }
}
