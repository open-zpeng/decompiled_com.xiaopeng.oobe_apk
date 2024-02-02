package org.tukaani.xz.lz;

import java.io.DataInputStream;
import java.io.IOException;
import org.tukaani.xz.CorruptedInputException;
/* loaded from: classes2.dex */
public final class LZDecoder {
    private final byte[] buf;
    private int full;
    private int pos;
    private int start;
    private int limit = 0;
    private int pendingLen = 0;
    private int pendingDist = 0;

    public LZDecoder(int i, byte[] bArr) {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        this.buf = new byte[i];
        if (bArr != null) {
            this.pos = Math.min(bArr.length, i);
            int i2 = this.pos;
            this.full = i2;
            this.start = i2;
            System.arraycopy(bArr, bArr.length - i2, this.buf, 0, i2);
        }
    }

    public void copyUncompressed(DataInputStream dataInputStream, int i) throws IOException {
        int min = Math.min(this.buf.length - this.pos, i);
        dataInputStream.readFully(this.buf, this.pos, min);
        this.pos += min;
        int i2 = this.full;
        int i3 = this.pos;
        if (i2 < i3) {
            this.full = i3;
        }
    }

    public int flush(byte[] bArr, int i) {
        int i2 = this.pos;
        int i3 = i2 - this.start;
        if (i2 == this.buf.length) {
            this.pos = 0;
        }
        System.arraycopy(this.buf, this.start, bArr, i, i3);
        this.start = this.pos;
        return i3;
    }

    public int getByte(int i) {
        int i2 = this.pos;
        int i3 = (i2 - i) - 1;
        if (i >= i2) {
            i3 += this.buf.length;
        }
        return this.buf[i3] & 255;
    }

    public int getPos() {
        return this.pos;
    }

    public boolean hasPending() {
        return this.pendingLen > 0;
    }

    public boolean hasSpace() {
        return this.pos < this.limit;
    }

    public void putByte(byte b) {
        byte[] bArr = this.buf;
        int i = this.pos;
        this.pos = i + 1;
        bArr[i] = b;
        int i2 = this.full;
        int i3 = this.pos;
        if (i2 < i3) {
            this.full = i3;
        }
    }

    public void repeat(int i, int i2) throws IOException {
        if (i < 0 || i >= this.full) {
            throw new CorruptedInputException();
        }
        int min = Math.min(this.limit - this.pos, i2);
        this.pendingLen = i2 - min;
        this.pendingDist = i;
        int i3 = this.pos;
        int i4 = (i3 - i) - 1;
        if (i >= i3) {
            i4 += this.buf.length;
        }
        do {
            byte[] bArr = this.buf;
            int i5 = this.pos;
            this.pos = i5 + 1;
            int i6 = i4 + 1;
            bArr[i5] = bArr[i4];
            i4 = i6 == bArr.length ? 0 : i6;
            min--;
        } while (min > 0);
        int i7 = this.full;
        int i8 = this.pos;
        if (i7 < i8) {
            this.full = i8;
        }
    }

    public void repeatPending() throws IOException {
        int i = this.pendingLen;
        if (i > 0) {
            repeat(this.pendingDist, i);
        }
    }

    public void reset() {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        this.limit = 0;
        byte[] bArr = this.buf;
        bArr[bArr.length - 1] = 0;
    }

    public void setLimit(int i) {
        byte[] bArr = this.buf;
        int length = bArr.length;
        int i2 = this.pos;
        if (length - i2 <= i) {
            this.limit = bArr.length;
        } else {
            this.limit = i2 + i;
        }
    }
}
