package com.xiaopeng.oobe.module.statement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.R;
import com.xiaopeng.oobe.module.BaseView;
import com.xiaopeng.oobe.module.statement.StatementContract;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.view.LoadingDialog;
import com.xiaopeng.privacymanager.PrivacyDialogListener;
import com.xiaopeng.xui.widget.XCheckBox;
/* loaded from: classes.dex */
public class StatementView extends BaseView implements StatementContract.IStatementView, View.OnClickListener {
    private static final String TAG = "StatementView";
    private Button mContinueBtn;
    private RelativeLayout mLayoutRoot;
    private TextView mPrivacyBtn;
    private PrivacyDialogListener mPrivacyDialogListener;
    private View mStatementView;
    private TextView mUserBtn;
    private XCheckBox mUserCheckBox;
    private StatementControl mViewControl;

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void updateTips(CharSequence charSequence, CharSequence charSequence2) {
    }

    @Override // com.xiaopeng.oobe.module.BaseView
    public void initViews() {
        this.mLayoutRoot = (RelativeLayout) findViewById(R.id.activity_oobe_layout_root);
        this.mStatementView = LayoutInflater.from(this.mLayoutRoot.getContext()).inflate(R.layout.summary_statement_layout, (ViewGroup) null);
        this.mContinueBtn = (Button) this.mStatementView.findViewById(R.id.continue_btn);
        this.mUserBtn = (TextView) this.mStatementView.findViewById(R.id.view_user_protocol_btn);
        this.mPrivacyBtn = (TextView) this.mStatementView.findViewById(R.id.view_privacy_protocol_btn);
        this.mUserCheckBox = (XCheckBox) this.mStatementView.findViewById(R.id.user_read_check);
        this.mUserCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.oobe.module.statement.-$$Lambda$StatementView$9tRtHjM_H9SJcI811PpY3bRatzA
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                StatementView.this.lambda$initViews$0$StatementView(compoundButton, z);
            }
        });
        this.mUserCheckBox.setOnClickListener(this);
        this.mUserBtn.setOnClickListener(this);
        this.mPrivacyBtn.setOnClickListener(this);
        this.mContinueBtn.setOnClickListener(this);
        this.mLayoutRoot.addView(this.mStatementView);
        this.mPrivacyDialogListener = new PrivacyDialogListener() { // from class: com.xiaopeng.oobe.module.statement.StatementView.1
            @Override // com.xiaopeng.privacymanager.PrivacyDialogListener
            public void onDialogClosed(int i, boolean z, int i2) {
                LogUtils.i(StatementView.TAG, "onDialogClosed");
            }

            @Override // com.xiaopeng.privacymanager.PrivacyDialogListener
            public void onDialogShown(int i) {
                LogUtils.i(StatementView.TAG, "onDialogShown");
                StatementView.this.hideLoadingDialog();
            }
        };
        updateBtn(false);
        String string = App.getInstance().getString(R.string.privacy_name_prefix, new Object[]{PrivacyHelper.getInstance().getProtocolName(101)});
        String string2 = App.getInstance().getString(R.string.privacy_name_prefix, new Object[]{PrivacyHelper.getInstance().getProtocolName(102)});
        this.mUserBtn.setText(string2);
        this.mPrivacyBtn.setText(string);
        LogUtils.i(TAG, "getProtocolName privayName:" + string);
        LogUtils.i(TAG, "getProtocolName userName:" + string2);
    }

    public /* synthetic */ void lambda$initViews$0$StatementView(CompoundButton compoundButton, boolean z) {
        LogUtils.i(TAG, "onCheckedChanged isChecked:" + z);
        updateBtn(z);
    }

    @Override // com.xiaopeng.oobe.module.BaseView
    public StatementControl getViewControl() {
        if (this.mViewControl == null) {
            this.mViewControl = new StatementControl(this);
        }
        return this.mViewControl;
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.module.IBaseView
    public void onStart() {
        super.onStart();
        getViewControl().onStart();
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.module.IBaseView
    public void onDestroy() {
        this.mLayoutRoot.removeView(this.mStatementView);
        super.onDestroy();
        getViewControl().onDestroy();
        LogUtils.i(TAG, "onDestroy: ");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continue_btn /* 2131296449 */:
                LogUtils.i(TAG, "continue_btn: ");
                agreeBtn();
                return;
            case R.id.user_read_check /* 2131296852 */:
                LogUtils.i(TAG, "user_read_check: ");
                if (PrivacyHelper.getInstance().isConnected()) {
                    return;
                }
                this.mUserCheckBox.setChecked(false);
                showLoadingDialog();
                return;
            case R.id.view_privacy_protocol_btn /* 2131296859 */:
                LogUtils.i(TAG, "view_privacy_protocol_btn: ");
                showLoadingDialog();
                if (PrivacyHelper.getInstance().isConnected()) {
                    PrivacyHelper.getInstance().showPrivacy(this.mPrivacyDialogListener);
                    return;
                }
                return;
            case R.id.view_user_protocol_btn /* 2131296870 */:
                LogUtils.i(TAG, "view_user_protocol_btn: ");
                showLoadingDialog();
                if (PrivacyHelper.getInstance().isConnected()) {
                    PrivacyHelper.getInstance().showUserExpericence(this.mPrivacyDialogListener);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void updateBtn(boolean z) {
        LogUtils.i(TAG, "updateBtn check:" + z);
        this.mContinueBtn.setBackgroundResource(z ? R.drawable.x_btn_real_positive_selector : R.drawable.continue_default_selector);
        this.mContinueBtn.setTextColor(getActivity().getColor(z ? R.color.x_theme_text_06 : R.color.x_theme_text_07));
    }

    private void agreeBtn() {
        boolean isChecked = this.mUserCheckBox.isChecked();
        LogUtils.i(TAG, "agreeBtn check:" + isChecked);
        if (isChecked) {
            onShowNext();
        } else {
            getViewControl().speakNeedSelectCheck();
        }
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void onShowNext() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.statement.-$$Lambda$StatementView$hvjYRECaxZHhDCIKNejhfaSOcdU
            @Override // java.lang.Runnable
            public final void run() {
                StatementView.this.lambda$onShowNext$1$StatementView();
            }
        });
    }

    public /* synthetic */ void lambda$onShowNext$1$StatementView() {
        getActivity().showNext();
    }

    private void showLoadingDialog() {
        LogUtils.i(TAG, "showLoadingDialog");
        LoadingDialog.getInstance().show();
    }

    @Override // com.xiaopeng.oobe.module.statement.StatementContract.View
    public void hideLoadingDialog() {
        LogUtils.i(TAG, "hideLoadingDialog");
        LoadingDialog.getInstance().dismiss();
    }
}
