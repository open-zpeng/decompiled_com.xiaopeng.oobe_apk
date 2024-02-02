package com.google.android.material.timepicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.Dimension;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ClockHandView extends View {
    private static final int ANIMATION_DURATION = 200;
    private boolean animatingOnTouchUp;
    private final float centerDotRadius;
    private boolean changedDuringTouch;
    private int circleRadius;
    private double degRad;
    private float downX;
    private float downY;
    private boolean isInTapRegion;
    private final List<OnRotateListener> listeners;
    private OnActionUpListener onActionUpListener;
    private float originalDeg;
    private final Paint paint;
    private ValueAnimator rotationAnimator;
    private int scaledTouchSlop;
    private final RectF selectorBox;
    private final int selectorRadius;
    @Px
    private final int selectorStrokeWidth;

    /* loaded from: classes.dex */
    public interface OnActionUpListener {
        void onActionUp(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z);
    }

    /* loaded from: classes.dex */
    public interface OnRotateListener {
        void onRotate(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z);
    }

    public ClockHandView(Context context) {
        this(context, null);
    }

    public ClockHandView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialClockStyle);
    }

    public ClockHandView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listeners = new ArrayList();
        this.paint = new Paint();
        this.selectorBox = new RectF();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockHandView, i, R.style.Widget_MaterialComponents_TimePicker_Clock);
        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_materialCircleRadius, 0);
        this.selectorRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_selectorSize, 0);
        Resources resources = getResources();
        this.selectorStrokeWidth = resources.getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width);
        this.centerDotRadius = resources.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius);
        int color = obtainStyledAttributes.getColor(R.styleable.ClockHandView_clockHandColor, 0);
        this.paint.setAntiAlias(true);
        this.paint.setColor(color);
        setHandRotation(0.0f);
        this.scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        ViewCompat.setImportantForAccessibility(this, 2);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setHandRotation(getHandRotation());
    }

    public void setHandRotation(@FloatRange(from = 0.0d, to = 360.0d) float f) {
        setHandRotation(f, false);
    }

    public void setHandRotation(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z) {
        ValueAnimator valueAnimator = this.rotationAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z) {
            setHandRotationInternal(f, false);
            return;
        }
        Pair<Float, Float> valuesForAnimation = getValuesForAnimation(f);
        this.rotationAnimator = ValueAnimator.ofFloat(((Float) valuesForAnimation.first).floatValue(), ((Float) valuesForAnimation.second).floatValue());
        this.rotationAnimator.setDuration(200L);
        this.rotationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.timepicker.ClockHandView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ClockHandView.this.setHandRotationInternal(((Float) valueAnimator2.getAnimatedValue()).floatValue(), true);
            }
        });
        this.rotationAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.timepicker.ClockHandView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                animator.end();
            }
        });
        this.rotationAnimator.start();
    }

    private Pair<Float, Float> getValuesForAnimation(float f) {
        float handRotation = getHandRotation();
        if (Math.abs(handRotation - f) > 180.0f) {
            if (handRotation > 180.0f && f < 180.0f) {
                f += 360.0f;
            }
            if (handRotation < 180.0f && f > 180.0f) {
                handRotation += 360.0f;
            }
        }
        return new Pair<>(Float.valueOf(handRotation), Float.valueOf(f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHandRotationInternal(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z) {
        float f2 = f % 360.0f;
        this.originalDeg = f2;
        this.degRad = Math.toRadians(this.originalDeg - 90.0f);
        float width = (getWidth() / 2) + (this.circleRadius * ((float) Math.cos(this.degRad)));
        float height = (getHeight() / 2) + (this.circleRadius * ((float) Math.sin(this.degRad)));
        RectF rectF = this.selectorBox;
        int i = this.selectorRadius;
        rectF.set(width - i, height - i, width + i, height + i);
        for (OnRotateListener onRotateListener : this.listeners) {
            onRotateListener.onRotate(f2, z);
        }
        invalidate();
    }

    public void setAnimateOnTouchUp(boolean z) {
        this.animatingOnTouchUp = z;
    }

    public void addOnRotateListener(OnRotateListener onRotateListener) {
        this.listeners.add(onRotateListener);
    }

    public void setOnActionUpListener(OnActionUpListener onActionUpListener) {
        this.onActionUpListener = onActionUpListener;
    }

    @FloatRange(from = 0.0d, to = 360.0d)
    public float getHandRotation() {
        return this.originalDeg;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSelector(canvas);
    }

    private void drawSelector(Canvas canvas) {
        int width;
        int height = getHeight() / 2;
        float width2 = getWidth() / 2;
        float f = height;
        this.paint.setStrokeWidth(0.0f);
        canvas.drawCircle((this.circleRadius * ((float) Math.cos(this.degRad))) + width2, (this.circleRadius * ((float) Math.sin(this.degRad))) + f, this.selectorRadius, this.paint);
        double sin = Math.sin(this.degRad);
        double cos = Math.cos(this.degRad);
        this.paint.setStrokeWidth(this.selectorStrokeWidth);
        canvas.drawLine(width2, f, width + ((int) (cos * r6)), height + ((int) (r6 * sin)), this.paint);
        canvas.drawCircle(width2, f, this.centerDotRadius, this.paint);
    }

    public RectF getCurrentSelectorBox() {
        return this.selectorBox;
    }

    public int getSelectorRadius() {
        return this.selectorRadius;
    }

    public void setCircleRadius(@Dimension int i) {
        this.circleRadius = i;
        invalidate();
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        boolean z3;
        OnActionUpListener onActionUpListener;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (actionMasked == 0) {
            this.downX = x;
            this.downY = y;
            this.isInTapRegion = true;
            this.changedDuringTouch = false;
            z = false;
            z2 = false;
            z3 = true;
        } else if (actionMasked == 1 || actionMasked == 2) {
            int i = (int) (x - this.downX);
            int i2 = (int) (y - this.downY);
            this.isInTapRegion = (i * i) + (i2 * i2) > this.scaledTouchSlop;
            boolean z4 = this.changedDuringTouch;
            z = actionMasked == 1;
            z3 = false;
            z2 = z4;
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        this.changedDuringTouch = handleTouchInput(x, y, z2, z3, z) | this.changedDuringTouch;
        if (this.changedDuringTouch && z && (onActionUpListener = this.onActionUpListener) != null) {
            onActionUpListener.onActionUp(getDegreesFromXY(x, y), this.isInTapRegion);
        }
        return true;
    }

    private boolean handleTouchInput(float f, float f2, boolean z, boolean z2, boolean z3) {
        float degreesFromXY = getDegreesFromXY(f, f2);
        boolean z4 = false;
        boolean z5 = getHandRotation() != degreesFromXY;
        if (z2 && z5) {
            return true;
        }
        if (z5 || z) {
            if (z3 && this.animatingOnTouchUp) {
                z4 = true;
            }
            setHandRotation(degreesFromXY, z4);
            return true;
        }
        return false;
    }

    private int getDegreesFromXY(float f, float f2) {
        int degrees = ((int) Math.toDegrees(Math.atan2(f2 - (getHeight() / 2), f - (getWidth() / 2)))) + 90;
        return degrees < 0 ? degrees + 360 : degrees;
    }
}
