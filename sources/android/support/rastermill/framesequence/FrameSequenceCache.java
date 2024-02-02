package android.support.rastermill.framesequence;

import android.support.rastermill.FrameSequence;
import android.support.rastermill.FrameSequenceDrawable;
import android.util.LruCache;
/* loaded from: classes.dex */
public class FrameSequenceCache {
    private static LruCache<String, FrameSequence> sFrameSequenceCache;
    private static boolean sFrameSequenceCacheEnable;
    private static LruCache<String, FrameSequenceDrawable> sRecycleDrawableCache;
    private static boolean sRecycleDrawableCacheEnable;
    private static Object sRecycleDrawableCacheLock = new Object();
    private static Object sFrameSequenceCacheLock = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setRecycleDrawableCacheSize(int i) {
        if (i <= 0) {
            sRecycleDrawableCache = null;
            sRecycleDrawableCacheEnable = false;
            return;
        }
        sRecycleDrawableCache = new LruCache<String, FrameSequenceDrawable>(i) { // from class: android.support.rastermill.framesequence.FrameSequenceCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public void entryRemoved(boolean z, String str, FrameSequenceDrawable frameSequenceDrawable, FrameSequenceDrawable frameSequenceDrawable2) {
                super.entryRemoved(z, (boolean) str, frameSequenceDrawable, frameSequenceDrawable2);
                if (z || !(z || frameSequenceDrawable2 == null || frameSequenceDrawable == frameSequenceDrawable2)) {
                    FrameSequencePlugin.destroy(str, frameSequenceDrawable);
                }
            }
        };
        sRecycleDrawableCacheEnable = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setFrameSequenceCacheSize(int i) {
        if (i <= 0) {
            sFrameSequenceCache = null;
            sFrameSequenceCacheEnable = false;
            return;
        }
        sFrameSequenceCache = new LruCache<String, FrameSequence>(i) { // from class: android.support.rastermill.framesequence.FrameSequenceCache.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public void entryRemoved(boolean z, String str, FrameSequence frameSequence, FrameSequence frameSequence2) {
                super.entryRemoved(z, (boolean) str, frameSequence, frameSequence2);
                if (frameSequence == null || FrameSequenceRefHelper.isActiveRef(frameSequence) || frameSequence.isDestroyed()) {
                    return;
                }
                frameSequence.destroy();
            }
        };
        sFrameSequenceCacheEnable = true;
    }

    public static boolean isFrameSequenceCacheEnable() {
        return sFrameSequenceCacheEnable;
    }

    public static boolean isRecycleDrawableCacheEnable() {
        return sRecycleDrawableCacheEnable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FrameSequenceDrawable getCachedFrameSequenceDrawable(String str) {
        FrameSequenceDrawable frameSequenceDrawable;
        if (str == null || !sRecycleDrawableCacheEnable) {
            return null;
        }
        synchronized (sRecycleDrawableCacheLock) {
            frameSequenceDrawable = sRecycleDrawableCache.get(str);
            sRecycleDrawableCache.remove(str);
        }
        if (frameSequenceDrawable == null || !frameSequenceDrawable.isDestroyed()) {
            return frameSequenceDrawable;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putCachedFrameSequenceDrawable(String str, FrameSequenceDrawable frameSequenceDrawable) {
        if (str == null || frameSequenceDrawable == null || !sRecycleDrawableCacheEnable) {
            return;
        }
        synchronized (sRecycleDrawableCacheLock) {
            sRecycleDrawableCache.put(str, frameSequenceDrawable);
        }
    }

    public static boolean isCachedFrameSequence(String str) {
        FrameSequence frameSequence;
        if (str == null || !sFrameSequenceCacheEnable) {
            return false;
        }
        synchronized (sFrameSequenceCacheLock) {
            frameSequence = sFrameSequenceCache.get(str);
        }
        return (frameSequence == null || frameSequence.isDestroyed()) ? false : true;
    }

    public static FrameSequence getCachedFrameSequence(String str) {
        FrameSequence frameSequence = null;
        if (str == null || !sFrameSequenceCacheEnable) {
            return null;
        }
        synchronized (sFrameSequenceCacheLock) {
            FrameSequence frameSequence2 = sFrameSequenceCache.get(str);
            if (frameSequence2 == null || !frameSequence2.isDestroyed()) {
                frameSequence = frameSequence2;
            } else {
                sFrameSequenceCache.remove(str);
            }
        }
        return frameSequence;
    }

    public static void putCachedFrameSequence(String str, FrameSequence frameSequence) {
        if (str == null || frameSequence == null || !sFrameSequenceCacheEnable) {
            return;
        }
        synchronized (sFrameSequenceCacheLock) {
            sFrameSequenceCache.put(str, frameSequence);
        }
    }
}
