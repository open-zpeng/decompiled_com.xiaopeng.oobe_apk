package android.support.rastermill.framesequence.loader;

import android.content.Context;
import android.support.rastermill.CacheEngine;
import java.io.File;
/* loaded from: classes.dex */
public class HttpLoader extends AbsLoader {
    private File mFile;

    @Override // android.support.rastermill.ILoader
    public int getType() {
        return 5;
    }

    @Override // android.support.rastermill.framesequence.loader.AbsLoader, android.support.rastermill.ILoader
    public boolean isCacheRequired() {
        return true;
    }

    public HttpLoader(Context context, String str) {
        super(context);
        this.mKey = str;
    }

    @Override // android.support.rastermill.ILoader
    public boolean exists() {
        if (this.mFile == null) {
            this.mFile = CacheEngine.getInstance().getDiskCache().get(this.mKey);
        }
        File file = this.mFile;
        return file != null && file.exists();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
        if (r1 != null) goto L40;
     */
    @Override // android.support.rastermill.framesequence.loader.AbsLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.support.rastermill.FrameSequence decode() {
        /*
            r6 = this;
            java.io.File r0 = r6.mFile
            if (r0 != 0) goto L14
            android.support.rastermill.CacheEngine r0 = android.support.rastermill.CacheEngine.getInstance()
            android.support.rastermill.cache.DiskCache r0 = r0.getDiskCache()
            java.lang.String r1 = r6.mKey
            java.io.File r0 = r0.get(r1)
            r6.mFile = r0
        L14:
            java.io.File r0 = r6.mFile
            r1 = 0
            if (r0 == 0) goto L6b
            boolean r0 = r0.exists()
            if (r0 == 0) goto L6b
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L44
            java.io.File r2 = r6.mFile     // Catch: java.lang.Throwable -> L44
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L44
            android.support.rastermill.FrameSequence r1 = android.support.rastermill.FrameSequence.decodeStream(r0)     // Catch: java.lang.Throwable -> L3d
            r0.close()     // Catch: java.io.IOException -> L2d
        L2d:
            if (r1 != 0) goto L6b
        L2f:
            android.support.rastermill.CacheEngine r0 = android.support.rastermill.CacheEngine.getInstance()
            android.support.rastermill.cache.DiskCache r0 = r0.getDiskCache()
            java.lang.String r2 = r6.mKey
            r0.delete(r2)
            goto L6b
        L3d:
            r2 = move-exception
            goto L46
        L3f:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L58
        L44:
            r2 = move-exception
            r0 = r1
        L46:
            java.lang.Class<android.support.rastermill.FrameSequenceUtil> r3 = android.support.rastermill.FrameSequenceUtil.class
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L57
            java.lang.String r4 = "decodeHttp"
            android.util.Log.e(r3, r4, r2)     // Catch: java.lang.Throwable -> L57
            if (r0 == 0) goto L2f
            r0.close()     // Catch: java.io.IOException -> L2f
            goto L2f
        L57:
            r1 = move-exception
        L58:
            if (r0 == 0) goto L5d
            r0.close()     // Catch: java.io.IOException -> L5d
        L5d:
            android.support.rastermill.CacheEngine r0 = android.support.rastermill.CacheEngine.getInstance()
            android.support.rastermill.cache.DiskCache r0 = r0.getDiskCache()
            java.lang.String r2 = r6.mKey
            r0.delete(r2)
            throw r1
        L6b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.rastermill.framesequence.loader.HttpLoader.decode():android.support.rastermill.FrameSequence");
    }
}
