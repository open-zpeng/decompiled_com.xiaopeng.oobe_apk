package com.xiaopeng.xui.drawable;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.utils.XLogUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes2.dex */
public class XPressableRoundRectDrawable extends Drawable implements Animatable {
    private static final int DEFAULT_COLOR = -857743131;
    private static final int DEFAULT_RADIUS = 8;
    private static final String TAG = "XPressableRoundRectDrawable";
    private ColorStateList mFillColorList;
    private int mHeight;
    private ValueAnimator mHeightAnimator;
    private boolean mIsPressed;
    private int mRadius;
    private int mValueFrom;
    private int mValueTo;
    private int mWidth;
    private final Paint mFillPaint = new Paint(1);
    private final RectF mRect = new RectF();

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        TypedArray obtainAttributes;
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        if (theme != null) {
            obtainAttributes = theme.obtainStyledAttributes(attributeSet, R.styleable.XPressableRoundRectDrawable, 0, 0);
        } else {
            obtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.XPressableRoundRectDrawable);
        }
        this.mFillColorList = obtainAttributes.getColorStateList(R.styleable.XPressableRoundRectDrawable_android_color);
        if (this.mFillColorList == null) {
            this.mFillColorList = ColorStateList.valueOf(DEFAULT_COLOR);
        }
        this.mFillPaint.setColor(this.mFillColorList.getDefaultColor());
        this.mRadius = obtainAttributes.getDimensionPixelSize(R.styleable.XPressableRoundRectDrawable_android_radius, 8);
        this.mValueFrom = obtainAttributes.getDimensionPixelSize(R.styleable.XPressableRoundRectDrawable_android_valueFrom, 0);
        this.mValueTo = obtainAttributes.getDimensionPixelSize(R.styleable.XPressableRoundRectDrawable_android_valueTo, 0);
        this.mWidth = obtainAttributes.getDimensionPixelSize(R.styleable.XPressableRoundRectDrawable_android_width, 0);
        this.mHeight = obtainAttributes.getDimensionPixelSize(R.styleable.XPressableRoundRectDrawable_android_height, 0);
        obtainAttributes.recycle();
    }

    private void animateHeight(int i, int i2) {
        ValueAnimator valueAnimator = this.mHeightAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mHeightAnimator.removeAllUpdateListeners();
        }
        this.mHeightAnimator = ValueAnimator.ofInt(i, i2);
        this.mHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.xui.drawable.XPressableRoundRectDrawable.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                XPressableRoundRectDrawable.this.setHeight(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                XPressableRoundRectDrawable.this.invalidateSelf();
            }
        });
        this.mHeightAnimator.setDuration(100L);
        this.mHeightAnimator.start();
    }

    public void setHeight(int i) {
        float centerY = getBounds().centerY();
        float f = i / 2.0f;
        float f2 = centerY - f;
        float f3 = centerY + f;
        RectF rectF = this.mRect;
        rectF.top = f2;
        rectF.bottom = f3;
        XLogUtils.d(TAG, "setHeight:" + i + ", result:" + this.mRect.height());
    }

    public void setWidth(int i) {
        float centerX = getBounds().centerX();
        float f = i / 2.0f;
        float f2 = centerX - f;
        float f3 = centerX + f;
        RectF rectF = this.mRect;
        rectF.left = f2;
        rectF.right = f3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        XLogUtils.d(TAG, "onBoundsChange: " + rect);
        this.mRect.set(rect);
        int i = this.mWidth;
        if (i > 0) {
            setWidth(i);
        }
        int i2 = this.mHeight;
        if (i2 != 0) {
            setHeight(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        RectF rectF = this.mRect;
        int i = this.mRadius;
        canvas.drawRoundRect(rectF, i, i, this.mFillPaint);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        this.mFillPaint.setColor(this.mFillColorList.getColorForState(iArr, DEFAULT_COLOR));
        if (this.mValueFrom != this.mValueTo) {
            int length = iArr.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (iArr[i] == 16842919) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (this.mIsPressed != z) {
                this.mIsPressed = z;
                if (this.mIsPressed) {
                    animateHeight(this.mValueFrom, this.mValueTo);
                } else {
                    animateHeight(this.mValueTo, this.mValueFrom);
                }
                return true;
            }
        }
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        ValueAnimator valueAnimator = this.mHeightAnimator;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        ValueAnimator valueAnimator = this.mHeightAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        ValueAnimator valueAnimator = this.mHeightAnimator;
        if (valueAnimator != null) {
            return valueAnimator.isRunning();
        }
        return false;
    }
}
