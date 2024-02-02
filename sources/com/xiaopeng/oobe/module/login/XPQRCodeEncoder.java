package com.xiaopeng.oobe.module.login;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.EnumMap;
import java.util.Map;
/* loaded from: classes.dex */
public class XPQRCodeEncoder {
    protected static final Map<EncodeHintType, Object> HINTS = new EnumMap(EncodeHintType.class);

    static {
        HINTS.put(EncodeHintType.CHARACTER_SET, "utf-8");
        HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        HINTS.put(EncodeHintType.MARGIN, 0);
    }

    private XPQRCodeEncoder() {
    }

    public static Bitmap syncEncodeQRCode(String str, int i) {
        return syncEncodeQRCode(str, i, ViewCompat.MEASURED_STATE_MASK, -1, null);
    }

    public static Bitmap syncEncodeQRCode(String str, int i, int i2, int i3, Bitmap bitmap) {
        try {
            BitMatrix deleteWhite = deleteWhite(new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i, HINTS));
            int width = deleteWhite.getWidth();
            int[] iArr = new int[width * width];
            for (int i4 = 0; i4 < width; i4++) {
                for (int i5 = 0; i5 < width; i5++) {
                    if (deleteWhite.get(i5, i4)) {
                        iArr[(i4 * width) + i5] = i2;
                    } else {
                        iArr[(i4 * width) + i5] = i3;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, width);
            return addLogoToQRCode(createBitmap, bitmap);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Bitmap addLogoToQRCode(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        double d = width;
        float f = (float) ((d / 5.0d) / width2);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f, f, (float) (d / 2.0d), (float) (height / 2.0d));
            canvas.drawBitmap(bitmap2, (float) ((width - width2) / 2.0d), (float) ((height - height2) / 2.0d), (Paint) null);
            canvas.save();
            canvas.restore();
            return createBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    private static BitMatrix deleteWhite(BitMatrix bitMatrix) {
        int[] enclosingRectangle = bitMatrix.getEnclosingRectangle();
        int i = enclosingRectangle[2] + 1;
        int i2 = enclosingRectangle[3] + 1;
        BitMatrix bitMatrix2 = new BitMatrix(i, i2);
        bitMatrix2.clear();
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (bitMatrix.get(enclosingRectangle[0] + i3, enclosingRectangle[1] + i4)) {
                    bitMatrix2.set(i3, i4);
                }
            }
        }
        return bitMatrix2;
    }
}
