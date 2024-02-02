package com.airbnb.lottie.animation.keyframe;

import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
/* loaded from: classes.dex */
public class DropShadowKeyframeAnimation implements BaseKeyframeAnimation.AnimationListener {
    private static final double DEG_TO_RAD = 0.017453292519943295d;
    private final BaseKeyframeAnimation<Integer, Integer> color;
    private final BaseKeyframeAnimation<Float, Float> direction;
    private final BaseKeyframeAnimation<Float, Float> distance;
    private boolean isDirty = true;
    private final BaseKeyframeAnimation.AnimationListener listener;
    private final BaseKeyframeAnimation<Float, Float> opacity;
    private final BaseKeyframeAnimation<Float, Float> radius;

    public DropShadowKeyframeAnimation(BaseKeyframeAnimation.AnimationListener animationListener, BaseLayer baseLayer, DropShadowEffect dropShadowEffect) {
        this.listener = animationListener;
        this.color = dropShadowEffect.getColor().createAnimation();
        this.color.addUpdateListener(this);
        baseLayer.addAnimation(this.color);
        this.opacity = dropShadowEffect.getOpacity().createAnimation();
        this.opacity.addUpdateListener(this);
        baseLayer.addAnimation(this.opacity);
        this.direction = dropShadowEffect.getDirection().createAnimation();
        this.direction.addUpdateListener(this);
        baseLayer.addAnimation(this.direction);
        this.distance = dropShadowEffect.getDistance().createAnimation();
        this.distance.addUpdateListener(this);
        baseLayer.addAnimation(this.distance);
        this.radius = dropShadowEffect.getRadius().createAnimation();
        this.radius.addUpdateListener(this);
        baseLayer.addAnimation(this.radius);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.isDirty = true;
        this.listener.onValueChanged();
    }

    public void applyTo(Paint paint) {
        if (this.isDirty) {
            this.isDirty = false;
            double floatValue = this.direction.getValue().floatValue() * DEG_TO_RAD;
            float floatValue2 = this.distance.getValue().floatValue();
            float sin = ((float) Math.sin(floatValue)) * floatValue2;
            float cos = ((float) Math.cos(floatValue + 3.141592653589793d)) * floatValue2;
            int intValue = this.color.getValue().intValue();
            paint.setShadowLayer(this.radius.getValue().floatValue(), sin, cos, Color.argb(Math.round(this.opacity.getValue().floatValue()), Color.red(intValue), Color.green(intValue), Color.blue(intValue)));
        }
    }

    public void setColorCallback(@Nullable LottieValueCallback<Integer> lottieValueCallback) {
        this.color.setValueCallback(lottieValueCallback);
    }

    public void setOpacityCallback(@Nullable final LottieValueCallback<Float> lottieValueCallback) {
        if (lottieValueCallback == null) {
            this.opacity.setValueCallback(null);
        } else {
            this.opacity.setValueCallback(new LottieValueCallback<Float>() { // from class: com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.airbnb.lottie.value.LottieValueCallback
                @Nullable
                public Float getValue(LottieFrameInfo<Float> lottieFrameInfo) {
                    Float f = (Float) lottieValueCallback.getValue(lottieFrameInfo);
                    if (f == null) {
                        return null;
                    }
                    return Float.valueOf(f.floatValue() * 2.55f);
                }
            });
        }
    }

    public void setDirectionCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        this.direction.setValueCallback(lottieValueCallback);
    }

    public void setDistanceCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        this.distance.setValueCallback(lottieValueCallback);
    }

    public void setRadiusCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        this.radius.setValueCallback(lottieValueCallback);
    }
}
