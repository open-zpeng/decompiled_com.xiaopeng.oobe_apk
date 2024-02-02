package com.google.android.flexbox;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.MarginLayoutParamsCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class FlexboxHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INITIAL_CAPACITY = 10;
    private static final long MEASURE_SPEC_WIDTH_MASK = 4294967295L;
    private boolean[] mChildrenFrozen;
    private final FlexContainer mFlexContainer;
    @Nullable
    int[] mIndexToFlexLine;
    @Nullable
    long[] mMeasureSpecCache;
    @Nullable
    private long[] mMeasuredSizeCache;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int extractHigherInt(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int extractLowerInt(long j) {
        return (int) j;
    }

    @VisibleForTesting
    long makeCombinedLong(int i, int i2) {
        return (i & MEASURE_SPEC_WIDTH_MASK) | (i2 << 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlexboxHelper(FlexContainer flexContainer) {
        this.mFlexContainer = flexContainer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] createReorderedIndices(View view, int i, ViewGroup.LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        List<Order> createOrders = createOrders(flexItemCount);
        Order order = new Order();
        if (view != null && (layoutParams instanceof FlexItem)) {
            order.order = ((FlexItem) layoutParams).getOrder();
        } else {
            order.order = 1;
        }
        if (i == -1 || i == flexItemCount) {
            order.index = flexItemCount;
        } else if (i < this.mFlexContainer.getFlexItemCount()) {
            order.index = i;
            while (i < flexItemCount) {
                createOrders.get(i).index++;
                i++;
            }
        } else {
            order.index = flexItemCount;
        }
        createOrders.add(order);
        return sortOrdersIntoReorderedIndices(flexItemCount + 1, createOrders, sparseIntArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] createReorderedIndices(SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        return sortOrdersIntoReorderedIndices(flexItemCount, createOrders(flexItemCount), sparseIntArray);
    }

    @NonNull
    private List<Order> createOrders(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            Order order = new Order();
            order.order = ((FlexItem) this.mFlexContainer.getFlexItemAt(i2).getLayoutParams()).getOrder();
            order.index = i2;
            arrayList.add(order);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isOrderChangedFromLastMeasurement(SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        for (int i = 0; i < flexItemCount; i++) {
            View flexItemAt = this.mFlexContainer.getFlexItemAt(i);
            if (flexItemAt != null && ((FlexItem) flexItemAt.getLayoutParams()).getOrder() != sparseIntArray.get(i)) {
                return true;
            }
        }
        return false;
    }

    private int[] sortOrdersIntoReorderedIndices(int i, List<Order> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (Order order : list) {
            iArr[i2] = order.index;
            sparseIntArray.append(order.index, order.order);
            i2++;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateHorizontalFlexLines(FlexLinesResult flexLinesResult, int i, int i2) {
        calculateFlexLines(flexLinesResult, i, i2, Integer.MAX_VALUE, 0, -1, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateHorizontalFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, @Nullable List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i, i2, i3, i4, -1, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateHorizontalFlexLinesToIndex(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i, i2, i3, 0, i4, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateVerticalFlexLines(FlexLinesResult flexLinesResult, int i, int i2) {
        calculateFlexLines(flexLinesResult, i2, i, Integer.MAX_VALUE, 0, -1, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateVerticalFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, @Nullable List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i2, i, i3, i4, -1, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateVerticalFlexLinesToIndex(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i2, i, i3, 0, i4, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, int i5, @Nullable List<FlexLine> list) {
        int i6;
        FlexLinesResult flexLinesResult2;
        int i7;
        int i8;
        int i9;
        List<FlexLine> list2;
        int i10;
        View view;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17 = i;
        int i18 = i2;
        int i19 = i5;
        boolean isMainAxisDirectionHorizontal = this.mFlexContainer.isMainAxisDirectionHorizontal();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        ArrayList arrayList = list == null ? new ArrayList() : list;
        flexLinesResult.mFlexLines = arrayList;
        boolean z = i19 == -1;
        int paddingStartMain = getPaddingStartMain(isMainAxisDirectionHorizontal);
        int paddingEndMain = getPaddingEndMain(isMainAxisDirectionHorizontal);
        int paddingStartCross = getPaddingStartCross(isMainAxisDirectionHorizontal);
        int paddingEndCross = getPaddingEndCross(isMainAxisDirectionHorizontal);
        FlexLine flexLine = new FlexLine();
        int i20 = i4;
        flexLine.mFirstIndex = i20;
        int i21 = paddingEndMain + paddingStartMain;
        flexLine.mMainSize = i21;
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        boolean z2 = z;
        int i22 = Integer.MIN_VALUE;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        while (true) {
            if (i20 >= flexItemCount) {
                i6 = i24;
                flexLinesResult2 = flexLinesResult;
                break;
            }
            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i20);
            if (reorderedFlexItemAt == null) {
                if (isLastFlexItem(i20, flexItemCount, flexLine)) {
                    addFlexLine(arrayList, flexLine, i20, i23);
                }
            } else if (reorderedFlexItemAt.getVisibility() == 8) {
                flexLine.mGoneItemCount++;
                flexLine.mItemCount++;
                if (isLastFlexItem(i20, flexItemCount, flexLine)) {
                    addFlexLine(arrayList, flexLine, i20, i23);
                }
            } else {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int i26 = flexItemCount;
                if (flexItem.getAlignSelf() == 4) {
                    flexLine.mIndicesAlignSelfStretch.add(Integer.valueOf(i20));
                }
                int flexItemSizeMain = getFlexItemSizeMain(flexItem, isMainAxisDirectionHorizontal);
                if (flexItem.getFlexBasisPercent() != -1.0f && mode == 1073741824) {
                    flexItemSizeMain = Math.round(size * flexItem.getFlexBasisPercent());
                }
                if (isMainAxisDirectionHorizontal) {
                    int childWidthMeasureSpec = this.mFlexContainer.getChildWidthMeasureSpec(i17, i21 + getFlexItemMarginStartMain(flexItem, true) + getFlexItemMarginEndMain(flexItem, true), flexItemSizeMain);
                    i7 = size;
                    i8 = mode;
                    int childHeightMeasureSpec = this.mFlexContainer.getChildHeightMeasureSpec(i18, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, true) + getFlexItemMarginEndCross(flexItem, true) + i23, getFlexItemSizeCross(flexItem, true));
                    reorderedFlexItemAt.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                    updateMeasureCache(i20, childWidthMeasureSpec, childHeightMeasureSpec, reorderedFlexItemAt);
                    i9 = childWidthMeasureSpec;
                } else {
                    i7 = size;
                    i8 = mode;
                    int childWidthMeasureSpec2 = this.mFlexContainer.getChildWidthMeasureSpec(i18, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, false) + getFlexItemMarginEndCross(flexItem, false) + i23, getFlexItemSizeCross(flexItem, false));
                    int childHeightMeasureSpec2 = this.mFlexContainer.getChildHeightMeasureSpec(i17, getFlexItemMarginStartMain(flexItem, false) + i21 + getFlexItemMarginEndMain(flexItem, false), flexItemSizeMain);
                    reorderedFlexItemAt.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                    updateMeasureCache(i20, childWidthMeasureSpec2, childHeightMeasureSpec2, reorderedFlexItemAt);
                    i9 = childHeightMeasureSpec2;
                }
                this.mFlexContainer.updateViewCache(i20, reorderedFlexItemAt);
                checkSizeConstraints(reorderedFlexItemAt, i20);
                i24 = View.combineMeasuredStates(i24, reorderedFlexItemAt.getMeasuredState());
                int i27 = i23;
                int i28 = i21;
                FlexLine flexLine2 = flexLine;
                int i29 = i20;
                list2 = arrayList;
                int i30 = i9;
                if (isWrapRequired(reorderedFlexItemAt, i8, i7, flexLine.mMainSize, getFlexItemMarginEndMain(flexItem, isMainAxisDirectionHorizontal) + getViewMeasuredSizeMain(reorderedFlexItemAt, isMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, isMainAxisDirectionHorizontal), flexItem, i29, i25, arrayList.size())) {
                    if (flexLine2.getItemCountNotGone() > 0) {
                        addFlexLine(list2, flexLine2, i29 > 0 ? i29 - 1 : 0, i27);
                        i16 = flexLine2.mCrossSize + i27;
                    } else {
                        i16 = i27;
                    }
                    if (isMainAxisDirectionHorizontal) {
                        if (flexItem.getHeight() == -1) {
                            FlexContainer flexContainer = this.mFlexContainer;
                            i10 = i2;
                            i20 = i29;
                            view = reorderedFlexItemAt;
                            view.measure(i30, flexContainer.getChildHeightMeasureSpec(i10, flexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i16, flexItem.getHeight()));
                            checkSizeConstraints(view, i20);
                        } else {
                            i10 = i2;
                            view = reorderedFlexItemAt;
                            i20 = i29;
                        }
                    } else {
                        i10 = i2;
                        view = reorderedFlexItemAt;
                        i20 = i29;
                        if (flexItem.getWidth() == -1) {
                            FlexContainer flexContainer2 = this.mFlexContainer;
                            view.measure(flexContainer2.getChildWidthMeasureSpec(i10, flexContainer2.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i16, flexItem.getWidth()), i30);
                            checkSizeConstraints(view, i20);
                        }
                    }
                    flexLine = new FlexLine();
                    flexLine.mItemCount = 1;
                    i11 = i28;
                    flexLine.mMainSize = i11;
                    flexLine.mFirstIndex = i20;
                    i27 = i16;
                    i13 = Integer.MIN_VALUE;
                    i12 = 0;
                } else {
                    i10 = i2;
                    view = reorderedFlexItemAt;
                    i20 = i29;
                    flexLine = flexLine2;
                    i11 = i28;
                    flexLine.mItemCount++;
                    i12 = i25 + 1;
                    i13 = i22;
                }
                int[] iArr = this.mIndexToFlexLine;
                if (iArr != null) {
                    iArr[i20] = list2.size();
                }
                flexLine.mMainSize += getViewMeasuredSizeMain(view, isMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, isMainAxisDirectionHorizontal) + getFlexItemMarginEndMain(flexItem, isMainAxisDirectionHorizontal);
                flexLine.mTotalFlexGrow += flexItem.getFlexGrow();
                flexLine.mTotalFlexShrink += flexItem.getFlexShrink();
                this.mFlexContainer.onNewFlexItemAdded(view, i20, i12, flexLine);
                int max = Math.max(i13, getViewMeasuredSizeCross(view, isMainAxisDirectionHorizontal) + getFlexItemMarginStartCross(flexItem, isMainAxisDirectionHorizontal) + getFlexItemMarginEndCross(flexItem, isMainAxisDirectionHorizontal) + this.mFlexContainer.getDecorationLengthCrossAxis(view));
                flexLine.mCrossSize = Math.max(flexLine.mCrossSize, max);
                if (isMainAxisDirectionHorizontal) {
                    if (this.mFlexContainer.getFlexWrap() != 2) {
                        flexLine.mMaxBaseline = Math.max(flexLine.mMaxBaseline, view.getBaseline() + flexItem.getMarginTop());
                    } else {
                        flexLine.mMaxBaseline = Math.max(flexLine.mMaxBaseline, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.getMarginBottom());
                    }
                }
                i14 = i26;
                if (isLastFlexItem(i20, i14, flexLine)) {
                    addFlexLine(list2, flexLine, i20, i27);
                    i27 += flexLine.mCrossSize;
                }
                i15 = i5;
                if (i15 != -1 && list2.size() > 0) {
                    if (list2.get(list2.size() - 1).mLastIndex >= i15 && i20 >= i15 && !z2) {
                        i27 = -flexLine.getCrossSize();
                        z2 = true;
                    }
                }
                int i31 = i27;
                if (i31 > i3 && z2) {
                    flexLinesResult2 = flexLinesResult;
                    i6 = i24;
                    break;
                }
                i25 = i12;
                i22 = max;
                i23 = i31;
                i20++;
                i17 = i;
                flexItemCount = i14;
                i18 = i10;
                i21 = i11;
                arrayList = list2;
                mode = i8;
                i19 = i15;
                size = i7;
            }
            i7 = size;
            i8 = mode;
            i10 = i18;
            i15 = i19;
            list2 = arrayList;
            i11 = i21;
            i14 = flexItemCount;
            i20++;
            i17 = i;
            flexItemCount = i14;
            i18 = i10;
            i21 = i11;
            arrayList = list2;
            mode = i8;
            i19 = i15;
            size = i7;
        }
        flexLinesResult2.mChildState = i6;
    }

    private int getPaddingStartMain(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingStart();
        }
        return this.mFlexContainer.getPaddingTop();
    }

    private int getPaddingEndMain(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingEnd();
        }
        return this.mFlexContainer.getPaddingBottom();
    }

    private int getPaddingStartCross(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingTop();
        }
        return this.mFlexContainer.getPaddingStart();
    }

    private int getPaddingEndCross(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingBottom();
        }
        return this.mFlexContainer.getPaddingEnd();
    }

    private int getViewMeasuredSizeMain(View view, boolean z) {
        if (z) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    private int getViewMeasuredSizeCross(View view, boolean z) {
        if (z) {
            return view.getMeasuredHeight();
        }
        return view.getMeasuredWidth();
    }

    private int getFlexItemSizeMain(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getWidth();
        }
        return flexItem.getHeight();
    }

    private int getFlexItemSizeCross(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getHeight();
        }
        return flexItem.getWidth();
    }

    private int getFlexItemMarginStartMain(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginLeft();
        }
        return flexItem.getMarginTop();
    }

    private int getFlexItemMarginEndMain(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginRight();
        }
        return flexItem.getMarginBottom();
    }

    private int getFlexItemMarginStartCross(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginTop();
        }
        return flexItem.getMarginLeft();
    }

    private int getFlexItemMarginEndCross(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginBottom();
        }
        return flexItem.getMarginRight();
    }

    private boolean isWrapRequired(View view, int i, int i2, int i3, int i4, FlexItem flexItem, int i5, int i6, int i7) {
        if (this.mFlexContainer.getFlexWrap() == 0) {
            return false;
        }
        if (flexItem.isWrapBefore()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int maxLine = this.mFlexContainer.getMaxLine();
        if (maxLine == -1 || maxLine > i7 + 1) {
            int decorationLengthMainAxis = this.mFlexContainer.getDecorationLengthMainAxis(view, i5, i6);
            if (decorationLengthMainAxis > 0) {
                i4 += decorationLengthMainAxis;
            }
            return i2 < i3 + i4;
        }
        return false;
    }

    private boolean isLastFlexItem(int i, int i2, FlexLine flexLine) {
        return i == i2 - 1 && flexLine.getItemCountNotGone() != 0;
    }

    private void addFlexLine(List<FlexLine> list, FlexLine flexLine, int i, int i2) {
        flexLine.mSumCrossSizeBefore = i2;
        this.mFlexContainer.onNewFlexLineAdded(flexLine);
        flexLine.mLastIndex = i;
        list.add(flexLine);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void checkSizeConstraints(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.getMinWidth()
            r4 = 1
            if (r1 >= r3) goto L1b
            int r1 = r0.getMinWidth()
        L19:
            r3 = r4
            goto L27
        L1b:
            int r3 = r0.getMaxWidth()
            if (r1 <= r3) goto L26
            int r1 = r0.getMaxWidth()
            goto L19
        L26:
            r3 = 0
        L27:
            int r5 = r0.getMinHeight()
            if (r2 >= r5) goto L32
            int r2 = r0.getMinHeight()
            goto L3e
        L32:
            int r5 = r0.getMaxHeight()
            if (r2 <= r5) goto L3d
            int r2 = r0.getMaxHeight()
            goto L3e
        L3d:
            r4 = r3
        L3e:
            if (r4 == 0) goto L55
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.updateMeasureCache(r8, r1, r0, r7)
            com.google.android.flexbox.FlexContainer r0 = r6.mFlexContainer
            r0.updateViewCache(r8, r7)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxHelper.checkSizeConstraints(android.view.View, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void determineMainSize(int i, int i2) {
        determineMainSize(i, i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void determineMainSize(int i, int i2, int i3) {
        int size;
        int paddingLeft;
        int paddingRight;
        ensureChildrenFrozen(this.mFlexContainer.getFlexItemCount());
        if (i3 >= this.mFlexContainer.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.mFlexContainer.getFlexDirection();
        int flexDirection2 = this.mFlexContainer.getFlexDirection();
        if (flexDirection2 == 0 || flexDirection2 == 1) {
            int mode = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
            if (mode != 1073741824) {
                size = this.mFlexContainer.getLargestMainSize();
            }
            paddingLeft = this.mFlexContainer.getPaddingLeft();
            paddingRight = this.mFlexContainer.getPaddingRight();
        } else if (flexDirection2 == 2 || flexDirection2 == 3) {
            int mode2 = View.MeasureSpec.getMode(i2);
            size = View.MeasureSpec.getSize(i2);
            if (mode2 != 1073741824) {
                size = this.mFlexContainer.getLargestMainSize();
            }
            paddingLeft = this.mFlexContainer.getPaddingTop();
            paddingRight = this.mFlexContainer.getPaddingBottom();
        } else {
            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        int i4 = paddingLeft + paddingRight;
        int[] iArr = this.mIndexToFlexLine;
        int i5 = iArr != null ? iArr[i3] : 0;
        List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
        int size2 = flexLinesInternal.size();
        for (int i6 = i5; i6 < size2; i6++) {
            FlexLine flexLine = flexLinesInternal.get(i6);
            if (flexLine.mMainSize < size) {
                expandFlexItems(i, i2, flexLine, size, i4, false);
            } else {
                shrinkFlexItems(i, i2, flexLine, size, i4, false);
            }
        }
    }

    private void ensureChildrenFrozen(int i) {
        boolean[] zArr = this.mChildrenFrozen;
        if (zArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.mChildrenFrozen = new boolean[i];
        } else if (zArr.length < i) {
            int length = zArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mChildrenFrozen = new boolean[i];
        } else {
            Arrays.fill(zArr, false);
        }
    }

    private void expandFlexItems(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        int i5;
        float f;
        int i6;
        double d;
        double d2;
        float f2 = 0.0f;
        if (flexLine.mTotalFlexGrow <= 0.0f || i3 < flexLine.mMainSize) {
            return;
        }
        int i7 = flexLine.mMainSize;
        float f3 = (i3 - flexLine.mMainSize) / flexLine.mTotalFlexGrow;
        flexLine.mMainSize = i4 + flexLine.mDividerLengthInMainSize;
        if (!z) {
            flexLine.mCrossSize = Integer.MIN_VALUE;
        }
        int i8 = 0;
        float f4 = 0.0f;
        boolean z2 = false;
        int i9 = 0;
        while (i8 < flexLine.mItemCount) {
            int i10 = flexLine.mFirstIndex + i8;
            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i10);
            if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                i5 = i7;
                f = f2;
            } else {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.mFlexContainer.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    i5 = i7;
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr = this.mMeasuredSizeCache;
                    if (jArr != null) {
                        measuredWidth = extractLowerInt(jArr[i10]);
                    }
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr2 = this.mMeasuredSizeCache;
                    if (jArr2 != null) {
                        measuredHeight = extractHigherInt(jArr2[i10]);
                    }
                    if (this.mChildrenFrozen[i10]) {
                        f = 0.0f;
                    } else {
                        f = 0.0f;
                        if (flexItem.getFlexGrow() > 0.0f) {
                            float flexGrow = measuredWidth + (flexItem.getFlexGrow() * f3);
                            if (i8 == flexLine.mItemCount - 1) {
                                flexGrow += f4;
                                f4 = 0.0f;
                            }
                            int round = Math.round(flexGrow);
                            if (round > flexItem.getMaxWidth()) {
                                round = flexItem.getMaxWidth();
                                z2 = true;
                                this.mChildrenFrozen[i10] = true;
                                flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                            } else {
                                f4 += flexGrow - round;
                                double d3 = f4;
                                if (d3 > 1.0d) {
                                    round++;
                                    d = d3 - 1.0d;
                                } else if (d3 < -1.0d) {
                                    round--;
                                    d = d3 + 1.0d;
                                }
                                f4 = (float) d;
                            }
                            int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i2, flexItem, flexLine.mSumCrossSizeBefore);
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, BasicMeasure.EXACTLY);
                            reorderedFlexItemAt.measure(makeMeasureSpec, childHeightMeasureSpecInternal);
                            int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i10, makeMeasureSpec, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i10, reorderedFlexItemAt);
                            measuredWidth = measuredWidth2;
                            measuredHeight = measuredHeight2;
                        }
                    }
                    int max = Math.max(i9, measuredHeight + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.mMainSize += measuredWidth + flexItem.getMarginLeft() + flexItem.getMarginRight();
                    i6 = max;
                } else {
                    int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr3 = this.mMeasuredSizeCache;
                    if (jArr3 != null) {
                        measuredHeight3 = extractHigherInt(jArr3[i10]);
                    }
                    int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr4 = this.mMeasuredSizeCache;
                    if (jArr4 != null) {
                        measuredWidth3 = extractLowerInt(jArr4[i10]);
                    }
                    if (this.mChildrenFrozen[i10] || flexItem.getFlexGrow() <= f2) {
                        i5 = i7;
                    } else {
                        float flexGrow2 = measuredHeight3 + (flexItem.getFlexGrow() * f3);
                        if (i8 == flexLine.mItemCount - 1) {
                            flexGrow2 += f4;
                            f4 = f2;
                        }
                        int round2 = Math.round(flexGrow2);
                        if (round2 > flexItem.getMaxHeight()) {
                            round2 = flexItem.getMaxHeight();
                            this.mChildrenFrozen[i10] = true;
                            flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                            i5 = i7;
                            z2 = true;
                        } else {
                            f4 += flexGrow2 - round2;
                            i5 = i7;
                            double d4 = f4;
                            if (d4 > 1.0d) {
                                round2++;
                                d2 = d4 - 1.0d;
                            } else if (d4 < -1.0d) {
                                round2--;
                                d2 = d4 + 1.0d;
                            }
                            f4 = (float) d2;
                        }
                        int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i, flexItem, flexLine.mSumCrossSizeBefore);
                        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, BasicMeasure.EXACTLY);
                        reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, makeMeasureSpec2);
                        measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                        updateMeasureCache(i10, childWidthMeasureSpecInternal, makeMeasureSpec2, reorderedFlexItemAt);
                        this.mFlexContainer.updateViewCache(i10, reorderedFlexItemAt);
                        measuredHeight3 = measuredHeight4;
                    }
                    i6 = Math.max(i9, measuredWidth3 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.mMainSize += measuredHeight3 + flexItem.getMarginTop() + flexItem.getMarginBottom();
                    f = 0.0f;
                }
                flexLine.mCrossSize = Math.max(flexLine.mCrossSize, i6);
                i9 = i6;
            }
            i8++;
            i7 = i5;
            f2 = f;
        }
        int i11 = i7;
        if (!z2 || i11 == flexLine.mMainSize) {
            return;
        }
        expandFlexItems(i, i2, flexLine, i3, i4, true);
    }

    private void shrinkFlexItems(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int max;
        int i7 = flexLine.mMainSize;
        float f = 0.0f;
        if (flexLine.mTotalFlexShrink <= 0.0f || i3 > flexLine.mMainSize) {
            return;
        }
        float f2 = (flexLine.mMainSize - i3) / flexLine.mTotalFlexShrink;
        flexLine.mMainSize = i4 + flexLine.mDividerLengthInMainSize;
        if (!z) {
            flexLine.mCrossSize = Integer.MIN_VALUE;
        }
        int i8 = 0;
        float f3 = 0.0f;
        boolean z2 = false;
        int i9 = 0;
        while (i8 < flexLine.mItemCount) {
            int i10 = flexLine.mFirstIndex + i8;
            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i10);
            if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                i5 = i8;
            } else {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.mFlexContainer.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    i5 = i8;
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr = this.mMeasuredSizeCache;
                    if (jArr != null) {
                        measuredWidth = extractLowerInt(jArr[i10]);
                    }
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr2 = this.mMeasuredSizeCache;
                    if (jArr2 != null) {
                        measuredHeight = extractHigherInt(jArr2[i10]);
                    }
                    if (this.mChildrenFrozen[i10] || flexItem.getFlexShrink() <= 0.0f) {
                        i6 = measuredWidth;
                    } else {
                        float flexShrink = measuredWidth - (flexItem.getFlexShrink() * f2);
                        if (i5 == flexLine.mItemCount - 1) {
                            flexShrink += f3;
                            f3 = 0.0f;
                        }
                        int round = Math.round(flexShrink);
                        if (round < flexItem.getMinWidth()) {
                            round = flexItem.getMinWidth();
                            this.mChildrenFrozen[i10] = true;
                            flexLine.mTotalFlexShrink -= flexItem.getFlexShrink();
                            z2 = true;
                        } else {
                            f3 += flexShrink - round;
                            double d = f3;
                            if (d > 1.0d) {
                                round++;
                                f3 -= 1.0f;
                            } else if (d < -1.0d) {
                                round--;
                                f3 += 1.0f;
                            }
                        }
                        int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i2, flexItem, flexLine.mSumCrossSizeBefore);
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, BasicMeasure.EXACTLY);
                        reorderedFlexItemAt.measure(makeMeasureSpec, childHeightMeasureSpecInternal);
                        i6 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        updateMeasureCache(i10, makeMeasureSpec, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                        this.mFlexContainer.updateViewCache(i10, reorderedFlexItemAt);
                        measuredHeight = measuredHeight2;
                    }
                    max = Math.max(i9, measuredHeight + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.mMainSize += i6 + flexItem.getMarginLeft() + flexItem.getMarginRight();
                } else {
                    int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr3 = this.mMeasuredSizeCache;
                    if (jArr3 != null) {
                        measuredHeight3 = extractHigherInt(jArr3[i10]);
                    }
                    int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr4 = this.mMeasuredSizeCache;
                    if (jArr4 != null) {
                        measuredWidth2 = extractLowerInt(jArr4[i10]);
                    }
                    if (this.mChildrenFrozen[i10] || flexItem.getFlexShrink() <= f) {
                        i5 = i8;
                    } else {
                        float flexShrink2 = measuredHeight3 - (flexItem.getFlexShrink() * f2);
                        if (i8 == flexLine.mItemCount - 1) {
                            flexShrink2 += f3;
                            f3 = f;
                        }
                        int round2 = Math.round(flexShrink2);
                        if (round2 < flexItem.getMinHeight()) {
                            round2 = flexItem.getMinHeight();
                            this.mChildrenFrozen[i10] = true;
                            flexLine.mTotalFlexShrink -= flexItem.getFlexShrink();
                            i5 = i8;
                            z2 = true;
                        } else {
                            f3 += flexShrink2 - round2;
                            i5 = i8;
                            double d2 = f3;
                            if (d2 > 1.0d) {
                                round2++;
                                f3 -= 1.0f;
                            } else if (d2 < -1.0d) {
                                round2--;
                                f3 += 1.0f;
                            }
                        }
                        int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i, flexItem, flexLine.mSumCrossSizeBefore);
                        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, BasicMeasure.EXACTLY);
                        reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, makeMeasureSpec2);
                        measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                        updateMeasureCache(i10, childWidthMeasureSpecInternal, makeMeasureSpec2, reorderedFlexItemAt);
                        this.mFlexContainer.updateViewCache(i10, reorderedFlexItemAt);
                        measuredHeight3 = measuredHeight4;
                    }
                    max = Math.max(i9, measuredWidth2 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.mMainSize += measuredHeight3 + flexItem.getMarginTop() + flexItem.getMarginBottom();
                }
                flexLine.mCrossSize = Math.max(flexLine.mCrossSize, max);
                i9 = max;
            }
            i8 = i5 + 1;
            f = 0.0f;
        }
        if (!z2 || i7 == flexLine.mMainSize) {
            return;
        }
        shrinkFlexItems(i, i2, flexLine, i3, i4, true);
    }

    private int getChildWidthMeasureSpecInternal(int i, FlexItem flexItem, int i2) {
        FlexContainer flexContainer = this.mFlexContainer;
        int childWidthMeasureSpec = flexContainer.getChildWidthMeasureSpec(i, flexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i2, flexItem.getWidth());
        int size = View.MeasureSpec.getSize(childWidthMeasureSpec);
        if (size > flexItem.getMaxWidth()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return size < flexItem.getMinWidth() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMinWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec)) : childWidthMeasureSpec;
    }

    private int getChildHeightMeasureSpecInternal(int i, FlexItem flexItem, int i2) {
        FlexContainer flexContainer = this.mFlexContainer;
        int childHeightMeasureSpec = flexContainer.getChildHeightMeasureSpec(i, flexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i2, flexItem.getHeight());
        int size = View.MeasureSpec.getSize(childHeightMeasureSpec);
        if (size > flexItem.getMaxHeight()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return size < flexItem.getMinHeight() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMinHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec)) : childHeightMeasureSpec;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void determineCrossSize(int i, int i2, int i3) {
        int mode;
        int size;
        int flexDirection = this.mFlexContainer.getFlexDirection();
        if (flexDirection == 0 || flexDirection == 1) {
            mode = View.MeasureSpec.getMode(i2);
            size = View.MeasureSpec.getSize(i2);
        } else if (flexDirection == 2 || flexDirection == 3) {
            int mode2 = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
            mode = mode2;
        } else {
            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
        if (mode == 1073741824) {
            int sumOfCrossSize = this.mFlexContainer.getSumOfCrossSize() + i3;
            int i4 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).mCrossSize = size - i3;
            } else if (flexLinesInternal.size() >= 2) {
                int alignContent = this.mFlexContainer.getAlignContent();
                if (alignContent == 1) {
                    int i5 = size - sumOfCrossSize;
                    FlexLine flexLine = new FlexLine();
                    flexLine.mCrossSize = i5;
                    flexLinesInternal.add(0, flexLine);
                } else if (alignContent == 2) {
                    this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLinesInternal, size, sumOfCrossSize));
                } else if (alignContent == 3) {
                    if (sumOfCrossSize >= size) {
                        return;
                    }
                    float size2 = (size - sumOfCrossSize) / (flexLinesInternal.size() - 1);
                    ArrayList arrayList = new ArrayList();
                    int size3 = flexLinesInternal.size();
                    float f = 0.0f;
                    while (i4 < size3) {
                        arrayList.add(flexLinesInternal.get(i4));
                        if (i4 != flexLinesInternal.size() - 1) {
                            FlexLine flexLine2 = new FlexLine();
                            if (i4 == flexLinesInternal.size() - 2) {
                                flexLine2.mCrossSize = Math.round(f + size2);
                                f = 0.0f;
                            } else {
                                flexLine2.mCrossSize = Math.round(size2);
                            }
                            f += size2 - flexLine2.mCrossSize;
                            if (f > 1.0f) {
                                flexLine2.mCrossSize++;
                                f -= 1.0f;
                            } else if (f < -1.0f) {
                                flexLine2.mCrossSize--;
                                f += 1.0f;
                            }
                            arrayList.add(flexLine2);
                        }
                        i4++;
                    }
                    this.mFlexContainer.setFlexLines(arrayList);
                } else if (alignContent == 4) {
                    if (sumOfCrossSize >= size) {
                        this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLinesInternal, size, sumOfCrossSize));
                        return;
                    }
                    int size4 = (size - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                    ArrayList arrayList2 = new ArrayList();
                    FlexLine flexLine3 = new FlexLine();
                    flexLine3.mCrossSize = size4;
                    for (FlexLine flexLine4 : flexLinesInternal) {
                        arrayList2.add(flexLine3);
                        arrayList2.add(flexLine4);
                        arrayList2.add(flexLine3);
                    }
                    this.mFlexContainer.setFlexLines(arrayList2);
                } else if (alignContent == 5 && sumOfCrossSize < size) {
                    float size5 = (size - sumOfCrossSize) / flexLinesInternal.size();
                    int size6 = flexLinesInternal.size();
                    float f2 = 0.0f;
                    while (i4 < size6) {
                        FlexLine flexLine5 = flexLinesInternal.get(i4);
                        float f3 = flexLine5.mCrossSize + size5;
                        if (i4 == flexLinesInternal.size() - 1) {
                            f3 += f2;
                            f2 = 0.0f;
                        }
                        int round = Math.round(f3);
                        f2 += f3 - round;
                        if (f2 > 1.0f) {
                            round++;
                            f2 -= 1.0f;
                        } else if (f2 < -1.0f) {
                            round--;
                            f2 += 1.0f;
                        }
                        flexLine5.mCrossSize = round;
                        i4++;
                    }
                }
            }
        }
    }

    private List<FlexLine> constructFlexLinesForAlignContentCenter(List<FlexLine> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        FlexLine flexLine = new FlexLine();
        flexLine.mCrossSize = (i - i2) / 2;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 == 0) {
                arrayList.add(flexLine);
            }
            arrayList.add(list.get(i3));
            if (i3 == list.size() - 1) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stretchViews() {
        stretchViews(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stretchViews(int i) {
        View reorderedFlexItemAt;
        if (i >= this.mFlexContainer.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.mFlexContainer.getFlexDirection();
        if (this.mFlexContainer.getAlignItems() == 4) {
            int[] iArr = this.mIndexToFlexLine;
            List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
            int size = flexLinesInternal.size();
            for (int i2 = iArr != null ? iArr[i] : 0; i2 < size; i2++) {
                FlexLine flexLine = flexLinesInternal.get(i2);
                int i3 = flexLine.mItemCount;
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = flexLine.mFirstIndex + i4;
                    if (i4 < this.mFlexContainer.getFlexItemCount() && (reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i5)) != null && reorderedFlexItemAt.getVisibility() != 8) {
                        FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                        if (flexItem.getAlignSelf() == -1 || flexItem.getAlignSelf() == 4) {
                            if (flexDirection == 0 || flexDirection == 1) {
                                stretchViewVertically(reorderedFlexItemAt, flexLine.mCrossSize, i5);
                            } else if (flexDirection == 2 || flexDirection == 3) {
                                stretchViewHorizontally(reorderedFlexItemAt, flexLine.mCrossSize, i5);
                            } else {
                                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                            }
                        }
                    }
                }
            }
            return;
        }
        for (FlexLine flexLine2 : this.mFlexContainer.getFlexLinesInternal()) {
            for (Integer num : flexLine2.mIndicesAlignSelfStretch) {
                View reorderedFlexItemAt2 = this.mFlexContainer.getReorderedFlexItemAt(num.intValue());
                if (flexDirection == 0 || flexDirection == 1) {
                    stretchViewVertically(reorderedFlexItemAt2, flexLine2.mCrossSize, num.intValue());
                } else {
                    if (flexDirection != 2 && flexDirection != 3) {
                        throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                    }
                    stretchViewHorizontally(reorderedFlexItemAt2, flexLine2.mCrossSize, num.intValue());
                }
            }
        }
    }

    private void stretchViewVertically(View view, int i, int i2) {
        int measuredWidth;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginTop()) - flexItem.getMarginBottom()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinHeight()), flexItem.getMaxHeight());
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr != null) {
            measuredWidth = extractLowerInt(jArr[i2]);
        } else {
            measuredWidth = view.getMeasuredWidth();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, BasicMeasure.EXACTLY);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, BasicMeasure.EXACTLY);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        updateMeasureCache(i2, makeMeasureSpec, makeMeasureSpec2, view);
        this.mFlexContainer.updateViewCache(i2, view);
    }

    private void stretchViewHorizontally(View view, int i, int i2) {
        int measuredHeight;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginLeft()) - flexItem.getMarginRight()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinWidth()), flexItem.getMaxWidth());
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr != null) {
            measuredHeight = extractHigherInt(jArr[i2]);
        } else {
            measuredHeight = view.getMeasuredHeight();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, BasicMeasure.EXACTLY);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, BasicMeasure.EXACTLY);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        updateMeasureCache(i2, makeMeasureSpec2, makeMeasureSpec, view);
        this.mFlexContainer.updateViewCache(i2, view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void layoutSingleChildHorizontal(View view, FlexLine flexLine, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.mCrossSize;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int i6 = i2 + i5;
                    view.layout(i, (i6 - view.getMeasuredHeight()) - flexItem.getMarginBottom(), i3, i6 - flexItem.getMarginBottom());
                    return;
                }
                view.layout(i, (i2 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop(), i3, (i4 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop());
                return;
            } else if (alignItems == 2) {
                int measuredHeight = (((i5 - view.getMeasuredHeight()) + flexItem.getMarginTop()) - flexItem.getMarginBottom()) / 2;
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int i7 = i2 + measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                }
                int i8 = i2 - measuredHeight;
                view.layout(i, i8, i3, view.getMeasuredHeight() + i8);
                return;
            } else if (alignItems == 3) {
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int max = Math.max(flexLine.mMaxBaseline - view.getBaseline(), flexItem.getMarginTop());
                    view.layout(i, i2 + max, i3, i4 + max);
                    return;
                }
                int max2 = Math.max((flexLine.mMaxBaseline - view.getMeasuredHeight()) + view.getBaseline(), flexItem.getMarginBottom());
                view.layout(i, i2 - max2, i3, i4 - max2);
                return;
            } else if (alignItems != 4) {
                return;
            }
        }
        if (this.mFlexContainer.getFlexWrap() != 2) {
            view.layout(i, i2 + flexItem.getMarginTop(), i3, i4 + flexItem.getMarginTop());
        } else {
            view.layout(i, i2 - flexItem.getMarginBottom(), i3, i4 - flexItem.getMarginBottom());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void layoutSingleChildVertical(View view, FlexLine flexLine, boolean z, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.mCrossSize;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (!z) {
                    view.layout(((i + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i2, ((i3 + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i4);
                    return;
                } else {
                    view.layout((i - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i2, (i3 - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i4);
                    return;
                }
            } else if (alignItems == 2) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int measuredWidth = (((i5 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams)) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                if (!z) {
                    view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                    return;
                } else {
                    view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                    return;
                }
            } else if (alignItems != 3 && alignItems != 4) {
                return;
            }
        }
        if (!z) {
            view.layout(i + flexItem.getMarginLeft(), i2, i3 + flexItem.getMarginLeft(), i4);
        } else {
            view.layout(i - flexItem.getMarginRight(), i2, i3 - flexItem.getMarginRight(), i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void ensureMeasuredSizeCache(int i) {
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.mMeasuredSizeCache = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mMeasuredSizeCache = Arrays.copyOf(this.mMeasuredSizeCache, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void ensureMeasureSpecCache(int i) {
        long[] jArr = this.mMeasureSpecCache;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.mMeasureSpecCache = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mMeasureSpecCache = Arrays.copyOf(this.mMeasureSpecCache, i);
        }
    }

    private void updateMeasureCache(int i, int i2, int i3, View view) {
        long[] jArr = this.mMeasureSpecCache;
        if (jArr != null) {
            jArr[i] = makeCombinedLong(i2, i3);
        }
        long[] jArr2 = this.mMeasuredSizeCache;
        if (jArr2 != null) {
            jArr2[i] = makeCombinedLong(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void ensureIndexToFlexLine(int i) {
        int[] iArr = this.mIndexToFlexLine;
        if (iArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.mIndexToFlexLine = new int[i];
        } else if (iArr.length < i) {
            int length = iArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mIndexToFlexLine = Arrays.copyOf(this.mIndexToFlexLine, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearFlexLines(List<FlexLine> list, int i) {
        int i2 = this.mIndexToFlexLine[i];
        if (i2 == -1) {
            i2 = 0;
        }
        for (int size = list.size() - 1; size >= i2; size--) {
            list.remove(size);
        }
        int[] iArr = this.mIndexToFlexLine;
        int length = iArr.length - 1;
        if (i > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i, length, -1);
        }
        long[] jArr = this.mMeasureSpecCache;
        int length2 = jArr.length - 1;
        if (i > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i, length2, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Order implements Comparable<Order> {
        int index;
        int order;

        private Order() {
        }

        @Override // java.lang.Comparable
        public int compareTo(@NonNull Order order) {
            int i = this.order;
            int i2 = order.order;
            return i != i2 ? i - i2 : this.index - order.index;
        }

        public String toString() {
            return "Order{order=" + this.order + ", index=" + this.index + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class FlexLinesResult {
        int mChildState;
        List<FlexLine> mFlexLines;

        /* JADX INFO: Access modifiers changed from: package-private */
        public void reset() {
            this.mFlexLines = null;
            this.mChildState = 0;
        }
    }
}
