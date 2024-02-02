package com.xiaopeng.oobe.module.locale;

import android.util.Log;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.SPUtils;
import com.xiaopeng.oobe.viewmodel.IBaseViewControl;
import com.xiaopeng.oobe.viewmodel.IView;
import java.util.Locale;
/* loaded from: classes.dex */
public class LocaleControl extends IBaseViewControl {
    private static final String TAG = "oobe-LocaleControl";

    public LocaleControl(IView iView) {
        super(iView);
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        super.onStart();
        LogUtils.i(TAG, "onStart mRootView:" + this.mRootView);
    }

    public void setReg(String str) {
        String lan = getLan();
        OOBEHelper.changeSystemLocale(new Locale(lan, str));
        SPUtils.setRegSelected(str);
        Log.i(TAG, "set lanCode: " + lan + " regCode:" + str);
    }

    public void setLan(String str) {
        Log.i(TAG, "set lanCode: " + str);
        OOBEHelper.changeSystemLocale(new Locale(str));
        SPUtils.setLanSelected(str);
    }

    public String getLan() {
        return SPUtils.getLanSelected();
    }

    public String getReg() {
        return SPUtils.getRegSelected();
    }
}
