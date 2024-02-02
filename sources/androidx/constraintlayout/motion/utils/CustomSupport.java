package androidx.constraintlayout.motion.utils;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class CustomSupport {
    private static final String TAG = "CustomSupport";

    private static int clamp(int c) {
        int i = (c & (~(c >> 31))) - 255;
        return (i & (i >> 31)) + 255;
    }

    public static void setInterpolatedValue(ConstraintAttribute att, View view, float[] value) {
        Class<?> cls = view.getClass();
        String str = "set" + att.getName();
        try {
            boolean z = true;
            switch (att.getType()) {
                case INT_TYPE:
                    cls.getMethod(str, Integer.TYPE).invoke(view, Integer.valueOf((int) value[0]));
                    return;
                case FLOAT_TYPE:
                    cls.getMethod(str, Float.TYPE).invoke(view, Float.valueOf(value[0]));
                    return;
                case COLOR_DRAWABLE_TYPE:
                    Method method = cls.getMethod(str, Drawable.class);
                    int clamp = clamp((int) (((float) Math.pow(value[0], 0.45454545454545453d)) * 255.0f));
                    int clamp2 = clamp((int) (((float) Math.pow(value[1], 0.45454545454545453d)) * 255.0f));
                    ColorDrawable colorDrawable = new ColorDrawable();
                    colorDrawable.setColor((clamp << 16) | (clamp((int) (value[3] * 255.0f)) << 24) | (clamp2 << 8) | clamp((int) (((float) Math.pow(value[2], 0.45454545454545453d)) * 255.0f)));
                    method.invoke(view, colorDrawable);
                    return;
                case COLOR_TYPE:
                    cls.getMethod(str, Integer.TYPE).invoke(view, Integer.valueOf((clamp((int) (((float) Math.pow(value[0], 0.45454545454545453d)) * 255.0f)) << 16) | (clamp((int) (value[3] * 255.0f)) << 24) | (clamp((int) (((float) Math.pow(value[1], 0.45454545454545453d)) * 255.0f)) << 8) | clamp((int) (((float) Math.pow(value[2], 0.45454545454545453d)) * 255.0f))));
                    return;
                case STRING_TYPE:
                    throw new RuntimeException("unable to interpolate strings " + att.getName());
                case BOOLEAN_TYPE:
                    Method method2 = cls.getMethod(str, Boolean.TYPE);
                    Object[] objArr = new Object[1];
                    if (value[0] <= 0.5f) {
                        z = false;
                    }
                    objArr[0] = Boolean.valueOf(z);
                    method2.invoke(view, objArr);
                    return;
                case DIMENSION_TYPE:
                    cls.getMethod(str, Float.TYPE).invoke(view, Float.valueOf(value[0]));
                    return;
                default:
                    return;
            }
        } catch (IllegalAccessException e) {
            Log.e(TAG, "cannot access method " + str + " on View \"" + Debug.getName(view) + "\"");
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "no method " + str + " on View \"" + Debug.getName(view) + "\"");
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }
}
