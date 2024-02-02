package com.xiaopeng.speech.protocol.query.phone;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.QueryPhoneEvent;
/* loaded from: classes2.dex */
public class PhoneQuery extends SpeechQuery<IPhoneQueryCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryPhoneEvent.GET_PAGE_OPEN_STATUS)
    public int getGuiPageOpenState(String str, String str2) {
        return ((IPhoneQueryCaller) this.mQueryCaller).getGuiPageOpenState(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryPhoneEvent.GET_BLUETOOTH_STATUS)
    public boolean onQueryBluetooth(String str, String str2) {
        return ((IPhoneQueryCaller) this.mQueryCaller).onQueryBluetooth();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryPhoneEvent.GET_CONTACT_SYNC_STATUS)
    public int onQueryContactSyncStatus(String str, String str2) {
        return ((IPhoneQueryCaller) this.mQueryCaller).onQueryContactSyncStatus();
    }
}
