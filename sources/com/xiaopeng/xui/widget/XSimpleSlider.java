package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import com.alibaba.mtl.appmonitor.AppMonitorDelegate;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.vui.commons.IVuiElementBuilder;
import com.xiaopeng.vui.commons.IVuiElementListener;
import com.xiaopeng.vui.commons.VuiElementType;
import com.xiaopeng.vui.commons.model.VuiElement;
import com.xiaopeng.vui.commons.model.VuiEvent;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.Xui;
import com.xiaopeng.xui.utils.FlavorUtils;
import com.xiaopeng.xui.view.XViewDelegate;
import com.xiaopeng.xui.vui.VuiView;
import com.xiaopeng.xui.vui.floatinglayer.VuiFloatingLayerManager;
import org.json.JSONObject;
import xiaopeng.widget.SimpleSlider;
/* loaded from: classes2.dex */
public class XSimpleSlider extends SimpleSlider implements VuiView, IVuiElementListener {
    private XViewDelegate mXViewDelegate;

    public XSimpleSlider(Context context) {
        this(context, null);
    }

    public XSimpleSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.style.XSimpleSlider);
    }

    public XSimpleSlider(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XSimpleSlider);
    }

    public XSimpleSlider(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mXViewDelegate = XViewDelegate.create(this, attributeSet, i, i2);
        this.mXViewDelegate.getThemeViewModel().setCallback(new ThemeViewModel.OnCallback() { // from class: com.xiaopeng.xui.widget.-$$Lambda$1nSZItY1LxLjhgUMuj4enwCbqSg
            @Override // com.xiaopeng.libtheme.ThemeViewModel.OnCallback
            public final void onThemeChanged() {
                XSimpleSlider.this.refreshVisual();
            }
        });
        initVui(this, attributeSet);
        if (Xui.isVuiEnable()) {
            addOnSliderChangeListener(new SimpleSlider.OnSlideChangeListener() { // from class: com.xiaopeng.xui.widget.XSimpleSlider.1
                @Override // xiaopeng.widget.SimpleSlider.OnSlideChangeListener
                public void onStartTrackingTouch(SimpleSlider simpleSlider) {
                }

                @Override // xiaopeng.widget.SimpleSlider.OnSlideChangeListener
                public void onStopTrackingTouch(SimpleSlider simpleSlider) {
                }

                @Override // xiaopeng.widget.SimpleSlider.OnSlideChangeListener
                public void onProgressChanged(SimpleSlider simpleSlider, int i3, boolean z) {
                    XSimpleSlider xSimpleSlider = XSimpleSlider.this;
                    xSimpleSlider.updateVui(xSimpleSlider);
                }
            });
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        XViewDelegate xViewDelegate = this.mXViewDelegate;
        if (xViewDelegate != null) {
            xViewDelegate.onConfigurationChanged(configuration);
        }
    }

    @Override // android.view.View
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

    @Override // com.xiaopeng.vui.commons.IVuiElementListener
    public VuiElement onBuildVuiElement(String str, IVuiElementBuilder iVuiElementBuilder) {
        if (getVuiElementType() == VuiElementType.STATEFULBUTTON) {
            return null;
        }
        setVuiValue(Integer.valueOf(getProgress()));
        if (getVuiProps() != null && getVuiProps().has("customSetProps") && getVuiProps().getBoolean("customSetProps")) {
            return null;
        }
        JSONObject vuiProps = getVuiProps();
        if (vuiProps == null) {
            vuiProps = new JSONObject();
        }
        vuiProps.put(AppMonitorDelegate.MIN_VALUE, getMin());
        vuiProps.put(AppMonitorDelegate.MAX_VALUE, getMax());
        vuiProps.put("interval", 1);
        setVuiProps(vuiProps);
        return null;
    }

    @Override // com.xiaopeng.vui.commons.IVuiElementListener
    public boolean onVuiElementEvent(View view, VuiEvent vuiEvent) {
        Double d;
        logD("simple slider onVuiElementEvent");
        if (view == null || getVuiElementType() == VuiElementType.STATEFULBUTTON || (d = (Double) vuiEvent.getEventValue(vuiEvent)) == null) {
            return false;
        }
        int ceil = (int) Math.ceil(d.doubleValue());
        if (ceil >= getMin() && ceil <= getMax()) {
            setProgressInternal(ceil, true, false);
            post(new Runnable() { // from class: com.xiaopeng.xui.widget.-$$Lambda$LCTTyar-woLSuf2-ofJ8NUIW8XI
                @Override // java.lang.Runnable
                public final void run() {
                    XSimpleSlider.this.showVuiFloating();
                }
            });
        }
        return true;
    }

    public void showVuiFloating() {
        VuiFloatingLayerManager.show(this, (int) (((-getWidth()) / 2.0f) + getPaddingStart() + (((getWidth() - getPaddingStart()) - getPaddingEnd()) * getCurrentVisualScale())), 0);
    }
}
