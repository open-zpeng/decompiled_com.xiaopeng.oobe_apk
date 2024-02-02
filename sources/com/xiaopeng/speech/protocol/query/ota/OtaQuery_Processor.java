package com.xiaopeng.speech.protocol.query.ota;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.OtaEvent;
/* loaded from: classes2.dex */
public class OtaQuery_Processor implements IQueryProcessor {
    private OtaQuery mTarget;

    public OtaQuery_Processor(OtaQuery otaQuery) {
        this.mTarget = otaQuery;
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        if (((str.hashCode() == 1851114339 && str.equals(OtaEvent.IS_LATEST_OTA_VERSION)) ? (char) 0 : (char) 65535) != 0) {
            return null;
        }
        return Boolean.valueOf(this.mTarget.isLatestVersion(str, str2));
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{OtaEvent.IS_LATEST_OTA_VERSION};
    }
}
