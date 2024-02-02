package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.archivers.zip.PKWareExtraHeader;
/* loaded from: classes2.dex */
public class X0017_StrongEncryptionHeader extends PKWareExtraHeader {
    private PKWareExtraHeader.EncryptionAlgorithm algId;
    private int bitlen;
    private byte[] erdData;
    private int flags;
    private int format;
    private PKWareExtraHeader.HashAlgorithm hashAlg;
    private int hashSize;
    private byte[] ivData;
    private byte[] keyBlob;
    private long rcount;
    private byte[] recipientKeyHash;
    private byte[] vCRC32;
    private byte[] vData;

    public X0017_StrongEncryptionHeader() {
        super(new ZipShort(23));
    }

    public long getRecordCount() {
        return this.rcount;
    }

    public PKWareExtraHeader.HashAlgorithm getHashAlgorithm() {
        return this.hashAlg;
    }

    public PKWareExtraHeader.EncryptionAlgorithm getEncryptionAlgorithm() {
        return this.algId;
    }

    public void parseCentralDirectoryFormat(byte[] bArr, int i, int i2) {
        this.format = ZipShort.getValue(bArr, i);
        this.algId = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i + 2));
        this.bitlen = ZipShort.getValue(bArr, i + 4);
        this.flags = ZipShort.getValue(bArr, i + 6);
        this.rcount = ZipLong.getValue(bArr, i + 8);
        if (this.rcount > 0) {
            this.hashAlg = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i + 12));
            this.hashSize = ZipShort.getValue(bArr, i + 14);
            for (int i3 = 0; i3 < this.rcount; i3++) {
                for (int i4 = 0; i4 < this.hashSize; i4++) {
                }
            }
        }
    }

    public void parseFileFormat(byte[] bArr, int i, int i2) {
        int value = ZipShort.getValue(bArr, i);
        this.ivData = new byte[value];
        System.arraycopy(bArr, i + 4, this.ivData, 0, value);
        int i3 = i + value;
        this.format = ZipShort.getValue(bArr, i3 + 6);
        this.algId = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i3 + 8));
        this.bitlen = ZipShort.getValue(bArr, i3 + 10);
        this.flags = ZipShort.getValue(bArr, i3 + 12);
        int value2 = ZipShort.getValue(bArr, i3 + 14);
        this.erdData = new byte[value2];
        int i4 = i3 + 16;
        System.arraycopy(bArr, i4, this.erdData, 0, value2);
        this.rcount = ZipLong.getValue(bArr, i4 + value2);
        System.out.println("rcount: " + this.rcount);
        if (this.rcount == 0) {
            int value3 = ZipShort.getValue(bArr, i3 + 20 + value2);
            int i5 = value3 - 4;
            this.vData = new byte[i5];
            this.vCRC32 = new byte[4];
            int i6 = i3 + 22 + value2;
            System.arraycopy(bArr, i6, this.vData, 0, i5);
            System.arraycopy(bArr, (i6 + value3) - 4, this.vCRC32, 0, 4);
            return;
        }
        this.hashAlg = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i3 + 20 + value2));
        int i7 = i3 + 22 + value2;
        this.hashSize = ZipShort.getValue(bArr, i7);
        int i8 = i3 + 24 + value2;
        int value4 = ZipShort.getValue(bArr, i8);
        int i9 = this.hashSize;
        this.recipientKeyHash = new byte[i9];
        this.keyBlob = new byte[value4 - i9];
        System.arraycopy(bArr, i8, this.recipientKeyHash, 0, i9);
        int i10 = this.hashSize;
        System.arraycopy(bArr, i8 + i10, this.keyBlob, 0, value4 - i10);
        int value5 = ZipShort.getValue(bArr, i3 + 26 + value2 + value4);
        int i11 = value5 - 4;
        this.vData = new byte[i11];
        this.vCRC32 = new byte[4];
        int i12 = i7 + value4;
        System.arraycopy(bArr, i12, this.vData, 0, i11);
        System.arraycopy(bArr, (i12 + value5) - 4, this.vCRC32, 0, 4);
    }

    @Override // org.apache.commons.compress.archivers.zip.PKWareExtraHeader, org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        super.parseFromLocalFileData(bArr, i, i2);
        parseFileFormat(bArr, i, i2);
    }

    @Override // org.apache.commons.compress.archivers.zip.PKWareExtraHeader, org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        super.parseFromCentralDirectoryData(bArr, i, i2);
        parseCentralDirectoryFormat(bArr, i, i2);
    }
}
