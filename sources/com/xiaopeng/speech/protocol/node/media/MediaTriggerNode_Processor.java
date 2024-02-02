package com.xiaopeng.speech.protocol.node.media;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.MediaEvent;
/* loaded from: classes2.dex */
public class MediaTriggerNode_Processor implements ICommandProcessor {
    private MediaTriggerNode mTarget;

    public MediaTriggerNode_Processor(MediaTriggerNode mediaTriggerNode) {
        this.mTarget = mediaTriggerNode;
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != 531930234) {
            if (hashCode == 1008119320 && str.equals(MediaEvent.TRIGGER_MESSAGE_SUBMIT)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(MediaEvent.TRIGGER_MESSAGE_CANCEL)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.mTarget.onSubmit(str, str2);
        } else if (c != 1) {
        } else {
            this.mTarget.onCancel(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{MediaEvent.TRIGGER_MESSAGE_SUBMIT, MediaEvent.TRIGGER_MESSAGE_CANCEL};
    }
}
