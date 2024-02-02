package com.xiaopeng.speech.protocol.query.media;

import com.xiaopeng.speech.IQueryCaller;
/* loaded from: classes2.dex */
public interface IMediaVideoQueryCaller extends IQueryCaller {
    default String getDemandApp(String str) {
        return null;
    }

    default String getMediaVideoInfo() {
        return null;
    }
}
