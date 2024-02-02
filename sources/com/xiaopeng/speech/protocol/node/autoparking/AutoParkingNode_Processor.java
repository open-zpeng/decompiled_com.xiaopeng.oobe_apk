package com.xiaopeng.speech.protocol.node.autoparking;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.AutoParkingEvent;
/* loaded from: classes2.dex */
public class AutoParkingNode_Processor implements ICommandProcessor {
    private AutoParkingNode mTarget;

    public AutoParkingNode_Processor(AutoParkingNode autoParkingNode) {
        this.mTarget = autoParkingNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case 60390650:
                if (str.equals(AutoParkingEvent.PARK_CARPORT_COUNT)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 658676530:
                if (str.equals(AutoParkingEvent.EXIT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 744299561:
                if (str.equals(AutoParkingEvent.MEMORY_PARKING_START)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 897056882:
                if (str.equals(AutoParkingEvent.PARK_START)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1563921671:
                if (str.equals(AutoParkingEvent.ACTIVATE)) {
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
            this.mTarget.onActivate(str, str2);
        } else if (c == 1) {
            this.mTarget.onExit(str, str2);
        } else if (c == 2) {
            this.mTarget.onParkStart(str, str2);
        } else if (c == 3) {
            this.mTarget.onParkCarportCount(str, str2);
        } else if (c != 4) {
        } else {
            this.mTarget.onMemoryParkingStart(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{AutoParkingEvent.ACTIVATE, AutoParkingEvent.EXIT, AutoParkingEvent.PARK_START, AutoParkingEvent.PARK_CARPORT_COUNT, AutoParkingEvent.MEMORY_PARKING_START};
    }
}
