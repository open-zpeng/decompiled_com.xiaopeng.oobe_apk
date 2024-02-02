package com.xiaopeng.oobe.utils;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
/* loaded from: classes.dex */
public class GsonUtil {
    private static final String TAG = "GsonUtil";
    private static Gson sGSon = new GsonBuilder().registerTypeAdapterFactory(new CustomTypeAdapterFactory()).create();

    public static String toJson(Object obj) {
        return sGSon.toJson(obj);
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        try {
            return (T) sGSon.fromJson(str, (Class<Object>) cls);
        } catch (Exception unused) {
            Log.w(TAG, "not json string: " + str);
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class FloatAdapter extends TypeAdapter<Float> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Float read(JsonReader jsonReader) throws IOException {
            float f = 0.0f;
            if (jsonReader.peek() == JsonToken.STRING || jsonReader.peek() == JsonToken.NUMBER) {
                String nextString = jsonReader.nextString();
                try {
                    f = new BigDecimal(nextString).floatValue();
                } catch (Exception unused) {
                    Log.w(GsonUtil.TAG, "FloatAdapter json error value:" + nextString);
                }
            } else {
                jsonReader.skipValue();
            }
            return Float.valueOf(f);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Float f) throws IOException {
            jsonWriter.value(f);
        }
    }

    /* loaded from: classes.dex */
    public static class DoubleAdapter extends TypeAdapter<Double> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Double read(JsonReader jsonReader) throws IOException {
            double d = 0.0d;
            if (jsonReader.peek() == JsonToken.STRING || jsonReader.peek() == JsonToken.NUMBER) {
                String nextString = jsonReader.nextString();
                try {
                    d = new BigDecimal(nextString).doubleValue();
                } catch (Exception unused) {
                    Log.w(GsonUtil.TAG, "DoubleAdapter json error value:" + nextString);
                }
            } else {
                jsonReader.skipValue();
            }
            return Double.valueOf(d);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Double d) throws IOException {
            jsonWriter.value(d);
        }
    }

    /* loaded from: classes.dex */
    public static class CustomTypeAdapterFactory<T> implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (rawType == Float.class || rawType == Float.TYPE) {
                return new FloatAdapter();
            }
            if (rawType == Double.class || rawType == Double.TYPE) {
                return new DoubleAdapter();
            }
            return null;
        }
    }
}
