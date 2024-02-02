package com.xiaopeng.oobe.module.vuiauthorizationos5;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.R;
import com.xiaopeng.oobe.module.BaseView;
import com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Contract;
import com.xiaopeng.oobe.utils.BIHelper;
import com.xiaopeng.oobe.utils.DialogUtil;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.view.LoadingDialog;
import com.xiaopeng.xui.app.XDialog;
import com.xiaopeng.xui.app.XDialogInterface;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XCheckBox;
/* loaded from: classes.dex */
public class VuiAuthorizationOS5View extends BaseView implements VuiAuthorizationOS5Contract.View, View.OnClickListener {
    public static final int HEAD_INDEX = 9;
    private static final String TAG = "VuiAuthorizationOS5View";
    private XDialog mAuthorizationDialog;
    private XDialog mCameraDialog;
    private RelativeLayout mLayoutRoot;
    private XDialog mMicDialog;
    private XDialog mNotUserDialog;
    private XCheckBox mUserCheckBox;
    private VuiAuthorizationOS5Control mViewControl;
    private TextView mVoiceProtocolBtn;
    private View mVuiAuthorizationOS5View;
    private XButton mVuiAuthorizedOk;
    private XButton mVuiAuthorizedSkip;
    private XDialog mVuiFullTimeDialog;
    private String voicePrivacy;
    private String vuiPrivacy;

    @Override // com.xiaopeng.oobe.module.BaseView
    public VuiAuthorizationOS5Control getViewControl() {
        if (this.mViewControl == null) {
            this.mViewControl = new VuiAuthorizationOS5Control(this);
        }
        return this.mViewControl;
    }

