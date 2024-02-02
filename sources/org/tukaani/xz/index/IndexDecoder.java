package org.tukaani.xz.index;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.MemoryLimitException;
import org.tukaani.xz.SeekableInputStream;
import org.tukaani.xz.UnsupportedOptionsException;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
/* loaded from: classes2.dex */
public class IndexDecoder extends IndexBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static /* synthetic */ Class class$org$tukaani$xz$index$IndexDecoder;
    private long compressedOffset;
    private long largestBlockSize;
    private final int memoryUsage;
    private int recordOffset;
    private final StreamFlags streamFlags;
    private final long streamPadding;
    private final long[] uncompressed;
    private long uncompressedOffset;
    private final long[] unpadded;

    static {
        if (class$org$tukaani$xz$index$IndexDecoder == null) {
            class$org$tukaani$xz$index$IndexDecoder = class$("org.tukaani.xz.index.IndexDecoder");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    public IndexDecoder(SeekableInputStream seekableInputStream, StreamFlags streamFlags, long j, int i) throws IOException {
        super(new CorruptedInputException("XZ Index is corrupt"));
        long decodeVLI;
        String str;
        String str2 = i;
        String str3 = "XZ Index is corrupt";
        this.largestBlockSize = 0L;
        this.recordOffset = 0;
        this.compressedOffset = 0L;
        this.uncompressedOffset = 0L;
        this.streamFlags = streamFlags;
        this.streamPadding = j;
        long position = (seekableInputStream.position() + streamFlags.backwardSize) - 4;
        CRC32 crc32 = new CRC32();
        CheckedInputStream checkedInputStream = new CheckedInputStream(seekableInputStream, crc32);
        if (checkedInputStream.read() != 0) {
            throw new CorruptedInputException("XZ Index is corrupt");
        }
        try {
            decodeVLI = DecoderUtil.decodeVLI(checkedInputStream);
        } catch (EOFException unused) {
            str2 = "XZ Index is corrupt";
        }
        if (decodeVLI >= streamFlags.backwardSize / 2) {
            throw new CorruptedInputException("XZ Index is corrupt");
        }
        if (decodeVLI > 2147483647L) {
            throw new UnsupportedOptionsException("XZ Index has over 2147483647 Records");
        }
        this.memoryUsage = ((int) (((16 * decodeVLI) + 1023) / 1024)) + 1;
        if (str2 >= 0 && this.memoryUsage > str2) {
            throw new MemoryLimitException(this.memoryUsage, str2);
        }
        int i2 = (int) decodeVLI;
        this.unpadded = new long[i2];
        this.uncompressed = new long[i2];
        int i3 = 0;
        while (i2 > 0) {
            long decodeVLI2 = DecoderUtil.decodeVLI(checkedInputStream);
            long decodeVLI3 = DecoderUtil.decodeVLI(checkedInputStream);
            if (seekableInputStream.position() > position) {
                str = str3;
                throw new CorruptedInputException(str);
            }
            String str4 = str3;
            try {
                this.unpadded[i3] = this.blocksSum + decodeVLI2;
                str = str4;
                try {
                    this.uncompressed[i3] = this.uncompressedSum + decodeVLI3;
                    i3++;
                    super.add(decodeVLI2, decodeVLI3);
                    if (this.largestBlockSize < decodeVLI3) {
                        this.largestBlockSize = decodeVLI3;
                    }
                    i2--;
                    str3 = str;
                } catch (EOFException unused2) {
                    str2 = str;
                }
            } catch (EOFException unused3) {
                str2 = str4;
            }
            str2 = str;
            throw new CorruptedInputException(str2);
        }
        String str5 = str3;
        int indexPaddingSize = getIndexPaddingSize();
        if (seekableInputStream.position() + indexPaddingSize != position) {
            throw new CorruptedInputException(str5);
        }
        while (true) {
            int i4 = indexPaddingSize - 1;
            if (indexPaddingSize <= 0) {
                long value = crc32.getValue();
                for (int i5 = 0; i5 < 4; i5++) {
                    if (((value >>> (i5 * 8)) & 255) != seekableInputStream.read()) {
                        throw new CorruptedInputException(str5);
                    }
                }
                return;
            } else if (checkedInputStream.read() != 0) {
                throw new CorruptedInputException(str5);
            } else {
                indexPaddingSize = i4;
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public long getLargestBlockSize() {
        return this.largestBlockSize;
    }

    public int getMemoryUsage() {
        return this.memoryUsage;
    }

    public int getRecordCount() {
        return (int) this.recordCount;
    }

    public StreamFlags getStreamFlags() {
        return this.streamFlags;
    }

    public long getUncompressedSize() {
        return this.uncompressedSum;
    }

    public boolean hasRecord(int i) {
        int i2 = this.recordOffset;
        return i >= i2 && ((long) i) < ((long) i2) + this.recordCount;
    }

    public boolean hasUncompressedOffset(long j) {
        long j2 = this.uncompressedOffset;
        return j >= j2 && j < j2 + this.uncompressedSum;
    }

    public void locateBlock(BlockInfo blockInfo, long j) {
        long j2 = j - this.uncompressedOffset;
        int i = 0;
        int length = this.unpadded.length - 1;
        while (i < length) {
            int i2 = ((length - i) / 2) + i;
            if (this.uncompressed[i2] <= j2) {
                i = i2 + 1;
            } else {
                length = i2;
            }
        }
        setBlockInfo(blockInfo, this.recordOffset + i);
    }

    public void setBlockInfo(BlockInfo blockInfo, int i) {
        long j;
        blockInfo.index = this;
        blockInfo.blockNumber = i;
        int i2 = i - this.recordOffset;
        if (i2 == 0) {
            j = 0;
            blockInfo.compressedOffset = 0L;
        } else {
            int i3 = i2 - 1;
            blockInfo.compressedOffset = (this.unpadded[i3] + 3) & (-4);
            j = this.uncompressed[i3];
        }
        blockInfo.uncompressedOffset = j;
        blockInfo.unpaddedSize = this.unpadded[i2] - blockInfo.compressedOffset;
        blockInfo.uncompressedSize = this.uncompressed[i2] - blockInfo.uncompressedOffset;
        blockInfo.compressedOffset += this.compressedOffset + 12;
        blockInfo.uncompressedOffset += this.uncompressedOffset;
    }

    public void setOffsets(IndexDecoder indexDecoder) {
        this.recordOffset = indexDecoder.recordOffset + ((int) indexDecoder.recordCount);
        this.compressedOffset = indexDecoder.compressedOffset + indexDecoder.getStreamSize() + indexDecoder.streamPadding;
        this.uncompressedOffset = indexDecoder.uncompressedOffset + indexDecoder.uncompressedSum;
    }
}
