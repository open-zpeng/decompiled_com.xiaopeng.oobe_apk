package com.xiaopeng.speech.protocol.node.userbook;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.CarcontrolEvent;
/* loaded from: classes2.dex */
public class UserBookNode_Processor implements ICommandProcessor {
    private UserBookNode mTarget;

    public UserBookNode_Processor(UserBookNode userBookNode) {
        this.mTarget = userBookNode;
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1001296170) {
            if (str.equals(CarcontrolEvent.CLOSE_USER_BOOK)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 1414376552) {
            if (hashCode == 1829348592 && str.equals(CarcontrolEvent.CHECK_USER_BOOK)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(CarcontrolEvent.OPEN_USER_BOOK)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.mTarget.onCheckUserBook(str, str2);
        } else if (c == 1) {
            this.mTarget.onOpenUserBook(str, str2);
        } else if (c != 2) {
        } else {
            this.mTarget.onCloseUserBook(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{CarcontrolEvent.CHECK_USER_BOOK, CarcontrolEvent.OPEN_USER_BOOK, CarcontrolEvent.CLOSE_USER_BOOK};
    }
}
