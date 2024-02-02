package com.xiaopeng.oobe.utils;

import android.graphics.Bitmap;
import android.support.rastermill.FrameSequenceDrawable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
/* loaded from: classes.dex */
public class ViewUtil {
    public static SparseArray<LinkedList<WeakReference<Bitmap>>> sBitmapCache = new SparseArray<>();
    public static FrameSequenceDrawable.BitmapProvider sBitmapProvider = new FrameSequenceDrawable.BitmapProvider() { // from class: com.xiaopeng.oobe.utils.ViewUtil.1
        private int getKey(int i, int i2) {
            return (i * 1000) + i2;
        }

        @Override // android.support.rastermill.FrameSequenceDrawable.BitmapProvider
        public Bitmap acquireBitmap(int i, int i2) {
            Bitmap bitmap;
            LinkedList<WeakReference<Bitmap>> linkedList = ViewUtil.sBitmapCache.get(getKey(i, i2));
            if (linkedList != null && !linkedList.isEmpty()) {
                do {
                    bitmap = linkedList.poll().get();
                    if (bitmap != null) {
                        break;
                    }
                } while (!linkedList.isEmpty());
            } else {
                bitmap = null;
            }
            return bitmap == null ? Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888) : bitmap;
        }

        @Override // android.support.rastermill.FrameSequenceDrawable.BitmapProvider
        public void releaseBitmap(Bitmap bitmap) {
            int key = getKey(bitmap.getWidth(), bitmap.getHeight());
            LinkedList<WeakReference<Bitmap>> linkedList = ViewUtil.sBitmapCache.get(key);
            if (linkedList == null) {
                linkedList = new LinkedList<>();
                ViewUtil.sBitmapCache.put(key, linkedList);
            }
            linkedList.add(new WeakReference<>(bitmap));
        }
    };

    public static void onPreDraw(final View view, final Runnable runnable, final boolean z) {
        if (view == null) {
            return;
        }
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.xiaopeng.oobe.utils.ViewUtil.2
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeOnPreDrawListener(this);
                    if (z) {
                        view.post(runnable);
                        return false;
                    }
                    runnable.run();
                    return false;
                }
                return false;
            }
        });
    }
}
