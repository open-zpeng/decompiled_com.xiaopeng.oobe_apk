package com.xiaopeng.speech.protocol.node.personalCenter;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.protocol.event.PersonalCenterEvent;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PersonalCenterNode extends SpeechNode<PersonalCenterListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = PersonalCenterEvent.CONTROL_PROFILE_HABIT_SELECT)
    public void onControlProfileHabitSelect(String str, String str2) {
        int i;
        LogUtils.i("PersonalCenterNode", "data = " + str2 + " , event = " + str);
        try {
            i = new JSONObject(str2).optInt("index");
        } catch (Throwable unused) {
            LogUtils.e("CaracNode", "onControlProfileHabitSelect string data error, data = " + str2);
            i = 0;
        }
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PersonalCenterListener) obj).onControlProfileHabitSelect(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = PersonalCenterEvent.CONTROL_PROFILE_HABIT_SELECT_NEXT)
    public void onControlProfileHabitSelectNext(String str, String str2) {
        LogUtils.i("PersonalCenterNode", "data = " + str2 + " , event = " + str);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PersonalCenterListener) obj).onControlProfileHabitSelectNext();
            }
        }
    }
}
