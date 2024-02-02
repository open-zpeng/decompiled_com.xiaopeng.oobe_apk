package com.xiaopeng.speech.protocol.query.music;

import com.xiaopeng.speech.IQueryCaller;
/* loaded from: classes2.dex */
public interface ISingQueryCaller extends IQueryCaller {
    default int getSingStatus(String str) {
        return -1;
    }
}
