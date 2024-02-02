package com.xiaopeng.speech.protocol.node.ota;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.OtaEvent;
/* loaded from: classes2.dex */
public class OtaNode_Processor implements ICommandProcessor {
    private OtaNode mTarget;

    public OtaNode_Processor(OtaNode otaNode) {
        this.mTarget = otaNode;
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != 478800373) {
            if (hashCode == 1009711693 && str.equals(OtaEvent.OTARESERVATION_PAGE_OPEN)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(OtaEvent.OTA_PAGE_OPEN)) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            this.mTarget.onOpenOtaPage(str, str2);
        } else if (c != 1) {
        } else {
            this.mTarget.onOpenReservationPage(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{OtaEvent.OTA_PAGE_OPEN, OtaEvent.OTARESERVATION_PAGE_OPEN};
    }
}
