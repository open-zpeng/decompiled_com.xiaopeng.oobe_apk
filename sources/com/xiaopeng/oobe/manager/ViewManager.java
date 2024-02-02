package com.xiaopeng.oobe.manager;

import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.module.BaseView;
import com.xiaopeng.oobe.utils.SPUtils;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class ViewManager {
    private static final String TAG = "ViewManager";
    private int mCurrentViewIndex;
    private boolean mIsStoreStep;
    private ArrayList<ViewPair> mOOBEViews;

    public ViewManager() {
        this(true);
    }

    public ViewManager(boolean z) {
        this.mCurrentViewIndex = -1;
        this.mOOBEViews = new ArrayList<>();
        this.mIsStoreStep = z;
    }

    public void addView(BaseView baseView, int i) {
        this.mOOBEViews.add(new ViewPair(baseView, i));
    }

    public BaseView next() {
        if (currentView() != null) {
            currentView().getView().onDestroy();
            int step = currentView().getStep();
            LogUtils.i(TAG, "next: currentStep" + step);
            if (this.mIsStoreStep && step != 0) {
                int oobeFinishStep = SPUtils.getOobeFinishStep();
                LogUtils.i(TAG, "curStep:" + oobeFinishStep + ",spStep:" + oobeFinishStep);
                if (step <= 0 || step >= oobeFinishStep) {
                    SPUtils.setOobeFinishStep(step);
                }
            }
        }
        this.mCurrentViewIndex++;
        if (currentView() == null) {
            return null;
        }
        BaseView view = currentView().getView();
        view.onStart();
        return view;
    }

    public void jumpStatementView() {
        if (currentView() != null) {
            currentView().getView().onDestroy();
            this.mCurrentViewIndex = 4;
            currentView().getView().onStart();
        }
    }

    public void finishOOBE() {
        LogUtils.i(TAG, "finishOOBE");
        if (currentView() != null && currentView().getView() != null) {
            currentView().getView().onDestroy();
        }
        this.mOOBEViews.clear();
    }

    public ViewPair currentView() {
        int i = this.mCurrentViewIndex;
        if (i < 0 || i >= this.mOOBEViews.size()) {
            return null;
        }
        return this.mOOBEViews.get(this.mCurrentViewIndex);
    }

    public void reset() {
        this.mCurrentViewIndex = -1;
    }

    public int getCurrentViewIndex() {
        return this.mCurrentViewIndex;
    }

    public void setCurrentView(int i) {
        if (currentView() != null) {
            currentView().getView().onDestroy();
            this.mCurrentViewIndex = i;
            currentView().getView().onStart();
        }
    }

    /* loaded from: classes.dex */
    public static class ViewPair {
        int step;
        BaseView view;

        public ViewPair(BaseView baseView, int i) {
            this.view = baseView;
            this.step = i;
        }

        public BaseView getView() {
            if (this.view != null) {
                LogUtils.i(ViewManager.TAG, "getView: " + this.view.toString());
            }
            return this.view;
        }

        public int getStep() {
            return this.step;
        }
    }
}