    @Override // com.xiaopeng.oobe.module.BaseView
    public void initViews() {
        getActivity().getBtnSkip().setVisibility(8);
        this.mLayoutRoot = (RelativeLayout) findViewById(R.id.activity_oobe_layout_root);
        this.mVuiAuthorizationOS5View = LayoutInflater.from(this.mLayoutRoot.getContext()).inflate(R.layout.view_vui_authorized_os5, (ViewGroup) null);
        this.mUserCheckBox = (XCheckBox) this.mVuiAuthorizationOS5View.findViewById(R.id.user_read_check);
        this.mVoiceProtocolBtn = (TextView) this.mVuiAuthorizationOS5View.findViewById(R.id.voice_protocol_btn);
        this.mVuiAuthorizedOk = (XButton) this.mVuiAuthorizationOS5View.findViewById(R.id.vui_authorized_ok);
        this.mVuiAuthorizedSkip = (XButton) this.mVuiAuthorizationOS5View.findViewById(R.id.vui_authorized_skip);
        this.mUserCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$zh0sOr05tOixew5VvKhDlcMucFQ
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                VuiAuthorizationOS5View.lambda$initViews$0(compoundButton, z);
            }
        });
        this.mVoiceProtocolBtn.setOnClickListener(this);
        this.mVuiAuthorizedOk.setOnClickListener(this);
        this.mVuiAuthorizedSkip.setOnClickListener(this);
        this.mLayoutRoot.addView(this.mVuiAuthorizationOS5View);
        this.voicePrivacy = App.getInstance().getString(R.string.privacy_name_prefix, new Object[]{PrivacyHelper.getInstance().getProtocolName(103)});
        this.vuiPrivacy = App.getInstance().getString(R.string.privacy_name_prefix, new Object[]{PrivacyHelper.getInstance().getProtocolName(106)});
        if (OOBEHelper.isLandScreen()) {
            this.mVoiceProtocolBtn.setText(this.voicePrivacy);
        } else if (this.voicePrivacy.length() > 9) {
            String substring = this.voicePrivacy.substring(0, 9);
            this.voicePrivacy.substring(9);
            this.mVoiceProtocolBtn.setText(substring);
        }
        String str = TAG;
        LogUtils.i(str, "getProtocolName voicePrivacy:" + this.voicePrivacy);
        String str2 = TAG;
        LogUtils.i(str2, "getProtocolName userName:" + this.vuiPrivacy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initViews$0(CompoundButton compoundButton, boolean z) {
        String str = TAG;
        LogUtils.i(str, "isChecked: = " + z);
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.module.IBaseView
    public void onStart() {
        super.onStart();
        getViewControl().onStart();
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.module.IBaseView
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy: ");
        getViewControl().onDestroy();
        LoadingDialog.getInstance().dismiss();
        View view = this.mVuiAuthorizationOS5View;
        if (view != null) {
            this.mLayoutRoot.removeView(view);
        }
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        LogUtils.i(TAG, "onClick");
        switch (view.getId()) {
            case R.id.voice_protocol_btn /* 2131296874 */:
                PrivacyHelper.getInstance().showSpeechPlanPolicy(null);
                return;
            case R.id.vui_authorized_ok /* 2131296880 */:
                showVuiFullTimeDialog();
                BIHelper.getInstance().sendData("P30001", "B001");
                return;
            case R.id.vui_authorized_skip /* 2131296881 */:
                showNotUserDialog();
                return;
            default:
                return;
        }
    }

    private void notifyUserChecked() {
        if (this.mUserCheckBox.isChecked()) {
            getViewControl().setUserChecked(true);
        }
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void updateTips(CharSequence charSequence, CharSequence charSequence2) {
        LogUtils.i(TAG, Constants.Unity.UPDATE_TIPS);
        getActivity().getAssistantView().setPText(charSequence, charSequence2);
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void onShowNext() {
        LogUtils.i(TAG, Constants.Unity.SHOW_NEXT);
        getActivity().showNext();
    }

    public /* synthetic */ void lambda$setWebpVisibility$1$VuiAuthorizationOS5View(int i) {
        getActivity().getWebpView().setVisibility(i);
    }

    @Override // com.xiaopeng.oobe.module.IWebpVisibility
    public void setWebpVisibility(final int i) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$GyMleGY0Ldmm5_ziySBYqsctoCo
            @Override // java.lang.Runnable
            public final void run() {
                VuiAuthorizationOS5View.this.lambda$setWebpVisibility$1$VuiAuthorizationOS5View(i);
            }
        });
    }

    @Override // com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5Contract.IVuiAuthorization
    public void onVuiAuthorization(final boolean z) {
        String str = TAG;
        LogUtils.i(str, "onAuthorization success:" + z);
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$c36aga58NVVQ5oxYxNniP151to4
            @Override // java.lang.Runnable
            public final void run() {
                VuiAuthorizationOS5View.this.lambda$onVuiAuthorization$2$VuiAuthorizationOS5View(z);
            }
        });
    }

    public /* synthetic */ void lambda$onVuiAuthorization$2$VuiAuthorizationOS5View(boolean z) {
        LoadingDialog.getInstance().dismiss();
        if (z) {
            notifyUserChecked();
            showMicDialog();
            return;
        }
        onShowNext();
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    private void showNext(XDialog xDialog) {
        LogUtils.i(TAG, Constants.Unity.SHOW_NEXT);
        this.mVuiAuthorizationOS5View.setVisibility(8);
        getActivity().getApertureView().setVisibility(0);
        getActivity().getApertureView().setImageDrawable(App.getInstance().getDrawable(R.drawable.img_circle));
        if (xDialog != null) {
            xDialog.dismiss();
        }
        getViewControl().showSuccess();
    }

    @SuppressLint({"InflateParams"})
    public void showVuiFullTimeDialog() {
        XDialog xDialog = this.mVuiFullTimeDialog;
        if (xDialog != null && xDialog.isShowing()) {
            this.mVuiFullTimeDialog.dismiss();
        }
        if (this.mVuiFullTimeDialog == null) {
            this.mVuiFullTimeDialog = new XDialog(getActivity());
        }
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.view_vui_fulltime_dialog, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_privacy_link);
        textView.setText(this.vuiPrivacy);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$bbkoIru6S8P-PLdM4ZENyWZyLXU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyHelper.getInstance().showSpeechPrivacyDisclaimer(null);
            }
        });
        DialogUtil.showCustomDialog(this.mVuiFullTimeDialog, this.mVuiAuthorizationOS5View, inflate, App.getInstance().getString(R.string.dialog_full_time_title), App.getInstance().getString(R.string.authorization_ok), App.getInstance().getString(R.string.dialog_btn_cancel), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$tA4j1cIUgUibsgN5Z7aKDIF8Rtg
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VuiAuthorizationOS5View.this.lambda$showVuiFullTimeDialog$4$VuiAuthorizationOS5View(xDialog2, i);
            }
        }, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$ICDbVUNuumF3IV2tb8SA-6EoS8w
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VuiAuthorizationOS5View.this.lambda$showVuiFullTimeDialog$5$VuiAuthorizationOS5View(xDialog2, i);
            }
        });
        LogUtils.d(TAG, "showVuiFullTimeDialog");
    }

    public /* synthetic */ void lambda$showVuiFullTimeDialog$4$VuiAuthorizationOS5View(XDialog xDialog, int i) {
        LoadingDialog.getInstance().show();
        getViewControl().vuiAuthorization(true);
        BIHelper.getInstance().sendData("P30002", "B002");
    }

    public /* synthetic */ void lambda$showVuiFullTimeDialog$5$VuiAuthorizationOS5View(XDialog xDialog, int i) {
        this.mVuiAuthorizationOS5View.animate().alpha(1.0f).setDuration(100L).start();
        BIHelper.getInstance().sendData("P30002", "B003");
    }

    public void showMicDialog() {
        XDialog xDialog = this.mMicDialog;
        if (xDialog != null && xDialog.isShowing()) {
            this.mMicDialog.dismiss();
        }
        if (this.mMicDialog == null) {
            this.mMicDialog = new XDialog(getActivity());
        }
        DialogUtil.showDialog(this.mMicDialog, this.mVuiAuthorizationOS5View, App.getInstance().getString(R.string.dialog_title), App.getInstance().getString(R.string.dialog_content), App.getInstance().getString(R.string.dialog_btn_open), App.getInstance().getString(R.string.dialog_btn_cancel), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$ZOCh0LTd_tw1n4bagdYBbcHT0PI
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VuiAuthorizationOS5View.this.lambda$showMicDialog$6$VuiAuthorizationOS5View(xDialog2, i);
            }
        }, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$HCSOYqqkG2mYsXQlATdMlF0iwNA
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VuiAuthorizationOS5View.this.lambda$showMicDialog$7$VuiAuthorizationOS5View(xDialog2, i);
            }
        });
        LogUtils.d(TAG, "showMicDialog");
    }

    public /* synthetic */ void lambda$showMicDialog$6$VuiAuthorizationOS5View(XDialog xDialog, int i) {
        getViewControl().setOpenMicrophoneMute();
        showVuiAuthorizationDialog();
    }

    public /* synthetic */ void lambda$showMicDialog$7$VuiAuthorizationOS5View(XDialog xDialog, int i) {
        this.mVuiAuthorizationOS5View.animate().alpha(1.0f).setDuration(100L).start();
    }

    @SuppressLint({"InflateParams"})
    public void showVuiAuthorizationDialog() {
        XDialog xDialog = this.mAuthorizationDialog;
        if (xDialog != null && xDialog.isShowing()) {
            this.mAuthorizationDialog.dismiss();
        }
        if (this.mAuthorizationDialog == null) {
            this.mAuthorizationDialog = new XDialog(getActivity());
        }
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.view_vui_authorization_dialog, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.btn_one_year);
        View findViewById2 = inflate.findViewById(R.id.btn_once);
        View findViewById3 = inflate.findViewById(R.id.btn_never);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_privacy_link);
        textView.setText(this.vuiPrivacy);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$BFTB6m7paFa9QyltI6TM2aEGL3A
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyHelper.getInstance().showSpeechPrivacyDisclaimer(null);
            }
        });
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$tdQc2QwdlsermUSwnkKwYG_B7V8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VuiAuthorizationOS5View.this.lambda$showVuiAuthorizationDialog$9$VuiAuthorizationOS5View(view);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$eTHfoNCxUaPTKBxU26wCHFlQvAs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VuiAuthorizationOS5View.this.lambda$showVuiAuthorizationDialog$10$VuiAuthorizationOS5View(view);
            }
        });
        findViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$C8_jaw4Kk1PhD3j9dYLxb-dH2yA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VuiAuthorizationOS5View.this.lambda$showVuiAuthorizationDialog$11$VuiAuthorizationOS5View(view);
            }
        });
        DialogUtil.showCustomDialog(this.mAuthorizationDialog, this.mVuiAuthorizationOS5View, inflate, App.getInstance().getString(R.string.dialog_authorization_title));
        LogUtils.d(TAG, "showAuthorizationDialog");
    }

    public /* synthetic */ void lambda$showVuiAuthorizationDialog$9$VuiAuthorizationOS5View(View view) {
        getViewControl().setUseVoiceService(true);
        DialogUtil.dismissDialog(this.mAuthorizationDialog);
        showCameraDialog();
    }

    public /* synthetic */ void lambda$showVuiAuthorizationDialog$10$VuiAuthorizationOS5View(View view) {
        getViewControl().setUseVoiceService(false);
        DialogUtil.dismissDialog(this.mAuthorizationDialog);
        showCameraDialog();
    }

    public /* synthetic */ void lambda$showVuiAuthorizationDialog$11$VuiAuthorizationOS5View(View view) {
        showNotUserDialog();
    }

    public void showNotUserDialog() {
        XDialog xDialog = this.mNotUserDialog;
        if (xDialog != null && xDialog.isShowing()) {
            this.mNotUserDialog.dismiss();
        }
        if (this.mNotUserDialog == null) {
            this.mNotUserDialog = new XDialog(getActivity());
        }
        DialogUtil.showDialog(this.mNotUserDialog, this.mVuiAuthorizationOS5View, App.getInstance().getString(R.string.not_user_dialog_title), App.getInstance().getString(R.string.not_user_dialog_content), App.getInstance().getString(R.string.not_user_dialog_btn_again), App.getInstance().getString(R.string.not_user_dialog_btn_cancel), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$BrJNRHFkJKQrfiDNNDX--vb1Up0
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VuiAuthorizationOS5View.this.lambda$showNotUserDialog$12$VuiAuthorizationOS5View(xDialog2, i);
            }
        }, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$YsJfIgXiphwxnK07vQVoAOWUw44
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VuiAuthorizationOS5View.this.lambda$showNotUserDialog$13$VuiAuthorizationOS5View(xDialog2, i);
            }
        });
        LogUtils.d(TAG, "showNotUseDialog");
    }

    public /* synthetic */ void lambda$showNotUserDialog$12$VuiAuthorizationOS5View(XDialog xDialog, int i) {
        this.mVuiAuthorizationOS5View.animate().alpha(1.0f).setDuration(100L).start();
    }

    public /* synthetic */ void lambda$showNotUserDialog$13$VuiAuthorizationOS5View(XDialog xDialog, int i) {
        BIHelper.getInstance().sendData(BIHelper.PageId.OS5.VOICE_NO_USER_PAGE, "B009");
        showNext(this.mAuthorizationDialog);
    }

    public void showCameraDialog() {
        XDialog xDialog = this.mCameraDialog;
        if (xDialog != null && xDialog.isShowing()) {
            this.mCameraDialog.dismiss();
        }
        if (this.mCameraDialog == null) {
            this.mCameraDialog = new XDialog(getActivity());
        }
        DialogUtil.showDialog(this.mCameraDialog, this.mVuiAuthorizationOS5View, App.getInstance().getString(R.string.dialog_camera_title), App.getInstance().getString(R.string.dialog_camera_content), App.getInstance().getString(R.string.authorized_ok), App.getInstance().getString(R.string.authorized_no), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$5tVoSuLWxPGFc92eHmGS6eingsY
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VuiAuthorizationOS5View.this.lambda$showCameraDialog$14$VuiAuthorizationOS5View(xDialog2, i);
            }
        }, new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.vuiauthorizationos5.-$$Lambda$VuiAuthorizationOS5View$XGWQR41zFKKmDU1i0ehJeUBYRFI
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VuiAuthorizationOS5View.this.lambda$showCameraDialog$15$VuiAuthorizationOS5View(xDialog2, i);
            }
        });
        LogUtils.d(TAG, "showCameraDialog");
    }

    public /* synthetic */ void lambda$showCameraDialog$14$VuiAuthorizationOS5View(XDialog xDialog, int i) {
        getViewControl().setScuDsmSwState(true);
        BIHelper.getInstance().sendData("P30004", "B006");
        showNext(this.mCameraDialog);
    }

    public /* synthetic */ void lambda$showCameraDialog$15$VuiAuthorizationOS5View(XDialog xDialog, int i) {
        BIHelper.getInstance().sendData("P30004", "B007");
        showNext(this.mCameraDialog);
    }
}
