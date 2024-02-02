package com.xiaopeng.speech.protocol.node.personalCenter;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.PersonalCenterEvent;
/* loaded from: classes2.dex */
public class PersonalCenterNode_Processor implements ICommandProcessor {
    private PersonalCenterNode mTarget;

    public PersonalCenterNode_Processor(PersonalCenterNode personalCenterNode) {
        this.mTarget = personalCenterNode;
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != 911050310) {
            if (hashCode == 1416411835 && str.equals(PersonalCenterEvent.CONTROL_PROFILE_HABIT_SELECT)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(PersonalCenterEvent.CONTROL_PROFILE_HABIT_SELECT_NEXT)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.mTarget.onControlProfileHabitSelect(str, str2);
        } else if (c != 1) {
        } else {
            this.mTarget.onControlProfileHabitSelectNext(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{PersonalCenterEvent.CONTROL_PROFILE_HABIT_SELECT, PersonalCenterEvent.CONTROL_PROFILE_HABIT_SELECT_NEXT};
    }
}
