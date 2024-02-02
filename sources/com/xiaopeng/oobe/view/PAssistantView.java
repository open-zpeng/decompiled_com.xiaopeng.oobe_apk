package com.xiaopeng.oobe.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.R;
/* loaded from: classes.dex */
public class PAssistantView extends BaseAssistantView {
    private static final String TAG = "PAssistantView";
    private TextView mLoadingView;
    private TextView mTextSubView;
    private TextView mTextView;

    public PAssistantView(Context context) {
        this(context, null);
    }

    public PAssistantView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PAssistantView(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PAssistantView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initView();
    }

    private void initView() {
        this.mTextView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.view_passistant, (ViewGroup) this, true).findViewById(R.id.view_passistant_text);
        this.mLoadingView = (TextView) findViewById(R.id.view_passistant_loading);
        this.mTextSubView = (TextView) findViewById(R.id.view_passistant_text_sub);
    }

    @Override // com.xiaopeng.oobe.view.BaseAssistantView
    public void setPText(final CharSequence charSequence, final CharSequence charSequence2) {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.view.-$$Lambda$PAssistantView$yxq3wButiz0JO_3JL5vaeQ7jG2A
            @Override // java.lang.Runnable
            public final void run() {
                PAssistantView.this.lambda$setPText$0$PAssistantView(charSequence, charSequence2);
            }
        });
    }

    public /* synthetic */ void lambda$setPText$0$PAssistantView(CharSequence charSequence, CharSequence charSequence2) {
        this.mTextView.setText(charSequence);
        this.mTextSubView.setText(charSequence2);
    }

    @Override // com.xiaopeng.oobe.view.BaseAssistantView
    public void setLoading() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.view.-$$Lambda$PAssistantView$uyQCi8qLouK7l3FGqvgU4L4Q1TY
            @Override // java.lang.Runnable
            public final void run() {
                PAssistantView.this.lambda$setLoading$1$PAssistantView();
            }
        });
    }

    public /* synthetic */ void lambda$setLoading$1$PAssistantView() {
        TextView textView = this.mLoadingView;
        if (textView == null) {
            return;
        }
        if (textView.getVisibility() != 0) {
            this.mLoadingView.setVisibility(0);
        }
        if (TextUtils.isEmpty(this.mLoadingView.getText().toString())) {
            this.mLoadingView.setText(".");
        } else if (this.mLoadingView.getText().toString().equals(".")) {
            this.mLoadingView.setText("..");
        } else if (this.mLoadingView.getText().toString().equals("..")) {
            this.mLoadingView.setText("...");
        } else {
            this.mLoadingView.setText("");
        }
    }

    @Override // com.xiaopeng.oobe.view.BaseAssistantView
    public void cancelLoading() {
        LogUtils.i(TAG, "cancelLoading ");
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.view.-$$Lambda$PAssistantView$VPgvZ5lbqPm5X_qrn-bH5PDavNY
            @Override // java.lang.Runnable
            public final void run() {
                PAssistantView.this.lambda$cancelLoading$2$PAssistantView();
            }
        });
    }

    public /* synthetic */ void lambda$cancelLoading$2$PAssistantView() {
        TextView textView = this.mLoadingView;
        if (textView != null) {
            textView.setText("");
            this.mLoadingView.setVisibility(8);
        }
    }
}
