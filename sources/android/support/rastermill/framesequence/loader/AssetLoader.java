package android.support.rastermill.framesequence.loader;

import android.content.Context;
import android.text.TextUtils;
/* loaded from: classes.dex */
public class AssetLoader extends AbsLoader {
    private String mAsset;

    @Override // android.support.rastermill.ILoader
    public int getType() {
        return 1;
    }

    public AssetLoader(Context context, String str) {
        super(context);
        this.mAsset = str;
        this.mKey = str;
    }

    @Override // android.support.rastermill.ILoader
    public boolean exists() {
        return !TextUtils.isEmpty(this.mAsset);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r1 == null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002e, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        if (r1 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0013, code lost:
        r1.close();
     */
    @Override // android.support.rastermill.framesequence.loader.AbsLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.support.rastermill.FrameSequence decode() {
        /*
            r6 = this;
            r0 = 0
            android.content.Context r1 = r6.mContext     // Catch: java.lang.Throwable -> L1e
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch: java.lang.Throwable -> L1e
            java.lang.String r2 = r6.mAsset     // Catch: java.lang.Throwable -> L1e
            java.io.InputStream r1 = r1.open(r2)     // Catch: java.lang.Throwable -> L1e
            android.support.rastermill.FrameSequence r0 = android.support.rastermill.FrameSequence.decodeStream(r1)     // Catch: java.lang.Throwable -> L17
            if (r1 == 0) goto L2e
        L13:
            r1.close()     // Catch: java.io.IOException -> L2e
            goto L2e
        L17:
            r2 = move-exception
            goto L20
        L19:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L30
        L1e:
            r2 = move-exception
            r1 = r0
        L20:
            java.lang.Class<android.support.rastermill.FrameSequenceUtil> r3 = android.support.rastermill.FrameSequenceUtil.class
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L2f
            java.lang.String r4 = "decodeAsset"
            android.util.Log.e(r3, r4, r2)     // Catch: java.lang.Throwable -> L2f
            if (r1 == 0) goto L2e
            goto L13
        L2e:
            return r0
        L2f:
            r0 = move-exception
        L30:
            if (r1 == 0) goto L35
            r1.close()     // Catch: java.io.IOException -> L35
        L35:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.rastermill.framesequence.loader.AssetLoader.decode():android.support.rastermill.FrameSequence");
    }
}
