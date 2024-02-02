package com.xiaopeng.speech.protocol.node.bugreport;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.BugReportEvent;
/* loaded from: classes2.dex */
public class BugReportNode extends SpeechNode<BugReportListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = BugReportEvent.BUG_REPORT_BEGIN)
    public void onBugReportBegin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((BugReportListener) obj).onBugReportBegin();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = BugReportEvent.BUG_REPORT_END)
    public void onBugReportEnd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        BugReportEndValue fromJson = BugReportEndValue.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((BugReportListener) obj).onBugReportEnd(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = BugReportEvent.BUG_REPORT_SUBMIT)
    public void onBugReportSubmit(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((BugReportListener) obj).onBugReportSubmit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = BugReportEvent.BUG_REPORT_CANCEL)
    public void onBugReportCancel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((BugReportListener) obj).onBugReportCancel();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = BugReportEvent.BUG_REPORT_VOLUME)
    public void onBugReportVolume(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((BugReportListener) obj).onBugReportVolume(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = BugReportEvent.BUG_REPORT_ENDTTS)
    public void onBugReportEndtts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((BugReportListener) obj).onBugReportEndtts();
            }
        }
    }
}
