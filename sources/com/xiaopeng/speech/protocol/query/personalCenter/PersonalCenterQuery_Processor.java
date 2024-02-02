package com.xiaopeng.speech.protocol.query.personalCenter;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.QueryPersonalCenterEvent;
/* loaded from: classes2.dex */
public class PersonalCenterQuery_Processor implements IQueryProcessor {
    private PersonalCenterQuery mTarget;

    public PersonalCenterQuery_Processor(PersonalCenterQuery personalCenterQuery) {
        this.mTarget = personalCenterQuery;
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1082449569) {
            if (str.equals(QueryPersonalCenterEvent.GET_PAGE_OPEN_STATUS_PERSONAL_CENTER)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 982269335) {
            if (hashCode == 1404633087 && str.equals(QueryPersonalCenterEvent.CONTROL_PROFILE_HABIT_SUPPORT)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(QueryPersonalCenterEvent.CONTROL_PROFILE_HABIT_NUM_SUPPORT)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    return null;
                }
                return Integer.valueOf(this.mTarget.getGuiPageOpenState(str, str2));
            }
            return Integer.valueOf(this.mTarget.getControlProfileHabitNumSupport(str, str2));
        }
        return Integer.valueOf(this.mTarget.getControlProfileHabitSupport(str, str2));
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{QueryPersonalCenterEvent.CONTROL_PROFILE_HABIT_SUPPORT, QueryPersonalCenterEvent.CONTROL_PROFILE_HABIT_NUM_SUPPORT, QueryPersonalCenterEvent.GET_PAGE_OPEN_STATUS_PERSONAL_CENTER};
    }
}
