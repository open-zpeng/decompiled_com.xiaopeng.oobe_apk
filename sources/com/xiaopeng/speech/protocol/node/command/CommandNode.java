package com.xiaopeng.speech.protocol.node.command;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.CommandEvent;
import com.xiaopeng.speech.protocol.node.command.bean.CommandBean;
/* loaded from: classes2.dex */
public class CommandNode extends SpeechNode<CommandListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CommandEvent.COMMAND_JOSN_POST)
    public void onJsonPost(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        CommandBean fromJson = CommandBean.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CommandListener) obj).postCommand(fromJson);
            }
        }
    }
}
