package com.xiaopeng.speech.protocol.node.media;

import com.xiaopeng.speech.INodeListener;
/* loaded from: classes2.dex */
public interface MediaTriggerListener extends INodeListener {
    default void onCancel() {
    }

    default void onSubmit() {
    }
}
