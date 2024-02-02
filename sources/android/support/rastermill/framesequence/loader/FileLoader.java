package android.support.rastermill.framesequence.loader;

import android.content.Context;
import java.io.File;
/* loaded from: classes.dex */
public class FileLoader extends AbsLoader {
    private File mFile;

    @Override // android.support.rastermill.ILoader
    public int getType() {
        return 2;
    }

    public FileLoader(Context context, File file) {
        super(context);
        this.mFile = file;
        this.mKey = file.getAbsolutePath();
    }

    @Override // android.support.rastermill.ILoader
    public boolean exists() {
        File file = this.mFile;
        return file != null && file.exists();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0024, code lost:
        if (r1 == null) goto L10;
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
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L17
            java.io.File r2 = r6.mFile     // Catch: java.lang.Throwable -> L17
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L17
            android.support.rastermill.FrameSequence r0 = android.support.rastermill.FrameSequence.decodeStream(r1)     // Catch: java.lang.Throwable -> L10
        Lc:
            r1.close()     // Catch: java.io.IOException -> L27
            goto L27
        L10:
            r2 = move-exception
            goto L19
        L12:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L29
        L17:
            r2 = move-exception
            r1 = r0
        L19:
            java.lang.Class<android.support.rastermill.FrameSequenceUtil> r3 = android.support.rastermill.FrameSequenceUtil.class
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L28
            java.lang.String r4 = "decodeFile"
            android.util.Log.e(r3, r4, r2)     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L27
            goto Lc
        L27:
            return r0
        L28:
            r0 = move-exception
        L29:
            if (r1 == 0) goto L2e
            r1.close()     // Catch: java.io.IOException -> L2e
        L2e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.rastermill.framesequence.loader.FileLoader.decode():android.support.rastermill.FrameSequence");
    }
}
