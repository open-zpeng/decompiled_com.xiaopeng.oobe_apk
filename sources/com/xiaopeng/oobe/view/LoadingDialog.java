package com.xiaopeng.oobe.view;

import android.content.Context;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.xui.app.XLoadingDialog;
/* loaded from: classes.dex */
public class LoadingDialog {
    private static final String TAG = "LoadingDialog";
    private XLoadingDialog mLoadingDialog;

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        static LoadingDialog sInstance = new LoadingDialog();

        private SingletonHolder() {
        }
    }

    public static LoadingDialog getInstance() {
        return SingletonHolder.sInstance;
    }

    private LoadingDialog() {
        init(App.getInstance().getActivity());
    }

    public void init(Context context) {
        this.mLoadingDialog = new XLoadingDialog(context, 2131821296);
        this.mLoadingDialog.create();
        this.mLoadingDialog.setCancelable(false);
        this.mLoadingDialog.setCanceledOnTouchOutside(false);
        this.mLoadingDialog.setTimeOutCheck(true);
        this.mLoadingDialog.setTimeOut(20000);
    }

    public void show() {
        LogUtils.i(TAG, "showLoadingDialog");
        XLoadingDialog xLoadingDialog = this.mLoadingDialog;
        if (xLoadingDialog != null && xLoadingDialog.isShowing()) {
            LogUtils.i(TAG, "showLoadingDialog isShowing");
            this.mLoadingDialog.dismiss();
        }
        this.mLoadingDialog.show();
    }

    public void dismiss() {
        LogUtils.i(TAG, "hideLoadingDialog");
        this.mLoadingDialog.dismiss();
    }
}
