package com.xiaopeng.speech.protocol.node.video;

import com.xiaopeng.speech.INodeListener;
/* loaded from: classes2.dex */
public interface VideoListener extends INodeListener {
    default void onVideoAudioMode(String str) {
    }

    default void onVideoBackward(String str) {
    }

    default void onVideoClarityDown(String str) {
    }

    default void onVideoClaritySet(String str) {
    }

    default void onVideoClarityUP(String str) {
    }

    default void onVideoCollect(String str) {
    }

    default void onVideoCollectCancel(String str) {
    }

    default void onVideoDemand(String str) {
    }

    default void onVideoEnd(String str) {
    }

    default void onVideoForward(String str) {
    }

    default void onVideoNext(String str) {
    }

    default void onVideoPause(String str) {
    }

    default void onVideoPrev(String str) {
    }

    default void onVideoResume(String str) {
    }

    default void onVideoSet(String str) {
    }

    default void onVideoSettime(String str) {
    }

    default void onVideoSkipBegin(String str) {
    }

    default void onVideoSkipEnd(String str) {
    }

    default void onVideoSpeedDown(String str) {
    }

    default void onVideoSpeedSet(String str) {
    }

    default void onVideoSpeedUp(String str) {
    }
}
