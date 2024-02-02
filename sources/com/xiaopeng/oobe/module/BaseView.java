package com.xiaopeng.oobe.module;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.RelativeLayout;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.BaseActivity;
import com.xiaopeng.oobe.R;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.oobe.viewmodel.IViewControl;
/* loaded from: classes.dex */
public abstract class BaseView implements IBaseView, IView, IQuit {
    protected BaseActivity mActivity = (BaseActivity) App.getInstance().getActivity();
    protected Context mContext;
    protected RelativeLayout mLayoutRoot;

    @Override // com.xiaopeng.oobe.module.IQuit
    public void dismiss() {
    }

    public abstract IViewControl getViewControl();

    public abstract void initViews();

    @Override // com.xiaopeng.oobe.module.IBaseView
    public void onDestroy() {
    }

    @Override // com.xiaopeng.oobe.module.IBaseView
    public void onStop() {
    }

    @Override // com.xiaopeng.oobe.viewmodel.IView
    public void updateTips(CharSequence charSequence, CharSequence charSequence2) {
    }

    @Override // com.xiaopeng.oobe.module.IBaseView
    public void onStart() {
        this.mLayoutRoot = (RelativeLayout) findViewById(R.id.activity_oobe_layout_root);
        this.mContext = this.mLayoutRoot.getContext();
        initViews();
    }

    public BaseActivity getActivity() {
        return this.mActivity;
    }

    public Resources getResources() {
        return App.getInstance().getResources();
    }

    public <T extends View> T findViewById(int i) {
        BaseActivity baseActivity = this.mActivity;
        if (baseActivity != null) {
            return (T) baseActivity.findViewById(i);
        }
        return null;
    }

    @Override // com.xiaopeng.oobe.viewmodel.IView
    public void onShowNext() {
        getActivity().showNext();
    }

    @Override // com.xiaopeng.oobe.viewmodel.IView
    public void onShowUp() {
        getActivity().showUp();
    }

    @Override // com.xiaopeng.oobe.viewmodel.IView
    public void onWebp(int i) {
        BaseActivity baseActivity = this.mActivity;
        if (baseActivity != null) {
            baseActivity.showWebp(i);
        }
    }
}
