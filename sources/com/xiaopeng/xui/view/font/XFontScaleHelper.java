package com.xiaopeng.xui.view.font;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import com.xiaopeng.xui.Xui;
/* loaded from: classes2.dex */
public class XFontScaleHelper {
    private float mComplexToFloat;

    @Nullable
    public static XFontScaleHelper create(@NonNull Resources resources, @DimenRes int i) {
        if (Xui.isFontScaleDynamicChangeEnable()) {
            float complexToFloatForSp = complexToFloatForSp(resources, i);
            if (complexToFloatForSp != -1.0f) {
                return new XFontScaleHelper(complexToFloatForSp);
            }
            return null;
        }
        return null;
    }

    @Nullable
    public static XFontScaleHelper create(@NonNull TypedArray typedArray, @StyleableRes int i) {
        return create(typedArray, i, 0);
    }

    @Nullable
    public static XFontScaleHelper create(@NonNull TypedArray typedArray, @StyleableRes int i, @DimenRes int i2) {
        if (Xui.isFontScaleDynamicChangeEnable()) {
            float complexToFloatForSp = complexToFloatForSp(typedArray, i, i2);
            if (complexToFloatForSp != -1.0f) {
                return new XFontScaleHelper(complexToFloatForSp);
            }
            return null;
        }
        return null;
    }

    private XFontScaleHelper(float f) {
        this.mComplexToFloat = f;
    }

    public float getCurrentFontSize(@NonNull DisplayMetrics displayMetrics) {
        return TypedValue.applyDimension(2, this.mComplexToFloat, displayMetrics);
    }

    public void refreshTextSize(@NonNull TextView textView) {
        textView.setTextSize(this.mComplexToFloat);
    }

    private static float complexToFloatForSp(@NonNull TypedArray typedArray, @StyleableRes int i, @DimenRes int i2) {
        if (typedArray.hasValue(i)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i, typedValue);
            if (typedValue.getComplexUnit() == 2) {
                return TypedValue.complexToFloat(typedValue.data);
            }
            return -1.0f;
        }
        return complexToFloatForSp(typedArray.getResources(), i2);
    }

    private static float complexToFloatForSp(@NonNull Resources resources, @DimenRes int i) {
        if (i != 0) {
            TypedValue typedValue = new TypedValue();
            resources.getValue(i, typedValue, true);
            if (typedValue.getComplexUnit() == 2) {
                return TypedValue.complexToFloat(typedValue.data);
            }
            return -1.0f;
        }
        return -1.0f;
    }
}
