package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        int i2;
        int i3;
        ChainHead[] chainHeadArr;
        if (i == 0) {
            i3 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = 0;
        } else {
            i2 = 2;
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            ChainHead chainHead = chainHeadArr[i4];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
        if (r2.mHorizontalChainStyle == 2) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004a, code lost:
        if (r2.mVerticalChainStyle == 2) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004c, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004e, code lost:
        r5 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x03e7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r38, androidx.constraintlayout.core.LinearSystem r39, int r40, int r41, androidx.constraintlayout.core.widgets.ChainHead r42) {
        /*
            Method dump skipped, instructions count: 1424
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Chain.applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.LinearSystem, int, int, androidx.constraintlayout.core.widgets.ChainHead):void");
    }
}
