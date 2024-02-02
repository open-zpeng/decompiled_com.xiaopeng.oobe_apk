package org.tukaani.xz;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BlockInputStream extends InputStream {
    private final Check check;
    private long compressedSizeInHeader;
    private long compressedSizeLimit;
    private InputStream filterChain;
    private final int headerSize;
    private final CountingInputStream inCounted;
    private final DataInputStream inData;
    private long uncompressedSizeInHeader;
    private long uncompressedSize = 0;
    private boolean endReached = false;
    private final byte[] tempBuf = new byte[1];

    public BlockInputStream(InputStream inputStream, Check check, int i, long j, long j2) throws IOException, IndexIndicatorException {
        this.uncompressedSizeInHeader = -1L;
        this.compressedSizeInHeader = -1L;
        this.check = check;
        this.inData = new DataInputStream(inputStream);
        byte[] bArr = new byte[1024];
        this.inData.readFully(bArr, 0, 1);
        if (bArr[0] == 0) {
            throw new IndexIndicatorException();
        }
        this.headerSize = ((bArr[0] & 255) + 1) * 4;
        this.inData.readFully(bArr, 1, this.headerSize - 1);
        int i2 = this.headerSize;
        if (!DecoderUtil.isCRC32Valid(bArr, 0, i2 - 4, i2 - 4)) {
            throw new CorruptedInputException("XZ Block Header is corrupt");
        }
        if ((bArr[1] & 60) != 0) {
            throw new UnsupportedOptionsException("Unsupported options in XZ Block Header");
        }
        int i3 = (bArr[1] & 3) + 1;
        long[] jArr = new long[i3];
        byte[][] bArr2 = new byte[i3];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, 2, this.headerSize - 6);
        try {
            this.compressedSizeLimit = (9223372036854775804L - this.headerSize) - check.getSize();
            if ((bArr[1] & 64) != 0) {
                this.compressedSizeInHeader = DecoderUtil.decodeVLI(byteArrayInputStream);
                if (this.compressedSizeInHeader == 0 || this.compressedSizeInHeader > this.compressedSizeLimit) {
                    throw new CorruptedInputException();
                }
                this.compressedSizeLimit = this.compressedSizeInHeader;
            }
            if ((bArr[1] & 128) != 0) {
                this.uncompressedSizeInHeader = DecoderUtil.decodeVLI(byteArrayInputStream);
            }
            int i4 = 0;
            while (i4 < i3) {
                jArr[i4] = DecoderUtil.decodeVLI(byteArrayInputStream);
                long decodeVLI = DecoderUtil.decodeVLI(byteArrayInputStream);
                int i5 = i3;
                if (decodeVLI > byteArrayInputStream.available()) {
                    throw new CorruptedInputException();
                }
                bArr2[i4] = new byte[(int) decodeVLI];
                byteArrayInputStream.read(bArr2[i4]);
                i4++;
                i3 = i5;
            }
            for (int available = byteArrayInputStream.available(); available > 0; available--) {
                if (byteArrayInputStream.read() != 0) {
                    throw new UnsupportedOptionsException("Unsupported options in XZ Block Header");
                }
            }
            if (j != -1) {
                long size = this.headerSize + check.getSize();
                if (size >= j) {
                    throw new CorruptedInputException("XZ Index does not match a Block Header");
                }
                long j3 = j - size;
                if (j3 <= this.compressedSizeLimit) {
                    long j4 = this.compressedSizeInHeader;
                    if (j4 == -1 || j4 == j3) {
                        long j5 = this.uncompressedSizeInHeader;
                        if (j5 != -1 && j5 != j2) {
                            throw new CorruptedInputException("XZ Index does not match a Block Header");
                        }
                        this.compressedSizeLimit = j3;
                        this.compressedSizeInHeader = j3;
                        this.uncompressedSizeInHeader = j2;
                    }
                }
                throw new CorruptedInputException("XZ Index does not match a Block Header");
            }
            FilterDecoder[] filterDecoderArr = new FilterDecoder[jArr.length];
            for (int i6 = 0; i6 < filterDecoderArr.length; i6++) {
                if (jArr[i6] == 33) {
                    filterDecoderArr[i6] = new LZMA2Decoder(bArr2[i6]);
                } else if (jArr[i6] == 3) {
                    filterDecoderArr[i6] = new DeltaDecoder(bArr2[i6]);
                } else if (!BCJDecoder.isBCJFilterID(jArr[i6])) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unknown Filter ID ");
                    stringBuffer.append(jArr[i6]);
                    throw new UnsupportedOptionsException(stringBuffer.toString());
                } else {
                    filterDecoderArr[i6] = new BCJDecoder(jArr[i6], bArr2[i6]);
                }
            }
            RawCoder.validate(filterDecoderArr);
            if (i >= 0) {
                int i7 = 0;
                for (FilterDecoder filterDecoder : filterDecoderArr) {
                    i7 += filterDecoder.getMemoryUsage();
                }
                if (i7 > i) {
                    throw new MemoryLimitException(i7, i);
                }
            }
            this.inCounted = new CountingInputStream(inputStream);
            this.filterChain = this.inCounted;
            for (int length = filterDecoderArr.length - 1; length >= 0; length--) {
                this.filterChain = filterDecoderArr[length].getInputStream(this.filterChain);
            }
        } catch (IOException unused) {
            throw new CorruptedInputException("XZ Block Header is corrupt");
        }
    }

    private void validate() throws IOException {
        long size = this.inCounted.getSize();
        long j = this.compressedSizeInHeader;
        if (j == -1 || j == size) {
            long j2 = this.uncompressedSizeInHeader;
            if (j2 == -1 || j2 == this.uncompressedSize) {
                while (true) {
                    long j3 = 1 + size;
                    if ((size & 3) == 0) {
                        byte[] bArr = new byte[this.check.getSize()];
                        this.inData.readFully(bArr);
                        if (Arrays.equals(this.check.finish(), bArr)) {
                            return;
                        }
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Integrity check (");
                        stringBuffer.append(this.check.getName());
                        stringBuffer.append(") does not match");
                        throw new CorruptedInputException(stringBuffer.toString());
                    } else if (this.inData.readUnsignedByte() != 0) {
                        throw new CorruptedInputException();
                    } else {
                        size = j3;
                    }
                }
            }
        }
        throw new CorruptedInputException();
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.filterChain.available();
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }

    public long getUnpaddedSize() {
        return this.headerSize + this.inCounted.getSize() + this.check.getSize();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.tempBuf, 0, 1) == -1) {
            return -1;
        }
        return this.tempBuf[0] & 255;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
        if (r0 == (-1)) goto L25;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int read(byte[] r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            boolean r0 = r7.endReached
            r1 = -1
            if (r0 == 0) goto L6
            return r1
        L6:
            java.io.InputStream r0 = r7.filterChain
            int r0 = r0.read(r8, r9, r10)
            r2 = 1
            if (r0 <= 0) goto L5d
            org.tukaani.xz.check.Check r3 = r7.check
            r3.update(r8, r9, r0)
            long r8 = r7.uncompressedSize
            long r3 = (long) r0
            long r8 = r8 + r3
            r7.uncompressedSize = r8
            org.tukaani.xz.CountingInputStream r8 = r7.inCounted
            long r8 = r8.getSize()
            r3 = 0
            int r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r5 < 0) goto L57
            long r5 = r7.compressedSizeLimit
            int r8 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r8 > 0) goto L57
            long r8 = r7.uncompressedSize
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 < 0) goto L57
            long r3 = r7.uncompressedSizeInHeader
            r5 = -1
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 == 0) goto L3e
            int r8 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r8 > 0) goto L57
        L3e:
            if (r0 < r10) goto L48
            long r8 = r7.uncompressedSize
            long r3 = r7.uncompressedSizeInHeader
            int r8 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r8 != 0) goto L64
        L48:
            java.io.InputStream r8 = r7.filterChain
            int r8 = r8.read()
            if (r8 != r1) goto L51
            goto L5f
        L51:
            org.tukaani.xz.CorruptedInputException r8 = new org.tukaani.xz.CorruptedInputException
            r8.<init>()
            throw r8
        L57:
            org.tukaani.xz.CorruptedInputException r8 = new org.tukaani.xz.CorruptedInputException
            r8.<init>()
            throw r8
        L5d:
            if (r0 != r1) goto L64
        L5f:
            r7.validate()
            r7.endReached = r2
        L64:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.BlockInputStream.read(byte[], int, int):int");
    }
}
