package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
/* loaded from: classes.dex */
public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    private Encoder() {
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AztecCode encode(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        BitArray stuffBits;
        BitArray bitArray;
        boolean z;
        int i5;
        int i6;
        int i7;
        int i8;
        BitArray encode = new HighLevelEncoder(bArr).encode();
        int size = ((encode.getSize() * i) / 100) + 11;
        int size2 = encode.getSize() + size;
        int i9 = 0;
        int i10 = 1;
        if (i2 != 0) {
            z = i2 < 0;
            i5 = Math.abs(i2);
            if (i5 > (z ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
            i6 = totalBitsInLayer(i5, z);
            i4 = WORD_SIZE[i5];
            int i11 = i6 - (i6 % i4);
            bitArray = stuffBits(encode, i4);
            if (bitArray.getSize() + size > i11) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            if (z && bitArray.getSize() > (i4 << 6)) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
        } else {
            int i12 = 0;
            BitArray bitArray2 = null;
            int i13 = 0;
            while (i13 <= 32) {
                boolean z2 = i13 <= 3 ? i10 : i9;
                int i14 = z2 ? i13 + 1 : i13;
                int i15 = totalBitsInLayer(i14, z2);
                if (size2 <= i15) {
                    if (bitArray2 == null || i12 != WORD_SIZE[i14]) {
                        i4 = WORD_SIZE[i14];
                        stuffBits = stuffBits(encode, i4);
                    } else {
                        int i16 = i12;
                        stuffBits = bitArray2;
                        i4 = i16;
                    }
                    int i17 = i15 - (i15 % i4);
                    if ((!z2 || stuffBits.getSize() <= (i4 << 6)) && stuffBits.getSize() + size <= i17) {
                        bitArray = stuffBits;
                        z = z2;
                        i5 = i14;
                        i6 = i15;
                        i9 = i9;
                    } else {
                        i3 = i10;
                        BitArray bitArray3 = stuffBits;
                        i12 = i4;
                        bitArray2 = bitArray3;
                    }
                } else {
                    i3 = i10;
                }
                i13++;
                i10 = i3;
                i9 = 0;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray generateCheckWords = generateCheckWords(bitArray, i6, i4);
        int size3 = bitArray.getSize() / i4;
        BitArray generateModeMessage = generateModeMessage(z, i5, size3);
        int i18 = (z ? 11 : 14) + (i5 << 2);
        int[] iArr = new int[i18];
        int i19 = 2;
        if (z) {
            for (int i20 = i9; i20 < iArr.length; i20++) {
                iArr[i20] = i20;
            }
            i7 = i18;
        } else {
            int i21 = i18 / 2;
            i7 = i18 + 1 + (((i21 - 1) / 15) * 2);
            int i22 = i7 / 2;
            for (int i23 = i9; i23 < i21; i23++) {
                iArr[(i21 - i23) - i10] = (i22 - i8) - 1;
                iArr[i21 + i23] = (i23 / 15) + i23 + i22 + i10;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i7);
        int i24 = i9;
        int i25 = i24;
        while (i24 < i5) {
            int i26 = ((i5 - i24) << i19) + (z ? 9 : 12);
            int i27 = i9;
            while (i27 < i26) {
                int i28 = i27 << 1;
                while (i9 < i19) {
                    if (generateCheckWords.get(i25 + i28 + i9)) {
                        int i29 = i24 << 1;
                        bitMatrix.set(iArr[i29 + i9], iArr[i29 + i27]);
                    }
                    if (generateCheckWords.get((i26 << 1) + i25 + i28 + i9)) {
                        int i30 = i24 << 1;
                        bitMatrix.set(iArr[i30 + i27], iArr[((i18 - 1) - i30) - i9]);
                    }
                    if (generateCheckWords.get((i26 << 2) + i25 + i28 + i9)) {
                        int i31 = (i18 - 1) - (i24 << 1);
                        bitMatrix.set(iArr[i31 - i9], iArr[i31 - i27]);
                    }
                    if (generateCheckWords.get((i26 * 6) + i25 + i28 + i9)) {
                        int i32 = i24 << 1;
                        bitMatrix.set(iArr[((i18 - 1) - i32) - i27], iArr[i32 + i9]);
                    }
                    i9++;
                    i19 = 2;
                }
                i27++;
                i9 = 0;
                i19 = 2;
            }
            i25 += i26 << 3;
            i24++;
            i9 = 0;
            i19 = 2;
        }
        drawModeMessage(bitMatrix, z, i7, generateModeMessage);
        if (z) {
            drawBullsEye(bitMatrix, i7 / 2, 5);
        } else {
            int i33 = i7 / 2;
            drawBullsEye(bitMatrix, i33, 7);
            int i34 = 0;
            int i35 = 0;
            while (i34 < (i18 / 2) - 1) {
                for (int i36 = i33 & 1; i36 < i7; i36 += 2) {
                    int i37 = i33 - i35;
                    bitMatrix.set(i37, i36);
                    int i38 = i33 + i35;
                    bitMatrix.set(i38, i36);
                    bitMatrix.set(i36, i37);
                    bitMatrix.set(i36, i38);
                }
                i34 += 15;
                i35 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i7);
        aztecCode.setLayers(i5);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 <= i6) {
                    bitMatrix.set(i5, i4);
                    bitMatrix.set(i5, i6);
                    bitMatrix.set(i4, i5);
                    bitMatrix.set(i6, i5);
                    i5++;
                }
            }
        }
        int i7 = i - i2;
        bitMatrix.set(i7, i7);
        int i8 = i7 + 1;
        bitMatrix.set(i8, i7);
        bitMatrix.set(i7, i8);
        int i9 = i + i2;
        bitMatrix.set(i9, i7);
        bitMatrix.set(i9, i8);
        bitMatrix.set(i9, i9 - 1);
    }

    static BitArray generateModeMessage(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i - 1, 2);
            bitArray.appendBits(i2 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(i - 1, 5);
        bitArray.appendBits(i2 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (bitArray.get(i3)) {
                    bitMatrix.set(i4, i2 - 5);
                }
                if (bitArray.get(i3 + 7)) {
                    bitMatrix.set(i2 + 5, i4);
                }
                if (bitArray.get(20 - i3)) {
                    bitMatrix.set(i4, i2 + 5);
                }
                if (bitArray.get(27 - i3)) {
                    bitMatrix.set(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (bitArray.get(i3)) {
                bitMatrix.set(i5, i2 - 7);
            }
            if (bitArray.get(i3 + 10)) {
                bitMatrix.set(i2 + 7, i5);
            }
            if (bitArray.get(29 - i3)) {
                bitMatrix.set(i5, i2 + 7);
            }
            if (bitArray.get(39 - i3)) {
                bitMatrix.set(i2 - 7, i5);
            }
            i3++;
        }
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i2));
        int i3 = i / i2;
        int[] bitsToWords = bitsToWords(bitArray, i2, i3);
        reedSolomonEncoder.encode(bitsToWords, i3 - (bitArray.getSize() / i2));
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i % i2);
        for (int i4 : bitsToWords) {
            bitArray2.appendBits(i4, i2);
        }
        return bitArray2;
    }

    private static int[] bitsToWords(BitArray bitArray, int i, int i2) {
        int[] iArr = new int[i2];
        int size = bitArray.getSize() / i;
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= bitArray.get((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    private static GenericGF getGF(int i) {
        if (i != 4) {
            if (i != 6) {
                if (i != 8) {
                    if (i != 10) {
                        if (i == 12) {
                            return GenericGF.AZTEC_DATA_12;
                        }
                        throw new IllegalArgumentException("Unsupported word size ".concat(String.valueOf(i)));
                    }
                    return GenericGF.AZTEC_DATA_10;
                }
                return GenericGF.AZTEC_DATA_8;
            }
            return GenericGF.AZTEC_DATA_6;
        }
        return GenericGF.AZTEC_PARAM;
    }

    static BitArray stuffBits(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < size) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= size || bitArray.get(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                bitArray2.appendBits(i7, i);
            } else if (i7 == 0) {
                bitArray2.appendBits(i4 | 1, i);
            } else {
                bitArray2.appendBits(i4, i);
                i3 += i;
            }
            i3--;
            i3 += i;
        }
        return bitArray2;
    }
}
