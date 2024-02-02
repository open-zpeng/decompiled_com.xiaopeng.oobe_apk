package com.xiaopeng.oobe.module.voiceservice;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.R;
import com.xiaopeng.oobe.module.BaseView;
import com.xiaopeng.oobe.module.voiceservice.VoiceServiceContract;
import com.xiaopeng.oobe.utils.BIHelper;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.xui.app.XDialog;
import com.xiaopeng.xui.app.XDialogInterface;
import com.xiaopeng.xui.widget.XButton;
/* loaded from: classes.dex */
public class VoiceServiceView extends BaseView implements VoiceServiceContract.View, View.OnClickListener {
    private static final String TAG = "oobe-VoiceServiceView";
    public static final int WEBP_ANIM_Y = -150;
    public static final int WEBP_ANIM_Y_POS = 101;
    private XButton mAuthorizedOnce;
    private XButton mAuthorizedSkip;
    private XButton mAuthorizedYes;
    private XDialog mDialog;
    private RelativeLayout mLayoutRoot;
    private XDialog mNotUserDialog;
    private boolean mOneYear = false;
    private TextView mPrivacyDisclaimerBtn;
    private VoiceServiceContract.ViewControl mViewControl;
    private View mVoiceServiceView;

    @Override // com.xiaopeng.oobe.module.BaseView
    public VoiceServiceContract.ViewControl getViewControl() {
        if (this.mViewControl == null) {
            this.mViewControl = new VoiceServiceControl(this);
        }
        return this.mViewControl;
    }

