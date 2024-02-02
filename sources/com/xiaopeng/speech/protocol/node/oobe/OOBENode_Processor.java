package com.xiaopeng.speech.protocol.node.oobe;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.ErrorEvent;
import com.xiaopeng.speech.protocol.event.OOBEEvent;
/* loaded from: classes2.dex */
public class OOBENode_Processor implements ICommandProcessor {
    private OOBENode mTarget;

    public OOBENode_Processor(OOBENode oOBENode) {
        this.mTarget = oOBENode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1653395749:
                if (str.equals(OOBEEvent.OOBE_SKIP)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1548651618:
                if (str.equals(OOBEEvent.OOBE_SEARCH_ERROR)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1397629300:
                if (str.equals(OOBEEvent.OOBE_ADDRESS_SET)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 176523582:
                if (str.equals(OOBEEvent.OOBE_ASR_ERROR)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 177511753:
                if (str.equals(OOBEEvent.OOBE_RECORD_INPUT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 314263926:
                if (str.equals(OOBEEvent.OOBE_VOLUME)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 780227936:
                if (str.equals(ErrorEvent.ERROR_ASR_RESULT)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 983678060:
                if (str.equals(OOBEEvent.OOBE_NETWORK_ERROR)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1457336958:
                if (str.equals(OOBEEvent.OOBE_RECORD_RESULT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1505323214:
                if (str.equals(OOBEEvent.OOBE_OTHER_ERROR)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.mTarget.onRecordResult(str, str2);
                return;
            case 1:
                this.mTarget.onRecordInput(str, str2);
                return;
            case 2:
                this.mTarget.onAddressSet(str, str2);
                return;
            case 3:
                this.mTarget.onSkip(str, str2);
                return;
            case 4:
                this.mTarget.onSearchError(str, str2);
                return;
            case 5:
                this.mTarget.onNetError(str, str2);
                return;
            case 6:
                this.mTarget.onASRError(str, str2);
                return;
            case 7:
                this.mTarget.onError(str, str2);
                return;
            case '\b':
                this.mTarget.onVolumeChange(str, str2);
                return;
            case '\t':
                this.mTarget.onNetWorkError(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{OOBEEvent.OOBE_RECORD_RESULT, OOBEEvent.OOBE_RECORD_INPUT, OOBEEvent.OOBE_ADDRESS_SET, OOBEEvent.OOBE_SKIP, OOBEEvent.OOBE_SEARCH_ERROR, ErrorEvent.ERROR_ASR_RESULT, OOBEEvent.OOBE_ASR_ERROR, OOBEEvent.OOBE_OTHER_ERROR, OOBEEvent.OOBE_VOLUME, OOBEEvent.OOBE_NETWORK_ERROR};
    }
}
