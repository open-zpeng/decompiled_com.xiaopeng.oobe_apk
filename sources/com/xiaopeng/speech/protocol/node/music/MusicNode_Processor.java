package com.xiaopeng.speech.protocol.node.music;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.MusicEvent;
/* loaded from: classes2.dex */
public class MusicNode_Processor implements ICommandProcessor {
    private MusicNode mTarget;

    public MusicNode_Processor(MusicNode musicNode) {
        this.mTarget = musicNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -2013000544:
                if (str.equals(MusicEvent.PLAY_LOOP_ALL)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -2002174621:
                if (str.equals(MusicEvent.MUSIC_SPEED_SET)) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case -1938308159:
                if (str.equals(MusicEvent.MUSIC_SPEED_DOWN)) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -1829687228:
                if (str.equals(MusicEvent.PLAY_MODE_SUPPORT)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1756385993:
                if (str.equals(MusicEvent.PLAY_LOOP_CLOSE)) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            case -1728394409:
                if (str.equals(MusicEvent.MUSIC_SETTIME)) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case -1627989588:
                if (str.equals(MusicEvent.MUSIC_LIST_PLAY)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1425904452:
                if (str.equals(MusicEvent.NEXT)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1425838851:
                if (str.equals(MusicEvent.PLAY)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1425832964:
                if (str.equals(MusicEvent.PREV)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1425741365:
                if (str.equals(MusicEvent.STOP)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1353272389:
                if (str.equals(MusicEvent.MUSIC_BACKWARD)) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -1311512198:
                if (str.equals(MusicEvent.MUSIC_SPEED_UP)) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -1292964230:
                if (str.equals(MusicEvent.CANCEL_PLAY_SIMILAR)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -1251639987:
                if (str.equals(MusicEvent.PAUSE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1236424121:
                if (str.equals(MusicEvent.CANCEL_COLLECT)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1071838543:
                if (str.equals(MusicEvent.PLAY_SPOTIFY)) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case -782232511:
                if (str.equals(MusicEvent.PLAY_HISTORY_LIST)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -679814637:
                if (str.equals(MusicEvent.MUSIC_DAILYREC_PLAY)) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case -496865250:
                if (str.equals(MusicEvent.SOUND_EFFECT_STEREO)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -447517996:
                if (str.equals(MusicEvent.PLAY_BLUETOOTH)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -362899903:
                if (str.equals(MusicEvent.PLAY_COLLECT)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -151956081:
                if (str.equals(MusicEvent.SOUND_EFFECT_VOCAL)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -102819689:
                if (str.equals(MusicEvent.MUSIC_NEWS_PLAY)) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case -96514067:
                if (str.equals(MusicEvent.MUSIC_FORWARD)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case -89098164:
                if (str.equals(MusicEvent.PLAY_LOOP_RANDOM)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -85238858:
                if (str.equals(MusicEvent.RESUME)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -28153286:
                if (str.equals(MusicEvent.TWELVE_NOVEL_PLAY)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 554746164:
                if (str.equals(MusicEvent.SEARCH)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 720689622:
                if (str.equals(MusicEvent.PLAY_RANDOM_CLOSE)) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case 899591924:
                if (str.equals(MusicEvent.SOUND_EFFECT_SUPERBASS)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 1260718851:
                if (str.equals(MusicEvent.AUDIO_BOOK_SUBSCRIBE)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1313205723:
                if (str.equals(MusicEvent.AUDIO_BOOK_PLAY)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1504460481:
                if (str.equals(MusicEvent.CONTROL_COLLECT)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1616575982:
                if (str.equals(MusicEvent.PLAY_SIMILAR)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 1657363090:
                if (str.equals(MusicEvent.SOUND_EFFECT_LIVE)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 1841863913:
                if (str.equals(MusicEvent.PLAY_LOOP_SINGLE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1903252755:
                if (str.equals(MusicEvent.PLAY_USB)) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.mTarget.onPlay(str, str2);
                return;
            case 1:
                this.mTarget.onPlayLoopSingle(str, str2);
                return;
            case 2:
                this.mTarget.onPlayLoopAll(str, str2);
                return;
            case 3:
                this.mTarget.onPlayLoopRandom(str, str2);
                return;
            case 4:
                this.mTarget.onSearch(str, str2);
                return;
            case 5:
                this.mTarget.onPause(str, str2);
                return;
            case 6:
                this.mTarget.onResume(str, str2);
                return;
            case 7:
                this.mTarget.onPrev(str, str2);
                return;
            case '\b':
                this.mTarget.onNext(str, str2);
                return;
            case '\t':
                this.mTarget.onStop(str, str2);
                return;
            case '\n':
                this.mTarget.onPlayBlueTooth(str, str2);
                return;
            case 11:
                this.mTarget.onSupportPlayModeChange(str, str2);
                return;
            case '\f':
                this.mTarget.onAudioBookPlay(str, str2);
                return;
            case '\r':
                this.mTarget.onMusicListPlay(str, str2);
                return;
            case 14:
                this.mTarget.onTwelveNovelPlay(str, str2);
                return;
            case 15:
                this.mTarget.onControlCollect(str, str2);
                return;
            case 16:
                this.mTarget.onAudioBookSubscribe(str, str2);
                return;
            case 17:
                this.mTarget.onSoundEffectStereo(str, str2);
                return;
            case 18:
                this.mTarget.onSoundEffectLive(str, str2);
                return;
            case 19:
                this.mTarget.onSoundEffectVocal(str, str2);
                return;
            case 20:
                this.mTarget.onSoundEffectSuperbass(str, str2);
                return;
            case 21:
                this.mTarget.onDelCollect(str, str2);
                return;
            case 22:
                this.mTarget.onPlayCollect(str, str2);
                return;
            case 23:
                this.mTarget.onPlaySimilar(str, str2);
                return;
            case 24:
                this.mTarget.onCancelPlaySimilar(str, str2);
                return;
            case 25:
                this.mTarget.onPlayHistoryList(str, str2);
                return;
            case 26:
                this.mTarget.onMusicForward(str, str2);
                return;
            case 27:
                this.mTarget.onMusicBackward(str, str2);
                return;
            case 28:
                this.mTarget.onMusicSettime(str, str2);
                return;
            case 29:
                this.mTarget.onMusicSpeedUp(str, str2);
                return;
            case 30:
                this.mTarget.onMusicSpeedDown(str, str2);
                return;
            case 31:
                this.mTarget.onMusicSpeedSet(str, str2);
                return;
            case ' ':
                this.mTarget.onMusicNewsPlay(str, str2);
                return;
            case '!':
                this.mTarget.onMusicDailyrecPlay(str, str2);
                return;
            case '\"':
                this.mTarget.onPlayUsb(str, str2);
                return;
            case '#':
                this.mTarget.onPlaySpotify(str, str2);
                return;
            case '$':
                this.mTarget.onPlayLoopClose(str, str2);
                return;
            case '%':
                this.mTarget.onPlayRandomClose(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{MusicEvent.PLAY, MusicEvent.PLAY_LOOP_SINGLE, MusicEvent.PLAY_LOOP_ALL, MusicEvent.PLAY_LOOP_RANDOM, MusicEvent.SEARCH, MusicEvent.PAUSE, MusicEvent.RESUME, MusicEvent.PREV, MusicEvent.NEXT, MusicEvent.STOP, MusicEvent.PLAY_BLUETOOTH, MusicEvent.PLAY_MODE_SUPPORT, MusicEvent.AUDIO_BOOK_PLAY, MusicEvent.MUSIC_LIST_PLAY, MusicEvent.TWELVE_NOVEL_PLAY, MusicEvent.CONTROL_COLLECT, MusicEvent.AUDIO_BOOK_SUBSCRIBE, MusicEvent.SOUND_EFFECT_STEREO, MusicEvent.SOUND_EFFECT_LIVE, MusicEvent.SOUND_EFFECT_VOCAL, MusicEvent.SOUND_EFFECT_SUPERBASS, MusicEvent.CANCEL_COLLECT, MusicEvent.PLAY_COLLECT, MusicEvent.PLAY_SIMILAR, MusicEvent.CANCEL_PLAY_SIMILAR, MusicEvent.PLAY_HISTORY_LIST, MusicEvent.MUSIC_FORWARD, MusicEvent.MUSIC_BACKWARD, MusicEvent.MUSIC_SETTIME, MusicEvent.MUSIC_SPEED_UP, MusicEvent.MUSIC_SPEED_DOWN, MusicEvent.MUSIC_SPEED_SET, MusicEvent.MUSIC_NEWS_PLAY, MusicEvent.MUSIC_DAILYREC_PLAY, MusicEvent.PLAY_USB, MusicEvent.PLAY_SPOTIFY, MusicEvent.PLAY_LOOP_CLOSE, MusicEvent.PLAY_RANDOM_CLOSE};
    }
}
