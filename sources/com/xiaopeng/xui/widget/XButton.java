package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xui.utils.FlavorUtils;
import com.xiaopeng.xui.utils.XBackgroundPaddingUtils;
import com.xiaopeng.xui.view.XViewDelegate;
import com.xiaopeng.xui.vui.VuiView;
/* loaded from: classes2.dex */
public class XButton extends AppCompatButton implements VuiView {
    private Rect mRectBgPadding;
    protected XViewDelegate mXViewDelegate;
    private boolean pressAnimEnable;

    public XButton(Context context) {
        super(context);
        this.pressAnimEnable = true;
    }

    public XButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.pressAnimEnable = true;
        this.mXViewDelegate = XViewDelegate.create(this, attributeSet);
        init(attributeSet);
    }

    public XButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pressAnimEnable = true;
        this.mXViewDelegate = XViewDelegate.create(this, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        initVui(this, attributeSet);
        this.mRectBgPadding = XBackgroundPaddingUtils.backgroundPadding(this, attributeSet);
        XViewDelegate xViewDelegate = this.mXViewDelegate;
        if (xViewDelegate == null || xViewDelegate.getThemeViewModel() == null) {
            return;
        }
        this.mXViewDelegate.getThemeViewModel().setCallback(new ThemeViewModel.OnCallback() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XButton$bQLWIPstTakjCA8X4JBkR4xO3_s
            @Override // com.xiaopeng.libtheme.ThemeViewModel.OnCallback
            public final void onThemeChanged() {
                XButton.this.lambda$init$1$XButton();
            }
        });
    }

    public /* synthetic */ void lambda$init$1$XButton() {
        post(new Runnable() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XButton$W4DwNpm_x2Hg_uCeAR1B-dyN6Nk
            @Override // java.lang.Runnable
            public final void run() {
                XButton.this.lambda$null$0$XButton();
            }
        });
    }

    public /* synthetic */ void lambda$null$0$XButton() {
        if (this.mRectBgPadding != null) {
            logD("XButton change theme reset backgroundPadding");
            this.mRectBgPadding = XBackgroundPaddingUtils.backgroundPadding(this, this.mRectBgPadding.left, this.mRectBgPadding.top, this.mRectBgPadding.right, this.mRectBgPadding.bottom);
        }
    }

    public void backgroundPadding(int i, int i2, int i3, int i4) {
        this.mRectBgPadding = XBackgroundPaddingUtils.backgroundPadding(this, i, i2, i3, i4);
    }

    public void setPressAnimEnable(boolean z) {
        this.pressAnimEnable = z;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        XViewDelegate xViewDelegate = this.mXViewDelegate;
        if (xViewDelegate != null) {
            xViewDelegate.onConfigurationChanged(configuration);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        XViewDelegate xViewDelegate = this.mXViewDelegate;
        if (xViewDelegate != null) {
            xViewDelegate.onAttachedToWindow();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        XViewDelegate xViewDelegate = this.mXViewDelegate;
        if (xViewDelegate != null) {
            xViewDelegate.onDetachedFromWindow();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        setVuiVisibility(this, i);
    }

    @Override // android.widget.TextView, android.view.View
    public void setSelected(boolean z) {
        super.setSelected(z);
        setVuiSelected(this, z);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        releaseVui();
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (FlavorUtils.isV5()) {
            if (z) {
                setAlpha(1.0f);
            } else {
                setAlpha(0.4f);
            }
        }
    }

    @Override // com.xiaopeng.xui.vui.VuiView
    public void initVui(View view, AttributeSet attributeSet) {
        super.initVui(view, attributeSet);
        setTextWatcherChanged(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        XViewDelegate xViewDelegate = this.mXViewDelegate;
        if (xViewDelegate == null || !this.pressAnimEnable) {
            return;
        }
        xViewDelegate.drawableStateChanged();
    }
}
