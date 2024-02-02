package com.xiaopeng.speech.protocol.query.xpu;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.speech.QueryXpuEvent;
/* loaded from: classes2.dex */
public class XpuQuery_Processor implements IQueryProcessor {
    private XpuQuery mTarget;

    public XpuQuery_Processor(XpuQuery xpuQuery) {
        this.mTarget = xpuQuery;
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -1809695763) {
            if (hashCode == -603903460 && str.equals(QueryXpuEvent.XPU_IS_ON_ALC)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(QueryXpuEvent.XPU_IS_ON_AUTOPILOT)) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return null;
            }
            return Integer.valueOf(this.mTarget.getALCStatus(str, str2));
        }
        return Integer.valueOf(this.mTarget.getAutoPilotStatus(str, str2));
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{QueryXpuEvent.XPU_IS_ON_AUTOPILOT, QueryXpuEvent.XPU_IS_ON_ALC};
    }
}
