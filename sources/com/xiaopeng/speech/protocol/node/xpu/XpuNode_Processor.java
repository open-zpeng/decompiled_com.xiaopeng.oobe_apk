package com.xiaopeng.speech.protocol.node.xpu;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.XpuEvent;
/* loaded from: classes2.dex */
public class XpuNode_Processor implements ICommandProcessor {
    private XpuNode mTarget;

    public XpuNode_Processor(XpuNode xpuNode) {
        this.mTarget = xpuNode;
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        if (((str.hashCode() == -47958497 && str.equals(XpuEvent.XPU_VOICE_CHANGE_LANE)) ? (char) 0 : (char) 65535) != 0) {
            return;
        }
        this.mTarget.laneChange(str, str2);
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{XpuEvent.XPU_VOICE_CHANGE_LANE};
    }
}
