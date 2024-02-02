package com.xiaopeng.speech.protocol.query.aiassistant;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.speech.QueryAiAssistantEvent;
/* loaded from: classes2.dex */
public class AiAssistantQuery_Processor implements IQueryProcessor {
    private AiAssistantQuery mTarget;

    public AiAssistantQuery_Processor(AiAssistantQuery aiAssistantQuery) {
        this.mTarget = aiAssistantQuery;
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -1132668627) {
            if (hashCode == -497888784 && str.equals(QueryAiAssistantEvent.GUI_AI_VIDEO_OPEN)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(QueryAiAssistantEvent.GET_SKIN_TYPE)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return null;
            }
            return Integer.valueOf(this.mTarget.getAiSkinType(str, str2));
        }
        return Integer.valueOf(this.mTarget.getAiVideoOpenStatus(str, str2));
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{QueryAiAssistantEvent.GUI_AI_VIDEO_OPEN, QueryAiAssistantEvent.GET_SKIN_TYPE};
    }
}
