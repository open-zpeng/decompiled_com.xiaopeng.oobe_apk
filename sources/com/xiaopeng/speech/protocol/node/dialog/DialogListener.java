package com.xiaopeng.speech.protocol.node.dialog;

import com.xiaopeng.speech.INodeListener;
import com.xiaopeng.speech.jarvisproto.DMWait;
import com.xiaopeng.speech.jarvisproto.DialogSoundAreaStatus;
import com.xiaopeng.speech.jarvisproto.SoundAreaStatus;
import com.xiaopeng.speech.jarvisproto.SwitchStatus;
import com.xiaopeng.speech.protocol.node.dialog.bean.DialogActiveReason;
import com.xiaopeng.speech.protocol.node.dialog.bean.DialogEndReason;
import com.xiaopeng.speech.protocol.node.dialog.bean.DialogExitReason;
import com.xiaopeng.speech.protocol.node.dialog.bean.PrepareReason;
import com.xiaopeng.speech.protocol.node.dialog.bean.WakeupReason;
/* loaded from: classes2.dex */
public interface DialogListener extends INodeListener {
    default void onDialogActive(DialogActiveReason dialogActiveReason) {
    }

    void onDialogContinue();

    void onDialogEnd(DialogEndReason dialogEndReason);

    void onDialogError();

    default void onDialogExit(DialogExitReason dialogExitReason) {
    }

    default void onDialogPrepare(PrepareReason prepareReason) {
    }

    default void onDialogSoundAreaStatusChanged(DialogSoundAreaStatus dialogSoundAreaStatus) {
    }

    void onDialogStart(WakeupReason wakeupReason);

    default void onDialogStatusChanged(String str) {
    }

    default void onDialogSwitchStatusChanged(SwitchStatus switchStatus) {
    }

    void onDialogWait(DMWait dMWait);

    default void onSoundAreaStatusChanged(SoundAreaStatus soundAreaStatus) {
    }

    void onVadBegin();

    void onVadEnd();

    void onWakeupResult();
}
