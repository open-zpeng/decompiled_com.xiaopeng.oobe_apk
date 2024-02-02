package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.BlockInfo;
import org.tukaani.xz.index.IndexDecoder;
/* loaded from: classes2.dex */
public class SeekableXZInputStream extends SeekableInputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static /* synthetic */ Class class$org$tukaani$xz$SeekableXZInputStream;
    private int blockCount;
    private BlockInputStream blockDecoder;
    private Check check;
    private int checkTypes;
    private final BlockInfo curBlockInfo;
    private long curPos;
    private boolean endReached;
    private IOException exception;
    private SeekableInputStream in;
    private int indexMemoryUsage;
    private long largestBlockSize;
    private final int memoryLimit;
    private final BlockInfo queriedBlockInfo;
    private boolean seekNeeded;
    private long seekPos;
    private final ArrayList streams;
    private final byte[] tempBuf;
    private long uncompressedSize;

    static {
        if (class$org$tukaani$xz$SeekableXZInputStream == null) {
            class$org$tukaani$xz$SeekableXZInputStream = class$("org.tukaani.xz.SeekableXZInputStream");
        }
    }

    public SeekableXZInputStream(SeekableInputStream seekableInputStream) throws IOException {
        this(seekableInputStream, -1);
    }

    public SeekableXZInputStream(SeekableInputStream seekableInputStream, int i) throws IOException {
        this.indexMemoryUsage = 0;
        this.streams = new ArrayList();
        this.checkTypes = 0;
        long j = 0;
        this.uncompressedSize = 0L;
        this.largestBlockSize = 0L;
        this.blockCount = 0;
        this.blockDecoder = null;
        this.curPos = 0L;
        this.seekNeeded = false;
        this.endReached = false;
        this.exception = null;
        int i2 = 1;
        this.tempBuf = new byte[1];
        this.in = seekableInputStream;
        DataInputStream dataInputStream = new DataInputStream(seekableInputStream);
        seekableInputStream.seek(0L);
        byte[] bArr = new byte[XZ.HEADER_MAGIC.length];
        dataInputStream.readFully(bArr);
        if (!Arrays.equals(bArr, XZ.HEADER_MAGIC)) {
            throw new XZFormatException();
        }
        long length = seekableInputStream.length();
        if ((3 & length) != 0) {
            throw new CorruptedInputException("XZ file size is not a multiple of 4 bytes");
        }
        byte[] bArr2 = new byte[12];
        int i3 = i;
        while (true) {
            long j2 = j;
            while (length > j) {
                if (length < 12) {
                    throw new CorruptedInputException();
                }
                long j3 = length - 12;
                seekableInputStream.seek(j3);
                dataInputStream.readFully(bArr2);
                if (bArr2[8] == 0 && bArr2[9] == 0 && bArr2[10] == 0 && bArr2[11] == 0) {
                    j2 += 4;
                    length -= 4;
                    j = 0;
                } else {
                    StreamFlags decodeStreamFooter = DecoderUtil.decodeStreamFooter(bArr2);
                    if (decodeStreamFooter.backwardSize >= j3) {
                        throw new CorruptedInputException("Backward Size in XZ Stream Footer is too big");
                    }
                    this.check = Check.getInstance(decodeStreamFooter.checkType);
                    this.checkTypes |= i2 << decodeStreamFooter.checkType;
                    seekableInputStream.seek(j3 - decodeStreamFooter.backwardSize);
                    try {
                        IndexDecoder indexDecoder = new IndexDecoder(seekableInputStream, decodeStreamFooter, j2, i3);
                        this.indexMemoryUsage += indexDecoder.getMemoryUsage();
                        i3 = i3 >= 0 ? i3 - indexDecoder.getMemoryUsage() : i3;
                        if (this.largestBlockSize < indexDecoder.getLargestBlockSize()) {
                            this.largestBlockSize = indexDecoder.getLargestBlockSize();
                        }
                        long streamSize = indexDecoder.getStreamSize() - 12;
                        if (j3 < streamSize) {
                            throw new CorruptedInputException("XZ Index indicates too big compressed size for the XZ Stream");
                        }
                        length = j3 - streamSize;
                        seekableInputStream.seek(length);
                        dataInputStream.readFully(bArr2);
                        if (!DecoderUtil.areStreamFlagsEqual(DecoderUtil.decodeStreamHeader(bArr2), decodeStreamFooter)) {
                            throw new CorruptedInputException("XZ Stream Footer does not match Stream Header");
                        }
                        this.uncompressedSize += indexDecoder.getUncompressedSize();
                        if (this.uncompressedSize < 0) {
                            throw new UnsupportedOptionsException("XZ file is too big");
                        }
                        this.blockCount += indexDecoder.getRecordCount();
                        if (this.blockCount < 0) {
                            throw new UnsupportedOptionsException("XZ file has over 2147483647 Blocks");
                        }
                        this.streams.add(indexDecoder);
                        j = 0;
                        i2 = 1;
                    } catch (MemoryLimitException e) {
                        int memoryNeeded = e.getMemoryNeeded();
                        int i4 = this.indexMemoryUsage;
                        throw new MemoryLimitException(memoryNeeded + i4, i3 + i4);
                    }
                }
            }
            this.memoryLimit = i3;
            ArrayList arrayList = this.streams;
            IndexDecoder indexDecoder2 = (IndexDecoder) arrayList.get(arrayList.size() - 1);
            int size = this.streams.size() - 2;
            while (size >= 0) {
                IndexDecoder indexDecoder3 = (IndexDecoder) this.streams.get(size);
                indexDecoder3.setOffsets(indexDecoder2);
                size--;
                indexDecoder2 = indexDecoder3;
            }
            ArrayList arrayList2 = this.streams;
            IndexDecoder indexDecoder4 = (IndexDecoder) arrayList2.get(arrayList2.size() - 1);
            this.curBlockInfo = new BlockInfo(indexDecoder4);
            this.queriedBlockInfo = new BlockInfo(indexDecoder4);
            return;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private void initBlockDecoder() throws IOException {
        try {
            this.blockDecoder = null;
            this.blockDecoder = new BlockInputStream(this.in, this.check, this.memoryLimit, this.curBlockInfo.unpaddedSize, this.curBlockInfo.uncompressedSize);
        } catch (IndexIndicatorException unused) {
            throw new CorruptedInputException();
        } catch (MemoryLimitException e) {
            int memoryNeeded = e.getMemoryNeeded();
            int i = this.indexMemoryUsage;
            throw new MemoryLimitException(memoryNeeded + i, this.memoryLimit + i);
        }
    }

    private void locateBlockByNumber(BlockInfo blockInfo, int i) {
        if (i < 0 || i >= this.blockCount) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid XZ Block number: ");
            stringBuffer.append(i);
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        } else if (blockInfo.blockNumber == i) {
        } else {
            int i2 = 0;
            while (true) {
                IndexDecoder indexDecoder = (IndexDecoder) this.streams.get(i2);
                if (indexDecoder.hasRecord(i)) {
                    indexDecoder.setBlockInfo(blockInfo, i);
                    return;
                }
                i2++;
            }
        }
    }

    private void locateBlockByPos(BlockInfo blockInfo, long j) {
        if (j < 0 || j >= this.uncompressedSize) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid uncompressed position: ");
            stringBuffer.append(j);
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }
        int i = 0;
        while (true) {
            IndexDecoder indexDecoder = (IndexDecoder) this.streams.get(i);
            if (indexDecoder.hasUncompressedOffset(j)) {
                indexDecoder.locateBlock(blockInfo, j);
                return;
            }
            i++;
        }
    }

    private void seek() throws IOException {
        if (!this.seekNeeded) {
            if (this.curBlockInfo.hasNext()) {
                this.curBlockInfo.setNext();
                initBlockDecoder();
                return;
            }
            this.seekPos = this.curPos;
        }
        this.seekNeeded = false;
        long j = this.seekPos;
        if (j >= this.uncompressedSize) {
            this.curPos = j;
            this.blockDecoder = null;
            this.endReached = true;
            return;
        }
        this.endReached = false;
        locateBlockByPos(this.curBlockInfo, j);
        if (this.curPos <= this.curBlockInfo.uncompressedOffset || this.curPos > this.seekPos) {
            this.in.seek(this.curBlockInfo.compressedOffset);
            this.check = Check.getInstance(this.curBlockInfo.getCheckType());
            initBlockDecoder();
            this.curPos = this.curBlockInfo.uncompressedOffset;
        }
        long j2 = this.seekPos;
        long j3 = this.curPos;
        if (j2 > j3) {
            long j4 = j2 - j3;
            if (this.blockDecoder.skip(j4) != j4) {
                throw new CorruptedInputException();
            }
            this.curPos = this.seekPos;
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        BlockInputStream blockInputStream;
        if (this.in != null) {
            IOException iOException = this.exception;
            if (iOException == null) {
                if (this.endReached || this.seekNeeded || (blockInputStream = this.blockDecoder) == null) {
                    return 0;
                }
                return blockInputStream.available();
            }
            throw iOException;
        }
        throw new XZIOException("Stream closed");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        SeekableInputStream seekableInputStream = this.in;
        if (seekableInputStream != null) {
            try {
                seekableInputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    public int getBlockCheckType(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return this.queriedBlockInfo.getCheckType();
    }

    public long getBlockCompPos(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return this.queriedBlockInfo.compressedOffset;
    }

    public long getBlockCompSize(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return (this.queriedBlockInfo.unpaddedSize + 3) & (-4);
    }

    public int getBlockCount() {
        return this.blockCount;
    }

    public int getBlockNumber(long j) {
        locateBlockByPos(this.queriedBlockInfo, j);
        return this.queriedBlockInfo.blockNumber;
    }

    public long getBlockPos(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return this.queriedBlockInfo.uncompressedOffset;
    }

    public long getBlockSize(int i) {
        locateBlockByNumber(this.queriedBlockInfo, i);
        return this.queriedBlockInfo.uncompressedSize;
    }

    public int getCheckTypes() {
        return this.checkTypes;
    }

    public int getIndexMemoryUsage() {
        return this.indexMemoryUsage;
    }

    public long getLargestBlockSize() {
        return this.largestBlockSize;
    }

    public int getStreamCount() {
        return this.streams.size();
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long length() {
        return this.uncompressedSize;
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long position() throws IOException {
        if (this.in != null) {
            return this.seekNeeded ? this.seekPos : this.curPos;
        }
        throw new XZIOException("Stream closed");
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
        int i4 = 0;
        if (i2 == 0) {
            return 0;
        }
        if (this.in != null) {
            IOException iOException = this.exception;
            if (iOException == null) {
                try {
                    if (this.seekNeeded) {
                        seek();
                    }
                } catch (IOException e) {
                    e = e;
                    if (e instanceof EOFException) {
                        e = new CorruptedInputException();
                    }
                    this.exception = e;
                    if (i4 == 0) {
                        throw e;
                    }
                }
                if (this.endReached) {
                    return -1;
                }
                while (i2 > 0) {
                    if (this.blockDecoder == null) {
                        seek();
                        if (this.endReached) {
                            break;
                        }
                    }
                    int read = this.blockDecoder.read(bArr, i, i2);
                    if (read > 0) {
                        this.curPos += read;
                        i4 += read;
                        i += read;
                        i2 -= read;
                    } else if (read == -1) {
                        this.blockDecoder = null;
                    }
                }
                return i4;
            }
            throw iOException;
        }
        throw new XZIOException("Stream closed");
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public void seek(long j) throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        if (j >= 0) {
            this.seekPos = j;
            this.seekNeeded = true;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Negative seek position: ");
        stringBuffer.append(j);
        throw new XZIOException(stringBuffer.toString());
    }

    public void seekToBlock(int i) throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        if (i >= 0 && i < this.blockCount) {
            this.seekPos = getBlockPos(i);
            this.seekNeeded = true;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Invalid XZ Block number: ");
        stringBuffer.append(i);
        throw new XZIOException(stringBuffer.toString());
    }
}
