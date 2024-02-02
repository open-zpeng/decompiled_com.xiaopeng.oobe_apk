package android.support.rastermill;

import android.graphics.drawable.Drawable;
/* loaded from: classes.dex */
public interface LoadListener {
    void onFail(int i, String str);

    void onReady(int i, String str, Drawable drawable);
}
