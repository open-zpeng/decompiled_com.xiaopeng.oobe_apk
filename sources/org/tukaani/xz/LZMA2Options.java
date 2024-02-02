package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class LZMA2Options extends FilterOptions {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DICT_SIZE_DEFAULT = 8388608;
    public static final int DICT_SIZE_MAX = 805306368;
    public static final int DICT_SIZE_MIN = 4096;
    public static final int LC_DEFAULT = 3;
    public static final int LC_LP_MAX = 4;
    public static final int LP_DEFAULT = 0;
    public static final int MF_BT4 = 20;
    public static final int MF_HC4 = 4;
    public static final int MODE_FAST = 1;
    public static final int MODE_NORMAL = 2;
    public static final int MODE_UNCOMPRESSED = 0;
    public static final int NICE_LEN_MAX = 273;
    public static final int NICE_LEN_MIN = 8;
    public static final int PB_DEFAULT = 2;
    public static final int PB_MAX = 4;
    public static final int PRESET_DEFAULT = 6;
    public static final int PRESET_MAX = 9;
    public static final int PRESET_MIN = 0;
    static /* synthetic */ Class class$org$tukaani$xz$LZMA2Options;
    private static final int[] presetToDepthLimit;
    private static final int[] presetToDictSize;
    private int depthLimit;
    private int dictSize;
    private int lc;
    private int lp;
    private int mf;
    private int mode;
    private int niceLen;
    private int pb;
    private byte[] presetDict = null;

    static {
        if (class$org$tukaani$xz$LZMA2Options == null) {
            class$org$tukaani$xz$LZMA2Options = class$("org.tukaani.xz.LZMA2Options");
        }
        presetToDictSize = new int[]{262144, 1048576, 2097152, 4194304, 4194304, 8388608, 8388608, 16777216, 33554432, 67108864};
        presetToDepthLimit = new int[]{4, 8, 24, 48};
    }

    public LZMA2Options() {
        try {
            setPreset(6);
        } catch (UnsupportedOptionsException unused) {
            throw new RuntimeException();
        }
    }

    public LZMA2Options(int i) throws UnsupportedOptionsException {
        setPreset(i);
    }

    public LZMA2Options(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) throws UnsupportedOptionsException {
        setDictSize(i);
        setLcLp(i2, i3);
        setPb(i4);
        setMode(i5);
        setNiceLen(i6);
        setMatchFinder(i7);
        setDepthLimit(i8);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException();
        }
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getDecoderMemoryUsage() {
        int i = this.dictSize - 1;
        int i2 = i | (i >>> 2);
        int i3 = i2 | (i2 >>> 3);
        int i4 = i3 | (i3 >>> 4);
        int i5 = i4 | (i4 >>> 8);
        return LZMA2InputStream.getMemoryUsage((i5 | (i5 >>> 16)) + 1);
    }

    public int getDepthLimit() {
        return this.depthLimit;
    }

    public int getDictSize() {
        return this.dictSize;
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getEncoderMemoryUsage() {
        return this.mode == 0 ? UncompressedLZMA2OutputStream.getMemoryUsage() : LZMA2OutputStream.getMemoryUsage(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.tukaani.xz.FilterOptions
    public FilterEncoder getFilterEncoder() {
        return new LZMA2Encoder(this);
    }

    @Override // org.tukaani.xz.FilterOptions
    public InputStream getInputStream(InputStream inputStream) throws IOException {
        return new LZMA2InputStream(inputStream, this.dictSize);
    }

    public int getLc() {
        return this.lc;
    }

    public int getLp() {
        return this.lp;
    }

    public int getMatchFinder() {
        return this.mf;
    }

    public int getMode() {
        return this.mode;
    }

    public int getNiceLen() {
        return this.niceLen;
    }

    @Override // org.tukaani.xz.FilterOptions
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream) {
        return this.mode == 0 ? new UncompressedLZMA2OutputStream(finishableOutputStream) : new LZMA2OutputStream(finishableOutputStream, this);
    }

    public int getPb() {
        return this.pb;
    }

    public byte[] getPresetDict() {
        return this.presetDict;
    }

    public void setDepthLimit(int i) throws UnsupportedOptionsException {
        if (i >= 0) {
            this.depthLimit = i;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Depth limit cannot be negative: ");
        stringBuffer.append(i);
        throw new UnsupportedOptionsException(stringBuffer.toString());
    }

    public void setDictSize(int i) throws UnsupportedOptionsException {
        if (i < 4096) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("LZMA2 dictionary size must be at least 4 KiB: ");
            stringBuffer.append(i);
            stringBuffer.append(" B");
            throw new UnsupportedOptionsException(stringBuffer.toString());
        } else if (i <= 805306368) {
            this.dictSize = i;
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("LZMA2 dictionary size must not exceed 768 MiB: ");
            stringBuffer2.append(i);
            stringBuffer2.append(" B");
            throw new UnsupportedOptionsException(stringBuffer2.toString());
        }
    }

    public void setLc(int i) throws UnsupportedOptionsException {
        setLcLp(i, this.lp);
    }

    public void setLcLp(int i, int i2) throws UnsupportedOptionsException {
        if (i >= 0 && i2 >= 0 && i <= 4 && i2 <= 4 && i + i2 <= 4) {
            this.lc = i;
            this.lp = i2;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("lc + lp must not exceed 4: ");
        stringBuffer.append(i);
        stringBuffer.append(" + ");
        stringBuffer.append(i2);
        throw new UnsupportedOptionsException(stringBuffer.toString());
    }

    public void setLp(int i) throws UnsupportedOptionsException {
        setLcLp(this.lc, i);
    }

    public void setMatchFinder(int i) throws UnsupportedOptionsException {
        if (i == 4 || i == 20) {
            this.mf = i;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unsupported match finder: ");
        stringBuffer.append(i);
        throw new UnsupportedOptionsException(stringBuffer.toString());
    }

    public void setMode(int i) throws UnsupportedOptionsException {
        if (i >= 0 && i <= 2) {
            this.mode = i;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unsupported compression mode: ");
        stringBuffer.append(i);
        throw new UnsupportedOptionsException(stringBuffer.toString());
    }

    public void setNiceLen(int i) throws UnsupportedOptionsException {
        if (i < 8) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Minimum nice length of matches is 8 bytes: ");
            stringBuffer.append(i);
            throw new UnsupportedOptionsException(stringBuffer.toString());
        } else if (i <= 273) {
            this.niceLen = i;
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Maximum nice length of matches is 273: ");
            stringBuffer2.append(i);
            throw new UnsupportedOptionsException(stringBuffer2.toString());
        }
    }

    public void setPb(int i) throws UnsupportedOptionsException {
        if (i >= 0 && i <= 4) {
            this.pb = i;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("pb must not exceed 4: ");
        stringBuffer.append(i);
        throw new UnsupportedOptionsException(stringBuffer.toString());
    }

    public void setPreset(int i) throws UnsupportedOptionsException {
        if (i < 0 || i > 9) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unsupported preset: ");
            stringBuffer.append(i);
            throw new UnsupportedOptionsException(stringBuffer.toString());
        }
        this.lc = 3;
        this.lp = 0;
        this.pb = 2;
        this.dictSize = presetToDictSize[i];
        if (i <= 3) {
            this.mode = 1;
            this.mf = 4;
            this.niceLen = i <= 1 ? 128 : NICE_LEN_MAX;
            this.depthLimit = presetToDepthLimit[i];
            return;
        }
        this.mode = 2;
        this.mf = 20;
        this.niceLen = i == 4 ? 16 : i == 5 ? 32 : 64;
        this.depthLimit = 0;
    }

    public void setPresetDict(byte[] bArr) {
        this.presetDict = bArr;
    }
}