    @Override // com.xiaopeng.oobe.module.BaseView
    public void initViews() {
        getActivity().getBtnSkip().setVisibility(8);
        this.mLayoutRoot = (RelativeLayout) findViewById(R.id.activity_oobe_layout_root);
        float y = getActivity().getWebpView().getY();
        boolean z = y != 101.0f;
        LogUtils.i(TAG, "initViews webpY:" + y + ",needWebPAnim:" + z);
        if (OOBEHelper.isLandScreen() && z) {
            getActivity().getWebpView().animate().translationY(-150.0f).start();
        }
        this.mVoiceServiceView = LayoutInflater.from(this.mLayoutRoot.getContext()).inflate(R.layout.view_voice_service, (ViewGroup) null);
        this.mPrivacyDisclaimerBtn = (TextView) this.mVoiceServiceView.findViewById(R.id.privacy_disclaimer_link);
        this.mAuthorizedYes = (XButton) this.mVoiceServiceView.findViewById(R.id.authorized_yes);
        this.mAuthorizedOnce = (XButton) this.mVoiceServiceView.findViewById(R.id.authorized_once);
        this.mAuthorizedSkip = (XButton) this.mVoiceServiceView.findViewById(R.id.authorized_skip);
        this.mAuthorizedYes.setOnClickListener(this);
        this.mAuthorizedOnce.setOnClickListener(this);
        this.mAuthorizedSkip.setOnClickListener(this);
        this.mPrivacyDisclaimerBtn.setOnClickListener(this);
        this.mLayoutRoot.addView(this.mVoiceServiceView);
        String string = App.getInstance().getString(R.string.privacy_name_prefix, new Object[]{PrivacyHelper.getInstance().getProtocolName(106)});
        LogUtils.i(TAG, "getProtocolName speechDisclaimer:" + string);
        this.mPrivacyDisclaimerBtn.setText(string);
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
        View view = this.mVoiceServiceView;
        if (view != null) {
            this.mLayoutRoot.removeView(view);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtils.i(TAG, "onClick");
        switch (view.getId()) {
            case R.id.authorized_once /* 2131296382 */:
                showAuthorizationDialog(false);
                return;
            case R.id.authorized_skip /* 2131296383 */:
                showNotUserDialog();
                return;
            case R.id.authorized_yes /* 2131296385 */:
                showAuthorizationDialog(true);
                return;
            case R.id.privacy_disclaimer_link /* 2131296676 */:
                PrivacyHelper.getInstance().showSpeechPrivacyDisclaimer(null);
                return;
            default:
                return;
        }
    }

    private void showNext(XDialog xDialog) {
        LogUtils.i(TAG, Constants.Unity.SHOW_NEXT);
        this.mVoiceServiceView.setVisibility(8);
        if (xDialog != null) {
            xDialog.dismiss();
        }
        getViewControl().showSuccess();
    }

    private void showAuthorizationDialog(boolean z) {
        this.mOneYear = z;
        LogUtils.i(TAG, "showAuthorizationDialog oneYear:" + z);
        XDialog xDialog = this.mDialog;
        if (xDialog != null && xDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        if (this.mDialog == null) {
            this.mDialog = new XDialog(getActivity());
        }
        this.mVoiceServiceView.animate().alpha(0.0f).setDuration(100L).start();
        this.mDialog.setCanceledOnTouchOutside(false);
        this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.xiaopeng.oobe.module.voiceservice.-$$Lambda$VoiceServiceView$FqpJnudyuQMEYEVQimy0HxAEPv8
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                VoiceServiceView.this.lambda$showAuthorizationDialog$0$VoiceServiceView(dialogInterface);
            }
        });
        this.mDialog.setNegativeButton(App.getInstance().getString(R.string.dialog_btn_cancel), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.voiceservice.-$$Lambda$VoiceServiceView$Zrf03VvtA05_g7WwVEakl_IpaM4
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VoiceServiceView.this.lambda$showAuthorizationDialog$1$VoiceServiceView(xDialog2, i);
            }
        });
        this.mDialog.setPositiveButton(App.getInstance().getString(R.string.dialog_btn_open), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.voiceservice.-$$Lambda$VoiceServiceView$Qjjfdy0W2gusAopUbkjkKiXXHKQ
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VoiceServiceView.this.lambda$showAuthorizationDialog$2$VoiceServiceView(xDialog2, i);
            }
        });
        this.mDialog.setTitle(App.getInstance().getString(R.string.dialog_title));
        this.mDialog.setMessage(App.getInstance().getString(R.string.dialog_content));
        this.mDialog.show();
    }

    public /* synthetic */ void lambda$showAuthorizationDialog$0$VoiceServiceView(DialogInterface dialogInterface) {
        this.mVoiceServiceView.animate().alpha(1.0f).setDuration(100L).start();
    }

    public /* synthetic */ void lambda$showAuthorizationDialog$1$VoiceServiceView(XDialog xDialog, int i) {
        this.mVoiceServiceView.animate().alpha(1.0f).setDuration(100L).start();
    }

    public /* synthetic */ void lambda$showAuthorizationDialog$2$VoiceServiceView(XDialog xDialog, int i) {
        getViewControl().openMicrophoneMuteAndUseVoiceService(this.mOneYear);
        showNext(this.mDialog);
    }

    private void showNotUserDialog() {
        LogUtils.i(TAG, "showNotUserDialog");
        XDialog xDialog = this.mNotUserDialog;
        if (xDialog != null && xDialog.isShowing()) {
            this.mNotUserDialog.dismiss();
        }
        if (this.mNotUserDialog == null) {
            this.mNotUserDialog = new XDialog(getActivity());
        }
        this.mVoiceServiceView.animate().alpha(0.0f).setDuration(100L).start();
        this.mNotUserDialog.setCanceledOnTouchOutside(false);
        this.mNotUserDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.xiaopeng.oobe.module.voiceservice.-$$Lambda$VoiceServiceView$DrqHa-luwMhU_erQY_q5nww-HTI
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                VoiceServiceView.this.lambda$showNotUserDialog$3$VoiceServiceView(dialogInterface);
            }
        });
        this.mNotUserDialog.setPositiveButton(App.getInstance().getString(R.string.not_user_dialog_btn_again), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.voiceservice.-$$Lambda$VoiceServiceView$DGUpkEAUTb8w7tqhgsH49pw125s
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VoiceServiceView.this.lambda$showNotUserDialog$4$VoiceServiceView(xDialog2, i);
            }
        });
        this.mNotUserDialog.setNegativeButton(App.getInstance().getString(R.string.not_user_dialog_btn_cancel), new XDialogInterface.OnClickListener() { // from class: com.xiaopeng.oobe.module.voiceservice.-$$Lambda$VoiceServiceView$ahWs-RCMMlkEgoiGSWokl31Hw84
            @Override // com.xiaopeng.xui.app.XDialogInterface.OnClickListener
            public final void onClick(XDialog xDialog2, int i) {
                VoiceServiceView.this.lambda$showNotUserDialog$5$VoiceServiceView(xDialog2, i);
            }
        });
        this.mNotUserDialog.setTitle(App.getInstance().getString(R.string.not_user_dialog_title));
        this.mNotUserDialog.setMessage(App.getInstance().getString(R.string.not_user_dialog_content));
        this.mNotUserDialog.show();
    }

    public /* synthetic */ void lambda$showNotUserDialog$3$VoiceServiceView(DialogInterface dialogInterface) {
        this.mVoiceServiceView.animate().alpha(1.0f).setDuration(100L).start();
    }

    public /* synthetic */ void lambda$showNotUserDialog$4$VoiceServiceView(XDialog xDialog, int i) {
        this.mVoiceServiceView.animate().alpha(1.0f).setDuration(100L).start();
    }

    public /* synthetic */ void lambda$showNotUserDialog$5$VoiceServiceView(XDialog xDialog, int i) {
        BIHelper.getInstance().sendVoiceNotUserPage();
        showNext(this.mNotUserDialog);
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

    public /* synthetic */ void lambda$setWebpVisibility$6$VoiceServiceView(int i) {
        getActivity().getWebpView().setVisibility(i);
    }

    @Override // com.xiaopeng.oobe.module.IWebpVisibility
    public void setWebpVisibility(final int i) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.voiceservice.-$$Lambda$VoiceServiceView$IhSI7RpRbGqY5yAOkS5HybofehA
            @Override // java.lang.Runnable
            public final void run() {
                VoiceServiceView.this.lambda$setWebpVisibility$6$VoiceServiceView(i);
            }
        });
    }
}
