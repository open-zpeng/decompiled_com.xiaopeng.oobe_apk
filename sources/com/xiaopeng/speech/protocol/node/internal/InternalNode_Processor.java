package com.xiaopeng.speech.protocol.node.internal;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.InternalEvent;
/* loaded from: classes2.dex */
public class InternalNode_Processor implements ICommandProcessor {
    private InternalNode mTarget;

    public InternalNode_Processor(InternalNode internalNode) {
        this.mTarget = internalNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1771481023:
                if (str.equals(InternalEvent.REBOOT_COMPLETE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 735086539:
                if (str.equals(InternalEvent.INPUT_DM_DATA)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1211476952:
                if (str.equals(InternalEvent.LOCAL_WAKEUP_RESULT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1445372987:
                if (str.equals(InternalEvent.RESOURCE_UPDATE_FINISH)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1795896838:
                if (str.equals(InternalEvent.DM_OUTPUT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.mTarget.onDmOutput(str, str2);
        } else if (c == 1) {
            this.mTarget.onInputDmData(str, str2);
        } else if (c == 2) {
            this.mTarget.onLocalWakeupResult(str, str2);
        } else if (c == 3) {
            this.mTarget.onResourceUpdateFinish(str, str2);
        } else if (c != 4) {
        } else {
            this.mTarget.onRebootComplete(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{InternalEvent.DM_OUTPUT, InternalEvent.INPUT_DM_DATA, InternalEvent.LOCAL_WAKEUP_RESULT, InternalEvent.RESOURCE_UPDATE_FINISH, InternalEvent.REBOOT_COMPLETE};
    }
}
