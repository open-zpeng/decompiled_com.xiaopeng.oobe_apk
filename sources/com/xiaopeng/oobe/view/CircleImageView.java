package com.xiaopeng.oobe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import java.math.BigDecimal;
@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class CircleImageView extends ImageView {
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private Bitmap mBitmap;
    private int mBitmapHeight;
    private final Paint mBitmapPaint;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private final Paint mBorderPaint;
    private final RectF mBorderRect;
    private final Paint mCircleBackgroundPaint;
    private ColorFilter mColorFilter;
    private double mDrawableRadius;
    private final RectF mDrawableRect;
    private boolean mReady;
    private boolean mSetupPending;
    private final Matrix mShaderMatrix;
    private static final ImageView.ScaleType SCALE_TYPE = ImageView.ScaleType.CENTER_CROP;
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;

    public CircleImageView(Context context) {
        super(context);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mCircleBackgroundPaint = new Paint();
        init();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mCircleBackgroundPaint = new Paint();
        init();
    }

    private void init() {
        super.setScaleType(SCALE_TYPE);
        this.mReady = true;
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new OutlineProvider());
        }
        if (this.mSetupPending) {
            setup();
            this.mSetupPending = false;
        }
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    @Override // android.widget.ImageView
    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mBitmap == null) {
            return;
        }
        canvas.drawCircle(this.mDrawableRect.centerX(), this.mDrawableRect.centerY(), (float) this.mDrawableRadius, this.mBitmapPaint);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setup();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        setup();
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        setup();
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        initializeBitmap();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        initializeBitmap();
    }

    @Override // android.widget.ImageView
    public void setImageResource(@DrawableRes int i) {
        super.setImageResource(i);
        initializeBitmap();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        initializeBitmap();
    }

    @Override // android.widget.ImageView
    public ColorFilter getColorFilter() {
        return this.mColorFilter;
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter == this.mColorFilter) {
            return;
        }
        this.mColorFilter = colorFilter;
        applyColorFilter();
        invalidate();
    }

    private void applyColorFilter() {
        this.mBitmapPaint.setColorFilter(this.mColorFilter);
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap createBitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            if (drawable instanceof ColorDrawable) {
                createBitmap = Bitmap.createBitmap(2, 2, BITMAP_CONFIG);
            } else {
                createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initializeBitmap() {
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private void setup() {
        if (!this.mReady) {
            this.mSetupPending = true;
        } else if (getWidth() == 0 && getHeight() == 0) {
        } else {
            Bitmap bitmap = this.mBitmap;
            if (bitmap == null) {
                invalidate();
                return;
            }
            this.mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.mBitmapPaint.setAntiAlias(true);
            this.mBitmapPaint.setShader(this.mBitmapShader);
            this.mBorderPaint.setStyle(Paint.Style.STROKE);
            this.mBorderPaint.setAntiAlias(true);
            this.mBorderPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mCircleBackgroundPaint.setStyle(Paint.Style.FILL);
            this.mCircleBackgroundPaint.setAntiAlias(true);
            this.mCircleBackgroundPaint.setColor(0);
            this.mBitmapHeight = this.mBitmap.getHeight();
            this.mBitmapWidth = this.mBitmap.getWidth();
            this.mBorderRect.set(calculateBounds());
            this.mDrawableRect.set(this.mBorderRect);
            this.mDrawableRadius = Math.min(this.mDrawableRect.height() / 2.0d, this.mDrawableRect.width() / 2.0d);
            applyColorFilter();
            updateShaderMatrix();
            invalidate();
        }
    }

    private RectF calculateBounds() {
        int width;
        int height;
        float min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        int i = (int) min;
        float paddingLeft = (float) (getPaddingLeft() + ((width - i) / 2.0d));
        float paddingTop = (float) (getPaddingTop() + ((height - i) / 2.0d));
        double d = min;
        return new RectF(paddingLeft, paddingTop, BigDecimal.valueOf(paddingLeft).add(BigDecimal.valueOf(d)).floatValue(), BigDecimal.valueOf(paddingTop).add(BigDecimal.valueOf(d)).floatValue());
    }

    private void updateShaderMatrix() {
        float width;
        float f;
        this.mShaderMatrix.set(null);
        float f2 = 0.0f;
        if (this.mBitmapWidth * ((int) this.mDrawableRect.height()) > ((int) this.mDrawableRect.width()) * this.mBitmapHeight) {
            width = (float) (this.mDrawableRect.height() / this.mBitmapHeight);
            f = (float) ((this.mDrawableRect.width() - (this.mBitmapWidth * width)) * 0.5d);
        } else {
            width = (float) (this.mDrawableRect.width() / this.mBitmapWidth);
            f2 = (float) ((this.mDrawableRect.height() - (this.mBitmapHeight * width)) * 0.5d);
            f = 0.0f;
        }
        this.mShaderMatrix.setScale(width, width);
        this.mShaderMatrix.postTranslate((float) (f + 0.5d + this.mDrawableRect.left), (float) (f2 + 0.5d + this.mDrawableRect.top));
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 21)
    /* loaded from: classes.dex */
    public class OutlineProvider extends ViewOutlineProvider {
        private OutlineProvider() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            Rect rect = new Rect();
            CircleImageView.this.mBorderRect.roundOut(rect);
            outline.setRoundRect(rect, (float) (rect.width() / 2.0d));
        }
    }
}
