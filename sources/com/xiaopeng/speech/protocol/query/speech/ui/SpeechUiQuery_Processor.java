package com.xiaopeng.speech.protocol.query.speech.ui;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.speech.SpeechQueryEvent;
/* loaded from: classes2.dex */
public class SpeechUiQuery_Processor implements IQueryProcessor {
    private SpeechUiQuery mTarget;

    public SpeechUiQuery_Processor(SpeechUiQuery speechUiQuery) {
        this.mTarget = speechUiQuery;
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -1584266840) {
            if (hashCode == -1417712984 && str.equals(SpeechQueryEvent.IS_SUPERDIALOGUE_OPENED)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(SpeechQueryEvent.IS_SUPERDIALOGUE_WHITELIST)) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return null;
            }
            return Boolean.valueOf(this.mTarget.isSuperDialogueOpened(str, str2));
        }
        return Boolean.valueOf(this.mTarget.isSuperDialogueWhitelist(str, str2));
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{SpeechQueryEvent.IS_SUPERDIALOGUE_WHITELIST, SpeechQueryEvent.IS_SUPERDIALOGUE_OPENED};
    }
}
