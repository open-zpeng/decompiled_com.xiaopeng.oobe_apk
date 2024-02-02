package com.xiaopeng.oobe.module.xpilot;

import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.R;
import com.xiaopeng.oobe.module.BaseView;
import com.xiaopeng.oobe.module.xpilot.XpilotUserExperienceContract;
import com.xiaopeng.oobe.utils.PrivacyHelper;
/* loaded from: classes.dex */
public class XpilotUserExperienceView extends BaseView implements XpilotUserExperienceContract.IXpilotView, View.OnClickListener {
    private static final int LINK_NUM_END = 75;
    private static final int LINK_NUM_START = 61;
    private static final String TAG = "StatementView";
    private Button mAgreeBtn;
    private TextView mContent;
    private RelativeLayout mLayoutRoot;
    private Button mSkipBtn;
    private XpilotUserExperienceControl mViewControl;
    private View mXpilotView;

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void updateTips(CharSequence charSequence, CharSequence charSequence2) {
    }

    @Override // com.xiaopeng.oobe.module.BaseView
    public void initViews() {
        this.mLayoutRoot = (RelativeLayout) findViewById(R.id.activity_oobe_layout_root);
        this.mXpilotView = LayoutInflater.from(this.mLayoutRoot.getContext()).inflate(R.layout.xpilot_user_experience_layout, (ViewGroup) null);
        this.mAgreeBtn = (Button) this.mXpilotView.findViewById(R.id.xpilot_agree_btn);
        this.mSkipBtn = (Button) this.mXpilotView.findViewById(R.id.xpilot_skip_btn);
        this.mContent = (TextView) this.mXpilotView.findViewById(R.id.xpilot_content);
        this.mAgreeBtn.setOnClickListener(this);
        this.mSkipBtn.setOnClickListener(this);
        this.mLayoutRoot.addView(this.mXpilotView);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mContext.getString(R.string.xpilot_content));
        spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.xiaopeng.oobe.module.xpilot.XpilotUserExperienceView.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                String charSequence = XpilotUserExperienceView.this.mContent.getText().subSequence(61, 75).toString();
                LogUtils.i(XpilotUserExperienceView.TAG, ">>> activate dial num click = " + charSequence);
                PrivacyHelper.getInstance().showXpilotUserExpericence(null);
                XpilotUserExperienceView.this.getViewControl().setXpilotPrivacyRead();
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }
        }, 61, 75, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(App.getInstance().getColor(R.color.qrcode_privacy_policy_btn_color)), 61, 75, 33);
        this.mContent.setMovementMethod(LinkMovementMethod.getInstance());
        this.mContent.setText(spannableStringBuilder);
    }

    @Override // com.xiaopeng.oobe.module.BaseView
    public XpilotUserExperienceControl getViewControl() {
        if (this.mViewControl == null) {
            this.mViewControl = new XpilotUserExperienceControl(this);
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
        this.mLayoutRoot.removeView(this.mXpilotView);
        super.onDestroy();
        getViewControl().onDestroy();
        LogUtils.i(TAG, "onDestroy: ");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.xpilot_agree_btn) {
            LogUtils.i(TAG, "xpilot_agree_btn: ");
            getViewControl().agree();
        } else if (id != R.id.xpilot_skip_btn) {
        } else {
            LogUtils.i(TAG, "xpilot_skip_btn: ");
            onShowNext();
        }
    }

    @Override // com.xiaopeng.oobe.module.BaseView, com.xiaopeng.oobe.viewmodel.IView
    public void onShowNext() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.xpilot.-$$Lambda$XpilotUserExperienceView$qmhgZ659oM6J_lpBKvnYqT5NTog
            @Override // java.lang.Runnable
            public final void run() {
                XpilotUserExperienceView.this.lambda$onShowNext$0$XpilotUserExperienceView();
            }
        });
    }

    public /* synthetic */ void lambda$onShowNext$0$XpilotUserExperienceView() {
        getActivity().showNext();
    }
}
