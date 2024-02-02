package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.simple.SimpleFilter;
/* loaded from: classes2.dex */
class SimpleInputStream extends InputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FILTER_BUF_SIZE = 4096;
    static /* synthetic */ Class class$org$tukaani$xz$SimpleInputStream;
    private InputStream in;
    private final SimpleFilter simpleFilter;
    private final byte[] filterBuf = new byte[4096];
    private int pos = 0;
    private int filtered = 0;
    private int unfiltered = 0;
    private boolean endReached = false;
    private IOException exception = null;
    private final byte[] tempBuf = new byte[1];

    static {
        if (class$org$tukaani$xz$SimpleInputStream == null) {
            class$org$tukaani$xz$SimpleInputStream = class$("org.tukaani.xz.SimpleInputStream");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleInputStream(InputStream inputStream, SimpleFilter simpleFilter) {
        if (inputStream == null) {
            throw new NullPointerException();
        }
        this.in = inputStream;
        this.simpleFilter = simpleFilter;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMemoryUsage() {
        return 5;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in != null) {
            IOException iOException = this.exception;
            if (iOException == null) {
                return this.filtered;
            }
            throw iOException;
        }
        throw new XZIOException("Stream closed");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.tempBuf, 0, 1) == -1) {
            return -1;
        }
        return this.tempBuf[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        if (this.in != null) {
            IOException iOException = this.exception;
            if (iOException == null) {
                int i4 = 0;
                while (true) {
                    try {
                        int min = Math.min(this.filtered, i2);
                        System.arraycopy(this.filterBuf, this.pos, bArr, i, min);
                        this.pos += min;
                        this.filtered -= min;
                        i += min;
                        i2 -= min;
                        i4 += min;
                        if (this.pos + this.filtered + this.unfiltered == 4096) {
                            System.arraycopy(this.filterBuf, this.pos, this.filterBuf, 0, this.filtered + this.unfiltered);
                            this.pos = 0;
                        }
                        if (i2 == 0 || this.endReached) {
                            break;
                        }
                        int read = this.in.read(this.filterBuf, this.pos + this.filtered + this.unfiltered, 4096 - ((this.pos + this.filtered) + this.unfiltered));
                        if (read == -1) {
                            this.endReached = true;
                            this.filtered = this.unfiltered;
                            this.unfiltered = 0;
                        } else {
                            this.unfiltered += read;
                            this.filtered = this.simpleFilter.code(this.filterBuf, this.pos, this.unfiltered);
                            this.unfiltered -= this.filtered;
                        }
                    } catch (IOException e) {
                        this.exception = e;
                        throw e;
                    }
                }
                if (i4 > 0) {
                    return i4;
                }
                return -1;
            }
            throw iOException;
        }
        throw new XZIOException("Stream closed");
    }
}
