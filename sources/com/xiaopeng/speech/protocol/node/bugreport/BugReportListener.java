package com.xiaopeng.speech.protocol.node.bugreport;

import com.xiaopeng.speech.INodeListener;
/* loaded from: classes2.dex */
public interface BugReportListener extends INodeListener {
    void onBugReportBegin();

    default void onBugReportCancel() {
    }

    void onBugReportEnd(BugReportEndValue bugReportEndValue);

    void onBugReportEndtts();

    default void onBugReportSubmit() {
    }

    void onBugReportVolume(String str);
}
