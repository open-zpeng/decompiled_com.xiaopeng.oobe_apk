package com.xiaopeng.oobe;

import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.module.BaseView;
import com.xiaopeng.oobe.module.init.InitView;
import com.xiaopeng.oobe.module.login.LoginView;
import com.xiaopeng.oobe.module.statement.StatementView;
import com.xiaopeng.oobe.module.voiceservice.VoiceServiceView;
import com.xiaopeng.oobe.module.vuiauthorization.VuiAuthorizationView;
import com.xiaopeng.oobe.module.vuiauthorizationos5.VuiAuthorizationOS5View;
import java.security.InvalidParameterException;
/* loaded from: classes.dex */
public class PageViewFactory {
    private static final String TAG = "PageViewFactory";

    public static BaseView createView(Constants.ViewMode viewMode) {
        LogUtils.i(TAG, "createView: ");
        if (viewMode == Constants.ViewMode.STATEMENT) {
            return new StatementView();
        }
        if (viewMode == Constants.ViewMode.INIT) {
            return new InitView();
        }
        if (viewMode == Constants.ViewMode.LOGIN) {
            return new LoginView();
        }
        if (viewMode == Constants.ViewMode.VUI_AUTHORIZATION) {
            return new VuiAuthorizationView();
        }
        if (viewMode == Constants.ViewMode.VUI_AUTHORIZATION_OS5) {
            return new VuiAuthorizationOS5View();
        }
        if (viewMode == Constants.ViewMode.VOICE_SERVICE) {
            return new VoiceServiceView();
        }
        throw new InvalidParameterException("unknown type");
    }
}
