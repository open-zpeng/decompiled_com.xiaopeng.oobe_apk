package com.xiaopeng.speech.protocol.node.meter;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.MeterEvent;
/* loaded from: classes2.dex */
public class MeterNode_Processor implements ICommandProcessor {
    private MeterNode mTarget;

    public MeterNode_Processor(MeterNode meterNode) {
        this.mTarget = meterNode;
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1943195853) {
            if (str.equals(MeterEvent.SET_RIGHTT_CARD)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != -1464668575) {
            if (hashCode == 432832230 && str.equals(MeterEvent.SET_LEFT_CARD)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(MeterEvent.DASHBOARD_LIGHTS_STATUS)) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            this.mTarget.setLeftCard(str, str2);
        } else if (c == 1) {
            this.mTarget.setRightCard(str, str2);
        } else if (c != 2) {
        } else {
            this.mTarget.onDashboardLightsStatus(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{MeterEvent.SET_LEFT_CARD, MeterEvent.SET_RIGHTT_CARD, MeterEvent.DASHBOARD_LIGHTS_STATUS};
    }
}
