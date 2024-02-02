package com.xiaopeng.speech.protocol.query.personalCenter;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.protocol.event.query.QueryPersonalCenterEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PersonalCenterQuery extends SpeechQuery<IPersonalCenterCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryPersonalCenterEvent.CONTROL_PROFILE_HABIT_SUPPORT)
    public int getControlProfileHabitSupport(String str, String str2) {
        return ((IPersonalCenterCaller) this.mQueryCaller).getControlProfileHabitSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryPersonalCenterEvent.CONTROL_PROFILE_HABIT_NUM_SUPPORT)
    public int getControlProfileHabitNumSupport(String str, String str2) {
        int i;
        LogUtils.i("PersonalCenterQuery", "getControlProfileHabitNumSupport data = " + str2 + " , event = " + str);
        try {
            i = new JSONObject(str2).optInt("index");
        } catch (JSONException e) {
            e.printStackTrace();
            i = 0;
        }
        return ((IPersonalCenterCaller) this.mQueryCaller).getControlProfileHabitNumSupport(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryPersonalCenterEvent.GET_PAGE_OPEN_STATUS_PERSONAL_CENTER)
    public int getGuiPageOpenState(String str, String str2) {
        LogUtils.i("PersonalCenterQuery", "getGuiPageOpenState data = " + str2 + " , event = " + str);
        return ((IPersonalCenterCaller) this.mQueryCaller).getGuiPageOpenState(str2);
    }
}
