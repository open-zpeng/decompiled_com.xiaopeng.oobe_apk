package android.support.rastermill.framesequence;

import android.support.rastermill.FrameSequence;
import android.support.rastermill.LogUtil;
import android.util.SparseArray;
/* loaded from: classes.dex */
public class FrameSequenceRefHelper {
    private static SparseArray<Integer> mRefCountMap = new SparseArray<>();

    /* JADX WARN: Removed duplicated region for block: B:16:0x0036 A[Catch: all -> 0x0054, TRY_LEAVE, TryCatch #0 {, blocks: (B:7:0x0007, B:9:0x0016, B:12:0x001d, B:14:0x002b, B:16:0x0036, B:13:0x0027), top: B:22:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized void increase(android.support.rastermill.FrameSequence r4) {
        /*
            java.lang.Class<android.support.rastermill.framesequence.FrameSequenceRefHelper> r0 = android.support.rastermill.framesequence.FrameSequenceRefHelper.class
            monitor-enter(r0)
            if (r4 != 0) goto L7
            monitor-exit(r0)
            return
        L7:
            int r4 = r4.hashCode()     // Catch: java.lang.Throwable -> L54
            android.util.SparseArray<java.lang.Integer> r1 = android.support.rastermill.framesequence.FrameSequenceRefHelper.mRefCountMap     // Catch: java.lang.Throwable -> L54
            java.lang.Object r1 = r1.get(r4)     // Catch: java.lang.Throwable -> L54
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Throwable -> L54
            r2 = 1
            if (r1 == 0) goto L27
            int r3 = r1.intValue()     // Catch: java.lang.Throwable -> L54
            if (r3 > 0) goto L1d
            goto L27
        L1d:
            int r1 = r1.intValue()     // Catch: java.lang.Throwable -> L54
            int r1 = r1 + r2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L54
            goto L2b
        L27:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L54
        L2b:
            android.util.SparseArray<java.lang.Integer> r2 = android.support.rastermill.framesequence.FrameSequenceRefHelper.mRefCountMap     // Catch: java.lang.Throwable -> L54
            r2.put(r4, r1)     // Catch: java.lang.Throwable -> L54
            boolean r2 = android.support.rastermill.LogUtil.isLogEnable()     // Catch: java.lang.Throwable -> L54
            if (r2 == 0) goto L52
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L54
            r2.<init>()     // Catch: java.lang.Throwable -> L54
            java.lang.String r3 = "increase : key = "
            r2.append(r3)     // Catch: java.lang.Throwable -> L54
            r2.append(r4)     // Catch: java.lang.Throwable -> L54
            java.lang.String r4 = ", count = "
            r2.append(r4)     // Catch: java.lang.Throwable -> L54
            r2.append(r1)     // Catch: java.lang.Throwable -> L54
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Throwable -> L54
            android.support.rastermill.LogUtil.e(r4)     // Catch: java.lang.Throwable -> L54
        L52:
            monitor-exit(r0)
            return
        L54:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.rastermill.framesequence.FrameSequenceRefHelper.increase(android.support.rastermill.FrameSequence):void");
    }

    public static synchronized void decrease(FrameSequence frameSequence, String str) {
        synchronized (FrameSequenceRefHelper.class) {
            if (frameSequence == null) {
                return;
            }
            int hashCode = frameSequence.hashCode();
            Integer num = mRefCountMap.get(hashCode);
            if (num != null) {
                Integer valueOf = Integer.valueOf(num.intValue() - 1);
                if (valueOf.intValue() <= 0) {
                    mRefCountMap.remove(hashCode);
                    if (!FrameSequenceCache.isCachedFrameSequence(str)) {
                        frameSequence.destroy();
                    }
                } else {
                    mRefCountMap.put(hashCode, valueOf);
                }
                if (LogUtil.isLogEnable()) {
                    LogUtil.e("decrease : key = " + hashCode + ", count = " + valueOf + ", cacheKey = " + str);
                }
            }
        }
    }

    public static synchronized boolean isActiveRef(FrameSequence frameSequence) {
        synchronized (FrameSequenceRefHelper.class) {
            boolean z = false;
            if (frameSequence == null) {
                return false;
            }
            Integer num = mRefCountMap.get(frameSequence.hashCode());
            if (num != null) {
                if (num.intValue() > 0) {
                    z = true;
                }
            }
            return z;
        }
    }
}
