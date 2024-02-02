package com.xiaopeng.speech.protocol.node.floater;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.FloaterSpeechEvent;
/* loaded from: classes2.dex */
public class FloaterSpeechNode_Processor implements ICommandProcessor {
    private FloaterSpeechNode mTarget;

    public FloaterSpeechNode_Processor(FloaterSpeechNode floaterSpeechNode) {
        this.mTarget = floaterSpeechNode;
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1829252700) {
            if (str.equals(FloaterSpeechEvent.XIAOP_EXPRESSION)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 1493310260) {
            if (hashCode == 1920721242 && str.equals(FloaterSpeechEvent.CLOSE_WINDOW)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(FloaterSpeechEvent.SET_ANIM_STATE)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.mTarget.onCloseWindow(str, str2);
        } else if (c == 1) {
            this.mTarget.onSetAnimState(str, str2);
        } else if (c != 2) {
        } else {
            this.mTarget.onXiaopExpression(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{FloaterSpeechEvent.CLOSE_WINDOW, FloaterSpeechEvent.SET_ANIM_STATE, FloaterSpeechEvent.XIAOP_EXPRESSION};
    }
}
