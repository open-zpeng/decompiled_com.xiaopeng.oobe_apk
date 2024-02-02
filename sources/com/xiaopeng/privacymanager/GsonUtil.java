package com.xiaopeng.privacymanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
/* loaded from: classes.dex */
public class GsonUtil {
    static Gson gson = newGsonBuilder().create();

    public static final GsonBuilder newGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        gsonBuilder.disableHtmlEscaping();
        return gsonBuilder;
    }

    public static final String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static final <T> T fromJson(String str, Class<T> cls) {
        return (T) gson.fromJson(str, (Class<Object>) cls);
    }

    public static final <T> T fromJson(String str, TypeToken<T> typeToken) {
        return (T) gson.fromJson(str, typeToken.getType());
    }
}
