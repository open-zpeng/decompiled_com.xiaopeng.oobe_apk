package com.xiaopeng.speech.protocol.query.media;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.QueryMediaEvent;
/* loaded from: classes2.dex */
public class MediaVideoQuery_Processor implements IQueryProcessor {
    private MediaVideoQuery mTarget;

    public MediaVideoQuery_Processor(MediaVideoQuery mediaVideoQuery) {
        this.mTarget = mediaVideoQuery;
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -941695413) {
            if (hashCode == -555849819 && str.equals(QueryMediaEvent.GET_MEDIAZ_VIDEO_INFO_QUERY)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(QueryMediaEvent.DEMAND_VIDEO_APP)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return null;
            }
            return this.mTarget.getDemandApp(str, str2);
        }
        return this.mTarget.getMediaVideoInfo(str, str2);
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{QueryMediaEvent.GET_MEDIAZ_VIDEO_INFO_QUERY, QueryMediaEvent.DEMAND_VIDEO_APP};
    }
}
