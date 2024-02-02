package com.xiaopeng.speech.protocol.query.media;

import com.xiaopeng.speech.IQueryCaller;
/* loaded from: classes2.dex */
public interface IMediaQueryCaller extends IQueryCaller {
    default int backward(String str) {
        return 0;
    }

    default int cancelCollect(String str) {
        return 0;
    }

    default int close(String str) {
        return 0;
    }

    default int collect(String str) {
        return 0;
    }

    default int forward(String str) {
        return 0;
    }

    default String getMediaInfo() {
        return null;
    }

    default int mediaAudioBookListPlay(String str) {
        return 0;
    }

    default int mediaAudioBookPlay(String str) {
        return 0;
    }

    default int mediaBluetoothPlay(String str) {
        return 0;
    }

    default int mediaCollectPlay(String str) {
        return 0;
    }

    default int mediaFmLocalOn(String str) {
        return 0;
    }

    default int mediaHistoryListPlay(String str) {
        return 0;
    }

    default int mediaListPlay(String str) {
        return 0;
    }

    default int mediaMusicDailyrecPlay(String str) {
        return 0;
    }

    default int mediaMusicNewsPlay(String str) {
        return 0;
    }

    default int mediaMusicPersonalityPlay(String str) {
        return 0;
    }

    default int mediaMusicPlay(String str) {
        return 0;
    }

    default int mediaUsbPlay(String str) {
        return 0;
    }

    default int next(String str) {
        return 0;
    }

    default int pause(String str) {
        return 0;
    }

    default int play(String str) {
        return 0;
    }

    default int playMode(String str) {
        return 0;
    }

    default int playModeClose(String str) {
        return 0;
    }

    default int prev(String str) {
        return 0;
    }

    default int resume(String str) {
        return 0;
    }

    default int setTime(String str) {
        return 0;
    }

    default int speedDown(String str) {
        return 0;
    }

    default int speedSet(String str) {
        return 0;
    }

    default int speedUp(String str) {
        return 0;
    }

    default int stop(String str) {
        return 0;
    }
}
