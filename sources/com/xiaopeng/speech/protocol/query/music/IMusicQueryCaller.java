package com.xiaopeng.speech.protocol.query.music;

import com.xiaopeng.speech.IQueryCaller;
/* loaded from: classes2.dex */
public interface IMusicQueryCaller extends IQueryCaller {
    String getHistoryPlayInfo(int i);

    String getPlayAlbum();

    String getPlayArtist();

    String getPlayCategory();

    String getPlayInfo();

    String getPlayLyric();

    default double getPlaySpeed() {
        return 0.0d;
    }

    String getPlayTitle();

    int getPlayType();

    default String getSearchResult(String str) {
        return null;
    }

    int getUsbState();

    boolean hasBluetoothMusicList();

    boolean isBtConnected();

    boolean isCanCollected();

    boolean isCollectListEmpty(int i);

    boolean isHistoryEmpty(int i);

    boolean isKuGouAuthed();

    default boolean isMusicAccountLogin() {
        return false;
    }

    default boolean isPlayCollect(String str) {
        return false;
    }

    default boolean isPlayHistory(String str) {
        return false;
    }

    default boolean isPlayPodcast(String str) {
        return false;
    }

    boolean isPlaySimilar();

    boolean isPlaying();

    default boolean isQualityPageOpend() {
        return false;
    }

    default boolean isSupportBtPlay() {
        return false;
    }

    default boolean isSupportRadioPlay() {
        return false;
    }

    default boolean isSupportSettime() {
        return false;
    }

    default boolean isSupportSpeed(double d) {
        return false;
    }

    default boolean isSupportSpotifyPlay() {
        return false;
    }

    default boolean isXimalayaAccountLogin() {
        return false;
    }
}
