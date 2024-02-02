package com.xiaopeng.speech.protocol.query.phone;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.QueryPhoneEvent;
/* loaded from: classes2.dex */
public class PhoneQuery_Processor implements IQueryProcessor {
    private PhoneQuery mTarget;

    public PhoneQuery_Processor(PhoneQuery phoneQuery) {
        this.mTarget = phoneQuery;
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -991919164) {
            if (str.equals(QueryPhoneEvent.GET_PAGE_OPEN_STATUS)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 579161930) {
            if (hashCode == 1113652927 && str.equals(QueryPhoneEvent.GET_CONTACT_SYNC_STATUS)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals(QueryPhoneEvent.GET_BLUETOOTH_STATUS)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    return null;
                }
                return Integer.valueOf(this.mTarget.onQueryContactSyncStatus(str, str2));
            }
            return Boolean.valueOf(this.mTarget.onQueryBluetooth(str, str2));
        }
        return Integer.valueOf(this.mTarget.getGuiPageOpenState(str, str2));
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{QueryPhoneEvent.GET_PAGE_OPEN_STATUS, QueryPhoneEvent.GET_BLUETOOTH_STATUS, QueryPhoneEvent.GET_CONTACT_SYNC_STATUS};
    }
}
