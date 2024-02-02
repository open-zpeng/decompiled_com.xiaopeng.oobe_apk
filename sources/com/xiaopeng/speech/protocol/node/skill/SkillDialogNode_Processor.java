package com.xiaopeng.speech.protocol.node.skill;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.SkillDialogEvent;
/* loaded from: classes2.dex */
public class SkillDialogNode_Processor implements ICommandProcessor {
    private SkillDialogNode mTarget;

    public SkillDialogNode_Processor(SkillDialogNode skillDialogNode) {
        this.mTarget = skillDialogNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1594338748:
                if (str.equals(SkillDialogEvent.SKILL_SHOW_KEY_GUIDE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1248956104:
                if (str.equals(SkillDialogEvent.ROUTE_OPEN_WINDOW)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -764162793:
                if (str.equals(SkillDialogEvent.ROUTE_CLOSE_WINDOW)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -91367129:
                if (str.equals(SkillDialogEvent.AI_FORWARD_SCREEN_EVENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2043108326:
                if (str.equals(SkillDialogEvent.SKILL_REFRESH_DATA)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2110322600:
                if (str.equals(SkillDialogEvent.SKILL_SHOW_DOUBLE_GUIDE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.mTarget.onCloseWindow(str, str2);
        } else if (c == 1) {
            this.mTarget.onOpenWindow(str, str2);
        } else if (c == 2) {
            this.mTarget.onForwardScreenEvent(str, str2);
        } else if (c == 3) {
            this.mTarget.onRefreshSkillData(str, str2);
        } else if (c == 4) {
            this.mTarget.onShowDoubleGuide(str, str2);
        } else if (c != 5) {
        } else {
            this.mTarget.onShowKeyGuide(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{SkillDialogEvent.ROUTE_CLOSE_WINDOW, SkillDialogEvent.ROUTE_OPEN_WINDOW, SkillDialogEvent.AI_FORWARD_SCREEN_EVENT, SkillDialogEvent.SKILL_REFRESH_DATA, SkillDialogEvent.SKILL_SHOW_DOUBLE_GUIDE, SkillDialogEvent.SKILL_SHOW_KEY_GUIDE};
    }
}
