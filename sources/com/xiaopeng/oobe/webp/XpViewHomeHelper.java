package com.xiaopeng.oobe.webp;
/* loaded from: classes.dex */
public class XpViewHomeHelper {
    private final XpView mXPImageView;

    public XpViewHomeHelper(XpView xpView) {
        this.mXPImageView = xpView;
    }

    public void showForHome() {
        this.mXPImageView.switchTo(XpViewStatus.HOME_DEFAULT);
    }
}
