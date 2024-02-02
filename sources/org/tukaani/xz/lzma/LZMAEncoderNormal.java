package org.tukaani.xz.lzma;

import org.apache.commons.compress.archivers.zip.UnixStat;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;
/* loaded from: classes2.dex */
final class LZMAEncoderNormal extends LZMAEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static int EXTRA_SIZE_AFTER = 0;
    private static int EXTRA_SIZE_BEFORE = 0;
    private static final int OPTS = 4096;
    static /* synthetic */ Class class$org$tukaani$xz$lzma$LZMAEncoderNormal;
    private Matches matches;
    private final State nextState;
    private int optCur;
    private int optEnd;
    private final Optimum[] opts;
    private final int[] repLens;

    static {
        if (class$org$tukaani$xz$lzma$LZMAEncoderNormal == null) {
            class$org$tukaani$xz$lzma$LZMAEncoderNormal = class$("org.tukaani.xz.lzma.LZMAEncoderNormal");
        }
        EXTRA_SIZE_BEFORE = 4096;
        EXTRA_SIZE_AFTER = 4096;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LZMAEncoderNormal(RangeEncoder rangeEncoder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        super(rangeEncoder, LZEncoder.getInstance(i4, Math.max(i5, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, i6, LZMA2Options.NICE_LEN_MAX, i7, i8), i, i2, i3, i4, i6);
        this.opts = new Optimum[4096];
        this.optCur = 0;
        this.optEnd = 0;
        this.repLens = new int[4];
        this.nextState = new State();
        for (int i9 = 0; i9 < 4096; i9++) {
            this.opts[i9] = new Optimum();
        }
    }

    private void calc1BytePrices(int i, int i2, int i3, int i4) {
        boolean z;
        int shortRepPrice;
        int i5 = this.lz.getByte(0);
        int i6 = this.lz.getByte(this.opts[this.optCur].reps[0] + 1);
        int price = this.opts[this.optCur].price + this.literalEncoder.getPrice(i5, i6, this.lz.getByte(1), i, this.opts[this.optCur].state);
        if (price < this.opts[this.optCur + 1].price) {
            Optimum[] optimumArr = this.opts;
            int i7 = this.optCur;
            optimumArr[i7 + 1].set1(price, i7, -1);
            z = true;
        } else {
            z = false;
        }
        if (i6 == i5) {
            int i8 = this.opts[this.optCur + 1].optPrev;
            int i9 = this.optCur;
            if ((i8 == i9 || this.opts[i9 + 1].backPrev != 0) && (shortRepPrice = getShortRepPrice(i4, this.opts[this.optCur].state, i2)) <= this.opts[this.optCur + 1].price) {
                Optimum[] optimumArr2 = this.opts;
                int i10 = this.optCur;
                optimumArr2[i10 + 1].set1(shortRepPrice, i10, 0);
                z = true;
            }
        }
        if (z || i6 == i5 || i3 <= 2) {
            return;
        }
        int matchLen = this.lz.getMatchLen(1, this.opts[this.optCur].reps[0], Math.min(this.niceLen, i3 - 1));
        if (matchLen >= 2) {
            this.nextState.set(this.opts[this.optCur].state);
            this.nextState.updateLiteral();
            int longRepAndLenPrice = price + getLongRepAndLenPrice(0, matchLen, this.nextState, (i + 1) & this.posMask);
            int i11 = this.optCur + 1 + matchLen;
            while (true) {
                int i12 = this.optEnd;
                if (i12 >= i11) {
                    break;
                }
                Optimum[] optimumArr3 = this.opts;
                int i13 = i12 + 1;
                this.optEnd = i13;
                optimumArr3[i13].reset();
            }
            if (longRepAndLenPrice < this.opts[i11].price) {
                this.opts[i11].set2(longRepAndLenPrice, this.optCur, 0);
            }
        }
    }

    private int calcLongRepPrices(int i, int i2, int i3, int i4) {
        int i5;
        int min = Math.min(i3, this.niceLen);
        int i6 = 2;
        for (int i7 = 0; i7 < 4; i7++) {
            int matchLen = this.lz.getMatchLen(this.opts[this.optCur].reps[i7], min);
            if (matchLen >= 2) {
                while (true) {
                    int i8 = this.optEnd;
                    i5 = this.optCur;
                    if (i8 >= i5 + matchLen) {
                        break;
                    }
                    Optimum[] optimumArr = this.opts;
                    int i9 = i8 + 1;
                    this.optEnd = i9;
                    optimumArr[i9].reset();
                }
                int longRepPrice = getLongRepPrice(i4, i7, this.opts[i5].state, i2);
                for (int i10 = matchLen; i10 >= 2; i10--) {
                    int price = this.repLenEncoder.getPrice(i10, i2) + longRepPrice;
                    if (price < this.opts[this.optCur + i10].price) {
                        Optimum[] optimumArr2 = this.opts;
                        int i11 = this.optCur;
                        optimumArr2[i11 + i10].set1(price, i11, i7);
                    }
                }
                if (i7 == 0) {
                    i6 = matchLen + 1;
                }
                int i12 = i6;
                int matchLen2 = this.lz.getMatchLen(matchLen + 1, this.opts[this.optCur].reps[i7], Math.min(this.niceLen, (i3 - matchLen) - 1));
                if (matchLen2 >= 2) {
                    int price2 = longRepPrice + this.repLenEncoder.getPrice(matchLen, i2);
                    this.nextState.set(this.opts[this.optCur].state);
                    this.nextState.updateLongRep();
                    int i13 = i + matchLen;
                    int price3 = price2 + this.literalEncoder.getPrice(this.lz.getByte(matchLen, 0), this.lz.getByte(0), this.lz.getByte(matchLen, 1), i13, this.nextState);
                    this.nextState.updateLiteral();
                    int longRepAndLenPrice = price3 + getLongRepAndLenPrice(0, matchLen2, this.nextState, (i13 + 1) & this.posMask);
                    int i14 = this.optCur + matchLen + 1 + matchLen2;
                    while (true) {
                        int i15 = this.optEnd;
                        if (i15 >= i14) {
                            break;
                        }
                        Optimum[] optimumArr3 = this.opts;
                        int i16 = i15 + 1;
                        this.optEnd = i16;
                        optimumArr3[i16].reset();
                    }
                    if (longRepAndLenPrice < this.opts[i14].price) {
                        this.opts[i14].set3(longRepAndLenPrice, this.optCur, i7, matchLen, 0);
                    }
                }
                i6 = i12;
            }
        }
        return i6;
    }

    private void calcNormalMatchPrices(int i, int i2, int i3, int i4, int i5) {
        int i6 = i5;
        if (this.matches.len[this.matches.count - 1] > i3) {
            this.matches.count = 0;
            while (this.matches.len[this.matches.count] < i3) {
                this.matches.count++;
            }
            int[] iArr = this.matches.len;
            Matches matches = this.matches;
            int i7 = matches.count;
            matches.count = i7 + 1;
            iArr[i7] = i3;
        }
        if (this.matches.len[this.matches.count - 1] < i6) {
            return;
        }
        while (this.optEnd < this.optCur + this.matches.len[this.matches.count - 1]) {
            Optimum[] optimumArr = this.opts;
            int i8 = this.optEnd + 1;
            this.optEnd = i8;
            optimumArr[i8].reset();
        }
        int normalMatchPrice = getNormalMatchPrice(i4, this.opts[this.optCur].state);
        int i9 = 0;
        while (i6 > this.matches.len[i9]) {
            i9++;
        }
        while (true) {
            int i10 = this.matches.dist[i9];
            int matchAndLenPrice = getMatchAndLenPrice(normalMatchPrice, i10, i6, i2);
            if (matchAndLenPrice < this.opts[this.optCur + i6].price) {
                Optimum[] optimumArr2 = this.opts;
                int i11 = this.optCur;
                optimumArr2[i11 + i6].set1(matchAndLenPrice, i11, i10 + 4);
            }
            if (i6 == this.matches.len[i9]) {
                int matchLen = this.lz.getMatchLen(i6 + 1, i10, Math.min(this.niceLen, (i3 - i6) - 1));
                if (matchLen >= 2) {
                    this.nextState.set(this.opts[this.optCur].state);
                    this.nextState.updateMatch();
                    int i12 = i + i6;
                    int price = matchAndLenPrice + this.literalEncoder.getPrice(this.lz.getByte(i6, 0), this.lz.getByte(0), this.lz.getByte(i6, 1), i12, this.nextState);
                    this.nextState.updateLiteral();
                    int longRepAndLenPrice = price + getLongRepAndLenPrice(0, matchLen, this.nextState, (i12 + 1) & this.posMask);
                    int i13 = this.optCur + i6 + 1 + matchLen;
                    while (true) {
                        int i14 = this.optEnd;
                        if (i14 >= i13) {
                            break;
                        }
                        Optimum[] optimumArr3 = this.opts;
                        int i15 = i14 + 1;
                        this.optEnd = i15;
                        optimumArr3[i15].reset();
                    }
                    if (longRepAndLenPrice < this.opts[i13].price) {
                        this.opts[i13].set3(longRepAndLenPrice, this.optCur, i10 + 4, i6, 0);
                    }
                }
                i9++;
                if (i9 == this.matches.count) {
                    return;
                }
            }
            i6++;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private int convertOpts() {
        int i = this.optCur;
        this.optEnd = i;
        int i2 = this.opts[i].optPrev;
        while (true) {
            Optimum optimum = this.opts[this.optCur];
            if (optimum.prev1IsLiteral) {
                Optimum[] optimumArr = this.opts;
                optimumArr[i2].optPrev = this.optCur;
                optimumArr[i2].backPrev = -1;
                int i3 = i2 - 1;
                this.optCur = i2;
                if (optimum.hasPrev2) {
                    Optimum[] optimumArr2 = this.opts;
                    optimumArr2[i3].optPrev = i3 + 1;
                    optimumArr2[i3].backPrev = optimum.backPrev2;
                    this.optCur = i3;
                    i2 = optimum.optPrev2;
                } else {
                    i2 = i3;
                }
            }
            int i4 = this.opts[i2].optPrev;
            Optimum[] optimumArr3 = this.opts;
            optimumArr3[i2].optPrev = this.optCur;
            this.optCur = i2;
            if (this.optCur <= 0) {
                this.optCur = optimumArr3[0].optPrev;
                this.back = this.opts[this.optCur].backPrev;
                return this.optCur;
            }
            i2 = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMemoryUsage(int i, int i2, int i3) {
        return LZEncoder.getMemoryUsage(i, Math.max(i2, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, LZMA2Options.NICE_LEN_MAX, i3) + 256;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x014e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void updateOptStateAndReps() {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lzma.LZMAEncoderNormal.updateOptStateAndReps():void");
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder
    int getNextSymbol() {
        int i;
        int shortRepPrice;
        int i2 = this.optCur;
        if (i2 < this.optEnd) {
            int i3 = this.opts[i2].optPrev;
            int i4 = this.optCur;
            int i5 = i3 - i4;
            this.optCur = this.opts[i4].optPrev;
            this.back = this.opts[this.optCur].backPrev;
            return i5;
        }
        this.optCur = 0;
        this.optEnd = 0;
        this.back = -1;
        if (this.readAhead == -1) {
            this.matches = getMatches();
        }
        int min = Math.min(this.lz.getAvail(), (int) LZMA2Options.NICE_LEN_MAX);
        if (min < 2) {
            return 1;
        }
        int i6 = 0;
        for (int i7 = 0; i7 < 4; i7++) {
            this.repLens[i7] = this.lz.getMatchLen(this.reps[i7], min);
            int[] iArr = this.repLens;
            if (iArr[i7] < 2) {
                iArr[i7] = 0;
            } else if (iArr[i7] > iArr[i6]) {
                i6 = i7;
            }
        }
        if (this.repLens[i6] >= this.niceLen) {
            this.back = i6;
            skip(this.repLens[i6] - 1);
            return this.repLens[i6];
        }
        if (this.matches.count > 0) {
            i = this.matches.len[this.matches.count - 1];
            int i8 = this.matches.dist[this.matches.count - 1];
            if (i >= this.niceLen) {
                this.back = i8 + 4;
                skip(i - 1);
                return i;
            }
        } else {
            i = 0;
        }
        int i9 = this.lz.getByte(0);
        int i10 = this.lz.getByte(this.reps[0] + 1);
        if (i >= 2 || i9 == i10 || this.repLens[i6] >= 2) {
            int pos = this.lz.getPos();
            int i11 = pos & this.posMask;
            this.opts[1].set1(this.literalEncoder.getPrice(i9, i10, this.lz.getByte(1), pos, this.state), 0, -1);
            int anyMatchPrice = getAnyMatchPrice(this.state, i11);
            int anyRepPrice = getAnyRepPrice(anyMatchPrice, this.state);
            if (i10 == i9 && (shortRepPrice = getShortRepPrice(anyRepPrice, this.state, i11)) < this.opts[1].price) {
                this.opts[1].set1(shortRepPrice, 0, 0);
            }
            this.optEnd = Math.max(i, this.repLens[i6]);
            if (this.optEnd < 2) {
                this.back = this.opts[1].backPrev;
                return 1;
            }
            updatePrices();
            this.opts[0].state.set(this.state);
            System.arraycopy(this.reps, 0, this.opts[0].reps, 0, 4);
            for (int i12 = this.optEnd; i12 >= 2; i12--) {
                this.opts[i12].reset();
            }
            for (int i13 = 0; i13 < 4; i13++) {
                int i14 = this.repLens[i13];
                if (i14 >= 2) {
                    int longRepPrice = getLongRepPrice(anyRepPrice, i13, this.state, i11);
                    do {
                        int price = this.repLenEncoder.getPrice(i14, i11) + longRepPrice;
                        if (price < this.opts[i14].price) {
                            this.opts[i14].set1(price, 0, i13);
                        }
                        i14--;
                    } while (i14 >= 2);
                }
            }
            int max = Math.max(this.repLens[0] + 1, 2);
            if (max <= i) {
                int normalMatchPrice = getNormalMatchPrice(anyMatchPrice, this.state);
                int i15 = 0;
                while (max > this.matches.len[i15]) {
                    i15++;
                }
                while (true) {
                    int i16 = this.matches.dist[i15];
                    int matchAndLenPrice = getMatchAndLenPrice(normalMatchPrice, i16, max, i11);
                    if (matchAndLenPrice < this.opts[max].price) {
                        this.opts[max].set1(matchAndLenPrice, 0, i16 + 4);
                    }
                    if (max == this.matches.len[i15] && (i15 = i15 + 1) == this.matches.count) {
                        break;
                    }
                    max++;
                }
            }
            int min2 = Math.min(this.lz.getAvail(), (int) UnixStat.PERM_MASK);
            while (true) {
                int i17 = this.optCur + 1;
                this.optCur = i17;
                if (i17 >= this.optEnd) {
                    break;
                }
                this.matches = getMatches();
                if (this.matches.count > 0 && this.matches.len[this.matches.count - 1] >= this.niceLen) {
                    break;
                }
                int i18 = min2 - 1;
                int i19 = pos + 1;
                int i20 = i19 & this.posMask;
                updateOptStateAndReps();
                int anyMatchPrice2 = this.opts[this.optCur].price + getAnyMatchPrice(this.opts[this.optCur].state, i20);
                int anyRepPrice2 = getAnyRepPrice(anyMatchPrice2, this.opts[this.optCur].state);
                calc1BytePrices(i19, i20, i18, anyRepPrice2);
                if (i18 >= 2) {
                    int calcLongRepPrices = calcLongRepPrices(i19, i20, i18, anyRepPrice2);
                    if (this.matches.count > 0) {
                        calcNormalMatchPrices(i19, i20, i18, anyMatchPrice2, calcLongRepPrices);
                    }
                }
                min2 = i18;
                pos = i19;
            }
            return convertOpts();
        }
        return 1;
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder, org.tukaani.xz.lzma.LZMACoder
    public void reset() {
        this.optCur = 0;
        this.optEnd = 0;
        super.reset();
    }
}
