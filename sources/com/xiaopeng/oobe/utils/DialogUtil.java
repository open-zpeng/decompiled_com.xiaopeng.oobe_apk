package com.xiaopeng.oobe.utils;

import android.content.DialogInterface;
import android.view.View;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.view.LoadingDialog;
import com.xiaopeng.xui.app.XDialog;
import com.xiaopeng.xui.app.XDialogInterface;
/* loaded from: classes.dex */
public class DialogUtil {
    private static final String TAG = "DialogUtil";

    public static void showDialog(XDialog xDialog, final View view, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, XDialogInterface.OnClickListener onClickListener, XDialogInterface.OnClickListener onClickListener2) {
        if (view != null) {
            view.animate().alpha(0.0f).setDuration(100L).start();
            xDialog.setCanceledOnTouchOutside(false);
            xDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.xiaopeng.oobe.utils.-$$Lambda$DialogUtil$DIw3_50WIvV6-RkCjykqIx0KxzU
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    LoadingDialog.getInstance().dismiss();
                }
            });
            xDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.xiaopeng.oobe.utils.-$$Lambda$DialogUtil$ujisMozww8yLaGln5o5CEbofzJs
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    view.animate().alpha(1.0f).setDuration(100L).start();
                }
            });
            xDialog.setNegativeButton(charSequence4, onClickListener2);
            xDialog.setPositiveButton(charSequence3, onClickListener);
            xDialog.setTitle(charSequence);
            xDialog.setMessage(charSequence2);
            xDialog.show();
        }
    }

    public static void showCustomDialog(XDialog xDialog, final View view, View view2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, XDialogInterface.OnClickListener onClickListener, XDialogInterface.OnClickListener onClickListener2) {
        if (view != null) {
            view.animate().alpha(0.0f).setDuration(100L).start();
            xDialog.setCanceledOnTouchOutside(false);
            xDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.xiaopeng.oobe.utils.-$$Lambda$DialogUtil$pmc5D4DsdHwOkl462fEkdFSWH_o
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    LoadingDialog.getInstance().dismiss();
                }
            });
            xDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.xiaopeng.oobe.utils.-$$Lambda$DialogUtil$ULxbe-z01W_Alb1AkKAawF-W5F0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    view.animate().alpha(1.0f).setDuration(100L).start();
                }
            });
            xDialog.setNegativeButton(charSequence3, onClickListener2);
            xDialog.setPositiveButton(charSequence2, onClickListener);
            xDialog.setCustomView(view2);
            xDialog.setTitle(charSequence);
            xDialog.show();
        }
    }

    public static void showCustomDialog(XDialog xDialog, final View view, View view2, CharSequence charSequence) {
        if (view != null) {
            view.animate().alpha(0.0f).setDuration(100L).start();
            xDialog.setCanceledOnTouchOutside(false);
            xDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.xiaopeng.oobe.utils.-$$Lambda$DialogUtil$eRms-67hzLEGr875D-tlH6CHHUE
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    LoadingDialog.getInstance().dismiss();
                }
            });
            xDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.xiaopeng.oobe.utils.-$$Lambda$DialogUtil$bS0mwHatn2Sjiud-_NOE4EpwtCQ
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    view.animate().alpha(1.0f).setDuration(100L).start();
                }
            });
            xDialog.setNegativeButtonEnable(false);
            xDialog.setPositiveButtonEnable(false);
            xDialog.setCustomView(view2);
            xDialog.setTitle(charSequence);
            xDialog.show();
        }
    }

    public static void dismissDialog(XDialog xDialog) {
        if (xDialog != null) {
            xDialog.dismiss();
            String str = TAG;
            LogUtils.d(str, xDialog + Constants.Unity.DISMISS);
        }
    }
}
