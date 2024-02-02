package com.xiaopeng.oobe.module.vuiauthorization;

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
import com.xiaopeng.oobe.module.vuiauthorization.VuiAuthorizationContract;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.view.LoadingDialog;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XCheckBox;
/* loaded from: classes.dex */
public class VuiAuthorizationView extends BaseView implements VuiAuthorizationContract.View, View.OnClickListener {
    public static final int HEAD_INDEX = 9;
    private static final String TAG = "oobe-VuiAuthorizationView";
    public static final int WEBP_ANIM_Y = -150;
    private RelativeLayout mLayoutRoot;
    private XCheckBox mUserCheckBox;
    private VuiAuthorizationControl mViewControl;
    private TextView mVoiceProtocolBtn;
    private TextView mVoiceProtocolFooterBtn;
    private View mVuiAuthorizationView;
    private XButton mVuiAuthorizedOk;
    private XButton mVuiAuthorizedSkip;
    private TextView mVuiPrivacyBtn;

    @Override // com.xiaopeng.oobe.module.BaseView
    public VuiAuthorizationControl getViewControl() {
        if (this.mViewControl == null) {
            this.mViewControl = new VuiAuthorizationControl(this);
        }
        return this.mViewControl;
    }

    @Override // com.xiaopeng.oobe.module.BaseView
    public void initViews() {
        getActivity().getBtnSkip().setVisibility(8);
        this.mLayoutRoot = (RelativeLayout) findViewById(R.id.activity_oobe_layout_root);
        if (OOBEHelper.isLandScreen()) {
            getActivity().getWebpView().animate().translationY(-150.0f).start();
        }
        this.mVuiAuthorizationView = LayoutInflater.from(this.mLayoutRoot.getContext()).inflate(R.layout.view_vui_authorized, (ViewGroup) null);
        this.mVuiAuthorizedOk = (XButton) this.mVuiAuthorizationView.findViewById(R.id.vui_authorized_yes);
        this.mVuiAuthorizedSkip = (XButton) this.mVuiAuthorizationView.findViewById(R.id.vui_authorized_skip);
        this.mUserCheckBox = (XCheckBox) this.mVuiAuthorizationView.findViewById(R.id.user_read_check);
        this.mVoiceProtocolBtn = (TextView) this.mVuiAuthorizationView.findViewById(R.id.voice_protocol_btn);
        this.mVoiceProtocolFooterBtn = (TextView) this.mVuiAuthorizationView.findViewById(R.id.voice_protocol_btn_footer);
        this.mVuiPrivacyBtn = (TextView) this.mVuiAuthorizationView.findViewById(R.id.vui_speech_privacy_link);
        this.mUserCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.oobe.module.vuiauthorization.-$$Lambda$VuiAuthorizationView$GDkrUTkxxxfXHCHTxMuaEuD65U0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtils.i(VuiAuthorizationView.TAG, "isChecked: = " + z);
            }
        });
        this.mVuiAuthorizedOk.setOnClickListener(this);
        this.mVuiAuthorizedSkip.setOnClickListener(this);
        this.mVoiceProtocolBtn.setOnClickListener(this);
        this.mVoiceProtocolFooterBtn.setOnClickListener(this);
        this.mVuiPrivacyBtn.setOnClickListener(this);
        this.mLayoutRoot.addView(this.mVuiAuthorizationView);
        String string = App.getInstance().getString(R.string.privacy_name_prefix, new Object[]{PrivacyHelper.getInstance().getProtocolName(103)});
        String string2 = App.getInstance().getString(R.string.privacy_name_prefix, new Object[]{PrivacyHelper.getInstance().getProtocolName(104)});
        if (OOBEHelper.isLandScreen()) {
            this.mVoiceProtocolBtn.setText(string);
        } else if (string.length() > 9) {
            String substring = string.substring(0, 9);
            String substring2 = string.substring(9);
            this.mVoiceProtocolBtn.setText(substring);
            this.mVoiceProtocolFooterBtn.setText(substring2);
        }
        LogUtils.i(TAG, "getProtocolName voicePrivacy:" + string);
        this.mVuiPrivacyBtn.setText(string2);
        LogUtils.i(TAG, "getProtocolName userName:" + string2);
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
        View view = this.mVuiAuthorizationView;
        if (view != null) {
            this.mLayoutRoot.removeView(view);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtils.i(TAG, "onClick");
        switch (view.getId()) {
            case R.id.voice_protocol_btn /* 2131296874 */:
            case R.id.voice_protocol_btn_footer /* 2131296875 */:
                PrivacyHelper.getInstance().showSpeechPlanPolicy(null);
                return;
            case R.id.vui_authorized_skip /* 2131296881 */:
                getViewControl().vuiAuthorization(false);
                LoadingDialog.getInstance().show();
                return;
            case R.id.vui_authorized_yes /* 2131296883 */:
                getViewControl().vuiAuthorization(true);
                LoadingDialog.getInstance().show();
                return;
            case R.id.vui_speech_privacy_link /* 2131296884 */:
                PrivacyHelper.getInstance().showVuiSpeechPolicy(null);
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

    public /* synthetic */ void lambda$setWebpVisibility$1$VuiAuthorizationView(int i) {
        getActivity().getWebpView().setVisibility(i);
    }

    @Override // com.xiaopeng.oobe.module.IWebpVisibility
    public void setWebpVisibility(final int i) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorization.-$$Lambda$VuiAuthorizationView$hxud0gt8l-QnCPBFSZ6Qy3K_6DE
            @Override // java.lang.Runnable
            public final void run() {
                VuiAuthorizationView.this.lambda$setWebpVisibility$1$VuiAuthorizationView(i);
            }
        });
    }

    @Override // com.xiaopeng.oobe.module.vuiauthorization.VuiAuthorizationContract.IVuiAuthorization
    public void onVuiAuthorization(final boolean z) {
        LogUtils.i(TAG, "onAuthorization success:" + z);
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.vuiauthorization.-$$Lambda$VuiAuthorizationView$n4eRfpvr1adWT-Szgb54gwl7Gdc
            @Override // java.lang.Runnable
            public final void run() {
                VuiAuthorizationView.this.lambda$onVuiAuthorization$2$VuiAuthorizationView(z);
            }
        });
    }

    public /* synthetic */ void lambda$onVuiAuthorization$2$VuiAuthorizationView(boolean z) {
        LoadingDialog.getInstance().dismiss();
        if (z) {
            notifyUserChecked();
        }
        onShowNext();
    }
}
