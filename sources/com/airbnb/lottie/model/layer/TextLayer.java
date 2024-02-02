package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class TextLayer extends BaseLayer {
    private final LongSparseArray<String> codePointCache;
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> colorAnimation;
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> colorCallbackAnimation;
    private final LottieComposition composition;
    private final Map<FontCharacter, List<ContentGroup>> contentsForCharacter;
    private final Paint fillPaint;
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix;
    private final RectF rectF;
    private final StringBuilder stringBuilder;
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> strokeColorAnimation;
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> strokeColorCallbackAnimation;
    private final Paint strokePaint;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> strokeWidthAnimation;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> strokeWidthCallbackAnimation;
    private final TextKeyframeAnimation textAnimation;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> textSizeCallbackAnimation;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> trackingAnimation;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> trackingCallbackAnimation;
    @Nullable
    private BaseKeyframeAnimation<Typeface, Typeface> typefaceCallbackAnimation;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        this.stringBuilder = new StringBuilder(2);
        this.rectF = new RectF();
        this.matrix = new Matrix();
        this.fillPaint = new Paint(1) { // from class: com.airbnb.lottie.model.layer.TextLayer.1
            {
                setStyle(Paint.Style.FILL);
            }
        };
        this.strokePaint = new Paint(1) { // from class: com.airbnb.lottie.model.layer.TextLayer.2
            {
                setStyle(Paint.Style.STROKE);
            }
        };
        this.contentsForCharacter = new HashMap();
        this.codePointCache = new LongSparseArray<>();
        this.lottieDrawable = lottieDrawable;
        this.composition = layer.getComposition();
        this.textAnimation = layer.getText().createAnimation();
        this.textAnimation.addUpdateListener(this);
        addAnimation(this.textAnimation);
        AnimatableTextProperties textProperties = layer.getTextProperties();
        if (textProperties != null && textProperties.color != null) {
            this.colorAnimation = textProperties.color.createAnimation();
            this.colorAnimation.addUpdateListener(this);
            addAnimation(this.colorAnimation);
        }
        if (textProperties != null && textProperties.stroke != null) {
            this.strokeColorAnimation = textProperties.stroke.createAnimation();
            this.strokeColorAnimation.addUpdateListener(this);
            addAnimation(this.strokeColorAnimation);
        }
        if (textProperties != null && textProperties.strokeWidth != null) {
            this.strokeWidthAnimation = textProperties.strokeWidth.createAnimation();
            this.strokeWidthAnimation.addUpdateListener(this);
            addAnimation(this.strokeWidthAnimation);
        }
        if (textProperties == null || textProperties.tracking == null) {
            return;
        }
        this.trackingAnimation = textProperties.tracking.createAnimation();
        this.trackingAnimation.addUpdateListener(this);
        addAnimation(this.trackingAnimation);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        rectF.set(0.0f, 0.0f, this.composition.getBounds().width(), this.composition.getBounds().height());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    void drawLayer(Canvas canvas, Matrix matrix, int i) {
        canvas.save();
        if (!this.lottieDrawable.useTextGlyphs()) {
            canvas.concat(matrix);
        }
        DocumentData value = this.textAnimation.getValue();
        Font font = this.composition.getFonts().get(value.fontName);
        if (font == null) {
            canvas.restore();
            return;
        }
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.colorCallbackAnimation;
        if (baseKeyframeAnimation != null) {
            this.fillPaint.setColor(baseKeyframeAnimation.getValue().intValue());
        } else {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.colorAnimation;
            if (baseKeyframeAnimation2 != null) {
                this.fillPaint.setColor(baseKeyframeAnimation2.getValue().intValue());
            } else {
                this.fillPaint.setColor(value.color);
            }
        }
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation3 = this.strokeColorCallbackAnimation;
        if (baseKeyframeAnimation3 != null) {
            this.strokePaint.setColor(baseKeyframeAnimation3.getValue().intValue());
        } else {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation4 = this.strokeColorAnimation;
            if (baseKeyframeAnimation4 != null) {
                this.strokePaint.setColor(baseKeyframeAnimation4.getValue().intValue());
            } else {
                this.strokePaint.setColor(value.strokeColor);
            }
        }
        int intValue = ((this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().getValue().intValue()) * 255) / 100;
        this.fillPaint.setAlpha(intValue);
        this.strokePaint.setAlpha(intValue);
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation5 = this.strokeWidthCallbackAnimation;
        if (baseKeyframeAnimation5 != null) {
            this.strokePaint.setStrokeWidth(baseKeyframeAnimation5.getValue().floatValue());
        } else {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation6 = this.strokeWidthAnimation;
            if (baseKeyframeAnimation6 != null) {
                this.strokePaint.setStrokeWidth(baseKeyframeAnimation6.getValue().floatValue());
            } else {
                this.strokePaint.setStrokeWidth(value.strokeWidth * Utils.dpScale() * Utils.getScale(matrix));
            }
        }
        if (this.lottieDrawable.useTextGlyphs()) {
            drawTextGlyphs(value, matrix, font, canvas);
        } else {
            drawTextWithFont(value, font, canvas);
        }
        canvas.restore();
    }

    private void drawTextGlyphs(DocumentData documentData, Matrix matrix, Font font, Canvas canvas) {
        float f;
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation = this.textSizeCallbackAnimation;
        if (baseKeyframeAnimation != null) {
            f = baseKeyframeAnimation.getValue().floatValue();
        } else {
            f = documentData.size;
        }
        float f2 = f / 100.0f;
        float scale = Utils.getScale(matrix);
        String str = documentData.text;
        float dpScale = documentData.lineHeight * Utils.dpScale();
        List<String> textLines = getTextLines(str);
        int size = textLines.size();
        for (int i = 0; i < size; i++) {
            String str2 = textLines.get(i);
            float textLineWidthForGlyphs = getTextLineWidthForGlyphs(str2, font, f2, scale);
            canvas.save();
            applyJustification(documentData.justification, canvas, textLineWidthForGlyphs);
            canvas.translate(0.0f, (i * dpScale) - (((size - 1) * dpScale) / 2.0f));
            drawGlyphTextLine(str2, documentData, matrix, font, canvas, scale, f2);
            canvas.restore();
        }
    }

    private void drawGlyphTextLine(String str, DocumentData documentData, Matrix matrix, Font font, Canvas canvas, float f, float f2) {
        float floatValue;
        for (int i = 0; i < str.length(); i++) {
            FontCharacter fontCharacter = this.composition.getCharacters().get(FontCharacter.hashFor(str.charAt(i), font.getFamily(), font.getStyle()));
            if (fontCharacter != null) {
                drawCharacterAsGlyph(fontCharacter, matrix, f2, documentData, canvas);
                float width = ((float) fontCharacter.getWidth()) * f2 * Utils.dpScale() * f;
                float f3 = documentData.tracking / 10.0f;
                BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation = this.trackingCallbackAnimation;
                if (baseKeyframeAnimation != null) {
                    floatValue = baseKeyframeAnimation.getValue().floatValue();
                } else {
                    BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.trackingAnimation;
                    if (baseKeyframeAnimation2 != null) {
                        floatValue = baseKeyframeAnimation2.getValue().floatValue();
                    }
                    canvas.translate(width + (f3 * f), 0.0f);
                }
                f3 += floatValue;
                canvas.translate(width + (f3 * f), 0.0f);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x008f A[LOOP:0: B:20:0x008d->B:21:0x008f, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void drawTextWithFont(com.airbnb.lottie.model.DocumentData r8, com.airbnb.lottie.model.Font r9, android.graphics.Canvas r10) {
        /*
            r7 = this;
            android.graphics.Typeface r9 = r7.getTypeface(r9)
            if (r9 != 0) goto L7
            return
        L7:
            java.lang.String r0 = r8.text
            com.airbnb.lottie.LottieDrawable r1 = r7.lottieDrawable
            com.airbnb.lottie.TextDelegate r1 = r1.getTextDelegate()
            if (r1 == 0) goto L19
            java.lang.String r2 = r7.getName()
            java.lang.String r0 = r1.getTextInternal(r2, r0)
        L19:
            android.graphics.Paint r1 = r7.fillPaint
            r1.setTypeface(r9)
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r9 = r7.textSizeCallbackAnimation
            if (r9 == 0) goto L2d
            java.lang.Object r9 = r9.getValue()
            java.lang.Float r9 = (java.lang.Float) r9
            float r9 = r9.floatValue()
            goto L2f
        L2d:
            float r9 = r8.size
        L2f:
            android.graphics.Paint r1 = r7.fillPaint
            float r2 = com.airbnb.lottie.utils.Utils.dpScale()
            float r2 = r2 * r9
            r1.setTextSize(r2)
            android.graphics.Paint r1 = r7.strokePaint
            android.graphics.Paint r2 = r7.fillPaint
            android.graphics.Typeface r2 = r2.getTypeface()
            r1.setTypeface(r2)
            android.graphics.Paint r1 = r7.strokePaint
            android.graphics.Paint r2 = r7.fillPaint
            float r2 = r2.getTextSize()
            r1.setTextSize(r2)
            float r1 = r8.lineHeight
            float r2 = com.airbnb.lottie.utils.Utils.dpScale()
            float r1 = r1 * r2
            int r2 = r8.tracking
            float r2 = (float) r2
            r3 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 / r3
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r3 = r7.trackingCallbackAnimation
            if (r3 == 0) goto L6c
            java.lang.Object r3 = r3.getValue()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
        L6a:
            float r2 = r2 + r3
            goto L7b
        L6c:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r3 = r7.trackingAnimation
            if (r3 == 0) goto L7b
            java.lang.Object r3 = r3.getValue()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            goto L6a
        L7b:
            float r3 = com.airbnb.lottie.utils.Utils.dpScale()
            float r2 = r2 * r3
            float r2 = r2 * r9
            r9 = 1120403456(0x42c80000, float:100.0)
            float r2 = r2 / r9
            java.util.List r9 = r7.getTextLines(r0)
            int r0 = r9.size()
            r3 = 0
        L8d:
            if (r3 >= r0) goto Lc3
            java.lang.Object r4 = r9.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            android.graphics.Paint r5 = r7.strokePaint
            float r5 = r5.measureText(r4)
            int r6 = r4.length()
            int r6 = r6 + (-1)
            float r6 = (float) r6
            float r6 = r6 * r2
            float r5 = r5 + r6
            r10.save()
            com.airbnb.lottie.model.DocumentData$Justification r6 = r8.justification
            r7.applyJustification(r6, r10, r5)
            int r5 = r0 + (-1)
            float r5 = (float) r5
            float r5 = r5 * r1
            r6 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 / r6
            float r6 = (float) r3
            float r6 = r6 * r1
            float r6 = r6 - r5
            r5 = 0
            r10.translate(r5, r6)
            r7.drawFontTextLine(r4, r8, r10, r2)
            r10.restore()
            int r3 = r3 + 1
            goto L8d
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.drawTextWithFont(com.airbnb.lottie.model.DocumentData, com.airbnb.lottie.model.Font, android.graphics.Canvas):void");
    }

    @Nullable
    private Typeface getTypeface(Font font) {
        Typeface value;
        BaseKeyframeAnimation<Typeface, Typeface> baseKeyframeAnimation = this.typefaceCallbackAnimation;
        if (baseKeyframeAnimation == null || (value = baseKeyframeAnimation.getValue()) == null) {
            Typeface typeface = this.lottieDrawable.getTypeface(font.getFamily(), font.getStyle());
            return typeface != null ? typeface : font.getTypeface();
        }
        return value;
    }

    private List<String> getTextLines(String str) {
        return Arrays.asList(str.replaceAll("\r\n", "\r").replaceAll("\n", "\r").split("\r"));
    }

    private void drawFontTextLine(String str, DocumentData documentData, Canvas canvas, float f) {
        int i = 0;
        while (i < str.length()) {
            String codePointToString = codePointToString(str, i);
            i += codePointToString.length();
            drawCharacterFromFont(codePointToString, documentData, canvas);
            canvas.translate(this.fillPaint.measureText(codePointToString) + f, 0.0f);
        }
    }

    private float getTextLineWidthForGlyphs(String str, Font font, float f, float f2) {
        float f3 = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            FontCharacter fontCharacter = this.composition.getCharacters().get(FontCharacter.hashFor(str.charAt(i), font.getFamily(), font.getStyle()));
            if (fontCharacter != null) {
                f3 = (float) (f3 + (fontCharacter.getWidth() * f * Utils.dpScale() * f2));
            }
        }
        return f3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.airbnb.lottie.model.layer.TextLayer$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification = new int[DocumentData.Justification.values().length];

        static {
            try {
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[DocumentData.Justification.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[DocumentData.Justification.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[DocumentData.Justification.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void applyJustification(DocumentData.Justification justification, Canvas canvas, float f) {
        int i = AnonymousClass3.$SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[justification.ordinal()];
        if (i != 1) {
            if (i == 2) {
                canvas.translate(-f, 0.0f);
            } else if (i != 3) {
            } else {
                canvas.translate((-f) / 2.0f, 0.0f);
            }
        }
    }

    private void drawCharacterAsGlyph(FontCharacter fontCharacter, Matrix matrix, float f, DocumentData documentData, Canvas canvas) {
        List<ContentGroup> contentsForCharacter = getContentsForCharacter(fontCharacter);
        for (int i = 0; i < contentsForCharacter.size(); i++) {
            Path path = contentsForCharacter.get(i).getPath();
            path.computeBounds(this.rectF, false);
            this.matrix.set(matrix);
            this.matrix.preTranslate(0.0f, (-documentData.baselineShift) * Utils.dpScale());
            this.matrix.preScale(f, f);
            path.transform(this.matrix);
            if (documentData.strokeOverFill) {
                drawGlyph(path, this.fillPaint, canvas);
                drawGlyph(path, this.strokePaint, canvas);
            } else {
                drawGlyph(path, this.strokePaint, canvas);
                drawGlyph(path, this.fillPaint, canvas);
            }
        }
    }

    private void drawGlyph(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    private void drawCharacterFromFont(String str, DocumentData documentData, Canvas canvas) {
        if (documentData.strokeOverFill) {
            drawCharacter(str, this.fillPaint, canvas);
            drawCharacter(str, this.strokePaint, canvas);
            return;
        }
        drawCharacter(str, this.strokePaint, canvas);
        drawCharacter(str, this.fillPaint, canvas);
    }

    private void drawCharacter(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    private List<ContentGroup> getContentsForCharacter(FontCharacter fontCharacter) {
        if (this.contentsForCharacter.containsKey(fontCharacter)) {
            return this.contentsForCharacter.get(fontCharacter);
        }
        List<ShapeGroup> shapes = fontCharacter.getShapes();
        int size = shapes.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new ContentGroup(this.lottieDrawable, this, shapes.get(i)));
        }
        this.contentsForCharacter.put(fontCharacter, arrayList);
        return arrayList;
    }

    private String codePointToString(String str, int i) {
        int codePointAt = str.codePointAt(i);
        int charCount = Character.charCount(codePointAt) + i;
        while (charCount < str.length()) {
            int codePointAt2 = str.codePointAt(charCount);
            if (!isModifier(codePointAt2)) {
                break;
            }
            charCount += Character.charCount(codePointAt2);
            codePointAt = (codePointAt * 31) + codePointAt2;
        }
        long j = codePointAt;
        if (this.codePointCache.containsKey(j)) {
            return this.codePointCache.get(j);
        }
        this.stringBuilder.setLength(0);
        while (i < charCount) {
            int codePointAt3 = str.codePointAt(i);
            this.stringBuilder.appendCodePoint(codePointAt3);
            i += Character.charCount(codePointAt3);
        }
        String sb = this.stringBuilder.toString();
        this.codePointCache.put(j, sb);
        return sb;
    }

    private boolean isModifier(int i) {
        return Character.getType(i) == 16 || Character.getType(i) == 27 || Character.getType(i) == 6 || Character.getType(i) == 28 || Character.getType(i) == 8 || Character.getType(i) == 19;
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t, lottieValueCallback);
        if (t == LottieProperty.COLOR) {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.colorCallbackAnimation;
            if (baseKeyframeAnimation != null) {
                removeAnimation(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            this.colorCallbackAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.colorCallbackAnimation.addUpdateListener(this);
            addAnimation(this.colorCallbackAnimation);
        } else if (t == LottieProperty.STROKE_COLOR) {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.strokeColorCallbackAnimation;
            if (baseKeyframeAnimation2 != null) {
                removeAnimation(baseKeyframeAnimation2);
            }
            if (lottieValueCallback == null) {
                this.strokeColorCallbackAnimation = null;
                return;
            }
            this.strokeColorCallbackAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.strokeColorCallbackAnimation.addUpdateListener(this);
            addAnimation(this.strokeColorCallbackAnimation);
        } else if (t == LottieProperty.STROKE_WIDTH) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation3 = this.strokeWidthCallbackAnimation;
            if (baseKeyframeAnimation3 != null) {
                removeAnimation(baseKeyframeAnimation3);
            }
            if (lottieValueCallback == null) {
                this.strokeWidthCallbackAnimation = null;
                return;
            }
            this.strokeWidthCallbackAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.strokeWidthCallbackAnimation.addUpdateListener(this);
            addAnimation(this.strokeWidthCallbackAnimation);
        } else if (t == LottieProperty.TEXT_TRACKING) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation4 = this.trackingCallbackAnimation;
            if (baseKeyframeAnimation4 != null) {
                removeAnimation(baseKeyframeAnimation4);
            }
            if (lottieValueCallback == null) {
                this.trackingCallbackAnimation = null;
                return;
            }
            this.trackingCallbackAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.trackingCallbackAnimation.addUpdateListener(this);
            addAnimation(this.trackingCallbackAnimation);
        } else if (t == LottieProperty.TEXT_SIZE) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation5 = this.textSizeCallbackAnimation;
            if (baseKeyframeAnimation5 != null) {
                removeAnimation(baseKeyframeAnimation5);
            }
            if (lottieValueCallback == null) {
                this.textSizeCallbackAnimation = null;
                return;
            }
            this.textSizeCallbackAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.textSizeCallbackAnimation.addUpdateListener(this);
            addAnimation(this.textSizeCallbackAnimation);
        } else if (t == LottieProperty.TYPEFACE) {
            BaseKeyframeAnimation<Typeface, Typeface> baseKeyframeAnimation6 = this.typefaceCallbackAnimation;
            if (baseKeyframeAnimation6 != null) {
                removeAnimation(baseKeyframeAnimation6);
            }
            if (lottieValueCallback == null) {
                this.typefaceCallbackAnimation = null;
                return;
            }
            this.typefaceCallbackAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.typefaceCallbackAnimation.addUpdateListener(this);
            addAnimation(this.typefaceCallbackAnimation);
        } else if (t == LottieProperty.TEXT) {
            this.textAnimation.setStringValueCallback(lottieValueCallback);
        }
    }
}
