package com.xiaopeng.speech.protocol.node.phone;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.PhoneEvent;
/* loaded from: classes2.dex */
public class PhoneNode_Processor implements ICommandProcessor {
    private PhoneNode mTarget;

    public PhoneNode_Processor(PhoneNode phoneNode) {
        this.mTarget = phoneNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1366657894:
                if (str.equals(PhoneEvent.QUERY_DETAIL_PHONEINFO)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1249983198:
                if (str.equals(PhoneEvent.IN_ACCEPT)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1094036411:
                if (str.equals(PhoneEvent.QUERY_BLUETOOTH)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1090067804:
                if (str.equals(PhoneEvent.QUERY_SYNC_BLUETOOTH)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -991558775:
                if (str.equals(PhoneEvent.CALLBACK_SUPPORT)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -761232455:
                if (str.equals(PhoneEvent.IN_REJECT)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -666255396:
                if (str.equals(PhoneEvent.QUERY_CONTACTS)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -500460440:
                if (str.equals(PhoneEvent.OUT_CUSTOMERSERVICE)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -307844569:
                if (str.equals(PhoneEvent.REDIAL_SUPPORT)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 478397247:
                if (str.equals(PhoneEvent.OUT)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1301635730:
                if (str.equals(PhoneEvent.REDIAL)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1330499435:
                if (str.equals(PhoneEvent.SELECT)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1640880884:
                if (str.equals(PhoneEvent.CALLBACK)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1710160883:
                if (str.equals(PhoneEvent.SYNC_CONTACT_RESULT)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1821930640:
                if (str.equals(PhoneEvent.OUT_HELP)) {
                    c = '\f';
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
                this.mTarget.onQuerySyncBluetooth(str, str2);
                return;
            case 1:
                this.mTarget.onQueryContacts(str, str2);
                return;
            case 2:
                this.mTarget.onQueryDetailPhoneInfo(str, str2);
                return;
            case 3:
                this.mTarget.onOut(str, str2);
                return;
            case 4:
                this.mTarget.onPhoneSelectOut(str, str2);
                return;
            case 5:
                this.mTarget.onInAccept(str, str2);
                return;
            case 6:
                this.mTarget.onInReject(str, str2);
                return;
            case 7:
                this.mTarget.onRedialSupport(str, str2);
                return;
            case '\b':
                this.mTarget.onRedial(str, str2);
                return;
            case '\t':
                this.mTarget.onCallbackSupport(str, str2);
                return;
            case '\n':
                this.mTarget.onCallback(str, str2);
                return;
            case 11:
                this.mTarget.onOutCustomerservice(str, str2);
                return;
            case '\f':
                this.mTarget.onOutHelp(str, str2);
                return;
            case '\r':
                this.mTarget.onQueryBluetooth(str, str2);
                return;
            case 14:
                this.mTarget.onSyncContactResult(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{PhoneEvent.QUERY_SYNC_BLUETOOTH, PhoneEvent.QUERY_CONTACTS, PhoneEvent.QUERY_DETAIL_PHONEINFO, PhoneEvent.OUT, PhoneEvent.SELECT, PhoneEvent.IN_ACCEPT, PhoneEvent.IN_REJECT, PhoneEvent.REDIAL_SUPPORT, PhoneEvent.REDIAL, PhoneEvent.CALLBACK_SUPPORT, PhoneEvent.CALLBACK, PhoneEvent.OUT_CUSTOMERSERVICE, PhoneEvent.OUT_HELP, PhoneEvent.QUERY_BLUETOOTH, PhoneEvent.SYNC_CONTACT_RESULT};
    }
}
