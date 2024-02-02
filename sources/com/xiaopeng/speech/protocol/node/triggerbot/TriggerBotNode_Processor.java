package com.xiaopeng.speech.protocol.node.triggerbot;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.TriggerBotEvent;
/* loaded from: classes2.dex */
public class TriggerBotNode_Processor implements ICommandProcessor {
    private TriggerBotNode mTarget;

    public TriggerBotNode_Processor(TriggerBotNode triggerBotNode) {
        this.mTarget = triggerBotNode;
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        if (((str.hashCode() == 374985417 && str.equals(TriggerBotEvent.TRIGGER_BOT_RESULT)) ? (char) 0 : (char) 65535) != 0) {
            return;
        }
        this.mTarget.onTriggerResult(str, str2);
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{TriggerBotEvent.TRIGGER_BOT_RESULT};
    }
}
