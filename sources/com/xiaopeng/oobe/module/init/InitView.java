package com.xiaopeng.oobe.module.init;

import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.module.BaseView;
import com.xiaopeng.oobe.module.init.InitContract;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.viewmodel.IViewControl;
/* loaded from: classes.dex */
public class InitView extends BaseView implements InitContract.IInitView {
    private static final String TAG = "InitView";
    private IViewControl mViewControl;

    @Override // com.xiaopeng.oobe.module.BaseView
    public IViewControl getViewControl() {
        if (this.mViewControl == null) {
            this.mViewControl = new InitControl(this);
        }
        return this.mViewControl;
    }

    @Override // com.xiaopeng.oobe.module.BaseView
    public void initViews() {
        getActivity().getBtnSkip().setVisibility(8);
        if (OOBEHelper.isSupportOS5()) {
            getActivity().getApertureView().setVisibility(0);
        }
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.module.IBaseView
    public void onStart() {
        super.onStart();
        getActivity().getWebpView().setVisibility(0);
        getViewControl().onStart();
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.module.IBaseView
    public void onDestroy() {
        getActivity().getWebpView().setVisibility(8);
        super.onDestroy();
        getViewControl().onDestroy();
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void updateTips(CharSequence charSequence, CharSequence charSequence2) {
        getActivity().getAssistantView().setPText(charSequence, charSequence2);
    }

    @Override // com.xiaopeng.oobe.module.init.InitContract.View
    public void setLoading() {
        getActivity().getAssistantView().setLoading();
    }

    @Override // com.xiaopeng.oobe.module.init.InitContract.View
    public void cancelLoading() {
        LogUtils.i(TAG, Constants.Unity.CANCEL_LOADING);
        getActivity().getAssistantView().cancelLoading();
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.module.IQuit
    public void dismiss() {
        getActivity().dismiss();
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void onShowNext() {
        getActivity().showNext();
    }
}
