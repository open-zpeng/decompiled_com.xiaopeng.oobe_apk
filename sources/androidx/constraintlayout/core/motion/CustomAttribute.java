package androidx.constraintlayout.core.motion;

import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public class CustomAttribute {
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    private boolean mMethod;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    /* loaded from: classes.dex */
    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE
    }

    private static int clamp(int i) {
        int i2 = (i & (~(i >> 31))) - 255;
        return (i2 & (i2 >> 31)) + 255;
    }

    public static int hsvToRgb(float f, float f2, float f3) {
        float f4 = f * 6.0f;
        int i = (int) f4;
        float f5 = f4 - i;
        float f6 = f3 * 255.0f;
        int i2 = (int) (((1.0f - f2) * f6) + 0.5f);
        int i3 = (int) (((1.0f - (f5 * f2)) * f6) + 0.5f);
        int i4 = (int) (((1.0f - ((1.0f - f5) * f2)) * f6) + 0.5f);
        int i5 = (int) (f6 + 0.5f);
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return 0;
                            }
                            return ((i5 << 16) + (i2 << 8) + i3) | ViewCompat.MEASURED_STATE_MASK;
                        }
                        return ((i4 << 16) + (i2 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
                    }
                    return ((i2 << 16) + (i3 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
                }
                return ((i2 << 16) + (i5 << 8) + i4) | ViewCompat.MEASURED_STATE_MASK;
            }
            return ((i3 << 16) + (i5 << 8) + i2) | ViewCompat.MEASURED_STATE_MASK;
        }
        return ((i5 << 16) + (i4 << 8) + i2) | ViewCompat.MEASURED_STATE_MASK;
    }

    public AttributeType getType() {
        return this.mType;
    }

    public boolean isContinuous() {
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()];
        return (i == 1 || i == 2 || i == 3) ? false : true;
    }

    public void setFloatValue(float f) {
        this.mFloatValue = f;
    }

    public void setColorValue(int i) {
        this.mColorValue = i;
    }

    public void setIntValue(int i) {
        this.mIntegerValue = i;
    }

    public void setStringValue(String str) {
        this.mStringValue = str;
    }

    public int numberOfInterpolatedValues() {
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()];
        return (i == 4 || i == 5) ? 4 : 1;
    }

    public float getValueToInterpolate() {
        switch (this.mType) {
            case BOOLEAN_TYPE:
                return this.mBooleanValue ? 1.0f : 0.0f;
            case STRING_TYPE:
                throw new RuntimeException("Cannot interpolate String");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case INT_TYPE:
                return this.mIntegerValue;
            case FLOAT_TYPE:
                return this.mFloatValue;
            case DIMENSION_TYPE:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] fArr) {
        switch (this.mType) {
            case BOOLEAN_TYPE:
                fArr[0] = this.mBooleanValue ? 1.0f : 0.0f;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                int i = this.mColorValue;
                float pow = (float) Math.pow(((i >> 16) & 255) / 255.0f, 2.2d);
                float pow2 = (float) Math.pow(((i >> 8) & 255) / 255.0f, 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = (float) Math.pow((i & 255) / 255.0f, 2.2d);
                fArr[3] = ((i >> 24) & 255) / 255.0f;
                return;
            case INT_TYPE:
                fArr[0] = this.mIntegerValue;
                return;
            case FLOAT_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            case DIMENSION_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public void setValue(float[] fArr) {
        switch (this.mType) {
            case REFERENCE_TYPE:
            case INT_TYPE:
                this.mIntegerValue = (int) fArr[0];
                return;
            case BOOLEAN_TYPE:
                this.mBooleanValue = ((double) fArr[0]) > 0.5d;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.mColorValue = hsvToRgb(fArr[0], fArr[1], fArr[2]);
                this.mColorValue = (clamp((int) (fArr[3] * 255.0f)) << 24) | (this.mColorValue & 16777215);
                return;
            case FLOAT_TYPE:
                this.mFloatValue = fArr[0];
                return;
            case DIMENSION_TYPE:
                this.mFloatValue = fArr[0];
                return;
            default:
                return;
        }
    }

    public boolean diff(CustomAttribute customAttribute) {
        if (customAttribute == null || this.mType != customAttribute.mType) {
            return false;
        }
        switch (this.mType) {
            case REFERENCE_TYPE:
            case INT_TYPE:
                return this.mIntegerValue == customAttribute.mIntegerValue;
            case BOOLEAN_TYPE:
                return this.mBooleanValue == customAttribute.mBooleanValue;
            case STRING_TYPE:
                return this.mIntegerValue == customAttribute.mIntegerValue;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                return this.mColorValue == customAttribute.mColorValue;
            case FLOAT_TYPE:
                return this.mFloatValue == customAttribute.mFloatValue;
            case DIMENSION_TYPE:
                return this.mFloatValue == customAttribute.mFloatValue;
            default:
                return false;
        }
    }

    public CustomAttribute(String str, AttributeType attributeType) {
        this.mMethod = false;
        this.mName = str;
        this.mType = attributeType;
    }

    public CustomAttribute(String str, AttributeType attributeType, Object obj, boolean z) {
        this.mMethod = false;
        this.mName = str;
        this.mType = attributeType;
        this.mMethod = z;
        setValue(obj);
    }

    public CustomAttribute(CustomAttribute customAttribute, Object obj) {
        this.mMethod = false;
        this.mName = customAttribute.mName;
        this.mType = customAttribute.mType;
        setValue(obj);
    }

    public void setValue(Object obj) {
        switch (this.mType) {
            case REFERENCE_TYPE:
            case INT_TYPE:
                this.mIntegerValue = ((Integer) obj).intValue();
                return;
            case BOOLEAN_TYPE:
                this.mBooleanValue = ((Boolean) obj).booleanValue();
                return;
            case STRING_TYPE:
                this.mStringValue = (String) obj;
                return;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.mColorValue = ((Integer) obj).intValue();
                return;
            case FLOAT_TYPE:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            case DIMENSION_TYPE:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            default:
                return;
        }
    }
}
