package com.xiaopeng.speech.protocol.query.music;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.QuerySingEvent;
/* loaded from: classes2.dex */
public class SingQuery_Processor implements IQueryProcessor {
    private SingQuery mTarget;

    public SingQuery_Processor(SingQuery singQuery) {
        this.mTarget = singQuery;
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        if (((str.hashCode() == -314142516 && str.equals(QuerySingEvent.GET_SING_STATUS_QUERY)) ? (char) 0 : (char) 65535) != 0) {
            return null;
        }
        return Integer.valueOf(this.mTarget.getSingStatus(str, str2));
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{QuerySingEvent.GET_SING_STATUS_QUERY};
    }
}
