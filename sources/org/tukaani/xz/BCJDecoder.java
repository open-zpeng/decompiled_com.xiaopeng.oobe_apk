package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARM;
import org.tukaani.xz.simple.ARMThumb;
import org.tukaani.xz.simple.IA64;
import org.tukaani.xz.simple.PowerPC;
import org.tukaani.xz.simple.SPARC;
import org.tukaani.xz.simple.X86;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BCJDecoder extends BCJCoder implements FilterDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static /* synthetic */ Class class$org$tukaani$xz$BCJDecoder;
    private final long filterID;
    private final int startOffset;

    static {
        if (class$org$tukaani$xz$BCJDecoder == null) {
            class$org$tukaani$xz$BCJDecoder = class$("org.tukaani.xz.BCJDecoder");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCJDecoder(long j, byte[] bArr) throws UnsupportedOptionsException {
        this.filterID = j;
        if (bArr.length == 0) {
            this.startOffset = 0;
        } else if (bArr.length != 4) {
            throw new UnsupportedOptionsException("Unsupported BCJ filter properties");
        } else {
            int i = 0;
            for (int i2 = 0; i2 < 4; i2++) {
                i |= (bArr[i2] & 255) << (i2 * 8);
            }
            this.startOffset = i;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // org.tukaani.xz.FilterDecoder
    public InputStream getInputStream(InputStream inputStream) {
        long j = this.filterID;
        return new SimpleInputStream(inputStream, j == 4 ? new X86(false, this.startOffset) : j == 5 ? new PowerPC(false, this.startOffset) : j == 6 ? new IA64(false, this.startOffset) : j == 7 ? new ARM(false, this.startOffset) : j == 8 ? new ARMThumb(false, this.startOffset) : j == 9 ? new SPARC(false, this.startOffset) : null);
    }

    @Override // org.tukaani.xz.FilterDecoder
    public int getMemoryUsage() {
        return SimpleInputStream.getMemoryUsage();
    }
}
