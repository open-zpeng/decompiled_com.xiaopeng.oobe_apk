package com.xiaopeng.speech.protocol.query.media;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.QueryMediaEvent;
/* loaded from: classes2.dex */
public class MediaQuery extends SpeechQuery<IMediaQueryCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.GET_INFO_QUERY)
    public String getMediaInfo(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).getMediaInfo();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.PLAY)
    public int play(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).play(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.PAUSE)
    public int pause(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).pause(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.RESUME)
    public int resume(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).resume(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.STOP)
    public int stop(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).stop(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.CLOSE)
    public int close(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).close(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.PREV)
    public int prev(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).prev(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.NEXT)
    public int next(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).next(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.PLAY_MODE)
    public int playMode(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).playMode(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.CONTROL_COLLECT)
    public int collect(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).collect(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.CANCEL_COLLECT)
    public int cancelCollect(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).cancelCollect(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.FORWARD)
    public int forward(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).forward(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.BACKWARD)
    public int backward(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).backward(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.SETTIME)
    public int setTime(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).setTime(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.SPEEDUP)
    public int speedUp(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).speedUp(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.SPEEDDOWN)
    public int speedDown(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).speedDown(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.SPEECHSET)
    public int speedSet(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).speedSet(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_MUSIC_PLAY_LIST)
    public int mediaListPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaListPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_AUDIOBOOK_PLAY_LIST)
    public int mediaAudioBookListPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaAudioBookListPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_MUSIC_CONTROL_PLAY)
    public int mediaMusicPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaMusicPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_XMLY_CONTROL_PLAY)
    public int mediaAudioBookPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaAudioBookPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_FM_LOCAL_ON)
    public int mediaFmLocalOn(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaFmLocalOn(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_MUSIC_NEWS_PLAY)
    public int mediaMusicNewsPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaMusicNewsPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_MUSIC_DAILYREC_PLAY)
    public int mediaMusicDailyrecPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaMusicDailyrecPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_FM_PLAY_PERSONALITY)
    public int mediaMusicPersonalityPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaMusicPersonalityPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_MUSIC_CONTROL_BLUETOOTH_PLAY_RANDOM)
    public int mediaBluetoothPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaBluetoothPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_MUSIC_CONTROL_PLAY_USB)
    public int mediaUsbPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaUsbPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_MUSIC_CONTROL_COLLECT_PLAY)
    public int mediaCollectPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaCollectPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.MEDIA_MUSIC_CONTROL_PLAYLIST_HISTORY_PLAY)
    public int mediaHistoryListPlay(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).mediaHistoryListPlay(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryMediaEvent.PLAY_MODE_CLOSE)
    public int playModeClose(String str, String str2) {
        return ((IMediaQueryCaller) this.mQueryCaller).playModeClose(str2);
    }
}
