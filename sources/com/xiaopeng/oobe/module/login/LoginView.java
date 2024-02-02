package com.xiaopeng.oobe.module.login;

import android.annotation.SuppressLint;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.RelativeLayout;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.R;
import com.xiaopeng.oobe.module.BaseView;
import com.xiaopeng.oobe.module.login.LoginContract;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.view.QrCodeView;
/* loaded from: classes.dex */
public class LoginView extends BaseView implements LoginContract.ILoginView {
    public static final String TAG = "LoginView";
    private RelativeLayout mLayoutRoot;
    private QrCodeView mQrCodeView;
    private LoginContract.ViewControl mViewControl;

    @Override // com.xiaopeng.oobe.module.BaseView
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void initViews() {
        this.mLayoutRoot = (RelativeLayout) findViewById(R.id.activity_oobe_layout_root);
        if (OOBEHelper.isSupportOS5()) {
            getActivity().getApertureView().setVisibility(0);
            getActivity().getApertureView().setImageDrawable(getResources().getDrawable(R.drawable.img_square));
        }
        this.mQrCodeView = new QrCodeView(getActivity());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        if (OOBEHelper.isSupportOS5()) {
            layoutParams.addRule(21);
        }
        layoutParams.setMargins(0, 0, getActivity().getResources().getDimensionPixelSize(R.dimen.qr_code_view_end), getActivity().getResources().getDimensionPixelSize(R.dimen.qr_code_view_bottom));
        this.mLayoutRoot.addView(this.mQrCodeView, layoutParams);
        this.mQrCodeView.setQrCodeView(0);
        this.mQrCodeView.setOnRefreshBthClick(new View.OnClickListener() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginView$y93SWGqZRdM8Y0i88bSIc-KtPC0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginView.this.lambda$initViews$0$LoginView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initViews$0$LoginView(View view) {
        getViewControl().refreshQrCode();
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.module.IBaseView
    public void onStart() {
        super.onStart();
        getViewControl().onStart();
        LogUtils.i(TAG, "onStart: ");
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.module.IBaseView
    public void onDestroy() {
        super.onDestroy();
        getActivity().getApertureView().setVisibility(8);
        getViewControl().onDestroy();
        LogUtils.i(TAG, "onDestroy: ");
        QrCodeView qrCodeView = this.mQrCodeView;
        if (qrCodeView != null) {
            this.mLayoutRoot.removeView(qrCodeView);
        }
    }

    @Override // com.xiaopeng.oobe.module.BaseView
    public LoginContract.ViewControl getViewControl() {
        if (this.mViewControl == null) {
            this.mViewControl = new LoginControl(this);
        }
        return this.mViewControl;
    }

    @Override // com.xiaopeng.oobe.module.login.LoginContract.View
    public void setQrCodeView(final int i, final String str) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginView$sErGwRu_FAy9Akytf_GCWtDnqrM
            @Override // java.lang.Runnable
            public final void run() {
                LoginView.this.lambda$setQrCodeView$1$LoginView(i, str);
            }
        });
    }

    public /* synthetic */ void lambda$setQrCodeView$1$LoginView(int i, String str) {
        QrCodeView qrCodeView = this.mQrCodeView;
        if (qrCodeView != null) {
            qrCodeView.setQrCodeView(i);
            if (i == 1) {
                QrCodeView qrCodeView2 = this.mQrCodeView;
                qrCodeView2.setQrCode(XPQRCodeEncoder.syncEncodeQRCode(str, qrCodeView2.getQrCodeViewSize()));
            }
            getActivity().getBtnSkip().setVisibility(i == 2 ? 0 : 8);
        }
    }

    @Override // com.xiaopeng.oobe.module.login.LoginContract.View
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void showLoginSuccess(final boolean z, final String str, final String str2) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginView$6xOtg9lkZ94Kur42-81Agw9OEK4
            @Override // java.lang.Runnable
            public final void run() {
                LoginView.this.lambda$showLoginSuccess$2$LoginView(z, str, str2);
            }
        });
    }

    public /* synthetic */ void lambda$showLoginSuccess$2$LoginView(boolean z, String str, String str2) {
        if (z) {
            if (OOBEHelper.isSupportOS5()) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(App.getInstance().getColor(R.color.x_color_primary)), 0, 12, 33);
                getActivity().getAssistantView().setPText(spannableStringBuilder, App.getInstance().getString(R.string.login_success_tips));
                getActivity().getApertureView().setImageDrawable(getResources().getDrawable(R.drawable.img_circle));
            }
            this.mQrCodeView.showLoginSuccess(str, str2);
            return;
        }
        getActivity().getBtnSkip().setVisibility(0);
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void updateTips(final CharSequence charSequence, final CharSequence charSequence2) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginView$BC6yaBDQMhLBXLGmy7CVK6Px4Bc
            @Override // java.lang.Runnable
            public final void run() {
                LoginView.this.lambda$updateTips$3$LoginView(charSequence, charSequence2);
            }
        });
    }

    public /* synthetic */ void lambda$updateTips$3$LoginView(CharSequence charSequence, CharSequence charSequence2) {
        if (OOBEHelper.isSupportOS5()) {
            getActivity().getAssistantView().setPText(charSequence, charSequence2);
        } else {
            this.mQrCodeView.setExplainTv(charSequence.toString());
        }
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void onShowNext() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginView$z_W58QWtnqd9hTE_gAnrqJpLLmU
            @Override // java.lang.Runnable
            public final void run() {
                LoginView.this.lambda$onShowNext$4$LoginView();
            }
        });
    }

    public /* synthetic */ void lambda$onShowNext$4$LoginView() {
        LogUtils.i(TAG, "set qr code view");
        this.mQrCodeView.setVisibility(8);
        this.mQrCodeView.clear();
        getActivity().showNext();
    }

    public /* synthetic */ void lambda$setWebpVisibility$5$LoginView(int i) {
        getActivity().getWebpView().setVisibility(i);
    }

    @Override // com.xiaopeng.oobe.module.IWebpVisibility
    public void setWebpVisibility(final int i) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.login.-$$Lambda$LoginView$ZTxftQ2HRyos-OWLvXpUqfku28s
            @Override // java.lang.Runnable
            public final void run() {
                LoginView.this.lambda$setWebpVisibility$5$LoginView(i);
            }
        });
    }
}
