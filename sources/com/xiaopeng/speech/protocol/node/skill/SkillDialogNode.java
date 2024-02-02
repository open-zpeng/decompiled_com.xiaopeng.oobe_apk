package com.xiaopeng.speech.protocol.node.skill;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.SkillDialogEvent;
/* loaded from: classes2.dex */
public class SkillDialogNode extends SpeechNode<SkillDialogListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SkillDialogEvent.ROUTE_CLOSE_WINDOW)
    public void onCloseWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SkillDialogListener) obj).onCloseWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SkillDialogEvent.ROUTE_OPEN_WINDOW)
    public void onOpenWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SkillDialogListener) obj).onOpenWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SkillDialogEvent.AI_FORWARD_SCREEN_EVENT)
    public void onForwardScreenEvent(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SkillDialogListener) obj).onForwardScreenEvent(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SkillDialogEvent.SKILL_REFRESH_DATA)
    public void onRefreshSkillData(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SkillDialogListener) obj).onRefreshSkillData(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SkillDialogEvent.SKILL_SHOW_DOUBLE_GUIDE)
    public void onShowDoubleGuide(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SkillDialogListener) obj).onShowDoubleGuide(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SkillDialogEvent.SKILL_SHOW_KEY_GUIDE)
    public void onShowKeyGuide(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SkillDialogListener) obj).onShowKeyGuide(str2);
            }
        }
    }
}
